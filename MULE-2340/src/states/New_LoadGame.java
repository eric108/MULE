package states;

import game.GameData;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ui_components.Button;

/**
 * 
 * @author Z
 * 
 */

public class New_LoadGame extends BasicGameState {
	private Button newGame;
	private Button loadGame;
	private final String s1 = "New Game";
	private final String s2 = "Load Game";
	private final int id;
	private Font titleFont;
	private TrueTypeFont tTitleFont;
	private Font turnFont;
	private TrueTypeFont h1;
	private Font categoryFont;
	private TrueTypeFont h2;
	private Font teamIndex;
	private TrueTypeFont h3;
	private String imageDir = "/resources/images/town/titleImg.png";
	private Image img;

	public New_LoadGame(int id) {
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
		titleFont = new Font("Arial", Font.PLAIN, 15);
		tTitleFont = new TrueTypeFont(titleFont, true);
		turnFont = new Font("Impact", Font.BOLD, 40);
		h1 = new TrueTypeFont(turnFont, true);
		categoryFont = new Font("Impact", Font.PLAIN, 60);
		h2 = new TrueTypeFont(categoryFont, true);
		teamIndex = new Font("Arial", Font.PLAIN, 25);
		h3 = new TrueTypeFont(teamIndex, true);
		newGame = new Button(300, 250, 220, 50, s1);
		loadGame = new Button(300, 350, 220, 50, s2);
		img = new Image(imageDir);
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
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (newGame.checkClick(container.getInput().getMouseX(), container
					.getInput().getMouseY())) {
				sbg.enterState(getID() + 1);
			} else if (loadGame.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())) {
				try {
					if (GameData.load("saveFile.dat")) {
						Sys.alert("Success", "Game loaded successfully!");
						sbg.enterState(5);
					} else {
						Sys.alert("Error", "Error loading game!");
					}
				} catch (Exception ex) {
					System.out.println(ex.toString());
				}
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
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {

		g.drawImage(img, 0, 0);
		// title panel
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, arg0.getWidth(), 50);
		// title label
		g.setColor(Color.white);
		g.setFont(tTitleFont);
		g.drawString("Game Options", ((arg0.getWidth() / 2) - 50), 25);

		// set button color
		g.setColor(Color.white);
		// button "play game"

		g.fill(newGame.getButtonShape());
		// button "how to play"

		g.fill(loadGame.getButtonShape());

		// labels for two buttons
		g.setColor(Color.blue);
		g.setFont(h1);
		g.drawString(s1, 315, 250);
		g.setFont(h1);
		g.drawString(s2, 315, 350);

	}

	/**
	 * returns the id of this state
	 */
	public int getID() {

		return id;
	}
	/*
	 * Create button class
	 */

}
