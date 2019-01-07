import java.awt.image.BufferedImage;

/**
 * Serves as a parent class to the other sprite
 * classes in the program.
 * Stores an x and y, which is mutated by the
 * several run method, as well as a BufferedImage
 * which is changed by indexing in the mouseClicked method
 * of Field.java
 * The run method is overrun in the child classes so they
 * travel different distances. Admittedly, the code doesn't use
 * inheritance to much extent. The run method could have been
 * written in a way to make overriding negligible, however
 * the code is written in this way to demonstrate the
 * ability for child classes to override parent methods
 */

public abstract class Sprite {
	/**
	 * The starting x and y position of the sprite
	 */
	protected int x, y;

	/**
	 * The actual width and height of the sprite, used to check if isInside
	 */
	protected int actualWidth, actualHeight;

	/**
	 * The speed at which the sprite runs and jumps as well as 
	 * the number of cycles it goes through
	 */
	protected int  runCycles, runSpeed;

	/**
	 * Each Sprite will have a BufferedImage which represents
	 * a frame in the animation
	 */
	protected BufferedImage f;


	/**
	 * If isInside returns true, will be set to true, triggering animation
	 */
	protected boolean move;

	/**
	 * Default constructor that gives sprite an
	 * arbitrary starting point and other data members
	 */
	public Sprite(){
		this(50, 50,		//Arbitary starting point of 50, 50
				5, 5,		//Arbitrary width, height, 
				5, 5,		//cycles, and speed);	
				null);			//the single sprite it holds
	}

	/**
	 * Constructor method of the sprite, defining its
	 * starting X and starting Y
	 * @param someX - starting X position
	 * @param someY - starting Y position
	 * @param someWidth - actual width of sprite
	 * @param someHeight - actual height of sprite
	 * @param someRCycles - how quickly the sprite moves
	 * @param someRSpeed - how quickly the sprite moves
	 * @param someIMG - sprite's appearance
	 */
	public Sprite(int someX, int someY, int someWidth, int someHeight, int someRCycles, int someRSpeed, BufferedImage someIMG){
		x = someX;
		y = someY;
		actualWidth = someWidth;
		actualHeight = someHeight;
		runCycles = someRCycles;
		runSpeed = someRSpeed;
		f = someIMG;
	}

	/**  
	 * Defines the characteristics of a die sprite
	 *      given a location, height, width,
	 *      cycles, speed, and image
	 */ 
	public void setup(int someX, int someY, int someWidth, int someHeight, int someRCycles, int someRSpeed, BufferedImage someIMG){
		x = someX;
		y = someY;
		actualWidth = someWidth;
		actualHeight = someHeight;
		runCycles = someRCycles;
		runSpeed = someRSpeed;
		f = someIMG;

	}



	/**
	 * Changes the x  and y variable of the sprite to make
	 * a random location on the screen
	 */
	protected abstract void run();


	/**
	 * Checks if mouse click x and y are inside the sprite
	 * @param x
	 * @param y
	 * @return true if mouse click x and y are in the sprite
	 */
	public boolean isInside(int xPos, int yPos){
		boolean inside = false;

		if (xPos >= this.x && xPos <= this.x + this.actualHeight
				&& yPos >= this.y && yPos <= this.y + this.actualHeight){
			inside = true;
		}
		return inside;
	}


}