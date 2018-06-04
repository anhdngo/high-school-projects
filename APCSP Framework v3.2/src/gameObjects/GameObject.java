package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;

public abstract class GameObject extends Rectangle{
	//FIELDS
	public double dx, dy, angle;
	public Color color = Color.BLACK;
	
	//CONSTRUCTORS
	public GameObject(int x, int y, int w, int h, double dx, double dy){
		super(x, y, w, h);
		this.dx = dx;
		this.dy = dy;
	}
	
	//METHODS
	public void move(){
		x += dx;
		y += dy;
	}
	
	//getters and setters
//	public int getDX(){return dx;}
//	public int getDY(){return dy;}
//	public int getAngle(){return angle;}
}
