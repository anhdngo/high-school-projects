package gameObjects;

import gui.GameBox;
import gui.Main;
import gui.SlowBar;

public class Player extends GameObject{
	//FIELDS
	public static final double TURNSPEED = 0.10;
	public static double speed = 0.4, turnSpeed = TURNSPEED, fireSpeed = 5;
	private static int size = 10;
	public static boolean isSlowedDown = false;
	
	//CONSTRUCTORS
	public Player(int x, int y, int dx, int dy){
		super(x, y, size, size, dx, dy);
		angle = Math.toRadians(90);
		turnSpeed = TURNSPEED;
	}
	
	//METHODS
	public void reset(){
		angle = Math.toRadians(45);
	}
	
	//returns coordinates relative to current location so Graphics.drawPolygon draws a triangle
	public int[] getXPoints(){
		int[] array = {(int)(x - width / 2), (int)(x + width / 2), (int)(x + width * 1.5)};
		return array;
	}
	public int[] getYPoints(){
		int[] array = {(int)(y + height), (int)(y - height),(int)(y + height)};
		return array;
	}
	//same as above but for the point and not the body of the triangle
	public int[] getXTipPoints(){
		int[] array = {(int)(x - width / 4) + 2, (int)(x + width / 2), (int)(x + width)};
		return array;
	}
	public int[] getYTipPoints(){
		int[] array = {(int)(y + height), (int)(y - height),(int)(y + height)};
		return array;
	}
	
	//MOVEMENT METHODS
	public void move(){
		//Controls player based on buttons down
		if(Main.up){thrust();}
		if(Main.right){turnRight();}
		if(Main.down){backThrust();}
		if(Main.left){turnLeft();}
		if(Main.a){
			if(Main.ticks % fireSpeed == 0){
				shoot();
			}
		}
		if(Main.b){}
		
		//Moves player based on velocity
		super.move();
		
		//Adds gravity and wind
		dy += GameBox.gravity;
		dx += GameBox.wind;
		
		//If player is near out of bounds, pushes player back in bounds
		//top and bottom
		if(y < 75){dy++;}
		if(y > Main.SCREEN_Y - 75){dy--;}
		if(y < 25){dy += 2;}
		if(y > Main.SCREEN_Y - 25){dy -= 2;}
		//front and back
		if(x > Main.SCREEN_X - 100){dx -= 0.5;}
		if(x < 100){dx += 0.25;}
		//Friction: slows down when not moving
		if(dy > 0){
			dy -= GameBox.friction;
		}else if(dy < 0){
			dy += GameBox.friction;
		}
		if(dx > 0){
			dx -= GameBox.friction;
		}else if(dx < 0){
			dx += GameBox.friction;
		}
		
		//Resets game if player is behind screen
		if(x < -100){
			GameBox.reset();
		}
		
		//Dies and resets if player collides with enemy or enemy bullet
		for(Enemy e: GameBox.enemies){
			if(this.intersects(e)){
				GameBox.pause();
				GameBox.reset();
			}
		}
		for(Bullet b: GameBox.enemyBullets){
			if(this.intersects(b)){
				GameBox.pause();
				GameBox.reset();
			}
		}
		
		//Decreases slowdown meter if player slowed down, and sets normal speed if not slowed down
		if(isSlowedDown){
			SlowBar.current--;
			if(SlowBar.current < 0){
				GameBox.normalSpeed();
			}
		}else{
			SlowBar.current++;
			if(SlowBar.current > SlowBar.max){
				SlowBar.current = SlowBar.max;
			}
		}
		//if not slowed down, add to slowbar
	}
	
	public void thrust(){
		dx += speed * Math.sin(angle);
		dy += -speed * Math.cos(angle);	//negative because axes are reversed in java
	}
	public void backThrust(){
		dx -= speed * Math.sin(angle);
		dy -= -speed * Math.cos(angle);
	}
	public void turnRight(){
		angle += turnSpeed;
	}
	public void turnLeft(){
		angle -= turnSpeed;
	}
	public void shoot(){
		GameBox.playerBullets.add(new Bullet(x, y, angle, Bullet.friendlySpeed));
	}
	
	//slows down player if they are exceeding speed limit
	public void speedLimit(){
		if(dx > GameBox.speed_limit){
			dx = GameBox.speed_limit;
		}
		if(dy > GameBox.speed_limit){
			dy = GameBox.speed_limit;
		}
	}
}
