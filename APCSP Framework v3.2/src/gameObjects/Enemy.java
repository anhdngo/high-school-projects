package gameObjects;

import java.awt.Color;

import gui.GameBox;
import gui.Main;

public class Enemy extends GameObject{
	//FIELDS
	public static final double SPAWN = 0.005, SPECIAL_SPAWN = 0.001, SHOOTER_FIRE = 50, difficultySpike = 0.00005;
	public static double spawnFrequency = SPAWN, specialSpawnFrequency = SPECIAL_SPAWN, shooterFireFrequency = SHOOTER_FIRE;
	public static int maxEnemies = 50;
	public static int enemySize = 50;
	public static int enemySpeed1 = 10, enemySpeed2 = 1;
	
	public int enemyType;

	public Enemy(int x, int y, double dx, double dy, int type){
		super(x, y, enemySize, enemySize, dx, dy);
		enemyType = type;
		if(type == 0){
			color = Color.decode("#c0392b");
		}else if(type == 1){
			color = Color.decode("#e67e22");
		}else if(type == 2){
			color = Color.decode("#f1c40f");
		}
	}
	
	public static void update(){
		//increases difficulty by making everything spawn more and fire faster
		spawnFrequency += difficultySpike;
		specialSpawnFrequency += difficultySpike;
		shooterFireFrequency -= difficultySpike * 50;
		Bullet.enemySpeed += difficultySpike * 10;
		
		
		for(int i = GameBox.enemies.size() - 1; i >= 0; i--){
			Enemy e = GameBox.enemies.get(i);
			e.move();
			
			//removes enemy if out of bounds
			if(e.x > Main.SCREEN_X || e.x < 0 || e.y > Main.SCREEN_Y || e.y < 0){
				GameBox.enemies.remove(i);
			}
			
			//removes enemy if intersecting playerBullet
			for(Bullet b: GameBox.playerBullets){
				if(e.intersects(b)){
					GameBox.enemies.remove(i);
					GameBox.score += 10;
				}
			}
			
			//implements enemy behaviors
			switch(e.enemyType){
				case 0:
					break;
				case 1:
					e.missileMove();
					break;
				case 2:
					e.shooterMove();
					break;
				default:
					break;
			}
			
		}
		
		//SPAWNS ASTEROID
		if(GameBox.enemies.size() < maxEnemies && Math.random() < spawnFrequency){
			GameBox.enemies.add(new Enemy(Main.SCREEN_X, (int)(Main.SCREEN_Y * Math.random()), -enemySpeed1, 0, ASTEROID));
		}
		//SPAWNS MISSILE
		if(GameBox.enemies.size() < maxEnemies && Math.random() < specialSpawnFrequency){
			GameBox.enemies.add(new Enemy(Main.SCREEN_X, (int)(Main.SCREEN_Y * Math.random()), -enemySpeed2 * 2, 0, MISSILE));
		}
		//SPAWNS SHOOTER
		if(GameBox.enemies.size() < maxEnemies && Math.random() < specialSpawnFrequency){
			GameBox.enemies.add(new Enemy(Main.SCREEN_X, (int)(Main.SCREEN_Y * Math.random()), -enemySpeed2, 0, SHOOTER));
		}
	}
	
	public static final int ASTEROID = 0, MISSILE = 1, SHOOTER = 2;
	
	//missile behavior: get in front of the player
	public void missileMove(){
		super.move();
		if(y > GameBox.player.getY()){
			dy -= 0.1;
		}else{
			dy += 0.1;
		}
	}
	
	//shooter behavior: move and shoot every shooterFireFrequency ticks
	private int direction;
	public void shooterMove(){
		super.move();
		if(Main.ticks % (int)shooterFireFrequency == 0){
			direction ++;
			GameBox.enemyBullets.add(new Bullet(x + enemySize / 2, y + enemySize / 2, direction));
			GameBox.enemyBullets.add(new Bullet(x + enemySize / 2, y + enemySize / 2, direction + 4));
			
		}
	}
	
	//reset difficulties
	public static void reset(){
		spawnFrequency = SPAWN; 
		specialSpawnFrequency = SPECIAL_SPAWN;
		shooterFireFrequency = SHOOTER_FIRE;
		Bullet.enemySpeed = Bullet.ENEMY_SPEED;
	}
	
}
