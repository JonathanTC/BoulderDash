import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JFrame {
	
	private Hero hero;
	private JPanel panel = new JPanel();
	private Map map = new Map();
	
	public static JLayeredPane background = new JLayeredPane();
	public static JLayeredPane ground  = new JLayeredPane();
	
	private int[][] data = { 
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2 }, 
		{ 2, 2, 2, 2, 2, 1, 5, 2, 1, 2, 4, 1, 2, 2, 3, 4, 1, 3, 4, 3, 0, 3, 0, 6, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 3, 2, 1, 3, 3, 1, 2, 2, 3, 3, 1, 2, 3, 0, 3, 2, 3, 0, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 3, 4, 2, 2, 2, 1, 2, 3, 2, 2, 0, 7, 0, 1, 3, 3, 3, 3, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 1, 3, 1, 1, 1, 1, 2, 4, 2, 2, 2, 2, 2, 1, 3, 3, 3, 4, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 3, 3, 4, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 3, 4, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 1, 3, 3, 1, 2, 2, 3, 1, 4, 2, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 3, 2, 1, 2, 2, 3, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 3, 3, 3, 3, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 2, 2, 2, 4, 3, 3, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 1, 2, 2, 1, 4, 3, 2, 2, 2, 1, 2, 2, 2, 2, 3, 2, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 4, 3, 2, 1, 3, 2, 2, 2, 2, 1, 2, 2, 3, 3, 3, 2, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 1, 3, 2, 1, 2, 2, 2, 0, 0, 3, 3, 2, 2, 2, 2, 1, 3, 3, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 4, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 4, 3, 1, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
		{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }};
	
	public Window(String pTitle, int pWidth, int pHeight)
	{
		this.setTitle(pTitle);
		this.setVisible(true);
		this.setSize(pWidth, pHeight);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		
		panel.setLayout(new BorderLayout());
		panel.add(ground, 0);
		panel.add(background, 1);
	}
	
	public void init()
	{
		map.load(data);
		addKeyListener(map.getHero());
	}	
}
