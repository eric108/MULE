package player;

import game.GameData;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

import map.Tile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import races.Flapper;
import races.Human;
import races.Race;

/**
 * 
 * @author Brandon Duarte
 * 
 */

public class Player implements java.io.Serializable {

	Race race;
	private Resources resources;
	private LinkedList<Mule> mules;
	String image;
	String name;
	private org.newdawn.slick.Color color;
	boolean inTown;
	int score;
	final int HEIGHT = 60;
	final int WIDTH = 60;
	int LocX;
	int LocY;
	public boolean muleEquipped;

	String gameDiff;

	public Player() {
		gameDiff = GameData.getInstance().getDifficulty();
		resources = new Resources();
		LocX = 370;
		LocY = 380;

		if (gameDiff == "Hard")
			resources.setHard();
		else if (gameDiff == "Standard")
			resources.setStandard();
		else
			resources.setEasy();

		mules = new LinkedList();
		muleEquipped = false;
	}

	public void setInTown(boolean x) {
		inTown = x;
	}

	/**
	 * reset players starting location
	 */
	public void resetLocationMain() {
		LocX = 370;
		LocY = 380;
	}

	public void resetLocationTown() {
		LocX = 380;
		LocY = 500;
	}

	/**
	 * getter for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name
	 * 
	 * @param s
	 */
	public void setName(String s) {
		name = s;
	}

	public Resources getResources() {
		return resources;
	}

	/**
	 * setter for race
	 * 
	 * @param r
	 */
	public void setRace(Race r) {
		race = r;
		if (r instanceof Human)
			resources.setGold(600);
		else if (r instanceof Flapper)
			resources.setGold(1600);

	}

	/**
	 * setter for color
	 * 
	 * @param c
	 */
	public void setColor(org.newdawn.slick.Color c) {
		color = c;
	}

	public org.newdawn.slick.Color getColor() {
		return color;
	}

	/**
	 * checks if player owns a mule
	 * 
	 * @return true if player has a mule
	 */
	public boolean hasMule() {
		if (mules.size() == 0)
			return false;
		return true;
	}

	public boolean returnMuleEquipped() {
		return muleEquipped;
	}

	/**
	 * setter for mule
	 * 
	 * @param m
	 */

	public int getScore() {
		score = (resources.getEnergy() + resources.getOre()
				+ resources.getFood() + resources.getGold());
		return score;
	}

	public Image getStillImage() {
		return race.getStillImage();
	}

	public Animation getAnimation() {
		return race.getAnimation();
	}

	public int getGold() {
		return resources.getGold();
	}

	public int getFood() {
		return resources.getFood();
	}

	public int getEnergy() {
		return resources.getEnergy();
	}

	public void setEnergy(int energy) {
		resources.energy = energy;
	}

	public int getOre() {
		return resources.getOre();
	}

	public void setOre(int ore) {
		resources.ore = ore;
	}

	public void setGold(int total) {
		resources.setGold(total);
	}

	public void setFood(int total) {
		resources.setFood(total);
	}

	/**
	 * Takes in the number of food the player has bought from the store. Sets
	 * their food count and deducts their gold according to the amount
	 * 
	 * @param bought
	 *            number of food units purchased
	 */
	public void boughtFood(int bought) {
		resources.setFood(getFood() + bought);
		deductGold(bought * 30);
	}

	/**
	 * Takes in the number of energy the player has bought from the store. Sets
	 * their food count and deducts their gold according to the amount
	 * 
	 * @param bought
	 *            number of energy units purchased
	 */
	public void boughtEnergy(int bought) {
		resources.setEnergy(getEnergy() + bought);
		deductGold(bought * 25);
	}

	/**
	 * Takes in the number of ore the player has bought from the store. Sets
	 * their food count and deducts their gold according to the amount
	 * 
	 * @param bought
	 *            number of ore units purchased
	 */
	public void boughtOre(int bought) {
		resources.setOre(getOre() + bought);
		deductGold(bought * 50);
	}

