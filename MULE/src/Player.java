import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * 
 * @author Ziyi
 * 
 */
public class Player {

	Race race;
	Resources resources;
	private LinkedList<Mule> mules;
	String image;
	String name;
	org.newdawn.slick.Color color;
	boolean inTown;
	int score;
	final int HEIGHT=60;
	final int WIDTH=60;
	int LocX;
	int LocY;
	boolean muleEquipped;

	String gameDiff;

	public Player() {
		gameDiff = GameData.getDifficulty();
		resources = new Resources();
		LocX=370;
		LocY=380;

		if (gameDiff == "Hard")
			resources.setHard();
		else if (gameDiff == "Standard")
			resources.setStandard();
		else
			resources.setEasy();
		
		mules = new LinkedList();
		muleEquipped = false;
	}
	
	public void setInTown(boolean x)
	{
		inTown=x;
	}
	/**
	 * reset players starting location	
	 */
	public void resetLocationMain()
	{
		LocX=370;
		LocY=380;
	}

	public void resetLocationTown()
	{
		LocX=380;
		LocY=500;
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

	/**
	 * getter for resources
	 * 
	 * @return
	 */
	public Resources getResources() {
		return resources;
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

	/**
	 * setter for mule
	 * 
	 * @param m
	 */

	public int getScore() {
		score = (resources.getEnergy() + resources.getOre()
				+ resources.getFood() + resources.getGold()) * 100;
		return score;
	}

	public Image getImage() {
		return race.getImage();
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

	public int getOre() {
		return resources.getOre();
	}

	public void setGold(int total) {
		resources.setGold(total);
	}

	public void boughtFood(int bought) {
		resources.setFood(getFood() + bought);
	}

	public void boughtEnergy(int bought) {
		resources.setEnergy(getEnergy() + bought);
	}

	public void boughtOre(int bought) {
		resources.setOre(getOre() + bought);
	}

	public boolean soldFood(int sold) {

		if ((getFood() - sold) >= 0) {
			resources.setFood(getFood() - sold);
		} else {
			return false;
		}
		return true;
	}

	public boolean soldEnergy(int sold) {
		if ((getEnergy() - sold) >= 0) {
			resources.setEnergy(getEnergy() - sold);
		} else {
			return false;
		}
		return true;
	}

	public boolean soldOre(int sold) {
		if ((getOre() - sold) >= 0) {
			resources.setOre(getOre() - sold);
		} else {
			return false;
		}
		return true;
	}
	
	public boolean deductGold(int i) {
		if(getGold() - i >= 0) {
		resources.setGold(getGold()-i);
		return true;
		}
		return false;
	}
	
	public void moveLeft()
	{
		if(LocX>30 & !inTown)
		LocX-=1;
		if(inTown)
		{
			LocX-=1;
		}
		
	}
	public void moveRight()
	{
		if(LocX<715 & !inTown)
		LocX+=1;
		if(inTown)
		{
			LocX+=1;
		}
	}
	public void moveUp()
	{
		if(LocY>130 & !inTown)
		LocY-=1;
		if(inTown)
		{
			LocY-=1;
		}
	}
	public void moveDown()
	{
		if(LocY<475 & !inTown)
		LocY+=1;
		if(inTown)
		{
			LocY+=1;
		}
	}
	public int getX()
	{
		return LocX;
	}
	public int getY()
	{
		return LocY;
	}
	public void buyMule(String type){
		mules.addFirst(new Mule(type, this));
		muleEquipped = true;
	}
	public Mule getLastMule(){
		return mules.getFirst();
	}
	
	public void removeLastMule(){
		mules.removeFirst();
	}
	public void dropMule(Tile currTile){

		if((currTile != null) && (currTile.getOwner() == GameData.getActivePlayer())){
			// set mule
			GameData.getActivePlayer().getLastMule().setTile(currTile);
			GameData.getActivePlayer().muleEquipped = false;
			currTile.setMule(GameData.getActivePlayer().getLastMule());		
		}
		else{
			// mule is lost
			GameData.getActivePlayer().removeLastMule();
			GameData.getActivePlayer().muleEquipped = false;
		}
	}
	
	public Point getCenterOfPlayer()
	{
		Point x = new Point(LocX+WIDTH/2,LocY+HEIGHT/2);
		return x;
	}
	
	public int getCentX(){
		return getCenterOfPlayer().x;
	}
	public int getCentY(){
		return getCenterOfPlayer().y;
	}
	
	public void draw(Graphics g)
	{
		race.getImage().draw(LocX,LocY, WIDTH, HEIGHT);
	}
	
	public void addPlayerRoundProd(){
		for(Tile t : GameData.map.getPlayerTiles(this)){
			resources.setOre(resources.getOre() + t.getOreProduction());
			resources.setEnergy(resources.getEnergy() + t.getEnergyProduction());
			resources.setFood(resources.getFood() + t.getFoodProduction());
		}
	}
	
	public void drawResources(Graphics g){
		g.drawRect(40, 10, 200, 125);
		// draw active player attributes
		g.drawString("Score:", 60, 13);
		g.drawString(Integer.toString(score), 150, 13);
		g.drawString("Gold:", 60, 33);
		g.drawString(Integer.toString(resources.gold),  150, 33);
		g.drawString("Ore:", 60, 53);
		g.drawString(Integer.toString(resources.ore), 150, 53);
		g.drawString("Food:", 60, 73);
		g.drawString(Integer.toString(resources.food), 150, 73);
		g.drawString("Energy", 60, 93);
		g.drawString(Integer.toString(resources.energy), 150, 93);
	}

	
}
