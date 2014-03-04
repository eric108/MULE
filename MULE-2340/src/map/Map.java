package map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Graphics;

import player.Player;

public class Map implements java.io.Serializable {
	// make sure to change these if we change map dimensions
	public final static int numbRows = 5;
	public final static int numbCols = 9;
	public Tile[][] map;

	public Map(boolean random) {
		map = generateTileMap(random);
	}

	/**
	 * Creates 2d array of integers that represents the tile map to be created 0
	 * - plains 1 - mountain1 2 - mountain2 3 - mountain3 4 - river 5 - town
	 * 
	 * @return
	 */
	public int[][] randArrayGenerator() {
		int[][] list = new int[5][9];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				int ndx = (int) (Math.random() * 4);
				if (j == 4)
					if (i == 2)
						list[i][j] = 5;
					else
						list[i][j] = 4;
				else
					list[i][j] = ndx;
				// System.out.print(list[i][j] + " ");
			}
			// System.out.println("");
		}
		return list;
	}

	/**
	 * Generic map
	 * 
	 * @return
	 */
	public int[][] arrayGenerator() {

		int[][] list = new int[5][9];
		// input river and town
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				list[i][j] = 0;
				if (j == 4) {
					if (i == 2) {
						list[i][j] = 5;
					} else {
						list[i][j] = 4;
					}
				}

			}

		}
		// hardcode other tiles
		list[0][2] = 1;
		list[0][6] = 3;
		list[1][1] = 1;
		list[1][8] = 3;
		list[2][0] = 3;
		list[2][8] = 1;
		list[3][1] = 2;
		list[3][6] = 2;
		list[4][2] = 2;
		list[4][8] = 2;

		return list;
	}

	/**
	 * generates the map of the tile
	 * 
	 * @param randMap
	 * @return Tile[][]
	 */
	public Tile[][] generateTileMap(boolean randMap) {
		Tile[][] tmap = new Tile[5][9];
		int[][] imap;
		if (randMap) {
			imap = randArrayGenerator();
		} else {
			imap = arrayGenerator();
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				switch (imap[i][j]) {
				case (0):
					tmap[i][j] = TileFactory.createTile("PLAIN");
					break;
				case (1):
					tmap[i][j] = TileFactory.createTile("MOUNTAIN1");
					break;
				case (2):
					tmap[i][j] = TileFactory.createTile("MOUNTAIN2");
					break;
				case (3):
					tmap[i][j] = TileFactory.createTile("MOUNTAIN3");
					break;
				case (4):
					tmap[i][j] = TileFactory.createTile("RIVER");
					break;
				case (5):
					tmap[i][j] = TileFactory.createTile("TOWN");
					break;

				}

			}

		}
		return tmap;

	}

	/**
	 * 
	 * @param tileSize
	 * @param mapLMargin
	 * @param mapTopMargin
	 */
	public void setMapTileCoords(int tileSize, int mapLMargin, int mapTopMargin) {
		int currH = mapLMargin;
		int currV = mapTopMargin;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				map[row][col].setX(currH);
				map[row][col].setY(currV);
				map[row][col].setSize(tileSize);
				currH += tileSize;
			}
			currV += tileSize;
			currH = mapLMargin;
		}
	}

	/**
	 * retrieves a tile object from map
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Tile getTile(int row, int col) {
		return map[row][col];
	}

	/**
	 * Get's the first tile that is ownable and is not owned
	 */
	public Tile getFirstAvailableTile() {
		for (int row = 0; row < Map.numbRows; row++) {
			for (int col = 0; col < Map.numbCols; col++) {
				if ((getTile(row, col).getOwner() == null)
						&& !(getTile(row, col) instanceof Town)
						&& !(getTile(row, col) instanceof River))
					return getTile(row, col);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public LinkedList<Tile> getPlayerTiles(Player p) {
		LinkedList pTiles = new LinkedList();
		for (int row = 0; row < Map.numbRows; row++) {
			for (int col = 0; col < Map.numbCols; col++) {
				if (getTile(row, col).getOwner() == p) {
					pTiles.add(getTile(row, col));
				}
			}
		}
		return pTiles;
	}

	/**
	 * Checks to see if a tile has been clicked
	 * 
	 * @param mX
	 * @param mY
	 * @return returns clicked tile if there is one, null if not
	 */
	public Tile tileClicked(int mX, int mY) {
		for (int row = 0; row < Map.numbRows; row++) {
			for (int col = 0; col < Map.numbCols; col++) {
				if (getTile(row, col).isClicked(mX, mY)) {
					return getTile(row, col);
				}
			}
		}
		return null;
	}

	/**
	 * draws the map
	 * 
	 * @param g
	 */
	public void drawMap(Graphics g) {
		for (int row = 0; row < Map.numbRows; row++) {
			for (int col = 0; col < Map.numbCols; col++) {
				getTile(row, col).draw(g);
			}
		}
	}

}
