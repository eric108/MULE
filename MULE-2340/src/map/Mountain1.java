package map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Mule;

/**
 * one type of tile
 * 
 * @author Ziyi Jiang
 * 
 */
public class Mountain1 extends BasicTyle {
	private final String imageDir = "/resources/images/tiles/mt1.png";

	/**
	 * constructor
	 */
	public Mountain1() {
		imgDir = imageDir;
	}

	/**
	 * getter for ore produced by the tile
	 */
	public int getOreProduction() {
		if ((m != null) && (m.getType() == Mule.ORE)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * getter for Food Produced by the tile
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
}
