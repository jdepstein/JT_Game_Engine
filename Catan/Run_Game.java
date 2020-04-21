package Catan;
import Game_Engine.Game_Frame;
public class Run_Game {
	

public static void main(String[] args) {
	Catan_Game catan = new Catan_Game();
	Catan_Scene cs = new Catan_Scene(catan.make_hash(), catan.getPlayers(), catan.get_txt(), 0, catan.getBackround());
	new Game_Frame(1200, cs);
	
	
	
	
}}
