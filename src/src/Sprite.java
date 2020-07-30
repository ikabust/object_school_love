package src;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * This class is the pattern for every sprite of the game.
 * It contains every kind of information related to a sprite. 
 */
// 表示のために使ってるっぽい
public class Sprite {
	
	//coordinates of the sprite
    protected int x;
    protected int y;
    //size of the sprite
    protected int width;
    protected int height;
    //state of life (visible or not)
    protected boolean vis;    // 見えているか
    protected Image image;
    
    protected String soundName;    // 効果音の名前？

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }
    
    /*
     * Load the image associated with the sprite (given in parameters)
     */
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(getClass().getResource(imageName));
        image = ii.getImage();
    }
    
    /*
     * Load the sound associated with the sprite (given in prameters)
     */
    protected void loadSoundName(String soundName){
    	this.soundName = soundName;
    }
    
    protected void getImageDimensions() {
        // 画像サイズまんまだから調整必要ですね
        width = image.getWidth(null);
        height = image.getHeight(null);
    }    

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
    
    //returns the bounding rectangle of the sprite image
    // 当たり判定で使用
    public Rectangle getBounds(){
    	
    	return new Rectangle(x, y, width, height);
    	
    }
    
    public String getSoundName(){
    	return soundName;
    }
    
    // 音楽流す 効果音かな
    public void playSound(){
    	Sound.play(this.getSoundName());
    }
}