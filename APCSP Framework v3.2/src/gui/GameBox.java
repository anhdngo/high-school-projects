package gui;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import gameObjects.Bullet;
import gameObjects.Enemy;
import gameObjects.Particles;
import gameObjects.Player;

public class GameBox {

	//FIELDS
	public static final double GRAVITY = 0.0, WIND = -0.06, FRICTION = 0.1, SPEED_LIMIT = 5;
	public static double gravity = 0.0, wind = WIND, friction = 0.05, speed_limit = 5;
	public static int dxInitial = 10, dyInitial = 0;
	
	public static int score = 0, highScore = 0, timeSurvived = 0, maxTimeSurvived = 0;
	
	//static fields here
	public static Player player;
	//arrays of stuff here
	public static ArrayList<Bullet> playerBullets;
	public static ArrayList<Bullet> enemyBullets;
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Point> particles;
	
	//CONSTRUCTOR
	public GameBox(){
		reset();
	}
	
	//METHODS
	public static void reset(){
		player = new Player(Main.SCREEN_X / 5, Main.SCREEN_Y / 2, dxInitial, dyInitial);	//SET TO WORLD LATER
		playerBullets = new ArrayList<Bullet>();
		enemyBullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		particles = new ArrayList<Point>();
		
		SlowBar.reset();
		score = 0;
		timeSurvived = 0;
		Enemy.reset();
		bomb = 3;
	}
	public static void update(){
		player.move();
		Enemy.update();
		Bullet.update();
		Particles.update();
		
		//sets highScore to max score
		if(score > highScore){
			highScore = score;
		}
		
		//timeSruvived increases every 100 ticks
		if(Main.ticks % 100 == 0){
			timeSurvived += 1;
			if(timeSurvived > maxTimeSurvived){
				maxTimeSurvived = timeSurvived;
			}
		}
	}
	//pauses a bit before resetting so player can see their mistake
	public static void pause(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

		}
	}
	
	//SHIFT TO SLOW DOWN METHODS
	public static void slowDown(){
		wind = WIND / 4;
		player.turnSpeed = Player.TURNSPEED / 2;
		player.isSlowedDown = true;
		Main.timer.setDelay(Main.delay * 2);
		player.color = SlowBar.color;
		RenderPanel.bgColor = RenderPanel.slowColor;
	}
	public static void normalSpeed(){
		wind = WIND;
		player.turnSpeed = Player.TURNSPEED;
		player.isSlowedDown = false;
		Main.timer.setDelay(Main.delay);
		player.color = Color.BLACK;
		RenderPanel.bgColor = RenderPanel.defaultBG;
	}
	
	//bomb clears all bullets and enemies
	public static int bomb = 3;
	public static void bomb(){
		if(bomb > 0){
			RenderPanel.bombed = 3;
			enemyBullets = new ArrayList<Bullet>();
			enemies = new ArrayList<Enemy>();
			bomb--;
		}
	}
}
