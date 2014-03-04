package game;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.OutputStreamWriter;

import player.Player;
import map.Map;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sun.istack.internal.logging.Logger;

/**
 * 
 */

/**
 * @author Ziyi Jiang
 * 
 */
public class GameData implements Serializable {
	private static GameData gameData = null;
	// Player list
	private ArrayList<Player> players;
	public ArrayList<Player> origOrderPlayers;
	private ArrayList<Player> roundOrder;
	private Player activePlayer;
	// total rounds
	private final int totalRounds = 12;
	// current round
	private int currRound;
	// dificulty of game
	private String dificulty;
	private int numPlayer;
	private int currPlayer;
	public static int turnCount;
	private int foodReq; // required food amount for each round
	// game timer
	private transient Timer timer;
	private int roundTime;
	private int roundTimeDisplay = 5000;// in seconds
	public Map map;
	// displaying info for event
	private String eventDisplay;

	private boolean playerChange;

	public boolean landSelection;
	// counts the number of players that have skipped land selection this round
	// if equal to number of players, land selection should end
	public int playerLandSkipCount;
	public boolean skipLandSelect;
	public boolean startRoundAfterSkipLandSelect;

	/*
	 * stop from multiple instances from being created
	 */
	protected GameData() {

	}

	/**
	 * Gives the instance of the class
	 * 
	 * @return GameData
	 */
	public static GameData getInstance() {
		if (gameData == null) {
			gameData = new GameData();
		}
		return gameData;
	}

	/**
	 * Constructor
	 * 
	 * @param numberOfPlayers
	 * @param mapType
	 *            true if random, false if standar
	 * @param dificulty
	 *            the difficulty of the game
	 * @return
	 */
	public void init(int numberOfPlayers, boolean mapType, String dificulty) {
		currRound = 0;
		players = new ArrayList<Player>(numberOfPlayers);
		roundOrder = new ArrayList<Player>(numberOfPlayers);
		origOrderPlayers = new ArrayList<Player>(numberOfPlayers);
		this.dificulty = dificulty;
		numPlayer = numberOfPlayers;
		timer = new Timer();
		currPlayer = 1;
		turnCount = 0;
		// create map passing in maptype
		map = new Map(mapType);
		landSelection = true;
		playerChange = false;
		playerLandSkipCount = 0;
		skipLandSelect = false;
		startRoundAfterSkipLandSelect = false;
		setEventDisplay(" ");
	}

	/**
	 * This Method saves the state of the Game
	 */
	public void save() {
		try {
			/*
			 * Create the object output stream for serialization. We are
			 * wrapping a FileOutputStream since we want to save to disk. You
			 * can also save to socket streams or any other kind of stream.
			 */
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("saveFile.dat"));

			/*
			 * The only real call we need. The stream buffers the output and
			 * reuses data, so if you are serializing very frequently, then the
			 * object values might not actually change because the old
			 * serialized object is being reused.
			 * 
			 * To fix this you can try writeUnshared() or you can reset the
			 * stream. out.reset();
			 */
			out.writeObject(this);
		} catch (FileNotFoundException e) {
			System.out.println("EXCEPTION FOUND!!!!");
			System.out.println(e.toString());
			// myLogger.log(Level.SEVERE, "Save file not found: " + filename,
			// e);
		} catch (IOException e) {
			System.out.println("EXCEPTION FOUND!!!!");
			System.out.println(e.toString());
			// myLogger.log(Level.SEVERE, "General IO Error on saving: " +
			// filename, e);
		}

