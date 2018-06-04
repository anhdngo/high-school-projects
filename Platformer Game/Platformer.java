import java.util.ArrayList;

public class Platformer{
    
    //SYSTEM VARIABLES: DO NOT CHANGE
    Character c = new Character();
    //arraylists to store all objects
    public static ArrayList<Danger> buildingBases;
    public static ArrayList<Floor> floors;
    public static ArrayList<Danger> obstacles;
    
    public static ArrayList<Enemy> enemies;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<MyBullet> myBullets;
    
    public static ArrayList<Particle> particles;
    public static ArrayList<PowerUp> powerups;
    
    public static double score = 0;
    public static double highScore = 0;
    
    public Platformer(){
        reset();
    }
    
    public void update(){
        //updates all game objects
        c.update();
        for(GameObject o: floors){o.update();}
        for(GameObject o: buildingBases){o.update();}
        for(GameObject o: obstacles){o.update();}
        for(GameObject o: enemies){o.update();}
        for(GameObject o: bullets){o.update();}
        for(GameObject b: myBullets){b.update();}
        for(GameObject p: particles){p.update();}
        for(GameObject p: powerups){p.update();}
        
        ObjectControl.addPlatform(floors, buildingBases, obstacles);
        ObjectControl.addEnemy(enemies);
        ObjectControl.addPowerUp(powerups);
        
        score += 0.1;
        
        //static updates
        JumpMeter.update();
        Particle.update(particles);
        MyBullet.update(myBullets, enemies);
        PowerUp.updatePowerUps(powerups);
        
    }
    
    //when character dies
    public void reset(){
        //color options
        RenderPanel.randomScheme();
        
        //reset arrayLists
        buildingBases = new ArrayList<Danger>();
        floors = new ArrayList<Floor>();
        obstacles = new ArrayList<Danger>();
        
        particles = new ArrayList<Particle>();
        Particle.initialize(particles);
        
        myBullets = new ArrayList<MyBullet>();
        
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        
        powerups = new ArrayList<PowerUp>();
        
        //Initial Objects
        double random = Math.random();
        
        floors.add(new Floor(0, 500, 2000, ObjectControl.nextFloorHeight));
        buildingBases.add(new Danger(10, 500+ObjectControl.nextFloorHeight, 2000-20 , 800));
        powerups.add(new PowerUp(10, -20));
        
        floors.add(new Floor(2200, 350, 600, ObjectControl.nextFloorHeight));
        buildingBases.add(new Danger(2210, 350+ObjectControl.nextFloorHeight, 600 -20, 800));
        powerups.add(new PowerUp( 2215, 300));
        
        floors.add(new Floor(3200, 450 + (int)(random * 150), ObjectControl.nextFloorWidth, ObjectControl.nextFloorHeight));
        buildingBases.add(new Danger(3210, 450+ObjectControl.nextFloorHeight + (int)(random * 150), ObjectControl.nextFloorWidth -20, 800));
        
        floors.add(new Floor(3200+ObjectControl.nextFloorWidth+500, 400, ObjectControl.nextFloorWidth, ObjectControl.nextFloorHeight));
        buildingBases.add(new Danger(3200+ObjectControl.nextFloorWidth+510, 400+ObjectControl.nextFloorHeight, ObjectControl.nextFloorWidth -20, 800));  
        
        obstacles.add(new Danger(2210+ObjectControl.nextFloorWidth/2, 450+ObjectControl.nextFloorHeight+ObjectControl.obstacleHeight, ObjectControl.obstacleHeight, ObjectControl.obstacleHeight)); 
        obstacles.add(new Danger(2210+ObjectControl.nextFloorWidth/2, 450+ObjectControl.nextFloorHeight+ObjectControl.obstacleHeight, ObjectControl.obstacleHeight, ObjectControl.obstacleHeight));
        

        enemies.add(new Enemy(Main.screenX, 200, 100, 100));
        
        c.reset();
        
        if(score > highScore){
            highScore = score;
        }
        score = 0;
        
        //resets jumpMeter
        JumpMeter.reset();
    }
    
    
}