package states;

import game.GameData;

import java.awt.Font;
import java.io.IOException;

import map.Map;
import map.Pub;
import map.Tile;
import map.Town;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ui_components.Button;

/*
 * 
 *  This is a placeholder.
 */

public class MainGameState extends BasicGameState {
	private final int id;
	// hieght and width of square tile
	private final int tileSize = 80;
	// time player has to select land in ms
	private final int selectionTime = 10000;
	private ui_components.Button saveGame;
	// change these if we change map dimensions
	// horizontal and vertical place markers for drawing map on screen
	private int currH, currV;
	private int mapLMargin, mapTopMargin;
	private int mapWidth, mapHeight;
	// fonts
	private Font fntWhosTurn;
	private TrueTypeFont ttfWhosTurn;
	private Font fntLandSelection;
	private TrueTypeFont ttfLandSelection;
	private Font fntResources;
	private TrueTypeFont ttfResources;
	private Font fntEventDisplay;
	private TrueTypeFont ttfEventDisplay;
	// vertical/horizontal centers
	private int hCent, vCent;

	// timer for land selection
	private int landSelectionDelta;
	private int roundTimer;

	// mouse input
	private int mX, mY;
	private Tile currTile;
	private int currPlayer;

	private int PLAYER_START_X;
	private int PLAYER_START_Y;

	private boolean roundNotStarted;

	// PLAYER ATTRIBUTES
	private int playerLocX, playerLocY, playerW, playerH;

	public MainGameState(int id) {
		this.id = id;
	}

	/**
	 * 
	 * Initialization for state variables
	 * 
	 */
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		hCent = gc.getWidth() / 2;
		vCent = gc.getHeight() / 2;
		mapWidth = Map.numbCols * tileSize;
		mapHeight = Map.numbRows * tileSize;
		// center map on screen
		mapLMargin = (gc.getWidth() - mapWidth) / 2;
		mapTopMargin = ((gc.getHeight() - mapHeight) / 2) + 40;
		// init fonts
		fntWhosTurn = new Font("Impact", Font.BOLD, 45);
		ttfWhosTurn = new TrueTypeFont(fntWhosTurn, true);
		fntLandSelection = new Font("Impact", Font.BOLD, 25);
		ttfLandSelection = new TrueTypeFont(fntLandSelection, true);
		fntResources = new Font("Impact", Font.PLAIN, 20);
		ttfResources = new TrueTypeFont(fntResources, true);
		fntEventDisplay = new Font("Impact", Font.BOLD, 12);
		ttfEventDisplay = new TrueTypeFont(fntEventDisplay, true);
		// land selection initially true
		currPlayer = 0;
		// land selection timer starts at 10
		landSelectionDelta = 10000;
		roundTimer = GameData.getInstance().getRoundTime();

