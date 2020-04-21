package Game_Engine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Game_Text {
	private String text;
	private int x;
	private int y;
	private Color color;
	private boolean On_Screen;
	private int Font_size;
	private String font;
	
	/**
	 * @param txt String: The text that is being written
	 * @param x integer: The x location of the text
	 * @param y integer: The y location of the text
	 * @param color Color: The color of the text
	 * @param on boolean: For if the text is on or off screen
	 * @param Font String: The name of the font you are using
	 * @param Font_Size integer: The size of the font you are using
	 */
	public Game_Text(String txt, int x, int y, Color color, boolean on, String Font, int Font_Size) {
		this.text = txt;
		this.x = x;
		this.y = y;
		this.color = color;
		this.On_Screen = on;
		this.font = Font;
		this.Font_size = Font_Size;}
	
	/**
	 *  Changes the on_screen value
	 */
	public void change_screen() {
		this.On_Screen = !this.On_Screen;}
	
	/**
	 * @param page the page the text is being drawn on
	 */
	public void draw(Graphics page) {
		((Graphics2D) page).setPaint(color);
		Font font = new Font(this.font, Font.BOLD, this.Font_size);
		page.setFont(font);
		page.drawString(this.text, this.x, this.y);}
		
	/**
	 * @param x: The text you want to change the text to
	 */
	public void change_text(String x) {
		this.text = x;}}
