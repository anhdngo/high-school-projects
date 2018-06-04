package gui;

import java.awt.Color;
import java.awt.Rectangle;

public class SlowBar extends Rectangle{
	public static Color color = Color.decode("#89C4F4");
	public static int max = 100;
	public static int current = 100;
	public static int maxHeight = 200;
	
	public static int getBarHeight(){
		return (int)(maxHeight);
	}
	public static int getCurrentBarHeight(){
		return (int)(current * maxHeight / max);
	}
	
	public static void reset(){
		current = max;
	}
}
