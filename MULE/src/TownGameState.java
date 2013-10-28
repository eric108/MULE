import java.awt.Font;

import java.util.TimerTask;
import java.util.Timer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Rectangle;

public class TownGameState extends BasicGameState {

	private final int id;

	// change these if we change map dimensions
	// horizontal and vertical place markers for drawing map on screen
	private int currH, currV;
	private int mapLMargin, mapTopMargin;
	private int mapWidth, mapHeight;
	// fonts
	private Font fntWhosTurn;
	private TrueTypeFont ttfWhosTurn;
	private Font fntResources;
	private TrueTypeFont ttfResources;
	// vertical/horizontal centers
	private int hCent, vCent;
	// mouse input
	private int mX, mY;

	// PLAYER ATTRIBUTES
	private int playerLocX, playerLocY, playerW, playerH;

	private boolean playerInStore;

	private static int roundTimer;
	private Store theStore;
	private Pub thePub;
	private LandOffice theLandOffice;
	private AssayOffice theAssayOffice;
	Rectangle rectangle;
	private final int STARTING_LOC_X = 380;
	private final int STARTING_LOC_Y = 500;

	public TownGameState(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		// hCent = container.getWidth() / 2;
		// vCent = container.getHeight() / 2;
		// player dimensions

		playerInStore = false;
		playerW = 60;
		playerH = 60;
		// Player starting location
		playerLocX = STARTING_LOC_X;// hCent - playerW / 2;
		playerLocY = STARTING_LOC_Y;// vCent + 60;

		theStore = new Store(container);
		thePub = new Pub();
		theLandOffice = new LandOffice();
		theAssayOffice = new AssayOffice();
		rectangle = new Rectangle(0, 380, container.getWidth(),
				container.getHeight() - 380);

		// fonts
		fntWhosTurn = new Font("Impact", Font.BOLD, 25);
		ttfWhosTurn = new TrueTypeFont(fntWhosTurn, true);
		fntResources = new Font("Impact", Font.PLAIN, 20);
		ttfResources = new TrueTypeFont(fntResources, true);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		roundTimer = GameData.getRoundTime() / 1000;
		System.out.println(roundTimer);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// System.out.println(delta);
		roundTimer -= delta;
		GameData.setRoundTime(roundTimer / 1000);
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			mX = input.getMouseX();
			mY = input.getMouseY();
		}
		
		if (GameData.landSelection || GameData.getPlayerChange()
				|| playerLocX <= 2 || playerLocX >= 798 || playerLocY >= 598) {
			System.out.println("conditional hit");
			System.out.println(GameData.getPlayerChange());
			if (GameData.getPlayerChange()) {
				System.out.println("player change is false");
				GameData.setPlayerChange(false);
			}
			playerLocX = STARTING_LOC_X;
			playerLocY = STARTING_LOC_Y;
			GameData.setRoundTime(roundTimer / 1000);
			sbg.enterState(getID() - 1);
		}
		

		if (input.isKeyDown(Input.KEY_LEFT)) {
			playerLocX -= 1;// 3
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			playerLocX += 1;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			playerLocY -= 1;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			playerLocY += 1;
		}
// WHY DOESNT THIS WORK JLASDJKFLASJDFIOeJRFKlDSFKLAJFireA
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			System.out.println("................................");
		}
		// are you in the store?
		if (theStore.onStore(playerLocX, playerLocY)) {
			// sbg.enterState(7);
			//System.out.println("in store");
			playerInStore = true;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				System.out.println("CCCC");
				playerInStore = theStore.onclick(container, true);
			}
			else {
				System.out.println("nonononononononnnonnonnoo");
				playerInStore = theStore.onclick(container, false);
			}
				if (!playerInStore) {
				// player just left the store
				playerLocX = STARTING_LOC_X; // change the player' position so
												// that it won't update forever
				playerLocY = STARTING_LOC_Y;
			}
		}
		// gambling issue
		else if (thePub.onPub(playerLocX, playerLocY)) {
			System.out.println("enter pub!");
			playerLocX = STARTING_LOC_X; // change the player' position so that
											// it won't update forever
			playerLocY = STARTING_LOC_Y;
			// Gambling in pub
			Pub.onClick(roundTimer);
			sbg.enterState(getID() - 1);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

		/*
		 * Code to test buy screen;
		 */
		Input input = gc.getInput();
		if (playerInStore) {
			theStore.drawStoreUI(g, gc);

		} else {
			g.setColor(Color.gray);
			g.fill(rectangle);
			g.drawString("TOWN STATE", 500, 40);
			GameData.getActivePlayer().getImage()
					.draw(playerLocX, playerLocY, playerW, playerH);
			theStore.draw(g);
			thePub.draw(g);
			// TODO: make land office and assay off draw themselves
			theLandOffice.getImage().draw(400, 200, 200, 200);
			theAssayOffice.getImage().draw(600, 200, 200, 200);
		}
		// draw curr player and time
		g.setColor(Color.orange);
		g.setFont(ttfWhosTurn);
		g.drawString(GameData.getActivePlayer().getName(), 625, 20);
		// draw timer
		g.drawString(roundTimer / 1000 + "sec.", 625, 50);

		// draw resource
		g.setFont(ttfResources);
		GameData.getActivePlayer().drawResources(g);

	}

	public static void setRoundTime(int time) {
		roundTimer = time;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
