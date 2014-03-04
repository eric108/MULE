package races;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Buzzite implements Race {

	private final String imageDir = "/resources/images/races/buzzite.png";
	private static Image img;
	private static SpriteSheet sprite;
	private static Animation animation;
	private final String spriteDir = "/resources/images/races/buzzSprite.png";
	private final String buzzite = "buzzite";

	/**
	 * Constructor Method
	 */
	public Buzzite() {

	}

	/**
	 * getter for the races image
	 */
	public Image getStillImage() {
		try {
			if (img == null) {
				img = new Image(imageDir);
				System.out.println("creating new image buzz");
			}
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
				System.out.println("creating new ani buzz");
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
