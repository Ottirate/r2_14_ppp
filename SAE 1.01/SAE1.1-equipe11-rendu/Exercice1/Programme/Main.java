import java.util.Scanner;
import java.io.FileInputStream;

import iut.algo.Decomposeur;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args) 
    {
        /*CONSTANTE*/
        //Paramètre d'entrée
        final boolean TRI_CAT       = true;

        //Fichiers traités
        final String      FICH_INFO   = "../data/ressources.data"; //Fichier info (salle + nbMembre)
        final String      FICH_ELEVES = "../data/promotion.data";  //Fichier des eleves

        /* Données */

        //Salle
        String[]    salle;
        int[]       capaciteSalle;
        int         nbSalle;

        //Lecture doc
        Scanner     scanner ;
        Decomposeur dec;

        //Liste des étudiants
        Etudiant[]  liste;
        int         nbPers,numPers;

        //Equipe
        Equipe[]    tabEquipe;
		int 		numPersEqu, numEqu;
        Etudiant[]  equipeEtudiant;
        int         nbEleveReste;
        int         tailleEquipe = 4;

        //Autre
        int         cpt;
        
        /*              */
        /* Instructions */
        /*              */

        try
        {
            //Lecture du nombre de salle
            scanner = new Scanner (new FileInputStream( FICH_INFO )); 
            nbSalle = compteLigneFich(scanner);
            scanner.close();


            //Lecture du nombre de personne par équipe
            scanner       = new Scanner (new FileInputStream( FICH_INFO ));
            dec = new Decomposeur ( scanner.nextLine() );
            tailleEquipe   = dec.getInt(0);
            

            //Lecture des salles et ses capacités
            salle         = new String[nbSalle];
            capaciteSalle = new int   [nbSalle];

            for (cpt = 0; scanner.hasNextLine(); cpt++ )
            {
                dec    = new Decomposeur ( scanner.nextLine() );
                salle[cpt]         = dec.getString(0);
                capaciteSalle[cpt] = dec.getInt   (1);
            }
            scanner.close();
            
            //Compter le nombre d'élèves total
            scanner = new Scanner (new FileInputStream( FICH_ELEVES )); 
            nbPers = compteLigneFich(scanner);

            liste = new Etudiant [nbPers];


            //On remplie la liste
            scanner = new Scanner (new FileInputStream( FICH_ELEVES )); 
            scanner.nextLine();
            for (cpt = 0; cpt < nbPers; cpt++)
            {
                dec = new Decomposeur ( scanner.nextLine() );
                liste[cpt] = new Etudiant ( dec.getString(0),dec.getString(1),dec.getChar(2),dec.getChar(3) );
            }

            if (TRI_CAT)
                Arrays.sort(liste);
            

            //On remplie les équipes par catégorie
            int nbEqu    = 0;
            int deb      = 0;
            int fin      = nbPers - 1;

            if (TRI_CAT)
            {
                while ( deb < liste.length)
                {
                    fin    = numFinCat(deb, liste, liste[deb].getCat());
                    nbEqu += (fin-deb+1) / tailleEquipe;
                    deb    = fin+1;
                }
                // if (tailleEquipe %2 == 1 )
                    tabEquipe = new Equipe[nbEqu  ];
                // else
                //     tabEquipe = new Equipe[nbEqu+1];

                deb       = 0;
                while ( deb < liste.length)
                {
                    fin    = numFinCat(deb, liste, liste[deb].getCat());
                    tabEquipe = remplirEquipes(liste, tabEquipe, deb, fin, tailleEquipe, salle,capaciteSalle);
                    deb    = fin+1;
                }
            }
            else
            {
                tabEquipe = new Equipe[deb/fin];
                tabEquipe = remplirEquipes(liste, tabEquipe, deb, fin, tailleEquipe, salle,capaciteSalle);
            }

            
            //Affiche les équipes
            for (int i = 0; i < tabEquipe.length; i++)
            {
                System.out.println("-----------------------------");
                System.out.print(tabEquipe[i]);
            }
            
            
        }
        catch (Exception e){ e.printStackTrace(); }


    }

    /*                                               */
    /* Trouve l'indice de fin d'une catégorie (trié) */
    /*                                               */
    public static int numFinCat(int deb, Etudiant[] tab, char bonneCat)
    {
        int cpt;

        for (cpt = deb; cpt < tab.length-1 && tab[cpt].getCat() == bonneCat; cpt++ ){}
        
        if (cpt == tab.length-1)
            return cpt; //Si on est a la limite, on renvoie cpt
        return cpt-1; //Sinon on renvoie cpt - 1, soit la position d'avant
    }


    public static int compteLigneFich(Scanner scanner)
    {
        int cpt;
        for(cpt = 0; scanner.hasNextLine(); cpt++) 
        {
            scanner.nextLine();
        }
        scanner.close();
        return cpt-1;
    }


    /*                                               */
    /* Remplies les équipes sur un intervalle donnée */
    /*                                               */
    public static Equipe[] remplirEquipes ( Etudiant[] liste, Equipe[] tabEqu,int deb, int fin, int tailleEqu, String[] salle, int[] cap) 
    {
        int        numEqu;
        int        numPers    = deb; 
        int        nbPers     = fin-deb+1; 
        int        nbPersRest = nbPers % tailleEqu;
        int        indSalle   = (int) (Math.random()*salle.length);
        int        nbGroupe   = nbPers/tailleEqu;

        int indiceTab=0;
        for (int cpt = 0; cpt < tabEqu.length && deb !=0; cpt++)
            if ( tabEqu[cpt] != null )
                indiceTab = cpt+1;

        Etudiant[] etudEqu= new Etudiant[tailleEqu];

        for (numEqu = 0;numEqu < nbGroupe - nbPersRest; numEqu++)
        {
            for(int numPersEqu=0; numPersEqu < tailleEqu; numPersEqu++ )
            {
                etudEqu[numPersEqu] = liste[numPers];
                numPers ++;
            }
  
            while (cap[indSalle] <= 0)
                indSalle = (int) (Math.random()*salle.length);
            
            tabEqu[numEqu + indiceTab ] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

            cap[indSalle]--;
        }

        //Creer le reste des équipes qui comportent les élèves supplémentaires
        
        if (nbPersRest < nbGroupe)
        {
            while (numEqu<nbGroupe)
            {
                etudEqu = new Etudiant[tailleEqu+1];

                for(int numPersEqu=0; numPersEqu < etudEqu.length; numPersEqu++ )
                {
                    etudEqu[numPersEqu] = liste[numPers];
                    numPers ++;
                }
    
                while (cap[indSalle] <= 0)
                    indSalle = (int) (Math.random()*salle.length);
                
                tabEqu[numEqu + indiceTab] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

                cap[indSalle]--;
                numEqu++;
            }

        }
        else
        {
            int moyenneEnPlus = nbPersRest/nbGroupe;
            int groupeUnPlus  = nbPersRest-moyenneEnPlus*nbGroupe;

            while (numEqu<nbGroupe-groupeUnPlus)
            {
                etudEqu = new Etudiant[tailleEqu+moyenneEnPlus];

                for(int numPersEqu=0; numPersEqu < etudEqu.length; numPersEqu++ )
                {
                    etudEqu[numPersEqu] = liste[numPers];
                    numPers ++;
                }
    
                while (cap[indSalle] <= 0)
                    indSalle = (int) (Math.random()*salle.length);
                
                tabEqu[numEqu + indiceTab] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

                cap[indSalle]--;
                numEqu++;
            }

            while (numEqu<nbGroupe)
            {
                etudEqu = new Etudiant[tailleEqu+moyenneEnPlus+1];

                for(int numPersEqu=0; numPersEqu < etudEqu.length; numPersEqu++ )
                {
                    etudEqu[numPersEqu] = liste[numPers];
                    numPers ++;
                }
    
                while (cap[indSalle] <= 0)
                    indSalle = (int) (Math.random()*salle.length);
                
                tabEqu[numEqu + indiceTab] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

                cap[indSalle]--;
                numEqu++;
            }

        }

        return tabEqu;
    }
}