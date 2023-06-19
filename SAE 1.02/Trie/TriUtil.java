import iut.algo.Clavier;
public class TriUtil
{

	public static void main(String[] args)
	{
		/*---Donnees---*/
		//Variables
		int        taille, bornMin, bornMax;
		int[]      tabOG;
		int[]      tabUse;
		long       start;
		
		System.out.print("Entrez la taille des tableaux    : ");
		taille  = Clavier.lire_int();
		
		System.out.print("Entrez la valeur max du tableaux : ");
		bornMax = Clavier.lire_int();
		
		System.out.print("Entrez la valeur min du tableaux : ");
		bornMin = Clavier.lire_int();
		
		
		
		
		for (int cpt = 0; cpt < 10; cpt++) //On ferra le test 10 fois pour avoir plusieur resultats
		{
			System.out.println("Tableau N" + (cpt+1));
			tabOG      = genererTableau(taille,bornMin,bornMax);
			
			//Etape 1.1
			System.out.println("+----------| Tri à bulle |----------+");
			
			tabUse = copierTableau(tabOG);
			System.out.print("Tableau toatelement melange : ");
			start = System.nanoTime();
			triBulle(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			
			//Etape 1.2
			tabUse = inverserTableau(tabOG);
			System.out.print("Tableau inverse             : ");
			start = System.nanoTime();
			triBulle(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			//Etape 1.3
			tabUse = melangerTableau(tabUse,taille/10);
			System.out.print("Tableau legèrement melange  : ");
			start = System.nanoTime();
			triBulle(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			
			
			
			//Etape 2.1
			System.out.println("+----------| Tri selection |----------+");
			
			tabUse = copierTableau(tabOG);
			System.out.print("Tableau toatelement melange : ");
			start = System.nanoTime();
			triSelection(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			
			//Etape 2.2
			tabUse = inverserTableau(tabOG);
			System.out.print("Tableau inverse             : ");
			start = System.nanoTime();
			triSelection(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			//Etape 2.3
			tabUse = melangerTableau(tabUse,taille/10);
			System.out.print("Tableau legèrement melange  : ");
			start = System.nanoTime();
			triSelection(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			
			
			
			//Etape 2.1
			System.out.println("+----------| Tri Insertion |----------+");
			
			tabUse = copierTableau(tabOG);
			System.out.print("Tableau toatelement melange : ");
			start = System.nanoTime();
			triInsertion(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			
			//Etape 2.2
			tabUse = inverserTableau(tabOG);
			System.out.print("Tableau inverse             : ");
			start = System.nanoTime();
			triInsertion(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			//Etape 2.3
			tabUse = melangerTableau(tabUse,taille/10);
			System.out.print("Tableau legèrement melange  : ");
			start = System.nanoTime();
			triInsertion(tabUse);
			
			if ( !estTrie(tabUse))
			{
				System.out.println("Un problème est survenue, nous sommes desole");
				return;
			}
			
			System.out.println(System.nanoTime() - start);
			System.out.println(toString(tabUse));
			
			
		}

	}

	public static void triSelection ( int[] tab )                                  /* Cette methode tri le tableau tab selon l’algorithme du tri par selection */
	{
		int   max;
		int   tempo;

		for(int tour = 0; tour < tab.length;tour++)
		{
			// On cherche l'indice de la plus grande valeur
			max = 0;
			for (int cpt = 0; cpt < tab.length - tour; cpt++)
				if (tab[cpt] > tab[max]) { max = cpt;}
			

			/* Une fois l'indice trouve, ou la deplace en la mettant à la 
			"dernière" case. */
			
			permuter(tab, max, tab.length - tour - 1);
		}

	}

	public static void triBulle ( int[] tab )                                      /* Cette methode tri le tableau tab selon l’algorithme du tri à bulle */                                
	{ 
		int valCaseA, valCaseB;
		int swap;

		swap=1;
		while (swap >=1)
			{
				swap=0;
				for(int cpt = 0; cpt+1 < tab.length; cpt++) 
				{
					if (tab[cpt] > tab[cpt+1])
					{
						swap ++;
						permuter(tab, cpt, cpt+1);
					}
			}
		}
	}


	public static void triInsertion ( int[] tab )                                  /* Cette methode tri le tableau tab selon l’algorithme du tri par insertion */
	{
		int tempo=0;
		
		for (int cpt1 = 1; cpt1< tab.length; cpt1++)
		{
			
			tempo = tab[cpt1];
			
			int cpt2=cpt1;
			
			while (cpt2 >0 && tab[cpt2-1] > tempo)
			{
				cpt2 --;
			}
			
			if (cpt2 != cpt1 )
			{
				for (int cpt3 = cpt1; cpt2 < cpt3; cpt3--)
					tab [cpt3] = tab[cpt3-1];
				tab[cpt2] = tempo;
			}
			
		}

	}

	public static boolean estTrie ( int[] tab )                                    /* Cette methode determine si le tableau tab est trie dans l’ordre croissant */
	{
		boolean estTrie = true;

		int   valPrec = tab[0];
		for (int cpt = 1; cpt < tab.length; cpt++)
		{
			if ( valPrec > tab[cpt] )
				estTrie = false;
				valPrec = tab[cpt];
		}

		return estTrie;

	}
	public static int[] genererTableau ( int nbCases, int valMin, int valMax )     /* Cette methode retourne un tableau de taille nbCases integralement remplie avec des valeurs aleatoires prises sur l’intervalle [valMin ;valMax] */
	{
		int[] tab;
		tab = new int [nbCases];

		for(int cpt = 0; cpt<nbCases; cpt++)
		tab[cpt] = (int) (Math.random() * (valMax-valMin-1)) + valMin;

		return tab;


	}
	public static int[] copierTableau ( int[] tab )                                /* Cette methode retourne un nouveau tableau à l’identique du tableau passe en paramètre. */
	{
		int[] tabCopie;
		tabCopie = new int [tab.length];

		for(int cpt = 0; cpt <tab.length; cpt++)
		{
			tabCopie[cpt] = tab[cpt];
		}
		return tab;
	}

	public static String toString ( int[] tab )                                    /* Cette methode affiche les 4 premiers et 4 derniers elements du tableau. */
	{
		String s="|";
		String l="+";
		String i="";

		for(int cpt = 0; cpt<4; cpt++)
		{
			s += String.format("%6s",tab[cpt]) + "|";
			l += "------+";
			i += String.format("%7s",cpt);
		}
		
		l+="------+";
		s+="......|";
		i+="       ";
		
		for(int cpt = tab.length-4; cpt<tab.length; cpt++)
		{
			s += String.format("%6s",tab[cpt]) + "|";
			l += "------+";
			i += String.format("%7s",cpt);
		}
		
		l += '\n';
		s += '\n';
		s = l + s + l + i;

		return s;

	}
	
	private static void permuter ( int[] tab, int ind1, int ind2 )
	{
		int tempo = tab[ind1];
		tab[ind1] = tab[ind2];
		tab[ind2] = tempo;
	}
	
	public static int[] inverserTableau (int[] tab)
	{
		int[]   tab2;
		
		tab2 = new int[tab.length];
		for (int cpt = 0; cpt < tab.length; cpt++)
		{
			tab2[cpt]= tab[tab.length - cpt - 1];
		}
		
		return tab2;
	}
	
	public static int[] melangerTableau (int[] tab, int nbMelange)
	{
		int   rand1, rand2;
		int[] tab2;
		
		tab2 = copierTableau(tab);
		
		rand1 = 0;
		rand2 = 0;
		
		for (int cpt = 0; cpt < nbMelange; cpt++)
		{
			rand1 = (int) Math.random()*(tab.length) ;
			rand2 = (int) Math.random()*(tab.length) ;
			permuter(tab, rand1,rand2);
		}
		
		return tab2;
		
	}


}

