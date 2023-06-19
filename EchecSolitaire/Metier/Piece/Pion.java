package EchecSolitaire.Metier.Piece;

import java.util.ArrayList;

//Fonctionne
public class Pion extends Piece
{
	public Pion (int lig, char col)
	{
		super (lig, col);
	}

	public boolean deplacementPossible(int lig, int col, ArrayList<Piece> tabPieces)
	{
		//Si il essaye de bouger autrement qu'en diagonale
		if (Math.abs(this.posLig - lig) != Math.abs(this.posCol - col)) return false;


		//Si il essaye de bouger de + de deux cases
		if (Math.abs ( this.posLig - lig ) > 1 ) return false;
		if (Math.abs ( this.posCol - col ) > 1 ) return false;
		

		return super.deplacementPossible(lig, col, tabPieces);
	}
	
	public char getSymbole() { return 'P'; }
}
