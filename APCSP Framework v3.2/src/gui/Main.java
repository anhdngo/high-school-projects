//MADE ON 3/20/17
//ANH NGO

package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Main implements ActionListener, KeyListener{

	//CONSTANTS
	public static final int SCREEN_X = 1600, SCREEN_Y = 800;
	public static int delay = 10;
	
	//FIELDS
	public static Main main;
	public static JFrame frame;
	public static RenderPanel panel;
	public static Timer timer;
	public static GameBox game;
	public static int ticks;
	
	
	//METHODS
	public static void main(String[] args){
		main = new Main();
		
		frame = new JFrame("Awesome-Polygons-Create-Serious-Pwnage (APCSP) v3.2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_X, SCREEN_Y);
		frame.setVisible(true);
		frame.setResizable(true);
		
		panel = new RenderPanel();
		frame.add(panel);
		
		game = new GameBox();
		
		timer = new Timer(delay, main);
		timer.start();
		
		frame.addKeyListener(main);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		GameBox.update();
		panel.repaint();
		ticks++;
	}
	
	//CONTROLS
	//control booleans
	public static boolean up = false, right = false, down = false, left = false, a = false, b = false;
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			up = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			right = true;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			down = true;
		}
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			left = true;
		}
		if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_Z){
			a = true;
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_X || keyCode == KeyEvent.VK_SHIFT){
			b = true;
			GameBox.slowDown();
		}
		if(keyCode == KeyEvent.VK_CONTROL || keyCode == KeyEvent.VK_C){
			GameBox.bomb();
		}
	}
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			up = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			right = false;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			down = false;
		}
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			left = false;
		}
		if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_Z){
			a = false;
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_X ||  keyCode == KeyEvent.VK_SHIFT){
			b = false;
			GameBox.normalSpeed();
		}
	}
	public void keyTyped(KeyEvent e) {
		//THIS DOES NOTHING, JUST HERE TO OVERRIDE KEYLISTENER INTERFACE :D
	}

}
