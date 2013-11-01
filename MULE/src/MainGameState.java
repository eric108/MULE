import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

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
		mapTopMargin = (gc.getHeight() - mapHeight) / 2;
		// init fonts
		fntWhosTurn = new Font("Impact", Font.BOLD, 45);
		ttfWhosTurn = new TrueTypeFont(fntWhosTurn, true);
		fntLandSelection = new Font("Impact", Font.BOLD, 25);
		ttfLandSelection = new TrueTypeFont(fntLandSelection, true);
		// land selection initially true
		
		currPlayer = 0;

		// player dimensions
		playerW = 60;
		playerH = 60;
		// Player starting location
		PLAYER_START_X = hCent - playerW / 2;
		PLAYER_START_Y = vCent + 60;
		
		playerLocX = PLAYER_START_X;
		playerLocY = PLAYER_START_Y;
		// land selection timer starts at 10
		landSelectionDelta = 10000;
		roundTimer = GameData.getRoundTime();
		
		//roundNotStarted=true;
		
	}

	/**
	 * 
	 * Update loop that will be called constantly. Use this for things you
	 * always want to be checking or running in this state.
	 * 
	 */

	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
//		//check if gamble result need to be shown
//		if(Pub.getIfGamble()){
//			System.out.println("showing gambling!");
//			GameData.showGamblingResult(); 
//		}
		Input input = container.getInput();
		// land selection event listener
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			roundTimer = 5000;
			mX = input.getMouseX();
			mY = input.getMouseY();
			if (GameData.landSelection && !GameData.skipLandSelect) {
				// see if a tile has been clicked
				currTile = GameData.map.tileClicked(mX, mY);
				if ((currTile != null )
						&& !(currTile instanceof Town)
						&& (currTile.getOwner() == null)) {
					//get the activePlayer's gold
					int gold = GameData.getActivePlayer().
							getResources().getGold();
					if(GameData.getCurrRound()>1){
						    
						    if(gold<300){
								System.out.println( "Low gold for " +"Player "+ 
								currPlayer + " to buy land");
						    }else{
						    	currTile.setOwner(GameData.getActivePlayer());
								GameData.getActivePlayer().getResources().setGold(gold-300);
								System.out.println("success purchase!");
								System.out.println("Balance now: " + GameData.getActivePlayer().
										getResources().getGold());
						    }
					}else if(GameData.getCurrRound()<=1){
						currTile.setOwner(GameData.getActivePlayer());
						System.out.println("Free purchase");
						System.out.println("Balance now: " + GameData.getActivePlayer().
								getResources().getGold());
					}
					// valid tile was clicked, was it the last player
					if (currPlayer >= (GameData.getNumPlayer() - 1)) {
						landSelectionDelta = 10000;
						GameData.landSelection = false;
						currPlayer = 0;
						System.out.println("Round start");
						GameData.turnCount = 0;
						playerLocX = PLAYER_START_X;
						playerLocY = PLAYER_START_Y;
						GameData.startRound();
						
					} else {
						GameData.nextPlayer();
						currPlayer++;
						landSelectionDelta = 10000;
					}
				}
			} 

		}
		
		if(GameData.startRoundAfterSkipLandSelect) {
			currPlayer = 0;
			System.out.println("skip land select start round");
			System.out.println(GameData.skipLandSelect);
			GameData.turnCount = 0;
			playerLocX = PLAYER_START_X;
			playerLocY = PLAYER_START_Y;
			GameData.startRound();
			GameData.startRoundAfterSkipLandSelect = false;
		}

		// increment timer in land selection
		if (GameData.landSelection && !GameData.skipLandSelect) {
			//System.out.println(landSelectionDelta);
			if (landSelectionDelta <= 100) {
				System.out.println("landSelectionDelta = 0");
				GameData.playerLandSkipCount++;
				// move to the next player, if all players have selected, done
				// with
				// land selection
				// TODO: may want to remove, give activeplayer first available
				// spot
				// since they did not select
				if (currPlayer >= GameData.getNumPlayer() - 1) {
					// all players have selected so start round
					
					GameData.landSelection = false;
					if(GameData.playerLandSkipCount == GameData.getNumPlayer()) {
						GameData.skipLandSelect = true;
						GameData.landSelection = false;
						
					} else {
						GameData.playerLandSkipCount = 0;
					}
					landSelectionDelta = 10000;
					GameData.landSelection = false;
					currPlayer = 0;
					System.out.println("Round start");
					GameData.turnCount = 0;
					playerLocX = PLAYER_START_X;
					playerLocY = PLAYER_START_Y;
					System.out.println("skip count" + GameData.playerLandSkipCount);
					System.out.println("num players" + GameData.getNumPlayer());
					GameData.startRound();
					
				} else {
					
					GameData.nextPlayer();
					currPlayer++;
					landSelectionDelta = 10000;
				}
				//landSelectionDelta = 10; //MIGHT NEED TO BE 10000
			}
			landSelectionDelta -= delta;//WHATS DELTA???????
			
		} else { // we are not in land selection and on the map
			// player movement
			//System.out.println("round started");
			//if(roundNotStarted){
				//GameData.startRound();
				//roundNotStarted=false;
			//}
			
			
			roundTimer -= delta;
			if(GameData.getPlayerChange()) {
				playerLocX = PLAYER_START_X;
				playerLocY = PLAYER_START_Y;
				roundTimer=5000;
				GameData.setPlayerChange(false);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) { //do we want players to not fluidly move but jump to next tile?
				playerLocX-= 1;
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				playerLocX+=1;
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				playerLocY-=1;
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				playerLocY+=1;
			}
			if(playerLocX<=15)
				playerLocX=15;
			if(playerLocX>=715)
				playerLocX=715;
			if(playerLocY<=100)
				playerLocY=100;
			if(playerLocY>=440)
				playerLocY=440;
			
			
			
			
				
			
			
			currTile = GameData.map.tileClicked(playerLocX+playerW/2, playerLocY+playerH/2);
			
			if(currTile instanceof Town) {
				// Transition to Town screen
				// reset player location
				playerLocX = hCent - playerW / 2;
				playerLocY = vCent + 60;
				//roundTimer = GameData.getRoundTime();
				//System.out.println("AHHHHHHHHHHHHHHHHHHH");
				sbg.enterState(getID()+1);
			}
			
		}
		
		//System.out.println(landSelectionDelta);
	}


	/**
	 * 
	 * Used to generate the graphics and what to display.
	 * 
	 */
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		if (GameData.getCurrRound() == 	0)
			GameData.map.setMapTileCoords(tileSize, mapLMargin, mapTopMargin);
		// draw map
		drawMap(g);
