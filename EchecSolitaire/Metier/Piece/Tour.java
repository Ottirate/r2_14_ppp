package EchecSolitaire.Metier.Piece;

import java.util.ArrayList;

// FONCTIONNE
public class Tour extends Piece
{
	public Tour (int lig, char col)
	{
		super (lig, col);
	}

	public boolean deplacementPossible(int lig, int col, ArrayList<Piece> tabPieces)
	{
		if (lig != this.posLig && col != this.posCol ) return false;


		int pasL = 0, pasC = 0;

		if (lig < this.posLig) pasL -= 1;
		if (lig > this.posLig) pasL += 1;

		if (col < this.posCol) pasC -= 1;
		if (col > this.posCol) pasC += 1;

		for (int cptLig  = this.posLig,  cptCol  = this.posCol; 
		         cptLig != lig        || cptCol != col; 
				 cptLig += pasL       ,  cptCol += pasC)
		{
			for (Piece p : tabPieces)
				if (p != null && p != this && p.caseOccupe(cptLig, cptCol)) return false;
		}

		return super.deplacementPossible(lig, col, tabPieces);
	}
	
	public char getSymbole() { return 'T'; }
}
