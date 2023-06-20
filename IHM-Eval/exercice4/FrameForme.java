import javax.swing.*;

public class FrameForme extends JFrame
{
	private static final String[] libelleFormes  = new String [] { "cercle", "triangle", "hexagone", "croix", "étoile" };
	private static final String[] libelleCouleurs = new String [] { "vert", "rouge", "blanc", "bleu", "rose" };

	private PanelForme panelForme;


	public FrameForme ( Controleur ctrl )
	{
		this.setTitle("Forme");
		this.setSize(500,650);
		this.setLocation(10,10);
		
		/*-------------------------------*/
		/* Création des composants       */
		/*-------------------------------*/
		this.panelForme = new PanelForme ( ctrl, FrameForme.libelleFormes, FrameForme.libelleCouleurs);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add ( this.panelForme );

		/* A Compléter */
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}