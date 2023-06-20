import javax.swing.*;

public class FrameForme extends JFrame
{
	private PanelForme panelForme;


	public FrameForme ( Controleur ctrl )
	{
		this.setTitle("Forme");
		this.setSize(500,650);
		this.setLocation(10,10);
		
		/*-------------------------------*/
		/* Création des composants       */
		/*-------------------------------*/
		this.panelForme = new PanelForme ( ctrl );
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add ( this.panelForme );

		/* A Compléter */
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}