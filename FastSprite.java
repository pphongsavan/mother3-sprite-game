import java.awt.image.BufferedImage;
/**
 * Child class of Sprite can run away the farthest out of the others
 * Overrides Sprite.run in order to accomplish this
 * @author Prinyavong Phongsvan
 *
 */
public class FastSprite extends Sprite{

	/**
	 * Default constructor that automatically calls
	 * the super default constructor
	 */
	public FastSprite(){}

	/**
	 * Constructor method of the sprite, defining its
	 * starting X and starting Y
	 * @param someX - starting X position
	 * @param someY - starting Y position
	 * @param someWidth - actual width of sprite
	 * @param someRSpeed - actual height of sprite
	 * @param someRCycles - Jump height
	 */
	public FastSprite(int someX, int someY, 
			int someWidth, int someHeight, 
			int someRCycles, int someRSpeed, 
			BufferedImage someIMG){
		super(someX, someY, someWidth, someHeight, someRCycles, someRSpeed, someIMG);
	}

	/**
	 * Changes the x  and y variable of the Sprite to make it move
	 * it move to a random location on the screen.
	 * FastSprite can run in a sphere of 200 spaces from
	 * its current location
	 */
	protected void run(){
		//Generate a number from 1 or 2
		int addOrSubtract = (int)((Math.random()) * 2);

		//if addOrSubtract = 1, move it forward somewhere
		if (addOrSubtract == 1){
			x += (int)((Math.random()) * 200);
			y += (int)((Math.random()) * 200);
		}

		//else move it back somewhere
		else{
			x -= (int)((Math.random()) * 200);
			y -= (int)((Math.random()) * 200);
		}

		//If it runs off screen, put it back where the user can click on it
		if(x > 390 || x < 10){
			x = 15;
		}
		if(y > 390 || y < 10){
			y = 50;
		}
	}
}
