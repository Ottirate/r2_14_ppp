package EchecSolitaire.Metier.Piece;

import java.util.ArrayList;

public class Cavalier extends Piece
{
	public Cavalier (int lig, char col)
	{
		super (lig, col);
	}

	public boolean deplacementPossible(int lig, int col,  ArrayList<Piece> tabPieces)
	{

		//Si il essaye de bouger autrement que ses positions bizarre 
		if (!(Math.abs(lig-this.posLig) == 2 && Math.abs(col-this.posCol) == 1) &&
		    !(Math.abs(lig-this.posLig) == 1 && Math.abs(col-this.posCol) == 2)  ) return false;

		return super.deplacementPossible(lig, col, tabPieces);
	}
	
	public char getSymbole() { return 'C'; }
}
