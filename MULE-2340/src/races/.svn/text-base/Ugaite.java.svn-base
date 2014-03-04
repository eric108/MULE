package races;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Ugaite implements Race {

	private final String imageDir = "/resources/images/races/uga.png";
	private static Image img;
	private static SpriteSheet sprite;
	private static Animation animation;
	private final String spriteDir = "/resources/images/races/ugaSprite.png";
	private final String ugaite = "ugaite";

	/**
	 * Constructor Method
	 */
	public Ugaite() {

	}

	/**
	 * getter for the races image
	 */
	public Image getStillImage() {
		try {
			if (img == null)
				img = new Image(imageDir);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return img;
	}

	/**
	 * getter for the animation of the race
	 */
	public Animation getAnimation() {
		try {
			if (sprite == null) {
				sprite = new SpriteSheet(spriteDir, 80, 80);
				animation = new Animation(sprite, 240);
			}
		} catch (Exception ex) {
			// TODO: implement exception handling
			System.out.println(ex.toString());
		}
		return animation;// img;
	}

}
