
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Hero extends Sprite implements KeyListener{
	
	private Point savePosition;
	private int nbDiamond = 0;
	private int vy = -10;
	
	// constructeur
	
	public Hero(int pX, int pY)
	{
		super(pX, pY);
		this.path = "hero.png";
		this.setImage();
		addKeyListener(this);
		timer.setDelay(16);
	}
	
	// accesseurs
	
	public int getDiamond()
	{
		return this.nbDiamond;
	}
	
	// methodes
	
	public void update()
	{
		super.update();
		
		if(!this.isActive)
		{	
			if(alive)
			{
				if(getY() == savePosition.y && this.vy > 0)
				{
					this.vy = -10;
				}
				else
				{
					setY(getY() + vy);
					this.vy += 1;
				}
			}
			else
			{
				setY(getY() + vy);
				this.vy += 1;
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(this.isActive)
		{
			int x = getX(), y = getY();

			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP: 	y -= Main.TILESIZE; break;
				case KeyEvent.VK_DOWN: 	y += Main.TILESIZE; break;
				case KeyEvent.VK_LEFT: 	x -= Main.TILESIZE; break;
				case KeyEvent.VK_RIGHT: x += Main.TILESIZE; break;
				default: break;
			}
				
			Component component = Window.ground.getComponentAt(x, y);
			
			Boolean move = true;
			
			switch(component.getClass().getName())
			{
				case "Block":	move = false; 
								break;
				case "Rock": 	if(y == getY())
								{ 
									String next = Window.ground.findComponentAt(x + Main.TILESIZE, y).getClass().getName();
									
									if(x > getX() && next.equals("javax.swing.JLayeredPane"))
									{
										component.setLocation(x + Main.TILESIZE, y);
										move = false;
										break;
									}
	
									next = Window.ground.findComponentAt(x - Main.TILESIZE, y).getClass().getName();
									
									if(x < getX() && next.equals("javax.swing.JLayeredPane"))
									{
										component.setLocation(x - Main.TILESIZE, y);
										move = false;
										break;
									}
								};
								move = false;
								break;
				case "Dirt":	Window.ground.remove(component); 
								break;
				case "Diamond": Window.ground.remove(component); 
								nbDiamond++; 
								System.out.println("le Hero récupere un diamand: " + this.nbDiamond);
								break;
				case "End": 	move = !((End)component).getIsSolid();
								if(!((End)component).getIsSolid())
								{
									System.out.println("le Hero à réussi sa quête, Bien Jouer !!!");
									this.isActive = false;
									this.savePosition = getLocation();
								}
								break;
				default: break;
			}
			
			if(move)
			{
				int moveX = 0, moveY = 0;
				
				if((getX() > 8 * Main.TILESIZE) && (getX() < x))
				{
					moveX = -Main.TILESIZE;
				}
				else if((getX() < 7 * Main.TILESIZE) && (getX() > x))
				{
					moveX = Main.TILESIZE;
				}
				else
				{
					setX(x);
				}
				
				if((getY() > 8 * Main.TILESIZE) && (getY() < y))
				{
					moveY = -Main.TILESIZE;
				}
				else if((getY() < 7 * Main.TILESIZE) && (getY() > y))
				{
					moveY = Main.TILESIZE;
				}
				else
				{
					setY(y);
				}
				
				for(Component component1 : Window.ground.getComponents())
				{
					if(Window.ground.getComponentZOrder(component1) != 0)
					{
						component1.setLocation(component1.getX() + moveX, component1.getY() + moveY);
					}
				}
			}
		}
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		
	}
}
