package EchecSolitaire.Metier.Piece;

import java.util.ArrayList;

public abstract class Piece 
{
	public static final int TAILLE_PLATEAU = 4;

	protected int     posLig;
	protected int     posCol;

	protected Piece (int lig, char col)
	{
		this.posLig = lig -  1 ;
		this.posCol = col - 'A';
	}

	/**Permet de savoir si on peut se déplacer.*/
	public boolean deplacementPossible(int lig, int col, ArrayList<Piece> tabPieces)
	{
		//Si ca sort du tableau, c'est non
		if ( lig >= Piece.TAILLE_PLATEAU ) return false;
		if ( col >= Piece.TAILLE_PLATEAU ) return false;

		return true;
	}

	public int getPosCol() { return this.posCol; }
	public int getPosLig() { return this.posLig; }

	public boolean caseOccupe (int l, int c)
	{
		return c == this.posCol && l == this.posLig;
	}

	
	/**Permet de se déplacer.*/
	public boolean deplacer (int ligDest, char colDest,  ArrayList<Piece> tabPieces)
	{
		if (! deplacementPossible(ligDest - 1, colDest-'A', tabPieces)) return false;

		this.posLig = ligDest -  1;
		this.posCol = colDest - 'A';

		return true;
	}

	public abstract char getSymbole();

	public String toString()
	{
		return this.getClass().getSimpleName() + " en position " + (this.posLig + 1) + (char)(this.posCol + 'A');
	}
	
}
