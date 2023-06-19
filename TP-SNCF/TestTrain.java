import train.Train;
import train.vehicule.*;

public class TestTrain 
{
	public static void main(String[] args) 
	{
		Train t = Train.creerTrain();
		Vehicule v = new Motrice(4, 0, 0);

		t.ajouterVehicule(v);

		for (int i = 0; i < (Math.random()* 10 + 5); i ++)
		{
			int random = (int) (Math.random()*2);

			switch (random)
			{
				case 0 -> v = new Voiture(2, 2, 2); 
				case 1 -> v = new Wagon  (2, 2, 2); 
			}
			
			t.ajouterVehicule(v);
		}

		System.out.println(t);
	}	
}
