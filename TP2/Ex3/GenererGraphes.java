import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import java.util.Iterator;
import org.graphstream.algorithm.Toolkit ;

import java.util.ArrayList;

public class GenererGraphes 
{
	public static SingleGraph genererGraphe(int NB_SOMMET, int NB_ARRET) 
	{
		if (NB_SOMMET == 0       )                  return null;
		if (NB_ARRET > (NB_SOMMET*(NB_SOMMET-1))/2) return null;

		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		SingleGraph graph = new SingleGraph("Graphe Generer");

		//Création des nodes
		for (int i = 1;i <= NB_SOMMET; i++)
			graph.addNode("S"+(i));

		for (int i = 1; i<=NB_ARRET; i++)
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

		graph.addEdge(s1.getId()+","+s2.getId(), s1.getId(), s2.getId());
	}
	
	public static int compterComposants(SingleGraph graph)
	{
		int nbComposants = 0;
		
		for (Node n : graph.getNodeSet())
		{
			if (!n.hasAttribute("marque"))
			{
				parcourireComposant(graph,n);
				n.setAttribute("ui.style","fill-color:red;");
				nbComposants++;
			}
		}

		return nbComposants ;
	}

	private static void parcourireComposant (SingleGraph graph, Node sommet)
	{
		ArrayList<Node> composant = new ArrayList<Node>();
		Node n = sommet;

		composant.add(sommet);

		while (!composant.isEmpty())
		{
			n = (Node) composant.remove(0);

			if (!n.hasAttribute("marque"))
			{
				n.setAttribute("marque",true);

				Iterator<Node> lesVoisins = n.getNeighborNodeIterator();

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

		System.out.println(compterComposants(graph));
	}
}
