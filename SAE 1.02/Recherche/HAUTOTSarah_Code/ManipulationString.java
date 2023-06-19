/* DS S1.02
 * Date : 9/12/2022
 *
 * @author : Hautot Sarah (A1)
 */


public class ManipulationString
{
	public static void main(String[] a)
	{
		String[] tabMot = new String[] { "Abricot", "Ananas",   "Citron", "Fraise", "Framboise",  
		                                 "Mangue",  "Myrtille", "Poire",   "Pomme", "Raisin"     };

		/* La méthode equals renvoit 
		 *    vrai si le contenu de l'objet courant est le même que le contenu de l'objet passé en paramètre
		 *    faux si le contenu de l'objet courant est différent du   contenu de l'objet passé en paramètre
		 */

		System.out.println ( "Test 1 : " + tabMot[0].equals("Mangue") );
		System.out.println ( "Test 2 : " + tabMot[5].equals("Mangue") );
		System.out.println ( "Test 3 : " + tabMot[9].equals("Mangue") );		

		/* La méthode compareTo permet de déterminer si le contenu d'une chaine est plus petit ou plus grand 
		 * que le contenu d'une autre chaine. La comparaison se fait selon l'ordre lexicographique (ordre du dictionnaire)
		 * la méthode compareTo renvoit une valeur 
		 *    < 0 si le contenu de l'objet courant est plus petit que le contenu de l'objet passé en paramètre
		 *    > 0 si le contenu de l'objet courant est plus grand que le contenu de l'objet passé en paramètre  
		 *    = 0 si le contenu de l'objet courant est le même    que le contenu de l'objet passé en paramètre  
		 */

		System.out.println ( "Test 4 : " + tabMot[0].compareTo("Mangue") );
		System.out.println ( "Test 5 : " + tabMot[5].compareTo("Mangue") );
		System.out.println ( "Test 6 : " + tabMot[9].compareTo("Mangue") );

	}


}
