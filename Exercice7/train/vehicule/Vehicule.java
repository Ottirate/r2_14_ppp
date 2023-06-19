package train.vehicule;

public abstract class Vehicule 
{
	protected int    nbRoues;
	protected double poidsVide;

	protected Vehicule (int nbRoues, double poidsVide)
	{
		this.nbRoues   = nbRoues;
		this.poidsVide = poidsVide;
	}

	public abstract double getPoidEnCharge();

	public double getPoidParRoues()
	{
		return this.getPoidEnCharge() / this.nbRoues;
	}

	public String toString ()
	{
		String sRep = this.getClass().getName() + "\n";

		sRep += "\t Nombre de roues : " + this.nbRoues   + "\n";
		sRep += "\t Poids vide      : " + this.poidsVide + "\n";

		return sRep;
	}

	public abstract String getSymbole();
	public abstract String getSchema(int ind);
}
