import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;


public class Human implements Race {

	private final String imageDir = "/resources/images/races/human.png";
	private static Image img;
	private static SpriteSheet sprite;
	private Animation animation;
	private final String spriteDir = "/resources/images/races/humanSprite.png";
	
	public Human(){
		try{
			if(img == null) img = new Image(imageDir);
			if(sprite == null) sprite = new SpriteSheet(spriteDir,80,80);
			animation = new Animation(sprite,240);
		}
		catch(Exception ex){
			//TODO: implement exception handling
			System.out.println(ex.toString());
		}
	}
	public Image getStillImage() {
		return img;
	}
	public Animation getAnimation() {
		return animation;//img;
	}
}
