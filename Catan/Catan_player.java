package Catan;
import Game_Engine.Player;
import Game_Engine.Game_Object;
import Game_Engine.Game_Text;
import Game_Engine.JT_Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

;

public class Catan_player extends Player{
	 
	// The has_hmap of game_objects from PLayer will be everything on the board for each player
	
	public Integer Brick_c;
	private Integer Wood_c;
	private Integer Ore_c;
	private Integer Wool_c;
	private Integer Wheat_c;
					  
	private int set_count;
	private int cit_count;
	private int rd_count;
	
	
	/**
	 * used to help display resource counts for each player
	 */
	private ArrayList<Game_Text> add_text(){
		ArrayList<Game_Text> display = new  ArrayList<Game_Text>();
		display.add(new Game_Text(this.getName()+ "'s Turn", 50, 50, Color.black, true, "Serif",20));
		display.add(new Game_Text(this.getScore().toString(), 160, 102, Color.black, true, "Serif",20));
		display.add(new Game_Text(Brick_c.toString(), 125, 665, Color.black, true, "Serif",20));
		display.add(new Game_Text(Wood_c.toString(), 200, 665, Color.black, true, "Serif",20));
		display.add(new Game_Text(Ore_c.toString(), 445, 665, Color.black, true, "Serif",20));
		display.add(new Game_Text(Wool_c.toString(), 365, 665, Color.black, true, "Serif",20));
		display.add(new Game_Text(Wheat_c.toString(), 280,665, Color.black, true, "Serif",20));
		return display;}
	
	/**
	 * used to help display build cards for each player
	 */
	private ArrayList<Game_Object> build(){
		ArrayList<Game_Object> display = new  ArrayList<Game_Object>();
		if (this.getName().equals("Red")) {
			display.add(new Game_Object("Builds", new JT_Point(800,100), 0, 0, "Pictures/red_road.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(810,170), 0, 0, "Pictures/red_settlement.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(810,290), 0, 0, "Pictures/red_city.png", null, null, 0));
		}
		else if (this.getName().equals("Blue")) {
			display.add(new Game_Object("Builds", new JT_Point(780,90), 0, 0, "Pictures/blue_road.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(810,170), 0, 0, "Pictures/Blue_settlement.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(810,290), 0, 0, "Pictures/blue_city.png", null, null, 0));
			}
		else if (this.getName().equals("Orange")) {
			display.add(new Game_Object("Builds", new JT_Point(804,87), 0, 0, "Pictures/orange_road.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(814,170), 0, 0, "Pictures/orange_settlement.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(780,290), 0, 0, "Pictures/orange_city.png", null, null, 0));
		}
		else if (this.getName().equals("Black")) {
			display.add(new Game_Object("Builds", new JT_Point(790,76), 0, 0, "Pictures/Back_road.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(807,170), 0, 0, "Pictures/black_settlement.png", null, null, 0));
			display.add(new Game_Object("Builds", new JT_Point(793,300), 0, 0, "Pictures/Black_city.png", null, null, 0));}
		
		return display;}
	
	

	public Catan_player(String nm, Integer sc, boolean ip, boolean hl, boolean hw, HashMap<String, ArrayList<Game_Object>> obj,HashMap<String, ArrayList<Game_Text>> game_text, Color color) {
		super(nm, sc, ip, hl, hw, obj, game_text);
		

		this.Brick_c = 0;
		this.Wood_c = 0;
		this.Ore_c = 0;
		this.Wool_c = 0;
		this.Wheat_c = 0;
		this.cit_count = 0;
		this.set_count = 0;
		this.rd_count = 0;

		
		
		if (this.getName().equals("Red")) 
		{
			ArrayList<Game_Text> dis = this.add_text();
			ArrayList<Game_Object> bld = this.build();
			
			this.setGame_text("Turn_stuff", dis);
			this.setObjects("Turn_Stuff", bld);}}
		
		// sends text updated & object to what to the players build card and resource counts are	
		@Override
		public void playing() {
			this.is_playing = true;
			ArrayList<Game_Object> bld = this.build();
			this.setObjects("Turn_Stuff",bld);
			ArrayList<Game_Text> dis = this.add_text();
			this.setGame_text("Turn_stuff", dis);
		
			}
		
		// gets rid text the players build card and resource counts are
		@Override
		public void not_playing() {
			this.is_playing = false;
			this.removetxt("Turn_stuff");
			this.removeobj("Turn_Stuff");}
		
		/**
		 * @param name String: The resource that was gained
		 * @param obj Catan_Object:  The object it was gained from
		 */
		public void got_resource(String name, Catan_Object obj) {
			if (name.equals("Brick")) {

				this.Brick_c += 1 * obj.getVictory_pts();
			}
			else if (name.equals("Wood")) {
				this.Wood_c += 1 * obj.getVictory_pts();
			}
			else if (name.equals("Wool")) {
				this.Wool_c += 1 * obj.getVictory_pts();
			}
			else if (name.equals("Wheat")) {
				this.Wheat_c += 1 * obj.getVictory_pts();
			} 
			else if (name.equals("Ore")) {
				this.Ore_c += 1 * obj.getVictory_pts();
			}}
		
		// The different build aplayer can make and the resource cost
		public void build_Settlement() {
				this.set_count += 1;
				this.Brick_c -= 1;
				this.Wheat_c -= 1;
				this.Wool_c -= 1;
				this.Wood_c -= 1;
				this.addScore(1);}
		
		public void build_City() {
				this.set_count -= 1;
				this.cit_count += 1;
				this.Wheat_c -= 1;
				this.Wheat_c -= 1;
				this.Ore_c -= 1;
				this.Ore_c -= 1;
				this.Ore_c -= 1;
				this.addScore(1);}
		
		public void build_rd() {
				this.rd_count += 1;
				this.Brick_c -= 1;
				this.Wood_c -= 1;}
		
		// Checks to see if a player is capable of build a certain item
		public boolean can_Set() {
			if (this.Brick_c > 0 && this.Wheat_c > 0 && this.Wool_c > 0 && this.Wood_c > 0 && set_count < 5) {
				return true;}
			else {return false;}}
		
		public boolean can_cit() {
			if (this.Wheat_c > 1 &&  this.Ore_c > 2 && this.set_count > 0 && cit_count < 4) {
				return true;}
			else {return false;}}
		
		public boolean can_rd() {
	
			if (this.Brick_c > 0 && this.Wood_c > 0 && this.rd_count < 15) {

				return true;}
			else {return false;}}
		
		// Used for the build phase of the game
		public void start_build() {
			this.rd_count += 1;
			this.set_count +=1;
			this.addScore(1);}
		
		//Used for checking a winner
		@Override
		public void addScore(int x) {
			this.Score += x;
			if (this.Score >= 10) {
				this.won();}}
	
	}
		
		
	
		
		
	
	
	

	
	
