package map;

import org.newdawn.slick.Image;

import player.Mule;

/**
 * one type of tile
 * 
 * @author Ziyi Jiang
 * 
 */
public class Mountain2 extends BasicTyle {
	private final String imageDir = "/resources/images/tiles/mt2.png";
	private final String spriteDir = "/resources/images/tiles/mt2Sprite.png";

	/**
	 * constructor
	 */

	public Mountain2() {
		imgDir = imageDir;
		super.spriteDir = spriteDir;
		super.aniExists = true;
	}

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

	/**
	 * getter for Ore produced by the tile
	 */
	public int getOreProduction() {
		if ((m != null) && (m.getType() == Mule.ORE)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 3;
		} else {
			return 0;
		}
	}
}
