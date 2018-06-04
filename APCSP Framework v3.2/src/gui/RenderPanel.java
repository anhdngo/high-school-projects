package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import gameObjects.Bullet;
import gameObjects.Enemy;
import gameObjects.Particles;
import gameObjects.Player;

public class RenderPanel extends JPanel{

	public static final Color defaultBG = Color.decode("#C5EFF7");
	public static Color bgColor = defaultBG, bombColor = Color.decode("#c0392b"), slowColor = Color.decode("#E4F1FE");
	public static int bombed = 0;
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();	//makes sure rotate() doesn't mess up future drawings
		
		
		//Draw BG
		g2d.setColor(bgColor);
		g2d.fillRect(0, 0, Main.SCREEN_X, Main.SCREEN_Y);
		//Draw Bomb BG if bomb is set off
		if(bombed > 0){
			g2d.setColor(bombColor);
			g2d.fillRect(0, 0, Main.SCREEN_X, Main.SCREEN_Y);
		}
		
		//DRAW PARTICLES
		for(Point p: GameBox.particles){
			g2d.setColor(Particles.particleColor);
			g2d.fillOval((int)p.getX(), (int)p.getY(), Particles.size, Particles.size);
			g2d.setColor(Color.BLACK);
			g2d.drawOval((int)p.getX(), (int)p.getY(), Particles.size, Particles.size);
		}
		
		//DRAW PLAYER BULLETS
		for(Bullet b: GameBox.playerBullets){
			g2d.setColor(Bullet.friendlyColor);
			g2d.fillOval(b.x, b.y, b.size, b.size);
		}
		//DRAW ENEMY BULLETS
		for(Bullet b: GameBox.enemyBullets){
			g2d.setColor(Bullet.enemyColor);
			g2d.fillOval(b.x, b.y, b.size, b.size);
		}
		
		//DRAW ENEMY
		for(Enemy e: GameBox.enemies){
			g2d.setColor(e.color);
//			if(e.enemyType > 0){
				g2d.fillRect(e.x, e.y, e.width, e.height);
//			}else{
//				g2d.drawRect(e.x, e.y, e.width, e.height);
//			}
		}
		//DRAW PLAYER
		Player p = GameBox.player;
//		g2d.setColor(Color.RED); //PAINT HITBOX
//		g2d.drawRect((int)(p.getX()), (int)(p.getY()), (int)(p.getWidth()), (int)(p.getHeight()));
		
		g2d.rotate(p.angle, p.getX() + p.getHeight() / 2, p.getY() + p.getWidth() / 2);
		//draw player
		g2d.setColor(p.color);
		g2d.fillPolygon(p.getXPoints(), p.getYPoints(), 3);
		//draw player tip
		g2d.setColor(Color.decode("#67809F"));
		g2d.fillPolygon(p.getXTipPoints(), p.getYTipPoints(), 3);
		//draw player outline
		g2d.setColor(Color.BLACK);
		g2d.drawPolygon(p.getXPoints(), p.getYPoints(), 3);
		//reset rotation
		g2d.setTransform(old);
		
		//DRAW SLOW BAR
		g2d.setColor(SlowBar.color);
		g2d.fillRect(20, 20, 20, SlowBar.getCurrentBarHeight());
		g2d.setColor(Color.BLACK);
		g2d.drawRect(20, 20, 20, SlowBar.getBarHeight());
		
		//DRAW BOMBS
		g2d.setColor(Color.BLACK);
		int b = GameBox.bomb;
		g2d.drawString("BOMBS", 20, 250);
		for(int i = 0; i < b; i++){
			g2d.setColor(bombColor);
			g2d.fillOval(25, 260 + 50 * i, 35, 35);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(25, 260 + 50 * i, 35, 35);
		}
		
		//DRAW SCORE AND OTHER STATS
		g2d.drawString("Score:               " + GameBox.score, 50, 30);
		g2d.drawString("HighScore:      " + GameBox.highScore, 50, 50);
		g2d.drawString("TimeSurvived: " + GameBox.timeSurvived, 50, 70);
		g2d.drawString("MaxTimeSurvived: " + GameBox.maxTimeSurvived, 50, 90);
		
		g2d.drawString("spawnFrequency:               " + Enemy.spawnFrequency, 200, 30);
		g2d.drawString("specialSpawnFrequency: " + Enemy.specialSpawnFrequency, 200, 50);
		g2d.drawString("shooterFireFrequency:      " + Enemy.shooterFireFrequency, 200, 70);
		g2d.drawString("enemyBulletSpeed:           " + Bullet.enemySpeed, 200, 90);
		
		g2d.drawString("Arrow Keys or WASD to move", 600, 30);
		g2d.drawString("Space or Z to shoot", 600, 50);
		g2d.drawString("Shift or X to slow", 600, 70);
		g2d.drawString("CTRL or C to bomb", 600, 90);

		//if bomb is set off and bg is changed, this changes it back after 3 frames
		if(bombed > 0){
			bombed--;
		}
	}
}
