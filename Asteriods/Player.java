package Asteriods;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import Game_Engine.Game_Object;
import Game_Engine.Game_Text;
import Game_Engine.JT_Point;

public class Player extends Game_Engine.Player{
	
	public static String Lives = "Pictures/Ship.png";
	
	
	public Player(String nm, Integer sc, boolean ip, boolean hl, boolean hw,
			HashMap<String, ArrayList<Game_Object>> obj, HashMap<String, ArrayList<Game_Text>> game_text) {
		
		super(nm, sc, ip, hl, hw, obj, game_text);
				
		this.setObjects("Lives", new ArrayList<Game_Object>());
		
		this.makeLives(this.get_object("Lives"));
		
		
	}
	
	/**
	 * 
	 * @param lst List for Game Objects
	 * This method creates the representation of lives at the top left corner of the screen
	 */
	private void makeLives(ArrayList<Game_Object> lst) {
		int x = 5;
		for(int i = 0; i<3; i++) {
			lst.add(new Game_Object("Life", new JT_Point(x,10), 0,0, Lives, null, Color.black,700));
			x= x+ 40;
		}
	}
	
}
