package train.vehicule;

public class Motrice extends Vehicule
{
	private static final String[] tabMotrice = {
	"  OO O o o o.",
	"  O      ____",
	"  ][_n_i_| ( ",
	" (__________|",
	" /0--0--0--0 ",};

	private int puissance;

	public Motrice (int nbRoues, double poidsVide, int puissance)
	{
		super(nbRoues, poidsVide);
		this.puissance = puissance;
	}

	public double getPoidEnCharge() 
	{
		return this.poidsVide;
	}

	public String toString ()
	{
		return super.toString() + 
		       "\t Puissance       : " + this.puissance + "\n" +
			   "\t Poids           : " + this.getPoidEnCharge();
	}

	public String getSymbole() { return "M";}

	public String getSchema (int ind) { return Motrice.tabMotrice[ind];}
	
}
