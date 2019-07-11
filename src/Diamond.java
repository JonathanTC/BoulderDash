
import javax.swing.JPanel;

public class Diamond extends Sprite{
	
	// CONSTRUCTEUR
	
	public Diamond(int pX, int pY)
	{
		super(pX, pY);
		this.path = "diamond.png";
		this.setImage();
	}
	
	// METHODES
	public String toString()
	{
		return "type:Diamond " + super.toString();
	}

	public void update()
	{
		super.update();
		applyGravity();
	}
}
