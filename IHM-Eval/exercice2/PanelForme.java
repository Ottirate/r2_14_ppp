import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;


public class PanelForme extends JPanel implements ActionListener, ItemListener, AdjustmentListener
{
	private Controleur ctrl;

	private JButton    btnValider;
	private JTextField txtFigure;

	private JScrollBar  sbNbFormes;
	private JLabel      lblNbFormes;

	private JCheckBox[] cbFormes;

	private int         formeSelectionne;

	


	public PanelForme (Controleur ctrl, String [] forme)
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

		//Pannel tempo
		JPanel panelHaut = new JPanel(new GridLayout(6,1));
		JPanel panelFig  = new JPanel(new FlowLayout(FlowLayout.LEFT));

		//JTextField
		this.txtFigure  = new JTextField();
		this.btnValider = new JButton("Valider");

		//JCheckBox
		this.cbFormes = new JCheckBox[forme.length];

		for (int cpt = 0; cpt < forme.length; cpt++)
			this.cbFormes[cpt] = new JCheckBox(forme[cpt]);

		//JScrollBar
		this.sbNbFormes = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 1, forme.length + 1);

		//JLabel
		this.lblNbFormes = new JLabel("Nombres de formes (1)");


		/*                        */
		/* Ajouter les composants */
		/*                        */

		//On ajoute les checkbox au pannel des figures 
		for (JCheckBox cb : this.cbFormes)
			panelFig.add(cb);


		//On ajoute les composants au panneau du haut
		panelHaut.add(new JLabel("Nom de la figure"));
		panelHaut.add(this.txtFigure);

		panelHaut.add(this.lblNbFormes);
		panelHaut.add(this.sbNbFormes );

		panelHaut.add(new JLabel("Formes :"));
		panelHaut.add(panelFig);


		//On ajoute tous ca au panneau principal
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
		
		//Adjustement
		this.sbNbFormes.addAdjustmentListener(this);
		
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
		if (e.getSource() == this.btnValider) //Test futile, seul action activé
		{ 

			//On regarde les figures
			String figure = "";
			for (JCheckBox cb : this.cbFormes)
				if (cb.isSelected())
					figure += cb.getText() + " ";

			//On affiche tous ca
			System.out.println("Nom de la figure         : " + this.txtFigure.getText()  + "\n" + 
			                   "Nombre max de figure     : " + this.cbFormes.length      + "\n" +
			                   "Nombre de forme possible : " + this.sbNbFormes.getValue()+ "\n" +
			                   "Figure selectionne       : " + figure                    + "\n" 
							  );
		}
	}




	public void itemStateChanged(ItemEvent e) 
	{
		//Pour chaque JCheckBox
		for (JCheckBox cb : this.cbFormes)
			if (cb == e.getSource()) //On regarde si c'est celui d'ou vient l'action
			{
				if (cb.isSelected())
				{
					//Si il y a déja assez de figure, on le déselectionne.
					if (this.formeSelectionne >= this.sbNbFormes.getValue())
						cb.setSelected(false);
					this.formeSelectionne ++;
				}
				else
				{
					this.formeSelectionne--;
				}
			}

		//Note : J'incrémente même quand le nombre est plus grand, car le fait de le déselectionné 
		//       réappelle la fonction qui décrémentera le nombre.
	}

	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		this.lblNbFormes.setText("Nombre de forme (" + this.sbNbFormes.getValue() + ")");

		for (JCheckBox cb : this.cbFormes)
			cb.setSelected(false);
	}

}