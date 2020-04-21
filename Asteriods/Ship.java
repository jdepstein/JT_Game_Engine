package Asteriods;
import java.awt.Color;
import Game_Engine.Game_Object;
import Game_Engine.Player;
import Game_Engine.JT_Point;


public class Ship extends Game_Object{
	
	/**
	 * 
	 * @param name
	 * @param loc
	 * @param dx
	 * @param dy
	 * @param img_path
	 * @param poly
	 * @param color
	 * @param Win_size
	 * @param Health
	 * 
	 * 
	 */
	
	Player player;
	
	private static String ship_img= "Pictures/Shippy.jpg";
	
	public Ship(String name, JT_Point loc, Color color,int Win_size, Player player) {
		
		super(name, loc, 0, 0, get_img(), null, color, Win_size);
	
		this.player = player;
	}
	
	/**
	 * 
	 * @return The Player the has this ship as one of its Game Objects
	 */
	public Player get_player() {
		return this.player;
	}
	
	/**
	 * 
	 * @return The string path to the image 
	 */
	private static String get_img() {
		return Ship.ship_img;
	}
	
	
	

}
