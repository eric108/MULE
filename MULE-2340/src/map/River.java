package map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import player.Mule;

public class River extends BasicTyle {
	final String imageDir = "/resources/images/tiles/river.png";
	private final String spriteDir = "/resources/images/tiles/riverSprite2.png";

	public River() {
		imgDir = imageDir;
		super.spriteDir = spriteDir;
		super.aniExists = true;
	}

	@Override
	public int getFoodProduction() {
		if ((m != null) && (m.getType() == Mule.FOOD)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 4;
		} else {
			return 0;
		}
	}

	@Override
	public int getOreProduction() {
		return 0;
	}

	@Override
	public int getEnergyProduction() {
		if ((m != null) && (m.getType() == Mule.ENERGY)) {
			owner.setEnergy(owner.getEnergy() - 1);
			return 2;
		} else {
			return 0;
		}
	}

}
