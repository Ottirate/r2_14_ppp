/* DS S1.02
 * Date : 9/12/2022
 *
 * @author : Hautot Sarah (A1)
 */


import java.util.Arrays;

public class Recherche
{
	public static int rechSequentielle ( String motRch, String[] tabMot  )
	{
		int indiceMot = -1;
		
		for (int cpt = 0; cpt < tabMot.length; cpt++)
			if (motRch.equals(tabMot[cpt])) { indiceMot = cpt; }
		
		return indiceMot;
	}

	public static int rechDichotomique ( String motRch, String[] tabMot  )
	{
		int indice = -1;
		int cpt;
		int deb,fin;
		
		deb = 0;
		fin = tabMot.length;
		cpt = (deb+fin) / 2;
		
		while (! (motRch.equals(tabMot[cpt]) || deb == fin))
		{
			
			if (motRch.compareTo(tabMot[cpt]) > 0)
				deb = cpt + 1;
			else
				fin = cpt - 1;
			
			cpt = (deb+fin) / 2;
		}
		
		if ( tabMot[cpt].equals(motRch)) { indice = cpt; }
		
		return indice;
		
	}


	public static int rechRec ( String motRch, String[] tabMot )
	{
		return rechRec ( motRch, tabMot, 0, tabMot.length - 1 );
	}




	private static int rechRec ( String motRch, String[] tabMot, int deb, int fin)
	{
		int milieu;

		if ( tabMot[deb].equals (motRch) ) return deb;
		if ( tabMot[fin].equals (motRch) ) return fin;

		if ( fin - deb > 1 )
		{
			milieu = (fin+deb)/2;

			
			if ( tabMot[milieu].compareTo ( motRch ) >  0 ) return Recherche.rechRec ( motRch, tabMot, deb,      milieu-1 );
			if ( tabMot[milieu].compareTo ( motRch ) <  0 ) return Recherche.rechRec ( motRch, tabMot, milieu+1, fin      );
			if ( tabMot[milieu].compareTo ( motRch ) == 0 ) return milieu;
		}

		return -1;
		

	}
	


	public static String[] genererMots(int nbMots)
	{
		String[] tabRet = new String[nbMots];

		for (int cpt=0; cpt< tabRet.length;cpt++)
		{
			tabRet[cpt] = "" + Recherche.lettre() + Recherche.lettre();
		}
		
		return tabRet;
	}

	private static char lettre ()
	{
		return (char) ( 'A' + (int) (Math.random() * 26) );
	}

}
