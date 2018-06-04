import java.awt.Color;
import java.awt.Rectangle;

public abstract class GameObject extends Rectangle{
    //SETTINGS
    public static double scrollSpeed = -9;
    
    //public double x, y;        //position
    public double dx, dy;   //velocity
    public double ax, ay;   //acceleration
    public Color color = Color.BLACK;
    
    public GameObject(int x, int y, int width, int height){
        super(x, y, width, height);
        dx = scrollSpeed;
    }
    
    public GameObject(int x, int y, int width, int height, double scrollSpeed){
        super(x, y, width, height);
        dx = scrollSpeed;
    }
    
    public void interact(){
        System.out.println("Wrong object idiot");
    }
    
    public void update(){
        setLocation((int)(this.getX() + dx), (int)(this.getY() + dy));
        dx += ax;
        dy += ay;
    }
    
}