		/*
		 * try{ System.out.println("SAVE GAME, IN GAMEDATA"); PrintWriter out =
		 * new PrintWriter("saveFile.txt");
		 * System.out.println("ABOUT TO START CONVERTING TO JSON"); Gson gson =
		 * new GsonBuilder() .registerTypeAdapterFactory(new
		 * RaceTypeAdapterFactory()) .setExclusionStrategies(new
		 * GameDataSerExclusion()) .create();
		 * out.println(gson.toJson(GameData.getInstance())); // Write to file
		 * using BufferedWriter out.close(); return true; } catch(Exception ex){
		 * System.out.println((ex.toString())); return false; }
		 */}

	/**
	 * This method loads a previously save game into the machine
	 * 
	 * @param filename
	 * @return Boolean whether the save actual happened
	 * @throws FileNotFoundException
	 */
	public static boolean load(String filename) throws FileNotFoundException {

		try {
			/*
			 * Create the input stream. Since we want to read from the disk, we
			 * wrap a file stream.
			 */
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filename));
			/*
			 * Now we can read the entire company in with only one call
			 */
			gameData = (GameData) in.readObject();
			return true;

		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			return false;
			// myLogger.log(Level.SEVERE, "Load file not found: " + filename,
			// e);
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
			// myLogger.log(Level.SEVERE, "General IO Error on loading: " +
			// filename, e);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
			return false;
			// myLogger.log(Level.SEVERE, "Company class not found on loading: "
			// + filename, e);
		}
		/*
		 * try{ String jsonSaveText = new Scanner(new
		 * File("saveFile.txt")).useDelimiter("\\Z").next();
		 * 
		 * Gson gson = new GsonBuilder() .registerTypeAdapterFactory(new
		 * RaceTypeAdapterFactory()) .setExclusionStrategies(new
		 * GameDataSerExclusion()) .create(); GameData temp =
		 * gson.fromJson(jsonSaveText, GameData.class); gameData = temp; return
		 * true; } catch(Exception ex){ return false; }
		 */
	}

	/**
	 * This method adds a player to the arraylist of players
	 * 
	 * @param p
	 *            A player to add to the player list
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}

	/**
	 * Getter method
	 * 
	 * @return int the number of players playing
	 */
	public int getNumPlayer() {
		return numPlayer;
	}

	/**
	 * 
	 * @return the next player
	 */
	public boolean getPlayerChange() {
		return playerChange;
	}

	/**
	 * Sets the next player to go
	 * 
	 * @param b
	 */
	public void setPlayerChange(boolean b) {
		playerChange = b;
	}

	/**
	 * getter for Difficulty
	 * 
	 * @return Dificulty
	 */
	public String getDifficulty() {
		return dificulty;
	}

	/**
	 * getter for player
	 * 
	 * @return Player
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	// startRousnd should be called after land selection or auction has ended
	/**
	 * This method starts the round between the player
	 */
	public void startRound() {
		System.out.print("roundCount: " + turnCount);
		System.out.println(" numPlayer: " + numPlayer);
		System.out.println(players.size());

		if (turnCount == numPlayer) {

			turnCount = 0;
			currRound++;
			activePlayer = origOrderPlayers.get(0);
			setPlayerChange(false);
			// System.out.println("goto LandSelect");
			if (skipLandSelect)
				startRoundAfterSkipLandSelect = true;
			else {

				landSelection = true;
			}// Go to land selection and/or auction. next Round
				// add up production here!
			for (Player p : players)
				p.addPlayerRoundProd();
		} else {
			event();
			if (turnCount == 0) {
				// new round

				createRoundOrder();
				getFoodReq();
			}
			// sets a timer to execute next player function after 60 seconds
			// need to calculate

			// need a better way to do this. tired, need sleep, don't want to
			// figure this out at the moment. sleeeeep
			if (activePlayer.getResources().getFood() == 0) {
				System.out.println("roundTime = 5");
				roundTime = 5;
			} else if (activePlayer.getResources().getFood() < foodReq) {
				System.out.println("roundTime = 30");
				roundTime = 30;
			} else {
				System.out.println("roundTime = 50");
				roundTime = 50;
			}
			// roundTimeDisplay--;
			// System.out.println("roundTime: "+ roundTimeDisplay);
			// executes run() after roundTime seconds. switches to next player
			timer.schedule(new TimerTask() {
				public void run() {

					System.out.println("run run run");
					nextPlayer();
					setPlayerChange(true);
					startRound();

				}
			}, roundTime * 1000);

		}

	}

	/**
	 * This method call EventHandler and pass the chance to it
	 * 
	 * @param p
	 *            A player to add to the player list
	 */
	public void event() {
		Random rand = new Random();
		double chance = rand.nextDouble();
		if (!landSelection) {
			eventDisplay = EventHandler.generation(chance, getCurrRound());
		}
	}

	/**
	 * figure out the next player to go
	 */
	public void nextPlayer() {

		// System.out.println(turnCount);
		turnCount++;
		// System.out.println(turnCount);
		if (!landSelection) {
			if (turnCount < players.size())
				activePlayer = roundOrder.get(turnCount);
		} else {
			if (startRoundAfterSkipLandSelect) {
				startRoundAfterSkipLandSelect = false;
			} else {
				activePlayer = origOrderPlayers.get(turnCount);
			}
		}
	}

	/**
	 * creates the player order for the current round
	 */
	private void createRoundOrder() {
		// roundOrder.clear();
		// System.out.println("RO size post clear:" + roundOrder.size());
		roundOrder = players;
		// System.out.println("RO size post player:" + roundOrder.size());

		Collections.sort(roundOrder, new PlayerComparator());

		activePlayer = roundOrder.get(0);
	}

	/**
	 * 
	 * @return foodreq
	 */
	public int getFoodReq() {
		switch (currRound) {
		case (0):
			foodReq = 3;
			break;
		case (5):
			foodReq = 4;
			break;
		case (9):
			foodReq = 5;
			break;
		default:
			break;
		}
		return foodReq;
	}

	/**
	 * Get the current player
	 * 
	 * @return activePlayer
	 */
	public Player getActivePlayer() {
		if (activePlayer == null) {
			activePlayer = players.get(0);
		}
		return activePlayer;
	}

	/**
	 * getter for round count
	 * 
	 * @return turnCount
	 */
	public int getRoundCount() {
		return turnCount;
	}

	/**
	 * getter for current round
	 * 
	 * @return currRound
	 */
	public int getCurrRound() {
		return currRound;
	}
	
	/**
	 * setter for current round
	 * @param x cuurent round
	 */
	public void setCurrRound(int x){
		currRound = x;
	}

	/**
	 * Getter for Timer
	 * 
	 * @return timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * setter for the timer
	 * 
	 * @param timer1
	 */
	public void setTimer(Timer timer1) {
		timer = timer1;
	}

	/**
	 * setter for the activePlayer
	 * 
	 * @param player
	 */
	public void setActivePlayer(Player player) {
		activePlayer = player;
	}

	/**
	 * setter for the players
	 * 
	 * @param players
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/**
	 * getter for roundTimeDisplay
	 * 
	 * @return roundTimeDisplay
	 */
	public int getroundTimeDisplay() {
		return roundTimeDisplay;
	}

	/**
	 * getter for round time
	 * 
	 * @return roundTime
	 */
	public int getRoundTime() {
		return roundTime * 1000;
	}

	/**
	 * setter fpr roundTime
	 * 
	 * @param time
	 */
	public void setRoundTime(int time) {
		roundTime = time;
	}

	/**
	 * getter for eventDisplay
	 * 
	 * @return
	 */
	public String getEventDisplay() {
		return eventDisplay;
	}

	/**
	 * setter for eventDisplay
	 * 
	 * @param eventDisplay
	 */
	public void setEventDisplay(String eventDisplay) {
		this.eventDisplay = eventDisplay;
	}

	/**
	 * setter for Difficulty
	 * 
	 * @param dificulty
	 */
	public void setDifficulty(String dificulty) {
		// TODO Auto-generated method stub
		this.dificulty = dificulty;
	}

	/**
	 * A comparator class for players
	 * 
	 * @author Brandon
	 * 
	 */
	public class PlayerComparator implements Comparator<Player> {
		public int compare(Player p1, Player p2) {
			return Integer.compare(p1.getScore(), p2.getScore());
		}
	}

}
