package EchecSolitaire.Metier;

import EchecSolitaire.Metier.Piece.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;

public class Plateau 
{
	private static String TETE_FICH  = "./data/niveau";
	private static String QUEUE_FICH = ".txt";


	private int           nbCoup;

	private int           niv;
	private int           nivMax;
	private ArrayList<Piece>  tabPieces;
	private String            difficultes;

	private ArrayList<Action> tabActions;

	public Plateau ()
	{
		this.niv ++;
		this.tabPieces  = new ArrayList<Piece>();
		this.tabActions = new ArrayList<Action>();
		this.chargerNiveau(Plateau.TETE_FICH + 1 + Plateau.QUEUE_FICH);
	}

	public Piece estSurCase (int l, int c)
	{
		for (Piece p : this.tabPieces)
			if (p.caseOccupe(l,c)) //On regarde si un pion est sur la case, renvoie le 1er.
				return p;
		
		return null;
	}

	

	private boolean chargerNiveau (String nomFic)
	{
		try 
		{
			Scanner scan = new Scanner(new FileReader(nomFic)); //Essaye de lire le fichier

			this.tabActions = new ArrayList<Action>();
			int nbLig = 0;

			//On prend la difficultés
			this.difficultes = scan.next(); 

			//Pour chaque ligne
			while (scan.hasNext())
			{
				String s = scan.nextLine();

				//Si ca commence par '|'
				if (s.startsWith("|"))
				{
					nbLig ++;
					this.ajouterPiece(s, nbLig); //On ajoute la pièce
				}
			}

			scan.close();
		}
		//Si on a eu un problème, on prévient qu'on a pas réussi à charger le tableau
		catch (Exception e) { return false ; } 

		//Sinon on renvoie vrai
		return true;
	}



	private void ajouterPiece(String s, int lig)
	{
		int col = 0;
		for (int i = 2; i < s.length(); i += 4) //Position des lettres
		{
			if (s.charAt(i) != ' ') //Si il y a un symbole
			{
				char c = s.charAt(i);


				//On crée une piece selon le symbole
				//Note : pas de switch, car tentative échoué de rendre le truc modulable
				if (c == 'F')
					this.tabPieces.add(new Fou(lig, (char) (col +'A')));

				if (c == 'K')
					this.tabPieces.add(new Roi(lig, (char) (col +'A')));

				if (c == 'Q')
					this.tabPieces.add(new Reine(lig, (char) (col +'A')));

				if (c == 'C')
					this.tabPieces.add(new Cavalier(lig, (char) (col +'A')));

				if (c == 'P')
					this.tabPieces.add(new Pion(lig, (char) (col +'A')));	

				if (c == 'T')
					this.tabPieces.add(new Tour(lig, (char) (col +'A')));				
			}
			//On incrémente col pour bien le placer sur le plateau
			col ++;
		}
	}

	//Méthode crée seulement pour la fase test
	public String toString ()
	{
		String sRep = this.difficultes + "\n";

		for (Piece p : this.tabPieces)
			sRep += p.toString() + "\n";

		return sRep;
	}

	//Return le symbole d'une pièce sur une case
	public char getSymbole (int l, int c)
	{
		Piece p = estSurCase(l, c);
		if (p == null ) return ' ';

		return p.getSymbole();
	}

	public String getDifficultes() { return this.difficultes;} //Retourne la difficultés
	public int    getNbCoup     () { return this.nbCoup;     } //Retourne le nombre de coup

	//Avale une pièce
	public boolean avaler (Piece p1, Piece p2)
	{
		int  lDest = p2.getPosLig() + 1;
		char cDest = (char) (p2.getPosCol() + 'A');

		if ( ! p1.deplacementPossible(p2.getPosLig() ,p2.getPosCol(),this.tabPieces))
			return false;

		this.tabActions.add(new Action(p1, p2)); //On créer une action avec les deux pièces
		p1.deplacer(lDest, cDest, this.tabPieces);
		this.tabPieces.remove(p2);

		this.nbCoup ++;

		return true;
	}

	//Permet le ctrl Z
	public boolean retour ()
	{
		if (this.tabActions.size() == 0) return false;

		Action a = this.tabActions.get(this.tabActions.size() - 1);
		a.p1.deplacer(a.l + 1, (char) (a.c + 'A'), this.tabPieces);

		this.tabPieces.add(a.p2);
		this.tabActions.remove(a);

		return true;
	}

	public void relancerNiv() 
	{ 
		this.tabPieces.clear();
		this.tabActions.clear();
		this.chargerNiveau(Plateau.TETE_FICH + this.niv + Plateau.QUEUE_FICH);
	}

	public boolean nivSuivant () 
	{ 
		if (this.tabPieces.size() != 1 && this.niv >= this.nivMax)
			return false;
		if (this.niv == 60 ) return false;
		
		this.niv++;

		if (this.niv > 24) this.niv ++; //Car il y a pas de niv 25,27,29, etc...

		if (this.niv > this.nivMax) this.nivMax = this.niv;

		this.tabPieces.clear();
		this.tabActions.clear();
		this.chargerNiveau(Plateau.TETE_FICH + this.niv + Plateau.QUEUE_FICH);
		this.nbCoup = 0;
		return true;
	}
	
	public boolean nivPrecedent () 
	{ 
		if (this.niv == 1) return false;
		
		this.niv--;
		this.tabPieces.clear();
		this.tabActions.clear();
		this.chargerNiveau(Plateau.TETE_FICH + this.niv + Plateau.QUEUE_FICH);
		this.nbCoup = 0;
		return true;
	}
}
