package player;

/**
 * This class hold all the resources in the game
 * 
 * @author Ziyi Jiang
 * 
 */
public class Resources implements java.io.Serializable {

	int gold;
	int ore;
	int energy;
	int food;

	/**
	 * getter for gold
	 * 
	 * @return gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * getter for ore
	 * 
	 * @return ore
	 */
	public int getOre() {
		return ore;
	}

	/**
	 * getter for Energy
	 * 
	 * @return energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * getter for food
	 * 
	 * @return food
	 */
	public int getFood() {
		return food;
	}

	/**
	 * setter for gold
	 * 
	 * @param i
	 *            the amount you want to set the gold to
	 */
	public void setGold(int i) {
		gold = i;
	}

	/**
	 * sets starting resources for easy difficulty
	 */
	public void setEasy() {
		food = 8;
		energy = 4;
		ore = 0;
		gold = 1000;
	}

	/**
	 * sets starting resources for standard difficulty
	 */
	public void setStandard() {
		food = 4;
		energy = 2;
		ore = 0;
		gold = 1000;
	}

	/**
	 * sets starting resources for hard difficulty
	 */
	public void setHard() {
		food = 4;
		energy = 2;
		ore = 0;
		gold = 1000;
	}

	/**
	 * setter for food
	 * 
	 * @param i
	 *            the amount of food
	 */
	public void setFood(int i) {
		food = i;

	}

	/**
	 * setter for energy
	 * 
	 * @param i
	 *            the amount of energy
	 */
	public void setEnergy(int i) {
		energy = i;

	}

	/**
	 * setter for ore
	 * 
	 * @param i
	 *            the amount of ore
	 */
	public void setOre(int i) {
		ore = i;

	}
}
