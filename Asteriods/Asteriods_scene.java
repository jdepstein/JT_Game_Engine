package Asteriods;
import Game_Engine.Game_Object;
import Game_Engine.JT_Point;
import Game_Engine.Game_Text;
import Asteriods.Player;
import Game_Engine.Scene;
import Game_Engine.Game_Panel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class Asteriods_scene extends Scene {

	public Asteriods_scene(HashMap<String, ArrayList<Game_Object>> objects, HashMap<String, Game_Engine.Player> players,
			HashMap<String, ArrayList<Game_Text>> text, int Window_Size, String backround) {
		super(objects, players, text, Window_Size, backround);

	}


	/**
	 * Game Objects Game list for Bullets and Asteroids
	 */	

	Asteriods_scene sc;
	Asteriods.Player Player;

	
	@Override
	public void create_S() {
		
		Player = new Player("Trey", 0, true, false, false, new HashMap<String,ArrayList<Game_Object>>(),new HashMap<String,ArrayList<Game_Text>>());


		/**
		 * Array List for asteroid list and shot list
		 */
		
		Player.setObjects("Ship", new ArrayList<Game_Object>());
		
		super.add_new_player("Trey", Player);
		
		super.add_new_object("AList",  new ArrayList<Game_Object>());
		
		super.add_new_object("BList",new ArrayList<Game_Object>());
		

		/**
		 * Ship Object for the user to be in control of
		 */
		Player.get_object("Ship").add(new Ship("Ship", new JT_Point(super.getWinSize() / 2,super.getWinSize() / 2), 
			 Color.black, 700, Player));
		
		
		/**    
		 * For loop to create 5 Asteroids on the screen
		 */
		for (int i = 0; i < 5; i++) {
			super.get_object("AList").add(new Asteroid("Asto",Player.get_object("Ship").get(0).getLocation().x, Player.get_object("Ship").get(0).getLocation().y,super.getWinSize(), 50));
		}
	
		super.add_txt("Label", new ArrayList<Game_Text>());
		
		super.add_txt("Score", new ArrayList<Game_Text>());
		
		super.getText("Label").add(new Game_Text("Score :", 575, 15, Color.white, true, "Serif", 20));
		
		super.getText("Score").add(new Game_Text(Integer.toString(Player.getScore()), 650, 15, Color.white, true, "Serif", 20));
	}
	
	
	
	@Override
	public void key_type(char key) {
		
		
		  // TODO Auto-generated method stub 
		switch (key) {		 
		 /**
			 * move the ship forward aka in the direction of the tip of the ship
			 */
		
		  case 'w': // this is the forwards keycode and set true so it moves continuously while // being hit
			  Game_Panel.keyCode = 1; Game_Panel.keyHeld =true; break;
		  
		 /**
			 * Rotates the ship Left key code and set true for same reasons as above
			 */
		
		  case 'a': Game_Panel.keyCode = 2; Game_Panel.keyHeld = true; break;
		  
		 /**
			 * rotates the ship right " "
			 */
				  case 'd': Game_Panel.keyCode = 3; Game_Panel.keyHeld = true; break; }
				 

	}


	@Override
	public void key_relase(char key) {
		
		Game_Panel.keyHeld=false;
		
		Ship me = (Ship)Player.get_object("Ship").get(0);
		
		if (key == ' ') {
		
			super.get_object("BList").add(new Bullet("Bullet", me, null,Color.black,700));
		
		}
	}

	public Graphics S_Paint(Graphics page) {
		return null;
	}
	

	public void update() {
		
		/**
		 * Update Score
		 */
		super.getText("Score").remove(0);
		
		super.getText("Score").add(new Game_Text(Integer.toString(Player.getScore()), 650, 15, Color.white, true, "Serif", 20));
		
		
		
		
		/**
		 * Rotates the Object on the render pad to the right
		 */
		if (Game_Panel.keyHeld == true && Game_Panel.keyCode == 3) {
			Player.get_object("Ship").get(0).increaseRotationAngle();
		}

		/**
		 * Rotates the Object on the render pad to the left
		 */
		else if (Game_Panel.keyHeld == true && Game_Panel.keyCode == 2) {
			Player.get_object("Ship").get(0).decreaseRotationAngle();
		}

		/**
		 * Moves the Game Object
		 */
		else if (Game_Panel.keyHeld == true && Game_Panel.keyCode == 1) {
			Player.get_object("Ship").get(0).move();
		}
		
		
		
		/**
		 * Iteration that ensure asteroid game objects stay on the screen
		 */
		Iterator<Game_Object> iter = super.get_object("AList").iterator();
		
		while (iter.hasNext()) {
			
			Game_Object AST = iter.next();
			
			if (AST.getLocation().x < 0 || (AST.getLocation().x + AST.getWidth()) > super.getWinSize()) {
				AST.setDx(-(AST.getDx()));
			}
			
			if (AST.getLocation().y < 0 || (AST.getLocation().y + AST.getHeight()) > super.getWinSize()) {
				AST.setDy(-(AST.getDy()));
			}
		}

		/**
		 * Iteration that Checks to see if the ship Game Object has been hit
		 */
		for (Game_Object asteroid : super.get_object("AList")) {
			if (asteroid.collision_1(asteroid, Player.get_object("Ship").get(0))) {
				
				Player.get_object("Ship").get(0).setXPos(super.getWinSize() / 2);
				
				Player.get_object("Ship").get(0).setYPos(super.getWinSize() / 2);
				
				asteroid.setDx(-(asteroid.getDx()));
				
				asteroid.setDy(-(asteroid.getDy()));
				
				int loc =Player.get_object("Lives").size();
				
				Player.get_object("Lives").remove(loc-1);
				
				Player.get_object("Ship").get(0).decrease_health();
				
				if (Player.get_object("Lives").size() == 0){
					super.add_txt("GameOver", new ArrayList<Game_Text>());
					
					super.getText("GameOver").add(new Game_Text("Game Over", super.getWinSize()/2, super.getWinSize()/2, Color.white, true, "Serif", 13));
				}
			}
		}

		/**
		 * Check that looks to see what asteroid game_Objects have been shot
		 */
		if(super.get_object("BList").size()>0) {
		
			Asteroid killed = (Asteroid) Player.get_object("Ship").get(0).collision_4(super.get_object("AList"),super.get_object("BList"));
		
			if (killed != null) {
			
				double x = killed.getLocation().x;
			
				double y = killed.getLocation().y;
			
				double dx = killed.getDx();
			
				double dy = killed.getDy();
			
				String name = killed.getName();
			
			
				super.get_object("AList").remove(killed);
			
			
				
				if (name.equals("Asto")) {	
				
					super.get_object("AList").add(new Asteroid("Asto_3", new JT_Point((int)x, (int)y), dx,-dy,super.getWinSize(),75));
				
					super.get_object("AList").add(new Asteroid("Asto_3", new JT_Point((int)x+50, (int)y), -dx,dy,super.getWinSize(),75));	
			
				}
			
			
				if(name.equals("Asto_3")) {
				
					super.get_object("AList").add(new Asteroid("Asto_2", new JT_Point((int)x, (int)y), dx,-dy,super.getWinSize(),100));
				
					super.get_object("AList").add(new Asteroid("Asto_2", new JT_Point((int)x+25, (int)y), -dx,dy, super.getWinSize(),100));
			
				}
			
				Player.addScore(killed.get_Value());
			
		
			}
		}

		/**
		 * Iteration that checks to see if asteroid game objects collide
		 */
		
		for (Game_Object asteroid : super.get_object("AList")) {
			
			asteroid.collision_2(super.get_object("AList"));
			
			asteroid.Auto_move();
		}

		/**
		 * Iteration that moves bullets
		 */
		for (Game_Object Bullet : super.get_object("BList")) {
			
			Bullet.Auto_move();
	
		}
		
	}



	
	

}
