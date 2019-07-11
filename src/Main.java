public class Main {
	
	static int SCALE = 2;
	static int TILESIZE = 16 * SCALE;
	static int WIDTH  = 16 * TILESIZE;
	static int HEIGHT = 16 * TILESIZE;
	static int OFFSETX = 0;
	static int OFFSETY = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window w = new Window("Boulder dash", WIDTH, HEIGHT);
		
		w.init();
		
		while(true)
		{
			w.repaint();
		}
	}
}
