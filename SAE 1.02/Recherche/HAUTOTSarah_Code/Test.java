/* DS S1.02
 * Date : 9/12/2022
 *
 * @author : Hautot Sarah (A1)
 */


public class Test
{
	public static void main(String[] a)
	{
		/*---Données---*/
		String[] tabMot;
		int      indice;
		
		long     start;
		String   motRch;
		
		
		/*---Instructions---*/
		
		// Etape 1
		tabMot = Recherche.genererMots(500);
		
		// Etape 2
		Test.trieSelection ( tabMot );
		
		//Etape 3
		
		//On prend un mots dans le tableau au hasard
		motRch = tabMot[ (int) (Math.random() * tabMot.length) ];
		System.out.println("Nous allons rechercher le mot " + motRch + 
		                   " via différente méthode");
		
		//On le cherche avec la première méthode
		System.out.print("Recherche séquentielle : ");
		start = System.nanoTime();
		
		indice = Recherche.rechSequentielle (motRch,tabMot);
		
		//On affiche le temps
		System.out.println(System.nanoTime() - start + " nano secondes");
		
		//Verification de l'indice trouvé
		if ( !motRch.equals(tabMot[indice]) )
		{
			System.out.println("Oh oh ! Il semblerait que le mots trouvé soit faux...");
			return;
		}
		
		
		//On le cherche avec la deuxième méthode
		
		System.out.print("Recherche dichotomique : ");
		start = System.nanoTime();
		
		indice = Recherche.rechDichotomique (motRch,tabMot);
		
		//On affiche le temps
		System.out.println(System.nanoTime() - start + " nano secondes");
		
		if ( !motRch.equals(tabMot[indice]) )
		{
			System.out.println("Oh oh ! Il semblerait que le mots trouvé soit faux...");
			return;
		}
		
	}
	
	public static void trieSelection (String[] tabMot)
	{
		/*--Données--*/
		int    max;
		String tempo;
		
		
		for(int tour = 0; tour < tabMot.length;tour++)
		{
			max = 0;
			for (int cpt = 1; cpt < tabMot.length - tour; cpt++)
				if (tabMot[cpt].compareTo(tabMot[max])>0) { max = cpt;}
			
			tempo                        = tabMot[max];
			tabMot[max]                  = tabMot[tabMot.length - tour - 1];
			tabMot[tabMot.length-tour-1] = tempo;
		}
	
	}
}
