
import javax.swing.JPanel;

public class Rock extends Sprite{
	
	public Rock(int pPosX, int pPosY)
	{
		super(pPosX, pPosY);
		this.path = "rock.png";
		this.isSolid = true;
		this.isMoveable = true;
		this.setImage();
	}
	
	// METHODES
	
	public String toString()
	{
		return "type:Rock " + super.toString();
	}
	
	public void update()
	{
		super.update();
		applyGravity();
	}
}
