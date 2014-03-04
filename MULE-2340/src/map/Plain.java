package map;

import org.newdawn.slick.Image;

import player.Mule;

/**
 * one type of tile
 * 
 * @author Ziyi Jiang
 * 
 */
public class Plain extends BasicTyle {
	private final String imageDir = "/resources/images/tiles/grass.png";

	/**
	 * constructor
	 */
	public Plain() {
		imgDir = imageDir;
	}

	@Override
	/**
	 * getter for food produced by the tile
	 */
	public int getFoodProduction() {
		if ((m != null) && (m.getType() == Mule.FOOD)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 2;
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
			return 1;
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
			return 3;
		} else {
			return 0;
		}
	}

}
