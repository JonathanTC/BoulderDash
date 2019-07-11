import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Sprite extends JPanel {
	
	protected String path = "empty.png";
	protected Boolean isSolid = false;
	protected Boolean isMoveable = false;
	protected Boolean isActive = true;
	protected Image img;
	protected int speed = 0;
	protected int time = 0;
	protected Boolean alive = true;
	
	//protected JPanel panel;
	protected Timer timer;
	
	// constructeur
	
	public Sprite(int pX, int pY)
	{
		this.setLocation(pX, pY);
		this.setSize(Main.TILESIZE, Main.TILESIZE);
		
		//panel = Window.panel;
		
		timer = new Timer(175, new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				update();
			}
		});
		
		timer.start();
	}
	
	public Sprite(String pPath, int pX, int pY)
	{
		this.setLocation(pX, pY);
		this.setSize(Main.TILESIZE, Main.TILESIZE);
		this.path = pPath;
		
		setImage();
	}
	
	// methodes
	
	public void applyGravity()
	{
		Component componentDown = Window.ground.getComponentAt(getX(), getY() + Main.TILESIZE);
		Component componentLeft = Window.ground.getComponentAt(getX() - Main.TILESIZE, getY());
		Component componentRight = Window.ground.getComponentAt(getX() + Main.TILESIZE, getY());
		Component componentLeftDown = Window.ground.getComponentAt(getX() - Main.TILESIZE, getY() + Main.TILESIZE);
		Component componentRightDown = Window.ground.getComponentAt(getX() + Main.TILESIZE, getY() + Main.TILESIZE);
		
		if(componentDown != null)
		{
			if(componentDown.getClass().getName().equals("javax.swing.JLayeredPane"))
			{
				setLocation(getX(), getY() + Main.TILESIZE);
				this.speed++;
			}
			else
			{
				if(componentDown.getClass().getName().equals("Rock") || 
						componentDown.getClass().getName().equals("Diamond"))
				{
					this.speed = 0;

					if((componentLeft != null && componentLeft.getClass().getName().equals("javax.swing.JLayeredPane")) &&
							(componentLeftDown != null && componentLeftDown.getClass().getName().equals("javax.swing.JLayeredPane")))
					{
						setLocation(getX() - Main.TILESIZE, getY());
					}
					else if((componentRight != null && componentRight.getClass().getName().equals("javax.swing.JLayeredPane")) &&
							(componentRightDown != null && componentRightDown.getClass().getName().equals("javax.swing.JLayeredPane")))
					{
						setLocation(getX() + Main.TILESIZE, getY());
					}			
				}
				else if(componentDown.getClass().getName().equals("Hero") ||
								componentDown.getClass().getName().equals("Enemy"))
				{
					if(this.speed > 0)
					{
						if(((Sprite)componentDown).getAlive())
						{
							setLocation(getX(), getY() + Main.TILESIZE);
							
							System.out.println("le Hero est mort !");
							((Sprite)componentDown).setIsActive(false);
							((Sprite)componentDown).setAlive(false);
							
							explode();
						}
					}
				}
				else
				{
					this.speed = 0;
				}
			}
		}
	}
	
	public void update()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
	
		g2d.drawImage(this.img, 
				0, 
				0, 
				Main.TILESIZE, 
				Main.TILESIZE, this);
	}
	
	private void explode()
	{
		int xMin = getX() - Main.TILESIZE;
		int xMax = getX() + Main.TILESIZE;
		int yMin = getY() - Main.TILESIZE;
		int yMax = getY() + Main.TILESIZE;
		
		for(int y = yMin; y <= yMax; y+=Main.TILESIZE)
		{
			for(int x = xMin; x <= xMax; x+=Main.TILESIZE)
			{
				Component component = Window.ground.getComponentAt(x, y);
				
				if(Window.ground.getComponentZOrder(component) != 0)
				{
					Window.ground.remove(component);
				}	
			}
		}
	}
	
	// mutateurs

	public void setImage()
	{
		try
		{
			this.img = ImageIO.read(new File(this.path));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setX(int pX)
	{
		this.setLocation(pX, this.getY());
	}
	
	public void setY(int pY)
	{
		this.setLocation(this.getX(), pY);
	}
	
	public void setIsSolid(Boolean pBool)
	{
		this.isSolid = pBool;
	}
	
	public void setIsActive(Boolean pBool)
	{
		this.isActive = pBool;
	}
	
	public void setPath(String pPath)
	{
		this.path = pPath;
	}

	public void setSpeed(int pSpeed)
	{
		this.speed = pSpeed;
	}
	
	public void setAlive(Boolean pBool)
	{
		this.alive = pBool;
	}
	
	// accesseurs
	
	public Boolean getAlive()
	{
		return this.alive;
	}
	
	public Boolean getIsSolid()
	{
		return this.isSolid;
	}

	public int getSpeed()
	{
		return this.speed;
	}
	
	public Boolean getIsActive()
	{
		return this.isActive;
	}
}