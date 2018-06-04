import java.awt.Graphics;
import java.awt.Color;

public class JumpMeter{
    //SETTINGS
    public static double maxJump = 100;
    public static double refillRate = 0.1;
    public static double jumpCost = 30;
    
    public static Color color = RenderPanel.jumpMeter;
    
    //SYSTEM VARIABLES
    public static double currentJump = 100;
    public static boolean firstJump = true;
    
    //METHODS
    public static void reset(){
        currentJump = maxJump;
        firstJump = true;
        color = RenderPanel.jumpMeter;
    }
    
    public static void jump(){
        currentJump -= jumpCost;
    }
    
    public static void paintJumpMeter(Graphics g, int x, int y, int sizeX, int sizeY){
        g.setColor(color);
        g.fillRect(x, y + sizeY - ((int)((currentJump / maxJump) * sizeY)), sizeX, (int)(sizeY * currentJump / maxJump));
        g.setColor(Color.BLACK);
        //g.drawRect(x, y, sizeX, sizeY);
    }
    public static void paintReverseJumpMeter(Graphics g, int x, int y, int sizeX, int sizeY){
        g.setColor(color);
        g.fillRect(x, y, sizeX, sizeY - (int)(sizeY * currentJump / maxJump));
        g.setColor(Color.BLACK);
        //g.drawRect(x, y, sizeX, sizeY);
    }
    
    public static void update(){
        if(currentJump < maxJump){
            currentJump += refillRate;
        }
    }
}