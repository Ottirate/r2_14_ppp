package EchecSolitaire;

import ihmgui.FrameGrille; 
import ihmgui.Controle;

import EchecSolitaire.Metier.Plateau;
import EchecSolitaire.Metier.Piece.*;
                                                         
public class Controleur extends Controle
{                        

	private FrameGrille ihm;
	private Plateau     metier;


	public Controleur ()
	{
		this.metier = new Plateau();

		System.out.println(this.metier);

		this.ihm    = new FrameGrille(this);

		ihm.setSize     ( 650, 400     );
		ihm.setLocation ( 200,  10     );
		ihm.setTitle    ( "Exemple"    );
		ihm.setVisible  ( true         );
	}

	public String setBouton(int numBtn)
	{
		String lib;

		switch ( numBtn )
		{
			case 0 : lib = "Reccomencer";      break;
			case 1 : lib = "Niveau Suivant";   break;
			case 2 : lib = "Niveau Precedent"; break;
			default: lib = null;                      
		}

		return lib;
	}

	public String setLabel (int numLbl)
	{
		String lib;

		switch ( numLbl )
		{
			case 0 : lib = "Nb coups";        break;
			default: lib = null;
		}

		return lib;
	}
	
	public String  setTextLabel      (int numLbl)
	{
		return "" + metier.getNbCoup ();
	}

	public void jouer (String touche)
	{
		if ( touche.equals ( "CR-Z" ) ) 
			this.metier.retour();
		
		this.ihm.majIHM();
	}

	public String setImage ( int ligne, int colonne, int couche)
	{
		char   symbole;
		String rep = "./Images/";
		String sImage=null;

		if ( couche==0)
		{
			symbole = metier.getSymbole (ligne, colonne);

			if ( symbole == ' ' ) sImage = rep + "vide52.gif"  ;
			if ( symbole == 'C' ) sImage = rep + "cavalier.gif";
			if ( symbole == 'F' ) sImage = rep + "fou.gif"     ;
			if ( symbole == 'P' ) sImage = rep + "pion.gif"    ;
			if ( symbole == 'Q' ) sImage = rep + "reine.gif"   ;
			if ( symbole == 'K' ) sImage = rep + "roi.gif"     ;
			if ( symbole == 'T' ) sImage = rep + "tour.gif"    ;
		}

		return sImage;
	}

	public int     setNbLigne        () { return 4; }
	public int     setNbColonne      () { return 4; }
	public boolean setNumLigneColonne() { return true;}
	public int     setLargeurImg     () { return 50; }

	public String  setFondGrille     () 
	{ 
		String sRep = "./Images/plateau";
		String diff = this.metier.getDifficultes();

		if (diff.equals("Débutant"))
			sRep += "vert";
		if (diff.equals("Intermédiaire"))
			sRep += "bleu";
		if (diff.equals("Avancé"))
			sRep += "violet";
		if (diff.equals("Expert"))
			sRep += "rouge";


		return sRep + ".gif";                  
	}

	public void glisser( int l1, int c1, int l2, int c2)
	{
		Piece p1 = this.metier.estSurCase(l1,c1);
		Piece p2 = this.metier.estSurCase(l2,c2);

		if (p1 != null && p2 != null && p1 != p2)
			this.metier.avaler(p1,p2);

		this.ihm.majIHM();
	}

	public void bouton​(int numBtn)
	{
		switch (numBtn)
		{
			case 0 -> this.metier.relancerNiv ();
			case 1 -> this.metier.nivSuivant  ();
			case 2 -> this.metier.nivPrecedent();
		}

		this.ihm.majIHM();
	}

	public static void main(String[] args) {
		Controleur c = new Controleur();

	}

                                                        
}    