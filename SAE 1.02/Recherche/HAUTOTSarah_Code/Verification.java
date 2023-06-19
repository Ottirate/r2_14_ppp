/* DS S1.02
 * Date : 9/12/2022
 *
 * @author : Hautot Sarah (A1)
 */


public class Verification
{
	public static void main(String[] a)
	{
		/*---Donnéees---*/
		int indice;
		String[] tabMot = new String[] { "Abricot", "Ananas",   "Citron", "Fraise", "Framboise",  
		                                 "Mangue",  "Myrtille", "Poire",   "Pomme", "Raisin"     };
		
		indice = Recherche.rechSequentielle ("Abricot",tabMot);
		System.out.println(tabMot[indice]);
		
		indice = Recherche.rechDichotomique ("Abricot",tabMot);
		System.out.println(tabMot[indice]);
		
		tabMot = Recherche.genererMots(5);
		Test.trieSelection ( tabMot );
		for (int cpt = 0; cpt < tabMot.length; cpt++)
			System.out.println(tabMot[cpt]);
	}
}
