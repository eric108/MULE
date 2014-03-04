package states;

import game.EventHandler;
import game.GameData;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ui_components.Button;

/**
 * @version 1
 * @author Bedford Peterson
 * 
 *         int playerCount represents the number of players chosen. String
 *         difficulty represents the difficulty chosen. boolean randomMap
 *         represents if a random map is desired.
 * 
 */

public class GameOptions extends BasicGameState {

	/*
	 * 
	 * Initialization for state variables
	 */
	private final int id;
	int playerCount, x, y;
	String difficulty, rand;
	boolean randomMap;
	private Button upPC, downPC, upDif, downDif, randBox, nextState;

	private Font titleFont;
	private TrueTypeFont tTitleFont;
	private Font turnFont;
	private TrueTypeFont h1;
	private Font categoryFont;
	private TrueTypeFont h2;
	private Font teamIndex;
	private TrueTypeFont h3;

	public static GameData gd;
	public static EventHandler eh;
	private String imageDir = "/resources/images/town/titleImg.png";
	private Image img;

	public GameOptions(int id) {
		this.id = id;
	}

	/**
	 * initializes all variables and objects
	 * 
	 * @param arg0
	 * @param arg1
	 * @throws SlickException
	 * 
	 *             Initializes buttons and values
	 */
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

		img = new Image(imageDir);
		upPC = new Button(500, 100, 30, 30, "+");
		downPC = new Button(500, 150, 30, 30, "-");
		upDif = new Button(500, 275, 30, 30, "+");
		downDif = new Button(500, 325, 30, 30, "-");
		randBox = new Button(500, 488, 30, 30, "x");
		nextState = new Button(650, 510, 100, 60, "Next");

		playerCount = 2;
		difficulty = "Standard";
		randomMap = false;
		rand = "no";

		titleFont = new Font("Arial", Font.PLAIN, 15);
		tTitleFont = new TrueTypeFont(titleFont, true);
		turnFont = new Font("Impact", Font.BOLD, 40);
		h1 = new TrueTypeFont(turnFont, true);
		categoryFont = new Font("Impact", Font.PLAIN, 60);
		h2 = new TrueTypeFont(categoryFont, true);
		teamIndex = new Font("Arial", Font.PLAIN, 25);
		h3 = new TrueTypeFont(teamIndex, true);

	}

	/**
	 * Update loop that will be called constantly. Use this for things you
	 * always want to be checking or running in this state.
	 * 
	 * @param container
	 * @param sbg
	 * @param arg2
	 * @throws SlickException
	 * 
	 **/
	public void update(GameContainer container, StateBasedGame sbg, int arg2)
			throws SlickException {

		Input input = container.getInput();
		x = input.getMouseX();
		y = input.getMouseY();

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			System.out.println("Clockclcick");
			// Player Count
			if (upPC.checkClick(container.getInput().getMouseX(), container
					.getInput().getMouseY())
					&& (playerCount < 4))
				playerCount++;
			else if (downPC.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY()) && (playerCount > 2))
				playerCount--;

			// Difficulties
			if (upDif.checkClick(container.getInput().getMouseX(), container
					.getInput().getMouseY())
					&& (difficulty.equals("Easy")))
				difficulty = "Standard";
			else if (upDif.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())
					&& (difficulty.equals("Standard")))
				difficulty = "Hard";
			else if (downDif.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())
					&& (difficulty.equals("Standard")))
				difficulty = "Easy";
			else if (downDif.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())
					&& (difficulty.equals("Hard")))
				difficulty = "Standard";

			// Random Map
			if (randBox.checkClick(container.getInput().getMouseX(), container
					.getInput().getMouseY())
					&& !(randomMap)) { // not selected
				randomMap = true;
				rand = "yes";
			} else if (randBox.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY()) && (randomMap)) {// selected
				randomMap = false;
				rand = "no";
			}

			// Next
			if (nextState.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())) {
				GameData.getInstance().init(playerCount, randomMap, difficulty);
				eh = new EventHandler();
				sbg.enterState(getID() + 1);
			}

		}
	}

	/**
	 * @param gc
	 * @param arg1
	 * @param g
	 * @throws SlickException
	 * 
	 *             Used to generate the graphics and what to display.
	 * 
	 **/
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {

		// background
		g.drawImage(img, 0, 0);
		// g.setColor(Color.blue);
		// g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		// title panel
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, gc.getWidth(), 50);
		// title label
		g.setColor(Color.white);
		g.setFont(tTitleFont);
		g.drawString("Game Options", ((gc.getWidth() / 2) - 50), 25);

		// Player Count
		g.setColor(Color.orange);
		g.setFont(h1);
		g.drawString("Player Count:", 100, 100);

		// Difficulty
		g.setColor(Color.orange);
		g.setFont(h1);
		g.drawString("Difficulty:", 100, 275);

		// Random Map
		g.setColor(Color.orange);
		g.setFont(h1);
		g.drawString("Random Map:", 100, 475);

		// up for Player Count
		g.setColor(Color.white);
		g.fill(upPC.getButtonShape());
		g.setColor(Color.blue);
		g.drawString("+", 503, 90);

		// down for Player Count
		g.setColor(Color.white);
		g.fill(downPC.getButtonShape());
		g.setColor(Color.blue);
		g.drawString("-", 506, 139);

		// player count
		g.setColor(Color.white);
		g.drawString(playerCount + "", 450, 103);

		// up for Difficulty
		g.setColor(Color.white);
		g.fill(upDif.getButtonShape());
		g.setColor(Color.blue);
		g.drawString("+", 503, 265);

		// down for Difficulty
		g.setColor(Color.white);
		g.fill(downDif.getButtonShape());
		g.setColor(Color.blue);
		g.drawString("-", 506, 314);

		// difficulty
		g.setColor(Color.white);
		g.drawString(difficulty, 300, 278);

		// toggle for Random Map
		g.setColor(Color.white);
		g.fill(randBox.getButtonShape());
		g.setColor(Color.blue);
		g.drawString("x", 505, 475);
		g.setColor(Color.white);
		g.drawString(rand, 405, 475);

		// next button
		g.setColor(Color.white);
		g.fill(nextState.getButtonShape());
		g.setColor(Color.blue);
		g.drawString(nextState.getButtonName(), 660, 515);

	}

	/**
	 * unique state identifier that is used to determined if the state will be
	 * displayed
	 */
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
