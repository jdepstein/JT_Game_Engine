package Catan;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;

import Game_Engine.Game_Object;
import Game_Engine.Game_Text;
import Game_Engine.Player;
import Game_Engine.JT_Point;

/**
 * This just creates all the text objects and players that will stay the same throughout out the course of the game
 */
public class Catan_Game {

	
	private ArrayList<Game_Object> tiles;
	private ArrayList<Game_Object> resrouce_card;
	private ArrayList<Game_Object> Costs;
	private ArrayList<Game_Object> whats_what;
	private ArrayList<Game_Object> buttons;
	private ArrayList<Game_Text> text;
	private ArrayList<Game_Object> Board;	
	private ArrayList<Player> players;
	private String backround;
	
	
	private Catan_Object Maketile(String value, int loc_x,int loc_y, int pts, Color color) {
		Polygon tile = this.creat_hex(loc_x, loc_y);
		return new Catan_Object("Tile", new JT_Point(loc_x, loc_y), 0, 0, null, tile,color,0, pts, value);}
	
	
	
	private Polygon creat_hex(int x, int y) {
			Polygon h = new Polygon();	
			for (int i = 0; i < 6; i++){
				h.addPoint((int) (x + 47 * Math.sin(i * 2 * Math.PI / 6)),
						  (int) (y + 47 * Math.cos(i * 2 * Math.PI / 6)));
			}
			return h;}
	
	private Polygon button(int x, int y, int w, int h) {
		int[] xs = {x, x + w, x + w , x};
		int[] ys = {y, y, y+ h, y+ h};
		
		Polygon t = new Polygon(xs,ys,4);

		return t;
	}
	
	
	
	public Catan_Game() {
		this.tiles = new ArrayList<Game_Object>();
		this.resrouce_card = new ArrayList<Game_Object>();
		this.Costs = new ArrayList<Game_Object>();
		this.whats_what = new ArrayList<Game_Object>();
		this.buttons = new ArrayList<Game_Object>();
		this.text = new ArrayList<Game_Text>();
		this.players = new ArrayList<Player>();
		this.Board = new ArrayList<Game_Object>();

		Color br =  new Color(105,39,39);
		Color or = new Color(138,138,138);
		Color wh = new Color(235,213,22);
		Color wo = new Color(160,255,155);
		Color wod = new Color(15,60,12);
		this.backround = "Pictures/catan.png";	
		
		tiles.add(Maketile("Brick",250,200,9,br));
		tiles.add(Maketile("Brick", 452,412,10,br));
		tiles.add(Maketile("Brick", 370,270,4,br));
		tiles.add(Maketile("Ore", 331,200,12,or));
		tiles.add(Maketile("Ore", 210,411,5,or));
		tiles.add(Maketile("Ore", 411,341,3,or));	
		tiles.add(Maketile("Wood", 412,200,8,wod));
		tiles.add(Maketile("Wood", 331,482,11,wod));
		tiles.add(Maketile("Wood", 250,482,9,wod));
		tiles.add(Maketile("Wood", 250,341,10,wod));	
		tiles.add(Maketile("Wheat", 452,271,2,wh));
		tiles.add(Maketile("Wheat", 170,339,8,wh));
		tiles.add(Maketile("Wheat", 290,270,6,wh));
		tiles.add(Maketile("Wheat", 290,412,3,wh));
		
		tiles.add(Maketile("Wool", 493,342,9,wo));
		
		tiles.add(Maketile("Wool", 412,482,6,wo));
		
		tiles.add(Maketile("Wool", 210,271,11,wo));
		
		tiles.add(Maketile("Wool", 370,412,4,wo));

		whats_what.add(new Catan_Object("Brick", new JT_Point(600, 10), 0, 0, "Pictures/brick.png", null, null,0, 0, "View"));
		whats_what.add(new  Catan_Object("Brick", new JT_Point(750,60), 0,0, "Pictures/brick_card.png",null,null,0,0, "view"));	
		whats_what.add(new Catan_Object("Ore", new JT_Point(542,160), 0, 0, "Pictures/Ore.png", null, null, 0,0, "View"));
		whats_what.add(new  Catan_Object("Ore", new JT_Point(750,250), 0,0, "Pictures/Stone_card.png",null,null,0,0, "view"));	
		whats_what.add(new Catan_Object("Wheat", new JT_Point(608,120), 0, 0, "Pictures/Wheat.png", null, null, 0,0, "View"));
		whats_what.add(new  Catan_Object("Wheat", new JT_Point(750,160), 0,0, "Pictures/Wheat_card.png",null,null,0,0, "view"));	
		whats_what.add(new Catan_Object("Wood", new JT_Point(580,300), 0, 0, "Pictures/Wood.png", null, null,0, 0, "View"));
		whats_what.add(new  Catan_Object("Wood", new JT_Point(750,350), 0,0, "Pictures/Wood_card.png",null,null,0,0, "view"));		
		whats_what.add(new Catan_Object("Wool", new JT_Point(548, 375), 0, 0, "Pictures/Wool.png", null, null,0, 0, "View"));
		whats_what.add(new  Catan_Object("Wool", new JT_Point(750,450), 0,0, "Pictures/Wool_Card.png",null,null,0,0, "view"));	
		
		buttons.add(new Game_Object("Button_City", new JT_Point(500,600), 0, 0, null, this.button(500, 600, 100, 50), Color.black,0));		
		buttons.add(new Game_Object("Button_Set", new JT_Point(610,600), 0, 0, null, this.button(610, 600, 160, 50), Color.black,0));
		buttons.add(new Game_Object("Button_Road", new JT_Point(780,600), 0, 0, null, this.button(780, 600, 110, 50), Color.black,0));
		buttons.add(new Game_Object("Button_Devo", new JT_Point(900,600), 0, 0, null, this.button(900, 600, 225, 50), Color.black,0));
		buttons.add(new Game_Object("Button_End", new JT_Point(480,55), 0, 0, null, this.button(480, 55, 110, 40), Color.black,0));
		buttons.add(new Game_Object("Roll", new JT_Point(500, 180),0,0,null, this.button(500, 180, 50, 30), Color.black, 0));
		
		Costs.add(new Game_Object("costs",new  JT_Point(940,80), 0,0,"Pictures/brick_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1010,90), 0,0,"Pictures/Wood_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(890,180), 0,0,"Pictures/brick_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(955,180), 0,0,"Pictures/Wood_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1020,180), 0,0,"Pictures/Wool_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1080,180), 0,0,"Pictures/Wheat_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(890,290), 0,0,"Pictures/Stone_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(953,290), 0,0,"Pictures/Stone_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1016,290), 0,0,"Pictures/Stone_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1079,290), 0,0,"Pictures/Wheat_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1138,290), 0,0,"Pictures/Wheat_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(830,410), 0,0,"Pictures/Devo_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(890,400), 0,0,"Pictures/Stone_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(953,400), 0,0,"Pictures/Stone_card.png", null, null,0));
		Costs.add(new Game_Object("costs",new  JT_Point(1012,400), 0,0,"Pictures/Wool_card.png", null, null,0));
		
		
		resrouce_card.add(new Game_Object("Brick", new JT_Point(100,580),0,0,"Pictures/brick_card.png", null,null,0));
		resrouce_card.add(new Game_Object("Wood", new JT_Point(180,580),0,0,"Pictures/Wood_card.png", null,null,0));
		resrouce_card.add(new Game_Object("Wheat", new JT_Point(260,580),0,0,"Pictures/Wheat_card.png", null,null,0));
		resrouce_card.add(new Game_Object("Wool", new JT_Point(340,580),0,0,"Pictures/Wool_card.png", null,null,0));
		resrouce_card.add(new Game_Object("Ore",new JT_Point(420,580),0,0,"Pictures/Stone_card.png", null,null,0));
		
