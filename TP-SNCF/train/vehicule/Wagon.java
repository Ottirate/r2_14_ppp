package train.vehicule;

public class Wagon extends Vehicule
{
	private static final String[] tabWagon = {
	"              ",
	" ____________ ",
	" |   |\\/|   | ",
	"_|___|/\\|___|_",
	"  0--0--0--0  ",};

	private int charge;
	private int capCharge;

	public Wagon (int nbRoues, double poidsVide, int capCharge)
	{
		super(nbRoues, poidsVide);

		this.capCharge = capCharge;
		this.charge    = 0;
	}

	public double getPoidEnCharge() 
	{
		return this.poidsVide + charge;
	}

	public int getChargeMaxi() 
	{
		return this.capCharge;
	}

	public String toString ()
	{
		return super.toString() + 
		       "\t Charges         : " + this.charge + " / " + this.capCharge + "t\n"+
		       "\t Poids           : " + this.getPoidEnCharge();
	}

	public boolean ajouterCharge(int nvCharge)
	{
		if (this.charge + nvCharge > this.capCharge) return false;
		if (nvCharge < 0) return false;

		this.charge += nvCharge;
		return true;
	}
	
	public boolean retirerCharge(int nvCharge)
	{
		if (this.charge - nvCharge < 0) return false;
		if (nvCharge < 0) return false;

		this.charge -= nvCharge;
		return true;
	}
	
	public void vider()
	{
		this.charge  = 0;
	}

	public boolean estVide()
	{
		return this.charge  == 0;
	}

	public String getSymbole() { return "W";}
	public String getSchema(int ind) { return Wagon.tabWagon[ind];}
	
}
