import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	public static boolean[][] map = new boolean[100][100];
	public static int dimensions = 600;
	public static int scale = dimensions / map.length;
	public static int delay = 1;
	public static ArrayList<Boolean> binaryRule = new ArrayList<Boolean>();

	public static void main(String[] args) {
		System.out.println("Cellular Automata v0.1 - Made by Anh Ngo");
		System.out.println("Commands: \"rule\", \"speed\", \"delay\"");

		String command;
		int argument;

		Scanner scan = new Scanner(System.in);

		boolean valid = true;
		do{
			valid = true;
			System.out.println("Enter Next Command:");
			try {
				String in = scan.nextLine();
				String[] input = in.split(" ");
				command = input[0];
				argument = Integer.parseInt(input[1]);
			} catch (Exception e) {
				System.out.println("Wrong input, please enter command followed by number");
				return;
			}

			if (command.equals("rule")) {
				//checks for valid rule
				if(argument > 255 || argument < 0){
					System.out.println("Rules can only range between 0 and 255");
					continue;
				}
				
				String binary = Integer.toBinaryString(argument);
				for(int i = 0; i < binary.length(); i++){
					binaryRule.add(binary.substring(i, i + 1).equals("1"));
				}
				//fills up so binaryRule is 8 digits long
				while(binaryRule.size() < 8){
					binaryRule.add(0, false);
				}
				
				cellularAutomata();
				
			}
			
			else if(command.equals("speed")){
				delay = (int)((1.0 / argument) * 1000);
				valid = false;
			}
			
			else if(command.equals("delay")){
				delay = argument;
				valid = false;
			}
			
			else{
				System.out.println("Command not recognized");
				valid = false;
			}
			
		}while(!valid);//end do while valid

	}//end main

	private static void cellularAutomata() {
		JFrame frame = new JFrame();
		frame.setSize(dimensions, dimensions);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SET FIRST POINT=========================================================
		map[0][dimensions/2 / scale] = true;
		
		Main panel = new Main();
		frame.add(panel);
		int row = 1;
		while(row < 100){
			panel.applyRule(row);
			panel.repaint();
			
			try{
				Thread.sleep(delay);
			}catch(Exception e){
				
			}
			
			row++;
		}
		
		/*
		//prints out everything on console
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		*/
	}

	//applies the rule: determines if each cell in row is alive or dead
	private void applyRule(int row) {
		for(int i = 1; i < map.length - 2; i++){
			String config = "";
			for(int j = i - 1; j <= i + 1; j++){
				if(map[row - 1][j]){
					config += "1";
				}else{
					config += "0";
				}
			}
			
			map[row][i] = tryConfig(config);
			
		}
		
	}
	
	private boolean tryConfig(String config){
		int rule = Integer.parseInt(config, 2);
		try{
			return (binaryRule.get(binaryRule.size() - 1 - rule));
		}catch(Exception e){
			System.out.println("Rule = " + rule);
			return false;
		}

	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		for(int row = 0; row < map.length; row++){
			for(int col = 0; col < map[row].length; col++){
				if(map[col][row]){
					g.fillRect(row * scale, col * scale, scale, scale);
				}
			}
		}
	}
	
}//end class
