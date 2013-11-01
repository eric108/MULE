import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

public class Store {
	private final String imageDir = "/resources/images/town/store.png";
	private static Image img;
	Button cancelB = new Button(630, 540, 70, 40, "Cancel");
	Button submitB = new Button(710, 540, 70, 40, "Submit");
	Button buyFMule = new Button(110, 540, 135, 40, "Buy Food Mule");
	Button buyEMule = new Button(250, 540, 155, 40, "Buy Energy Mule");
	Button buyOMule = new Button(410, 540, 135, 40, "Buy Ore Mule");

	// String goldStr = "Gold: ";
	String foodStr = "Food: ";
	String energyStr = "Energy: ";
	String oreStr = "Ore: ";
	String ownStr = "Own";
	String buyStr = "Buying";
	String sellStr = "Selling";
	int currGold;
	int currEnergy;
	int currFood;
	int currOre;

	int buyFood;
	int buyEnergy;
	int buyOre;
	int sellFood;
	int sellEnergy;
	int sellOre;

	int buyTotal;
	int sellTotal;
	private int stockFood;
	private int stockEnergy;
	private int stockOre;
	boolean badInput = false;

	// TextField tfg1;
	// TextField tfg2;
	TextField tfg3;
	TextField tff1;
	TextField tff2;
	TextField tff3;
	TextField tfe1;
	TextField tfe2;
	TextField tfe3;
	TextField tfo1;
	TextField tfo2;
	TextField tfo3;
	
	Font categoryFont = new Font("Impact", Font.PLAIN, 30);
	TrueTypeFont h2 = new TrueTypeFont(categoryFont, true);

	Font categoryFont2 = new Font("Impact", Font.PLAIN, 20);
	TrueTypeFont h3 = new TrueTypeFont(categoryFont2, true);

	private final int X = 0;
	private final int Y = 200;
	private final int W = 200;
	private final int H = 200;

	public Store(GameContainer gc) {

		// tfg1 = new TextField(gc, gc.getDefaultFont(), 420, 145, 30, 25);
		// tfg2 = new TextField(gc, gc.getDefaultFont(), 570, 145, 30, 25);

		tff1 = new TextField(gc, gc.getDefaultFont(), 420, 193, 30, 25);
		tff2 = new TextField(gc, gc.getDefaultFont(), 570, 193, 30, 25);

		tfe1 = new TextField(gc, gc.getDefaultFont(), 420, 293, 30, 25);
		tfe2 = new TextField(gc, gc.getDefaultFont(), 570, 293, 30, 25);

		tfo1 = new TextField(gc, gc.getDefaultFont(), 420, 393, 30, 25);
		tfo2 = new TextField(gc, gc.getDefaultFont(), 570, 393, 30, 25);

		// tfg1.setBackgroundColor(Color.darkGray);
		// tfg1.setTextColor(Color.white);

		// tfg2.setBackgroundColor(Color.darkGray);
		// tfg2.setTextColor(Color.white);

		tff1.setBackgroundColor(Color.darkGray);
		tff1.setTextColor(Color.white);
		tff1.setText("0");

		tff2.setBackgroundColor(Color.darkGray);
		tff2.setTextColor(Color.white);
		tff2.setText("0");

		tfe1.setBackgroundColor(Color.darkGray);
		tfe1.setTextColor(Color.white);
		tfe1.setText("0");

		tfe2.setBackgroundColor(Color.darkGray);
		tfe2.setTextColor(Color.white);
		tfe2.setText("0");

		tfo1.setBackgroundColor(Color.darkGray);
		tfo1.setTextColor(Color.white);
		tfo1.setText("0");

		tfo2.setBackgroundColor(Color.darkGray);
		tfo2.setTextColor(Color.white);
		tfo2.setText("0");
		
		try {

			if (img == null)
				img = new Image(imageDir);

		} catch (Exception ex) {
			// TODO: implement exception handling

			System.out.println(ex.toString());
		}
	}

