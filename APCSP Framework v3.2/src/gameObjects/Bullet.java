package gameObjects;

import java.awt.Color;

import gui.GameBox;
import gui.Main;

public class Bullet extends GameObject{

	//CONSTANTS
	public static Color friendlyColor = Color.BLACK, enemyColor = Color.RED;
	public static int size = 10;
	
	//FIELDS
	public static final double ENEMY_SPEED = 3;
	public static double friendlySpeed = 15, enemySpeed = ENEMY_SPEED;
	
	
	//CONSTRUCTORS
	public Bullet(int x, int y, double angle, double speed){
		super(x, y, size, size, speed * Math.sin(angle), -friendlySpeed * Math.cos(angle));
	}
	public Bullet(int x, int y, int direction){
		super(x, y, size, size, 0, 0);
		if(direction % 8 == 0){dy -= enemySpeed; dx -= Enemy.enemySpeed2;}
		if(direction % 8 == 1){dy -= enemySpeed; dx += enemySpeed;}
		if(direction % 8 == 2){dx += enemySpeed;}
		if(direction % 8 == 3){dx += enemySpeed; dy += enemySpeed;}
		if(direction % 8 == 4){dy += enemySpeed; dx -= Enemy.enemySpeed2;}
		if(direction % 8 == 5){dy += enemySpeed; dx -= enemySpeed;}
		if(direction % 8 == 6){dx -= 2 * enemySpeed;}
		if(direction % 8 == 7){dx -= enemySpeed; dy -= enemySpeed;}
	}
	
	//METHODS
	public static void update(){
		//MOVES PLAYER BULLETS
		for(int i = GameBox.playerBullets.size() - 1; i >= 0; i--){
			Bullet b = GameBox.playerBullets.get(i);
			
			b.move();
			
			//removes bullet if out of bounds
			if(b.x > Main.SCREEN_X || b.x < 0 || b.y > Main.SCREEN_Y || b.y < 0){
				GameBox.playerBullets.remove(i);
			}
		}
		//MOVES ENEMY BULLETS
		for(int i = GameBox.enemyBullets.size() - 1; i >= 0; i--){
			Bullet b = GameBox.enemyBullets.get(i);
			
			b.move();
			
			//removes bullet if out of bounds
			if(b.x > Main.SCREEN_X || b.x < 0 || b.y > Main.SCREEN_Y || b.y < 0){
//				GameBox.playerBullets.remove(i);
			}
		}
		
	}
	
	
}
