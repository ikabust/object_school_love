package src;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * Class for the craft (the player) that extends Sprite
 * Contains a list of missiles
 * Its coordinate are managed by x and y
 * The craft can only move up or down using the arrow keys
 */
public class Craft extends Sprite {
	
	private static Craft craft;
	
	private int dx; //繧ｭ繝｣繝ｩ縺ｮ菴咲ｽｮ
	private double dy;
	private int xv = 5;
	private int yv = 10;
	private double gravity = 0.4;
	private boolean jumping = false;
	private int jumpTimer = 0;

	private ArrayList<Missile> missiles; //list of visible missiles
	
	private boolean immune = false; //boolean for the immunity state, depending of the immune bonus

	private int missilestate; //defines the kind of missile the player is using
	
	public Craft(int x, int y){
		super(x,y);
		initCraft();
	}
	
	private void initCraft(){
		
		missiles = new ArrayList<>();
		loadImage("/Girl.png");
		width *= 0.5;
		height *= 0.5;
		//image = image.getScaledInstance((int) (width * 0.5), -1, Image.SCALE_SMOOTH);
		getImageDimensions();
		
		missilestate = 0;
		
		Craft.craft = this;
		
	}
	/*
	 * Increment of the y coordinate of the craft by the number given by keyPressed()
	 * The craft is limited by the upper and lower bounds of the screen (20 and 264)
	 */
	public void move(){
		
		x += dx;
		if (x < 20) {x = 20;} 
		if (x > 430) {x = 430;}

		y += dy;
		if (y < 20) {y=20;}
		if (y > 240) {y = 240;}
		
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getMissiles(){
		return missiles;
	}
	
	/*
	 * While the key is pressed, increments or decrements the y coordinate of the craft
	 */
	public void keyPressed(KeyEvent e){
		// 讓ｪ遘ｻ蜍輔↓譖ｸ縺肴鋤縺医ｋ
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			dx = -3;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
		if (key == KeyEvent.VK_SPACE) {
			jumpTimer += 1;
			if (!jumping) {
				dy = -10;
			}
		}		
	}
	
	/*
	 * The fire method is only called on the release of the space key to avoid a flooding of missiles
	 */
	public void keyReleased(KeyEvent e){
		// 繧ｸ繝｣繝ｳ繝怜�ｦ逅�霑ｽ蜉�縺励ｈ縺�
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			jumpTimer = 0;
		}
	}

	public void gravity() { 
		if (y < 240) {
			jumping = true;
			dy += gravity;
		} else {
			jumping = false;
		}
	}

	/*
	 * Add missiles to the ArrayList of missiles when the spacebar is released
	 * There is 7 different states for the missiles
	 * A switch is used to define the kind and the number of missiles to launch
	 */
	// 謇薙▽縺�縺九ｉ縺�繧峨↑縺�ｼ�
	public void fire(){
		
		switch(missilestate)
		{
			case 0: //the player can't shoot missiles
				break;
				//TODO
				//ne pas faire apparaitre des demis missiles 
			case 1: //the player can only shoot 5 missiles
				if(missiles.size() < 5)
					missiles.add(new Missile(x + width, y));
				break;
				
			case 2: //infinite missiles on a single row
				missiles.add(new Missile(x + width, y));
				break;
			
			case 3: //missiles on two rows
				if(y<264)
					missiles.add(new Missile(x + width, y + 64));
				missiles.add(new Missile(x + width, y));
				break;
				
			case 4: //missiles on three rows
				if(y<264)
					missiles.add(new Missile(x + width, y + 64));
				missiles.add(new Missile(x + width, y));
				missiles.add(new Missile(x + width, y - 64));
				break;
				
			case 5: //missiles on five rows
				missiles.add(new Missile(x + width, y - 128));
				missiles.add(new Missile(x + width, y - 64));
				missiles.add(new Missile(x + width, y));
				missiles.add(new Missile(x + width, y + 64));
				missiles.add(new Missile(x + width, y + 128));
				break;
				
			case 20: //piercing missiles on one row
				missiles.add(new Missile(x + width, y));
				break;
				
		}
	}
	
	/*
	 * Increment the missilestate : the craft get a better kind of missile
	 */
	public void upShoot(){
		this.missilestate++;
	}
	
	/*
	 * Decrement the missile state when the player is hit by an enemy
	 * The craft get a worse kind of missile
	 * 
	 */
	public void downShoot(){
		this.missilestate--;
		if(this.missilestate < 0)
			missilestate = 0;
	}
	
	public int getShoot(){
		return this.missilestate;
	}
	
	public void setShoot(int upgrade){
		this.missilestate = upgrade;
	}
	
	public static Craft getCraft(){
		return Craft.craft;
	}
	
	public boolean isImmune(){
		return immune;
	}
	
	public void setImmune(boolean immune){
		this.immune = immune;
	}

}   
