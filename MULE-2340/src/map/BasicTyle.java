package map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import player.Mule;
import player.Player;

/**
 * abstract Tile class
 * 
 * @author Ziyi Jiang
 * 
 */
public abstract class BasicTyle implements Tile {
	private int x, y, size;
	public Player owner;
	public String imgDir;
	protected String spriteDir;
	private transient Image img;
	private transient Animation ani;
	private transient SpriteSheet sprite;
	protected Mule m;
	protected boolean aniExists;

	/**
	 * setter for X coordinate of tile
	 */
	public void setX(int xCoord) {
		x = xCoord;
	}

	/**
	 * getter for x location of tile
	 */
	public int getX() {
		return x;
	}

	/**
	 * setter for Y coordinate of tile
	 */
	public void setY(int yCoord) {
		y = yCoord;

	}

	/**
	 * getter for Y coordinate of tile
	 */
	public int getY() {
		return y;
	}

	/**
	 * setter for size
	 * 
	 * @param tileSize
	 */
	public void setSize(int tileSize) {
		size = tileSize;
	}

	/**
	 * getter for size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * checks to see if tile is clicked
	 * 
	 * @param mX
	 *            the X coordinate of the click
	 * @param mY
	 *            the Y coordiante of the click
	 * @return boolean whether the tile was clicked
	 */
	public boolean isClicked(int mX, int mY) {
		return ((mX >= x) && (mX <= (x + size)) && (mY >= y) && (mY <= (y + size)));
	}

	/**
	 * setter for owner of the tile
	 * 
	 * @param Player
	 */
	public void setOwner(Player p) {
		owner = p;
	}

	/**
	 * getter for Owner of tile
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * getter for tile Image
	 */
	public Image getImage() {
		return img;
	}

	/**
	 * getter for animation
	 * 
	 * @return ani
	 */
	public Animation getAnimation() {
		return ani;
	}

	/**
	 * setter for Tile Image
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		img = image;
	}

	/**
	 * setter for Animation
	 * 
	 * @param animation
	 * @param isSet
	 */
	public void setAnimation(Animation animation, boolean isSet) {
		ani = animation;
		aniExists = isSet;
	}

	/**
	 * setter for Mule
	 */
	public void setMule(Mule m) {
		this.m = m;
	}

	/**
	 * getter for mule
	 */
	public Mule getMule() {
		return m;
	}

	/**
	 * draw method for the tile
	 */
	public void draw(Graphics g) {
		try {

			if (getImage() == null)
				setImage(new Image(imgDir));
			if (aniExists) {
				if (sprite == null) {
					sprite = new SpriteSheet(spriteDir, 538, 455);
					if (spriteDir == "/resources/images/tiles/riverSprite2.png") {
						ani = new Animation(sprite, 2690);
						ani.setSpeed(6);
					} else {
						ani = new Animation(sprite, 1603);
						ani.setSpeed(3);
					}
					// animation.setPingPong(true);
					setAnimation(ani, true);
				}

			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		if (owner == null) {
			if (!aniExists) {
				// just draw the image in the tiles normal location
				img.draw(x, y, size, size);
			} else
				ani.draw(x, y, size, size);
		} else {
			// add owners color around edges of tile
			g.setColor(owner.getColor());
			g.fillRect(x, y, size, size);
			if (!aniExists)
				img.draw(x + 7, y + 7, size - 15, size - 15);
			else
				ani.draw(x + 7, y + 7, size - 15, size - 15);
		}
		if (m != null) {
			m.drawMule(g);
		}
	}
}
