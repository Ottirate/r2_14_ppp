package EchecSolitaire.Metier.Piece;

import java.util.ArrayList;

public class Roi extends Piece
{
	public Roi (int lig, char col)
	{
		super (lig, col);
	}

	public boolean deplacementPossible(int lig, int col, ArrayList<Piece> tabPieces)
	{
		//Si il essaye de bouger de + de une cases
		if ( lig-this.posLig > 1 ||  lig-this.posLig < -1 ) return false;
		if ( col-this.posCol > 1 ||  col-this.posCol < -1 ) return false;


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
	
	public char getSymbole() { return 'K'; }
}
