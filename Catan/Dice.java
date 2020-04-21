package Catan;



import java.util.Random;


public class Dice {
	
	private int roll_val;

	public Dice(){
		
		roll_val = 0;}
	
	public void roll() {
		Random dice = new Random();
		this.roll_val = dice.nextInt(6)+ 1;}
	
	public Integer get_roll() {
		return this.roll_val;}

	

}
