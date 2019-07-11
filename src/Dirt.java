

public class Dirt extends Sprite {
	
	// CONSTRUCTEUR
	
	
	public Dirt(int pX, int pY)
	{
		super(pX, pY);
		this.path = "dirt.png";
		this.setImage();
	}
	
	// METHODES
	
	public String toString()
	{
		return "type:Dirt " + super.toString();
	}
}
