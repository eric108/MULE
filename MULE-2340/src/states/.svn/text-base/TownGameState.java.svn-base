package states;

import game.GameData;

import java.awt.Font;
import java.util.TimerTask;
import java.util.Timer;

import map.AssayOffice;
import map.LandOffice;
import map.Pub;
import map.Store;

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

	// fonts
	private Font fntWhosTurn;
	private TrueTypeFont ttfWhosTurn;
	private Font fntResources;
	private TrueTypeFont ttfResources;
	// vertical/horizontal centers
	// PLAYER ATTRIBUTES
	// private int playerLocX, playerLocY, playerW, playerH;

	private boolean playerInStore;

	private static int roundTimer;
	private Store theStore;
	private Pub thePub;
	private LandOffice theLandOffice;
	private AssayOffice theAssayOffice;
	Rectangle rectangle, rectForSky;

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
		// playerW = 60;
		// playerH = 60;
		// Player starting location
		// playerLocX = STARTING_LOC_X;// hCent - playerW / 2;
		// playerLocY = STARTING_LOC_Y;// vCent + 60;

		theStore = new Store(container);
		thePub = new Pub();
		theLandOffice = new LandOffice();
		theAssayOffice = new AssayOffice();
		rectangle = new Rectangle(0, 380, container.getWidth(),
				container.getHeight() - 380);
		rectForSky = new Rectangle(0, 0, container.getWidth(),
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
		theStore.setStockResources();
		GameData.getInstance().getInstance().getActivePlayer()
				.resetLocationTown();
		GameData.getInstance().getInstance().getActivePlayer().setInTown(true);
		roundTimer = GameData.getInstance().getInstance().getRoundTime() / 1000;
		System.out.println(roundTimer);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// System.out.println(delta);
		roundTimer -= delta;
		GameData.getInstance().setRoundTime(roundTimer / 1000);
		Input input = container.getInput();
		if (GameData.getInstance().landSelection
				|| GameData.getInstance().getPlayerChange()
				|| GameData.getInstance().getActivePlayer().getX() <= 2
				|| GameData.getInstance().getActivePlayer().getX() >= 798
				|| GameData.getInstance().getActivePlayer().getY() >= 598) {
			// System.out.println("conditional hit");
			System.out.println(GameData.getInstance().getPlayerChange());
			if (GameData.getInstance().getPlayerChange()) {
				System.out.println("player change is false");
				GameData.getInstance().setPlayerChange(false);
			}
			GameData.getInstance().getActivePlayer().resetLocationTown();
			// playerLocX = STARTING_LOC_X;
			// playerLocY = STARTING_LOC_Y;
			GameData.getInstance().setRoundTime(roundTimer / 1000);
			GameData.getInstance().getActivePlayer().setInTown(false);
			sbg.enterState(getID() - 1);
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			GameData.getInstance().getActivePlayer().moveLeft();
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			GameData.getInstance().getActivePlayer().moveRight();
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			GameData.getInstance().getActivePlayer().moveUp();
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			GameData.getInstance().getActivePlayer().moveDown();
		}

		// are you in the store?
		if (theStore.onStore(GameData.getInstance().getActivePlayer().getX(),
				GameData.getInstance().getActivePlayer().getY())) {
			playerInStore = true;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				playerInStore = theStore.onclick(container, true);
			} else {

				playerInStore = theStore.onclick(container, false);
			}
			if (!playerInStore) {
				// player just left the store
				GameData.getInstance().getActivePlayer().resetLocationTown();
				// playerLocX = STARTING_LOC_X; // change the player' position
				// so
				// that it won't update forever
				// playerLocY = STARTING_LOC_Y;
			}
		}
		// gambling issue
		else if (thePub.onPub(GameData.getInstance().getActivePlayer().getX(),
				GameData.getInstance().getActivePlayer().getY())) {
			System.out.println("enter pub!");
			GameData.getInstance().getActivePlayer().resetLocationTown();
			// playerLocX = STARTING_LOC_X; // change the player' position so
			// that
			// it won't update forever
			// playerLocY = STARTING_LOC_Y;
			// Gambling in pub
			Pub.onClick(roundTimer);
			GameData.getInstance().getActivePlayer().setInTown(false);
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
			g.setColor(Color.cyan);
			g.fill(rectForSky);
			g.setColor(Color.lightGray);
			g.fill(rectangle);
			g.drawString("TOWN STATE", 500, 40);
			GameData.getInstance().getActivePlayer().draw(g, gc);
			if (GameData.getInstance().getActivePlayer().muleEquipped) {
				GameData.getInstance().getActivePlayer().getLastMule()
						.drawMule(g);
			}
			theStore.draw(g);
			thePub.draw(g);
			// TODO: make land office and assay off draw themselves
			theLandOffice.getImage().draw(400, 200, 200, 200);
			theAssayOffice.getImage().draw(600, 200, 200, 200);
		}
		// draw curr player and time
		g.setColor(Color.orange);
		g.setFont(ttfWhosTurn);
		g.drawString(GameData.getInstance().getActivePlayer().getName(), 625,
				20);
		// draw timer
		g.drawString(roundTimer / 1000 + "sec.", 625, 50);

		// draw resource
		g.setFont(ttfResources);
		GameData.getInstance().getActivePlayer().drawResources(g);

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
