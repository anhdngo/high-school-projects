import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Main implements KeyListener{
    //OPTIONS: Change at will
    public static int screenX = 1200, screenY = 700;
    public static int delay = 10;  //milleseconds before each frame loads
    
    //SYSTEM VARIABLES: DO NOT CHANGE
    public static Platformer platformer;
    
    public static void main(String[] args){
        //sets up JFrame and panel
        Main main = new Main();
        JFrame frame = new JFrame("AAAA");
        frame.setSize(screenX, screenY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        RenderPanel panel = new RenderPanel();
        frame.add(panel);
        frame.addKeyListener(main);
        
        platformer = new Platformer();
        
        //GameLoop
        while(true){
            platformer.update();
            panel.repaint();
            
            //delay before next frame
            try{
                Thread.sleep(delay);
            }catch(Exception e){}
        }
    }
    
    
    //KEY LISTENER METHODS
    public static boolean up = false, right = false, down = false, left = false, a = false, b = false;
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			up = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			right = true;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			down = true;
		}
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			left = true;
		}
		if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_Z){
			a = true;
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_X || keyCode == KeyEvent.VK_SHIFT){
			b = true;
		}
		if(keyCode == KeyEvent.VK_CONTROL){
			delay = 30;
		}
    }
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			up = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			right = false;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			down = false;
		}
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			left = false;
		}
		if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_Z){
			a = false;
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_X || keyCode == KeyEvent.VK_SHIFT){
			b = false;
		}
		if(keyCode == KeyEvent.VK_CONTROL){
			delay = 10;
		}
    }
    public void keyTyped(KeyEvent e){
        
    }
}