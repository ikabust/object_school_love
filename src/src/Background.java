package src;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * This class is used to manage the background picture and its movement
 */
public class Background {

	private int pos_x; //initial position of the background
	private Image image;
	private int speed; //speed of the movement
	// プレイヤーの動きによってスピードを変えたら良さそう
	public Background(){
		
		ImageIcon ii = new ImageIcon(getClass().getResource("/Back.png"));
        image = ii.getImage();
        
        speed = 1;
		
		pos_x = this.getWidth() - 500;
		
	}
	
	public int getWidth(){
		
		return image.getWidth(null);
		
	}
	
	public Image getImage() {
	    return image;
	}

	//public void setEndImage() {
	//	ImageIcon endii = new ImageIcon(getClass().getResource("/BackEnd.png"));
	//	image = endii.getImage();
	//}
	 
	public int getPosX() {
		 
		return pos_x; 
		 
	}
	 
	 /*
	  * Increment of the x coordinate of the background
	  */
	 public void move(){
		 
		if(pos_x>image.getWidth(null))
			pos_x = 0;
			
		pos_x += speed ;
		 
	}
	 
	public int getSpeed(){
		return speed;
	}
	 
	public void setSpeed(int speed){
		this.speed = speed;
	}
}
