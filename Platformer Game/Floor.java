import java.awt.Color;
public class Floor extends GameObject{

    
    public Floor(int x, int y, int sizeX, int sizeY){
        super(x, y, sizeX, sizeY);
        this.color = RenderPanel.floor;
    }
    
    public void interact(Character c){

        if(c.intersects(this)){

            
            //sets normal force
            if(c.getY() < this.getY() - c.getHeight() + 30){ //if character is on top of obstacle 
                c.dy = 0;
                c.setLocation((int)c.getX(), this.y - (int)c.getHeight());
                
                
                
            }else{                                      //if character is on side of obstacle
                if(Main.right){
                    c.setLocation((int)(this.getX() - c.getWidth() - 10), (int)c.getY());
                }
                if(Main.left){
                    c.setLocation((int)(this.getX() + this.getWidth() + 10), (int)c.getY());
                }             
            }
            

            
        }
    }//end interact()
}//end class