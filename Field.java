import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

/**
 * Main class which handles all the painting and mouse events. It also instantiates
 * three different Sprites (lucas/duster/kumaIdle) and three arrays that store
 * Sprites (lucas/duster/kumaPoses). Field makes use of methods in the Sprite classes
 * as well as the ExtractSheet class to create these Sprites and Sprite arrays as well
 * as display the Sprites in a certain manner
 * @author Prinyavong Phongsavan
 *
 */
public class Field extends Canvas implements MouseListener, ImageObserver{


	/**
	 * The imported sprite sheet, m3sprites2.png, which makes use of ExtractSheet.java's
	 * various methods in order to create several subimages
	 */
	private BufferedImage spriteSheet = ExtractSheet.getFile("images/m3sprites2.png");


	/**
	 * Create a new instance of extractSheet so we can run
	 * getSingleSprite in our other methods, specifically
	 * the setupLucas, setupDuster, and setupKuma methods
	 */
	ExtractSheet ss = new ExtractSheet(spriteSheet);


	/**
	 * Number of columns in imported sheet, also used as the number of slots in
	 * the Poses arrays and used in the setupSprite methods as part of a loop
	 * 
	 */
	private final int ROWS = 9;


	/**
	 * The location where the mouse is clicked, used in the mouseClicked method
	 * to check if the mouse click isInside a Sprite, if so, various things will
	 * occur
	 */
	private int mouseX, mouseY;


	/**
	 * Instantiate the idle sprites which the Sprite.run method is applied
	 * to in the case of isInside being true. The x and y of the Idles are
	 * the ones checked in the isInside method
	 */
	private RegSprite lucasIdle = new RegSprite();
	private LazySprite dusterIdle = new LazySprite();
	private FastSprite kumaIdle = new FastSprite();


	/**
	 * Instantiate the arrays of frames. Each slot holds a different
	 * Sprite which has a different pose as its BufferedImage.
	 * These arrays are accessed when deciding which pose to paint
	 * in the paint method using an index based on a Count
	 */
	private RegSprite[] lucasPoses = new RegSprite[ROWS];
	private LazySprite[] dusterPoses = new LazySprite[ROWS];
	private FastSprite[] kumaPoses = new FastSprite[ROWS];


	/**
	 * Dimensions of the drawing canvas
	 */
	public int BG_WIDTH = 400;
	public int BG_HEIGHT = 400;


	/**
	 * Counts the number of times a Sprite has been clicked on
	 * This is used to index through the spritePoses arrays and display
	 * the correct image
	 */
	public int lucasCount, dusterCount, kumaCount = 0;


	/**
	 * Properties of the window, a canvas
	 */
	public Field(){
		//we can have a Frame without being a Frame!
		Frame window = new Frame("Sprite Game! - Click on a sprite!");

		setLocation(100, 100);
		setSize(BG_HEIGHT, BG_WIDTH);
		setBackground(Color.red);

		//So we can close our window
		UneFenetre ventana = new UneFenetre();
		window.addWindowListener(ventana);

		//Handles mouse clicks
		addMouseListener(this);

		//makes our Canvas the component held in the Frame
		window.add(this);

		//sets everything up to be visible
		window.pack();
		window.setResizable(false);
		window.setVisible(true);

		//Setup the three Idle Sprites and three Pose arrays
		init();

	}


