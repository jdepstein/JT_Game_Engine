package Catan;

import java.awt.Color;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import Game_Engine.Game_Object;
import Game_Engine.Game_Text;
import Game_Engine.Player;
import Game_Engine.Scene;
import Game_Engine.JT_Point;

public class Catan_Scene extends Scene {
	
	private Dice one;
	private Dice two;
	private Integer roll;
	private boolean placed_set;
	private boolean placed_road;
	private boolean has_rolled;
	private String current_player;
	private boolean can_hit;
	private String Build_button;
	

	public Catan_Scene(HashMap<String, ArrayList<Game_Object>> objects, HashMap<String, Player> players, HashMap<String,ArrayList<Game_Text>> text,
			 int Window_Size, String backround) {
		super(objects, players, text, Window_Size, backround);
		
		
		this.placed_road = false;
		this.placed_set = false;
		this.has_rolled = false;
		this.current_player = "Red";
		this.can_hit = false;
		
		Player x = this.get_player("Red");
		Player y  = this.get_player("Blue");
		Player z  = this.get_player("Orange");
		Player w =  this.get_player("Black");
		
		x.setObjects("Placed_Red", new ArrayList<Game_Object> ());
		y.setObjects("Placed_Blue", new ArrayList<Game_Object> ());
		z.setObjects("Placed_Orange", new ArrayList<Game_Object> ());
		w.setObjects("Placed_Black", new ArrayList<Game_Object> ());
		
		this.update_player("Red", x);
		this.update_player("Blue", y);
		this.update_player("Orange", z);
		this.update_player("Black", w);
		
	
		one = new Dice();
		two = new Dice();
		roll = one.get_roll() + two.get_roll();
		ArrayList<Game_Text> rolls = new ArrayList<Game_Text> ();
		rolls.add(new Game_Text(roll.toString(), 530,150 , Color.black, false, "Serif", 20));
		this.add_txt("Roll", rolls);
	
		ArrayList<Game_Text> winner = new ArrayList<Game_Text> ();
		rolls.add(new Game_Text("", 530,150 , Color.black, false, "Serif", 20));
		this.add_txt("Winner", winner);}
	
	
	// gets the resource values for a placed settlement or city
	private Catan_Object get_values(Catan_Object obj) {
		ArrayList<Game_Object> tiles =this.objects.get("tiles");
		for (Game_Object ob: tiles) {
			Catan_Object obs = (Catan_Object) ob;
			if (obj.isOverlapping(obs) == true){
				obj.setValue(obs.getVictory_pts().toString() + " " + obs.getValue() + " " + obj.getValue());}}
		return obj;}
	
	// Builds a road object
	private Polygon Road_Build(int x, int y, String type) {
		if (type.equals("Right")) {
			int [] xs = {x - 23, x - 18, x + 23, x + 18};
			int [] ys =  {y + 11, y + 17, y - 11, y - 17};
			return new Polygon (xs, ys, 4);}
		
		else if (type.equals("Left")) {
			int [] xs = {x + 18, x + 23, x - 18, x - 23 };
			int [] ys =  {y + 17, y + 11, y - 17, y - 11};
			return new Polygon (xs, ys, 4);}
		
		else {
			int [] xs = {x - 5, x + 5, x + 5, x - 5 };
			int [] ys =  {y + 23, y + 23, y - 23, y - 23};
			return new Polygon (xs, ys, 4);}}
	
	
	private Polygon get_road(int x, int y) {
		ArrayList<Game_Object> tiles =this.objects.get("tiles");
		String rd_type = "x";
		for (Game_Object ob: tiles) {
			Catan_Object obs = (Catan_Object) ob;
			if (obs.has_clicked(x, y)) {
				rd_type = obs.road_type(x, y);}}
		return this.Road_Build(x, y, rd_type);}
	
	
	// gives out resources on a roll
	private void allocate_res(String name){
		String get_val = "Placed"+"_"+ name;
			Catan_player player = (Catan_player) this.get_player(name);
			ArrayList<Game_Object> objects =  player.getObjects().get(get_val);
			
			if (objects != null){
			for (Game_Object ob:  objects) {
				Catan_Object obj = (Catan_Object) ob;
				String[] values = obj.getValue().split(" ");
				for (int x = 0; x < values.length;x++) {
					String val = values[x];
					if (val.equals(this.roll.toString()) == true){
						player.got_resource(values[x + 1], obj);}}}
			this.update_player(name, player);}}
	
