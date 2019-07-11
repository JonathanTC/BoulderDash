
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Enemy extends Sprite {

	private Timer timer;
	private int previous = 3;
	// CONSTRUCTEUR
	public Enemy(int pX, int pY)
	{
		super(pX, pY);
		this.path = "enemy.png";
		this.setImage();

		timer = new Timer(250, new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				move();
			}
		});
		
		timer.start();
	}
	
	// METHODES
	public void move()
	{
		Point tablePoint[] = {	new Point(getX(), getY() - Main.TILESIZE),
								new Point(getX() + Main.TILESIZE, getY()), 
								new Point(getX(), getY() + Main.TILESIZE), 
								new Point(getX() - Main.TILESIZE, getY()) };
		
		int[][] tableOrder = {
				{
					1, 2, 3, 0
				},
				{
					2, 3, 0, 1
				},
				{
					3, 0, 1, 2
				},
				{
					0, 1, 2, 3
				}
		};
		
		for(int i = 0; i < tableOrder[previous].length; i++)
		{
			int n = tableOrder[previous][i];
			
			Component component = Window.ground.getComponentAt(tablePoint[n]);
			
			if(component != null)
			{
				if(component.getClass().getName().equals("javax.swing.JLayeredPane") ||
						component.getClass().getName().equals("Hero"))
				{
					setLocation(tablePoint[n].getLocation());	
					
					if(component.getClass().getName().equals("Hero"))
					{
						System.out.println("Le Hero à trouvé la mort !");
						((Sprite)component).setAlive(false);
						((Sprite)component).setIsActive(false);
					}

					switch(n)
					{
						case 3: previous = 1; break;
						case 1: previous = 3; break;
						case 2: previous = 0; break;
						case 0: previous = 2; break;
					}
					break;
				}
			}
		}
	}
}
