import java.util.ArrayList;
import java.awt.Color;

public class Particle extends GameObject{
    //SETTINGS
    public static int size = 10;
    public static int maxParticles = 50;
    public static Color color = RenderPanel.particles;
    public static double scrollSpeed = -12;
    
    //creates random particle at end of screen
    public Particle(){
        super(Main.screenX + 10, (int)(Main.screenY * Math.random()), size, size, scrollSpeed);
    }
    //creates random particle anywhere on screen
    public Particle(boolean bool){
        super((int)(Main.screenX * Math.random()) , (int)(Main.screenY * Math.random()), size, size, scrollSpeed);
    }
    
    //fills initial array with random particles
    public static void initialize(ArrayList<Particle> particles){
        for(int i = 0; i < maxParticles; i++){
            particles.add(new Particle(true));
        }
    }
    
    //update
    public static void update(ArrayList<Particle> particles){
        for(int i = particles.size() - 1; i > 0; i--){
            if(particles.get(i).x < 0){
                particles.remove(i);
                particles.add(new Particle());
                
            }
        }
    }
}