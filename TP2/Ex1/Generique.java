
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import org.graphstream.algorithm.Toolkit ;

public class Generique 
{
	public static void main(String[] args) 
	{
		SingleGraph graph;

		if (args.length == 2) graph   = GenererGraphes.genererGraphe(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		else                  graph   = GenererGraphes.genererGraphe(20,40);

		ArrayList   list  = new ArrayList<Node>();

		int nbNode = 1;
		int sommetNum = 1;

		Node sommet = Toolkit.randomNode(graph);

		sommet.setAttribute("ui.style","fill-color:yellow;");

		list.add(sommet);

		graph.display();

		while (!list.isEmpty())
		{
			sommet = (Node) list.remove(0);

			sommet.setAttribute("label" + nbNode);

			if (!sommet.hasAttribute("marque"))
			{
				sommet.setAttribute("marque",true);

				Iterator<Node> lesVoisins = sommet.getNeighborNodeIterator();

				while (lesVoisins.hasNext())
				{
					Node voisin = lesVoisins.next();
					if (!voisin.hasAttribute("marque") && !voisin.equals(sommet))
					{
						try{Thread.sleep(500);}catch(Exception e){}
						voisin.setAttribute("label" + nbNode++);
						voisin.setAttribute("ui.style","fill-color:red;");
						list.add(voisin);
					}
				}

			}
		}

	}	
}