	/**
	 * Instantiate lists of Sprites and add Sprites to them using the init
	 * helper methods (setupLucas, setupDuster, setupKuma)
	 * Also sets up a single idleSprite that will be used to read mouseClicks
	 * (the idle is the sprite that's tested with the isInside method 
	 * from the Sprite class)
	 */
	public void init(){

		lucasIdle.setup(BG_WIDTH / 8 + (ExtractSheet.WIDTH/2)
				, BG_HEIGHT / 3, 30, 50, 7, 15
				,ss.getSingleSprite(1, 1, ExtractSheet.WIDTH, ExtractSheet.HEIGHT));


		dusterIdle.setup(BG_WIDTH / 2 - (ExtractSheet.WIDTH/2)
				, BG_HEIGHT / 3, 38, 58, 5, 5
				,ss.getSingleSprite(1, 2, ExtractSheet.WIDTH, ExtractSheet.HEIGHT));


		kumaIdle.setup(BG_WIDTH  * 3 / 4 - (ExtractSheet.WIDTH/2)
				, BG_HEIGHT  / 3, 35, 58, 10, 30
				,ss.getSingleSprite(1, 3, ExtractSheet.WIDTH, ExtractSheet.HEIGHT));


		lucasPoses = setupLucas();
		dusterPoses = setupDuster();
		kumaPoses = setupKuma();


	}



	/**
	 * Helper method for init()
	 * Make an array of RegSprite objects that store a different frame/pose
	 * from the imported BufferedImage
	 * Use a for loop to instantiate a temp RegSprite in spot in the array,
	 * set up that temp RegSprite, and then move on to the next slot and
	 * repeat until all the slots are setup
	 * @return lucasSprites - all of Lucas' poses
	 */
	public RegSprite[] setupLucas(){
		RegSprite[] lucasSprites = new RegSprite[9];
		BufferedImage current = null;	//The current frame in animation getSingleSprite is going to extract

		for(int h = 0; h < lucasSprites.length; h++){

			lucasSprites[h] = new RegSprite();	//make a temp sprite to be put in array

			current = ss.getSingleSprite(h + 1 ,1 ,ExtractSheet.WIDTH ,ExtractSheet.HEIGHT);	

			//set up the temp sprite with the grabbed image
			lucasSprites[h].setup(BG_WIDTH / 8 + (ExtractSheet.WIDTH/2)
					, (BG_HEIGHT / 3), 30, 50, 7, 10, current);	

		}
		return lucasSprites;
	}

	/**
	 * Helper method for init()
	 * Make an array of LazySprite objects that store a different frame/pose
	 * from the imported BufferedImage
	 * Use a for loop to instantiate a temp LazySprite in spot in the array,
	 * set up that temp LazySprite, and then move on to the next slot and
	 * repeat until all the slots are setup
	 * @return dusterSprites - all of Duster's poses
	 */
	public LazySprite[] setupDuster(){
		LazySprite[] dusterSprites = new LazySprite[9];
		BufferedImage current = null;	//The current frame in animation getSingleSprite is going to extract

		for(int h = 0; h < dusterSprites.length; h++){

			dusterSprites[h] = new LazySprite();	//make a temp sprite to be put in array

			//get the correct image
			current = ss.getSingleSprite(h + 1 ,2 ,ExtractSheet.WIDTH ,ExtractSheet.HEIGHT);	

			//set up the temp sprite with the grabbed image
			dusterSprites[h].setup(BG_WIDTH / 2 - (ExtractSheet.WIDTH/2)
					, (BG_HEIGHT / 3), 38, 58, 5, 5, current);	

		}
		return dusterSprites;
	}

	/**
	 * Helper method for init()
	 * Make a list of FastSprite objects that store a different frame/pose
	 * from the imported BufferedImage
	 * Use a for loop to instantiate a temp FastSprite in spot in the array,
	 * set up that temp FastSprite, and then move on to the next slot and
	 * repeat until all the slots are setup
	 * @return kumaSprites - all of Kumatora's poses
	 */
	public FastSprite[] setupKuma(){
		FastSprite[] kumaSprites = new FastSprite[9];
		BufferedImage current = null;	//The current frame in animation getSingleSprite is going to extract


		for(int h = 0; h < kumaSprites.length; h++){

			kumaSprites[h] = new FastSprite();	//make a temp sprite to be put in array

			//get the correct image
			current = ss.getSingleSprite(h + 1 ,3 ,ExtractSheet.WIDTH ,ExtractSheet.HEIGHT);	

			//set up the temp sprite with the grabbed image
			kumaSprites[h].setup(BG_WIDTH  * 3 / 4 - (ExtractSheet.WIDTH/2)
					, (BG_HEIGHT / 3), 38, 58, 5, 5, current);

		}
		return kumaSprites;

	}

