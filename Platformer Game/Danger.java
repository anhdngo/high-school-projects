import java.awt.Color;

public class Danger extends GameObject{
    public Danger(int x, int y, int sizeX, int sizeY){
        super(x, y, sizeX, sizeY);
        this.color = RenderPanel.bases;
    }
    
    public void interact(Character c){
        if(c.intersects(this)){
            //c.ay = 0;
            Main.platformer.reset();  //character dies
        }
    }
}