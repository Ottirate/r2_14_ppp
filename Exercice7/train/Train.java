package train;

import train.vehicule.*;
import java.util.ArrayList;

public class Train 
{
	private static char[] nomIncremente = {'A','A','A'};

	private ArrayList<Vehicule> tabVehic;
	private String nom;

	public static Train creerTrain()
	{
		if (Train.enChaine().equals("ZZZ")) return null;

		return new Train();
	}

	private Train ()
	{
		this.nom = Train.enChaine();
		
		this.tabVehic = new ArrayList<Vehicule>();
		Train.incrementeNom();
	}

	private static String enChaine()
	{
		String s = "";
		for (int i = 0; i< Train.nomIncremente.length; i++)
			s += Train.nomIncremente[i];
		return s;
	}

	public static void incrementeNom()
	{
		Train.nomIncremente[Train.nomIncremente.length - 1] = 
		(char) (Train.nomIncremente[Train.nomIncremente.length - 1] + 1);

		for (int i = Train.nomIncremente.length - 1; i > 0; i-- )
			if (Train.nomIncremente[i] > 'Z')
			{
				Train.nomIncremente[i]     = 'A';
				Train.nomIncremente[i - 1] = (char) (Train.nomIncremente[i - 1] + 1);
			}
	}

	public double getChargeMaxi()
	{
		double c = 0.0;

		for (Vehicule v : tabVehic)
			if (v instanceof Wagon)
				c += ((Wagon) v).getChargeMaxi() ;

		return c;
	}

	public boolean ajouterVehicule (Vehicule vehic)
	{
		if (!(vehic instanceof Motrice) && this.tabVehic.size() == 0) return false;
		if ( (vehic instanceof Motrice) && this.tabVehic.size() != 0) return false;

		tabVehic.add(vehic);
		return true;
	} 

	public String toString ()
	{
		String sRep = "+------------+\n| " + this.nom + "        |\n+------------+\n";
		for (int i = 0; i < 5; i++)
		{
			for (Vehicule v : this.tabVehic)
				sRep += v.getSchema(i);
			
			sRep += '\n';
		}
		
		return sRep;
	}

	public void animer()
	{
		String[] lignes = new String[5];

		String sRep = "";

		for (int i = 0; i < 5; i++)
		{
			for (Vehicule v : this.tabVehic)
				sRep += v.getSchema(i);
			
			lignes[i] = sRep;
			sRep = "";
		}

		

		for (int cara = 0; cara < lignes[0].length(); cara += 3)
		{
			int lim = 0;

			for (int l = 0; l < 5; l++)
			{
				if (lignes[l].length() - cara > 164) lim = 164 + cara;
				else                                 lim = lignes[0].length();

				System.out.println(lignes[l].substring(cara, lim));
			}

			try{Thread.sleep((lignes[0].length() - cara)*2 + 25);}catch(Exception e){}
			// try{Thread.sleep((int)(lim - lignes[0].length() + 20));}catch(Exception e){}

			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}
