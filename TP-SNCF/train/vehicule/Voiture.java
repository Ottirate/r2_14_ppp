package train.vehicule;


public class Voiture extends Vehicule
{
	private static double POIDS_PASSAGER = 0.08; //en tonnes
	private static final String[] tabVoiture = {
	"              ",
	" ____________ ",
	" | [] [] [] | ",
	"_|__________|_",
	"  0--0--0--0  ",};

	private int nbPassager;
	private int nbPlace;


	public Voiture (int nbRoues, double poidsVide, int nbPlace)
	{
		super(nbRoues, poidsVide);

		this.nbPlace    = nbPlace;
		this.nbPassager = 0;
	}


	public double getPoidEnCharge() 
	{
		return this.poidsVide + nbPassager * Voiture.POIDS_PASSAGER;
	}

	public String toString ()
	{
		return super.toString() + 
		       "\t Passager        : " + this.nbPassager + " / " + this.nbPlace + "\n" +
			   "\t Poids           : " + this.getPoidEnCharge();
	}



	public boolean ajouterPassager(int pass)
	{
		if (this.nbPassager + pass > this.nbPlace) return false;
		if (pass < 0) return false;

		this.nbPassager += pass;
		return true;
	}
	
	public boolean retirerPassager(int pass)
	{
		if (this.nbPassager - pass < 0) return false;
		if (pass < 0) return false;

		this.nbPassager -= pass;
		return true;
	}
	
	public void vider()
	{
		this.nbPassager  = 0;
	}

	public boolean estVide()
	{
		return this.nbPassager  == 0;
	}

	public String getSymbole() { return "V";}

	public String getSchema (int ind) { return Voiture.tabVoiture[ind];}

}
