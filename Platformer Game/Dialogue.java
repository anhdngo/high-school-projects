import java.awt.Graphics;
import java.awt.Font;

public class Dialogue{
    //SETTINGS
    public static int fontSize = 25;
    public static final int dialogSpeed = 15;
    public static int c=0;
    public static int pupDialogDuration = 30;
    public static boolean printed = false;
    public static void paintDialog(Graphics g, int x, int y){
        g.setFont(new Font("Century Gothic", Font.PLAIN, fontSize));
        g.setColor(RenderPanel.text);
        if(!PowerUp.dialog.equals("false")){
            printed = true;
            g.drawString(pupDialog(), x - pupDialog().length()/5*fontSize, y);
            c++;
        }
        if(!printed){g.drawString(getDialog((int)(Platformer.score / dialogSpeed)), x - getDialog((int)(Platformer.score / dialogSpeed)).length()/5*fontSize, y);}
    }
    
    public static String pupDialog(){
        if(PowerUp.dialog.equals("burst")){
            if(c==pupDialogDuration){
                PowerUp.dialogReset();
                c=0;
                printed = false;
            }
            return "Burst Powerup";
        }
        else if(PowerUp.dialog.equals("recharge")){
            if(c==pupDialogDuration){
                PowerUp.dialogReset();
                c=0;
                printed = false;
            }
            return "Recharge Powerup";
        }
        else if(PowerUp.dialog.equals("arrow")){
            if(c==pupDialogDuration){
                PowerUp.dialogReset();
                c=0;
                printed = false;
            }
            return "Arrow Shot";
        }
        else 
            return "";
    }
    
    public static String getDialog(int i){
        switch(i){
            case 0:
            return "Move with Arrow Keys";
            case 1:
            return "Shoot with Space/Down/Shift";
            case 2:
            return "Ctrl to Slo-Mo";
            case 4:
            return "My family....";
            case 6:
            return "I... I couldn't save them...";
            case 8:
            return "I'm all... alone...";
            case 10:
            return "What am I doing? What is my purpose?";
            case 12:
            return "They're all out to get me...";
            case 14:
            return "To make me jump through hoops...";
            case 16:
            return "But I'm jumping anyways, is that such a bad thing?";
            case 18:
            return "Maybe I should just return to them... to hurl myself off this cliff...";
            case 20:
            return "Maybe... this was what I was meant to do...";
            case 22:
            return "Nah man, I'm just gonna keep jumping cuz this is fun!";
            case 24:
            return "Maybe a bit masochistic, but still pretty fun!";
            
            //extra dialog
            case 26:
            return "Oh wow, you actually made it this far...";
            case 28:
            return "I'm impressed...";
            case 30:
            return "But seriously, don't you have better things to do?";
            case 32:
            return "Like srsly brah, I'm just a game ya know.";
            case 34:
            return "Go code another game! Shoo!";
            case 36:
            return "Still here, huh...";
            case 38:
            return "Well I'll come back when you have " + (50 * dialogSpeed) + " points.";
            case 50:
            return "Wow, you're persistent...";
            case 52:
            return "There's nothing left for you, ya know...";
            case 54:
            return "I guess I should tell you some fun facts about us developers then!";
            case 56:
            return "We're a team of two cool guys named Anh and Alvin!";
            case 58:
            return "We have like fifty girlfriends each!";
            case 60:
            return "We hope Tram and Jennifer don't make it this far... >_>";
            case 62:
            return "Our teacher and the person who assigned us this project is Ms. Terry";
            case 64:
            return "Heh, this game is pretty Terry-ble, huh ;)";
            case 66:
            return "Okay I think this is enough, you should really get some sunlight.";
            case 68:
            return "Or at least play something social, like Overwatch or LOL";
            case 70:
            return "Seriously, I'm not sure what to say after this man...";
            case 72:
            return "I have senioritis okay, I don't really wanna add more dialog...";
            case 74:
            return "Dang, " +(74 * dialogSpeed) + " points...";
            case 76:
            return "You really have no life huh...";
            case 78:
            return "Not that I'm judging...";
            case 80:
            return "Okay maybe I am judging.";
            case 82:
            return "Welp, if I keep spouting dialog we'll be here all day.";
            case 84:
            return "Tell ya what, if you make it to 1 million points...";
            case 86:
            return "I'll have a surprise for ya!";
            case (1000000/dialogSpeed):
            return "Okay, I lied. Congratz though!";
            case (1000000/dialogSpeed + 2):
            return "Tell us the code word \"AnhIsHawt\" in person and we'll give you something for reals!";
            case (1000000/dialogSpeed + 4):
            return "Well bai!";
            
            default:
            return "";
        }
    }
}