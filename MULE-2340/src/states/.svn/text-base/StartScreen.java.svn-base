package states;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import ui_components.Button;

/**
 * 
 * @author Z
 * 
 */
public class StartScreen extends BasicGameState {

	private Music music;
	
	
	private int id;
	private Button playGame;
	private Button howToPlay;
	private Button about;
	private final String s1 = "Play Game";
	private final String s2 = "How to play";
	private final String s3 = "About";
	// fonts from Matt
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

	public StartScreen(int id) {
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
		
		music = new Music("resources/Music/MuleMusic.ogg");
		music.setVolume(0.2f);
		music.loop();
		titleFont = new Font("Arial", Font.PLAIN, 15);
		tTitleFont = new TrueTypeFont(titleFont, true);
		turnFont = new Font("Impact", Font.BOLD, 40);
		h1 = new TrueTypeFont(turnFont, true);
		categoryFont = new Font("Impact", Font.PLAIN, 60);
		h2 = new TrueTypeFont(categoryFont, true);
		teamIndex = new Font("Arial", Font.PLAIN, 25);
		h3 = new TrueTypeFont(teamIndex, true);
		playGame = new Button(300, 250, 220, 50, s1);
		howToPlay = new Button(300, 350, 220, 50, s2);
		about = new Button(300, 450, 220, 50, s3);
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
			if (playGame.checkClick(container.getInput().getMouseX(), container
					.getInput().getMouseY())) {

				sbg.enterState(getID() + 1);
			} else if (howToPlay.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())) {
				// howToPlay.getActionListener().onAction(howToPlay.getButtonName());
			} else if (about.checkClick(container.getInput().getMouseX(),
					container.getInput().getMouseY())) {
				// about.getActionListener().onAction(about.getButtonName());
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
		g.drawString("Start Screen", ((arg0.getWidth() / 2) - 50), 25);
		// project name
		g.setColor(Color.yellow);
		g.setFont(h2);
		g.drawString("M.U.L.E.", 330, 100);
		// team name
		g.setColor(Color.yellow);
		g.setFont(h3);
		g.drawString("by team 25", 430, 170);

		// set button color
		g.setColor(Color.white);
		// button "play game"

		g.fill(playGame.getButtonShape());
		// button "how to play"

		g.fill(howToPlay.getButtonShape());
		// button "about"

		g.fill(about.getButtonShape());
		// labels for three buttons
		g.setColor(Color.blue);
		g.setFont(h1);
		g.drawString(s1, 315, 250);
		g.setFont(h1);
		g.drawString(s2, 305, 350);
		g.setFont(h1);
		g.drawString(s3, 355, 450);
	}

	@Override
	public int getID() {

		return id;
	}

}
