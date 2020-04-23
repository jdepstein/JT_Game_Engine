package Game_Engine;

import java.awt.BorderLayout;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

/** 
 * Sets up the game and its Frame for everything everything to run as it should
 */
@SuppressWarnings("serial")
public class Game_Frame  extends JFrame implements Runnable{
	
	
	    private  Game_Frame thepanel;
	    private static int WindowSize;
	    
	    /**
	     * @param size: The size of the window
	     * @param sc: The scene that will be playing out on the window 
	     */
	     public Game_Frame(int size, Scene sc) {  
	    	  Game_Frame.WindowSize= size;
	          this.setSize(WindowSize, WindowSize);
	          this.setTitle("New Game");
	          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          Game_Panel gamePanel = new Game_Panel(sc, size);
	          this.add(gamePanel, BorderLayout.CENTER);          
	          ScheduledThreadPoolExecutor timer = new ScheduledThreadPoolExecutor(5);
	          timer.scheduleAtFixedRate(Repaint(this), 0L, 20L, TimeUnit.MILLISECONDS);
	          this.setVisible(true);}
	                                

		public static int get_Size() {
			return WindowSize;}
	         
	               
	    private Runnable Repaint(Game_Frame panel) {
	        return this.thepanel = panel;}
	    
	    public static int WindowSize() {
	    		return WindowSize;}
	                               
	@Override
	    public void run() {
	    		thepanel.repaint();}}

	    		
