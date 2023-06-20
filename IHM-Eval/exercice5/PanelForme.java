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

		//On crée des panels tempo
		JPanel panelHaut = new JPanel(new GridLayout(8,1));
		JPanel panelFig  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panelNbCoul = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelCoul   = new JPanel(new GridLayout(2,2));


		//TextFields
		this.txtFigure  = new JTextField();

		//JButton
		this.btnValider = new JButton("Valider");

		//JCheckBox
		this.cbFormes = new JCheckBox[forme.length];
		for (int cpt = 0; cpt < forme.length; cpt++)
			this.cbFormes[cpt] = new JCheckBox(forme[cpt]);
		
		//JScrollbar
		this.sbNbFormes = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 1, forme.length + 1);

		//JLabel
		this.lblNbFormes = new JLabel("Nombres de formes (1)");

		//JRadioButton
		ButtonGroup bgCoul = new ButtonGroup(); 
		this.rbUni = new JRadioButton("unicolore", true);
		this.rbBi  = new JRadioButton("bicolore ");

		//JComboBox (DropDownList)
		this.ddlstCoul1 = new JComboBox<String>();
		this.ddlstCoul2 = new JComboBox<String>();

		for (String s : coul)
		{
			this.ddlstCoul1.addItem(s);
			this.ddlstCoul2.addItem(s);
		}

		this.ddlstCoul2.setEnabled(false);





		/*                        */
		/* Ajouter les composants */
		/*                        */

		//On ajoute les checkbox
		for (JCheckBox cb : this.cbFormes)
			panelFig.add(cb);

		
		//On ajoute les radio boutton au ButtonGroup (afin de forcer un seul choix)
		bgCoul.add(this.rbUni);
		bgCoul.add(this.rbBi );

		//On ajoute ces même radio boutton au panel du nombre de couleur selectionné
		panelNbCoul.add(this.rbUni);
		panelNbCoul.add(this.rbBi );

		//On ajoute les JComboBox à leur pannel
		panelCoul.add(new JLabel("Couleur 1"));
		panelCoul.add(new JLabel("Couleur 2"));
		panelCoul.add(this.ddlstCoul1);
		panelCoul.add(this.ddlstCoul2);


		//On ajoute tous les pannel et composant au panelHaut (au Nord)
		panelHaut.add(new JLabel("Nom de la figure"));
		panelHaut.add(this.txtFigure);

		panelHaut.add(this.lblNbFormes);
		panelHaut.add(this.sbNbFormes);

		panelHaut.add(new JLabel("Formes :"));
		panelHaut.add(panelFig);

		panelHaut.add(panelNbCoul);

		panelHaut.add(panelCoul);

		//On ajoute le panelHaut en haut, et le boutton tout en bas
		this.add(panelHaut      , BorderLayout.NORTH );
		this.add(this.btnValider, BorderLayout.SOUTH );


		
		/*                           */
		/* Activation des composants */
		/*                           */

		//Action
		this.btnValider.addActionListener(this);
		

		//Item
		for (JCheckBox cb : this.cbFormes)
			cb.addItemListener(this);

		this.rbUni.addItemListener(this);
		this.rbBi .addItemListener(this);
		

		//Adjustment
		this.sbNbFormes.addAdjustmentListener(this);
	}




	public void paintComponent (Graphics g)
	{
		super.paintComponent ( g );
		Graphics2D g2 = (Graphics2D) g;


		// Dessine une image en bas Du Panel
		//Note : j'ai légérement changer 'y' pour bien voir l'image.


		//On met les couleurs avant
		String coul1, coul2;
		if (this.rbBi.isSelected())
		{
			coul1 = this.ddlstCoul1.getSelectedItem().toString();
			coul2 = this.ddlstCoul2.getSelectedItem().toString();
		}
		else
			coul1 = coul2 = this.ddlstCoul1.getSelectedItem().toString();

		
		g2.drawImage( this.getToolkit().getImage ( "../images/" + coul1 + "_1.png" ),  5, 385, this ); 
		g2.drawImage( this.getToolkit().getImage ( "../images/" + coul2 + "_2.png" ),  5, 385, this );


		//Ensuite, on dessine chaque figure (si elle est selectionné)
		for (JCheckBox cb : this.cbFormes)
			if (cb.isSelected())
				g2.drawImage( this.getToolkit().getImage ( "../images/"+ cb.getText()+".png" ),  5, 385, this ); 



	}



	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnValider) //Test futile, car c'est le seul composant activer par ActionListener
		{ 
			String figure = "";

			//On regarde toutes les figures selectionné
			for (JCheckBox cb : this.cbFormes)
				if (cb.isSelected())
					figure += cb.getText() + " ";
			
			//On regarde les couleurs
			String coul;
			if (this.rbUni.isSelected()) 
				coul = "Couleur                  : " + this.ddlstCoul1.getSelectedItem().toString();
			else
				coul = "Couleur 1                : " + this.ddlstCoul1.getSelectedItem().toString() + '\n' + 
				       "Couleur 2                : " + this.ddlstCoul2.getSelectedItem().toString() ;


			//On affiche tous dans le terminal
			System.out.println("Nom de la figure         : " + this.txtFigure.getText()   + '\n' + 
			                   "Nombre max de figure     : " + this.cbFormes.length       + '\n' +
			                   "Nombre de forme possible : " + this.sbNbFormes.getValue() + '\n' +
			                   "Figure selectionne       : " + figure                     + '\n' +
			                   coul + '\n' 
							  );
		}

		this.repaint();
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

		this.repaint();
	}

	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		this.lblNbFormes.setText("Nombre de forme (" + this.sbNbFormes.getValue() + ")");

		for (JCheckBox cb : this.cbFormes)
			cb.setSelected(false);
	}

}