	// Places an object 
	private void place(Color color, String item, int x, int y, String name) {
		String get_val = "Placed"+"_"+ name;
		Catan_player play = (Catan_player) this.get_player(name);
		
		if (item == "Rd" ) {
			Polygon poly = this.get_road(x, y);
			Catan_Object Rd = new Catan_Object(name, new JT_Point(x, y), 0, 0, null, poly, color, 0, 01, "Road" );
			if (this.getlvl() > 1) {
				play.build_rd();}
			
			play.addObject(get_val, Rd);
			this.placed_road = true;
			this.update_player(name, play);
			this.Build_button = "";}
		
		else if (item == "Set") {
				int [] xs = {x-13, x + 13, x};
				int [] ys = {y, y, y-20};
				Polygon poly = new Polygon(xs, ys,3);
				Catan_Object Settlement = new Catan_Object(name, new JT_Point(x, y), 0, 0, null, poly, color, 0, 1, "Settlement" );
				Settlement = this.get_values(Settlement);
				
				if (this.getlvl() == 1) {
					String[] values = Settlement.getValue().split(" ");
					if (values.length == 3) {
						play.got_resource(values[1], Settlement);}
					
					else if (values.length == 5) {
						play.got_resource(values[1], Settlement);
						play.got_resource(values[3], Settlement);;}
									
					else if (values.length == 7) {
						play.got_resource(values[1], Settlement);
						play.got_resource(values[3], Settlement);
						play.got_resource(values[5], Settlement);}}
				
				else if (this.getlvl() > 1) {
					play.build_Settlement();}
				
				if (this.getlvl()< 2) {
					play.start_build();}
					
				play.addObject(get_val, Settlement);
				this.update_player(name, play);
				this.placed_set = true;
				this.Build_button = "";}
	
		else if (item == "Cit") {
			for (int z = 0; z < play.get_object(get_val).size(); z++) {
					Catan_Object settle = (Catan_Object) play.get_object(get_val).get(z);
					String[] values = settle.getValue().split(" ");	
					

					if (settle.has_clicked(x, y) == true && values[values.length -1].equals("Settlement")) {
						;
						play.get_object(get_val).remove(z);
						int [] xs = {x-10, x+10, x+10,x-10};
						int [] ys = {y-10, y-10, y+10,y+10};
						Polygon poly = new Polygon(xs, ys,4);
						Catan_Object City = new Catan_Object(name, new JT_Point(x-10, y-10), 0, 0, null, poly, color, 0, 2, "City" );
						City = this.get_values(City);
						play.addObject(get_val, City);
						play.build_City();
						this.update_player(name, play);
						}}}}
	
	// allocates to all players
	private void allocate_all(){
		this.allocate_res("Red");
		this.allocate_res("Blue");
		this.allocate_res("Orange");
		this.allocate_res("Black");}
	
	// updated player text
	private void update_texts() {
		if (current_player.equals("End") == false) {
		Catan_player current = (Catan_player) this.get_player(current_player);
		current.not_playing();
		current.playing();}}
	
	// rolls the dice
	private void Roll() {
		ArrayList<Game_Text> rolls = new ArrayList<Game_Text>();
		one.roll();
		two.roll();
		roll = one.get_roll() + two.get_roll();
		rolls.add(new Game_Text(roll.toString(), 530,150 , Color.black, false, "Serif", 20));
		this.replace_txt("Roll", rolls);
		this.allocate_all();
		this.has_rolled = true;
		this.can_hit = true;}
	
	// if a player has won
	private void end(String winner) {
		ArrayList<Game_Text> winners = new ArrayList<Game_Text>();
		winners.add(new Game_Text( winner +" HAS WON THE GAME", 450,300,Color.black, false, "", 80));
		this.replace_txt("Winner", winners);
		this.placed_road = true;
		this.placed_set = true;
		this.has_rolled = true;
		this.Build_button = "END";
		this.can_hit = false;
		this.current_player ="END";}
			
	@Override
	public void update() {
		if (this.get_player("Red").Has_won() == true){
			this.end("Red");
					
		}
		else if (this.get_player("Blue").Has_won()== true){
			this.end("Blue");
			
		}
		else if (this.get_player("Orange").Has_won()== true){
			this.end("Orange");
			
		}
		else if (this.get_player("Black").Has_won()== true){
			this.end("Black");
		}
		else {this.update_texts();}}
		
