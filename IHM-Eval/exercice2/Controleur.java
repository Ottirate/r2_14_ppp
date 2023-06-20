public class Controleur
{
	private FrameForme     ihm;

	public Controleur()
	{
		this.ihm  = new FrameForme(this);
	}


	public static void main (String[] a)
	{
		new Controleur();

	}
}