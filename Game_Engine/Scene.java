package Game_Engine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class Scene {
	protected HashMap<String, ArrayList<Game_Object>> objects;
	protected HashMap<String, Player> players;
	private HashMap<String,ArrayList<Game_Text>> text;
	private int level;
	private int Window_size;
	private Image Backround;
	private boolean keyheld;
	private boolean mouseheld;
	
	/**
	 * @param objects HashMap: All the Objects that are in the Scene
	 * @param players HashMap: All the players in the Scene
	 * @param text HashMap: All the Text that appear in the Scene
	 * @param Window_Size int: The size of the window that scene in on 
	 * @param backround Image: the Image to the Background of the scene 
	 */

	public Scene(HashMap<String, ArrayList<Game_Object>> objects, HashMap<String, Player> players, HashMap<String,ArrayList<Game_Text>> 
	text, int Window_Size, String backround){
		
		this.level = 0;
		this.Window_size = Window_Size;
		this.objects = objects;
		this.players = players;
		this.text =text;
		this.keyheld = false;
		this.mouseheld = false;
		
		 if(backround != null) {
			Image img = null;
			try {
				img = ImageIO.read(new File(backround));} 
			
			catch (IOException ioe) {
				System.out.println("Error Reading" + backround);
				System.exit(0);}
			this.Backround =img;}}
	
	/**
	 * Used for creating a Scene
	 */
	public void create_S() {}
	
	/**
	 * @param key char: used for interactions with the Game panel helps get the keys that are typed
	 */
	public void key_type(char key) {}
		
	/**
	 * @param key char: used for interactions with the Game panel helps get the keys that are released
	 */
	public void  key_relase(char key) {}
	
	/**
	 * @param key char: used for interactions with the Game panel helps get the keys that are pressed
	 */
	public void key_pressed(char key) {};
	
	/**
	 * @param x integer: used for interactions with the Game panel helps get the location of where a mouse is pressed in x
	 * @param y integer: used for interactions with the Game panel helps get the location of where a mouse is pressed in y
	 */
	public void mouse_pressed(int x, int y) {}
	
	/**
	 * @param x integer: used for interactions with the Game panel helps get the location of where a mouse is pressed in x
	 * @param y integer: used for interactions with the Game panel helps get the location of where a mouse is pressed in y
	 */
	public void mouse_released(int x, int y) {}
	
	/**
	 * @param x integer: used for interactions with the Game panel helps get the location of where a mouse is pressed in x
	 * @param y integer: used for interactions with the Game panel helps get the location of where a mouse is pressed in y
	 */
	public void mouse_clicked(int x, int y) {}
	
	
	/**
	 * @return integer: get the currents Scenes Window Size
	 */
	public int getWinSize() {
		return this.Window_size;}
	
	/**
	 * This sets that key is no longer held
	 */
	public void letgo_key() {
		this.keyheld = false;}
	
	/**
	 * This adds a new player to the Scene
	 * @param key String: The unique key that matches to this particular
	 * @param value Player: The Player that is being added to the Scene
	 */
	public void add_new_player(String key, Player value) {
		this.players.put(key, value);}
	
	/**
	 * The Key is being held now
	 */
	public void isholding_key() {
		this.keyheld = true;}
	
	/**
	 * The mouse is no longer held
	 */
	public void letgo_mouse() {
		this.mouseheld = false;}
	
	/**
	 * The mouse is being held now
	 */
	public void isholding_mouse() {
		this.mouseheld = true;}
	
	/**
	 * @param key String: The key value related to the text you are looking for
	 * @return ArrayList: The list of text objects related to the given key from this.text
	 */
	public ArrayList<Game_Text> getText(String key) {
		return this.text.get(key);}
	
	/**
	 * @param key String: The unique key related to the new list of Game_Objects
	 * @param value ArrayList: The list of objects that match the key
	 */
	public void add_new_object(String key, ArrayList<Game_Object> value) {
		this.objects.put(key, value);}
	
	/**
	 * @param key String: The unique key related to the of Game_Objects you are looking for
	 * @return ArrayList: The list of objects that match the key
	 */
	public ArrayList<Game_Object> get_object(String key) {
		return this.objects.get(key);}
	
	/**
	 * @param key String: The unique key related to the of Game_Objects you are looking for
	 * @param value Game_Object: The Game object you are adding to the list value that matches the key
	 */
	public void add_object(String key, Game_Object value) {
		ArrayList<Game_Object> ob = objects.get(key);
		ob.add(value);
		this.objects.replace(key, ob);}
	
	/**
	 * @param key String: The unique key related to the of player you are looking for
	 * @return Player: The Player that matches the key
	 */
	public Player get_player(String key) {
		return this.players.get(key);}

	/**
	 * @param x integer: the level you are looking to go to
	 */
	public void update_level(int x) {
		this.level = x;}

	/**
	 * @return integer: the level you are you are currently on
	 */
	public Integer getlvl() {
		return this.level;}
	
	/**
	 * updated the scene 
	 */
	public void update() {}
	
	/**
	 * @return ArrayList: All the non player objects in the Scene
	 */
	public ArrayList<Game_Object> getAllObjects() {
		ArrayList<Game_Object> allObjects = new ArrayList<Game_Object>();
		
		for (String key: objects.keySet()) {
			allObjects.addAll(objects.get(key));}
		return allObjects;}

	/**
	 * @return ArrayList: All the player's objects in the Scene
	 */
	public ArrayList<Game_Object> getAllObjectsPlayers() {
		ArrayList<Game_Object> allObjects = new ArrayList<Game_Object>();
		for (String player: players.keySet()) {
			allObjects.addAll(players.get(player).getAllObjects());}
		return allObjects;}
	
	/**
	 * @return ArrayList: All the player texts in the Scene
	 */
	public ArrayList<Game_Text> getAllObjectsPlayers_text() {
		ArrayList<Game_Text> allObjects = new ArrayList<Game_Text>();
		for (String player: players.keySet()) {
			allObjects.addAll(players.get(player).getAll_Text());}
		return allObjects;}
	
	/**
	 * @param key String: The unique key related to the of player you are looking for
	 * @param value Player: The updated Player that matches the key that will replaced the old one
	 */
	public void update_player(String key, Player value) {
		this.players.replace(key, value);}
	
	/**
	 * @return HashMap: returns the player hash map
	 */
	public HashMap<String, Player> getPlayers() {
		return players;}
	
	/**
	 * @return ArrayList: returns the non-player text
	 */
	public ArrayList<Game_Text> gettext(){	
		ArrayList<Game_Text> allObjects = new ArrayList<Game_Text>();
		for (String txt: this.text.keySet()) {
			allObjects.addAll(this.text.get(txt));}
		return allObjects;}

	/**
	 * @param key String: The unique key related to the of player you are looking for
	 * @param value  ArrayList: The text list will match that key 
	 */
	public void add_txt(String key, ArrayList<Game_Text> value) {
		this.text.put(key, value);}
	
	/**
	 * @param key String: The unique key related to the of text you are looking for
	 * @param value ArrayList: The updated text that matches the key that will replaced the old one
	 */
	public void replace_txt(String key, ArrayList<Game_Text> value) {
		this.text.replace(key, value);}
	
	/**
	 * @param obj HashMap: change all objects in the current scene
	 */
	public void set_Objects(HashMap<String, ArrayList<Game_Object>> obj) {
		this.objects = obj;}

	/**
	 * @return Image: get the background Image from the Scene
	 */
	public Image getBackround() {
		return Backround;}
	
	/**
	 * @param key String: The unique key related to the of player you are looking for 
	 * @param value Game_Object: The object you are adding to the player list
	 */
	public void add_player_objecy(String key,  Game_Object value) {
		Player play = players.get(key);
		play.addObject(key, value);
		this.update_player(key, play);}

	/**
	 * @return boolean: the key_hled value
	 */
	public boolean isKeyheld() {
		return keyheld;}

	/**
	 * @return boolean: the mouse_hled value
	 */
	public boolean isMouseheld() {
		return mouseheld;}
	

}