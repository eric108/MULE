package map;

import game.GameData;

import java.util.Random;
import java.util.Timer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * 
 * @author Brandon
 * 
 */
public class Pub {
	private final String imageDir = "/resources/images/town/pub1.png";
	private static Image img;
	private static int gamble;
	private static boolean ifGamble;
	private final int X = 200;
	private final int Y = 200;
	private final int W = 200;
	private final int H = 200;
	private static int roundBonus;

	/**
	 * Constructor
	 */
	public Pub() {
		try {
			if (img == null)
				img = new Image(imageDir);
			ifGamble = false;
			gamble = 0;
		} catch (Exception ex) {
			// TODO: implement exception handling
			System.out.println(ex.toString());
		}
	}

	/**
	 * getter for pubs image
	 * 
	 * @return img
	 */
	public Image getImage() {
		return img;
	}

	/**
	 * 
	 * @param i
	 */
	public static void onClick(int i) {
		Random rand = new Random(100);

		switch (GameData.getInstance().getCurrRound()) {
		case (0):
			roundBonus = 50;
			break;
		case (3):
			roundBonus = 100;
			break;
		case (7):
			roundBonus = 150;
			break;
		case (11):
			roundBonus = 200;
			break;
		default:
			roundBonus = 50;
			break;
		}
		if (i <= 50 && i > 37) {
			gamble = rand.nextInt(200);
		} else if (i <= 37 && i > 25) {
			gamble = rand.nextInt(150);
		} else if (i <= 25 && i > 12) {
			gamble = rand.nextInt(100);
		} else {
			gamble = rand.nextInt(50);
		}

		gamble = roundBonus * gamble;
		System.out.println("round bonus: " + roundBonus);
		System.out.println("gamble: " + gamble);
		System.out.println("before gamble added: "
				+ GameData.getInstance().getActivePlayer().getGold());
		int gold = GameData.getInstance().getActivePlayer().getGold();
		GameData.getInstance().getActivePlayer().setGold(gold + gamble);
		System.out.println("after gamble added: "
				+ GameData.getInstance().getActivePlayer().getGold());

		setIfGamble(true);
		System.out.println("finish gambling");

		// stop the current timer
		GameData.getInstance().getTimer().cancel();
		GameData.getInstance().nextPlayer();
		System.out.println("playChange: "
				+ GameData.getInstance().getPlayerChange());

		// new the timer again, or the timer cannot work by just .schedule
		GameData.getInstance().setTimer(new Timer());
		GameData.getInstance().setPlayerChange(true);
		// keep running the round
		GameData.getInstance().startRound();
	}

	/**
	 * getter for ifGamble
	 * 
	 * @return ifGamble
	 */
	public static boolean getIfGamble() {
		return ifGamble;
	}

	/**
	 * setter for ifGamble
	 * 
	 * @param ifGamble
	 */
	public static void setIfGamble(boolean ifGamble) {
		Pub.ifGamble = ifGamble;
	}

	/**
	 * Checks to see if you click on the pub
	 * 
	 * @param pX
	 * @param pY
	 * @return boolean
	 */
	public boolean onPub(int pX, int pY) {
		return ((pX >= X) && (pX <= (X + W)) && (pY >= Y) && (pY <= (Y + H)));
	}

	/**
	 * draw method for pub
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		this.img.draw(X, Y, W, H);
	}

}
