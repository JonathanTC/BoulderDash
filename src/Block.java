

public class Block extends Sprite {

	// CONSTRUCTEUR
	

	
	public Block(int pX, int pY)
	{
		super(pX, pY);
		this.path = "block.png";
		this.isSolid = true;
		this.setImage();
	}
	
	// METHODES
	
	public String toString()
	{
		return "type:Block " + super.toString();
	}
}