	public Image getImage() {
		return img;
	}
	public  void setStockResources(){
		if (GameData.getDifficulty().equals("Easy")){
			stockFood = 16;
			stockEnergy = 16;
			stockOre = 0;
		}else if(GameData.getDifficulty().equals("Standard") 
				|| GameData.getDifficulty().equals("Hard") ){
			stockFood = 8;
			stockEnergy = 8;
			stockOre = 8;
		}
	} 
	public boolean onclick(GameContainer gc, boolean leftClick) {
		Player currentPlayer = GameData.getActivePlayer();

		try {
			buyTotal = Integer.parseInt(tff1.getText()) * 30
					+ Integer.parseInt(tfe1.getText()) * 25
					+ Integer.parseInt(tfo1.getText()) * 50;
			sellTotal = Integer.parseInt(tff2.getText()) * 30
					+ Integer.parseInt(tfe2.getText()) * 25
					+ Integer.parseInt(tfo2.getText()) * 50;
			badInput = false;
		} catch (Exception e) {
			// ERROR, LETTERS OR SOMETHING IN TEXT BOX
			System.out.println("input error caught");
			badInput = true;
		}
		if(leftClick){
		if (cancelB.checkClick(gc.getInput().getMouseX(), gc.getInput()
				.getMouseY())) {
			// leave store
			System.out.println("click cancle");
			return false;
		}

		if (submitB.checkClick(gc.getInput().getMouseX(), gc.getInput()
				.getMouseY())
				&& !badInput) {
			System.out.println("Submit Button CLicked");
			int goldLeft = currGold + ((-1 * buyTotal) + sellTotal);
			
			if ((goldLeft >= 0) && (Integer.parseInt(tff1.getText())<= stockFood)
					&&(Integer.parseInt(tfe1.getText())<=stockEnergy) 
					&&(Integer.parseInt(tfo1.getText())<=stockOre)) {
				currentPlayer.setGold(goldLeft);
				currentPlayer.boughtFood(Integer.parseInt(tff1.getText()));
				stockFood -= Integer.parseInt(tfe1.getText());
				currentPlayer.boughtEnergy(Integer.parseInt(tfe1.getText()));
				stockEnergy -=Integer.parseInt(tfe1.getText());
				currentPlayer.boughtOre(Integer.parseInt(tfo1.getText()));
				stockOre-=Integer.parseInt(tfo1.getText());
				currentPlayer.soldFood(Integer.parseInt(tff2.getText()));
				stockFood += Integer.parseInt(tfe2.getText());
				currentPlayer.soldEnergy(Integer.parseInt(tfe2.getText()));
				stockEnergy += Integer.parseInt(tfe2.getText());
				currentPlayer.soldOre(Integer.parseInt(tfo2.getText()));
				stockOre +=Integer.parseInt(tfo2.getText());
			}
			return false;
		}
		if (buyFMule.checkClick(gc.getInput().getMouseX(), gc.getInput()
				.getMouseY())) {

			if (GameData.getActivePlayer().deductGold(125)) {

				GameData.getActivePlayer().buyMule(Mule.FOOD); // Food Mule
				return false;
			}
		}
		if (buyEMule.checkClick(gc.getInput().getMouseX(), gc.getInput()
				.getMouseY())) {

			if (GameData.getActivePlayer().deductGold(150)) {
				GameData.getActivePlayer().buyMule(Mule.ENERGY); // Energy Mule
				return false;
			}
		}
		if (buyOMule.checkClick(gc.getInput().getMouseX(), gc.getInput()
				.getMouseY())) {

			if (GameData.getActivePlayer().deductGold(175)) {
				GameData.getActivePlayer().buyMule(Mule.ORE); // Ore mule
				return false;
			}
		}
		}
		return true; // player still in store
		
	}

	public void drawStoreUI(Graphics g, GameContainer gc) {
		Player currentPlayer = GameData.getActivePlayer();

		if (currentPlayer.hasMule()) {
			buyFMule.disable();
			buyEMule.disable();
			buyOMule.disable();
		}

		currGold = currentPlayer.getGold();
		currFood = currentPlayer.getFood();
		currEnergy = currentPlayer.getEnergy();
		currOre = currentPlayer.getOre();

		g.setColor(Color.white);
		g.drawRect(0, 0, 800, 600);
		g.setColor(Color.blue);
		g.setFont(h2);
		g.drawString("Welcome to the Store!", 275, 80);

		cancelB.drawButton(g);
		submitB.drawButton(g);
		buyFMule.drawButton(g);
		buyEMule.drawButton(g);
		buyOMule.drawButton(g);
		g.setColor(Color.blue); // color changes in draw button
		g.setFont(h3);
		// g.drawString(goldStr, 100, 135);
		g.drawString(foodStr, 140, 195);
		g.drawString(energyStr, 140, 295);
		g.drawString(oreStr, 140, 395);
		g.drawString(ownStr, 257, 150);
		g.drawString(buyStr, 407, 150);
		g.drawString(sellStr, 557, 150);
		g.drawRect(140, 435, 500, 1);

		g.drawString("Total Cost: ", 140, 460);

		if (!badInput) {
			g.drawString("-" + buyTotal + " Gold", 407, 460);
			g.drawString("+" + sellTotal + " Gold", 557, 460);
		} else {
			g.setColor(Color.black);
			g.drawRect(407, 510, 300, 30);
			g.setColor(Color.blue);
		}

		// g.drawString("Your Gold: " + currGold, 140, 500);

		// g.drawString(currGold + "", 267, 135);
		g.drawString(currFood + "", 267, 195);
		g.drawString(currEnergy + "", 267, 295);
		g.drawString(currOre + "", 267, 395);

		// tfg1.render(gc, g);
		// tfg2.render(gc, g);
		tff1.render(gc, g);
		tff2.render(gc, g);
		tfe1.render(gc, g);
		tfe2.render(gc, g);
		tfo1.render(gc, g);
		tfo2.render(gc, g);
		g.drawString(" / "+ stockFood, 450, 193);
		g.drawString(" / "+ stockEnergy, 450, 293);
		g.drawString(" / "+ stockOre, 450, 393);
	}

	public boolean onStore(int pX, int pY) {
		return ((pX >= X) && (pX <= (X + W)) && (pY >= Y) && (pY <= (Y + H)));
	}

	public void draw(Graphics g) {
		this.img.draw(X, Y, W, H);
	}

}
