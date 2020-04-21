package Asteriods;
import Game_Engine.JT_Point;

import java.awt.Color;
import java.awt.Polygon;

import Game_Engine.Game_Object;

public class Bullet extends Game_Object{
	
	static String img_Path = "Pictures/Cshot.jpg";
	

	public Bullet(String name, Ship ship, Polygon poly, Color color,
			int Win_size) {
		super(name, getX_Y(ship), generateDX(ship), generateDY(ship), get_img(), poly, color, Win_size);
		
	}	
	
	/**
	 * 
	 * @param ship
	 * @return The JT point for the bullet
	 */
	private static JT_Point getX_Y(Ship ship) {
		return new JT_Point(ship.getLocation().x+((int)ship.getWidth()/2), ship.getLocation().y+((int)ship.getHeight()/2));
		
	}
	
	
	/**
	 * 
	 * @return The path to the image
	 */
	private static String get_img() {
		return img_Path;
	}
	
	/**
	 * 
	 * @param ship
	 * @return The correct DX according to the ships rotation angle
	 */
	
	private static double generateDX(Ship ship) {
		return 15 * Math.cos(Math.toRadians(ship.getRotationAngle() - 90));	
	}
	
	
	/**
	 * 
	 * @param ship
	 * @return the correct DY according to the ships rotation angle
	 */
	private static double generateDY(Ship ship) {
		return 15 * Math.sin(Math.toRadians(ship.getRotationAngle() - 90));
	}
	

	
	
}
