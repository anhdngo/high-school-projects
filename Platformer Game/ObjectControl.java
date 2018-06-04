import java.util.ArrayList;

public class ObjectControl{
    //SETTINGS
    
    //floor settings
    public static int floorLimit = Main.screenY/2;
    public static int nextFloorY, nextFloorX; 
    public static int floorYChange = (int)(Math.random()*100);                  //vertical gap
    public static int floorXChange = (int)((Math.random()*50)+150);             //horz gap between floors
    public static int nextFloorHeight = 50;//(int)((Math.random()*20)+40);      //floor thickness
    public static int nextFloorWidth = (int)((Math.random()*800)+200);          //floor length
    public static int overhang = 2;
    
    //obstacle settings
    public static int obstacleLocInFlo = 0;
    public static int obstacleHeight = 50;
    
    //enemy settings
    public static int maxEnemies = 2;
    
    //powerup settings
    public static int pupX, pupY;
    
    //ADDS PLATFORM
    public static void addPlatform(ArrayList<Floor> floors, ArrayList<Danger> buildingBases, ArrayList<Danger> obstacles){
        //RANDOMIZES EVERYTHING
        floorYChange = (int)(Math.random()*200);
        floorXChange = (int)((Math.random()*50)+150);
        //nextFloorHeight = 30;//(int)((Math.random()*20)+40);
        nextFloorWidth = (int)((Math.random()*800)+200);            
        
        
        Floor firstFloor = floors.get(0), prevFloor;
            
        if((firstFloor.getX()+firstFloor.getWidth())<0){
            floors.remove(0);
            prevFloor = floors.get(floors.size()-1);
            
            buildingBases.remove(0);
            
            //ADDS FLOOR======================================================================================
            nextFloorY = (int)(prevFloor.getY() + floorYChange); 
            if(nextFloorY < floorLimit){
                //if nextFloorY is too high
                nextFloorY += (int) ((Math.random()*200)+100);
            }
            else if(nextFloorY + nextFloorHeight + 100 > Main.screenY){
                //if nextFloorY is too low
                //next floor is above bottom of screen
                nextFloorY = Main.screenY-100;
                //adds random y to next floor
                nextFloorY -= (int) ((Math.random()*100)+100);
            } 
            else{
                //if nextFloorY is in good range
                nextFloorY = (int) (prevFloor.getY() + (int)(Math.random()*100));
            }
            
            //if next floor is lower, make gap bigger
            if(nextFloorY > prevFloor.getY()){
                floorXChange += (int)(Math.random() * 200);
            }
            
            nextFloorX = (int)(prevFloor.getX() + prevFloor.getWidth() + floorXChange);
            if(Math.random() > 0.8){nextFloorX += Math.random() * 300 + 100;}
            //Adds a randomly generated floor at the end of "floors" list
            floors.add(new Floor(nextFloorX - 5, nextFloorY, nextFloorWidth, nextFloorHeight));
            buildingBases.add(new Danger(nextFloorX + overhang - 2, nextFloorY + nextFloorHeight, nextFloorWidth - 5 * overhang, 500));
            
            //ADDS FLOOR======================================================================================
            
            //ADDS RANDOM OBSTACLE ON LONG FLOORS=============================================================
            if(nextFloorWidth > 600){
                if(Math.random() < .5){
                    if(obstacles.get(0).getX() + obstacleHeight < 0){
                        obstacles.remove(0);
                        obstacleLocInFlo = (int)(Math.random() * nextFloorWidth / 2 + nextFloorWidth / 4);
                        obstacles.add(new Danger(nextFloorX + obstacleLocInFlo, nextFloorY - obstacleHeight, obstacleHeight, obstacleHeight));
                    }
                }
            }
        }
    }
    
    public static void addEnemy(ArrayList<Enemy> enemies){
        if(enemies.size() < maxEnemies && Math.random() < Platformer.score / 5000){
            enemies.add(new Enemy(Main.screenX, (int)(Main.screenY * Math.random())));
        }
        for(int i = enemies.size() - 1; i >= 0; i--){
            if(enemies.get(i).x < -enemies.get(i).size ||  enemies.get(i).y < -enemies.get(i).size || enemies.get(i).x > Main.screenX || enemies.get(i).y > Main.screenY){
                enemies.remove(i);
            }
        }
        
        maxEnemies = (int)(Platformer.score / 10);
    }
    
    public static void addPowerUp(ArrayList<PowerUp> powerups){
        pupX = nextFloorX + nextFloorWidth/2;
        pupY = nextFloorY - PowerUp.sizeY - (int)Math.random()*100 - 100;
        if(powerups.get(0).x<(0-PowerUp.sizeX) && Math.random() < 0.001){
            powerups.remove(0);
            powerups.add(new PowerUp(pupX, pupY));
        }
        

    }
}