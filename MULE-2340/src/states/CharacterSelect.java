package states;

import game.GameData;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.BasicTriangulator;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Triangulator;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.*;

import player.Player;
import races.Bonzoid;
import races.Buzzite;
import races.Flapper;
import races.Human;
import races.Race;
import races.Ugaite;
import ui_components.Button;

/**
 * 
 * @author Ziyi Jiang
 */

public class CharacterSelect extends BasicGameState {

	/*
	 * 
	 * Initialization for state variables
	 */
	// int to indicate horizontal/vertical center
	private int hCent, vCent;
	// collection of colors we can iterate through
	private List<Color> colors;
	// TODO: Convert this to a collection of images
	private List<Race> races;
	private int currColor, currImage;
	// TODO: This needs to be replaced with a collection of players (maybe from
	// Game object)
	private List<String> players;
	private int currPlayer = 0;
	private int x, y;
	// fonts
	private Font titleFont;
	private TrueTypeFont tTitleFont;
	private Font turnFont;
	private TrueTypeFont h1;
	private Font categoryFont;
	private TrueTypeFont h2;
	private Font instrFont;
	private TrueTypeFont instr;
	private String imageDir = "/resources/images/town/titleImg.png";
	private Image img;

	// Text field
	TextField tf;

	// next player button
	Button nxtBtn;
	Button lftColor;
	Button rtColor;
	Button lftRace;
	Button rtRace;
	// left/right triangles
	Polygon lftTriColor;
	Polygon rtTriColor;
	Polygon lftTriRace;
	Polygon rtTriRace;
	// indicates that all players have selected their player/race
	private boolean done;

	// First player
	private Player p1;
	private Player activePlayer;

	private final int id;

