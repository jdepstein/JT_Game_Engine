package Game_Engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Game_Object {

private double dx, dy;
private JT_Point location;
private String name;
private boolean onScreen;
private double distance;
private double width;
private double height;
private BufferedImage image;
private BufferedImage renderImage;
private Polygon poly;
private int health;
private int dmg;
private double rotateangle; 
private Color color;
private int Win_size;

/**
 * @param name String: The name of the object
 * @param loc JT_Point: The location of the object
 * @param dx double: The x movement of the object
 * @param dy double: The y movement of the object
 * @param img_path Stirng: The path to the image could be null if using polygon
 * @param poly Polygon: The shape that is for this object can be null if using image
 * @param color Color: Used to Color the poly
 * @param Win_size: The size of the window the object is being dawn on
 */

public Game_Object(String name, JT_Point loc , double dx, double dy, String img_path, Polygon poly, Color color, int Win_size) {
		
		if (poly != null) {
			this.poly = poly;
			this.width = poly.getBounds().getWidth();
			this.height = poly.getBounds().getHeight();}
		
		else if(img_path != null) {
				Image img = null;
				try {
					img = ImageIO.read(new File(img_path));} 
				
				catch (IOException ioe) {
					System.out.println("Error Reading" + name);
					System.exit(0);}
				
				this.image = convert(img);
				this.width = this.image.getWidth(null);
				this.height = this.image.getHeight(null);
				this.distance = this.setdistance(this.image);
				this.renderImage = this.image;
				}
		
		this.name = name;
		this.location = loc;
		this.dx = dx;
		this.dy = dy;
		this.onScreen = false;
		this.color = color;
		this.Win_size= Win_size;
		
	}


	private BufferedImage convert(Image img) {
		
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
	
		return bimage;}
	
	/**
	 * @return double: the objects change in x
	 */
	public double getDx() {
		return dx;}
	
	/**
	 * @param double: the objects new change in x
	 */
	public void setDx(double dx) {
		this.dx = dx;}
	
	/**
	 * @return double: the objects change in y
	 */
	public double getDy() {
		return dy;}
	
	/**
	 * @param double: the objects new change in x
	 */
	public void setDy(double dy) {
		this.dy = dy;}
	
	/**
	 * @return JT_Point: get the objects location
	 */
	public JT_Point getLocation() {
		return location;}
	
	/**
	 * @param JT_Point: sets the objects location
	 */
	public void setLocation(JT_Point location) {
		this.location = location;}
	
	
	/**
	 * @param double: sets the objects location x plus a new value
	 */
	public void updateXpos(double loc) {
		
		this.getLocation().x= this.getLocation().x + loc;}
	
	
	/**
	 * @param double: sets the objects location y plus a new value
	 */
	public void updateYpos(double loc) {
		this.getLocation().y= this.getLocation().y + loc;}

	
	/**
	 * @param double: sets the objects location x 
	 */
	public void setXPos(double loc) {
		this.setLocation(new JT_Point(loc, this.getLocation().y));}
	
	/**
	 * @param double: sets the objects location y
	 */
	public void setYPos(double loc) {
		this.setLocation(new JT_Point(this.getLocation().x, loc));}
	
	/**
	 * @return double: gets the objects width 
	 */
	public double getWidth() {
		return width;}
	
	/**
	 * @return double: gets the objects Height 
	 */
	public double getHeight() {
		return height;}
	
	
	/**
	 * @param double: sets the objects Width 
	 */
	public void setWidth(double width) {
		this.width = width;}
	
	/**
	 * @param double: sets the objects Height 
	 */
	public void setHeight(double height) {
		this.height = height;}
	
	/**
	 * @return String: gets the objects Name 
	 */
	public String getName() {
		return name;}
	
	/**
	 * @return boolean: gets if objects on screen 
	 */
	public boolean isOnScreen() {
		return onScreen;}
	
	/**
	 * changes if the object is showing or not 
	 */
	public void ScreenCrange() {
		this.onScreen = !this.onScreen; }
	
	/**
	 * @return Image: Gets the objects image
	 */
	public Image getImage() {
		return image;}
	
	/**
	 * @return Polygon: Gets the objects Polygon
	 */
	public Polygon getPoly() {
		return poly;}
	
	
	private double setdistance(Image image) {
		return image.getHeight(null)/2;}
		
	
	/**
	 * @return integer: Gets the objects health
	 */
	public int get_health() {
		return health;}
	
	/**
	 * increments the objects health by one
	 */
	public void decrease_health() {
		this.health= this.health-1;}
	
	/**
	 * @param health integer: Sets the health to a new value
	 */
	public void set_health(int health) {
		this.health= health;}
	
	/**
	 * @return integer: get the objects damage value
	 */
	public int get_dmg() {
		return dmg;}
	
	
	/* Three types of collision checks
	 * 1) between two objects
	 * 2) between an object and the objects within a list
	 * 3) between two lists of objects
	 */
	public boolean collision_1(Game_Object one, Game_Object two) {
			return one.isOverlapping(two);}
	
	
	public void collision_2(ArrayList<Game_Object> list) {
		for (int i = 0; i <list.size() ; i++) {
		    for (int j = i + 1; j < list.size(); j++) {
		    Game_Object one = list.get(i);
			Game_Object two = list.get(j);
				if(one.isOverlapping(two)) {
					double tempdx = one.getDx();
					one.setDx(two.getDy());
					two.setDx(tempdx);
				
					double tempdy = one.getDy();
					one.setDy(two.getDy());
					two.setDy(tempdy);
				}}}}
		
		
	public Game_Object collision_4(ArrayList<Game_Object> list, ArrayList<Game_Object> list2) {
		for (int i = 0; i <list.size() ; i++) {
		    for (int j = i + 1; j < list2.size(); j++) {
		    Game_Object one = list.get(i);
			Game_Object two = list2.get(j);
				if(one.isOverlapping(two)) {
					return one;
				}}}
			return null;}
	
	
	/**
	 * @return double: returns the objects angle of rotation
	 */
	public double getRotationAngle(){
		return rotateangle;}
	
	/**
	 * changes an objects rotation angle  by increasing used for the actual rotation of the object
	 */
	public void increaseRotationAngle(){
		if(getRotationAngle() >= 355) 
			{rotateangle = 0;}
		else 
			{rotateangle += 5;}
		
		double rotationRequired = Math.toRadians (rotateangle);
		double locationX = this.width / 2;
		double locationY = this.height / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	
		renderImage = op.filter(image, null);}
	   
	/**
	 * changes an objects rotation angle  by decreasing used for the actual rotation of the object
	 */
	public void decreaseRotationAngle(){
		if(getRotationAngle() < 0)
			{rotateangle = 355;}
	    else {rotateangle -= 5;}
		
		double rotationRequired = Math.toRadians (rotateangle);
		double locationX = this.width / 2;
		double locationY = this.height / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	
	
		renderImage = op.filter(image, null);}
	
	
	/**
	 * @return double: get the objects distance
	 */
	public double getdistance()
		{return distance;}
	
	/**
	 * set the rotation angle back to 0
	 */
	public void setRA() {
		this.rotateangle=0;}
	
	/**
	 * Move an object
	 */
	public void move() {
		  
	   double chx = distance*Math.cos(Math.toRadians(rotateangle-90));
	   double chy = distance*Math.sin(Math.toRadians(rotateangle-90));
	  
	    if(this.getLocation().x < 0) {
	        this.setLocation(new JT_Point(this.Win_size, this.getLocation().y));} 
	    
	    else if (this.getLocation().x > this.Win_size){
	        	 this.setLocation(new JT_Point(0, this.getLocation().y));}
	   
	    if(this.getLocation().y < 0){
	    	 this.setLocation(new JT_Point(this.getLocation().x, this.Win_size));} 
	    
	    else if (this.getLocation().y > this.Win_size){
	    	 this.setLocation(new JT_Point(this.getLocation().x, 0));}
	    
	     this.updateXpos(chx/3);
	     this.updateYpos(chy/3);}
	 
	/**
	 * move object in x and y
	 */
	public void Auto_move() {
		updateXpos(dx);
		updateYpos(dy);}
		 
	/**
	 * @return boolean: checks to see if the objects  health is still above 0
	 */
	public boolean is_alive(){
		
		if (this.health > 0) {
			return true;}
		
		else {return false;}}
		
	/**	
	 * @param other Game_Object: checks to see if it is overlapping this game object
	 * @return boolean: returns if they are overlapping or not
	 */
	public boolean isOverlapping(Game_Object other) {
		
		   if ((this.getLocation().y - this.getHeight()) > (other.getLocation().y + (other.getHeight()/2))){
			   return false; }
				   
				   	   
		   if((this.getLocation().y) + this.getHeight() < (other.getLocation().y - (other.getHeight()/2))) {
			   return false;}
		     
		   	
		   if ((this.getLocation().x + this.getWidth()/2) < (other.getLocation().x - (other.getWidth()/2))) {
			   	return false;}
		   
		   if((this.getLocation().x - this.getWidth()/2) > (other.getLocation().x + other.getWidth()/2)) {
			   return false;}	   
				 
		    return true;}
	
	/**
	 * @param page Graphics: the page the object is being drawn on
	 */
	public void draw(Graphics page) {
		 page = (Graphics2D) page;
		if (getImage() != null) {
	
			page.drawImage(renderImage, (int) location.x, (int) location.y, null);}
		
		
		if (this.poly != null ) {
			((Graphics2D) page).setPaint(this.color);
			page.fillPolygon(this.getPoly());}}
	
	/**
	 * @param x integer: the location of the click in x
	 * @param y integer: the location of the click in y
	 * @return boolean: if the object was clicked or not
	 */
	public boolean has_clicked(int x, int y) {
		if (this.poly != null) {
			if (this.poly.contains(new Point(x,y))){
				return true;}}

		else if (x >= this.location.x && x <= this.location.x + this.getWidth()
		&& y >= this.location.y && y <= this.location.y + this.getHeight()) {
			return true;}
		return false;}
}


