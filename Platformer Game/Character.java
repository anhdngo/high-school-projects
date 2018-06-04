import java.awt.Color;

public class Character extends GameObject{
    
    //SETTINGS
    public double speed = 5;                //horizontal speed
    public double gravity = 0.5;
    public double jumpHeight = 5;
    public int fireRate = 5;
    
    public static int startX = Main.screenX / 5, startY = Main.screenY / 2;
    
    //SYSTEM VARIABLES
    public static int size = 50;
    public static int fire = 0;
    
    public Character(){
        super(startX, startY, size, size);
        this.ay = gravity;
        this.color = RenderPanel.character;
    }
    
    //movement methods
    public void moveRight(){
        x += speed;
    }
    public void moveLeft(){
        x -= speed;
    }
    
    //jump
    public void jump(){
        //if touching floor, does not decrease double jump meter
        if(touchingFloor()){
            y -= jumpHeight;
            dy = -10;
            
        }
        //double jump
        else if(JumpMeter.currentJump > JumpMeter.jumpCost && this.dy > 1 && JumpMeter.firstJump){
            JumpMeter.jump();
            y -= jumpHeight;
            dy = -10;
        }
    }
    
    //shoot
    public void shoot(){
        if(JumpMeter.currentJump > MyBullet.shootCost + 2 && fire % fireRate == 0){
            Platformer.myBullets.add(new MyBullet(this));
            JumpMeter.currentJump -= MyBullet.shootCost;
        }
        fire++;
    }
    
    public boolean touchingFloor(){
        for(GameObject g: Platformer.floors){    
            if(g.intersects(this)){
                JumpMeter.firstJump = true;
                return true;
            }
        }
        return false;
    }
    
    public void update(){
        //natural movement
        //ADD INTERSECTION SHT UNFINISHED <=====================================<
        //System.out.println(dx + " " + dy + " " + ay);
        
        x += dx;
        y += dy;
        
        dx += ax;
        dy += ay;
        
        //input movement
        if(Main.left){
            moveLeft();
        }
        if(Main.right){
            moveRight();
        }
        if(Main.up){
            jump();
        }
        if(Main.down || Main.a || Main.b){
            shoot();
        }
        
        //interacts with everything
        for(Danger d: Platformer.buildingBases){
            d.interact(this);
        }
        for(Floor f: Platformer.floors){
            f.interact(this);
        }
        for(Danger d: Platformer.obstacles){
            d.interact(this);
        }
        for(Danger d: Platformer.enemies){
            d.interact(this);
        }
        for(PowerUp p: Platformer.powerups){
            p.interact(this);
        }
        
        //dies if under screen
        if(getY() > Main.screenY){
            Main.platformer.reset();
        }
        
        //for jump purposes
        touchingFloor();
    }
    
    public void reset(){
        setLocation(startX, startY);
        dx = 0;
        dy = 0;
    }
}