		text.add(new Game_Text("=", 920,117, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 867,202, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 867,202, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 870,435, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 870,328, Color.black, true, "Serif", 20));
		text.add(new Game_Text("Victory Pts:", 50, 100,Color.black, true, "Serif", 20));
		text.add(new Game_Text("Roll:", 485,150,Color.black, true, "Serif", 20));
		text.add(new Game_Text("9", 245,200,Color.black, true, "Serif", 20));
		text.add(new Game_Text("....", 240,210,Color.black, true, "Serif", 20));
		text.add(new Game_Text("12", 320, 200,Color.black, true, "Serif", 20));
		text.add(new Game_Text(".", 327,210,Color.black, true, "Serif", 20));
		text.add(new Game_Text("8", 404, 200,Color.red, true, "Serif", 20));
		text.add(new Game_Text(".....", 397, 210,Color.red, true, "Serif", 20));
		text.add(new Game_Text("2", 447, 271,Color.black, true, "Serif", 20));
		text.add(new Game_Text(".", 449, 281,Color.black, true, "Serif", 20));
		text.add(new Game_Text("9", 489, 342,Color.black, true, "Serif", 20));
		text.add(new Game_Text("....", 485, 352,Color.black, true, "Serif", 20));
		text.add(new Game_Text("10", 442, 415,Color.black, true, "Serif", 20));
		text.add(new Game_Text("...", 444, 425,Color.black, true, "Serif", 20));
		text.add(new Game_Text("6", 410, 484,Color.red, true, "Serif", 20));
		text.add(new Game_Text(".....", 402, 494,Color.red, true, "Serif", 20));		
		text.add(new Game_Text("11", 320, 484,Color.black, true, "Serif", 20));
		text.add(new Game_Text("..", 324, 494,Color.black, true, "Serif", 20));	
		text.add(new Game_Text("9", 245, 484,Color.black, true, "Serif", 20));
		text.add(new Game_Text("....", 238, 494,Color.black, true, "Serif", 20));
		text.add(new Game_Text("5", 206, 415,Color.black, true, "Serif", 20));
		text.add(new Game_Text("....", 199, 425,Color.black, true, "Serif", 20));
		text.add(new Game_Text("8", 163, 342,Color.red, true, "Serif", 20));
		text.add(new Game_Text(".....", 155, 352,Color.red, true, "Serif", 20));
		text.add(new Game_Text("11",198, 271,Color.black, true, "Serif", 20));
		text.add(new Game_Text("..", 202, 281,Color.black, true, "Serif", 20));	
		text.add(new Game_Text("6", 285, 271,Color.red, true, "Serif", 20));
		text.add(new Game_Text(".....", 278, 281,Color.red, true, "Serif", 20));
		text.add(new Game_Text("4",365, 271,Color.black, true, "Serif", 20));
		text.add(new Game_Text("...", 363, 281,Color.black, true, "Serif", 20));	
		text.add(new Game_Text("3",404, 342,Color.black, true, "Serif", 20));
		text.add(new Game_Text("..", 404, 352,Color.black, true, "Serif", 20));
		text.add(new Game_Text("4",365, 415,Color.black, true, "Serif", 20));
		text.add(new Game_Text("...", 363, 425,Color.black, true, "Serif", 20));
		text.add(new Game_Text("3", 285, 415,Color.black, true, "Serif", 20));
		text.add(new Game_Text("..", 285, 425,Color.black, true, "Serif", 20));	
		text.add(new Game_Text("10",240, 342,Color.black, true, "Serif", 20));
		text.add(new Game_Text("...", 242, 352,Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 730,88, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 730,272, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 730,180, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 730,373, Color.black, true, "Serif", 20));
		text.add(new Game_Text("=", 730,476, Color.black, true, "Serif", 20));
		text.add(new Game_Text("Building Requirements", 840,50, Color.black, true, "Serif", 20));
		text.add(new Game_Text("Card Counts ", 50,570,Color.black, true, "Serif", 20));
		text.add(new Game_Text("Build Buttons ", 510,580,Color.black, true, "Serif", 20));
		text.add(new Game_Text("Build Road", 785,630, Color.white, true, "Serif", 20));
		text.add(new Game_Text("Build Devolopment Card", 905,630, Color.white, true, "Serif", 20));
		text.add(new Game_Text("END TURN", 485,80, Color.red, true, "Serif", 20));
		text.add(new Game_Text("Build Settlement", 615,630, Color.white, true, "Serif", 20));
		text.add(new Game_Text("Build City", 505,630, Color.white, true, "Serif", 20));	
		text.add(new Game_Text("Roll", 505, 200, Color.white, true,"Serif", 20));
		
		
		HashMap<String, ArrayList<Game_Object>> red = new  HashMap<String, ArrayList<Game_Object>>();
		HashMap<String, ArrayList<Game_Object>> blue = new  HashMap<String, ArrayList<Game_Object>>();
		HashMap<String, ArrayList<Game_Object>> orange = new  HashMap<String, ArrayList<Game_Object>>();
		HashMap<String, ArrayList<Game_Object>> black = new  HashMap<String, ArrayList<Game_Object>>();
			
		
		players.add(new Catan_player("Red", 0, true, false, false, red, new  HashMap<String, ArrayList<Game_Text>>(), Color.red));
		players.add(new Catan_player("Blue", 0, false, false, false, blue, new  HashMap<String, ArrayList<Game_Text>>(), Color.blue));
		players.add(new Catan_player("Orange", 0, false, false, false, orange, new  HashMap<String, ArrayList<Game_Text>>(), Color.orange));
		players.add(new Catan_player("Black", 0, false, false, false,  black, new  HashMap<String, ArrayList<Game_Text>>(), Color.black));
			
		Game_Object brd = new Game_Object("Game_Board",new JT_Point(128,155),0,0 , null, null,null,0);
		brd.setHeight(376);
		brd.setWidth(403);
		Board.add(brd);
		
		
	}
	
	public HashMap<String, ArrayList<Game_Object>> make_hash(){
		HashMap<String, ArrayList<Game_Object>> objs = new HashMap<String, ArrayList<Game_Object>>();
		objs.put("whats_what", this.whats_what);
		objs.put("tiles", this.tiles);
		objs.put("buttons", this.buttons);
		objs.put("Costs", this.Costs);
		objs.put("res", this.resrouce_card);
		objs.put("Board", this.Board);
		
		return objs;
	}
	
	public HashMap<String, Player> getPlayers() {
		HashMap<String, Player> player = new HashMap<String, Player>();
		for (Player play: this.players) {
			player.put(play.getName(), play);
		}
		return player;
	}



	public HashMap<String, ArrayList<Game_Text>> get_txt(){
		HashMap<String,  ArrayList<Game_Text>> txt = new HashMap<String, ArrayList<Game_Text>>();
		txt.put("Set_txt", this.text);
		return txt;
		
	}



	public String getBackround() {
		return backround;
	}
	
	
	
}
	
	