	/**
	 * Paints a message on the canvas with desired
	 * message, position, color, size, and font
	 * @param pane the instance of Graphics we use to paint
	 * @param msg	The message to print out
	 * @param x 	x Position of the message
	 * @param y 	y Position of the message
	 * @param c	 Color of the message
	 * @param s		Size of the message
	 * @param font The font of the message
	 */
	public void paintLetters(Graphics pane, 
			String msg, 	//message
			int x, int y,	//position
			Color c,		//color
			int s,			//size
			String font){	//font

		pane.setColor(c);
		pane.setFont(new Font(font, Font.PLAIN, s));
		pane.drawString(msg, x, y);
	}

	/**
	 * Runs the Sprites' isInside method in order to determine whether
	 * or not to run the Sprites' run methods, which changes the location
	 * of the Sprite. Also increments the lucas/duster/kuma counters if isInside
	 * in order to change the index looked at of the lucas/duster/kumaPoses arrays
	 * to change the pose that is drawn
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if(lucasIdle.isInside(mouseX, mouseY)){

			lucasIdle.run();	//move the RegSprite

			/*
			 * If the count exceeds the number of Poses, reset the count
			 * so that the first pose is shown again
			 */
			if(lucasCount < lucasPoses.length - 1){
				lucasCount++;
			}
			else{
				lucasCount = 0;
			}
			repaint();

		}

		if(dusterIdle.isInside(mouseX, mouseY)){

			dusterIdle.run();	//move the LazySprite

			/*
			 * If the count exceeds the number of Poses, reset the count
			 * so that the first pose is shown again
			 */
			if(dusterCount < dusterPoses.length - 1){
				dusterCount++;
			}
			else{
				dusterCount = 0;
			}
			repaint();


		}

		if(kumaIdle.isInside(mouseX, mouseY)){

			kumaIdle.run();		//move the FastSprite

			/*
			 * If the count exceeds the number of Poses, reset the count
			 * so that the first pose is shown again
			 */
			if(kumaCount < kumaPoses.length - 1){
				kumaCount++;
			}
			else{
				kumaCount = 0;
			}
			repaint();

		}


	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	/**
	 * Will be re-run every time the user clicks on
	 * a Sprite, prints the message using paintLetters and
	 * paints the Idle sprites which are mutated when the mouse
	 * click isInside
	 */
	public void paint(Graphics pane){

		/*
		 * Prints
		 * MOTHER 3
		 * Sprite Game
		 * Click on a sprite! It will change its pose!
		 */
		paintLetters(pane, "MOTHER 3", 
				95, 360, 
				Color.WHITE, 
				40, 
				"Papyrus");
		paintLetters(pane, "Sprite Game", 
				150, 380, 
				Color.WHITE, 
				20, 
				"Papyrus");
		paintLetters(pane, "Click on a sprite! It will change its pose!", 
				75, 395, 
				Color.WHITE, 
				15, 
				"Papyrus");


		// Draw the 3 Idles, but take in the pose at index Count as the drawn image
		pane.drawImage(lucasPoses[lucasCount].f,lucasIdle.x,lucasIdle.y,this);
		pane.drawImage(dusterPoses[dusterCount].f,dusterIdle.x,dusterIdle.y,this);
		pane.drawImage(kumaPoses[kumaCount].f,kumaIdle.x,kumaIdle.y,this);


	}

	/**
	 * Sets Field as the main method
	 * @param args	array of console line argument of data type is String
	 */
	public static void main(String args[]){
		new Field();
	}
}
