//Import des classes pour l'écriture des fichiers
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class ListePlanning
{
    public static void main (Jury[] tabJury)
    {

        String fich ="../Web/Page3/Planning.html";
        /*  Données  */
        try
        {
            PrintWriter pw = new PrintWriter( new FileOutputStream(fich) );
            int         cpt=0;
            String      date;

            /*  Instructions  */
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang=\"fr\">");
            pw.println("\t<head>");
            pw.println("\t\t<meta charset = \"UTF-8\">");
            pw.println("\t\t<title>Générateur de planning</title>");
            pw.println("\t\t<meta name=\"Author\" lang=\"fr\" content=\"Groupe11\">");
            pw.println("\t\t<meta name=\"description\" content=\"Page principale pour accéder aux différents planning générés en java\">");
            pw.println("\t\t<link rel=\"stylesheet\" href=\"../style/stylePlan.css\" media=\"all\" type=\"text/css\">");
            pw.println("\t</head>\n");

            pw.println("\t<body>");
            pw.println("\t\t<header>");
            pw.println("\t\t\t<h1>Generateur de planning</h1>");
            pw.println("\t\t</header>\n");
            
            pw.println("\t\t<section>");
            pw.println("\t\t\t<h2>" + tabJury[cpt].getDate() + "</h2>\n");
            pw.println("\t\t<article>");
            date = tabJury[cpt].getDate();

            
            //On cherche le max pour pouvoir déterminer le nombre de ligne vide à déclarer
            int max = tabJury[0].getNbEquipe();
            for (int cpt2 = 1; cpt2<tabJury.length; cpt2++)
                if (tabJury[cpt2].getNbEquipe() > max)
                    max = tabJury[cpt2].getNbEquipe();


            for (cpt =0; cpt < tabJury.length; cpt++)
            {
                if (!date.equals(tabJury[cpt].getDate()))
                {
                    pw.println("\t\t</article>");
                    pw.println("\t\t<h2>" + tabJury[cpt].getDate() + "</h2>");
                    date = tabJury[cpt].getDate();
                    pw.println("\t\t<article>");
                }
                pw.println("\t\t\t<table>");
                pw.println("\t\t\t\t<thead>");
                pw.println("\t\t\t\t\t<tr>");
                pw.println("\t\t\t\t\t\t<th colspan = \"4\">" + tabJury[cpt].getID() + "</th>");
                pw.println("\t\t\t\t\t</tr>");
                pw.println("\t\t\t\t</thead>");


                String[] listeProf = tabJury[cpt].getListeProf();
                pw.println("\t\t\t\t<tbody>");
                pw.println("\t\t\t\t\t<tr class=\"prof\">");
                pw.println("\t\t\t\t\t\t<td colspan = \"2\">");

                for (int numProf=0; numProf< listeProf.length; numProf++)
                {
                    pw.println("\t\t\t\t\t\t" + listeProf[numProf] + "<br>");
                }
                pw.println("\t\t\t\t\t\t</td>");
                pw.println("\t\t\t\t\t</tr>");

                int numPass;
                for (numPass = 0;numPass < tabJury[cpt].getNbEquipe(); numPass++)
                {
                    pw.println("\t\t\t\t\t<tr>");
                    pw.println("\t\t\t\t\t\t<td class=\"horaire\">" + tabJury[cpt].getHeureDebPassage(numPass) + "</td>");
                    pw.println("\t\t\t\t\t\t<td class=\"salleEtGroupe\" rowspan = \"2\">" + tabJury[cpt].getNumGroupe(numPass) + "<br>" + tabJury[cpt].getSalle() + "</td>");
                    pw.println("\t\t\t\t\t<td>&nbsp;<td>\n\n\t\t\t\t\t</tr>");
                    pw.println("\n\t\t\t\t\t<tr>");
                    pw.println("\t\t\t\t\t\t<td class=\"horaire\">" + tabJury[cpt].getHeureFinPassage(numPass) + " </td>");
                    pw.println("\t\t\t\t\t</tr>\n");
                    
                }

                for (int espaceVide = 0; espaceVide <= (max - tabJury[cpt].getNbEquipe()); espaceVide++)
                    pw.println("\t\t\t\t\t<tr>\n\t\t\t\t\t\t<td>&nbsp;</td>\n\t\t\t\t\t</tr>");
                
                pw.println("\t\t\t\t</tbody>");

                pw.println("\t\t\t</table>\n");
                
            }
            pw.println("\t\t</article>\n");
            pw.println("\t\t</section>\n");



            pw.println("\t</body>");

            pw.close();

        }catch (Exception e){ e.printStackTrace(); }
    }
}