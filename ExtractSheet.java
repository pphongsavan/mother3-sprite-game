import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Takes a buffered image, ideally a sprite sheet and 
 * makes defined tiles on them into sub images. These sub images 
 * are the individual frames for each Sprite, their different appearances
 * 
 * Used in the Field class to create Sprites and set a certain
 * BufferedImage to them which represents an individual pose
 * @author Prinyavong Phongsavan
 *
 */



public class ExtractSheet {

	/**
	 * The imported sheet, in this program in particular, m3sprites2.png
	 */
	private BufferedImage img;


	/**
	 * Constructor for the imported sheet
	 * @param img
	 */
	public ExtractSheet(BufferedImage img){
		this.img = img;
	}


	/**
	 * The imported sprite sheet image which will be
	 * run through a loop in order to create sub images
	 * that represent the different poses
	 * @param path - the location of the sprite sheet, in this case
	 * images/m3.spriites2.png
	 * @return sheet - the imported sprite sheet as a whole
	 */
	public static BufferedImage getFile(String path) {

		BufferedImage sheet = null;

		/*
		 * follows a defined path to find the file
		 * in this program the path is images/m3sprites2.png
		 */
		try {
			sheet = ImageIO.read(ExtractSheet.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sheet;
	}


	/**
	 * The width of the sprite, which will be used in the
	 * loop to define the dimensions of the sub image
	 */
	public final static int WIDTH = 50;


	/**
	 * The height of the sprite, which will be used in the
	 * loop to define the dimensions of the sub image
	 */
	public final static int HEIGHT = 70;



	/**
	 * Take the imported sprite sheet and take out individual
	 * sprites based on the cells' width and height and number
	 * of columns and rows
	 * @param row - number of rows on the sheet
	 * @param col - number of columns on the sheet
	 * @param width - width of single cell on the sheet
	 * @param height - height of single cell on the sheet
	 * @return single - a single subImage sprite from the sheet
	 */
	public BufferedImage getSingleSprite(int row,int col, int width, int height){
		BufferedImage single = img.getSubimage((row * WIDTH)-WIDTH, (col*HEIGHT)-HEIGHT, width, height);
		return single;
	}



}
