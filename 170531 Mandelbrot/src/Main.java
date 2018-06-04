import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	public static int frameX = 800, frameY = 600;
	public static int originX = 0, originY = 0;
	
	public static void main(String[] args){
		//sets up window
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameX, frameY);
		frame.setVisible(true);
		Main panel = new Main();
		frame.add(panel);
		
		
	}
	
	double scale = 10.0;	//to scale down to decimals
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		for(int x = 0; x < frameX; x++){
			for(int y = 0; y < frameY; y++){
				if(diverges(x/scale, y/scale)){
					g.fillRect(x + originX, frameY - y - originY, 1, 1);
				}
			}
		}
	}
	
	//method returns true when divergent
	//method returns false when convergent
	double constant = 5;
	double divergentValue = 4;	//if complex exceeds this value, we assume divergence
	int iterations = 500;		//if iterations exceeds this value, we assume convergence
	public boolean diverges(double a, double b){
		
		for(int i = 0; i < iterations; i++){
			if(a*a - b*b >= divergentValue){
				System.out.println("divergent at: " + a + " " + b);
				return true;
			}
			
			//(a + bi)^2 = a^2 + 2abi - b^2
			//this block squares the complex number and adds the constant
			double temp = a;
			a = a*a - b*b + constant;
			b = 2*temp*b;
			
		}
		System.out.println("convergent at: " + a + " " + b);
		return false;
		
	}
	
}//end Main