	/**
	 * Takes in the number of food the player has sold to the store. Sets their
	 * food count and deducts their gold according to the amount
	 * 
	 * @param sold
	 *            number of food units purchased
	 * @return If the transactio was successful
	 */
	public boolean soldFood(int sold) {

		if ((getFood() - sold) >= 0 && sold >= 0) {
			resources.setFood(getFood() - sold);
			setGold(getGold() + sold * 30);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Takes in the number of energy the player has sold to the store. Sets
	 * their food count and deducts their gold according to the amount
	 * 
	 * @param sold
	 *            number of energy units purchased
	 * @return If the transaction was successful
	 */
	public boolean soldEnergy(int sold) {
		if ((getEnergy() - sold) >= 0) {
			resources.setEnergy(getEnergy() - sold);
			setGold(getGold() + sold * 25);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Takes in the number of ore the player has sold to the store. Sets their
	 * food count and deducts their gold according to the amount
	 * 
	 * @param sold
	 *            number of ore units purchased
	 * @return If the transaction was successful
	 */
	public boolean soldOre(int sold) {
		if ((getOre() - sold) >= 0) {
			resources.setOre(getOre() - sold);
			setGold(getGold() + sold * 50);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Removes gold from user's count
	 * 
	 * @param i
	 *            Amount of gold to remove
	 * @return If the transaction was successful
	 */
	public boolean deductGold(int i) {
		if (getGold() - i >= 0) {
			resources.setGold(getGold() - i);
			return true;
		}
		return false;
	}

	/**
	 * Moves player left
	 */
	public void moveLeft() {
		if (LocX > 30 & !inTown)
			LocX -= 1;
		if (inTown) {
			LocX -= 1;
		}

	}

	/**
	 * Moves player right
	 */
	public void moveRight() {
		if (LocX < 715 & !inTown)
			LocX += 1;
		if (inTown) {
			LocX += 1;
		}
	}

	/**
	 * Moves player up
	 */
	public void moveUp() {
		if (LocY > 130 & !inTown)
			LocY -= 1;
		if (inTown) {
			LocY -= 1;
		}
	}

	/**
	 * Moves player down
	 */
	public void moveDown() {
		if (LocY < 475 & !inTown)
			LocY += 1;
		if (inTown) {
			LocY += 1;
		}
	}

	public int getX() {
		return LocX;
	}

	public int getY() {
		return LocY;
	}

	/**
	 * Takes in the type of the mule and assigns a mule object to the player
	 * 
	 * @param type
	 *            The type of mule. Food, energy, ore
	 */
	public void buyMule(String type) {
		mules.addFirst(new Mule(type, this));
		muleEquipped = true;
	}

	public Mule getLastMule() {
		return mules.getFirst();
	}

	public void removeLastMule() {
		mules.removeFirst();
	}

	/**
	 * Places the plaer's mule onto a tile if they have one equipped
	 * 
	 * @param currTile
	 *            The tile the player is trying to place the mule on
	 */
	public void dropMule(Tile currTile) {

		if ((currTile != null)
				&& (currTile.getOwner() == GameData.getInstance()
						.getActivePlayer()) && currTile.getMule() == null) {
			// set mule
			GameData.getInstance().getActivePlayer().getLastMule()
					.setTile(currTile);
			GameData.getInstance().getActivePlayer().muleEquipped = false;
			currTile.setMule(GameData.getInstance().getActivePlayer()
					.getLastMule());
		} else {
			// mule is lost
			GameData.getInstance().getActivePlayer().removeLastMule();
			GameData.getInstance().getActivePlayer().muleEquipped = false;
		}
	}

	public Point getCenterOfPlayer() {
		Point x = new Point(LocX + WIDTH / 2, LocY + HEIGHT / 2);
		return x;
	}

	public int getCentX() {
		return getCenterOfPlayer().x;
	}

	public int getCentY() {
		return getCenterOfPlayer().y;
	}

	public void updateSprite(int delta) {
		race.getAnimation().update(delta);
	}

	/**
	 * Draws player on the screen
	 * 
	 * @param g
	 *            Graphics object
	 * @param container
	 *            Current game container
	 */
	public void draw(Graphics g, GameContainer container) {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)
				|| input.isKeyDown(Input.KEY_DOWN)
				|| input.isKeyDown(Input.KEY_UP)) {
			race.getAnimation().draw(LocX, LocY, WIDTH, HEIGHT);
		} else {
			race.getStillImage().draw(LocX, LocY, WIDTH, HEIGHT);
		}
	}

	/**
	 * Calculates the player's production for the round
	 */
	public void addPlayerRoundProd() {
		for (Tile t : GameData.getInstance().map.getPlayerTiles(this)) {
			if (resources.energy > 1) {
				resources.setOre(resources.getOre() + t.getOreProduction());
				resources.setEnergy(resources.getEnergy()
						+ t.getEnergyProduction());
				resources.setFood(resources.getFood() + t.getFoodProduction());
			}
		}
	}

	/**
	 * Draws a player's resources on the screen
	 * 
	 * @param g
	 */
	public void drawResources(Graphics g) {
		g.drawRect(40, 10, 200, 125);
		// draw active player attributes
		g.drawString("Score:", 60, 13);
		g.drawString(Integer.toString(score), 150, 13);
		g.drawString("Gold:", 60, 33);
		g.drawString(Integer.toString(resources.gold), 150, 33);
		g.drawString("Ore:", 60, 53);
		g.drawString(Integer.toString(resources.ore), 150, 53);
		g.drawString("Food:", 60, 73);
		g.drawString(Integer.toString(resources.food), 150, 73);
		g.drawString("Energy", 60, 93);
		g.drawString(Integer.toString(resources.energy), 150, 93);
	}

}
