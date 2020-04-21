package Game_Engine;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
This will take care of all the stuff to make the game go it sends the all the actions made by the player of the game
as well as continually updating the screen to match what is going on in the scene
 */

@SuppressWarnings("serial")
public class Game_Panel extends JPanel implements KeyListener, ActionListener, MouseListener {


	private Timer timer;
	public static boolean keyHeld = false;
	public static int keyCode;
	public Scene sc;
	public int Size; 
		
	/**
	 * @param scene Scene: The scene that the game panel will be sending info to
	 * @param size integer: the size of the window
	 */
	
	  public Game_Panel(Scene scene, int size) {

          this.sc = scene;
          this.Size = size;

          timer = new Timer(100, this);

          timer.start();
          
          sc.create_S();

          super.addKeyListener(this);

          super.addMouseListener(this);

          super.setFocusable(true);


          this.addKeyListener(new KeyListener() {

                  @Override
                  public void keyPressed(KeyEvent event) {
                	  sc.key_pressed(event.getKeyChar());
                	  sc.isholding_key();}
              
                  @Override
                  public void keyReleased(KeyEvent event) {
                	  sc.key_relase(event.getKeyChar());
                	  sc.letgo_key();}

                  @Override
                  public void keyTyped(KeyEvent event) {
                	  sc.key_type(event.getKeyChar());}
                  
                  });

          
          this.addMouseListener(new MouseListener() {

                  @Override
                  public void mouseClicked(MouseEvent e) {
                	  sc.mouse_clicked(e.getX(),e.getY());}

                  @Override
                  public void mousePressed(MouseEvent e) {
                	  sc.mouse_pressed(e.getX(),e.getY()); 
                	  sc.isholding_mouse();}
                  
                  @Override
                  public void mouseReleased(MouseEvent e) {  	
                      sc.mouse_released(e.getX(),e.getY());
                      sc.letgo_mouse();}

                  @Override
                  public void mouseEntered(MouseEvent e) {}

                  @Override
                  public void mouseExited(MouseEvent e) {} 
                  });}
	
	/**
	 * paints all the objects and text from the scene
	 */
	public void paint(Graphics page) {
		super.paint(page);
		
		page.drawImage(sc.getBackround(), 0,0,null);
		
		for (Game_Object obj: sc.getAllObjects()) {
			obj.draw(page);}
		
		for (Game_Text txt: sc.gettext()) {
			txt.draw(page);}
		
		for (Game_Object obj: sc.getAllObjectsPlayers()) {
			obj.draw(page);}
		
		for (Game_Text txt: sc.getAllObjectsPlayers_text()) {
			txt.draw(page);}}


	/**
	 * Used for repainting the scene on the timer
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == timer) {
			
			sc.update(); 
			super.repaint();}
		
	super.repaint();}

	// All of the Override for the implemented stuff
	@Override
	public void keyTyped(KeyEvent event) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
		

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}

