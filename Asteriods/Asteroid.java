package Asteriods;
import java.awt.Color;
import Game_Engine.Game_Object;
import Game_Engine.JT_Point;


public class Asteroid extends Game_Object{
	
	int health;
	int value;

	/**
	 * Constructor for Asteroid in the beginning of the Game
	 * @param name
	 * @param shipX
	 * @param shipY
	 * @param windowSize
	 * @param value
	 */
	public Asteroid(String name, double shipX, double shipY, int windowSize, int value) {
		super(name, generateX_Y(windowSize, shipX, shipY), generateDX(), generateDY(), get_img(name), null,Color.black, windowSize);
		
		this.set_health(3);
		
		this.health=this.get_health();
		
		this.value = value;
		
		
	}
	
	
	/**
	 * Constructor for asteroid that knows what its X, Y DX and DY are.
	 * @param name
	 * @param point
	 * @param dx
	 * @param dy
	 * @param windowSize
	 * @param value
	 */
	public Asteroid(String name, JT_Point point, double dx , double dy,int windowSize, int value) {
		super(name, point, dx, dy, get_img(name), null,Color.black, windowSize);
		
		this.set_health(3);
		
		this.health=this.get_health();
		
		this.value = value;	
	}
	
	/**
	 * 
	 * @param windowSize
	 * @param shipX
	 * @param shipY
	 * @return A X and Y point for a asteroid
	 */
	
	private static JT_Point generateX_Y(int windowSize, double shipX, double shipY) {
		int RX = (int) (Math.random() * (windowSize - 40) + 1);
		
		int RY = (int) (Math.random() * (windowSize - 40) + 1);
		
		while (RX < 100 + shipX && RX < shipX+ 100 && RY < 100 + shipY && RY < shipY+ 100) {
			
			RX = (int) (Math.random() * (windowSize - 40) + 1);
			
			RY = (int) (Math.random() * (windowSize - 40) + 1);
		}
		
		return new JT_Point(RX,RY);
		
	}
	
	/**
	 * 
	 * @return The correct DX for the asteroid
	 */
	
	private static double generateDX() {
		int min = -2;
		
		int max = 2;
		
		double dx = Math.round((Math.random() * (max - min) + min) * 100.0) / 100.0;
		
		return dx;
	}
	
	
	/**
	 * 
	 * @return The correct DY for the asteroid
	 */
	private static double generateDY() {
		int min = -2;
		
		int max = 2;
		
		double dy = Math.round((Math.random() * (max - min) + min) * 100.0) / 100.0;
		
		return dy;
	}
	
	
	/**
	 * 
	 * @param name
	 * @return The correct image path according to the name of the Asteroid
	 */
	private static String get_img(String name) {
		if(name=="Asto") {
			return "Pictures/ast.jpg";
		}
		
		if(name=="Asto_2") {
			return "Pictures/ast-2.jpg";
		}
		
		if(name=="Asto_3") {
			return "Pictures/ast-3.jpg";
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return The value  of the asteroid
	 */
	public int get_Value() {
		
		return this.value;
	}

}
