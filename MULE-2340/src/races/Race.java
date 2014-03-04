package races;

import java.io.Serializable;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import com.google.gson.InstanceCreator;

/**
 * interface class for all of the races
 * 
 * @author Ziyi Jiang
 * 
 */
public interface Race extends Serializable {

	public Animation getAnimation();

	public Image getStillImage();

}
