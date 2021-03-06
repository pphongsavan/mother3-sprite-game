import java.awt.image.BufferedImage;
/**
 * Child class of Sprite which runs away the least compared
 * to the other children
 * Overrides Sprite.run in order to accomplish this
 * @author Prinyavong Phongsvan
 *
 */
public class LazySprite extends Sprite{

	/**
	 * Default constructor that automatically calls
	 * the super default constructor
	 */
	public LazySprite(){}

	/**
	 * Constructor method of the sprite, defining its
	 * starting X and starting Y
	 * @param someX - starting X position
	 * @param someY - starting Y position
	 * @param someWidth - actual width of sprite
	 * @param someRCycles - actual height of sprite
	 * @param someRCycles - Jump height
	 * @param someIMG 
	 */
	public LazySprite(int someX, int someY, 
			int someWidth, int someHeight, 
			int someRCycles, int someRSpeed, 
			BufferedImage someIMG){
		super(someX, someY, someWidth, someHeight, someRCycles, someRSpeed, someIMG);
	}


	/**
	 * Changes the x  and y variable of the Sprite to make it move
	 * it move to a random location on the screen.
	 * LazySprite only runs in a sphere of 50 spaces from
	 * its current location
	 */
	protected void run(){
		//Generate a number from 1 or 2
		int addOrSubtract = (int)((Math.random()) * 2);

		//if addOrSubtract = 1, move it forward somewhere
		if (addOrSubtract == 1){
			x += (int)((Math.random()) * 50);
			y += (int)((Math.random()) * 50);
		}

		//else move it back somewhere
		else{
			x -= (int)((Math.random()) * 50);
			y -= (int)((Math.random()) * 50);
		}


		//If it runs off screen, put it back where the user can click on it
		if(x > 390 || x < 10){
			x = 100;
		}
		if(y > 390 || y < 10){
			y = 50;
		}
	}




}
