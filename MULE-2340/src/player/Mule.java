package player;

import java.io.Serializable;

import map.Tile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ui_components.UserFonts;

public class Mule implements Serializable {
	public static final String ORE = "ORE";
	public static final String FOOD = "FOOD";
	public static final String ENERGY = "ENERGERY";
	public final int HEIGHT = 25;
	public final int WIDTH = 50;
	boolean planted;
	private Player owner;
	private Tile tile;
	private int x, y;
	private String type;

	public Mule(String typ, Player owner) {
		type = typ;
		this.owner = owner;
	}

	public void setTile(Tile t) {
		tile = t;
	}

	public Tile getTile() {
		return tile;
	}

	public String getType() {
		return type;
	}

	public void drawMule(Graphics g) {
		// draw body
		if (tile != null) {
			x = tile.getX() + 5;
			y = tile.getY() + 5;
		} else {
			x = owner.getX() + 40;
			y = owner.getY() - 15;
		}
		g.setColor(owner.getColor());
		g.setFont(UserFonts.fntMule);
		// body
		g.fillRect(x + 15, y + 15, WIDTH, HEIGHT);
		// head
		g.fillOval(x, y + 5, 20, 12);
		// legs
		g.fillRect(x + 17, (y + 15) + HEIGHT, 5, 15);
		g.fillRect(x + 22, (y + 15) + HEIGHT, 5, 15);
		g.fillRect(x + 42, (y + 15) + HEIGHT, 5, 15);
		g.fillRect(x + 47, (y + 15) + HEIGHT, 5, 15);
		g.setColor(Color.gray);
		g.drawString(type, x + 18, y + 18);

	}
}
