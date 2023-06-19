package EchecSolitaire.Metier;

import EchecSolitaire.Metier.Piece.*;

public class Action 
{

	//Visibilité packetage, pour faciliter l'utilisation dans plateau.
	Piece p1;
	Piece p2;

	int l;
	int c;

	public Action (Piece p1, Piece p2)
	{
		this.l = p1.getPosLig();
		this.c = p1.getPosCol();

		this.p1 = p1;
		this.p2 = p2;
	}
}
