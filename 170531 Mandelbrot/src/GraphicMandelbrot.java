import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicMandelbrot extends JPanel{
	public static void main(String[] args) {
		/*
		for (double re = -2; re < 2; re += 0.1) {
			for (double img = -2; img < 2; img += 0.1) {
				if(diverges(re, img)){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		*/
		
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		GraphicMandelbrot panel = new GraphicMandelbrot();
		frame.add(panel);
		
		
	}
	
	public void paintComponent(Graphics g){
		
		double re = -2.0, img = -2.0;
		for(int row = 0; row < 600; row++){
			for(int col = 0; col < 800; col++){
				if(diverges(re, img)){
					g.setColor(Color.WHITE);
					g.fillRect(col, row, 1, 1);
					System.out.println("diverges - re: " + re + " img: " + img);
				}else{
					g.setColor(Color.BLACK);
					g.fillRect(col, row, 1, 1);
					System.out.println("converges - re: " + re + " img: " + img);
				}
				re += 0.01;
			}
			re = -2.0;
			img += 0.01;
		}
		
		//debugging
		//g.setColor(Color.RED);
		//g.fillRect(0, 0, 100, 100);
	}

	// iterator
	public static void iterator(Complex c, double re, double img) {
		c.square();
		c.add(re, img);
	}

	// divergence checker
	public static boolean diverges(double re, double img) {
		int iterationsTilConv = 1000;
		double divergenceValue = 2;

		Complex c = new Complex(0, 0);
		for (int i = 0; i < iterationsTilConv; i++) {
			iterator(c, re, img);
			//if point moves farther than divergenceValue away from original point, it diverges
			if(Math.abs(c.re - re) > divergenceValue || Math.abs(c.img - img) > divergenceValue){
				return true;
			}
		}
		return false;
	}

}



class Complex {
	public double re;
	public double img;

	public Complex(double re, double img) {
		this.re = re;
		this.img = img;
	}

	// (a + bi)^2 = a^2 + 2*a*bi - b^2
	public void square() {
		double temp = re;
		re = re * re - img * img;
		img = 2 * temp * img;
	}

	public void add(double re, double img) {
		this.re += re;
		this.img += img;
	}
}

