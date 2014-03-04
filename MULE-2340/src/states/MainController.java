package states;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Ziyi Jiang
 * 
 */
public class MainController extends StateBasedGame {

	private final int StartScreen = 1;
	private final int New_LoadGame = 2;
	private final int GameOptions = 3;
	private final int CharacterSelect = 4;
	private final int MainGameState = 5;
	private final int TownGameState = 6;

	public MainController(String title) {
		super(title);
	}

	/**
	 * Main Method
	 * 
	 * @param args
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new MainController(
				"Setup test"));

		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);

		app.start();
	}

	/**
	 * initializes all states
	 */
	public void initStatesList(GameContainer container) throws SlickException {

		this.addState(new StartScreen(StartScreen));
		this.addState(new New_LoadGame(New_LoadGame));
		this.addState(new GameOptions(GameOptions));
		this.addState(new CharacterSelect(CharacterSelect));
		this.addState(new MainGameState(MainGameState));
		this.addState(new TownGameState(TownGameState));

	}

}