		saveGame = new ui_components.Button(gc.getWidth() - 120,
				gc.getHeight() - 50, 60, 35, "Save");

	}

	/**
	 * 
	 * Update loop that will be called constantly. Use this for things you
	 * always want to be checking or running in this state.
	 * 
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		roundTimer = GameData.getInstance().getRoundTime();
		System.out.println(roundTimer + "main");
		GameData.getInstance().event();
		// GameData.getInstance().save();

	}

	/**
	 * Continuously called. Updates any
	 */
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// check if gamble result need to be shown
		Input input = container.getInput();

		// land selection event listener
		if (GameData.getInstance().landSelection
				&& !GameData.getInstance().skipLandSelect
				&& input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			GameData.getInstance().event();
			roundTimer = 5000;
			mX = input.getMouseX();
			mY = input.getMouseY();
			if (GameData.getInstance().landSelection
					&& !GameData.getInstance().skipLandSelect) {
				// see if a tile has been clicked
				currTile = GameData.getInstance().map.tileClicked(mX, mY);

				if ((currTile != null) && !(currTile instanceof Town)
						&& (currTile.getOwner() == null)) {
					// get the activePlayer's gold
					int gold = GameData.getInstance().getActivePlayer()
							.getGold();
					if (GameData.getInstance().getCurrRound() > 1) {

						if (gold < 300) {
							System.out.println("Low gold for " + "Player "
									+ currPlayer + " to buy land");
						} else {
							currTile.setOwner(GameData.getInstance()
									.getActivePlayer());
							GameData.getInstance().getActivePlayer()
									.setGold(gold - 300);
							System.out.println("success purchase!");
							System.out.println("Balance now: "
									+ GameData.getInstance().getActivePlayer()
											.getGold());
						}
					} else if (GameData.getInstance().getCurrRound() <= 1) {
						currTile.setOwner(GameData.getInstance()
								.getActivePlayer());
						System.out.println("Free purchase");
						System.out.println("Balance now: "
								+ GameData.getInstance().getActivePlayer()
										.getGold());
					}
					// valid tile was clicked, was it the last player
					if (currPlayer >= (GameData.getInstance().getNumPlayer() - 1)) {
						landSelectionDelta = 10000;
						GameData.getInstance().landSelection = false;
						currPlayer = 0;
						System.out.println("Round start");
						GameData.turnCount = 0;
						GameData.getInstance().getActivePlayer()
								.resetLocationMain();
						// playerLocX = PLAYER_START_X;
						// playerLocY = PLAYER_START_Y;
						GameData.getInstance().startRound();
						roundTimer = GameData.getInstance().getRoundTime();

					} else {
						GameData.getInstance().nextPlayer();
						currPlayer++;
						landSelectionDelta = 10000;
					}
				}
			}

		}

		if (GameData.getInstance().startRoundAfterSkipLandSelect) {
			currPlayer = 0;
			System.out.println("skip land select start round");
			System.out.println(GameData.getInstance().skipLandSelect);
			GameData.getInstance().turnCount = 0;
			GameData.getInstance().getActivePlayer().resetLocationMain();
			// playerLocX = PLAYER_START_X;
			// playerLocY = PLAYER_START_Y;
			GameData.getInstance().startRound();
			roundTimer = GameData.getInstance().getRoundTime();
			GameData.getInstance().startRoundAfterSkipLandSelect = false;
		}

		// increment timer in land selection
		if (GameData.getInstance().landSelection
				&& !GameData.getInstance().skipLandSelect) {
			// System.out.println(landSelectionDelta);
			if (landSelectionDelta <= 100) {
				System.out.println("landSelectionDelta = 0");
				GameData.getInstance().playerLandSkipCount++;
				// move to the next player, if all players have selected, done
				// with
				// land selection
				// TODO: may want to remove, give activeplayer first available
				// spot
				// since they did not select
				if (currPlayer >= GameData.getInstance().getNumPlayer() - 1) {
					// all players have selected so start round

					GameData.getInstance().landSelection = false;
					if (GameData.getInstance().playerLandSkipCount == GameData
							.getInstance().getNumPlayer()) {
						GameData.getInstance().skipLandSelect = true;
						GameData.getInstance().landSelection = false;

					} else {
						GameData.getInstance().playerLandSkipCount = 0;
					}
					landSelectionDelta = 10000;
					GameData.getInstance().landSelection = false;
					currPlayer = 0;
					System.out.println("Round start");
					GameData.turnCount = 0;
					GameData.getInstance().getActivePlayer()
							.resetLocationMain();
					System.out.println("skip count"
							+ GameData.getInstance().playerLandSkipCount);
					System.out.println("num players"
							+ GameData.getInstance().getNumPlayer());
					GameData.getInstance().startRound();
					roundTimer = GameData.getInstance().getRoundTime();

				} else {

					GameData.getInstance().nextPlayer();
					currPlayer++;
					landSelectionDelta = 10000;
				}
				// landSelectionDelta = 10; //MIGHT NEED TO BE 10000
			}
			landSelectionDelta -= delta;// WHATS DELTA???????

		} else { // we are not in land selection and on the map

			roundTimer -= delta;
			if (GameData.getInstance().getPlayerChange()) {
				playerLocX = PLAYER_START_X;
				playerLocY = PLAYER_START_Y;
				roundTimer = GameData.getInstance().getRoundTime();
				GameData.getInstance().setPlayerChange(false);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) { // do we want players to not
													// fluidly move but jump to
													// next tile?
				GameData.getInstance().getActivePlayer().moveLeft();
				GameData.getInstance().getActivePlayer().updateSprite(delta);
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				GameData.getInstance().getActivePlayer().moveRight();
				GameData.getInstance().getActivePlayer().updateSprite(delta);
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				GameData.getInstance().getActivePlayer().moveUp();
				GameData.getInstance().getActivePlayer().updateSprite(delta);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				GameData.getInstance().getActivePlayer().moveDown();
				GameData.getInstance().getActivePlayer().updateSprite(delta);
			}
			// check if the player has droped a mule
			if (input.isKeyDown(Input.KEY_ENTER)
					&& (GameData.getInstance().getActivePlayer().muleEquipped)) {
				currTile = GameData.getInstance().map.tileClicked(GameData
						.getInstance().getActivePlayer().getCentX(), GameData
						.getInstance().getActivePlayer().getCentY());
				GameData.getInstance().getActivePlayer().dropMule(currTile);
			}
			// L.O.D.????
			// TODO: Add methods in Player getCenterX and getCenterY
			currTile = GameData.getInstance().map.tileClicked(
					(int) GameData.getInstance().getActivePlayer()
							.getCenterOfPlayer().getX(), (int) GameData
							.getInstance().getActivePlayer()
							.getCenterOfPlayer().getY());

			if (currTile instanceof Town) {
				GameData.getInstance().getActivePlayer().resetLocationMain();
				GameData.getInstance().setRoundTime(roundTimer);
				sbg.enterState(getID() + 1);
			}

		}
		// check if save game button click
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (saveGame.checkClick(input.getMouseX(), input.getMouseY())) {

				System.out.println("SAVE GAME!!!!!");
				GameData test = GameData.getInstance();
				GameData.getInstance().save();
				Sys.alert("Success", "Game Saved!");

			}
		}
	}

	/**
	 * 
	 * Used to generate the graphics and what to display.
	 * 
	 */
	public void render(GameContainer container, StateBasedGame arg1, Graphics g)
			throws SlickException {
		if (GameData.getInstance().getCurrRound() == 0)
			GameData.getInstance().map.setMapTileCoords(tileSize, mapLMargin,
					mapTopMargin);
		// draw map
		GameData.getInstance().map.drawMap(g);
		if (GameData.getInstance().landSelection
				&& !GameData.getInstance().skipLandSelect) {
			// title
			// LAND SELECTION?
			g.setFont(ttfWhosTurn);
			g.setColor(Color.orange);
			g.drawString("Land Selection", hCent - 115, 20);
			g.setFont(ttfLandSelection);
			// timer for player to select land
			g.drawString((landSelectionDelta / 1000) + "sec.", 625, 50);

		} else {
			// game is running, showing map and active player. player can move
			// around
			g.setColor(Color.orange);
			g.drawString("Start Round", 325, 70);
			g.setFont(ttfWhosTurn);
			g.setColor(Color.orange);
			g.drawString(
					"Round " + (GameData.getInstance().getCurrRound() + 1),
					hCent - 75, 20);
			// draw timer
			g.setFont(ttfLandSelection);
			g.drawString((roundTimer / 1000) + "sec.", 625, 50);
			g.setFont(ttfEventDisplay);
			g.drawString("" + GameData.getInstance().getEventDisplay(), 250,
					100);
			// fix this below. LoD
			GameData.getInstance().getActivePlayer().draw(g, container);
			if (GameData.getInstance().getActivePlayer().muleEquipped)
				GameData.getInstance().getActivePlayer().getLastMule()
						.drawMule(g);

		}
		g.setFont(ttfLandSelection);
		// draw player
		g.setColor(GameData.getInstance().getActivePlayer().getColor());
		g.drawString(GameData.getInstance().getActivePlayer().getName(), 625,
				20);
		// draw player resources
		g.setFont(ttfResources);
		GameData.getInstance().getActivePlayer().drawResources(g);
		// save game button
		saveGame.drawButton(g, Color.orange);

	}

	/**
	 * getter for the id of the state returns id
	 */
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