	public CharacterSelect(int id) {
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
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		img = new Image(imageDir);
		p1 = new Player();
		activePlayer = p1;

		done = false;
		hCent = gc.getWidth() / 2;
		vCent = gc.getHeight() / 2;

		tf = new TextField(gc, gc.getDefaultFont(), hCent - 40, 165, 100, 25);

		// fill our list of colors
		currColor = 0;
		colors = new ArrayList();
		colors.add(Color.green);
		colors.add(Color.white);
		colors.add(Color.red);
		colors.add(Color.orange);

		// we would add images to our collection here
		currImage = 0;
		races = new ArrayList();
		races.add(new Human());
		races.add(new Flapper());
		races.add(new Bonzoid());
		races.add(new Ugaite());
		races.add(new Buzzite());
		// TODO: This will need to be removed and replaced with a reference to
		// our player collection
		players = new ArrayList();
		players.add("Player 1");
		players.add("Player 2");
		players.add("Player 3");
		players.add("Player 4");

		titleFont = new Font("Arial", Font.PLAIN, 15);
		tTitleFont = new TrueTypeFont(titleFont, true);
		turnFont = new Font("Impact", Font.BOLD, 50);
		h1 = new TrueTypeFont(turnFont, true);
		categoryFont = new Font("Impact", Font.PLAIN, 40);
		h2 = new TrueTypeFont(categoryFont, true);
		instrFont = new Font("Impact", Font.ITALIC, 14);
		instr = new TrueTypeFont(instrFont, true);
		// init buttons
		nxtBtn = new Button(650, 510, 100, 60, "Next");
		lftColor = new Button(115, 235, 30, 30, "");
		rtColor = new Button(270, 235, 30, 30, "");
		lftRace = new Button(hCent + 115, 235, 30, 30, "");
		rtRace = new Button(hCent + 265, 235, 30, 30, "");
		// triangles
		lftTriColor = new Polygon();
		lftTriColor.addPoint(117, 250);
		lftTriColor.addPoint(143, 237);
		lftTriColor.addPoint(143, 263);
		rtTriColor = new Polygon();
		rtTriColor.addPoint(298, 250);
		rtTriColor.addPoint(272, 237);
		rtTriColor.addPoint(272, 263);
		lftTriRace = new Polygon();
		lftTriRace.addPoint(hCent + 117, 250);
		lftTriRace.addPoint(hCent + 143, 237);
		lftTriRace.addPoint(hCent + 143, 263);
		rtTriRace = new Polygon();
		rtTriRace.addPoint(hCent + 293, 250);
		rtTriRace.addPoint(hCent + 267, 237);
		rtTriRace.addPoint(hCent + 267, 263);
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
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			x = input.getMouseX();
			y = input.getMouseY();
			if (nxtBtn.checkClick(x, y)) {
				changePlayer();
			} else if (lftColor.checkClick(x, y)) {
				changeColor(-1);
			} else if (rtColor.checkClick(x, y)) {
				changeColor(1);
			} else if (lftRace.checkClick(x, y)) {
				changeRace(-1);
			} else if (rtRace.checkClick(x, y)) {
				changeRace(1);
			}
		}

	}

	/**
	 * This methods saves the options on the screen into a player class, and
	 * then changes the current player
	 */
	private void changePlayer() {
		// TODO: Set selected info for current player here!!!
		Color c = colors.get(currColor);
		if (tf.getText() != "")
			activePlayer.setName(tf.getText());
		else
			activePlayer.setName(players.get(currPlayer));
		activePlayer.setRace(races.get(currImage)); // Implement race type here
		activePlayer.setColor((Color) c);
		tf.setText("");
		if (colors.size() > 1)
			colors.remove(c);
		currColor = 0;

		// increment player
		if (currPlayer == (GameData.getInstance().getNumPlayer() - 1)) {
			done = true;
			return;
		} else {
			GameData.getInstance().addPlayer(activePlayer);
			currPlayer++;
			switch (currPlayer) {
			case 1:
				Player p2 = new Player();
				activePlayer = p2;
				break;
			case 2:
				Player p3 = new Player();
				activePlayer = p3;
				break;
			case 3:
				Player p4 = new Player();
				activePlayer = p4;
				break;
			}
		}

		if (currPlayer == (players.size() - 1)) {
			// this is the last player so the button should say start game
			nxtBtn.setButtonName("Start");
		}
	}

	/**
	 * this methods chagnes the current color being displayed
	 * 
	 * @param direction
	 */
	private void changeColor(int direction) {
		if (direction < 0) {
			// move to left
			if (currColor == 0)
				currColor = colors.size() - 1;
			else
				currColor--;
			// redrawColor();
		} else {
			if (currColor == (colors.size() - 1))
				currColor = 0;
			else
				currColor++;
			// redrawColor();
		}
	}

	/**
	 * this method changes the current race being displayed
	 * 
	 * @param direction
	 */
	private void changeRace(int direction) {
		if (direction < 0) {
			// move to left
			if (currImage == 0) {
				currImage = races.size() - 1;
			} else {
				currImage--;
			}
		} else {
			if (currImage == (races.size() - 1)) {
				currImage = 0;
			} else {
				currImage++;
			}
		}
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 * 
	 *             Used to generate the graphics and what to display.
	 * 
	 **/
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(img, 0, 0);
		if (done) {
			GameData.getInstance().addPlayer(activePlayer);
			GameData.getInstance().origOrderPlayers = GameData.getInstance()
					.getPlayers();
			sbg.enterState(getID() + 1);
		}
		// background
		// g.setColor(Color.blue);
		// g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		// title panel
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, gc.getWidth(), 50);
		// title label
		g.setColor(Color.white);
		g.setFont(tTitleFont);

		// text field
		tf.setBackgroundColor(Color.darkGray);
		tf.setTextColor(Color.white);
		tf.render(gc, g);

		g.drawString("Player Options", ((gc.getWidth() / 2) - 50), 25);
		// Whos turn is it
		g.setColor(Color.orange);
		g.setFont(h1);
		g.drawString(players.get(currPlayer) + " Name: ", hCent - 150, 100);

		// player / race selector
		g.setFont(h2);
		g.drawString("Color:", 160, vCent - 75);
		g.drawString("Race:", hCent + 160, vCent - 75);
		// where we show race picture
		g.setColor(colors.get(currColor));
		g.fillRect(hCent - 110, vCent, 250, 250);
		// fix this below. LoD
		races.get(currImage).getAnimation()
				.draw(hCent - 70, vCent + 35, 175, 175);
		// next button
		g.setColor(Color.white);
		g.fill(nxtBtn.getButtonShape());
		// triangle buttons
		g.fill(lftColor.getButtonShape());
		g.fill(rtColor.getButtonShape());
		g.fill(lftRace.getButtonShape());
		g.fill(rtRace.getButtonShape());
		g.setColor(Color.blue);
		g.drawString(nxtBtn.getButtonName(), 660, 515);
		g.fill(lftTriColor);
		g.fill(rtTriColor);
		g.fill(lftTriRace);
		g.fill(rtTriRace);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
