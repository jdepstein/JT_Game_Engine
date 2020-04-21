package Game_Engine;

import java.util.ArrayList;
import java.util.HashMap;


public class Player{
	
	private String name;
	protected Integer Score;
	protected boolean is_playing;
	private boolean has_lost;
	private boolean has_won;
	private HashMap<String, ArrayList<Game_Object>> objects;
	private HashMap<String, ArrayList<Game_Text>> game_text; 
	
	/**
	 * @param nm: A string for the players name 
	 * @param sc: An integer players score
	 * @param ip: A boolean the player is currently playing
	 * @param hl: Boolean value to say if the player has lost
	 * @param hw: Boolean value to say if the player has won
	 * @param obj: All the objects associated with the player in a HashMap
	 * @param game_text:	All the texts that are associated with the player in a HashMap
	 */
	
	public Player(String nm, Integer sc, boolean ip, boolean hl, boolean hw, HashMap<String, ArrayList<Game_Object>> obj,HashMap<String, ArrayList<Game_Text>> game_text ) {
		
		name = nm;
		Score = sc;
		is_playing = ip;
		has_lost = hl;
		has_won = hw;
		objects = obj;
		this.game_text = game_text;}

	/**
	 * @return Gets the players current Score
	 */
	public Integer getScore() {
		return Score;}
	
	/**
	 * @param score integer: The amount the score is being incremented by 
	 */
	public void addScore(int score) {
		Score += score;}

	/**
	 * @return boolean: The player's has_lost value
	 */
	public boolean Has_lost() {
		return has_lost;}

	/**
	 * @return boolean: The player's has_won value
	 */
	public boolean Has_won() {
		return has_won;}
	
	/**
	 * @return String: The name of the player
	 */
	public String getName() {
		return name;}
	
	/**
	 * @return boolean: The player's is_playing value
	 */
	public boolean is_playing() {
		return is_playing;}

	/**
	 * @return HashMap<String, ArrayList<Game_Object>>: The player's object Map
	 */
	public HashMap<String, ArrayList<Game_Object>> getObjects() {
		return objects;}

	/**
	 * @param key String: The type objects that you are looking for
	 * @return ArrayList<Game_Object>: The list of objects that matches the given in key in the player's object
	 */
	public ArrayList<Game_Object> get_object(String key) {
		return this.objects.get(key);}
	
	/**
	 * @param key String: The key that will represent the Game_Objects list 
	 * @param value ArrayList<Game_Object>: The list of objects that match with given key
	 */
	public void setObjects(String key, ArrayList<Game_Object> value) {
		this.objects.put(key, value);}
	
	/**
	 * @param key String: The key that represent the Game_Objects list you are adding to 
	 * @param value Game_Object: The object that will be added to the list that matches the key in player's object
	 */
	public void addObject(String key, Game_Object value) {
		
		ArrayList<Game_Object> objs = this.objects.get(key);
		objs.add(value);
		this.objects.replace(key, objs);}

	/**
	 * @param key String: The key that represent the Game_Texts list you are adding to 
	 * @param value ArrayList<Game_Object>: The list of objects that match with given key
	 */
	public void setGame_text(String key, ArrayList<Game_Text> value) {
		this.game_text.put(key, value);}
	
	
	/**
	 * @return ArrayList<Game_Text>: The values in the players Game_Text Map made into a list 
	 */
	public ArrayList<Game_Text> getAll_Text(){
		ArrayList<Game_Text> allObjects = new ArrayList<Game_Text> ();
		for (String key: this.game_text.keySet()) {
			allObjects.addAll(game_text.get(key));}
		return allObjects;}
	
	/**
	 * @return ArrayList<Game_Object>: The values in the players Game_Object Map made into a list 
	 */
	public ArrayList<Game_Object> getAllObjects() {
		ArrayList<Game_Object> allObjects = new ArrayList<Game_Object>();
		for (String key: objects.keySet()) {
			allObjects.addAll(objects.get(key));
			}
		return allObjects;}
	
	/**
	 * @param key String: The key that represent the Game_Object list you are removing from the player 
	 */
	public void removeobj(String key){
		objects.remove(key);}
	
	/**
	 * @param key String: The key that represent the Game_Texts list you are removing from the player 
	 */
	public void removetxt(String key){
		game_text.remove(key);}
		
	/**
	 * The player has lost so it sets has_lost equal to true
	 */
	public void lost() {
		has_lost = true;}
	
	/**
	 * The player has won so it sets has_won equal to true
	 */
	public void won() {
		has_won = true;}
		
	/**
	 * The player is now playing so it sets is_playing equal to true
	 */
	public void playing() {
		is_playing = true;}
	
	/**
	 * The player is no longer playing so it sets is_playing equal to flase
	 */
	public void not_playing() {
		is_playing = false;}

	}