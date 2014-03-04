package map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import player.Mule;

/**
 * one type of tile
 * 
 * @author Ziyi Jiang
 * 
 */
public class Mountain3 extends BasicTyle {
	private final String imageDir = "/resources/images/tiles/mt3.png";
	private final String spriteDir = "/resources/images/tiles/mt3Sprite.png";

	/**
	 * constructor
	 */
	public Mountain3() {
		imgDir = imageDir;
		super.spriteDir = spriteDir;
		super.aniExists = true;
	}

	@Override
	/**
	 * getter for Food produced by the tile
	 */
	public int getFoodProduction() {
		if ((m != null) && (m.getType() == Mule.FOOD)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	/**
	 * getter for ore produced by the tile
	 */
	public int getOreProduction() {
		if ((m != null) && (m.getType() == Mule.ORE)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 4;
		} else {
			return 0;
		}
	}

	@Override
	/**
	 * getter for Energy produced by the tile
	 */
	public int getEnergyProduction() {
		if ((m != null) && (m.getType() == Mule.ENERGY)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 1;
		} else {
			return 0;
		}
	}

}
