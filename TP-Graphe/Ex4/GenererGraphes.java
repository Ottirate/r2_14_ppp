import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import org.graphstream.algorithm.Toolkit ;

public class GenererGraphes 
{
	public static SingleGraph genererGraphe(int NB_SOMMET, int NB_ARRET) 
	{

		if (NB_SOMMET >  NB_ARRET)                  NB_ARRET = NB_SOMMET;
		if (NB_SOMMET == 0       )                  return null;         
		if (NB_ARRET > (NB_SOMMET*(NB_SOMMET-1))/2) return null;

		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		SingleGraph graph = new SingleGraph("Graphe Generer");

		
		graph.addNode("S1");
		for (int i = 2;i <= NB_SOMMET; i++)
		{
			Node n = Toolkit.randomNode(graph);
			graph.addNode("S"+(i));
			graph.addEdge (n.getId() + ",S" + i, n.getId(), "S" + i, true);
		}

		for (int i = NB_SOMMET; i<=NB_ARRET; i++)
			ajouterLiaisonHasard(graph);

		return graph;
	}

	public static void ajouterLiaisonHasard(SingleGraph graph)
	{
		Node s1 = Toolkit.randomNode(graph);
		Node s2 = Toolkit.randomNode(graph);

		while (s1.hasEdgeBetween(s2) || s1.equals(s2))
		{
			s1 = Toolkit.randomNode(graph);
			s2 = Toolkit.randomNode(graph);
		}

		graph.addEdge(s1.getId()+","+s2.getId(), s1.getId(), s2.getId(), true);
	}


	private static boolean parcourireComposant (SingleGraph graph)
	{
		ArrayList<org.w3c.dom.Node> composant = new ArrayList<Node>();

		Node sommet = Toolkit.randomNode(graph);
		composant.add(sommet);

		while (!composant.isEmpty())
		{
			sommet = (Node) composant.remove(0);

			if (!sommet.hasAttribute("marque"))
			{
				sommet.setAttribute("marque",true);

				Iterator<Node> lesVoisins = sommet.getNeighborNodeIterator();

				while (lesVoisins.hasNext())
				{
					Node voisin = lesVoisins.next();
					if (!voisin.hasAttribute("marque") && !voisin.equals(n))
						composant.add(voisin);
				}

			}
		}
	}

	public static void main(String[] args) 
	{
		SingleGraph graph = null;
		try 
		{
			graph = GenererGraphes.genererGraphe(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		}
		catch (Exception e)
		{
			GenererGraphes.genererGraphe(10,10);
		}
		graph.display();
	}
}
