package Catan;
import java.awt.Color;
import java.awt.Polygon;

import Game_Engine.Game_Object;
import Game_Engine.JT_Point;

public class Catan_Object extends Game_Object{
	

	private Integer Victory_pts;		// pts for each object
	private String 	Value;				// dice_values
	
	/**
	 * @param vp integer: the number of victory points this item is work
	 * @param val String: the value that is attached to this in terms of the game 
	 */
	public Catan_Object(String name, JT_Point loc, double dx, double dy, String img_path, Polygon poly, Color color,int win, int vp, String val) {
		super(name, loc, dx, dy, img_path, poly, color, win);
		this.Victory_pts = vp;
		this.Value = val;}
	
	//Getters and setters for value and Victory pts
	public Integer getVictory_pts() {
		return Victory_pts;}

	public void setVictory_pts(Integer victory_pts) {
		Victory_pts = victory_pts;}

	public String getValue() {
		return Value;}
 
	public void setValue(String value) {
		Value = value;}
	
	/**
	 * used for telling the game what type of road to place
	 */
	public String road_type(int x, int y) {
		double cx = this.getLocation().x;
		double cy = this.getLocation().y;

		if (cx + this.getWidth()/3 <= x && cy - 100 < y && cy + 100 > y) {

			return "Mid";}
		else if (cx - this.getWidth()/3 >= x && cy - 100 < y && cy + 100 > y) {

			return "Mid";}
		
		else if (cx >= x && cy > y) {
			return "Right";}
		
		else if (cx <= x && cy < y ) {
			return "Right";}
		
		else if (cx <= x && cy > y) {
			return "Left";}
		
		else {
			return "Left";
		}}
}
