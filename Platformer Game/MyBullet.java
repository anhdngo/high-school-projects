import java.util.ArrayList;

public class MyBullet extends GameObject{
    //SETTINGS
    public static int size = 10;
    public static double speed = 10;
    public static double shootCost = 2;
    public static int arrowSize = 200;
    
    public MyBullet(Character c){
        super(c.x + Character.size / 2, c.y + Character.size / 2, size, size);
        dx = speed;
        dy = (Math.random() * 4.0 - 2);
        color = RenderPanel.myBullet;
    }
    
    public MyBullet(){
        super(100, 2, size, size);
        dx = 5;
        dy = Math.random() * 2 - 1;
        color = RenderPanel.myBullet;
    }
    
    public MyBullet(int x, int y, double dx){
        super(x, y, size, size);
        this.dx = dx;
    }
    
    public static void burst(){
        for(int i = 0; i < Main.screenX; i++){
            Platformer.myBullets.add(new MyBullet(-5, i, 10));
        }
    }
    
    public static void arrowShot(Character c){
        int forLoopLength = c.size+arrowSize;
        int bulletX = c.x;
        int bulletY = c.y-arrowSize/2; 
        for(int i = 0; i<forLoopLength; i++){
            Platformer.myBullets.add(new MyBullet(bulletX, bulletY, 10));  
            if(i<forLoopLength/2){
                bulletX += 1;
            }
            else
                bulletX -= 1;
            bulletY +=1;
        }
    }
    
    public static void update(ArrayList<MyBullet> myBullets, ArrayList<Enemy> enemies){
        for(int i = myBullets.size() - 1; i >= 0; i--){
            if(myBullets.get(i).x > Main.screenX){
                myBullets.remove(i);
                break;
            }
            
            for(int j = enemies.size() - 1; j >= 0; j--){
                if(myBullets.get(i).intersects(enemies.get(j))){
                    myBullets.remove(i);
                    enemies.remove(j);
                    break;
                }
            }
        }
    }
    
}