//		if(Pub.getIfGamble()){
//			g.drawString("Gambling: ", hCent - 125, 225);
//		}
		if (GameData.landSelection && !GameData.skipLandSelect) {
			// title
			// LAND SELECTION?
			g.setFont(ttfWhosTurn);
			g.setColor(Color.orange);
			g.drawString("Land Selection", hCent - 125, 25);
			g.setFont(ttfLandSelection);
			g.drawString(GameData.getActivePlayer().getName() + "'s Turn",
					hCent - 125, 70);

			// timer for player to select land
			g.drawString((landSelectionDelta / 1000) + "sec.", 600, 50);
		} else {
			// game is running, showing map and active player. player can move
			// around
			g.setColor(Color.orange);
			g.drawString("Start Round", hCent - 125, 70);
			g.setFont(ttfWhosTurn);
			g.drawString((roundTimer / 1000) + "sec.", 600, 50);
			//g.drawString((GameData.getroundTimeDisplay() / 1000) + "sec.", 600, 50);
			g.drawString("Round " + GameData.getCurrRound(), hCent - 125, 25);
			// fix this below. LoD
			GameData.getActivePlayer().getImage()
					.draw(playerLocX, playerLocY, playerW, playerH);
		}

	}
	
	private void drawMap(Graphics g){
		for (int row = 0; row < Map.numbRows; row++) {
			for (int col = 0; col < Map.numbCols; col++) {
				// if it's owned, draw the player who own's it's color around it
				if (GameData.map.getTile(row, col).getOwner() != null) {
					g.setColor(GameData.map.getTile(row, col).getOwner().color);
					g.fillRect(GameData.map.getTile(row, col).getX(),
							GameData.map.getTile(row, col).getY(), tileSize,
							tileSize);
					// draw tile image
					// Map should be able to draw its self, not each tile drawing. FIX BELOW
					
					GameData.map
					.getTile(row, col)
					.getImage()
					.draw(GameData.map.getTile(row, col).getX() +7,
							GameData.map.getTile(row, col).getY() + 7,
							tileSize-15, tileSize-15); 
							
				} 
				else{
					GameData.map
					.getTile(row, col)
					.getImage()
					.draw(GameData.map.getTile(row, col).getX(),
							GameData.map.getTile(row, col).getY(),
							tileSize, tileSize);
				}
			}
		}
	}

	/**
	 * getter for the id of the state returns id
	 */
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
