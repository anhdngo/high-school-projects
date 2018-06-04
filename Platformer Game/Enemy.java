public class Enemy extends Danger{
    //SETTINGS
    public static int size = 100;
    
    public Enemy(int x, int y, int length, int width){
        super(x, y, length, width);
        color = RenderPanel.enemy;
        dx = Math.random() * 15 - 15;
        dy = Math.random() * 6 - 3;
    }
    
    public Enemy(int x, int y){
        this(x, y, size, size);
    }
}