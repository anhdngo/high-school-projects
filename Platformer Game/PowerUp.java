import java.awt.Color;
import java.util.ArrayList;

public class PowerUp extends GameObject{
    public static int sizeX = 20;
    public static int sizeY = 50;
    public static String dialog = "false";
    public PowerUp(int x, int y){
        super(x, y, sizeX, sizeY);
        this.color = RenderPanel.pup;
        dx = GameObject.scrollSpeed / 2 + 1;
    }
    
    public static void updatePowerUps(ArrayList<PowerUp> powerups){
        for(PowerUp p: Platformer.powerups){
            p.update();
        }
    }
    
    public void interact(Character c){
        if(c.intersects(this)){
            powerUpEffect();
            this.x = -sizeX;
        }
    }
    
    public void powerUpEffect(){
        int random = (int)(Math.random() * 3);
        switch(random){
            case 0:
            JumpMeter.currentJump = JumpMeter.maxJump;
            dialog = "recharge";
            break;
            
            case 1:
            MyBullet.burst();
            dialog = "burst";
            break;
            
            case 2:
            MyBullet.arrowShot(Main.platformer.c);
            dialog = "arrow";           
            break;
            
        }
    }
    
    public static void dialogReset(){
        dialog = "false";
    }
}