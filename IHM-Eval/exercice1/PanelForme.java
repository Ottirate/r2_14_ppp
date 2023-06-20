import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;


public class PanelForme extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton    btnValider;
	private JTextField txtFigure;

	


	public PanelForme (Controleur ctrl )
	{
		this.ctrl = ctrl;

		/*                         */
		/*   Paramètre personnel   */
		/*                         */

		this.setLayout(new BorderLayout());



		/*                         */
		/* Creation des composants */
		/*                         */

		//On crée des panels tempo
		JPanel panelHaut = new JPanel(new GridLayout(2,1));

		//JTextField
		this.txtFigure  = new JTextField();

		//JButton
		this.btnValider = new JButton("Valider");


		/*                        */
		/* Ajouter les composants */
		/*                        */

		//On ajoute le label et textfield au pannel du haut
		panelHaut.add(new JLabel("Nom de la figure"));
		panelHaut.add(this.txtFigure);

		//On les ajoute au panel principale
		this.add(panelHaut      , BorderLayout.NORTH );
		this.add(this.btnValider, BorderLayout.SOUTH );


		
		/*                           */
		/* Activation des composants */
		/*                           */

		//Action
		this.btnValider.addActionListener(this);
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
		//Test futile, seule composant activer
		if (e.getSource() == this.btnValider) System.out.println("Nom de la figure : " + this.txtFigure.getText());
	}
}