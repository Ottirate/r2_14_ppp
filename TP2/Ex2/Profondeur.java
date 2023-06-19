
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import java.util.Stack ;
import java.util.Iterator;
import org.graphstream.algorithm.Toolkit ;

public class Profondeur 
{
	public static void main(String[] args) 
	{
		SingleGraph graph;

		if (args.length == 2) graph   = GenererGraphes.genererGraphe(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		else                  graph   = GenererGraphes.genererGraphe(20,40);

		Stack<Node>   pile  = new Stack<Node>();

		int nbNode = 1;
		int sommetNum = 1;

		Node sommet = Toolkit.randomNode(graph);

		sommet.setAttribute("ui.style","fill-color:yellow;");

		pile.push(sommet);

		graph.display();

		while (!pile.empty())
		{
			sommet = (Node) pile.pop();

			sommet.setAttribute("label" + nbNode);

			if (!sommet.hasAttribute("marque"))
			{
				sommet.setAttribute("marque",true);
				sommet.setAttribute("ui.style","fill-color:red;");

				Iterator<Node> lesVoisins = sommet.getNeighborNodeIterator();

				while (lesVoisins.hasNext())
				{
					Node voisin = lesVoisins.next();
					if (!voisin.hasAttribute("marque") && !voisin.equals(sommet))
					{
						try{Thread.sleep(500);}catch(Exception e){}
						voisin.setAttribute("label" + nbNode++);

						pile.push(voisin);
					}
				}

			}
		}

	}	
}
