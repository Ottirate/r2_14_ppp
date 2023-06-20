import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;


public class PanelForme extends JPanel implements ActionListener, ItemListener, AdjustmentListener
{
	private Controleur   ctrl;

	private JButton      btnValider;

	private JTextField   txtFigure;

	private JScrollBar   sbNbFormes;
	private JLabel       lblNbFormes;

	private JCheckBox[]  cbFormes;
	private int          formeSelectionne;

	private JRadioButton rbUni;
	private JRadioButton rbBi;

	private JComboBox<String>  ddlstCoul1;
	private JComboBox<String>  ddlstCoul2;

	


	public PanelForme (Controleur ctrl, String [] forme, String[] coul)
	{
		this.ctrl             = ctrl;
		this.formeSelectionne = 0;

		/*                         */
		/*   Paramètre personnel   */
		/*                         */

		this.setLayout(new BorderLayout());



		/*                         */
		/* Creation des composants */
		/*                         */

		//On crée les panel tempo
		JPanel panelHaut = new JPanel(new GridLayout(8,1));
		JPanel panelFig  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panelNbCoul = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelCoul   = new JPanel(new GridLayout(2,2));

		//
		this.txtFigure  = new JTextField();
		this.btnValider = new JButton("Valider");

		this.cbFormes = new JCheckBox[forme.length];

		for (int cpt = 0; cpt < forme.length; cpt++)
			this.cbFormes[cpt] = new JCheckBox(forme[cpt]);
		
		this.sbNbFormes = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 1, forme.length + 1);

		this.lblNbFormes = new JLabel("Nombres de formes (1)");

		ButtonGroup bgCoul = new ButtonGroup();

		this.rbUni = new JRadioButton("unicolore", true);
		this.rbBi  = new JRadioButton("bicolore ");

		this.ddlstCoul1 = new JComboBox<String>();
		this.ddlstCoul2 = new JComboBox<String>();

		for (int cpt = 0; cpt < coul.length; cpt ++)
		{
			this.ddlstCoul1.addItem(coul[cpt]);
			this.ddlstCoul2.addItem(coul[cpt]);
		}

		this.ddlstCoul2.setEnabled(false);


		/*                        */
		/* Ajouter les composants */
		/*                        */

		for (JCheckBox cb : this.cbFormes)
			panelFig.add(cb);

		
		bgCoul.add(this.rbUni);
		bgCoul.add(this.rbBi );

		panelNbCoul.add(this.rbUni);
		panelNbCoul.add(this.rbBi );

		panelCoul.add(new JLabel("Couleur 1"));
		panelCoul.add(new JLabel("Couleur 2"));
		panelCoul.add(this.ddlstCoul1);
		panelCoul.add(this.ddlstCoul2);


		panelHaut.add(new JLabel("Nom de la figure"));
		panelHaut.add(this.txtFigure);

		panelHaut.add(this.lblNbFormes);
		panelHaut.add(this.sbNbFormes);

		panelHaut.add(new JLabel("Formes :"));
		panelHaut.add(panelFig);

		panelHaut.add(panelNbCoul);

		panelHaut.add(panelCoul);

		this.add(panelHaut      , BorderLayout.NORTH );
		this.add(this.btnValider, BorderLayout.SOUTH );


		
		/*                           */
		/* Activation des composants */
		/*                           */
		this.btnValider.addActionListener(this);
		
		for (JCheckBox cb : this.cbFormes)
			cb.addItemListener(this);
		
		this.sbNbFormes.addAdjustmentListener(this);

		this.rbUni.addItemListener(this);
		this.rbBi .addItemListener(this);
		
	}




	public void paintComponent (Graphics g)
	{
		super.paintComponent ( g );
		Graphics2D g2 = (Graphics2D) g;


		// Dessine une image en bas Du Panel
		g2.drawImage( this.getToolkit().getImage ( "../images/vide.png" ),  5, 350, this );

	}



	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnValider)
		{ 
			String figure = "";

			for (JCheckBox cb : this.cbFormes)
				if (cb.isSelected())
					figure += cb.getText() + " ";

			System.out.println("Nom de la figure         : " + this.txtFigure.getText()  + "\n" + 
			                   "Nombre max de figure     : " + this.cbFormes.length      + "\n" +
			                   "Nombre de forme possible : " + this.sbNbFormes.getValue()+ "\n" +
			                   "Figure selectionne       : " + figure                    + "\n" 
							  );
		}
	}




	public void itemStateChanged(ItemEvent e) 
	{
		if (this.rbUni == e.getSource())
			this.ddlstCoul2.setEnabled(false);

		if (this.rbBi == e.getSource())
			this.ddlstCoul2.setEnabled(true);
		
		//Si c'est un des JCheckBox
		if (e.getSource() instanceof JCheckBox)
			for (JCheckBox cb : this.cbFormes)
				if (cb == e.getSource())
				{
					if (cb.isSelected())
					{
						if (this.formeSelectionne >= this.sbNbFormes.getValue())
							cb.setSelected(false);
						this.formeSelectionne ++;
					}
					else
					{
						this.formeSelectionne--;
					}
				}
	}

	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		this.lblNbFormes.setText("Nombre de forme (" + this.sbNbFormes.getValue() + ")");

		for (JCheckBox cb : this.cbFormes)
			cb.setSelected(false);
	}

}