	// all that happens when a mouse is pressed	
	@Override
	public void mouse_pressed(int x, int y) {
		ArrayList<Game_Object> buttons = this.get_object("buttons");
		Catan_player current = (Catan_player) this.get_player(current_player);
		for (Game_Object button: buttons) {		
			if (button.has_clicked(x, y) == true && button.getName().equals("Roll") == true && this.has_rolled == false){
				if (this.getlvl() > 1) {
					this.Roll();}}
		
		
			else if (button.has_clicked(x, y) == true && button.getName().equals("Button_End") && can_hit != false) {
					current.not_playing();
					
					if (this.current_player.equals("Red")) {
						Catan_player player2 =  (Catan_player) this.get_player("Blue");
						player2.playing();
						this.current_player = "Blue";
						this.update_player("Red", current);
						this.update_player("Blue", player2);}
					
					else if (this.current_player.equals("Blue")) {
						Catan_player player2 =  (Catan_player) this.get_player("Orange");
						player2.playing();
						this.current_player = "Orange";
						this.update_player("Blue", current);
						this.update_player("Orange", player2);}
					
					else if (this.current_player.equals("Orange")) {
						Catan_player player2 =  (Catan_player) this.get_player("Black");
						player2.playing();
						this.current_player = "Black";
						this.update_player("Orange", current);
						this.update_player("Black", player2);}
					
					else if (this.current_player.equals("Black")) {
						Catan_player player2 =  (Catan_player) this.get_player("Red");
						player2.playing();
						this.current_player = "Red";
						this.update_player("Black", current);
						this.update_player("Red", player2);
						this.update_level(this.getlvl() + 1);}
								
					
					this.placed_road = false;
					this.placed_set = false;
					this.roll = 0;
					this.has_rolled = false;
					this.can_hit = false;
					this.Build_button = "";}
		
		
		else if (button.has_clicked(x, y) == true && button.getName().equals("Button_Set") && can_hit != false && current.can_Set() == true && this.getlvl() > 1) {
			this.Build_button = "Set";}
			
		else if (button.has_clicked(x, y) == true && button.getName().equals("Button_City") && can_hit != false && current.can_cit() == true && this.getlvl() > 1) {
			this.Build_button = "Cit";
		}
		else if (button.has_clicked(x, y) == true && button.getName().equals("Button_Road") && can_hit != false && current.can_rd() == true && this.getlvl() > 1) {
			this.Build_button = "Rd";
		}
	
		
		
		}
		
			ArrayList<Game_Object> place_ment = this.get_object("Board");
			if (place_ment.get(0).has_clicked(x, y)) {
				if (this.getlvl() <2) {
					
					if (this.get_player("Red").is_playing() == true && this.placed_set == false) {
						this.place(Color.red, "Set", x, y, "Red");
						}
					else if (this.get_player("Blue").is_playing() == true && this.placed_set == false) {
						this.place(Color.blue, "Set", x, y, "Blue");

						} 
					else if (this.get_player("Orange").is_playing() == true && this.placed_set == false) {
						this.place(new Color(255,137,0), "Set", x, y, "Orange");
						} 
					else if (this.get_player("Black").is_playing() == true && this.placed_set == false) {
						this.place(Color.black, "Set", x, y, "Black");}
						
					else if (this.get_player("Red").is_playing() == true && this.placed_set == true
							&& this.placed_road == false) {
						this.place(Color.red, "Rd", x, y, "Red");
						this.can_hit = true;}
					
					else if (this.get_player("Blue").is_playing() == true && this.placed_set == true
							&& this.placed_road == false) {
						this.place(Color.blue, "Rd", x, y, "Blue");
						this.can_hit = true;} 
					
					else if (this.get_player("Orange").is_playing() == true && this.placed_set == true
							&& this.placed_road == false) {
						this.place(new Color(255,137,0), "Rd", x, y, "Orange");
						this.can_hit = true;}
					
					else if (this.get_player("Black").is_playing() == true && this.placed_set == true
							&& this.placed_road == false) {
						this.place(Color.black, "Rd", x, y, "Black");
						this.can_hit = true;}}
						
						
						
					
					
				else if (this.Build_button == "Set") {
					if (this.get_player("Red").is_playing() == true && this.placed_set == false) {
						this.place(Color.red, "Set", x, y, "Red");
						this.Build_button = "";
						}
					else if (this.get_player("Blue").is_playing() == true) {
						this.place(Color.blue, "Set", x, y, "Blue");
						this.Build_button = "";

						} 
					else if (this.get_player("Orange").is_playing() == true ) {
						this.place(new Color(255,137,0), "Set", x, y, "Orange");
						this.Build_button = "";
						
						} 
					else if (this.get_player("Black").is_playing() == true) {
						this.place(Color.black, "Set", x, y, "Black");
						this.Build_button = "";
						}}
				
				else if (this.Build_button == "Cit") {
					if (this.get_player("Red").is_playing() == true) {
						this.place(Color.red, "Cit", x, y, "Red");
						this.Build_button = "";
						}
					else if (this.get_player("Blue").is_playing() == true) {
						this.place(Color.blue, "Cit", x, y, "Blue");
						this.Build_button = "";

						} 
					else if (this.get_player("Orange").is_playing() == true) {
						this.place(new Color(255,137,0), "Cit", x, y, "Orange");
						this.Build_button = "";
						
						} 
					else if (this.get_player("Black").is_playing() == true) {
						this.place(Color.black, "Cit", x, y, "Black");
						this.Build_button = "";
						}}
				
				else if (this.Build_button == "Rd") {
					if (this.get_player("Red").is_playing() == true) {
						this.place(Color.red, "Rd", x, y, "Red");
						this.Build_button = "";
						}
					else if (this.get_player("Blue").is_playing() == true) {
						this.place(Color.blue, "Rd", x, y, "Blue");
						this.Build_button = "";

						} 
					else if (this.get_player("Orange").is_playing() == true) {
						this.place(new Color(255,137,0), "Rd", x, y, "Orange");
						this.Build_button = "";
						
						} 
					else if (this.get_player("Black").is_playing() == true) {
						this.place(Color.black, "Rd", x, y, "Black");
						this.Build_button = "";
						}}
				}}}

	

