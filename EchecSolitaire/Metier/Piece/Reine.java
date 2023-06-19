package EchecSolitaire.Metier.Piece;

import java.util.ArrayList;

public class Reine extends Piece
{
	public Reine (int lig, char col)
	{
		super (lig, col);
	}

	public boolean deplacementPossible(int lig, int col, ArrayList<Piece> tabPieces)
	{
		//Si elle ne bouge pas en ligne droite
		if (lig != this.posLig && col != this.posCol )
			//Si c'est pas une diagonale alors
			if (Math.abs(lig-this.posLig) != Math.abs(col-this.posCol)) 
				return false;

		int pasL = 0, pasC = 0;

		if (lig < this.posLig) pasL -= 1;
		if (lig > this.posLig) pasL += 1;

		if (col < this.posCol) pasC -= 1;
		if (col > this.posCol) pasC += 1;


		// Si il essaye de passer malgrès la présence de qlq
		for (int cptLig  = this.posLig,  cptCol  = this.posCol; 
		         cptLig != lig        || cptCol != col; 
				 cptLig += pasL       ,  cptCol += pasC)
		{
			for (Piece p : tabPieces)
				if (p != null && p != this && p.caseOccupe(cptLig, cptCol)) return false;
		}

		return super.deplacementPossible(lig, col, tabPieces);
	}
	
	public char getSymbole() { return 'Q'; }
}
