
import java.awt.Component;

public class End extends Sprite{
	
	// CONSTRUCTEUR
	public End(int pX, int pY)
	{
		super(pX, pY);
		this.path = "block.png";
		this.isSolid = true;
		this.setImage();
	}
	
	// METHODES
	public String toString()
	{
		return "type:End" + super.toString();
	}

	public void update()
	{
		super.update();
	}
	
	public void setIsSolid(Boolean pBool)
	{
		super.setIsSolid(pBool);
		
		if(pBool)
		{
			this.path = "block.png";
		}
		else
		{
			this.path = "end.png";
		}
		
		this.setImage();
	}
}
