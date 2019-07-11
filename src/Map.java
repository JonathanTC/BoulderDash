
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Map {

	private Timer timer;
	private int[][] table;
	private int nbDiamond = 0;
	private End spriteEnd;
	private Hero spriteHero;

	public Map()
	{
		timer = new Timer(16, new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				update();
			}
		});	
	}		

	public void load(int[][] pTable)
	{
		table = pTable;
		
		Window.background.setSize(table[0].length * Main.TILESIZE,  table.length * Main.TILESIZE);
		Window.ground.setSize(table[0].length * Main.TILESIZE,  table.length * Main.TILESIZE);
		
		System.out.println("Création du background");
		
		for(int y=0; y < table.length; y++)
		{
			for(int x=0; x < table[y].length; x++)
			{
				Window.background.add(new Sprite("empty.png", x * Main.TILESIZE, y * Main.TILESIZE));
			}
		}
		
		System.out.println("Création de la map");
		
		for(int y=0; y < table.length; y++)
		{
			for(int x=0; x < table[y].length; x++)
			{
				switch(table[y][x])
				{
				case 1: Window.ground.add( new Block(x * Main.TILESIZE, y * Main.TILESIZE), 1); break;
				case 2: Window.ground.add( new Dirt(x * Main.TILESIZE, y * Main.TILESIZE), 1); break;
				case 3: Window.ground.add( new Rock(x * Main.TILESIZE, y * Main.TILESIZE), 1); break;
				case 4: Window.ground.add( new Diamond(x * Main.TILESIZE, y * Main.TILESIZE), 1); 
						nbDiamond++; 
						break;
				case 5: spriteHero = new Hero(x * Main.TILESIZE, y * Main.TILESIZE);
						Window.ground.add(spriteHero, 0);
						break;
				case 6: spriteEnd = new End(x * Main.TILESIZE, y * Main.TILESIZE);
						Window.ground.add(spriteEnd, 1);
						break;
				case 7: Window.ground.add( new Enemy(x * Main.TILESIZE, y * Main.TILESIZE), 1); break;
				default: break;
				}
			}
		}	
		timer.start();
	}

	public void update()
	{
		if(this.nbDiamond - spriteHero.getDiamond() <= 0) {
			spriteEnd.setIsSolid(false);
		}
	}
	
	public Hero getHero()
	{
		return spriteHero;
	}
}
