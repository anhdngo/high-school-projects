package gameObjects;

import gui.GameBox;
import gui.Main;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Particles {
	//FIELDS
	private static int maxParticles = 100;
	private static int speed = 16;
	private static int spawnSpeed = 10;
	public static Color particleColor = Color.WHITE;
	public static int size = 5;
		
	//CONSTRUCTOR
	
	
	//METHODS
	public static void update(){
		for(int i = GameBox.particles.size() - 1; i >= 0; i--){
			//moves particle down the screen
			GameBox.particles.get(i).move((int)GameBox.particles.get(i).getX() - speed, (int)GameBox.particles.get(i).getY());
			
			//removes particle if out of bounds
			if(GameBox.particles.get(i).getX() < 0){
				GameBox.particles.remove(i);
			}
		}
		
		//spawns a 3 new particles every spawnSpeed frames if fewer than maxParticles
		if(GameBox.particles.size() < maxParticles && Main.ticks % spawnSpeed == 0){
			GameBox.particles.add(new Point(Main.SCREEN_X, (int)(Math.random() * Main.SCREEN_Y)));
			GameBox.particles.add(new Point(Main.SCREEN_X, (int)(Math.random() * Main.SCREEN_Y)));
			GameBox.particles.add(new Point(Main.SCREEN_X, (int)(Math.random() * Main.SCREEN_Y)));
		}
	}
	

}
