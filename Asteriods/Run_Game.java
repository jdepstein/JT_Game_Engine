package Asteriods;
import java.util.ArrayList;
import java.util.HashMap;

import Game_Engine.Game_Frame;
import Game_Engine.Game_Object;
import Game_Engine.Player;
import Game_Engine.Game_Text;
	

public class Run_Game {

public static void main(String[] args) {
	
	Asteriods_scene cs = new Asteriods_scene(new HashMap<String, ArrayList<Game_Object>>(), new HashMap<String, Player>(), new HashMap<String,ArrayList<Game_Text>>(), 700, "Pictures/space_BC2.jpg");
	new Game_Frame(700, cs);
		
	}
}
