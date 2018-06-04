import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class RenderPanel extends JPanel{
    //SETTINGS: change at will
    
    //SYSTEM VARIABLES
    public static Color character = Color.decode("#212121");
    public static Color bg = Color.WHITE;
    public static Color floor = Color.BLUE;
    public static Color bases = Color.PINK;
    public static Color particles = Color.WHITE;
    public static Color jumpMeter = Color.BLUE;
    public static Color myBullet = Color.BLACK;
    public static Color enemy = Color.RED;
    public static Color text = Color.BLACK;
    public static Color pup = Color.decode("#ffff00");
   
    //SETS COLOR SCHEMES
    public static void randomScheme(){
        int random = (int)(Math.random() * 9);
        switch(random){
            case 0:
                bg = Color.decode("#BBDEFB");
                floor = Color.decode("#1976D2");
                bases = Color.decode("#2196F3");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#03A9F4");
            break;
            case 1:
                bg = Color.decode("#FFCCBC");
                floor = Color.decode("#E64A19");
                bases = Color.decode("#FF5722");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#FFC107");
            break;
            case 2:
                bg = Color.decode("#B2DFDB");
                floor = Color.decode("#00796B");
                bases = Color.decode("#009688");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#9E9E9E");
            break;
            case 3:
                bg = Color.decode("#C8E6C9");
                floor = Color.decode("#388E3C");
                bases = Color.decode("#4CAF50");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#8BC34A");
            break;
            case 4:
                bg = Color.decode("#C5CAE9");
                floor = Color.decode("#303F9F");
                bases = Color.decode("#3F51B5");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#00BCD4");
            break;
            case 5:
                bg = Color.decode("#F8BBD0");
                floor = Color.decode("#C2185B");
                bases = Color.decode("#E91E63");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#FF5252");
            break;
            case 6:
                bg = Color.decode("#CFD8DC");
                floor = Color.decode("#455A64");
                bases = Color.decode("#607D8B");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#9E9E9E");
            break;
            case 7:
                bg = Color.decode("#D1C4E9");
                floor = Color.decode("#512DA8");
                bases = Color.decode("#673AB7");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#E040FB");
            break;
            case 8:
                bg = Color.decode("#B3E5FC");
                floor = Color.decode("#0288D1");
                bases = Color.decode("#03A9F4");
                particles = Color.decode("#FFFFFF");
                jumpMeter = Color.decode("#536DFE");
            break;
        }
        
    }
    
    public void paintComponent(Graphics g){
        //paints backround
        g.setColor(bg);
        g.fillRect(0,0,Main.screenX, Main.screenY);
        
        //paints particles
        g.setColor(Particle.color);
        for(Particle p: Platformer.particles){
            g.fillOval(p.x, p.y, Particle.size, Particle.size);
        }
        
        Platformer p = Main.platformer;
        //paints score
        g.setFont(new Font("Century Gothic", Font.PLAIN, 50));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + (int)Platformer.score, 40, 50);
        g.drawString("HighScore: " + (int)Platformer.highScore, 40, 120);
        

        
        //paints buildingbases
        for(Danger d: p.buildingBases){
            g.setColor(d.color);
            g.fillRect((int)d.x, (int)d.y, (int)d.getWidth(), (int)d.getHeight());
        }
        
        //paints floors
        for(Floor f: p.floors){
            g.setColor(f.color);
            g.fillRect((int)f.x, (int)f.y, (int)f.getWidth(), (int)f.getHeight());
        }
        
        //paints obstacles
        for(Danger d: p.obstacles){
            g.setColor(d.color);
            g.fillRect((int)d.x, (int)d.y, (int)d.getWidth(), (int)d.getHeight());
        }
        
        //paints bullets
        for(Bullet b: Platformer.bullets){
            g.setColor(b.color);
            g.fillOval(b.x, b.y, b.width, b.height);
        }
        
        for(MyBullet b: Platformer.myBullets){
            g.setColor(b.color);
            g.fillOval(b.x, b.y, b.size, b.size);
        }
        
        //paints powerups
        for(PowerUp pup: p.powerups){
            g.setColor(pup.color);
            g.fillOval(pup.x, pup.y, PowerUp.sizeX, PowerUp.sizeY);
        }
        
        //paints player character
        Character c = p.c;
        g.setColor(c.color);
        g.fillRect((int)c.x, (int)c.y, (int)c.getWidth(), (int)c.getHeight());
        JumpMeter.paintReverseJumpMeter(g, (int)c.x, (int)c.y, (int)c.getWidth(), (int)c.getHeight());        
        
        //paints enemies
        for(Enemy e: p.enemies){
            g.setColor(e.color);
            g.fillRect(e.x, e.y, e.width, e.height);
        }
        
        //paints jumpMeter
        JumpMeter.paintJumpMeter(g, 0, 0, 15, Main.screenY);
        JumpMeter.paintJumpMeter(g, Main.screenX - 30, 0, 20, Main.screenY);
        //JumpMeter.paintJumpMeter(g, Main.screenX - 50, 20, 20, 200);
        
        //paints dialog
        Dialogue.paintDialog(g, c.x, c.y - 10 - Dialogue.fontSize);
    }
}
    