package ui_components;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * @author Brandon Duarte
 * 
 */
public class Button {

	String buttonName;
	Rectangle buttonShape;
	int xLoc, yLoc, width, height;
	Font categoryFont = new Font("Impact", Font.PLAIN, 20);
	TrueTypeFont h2 = new TrueTypeFont(categoryFont, true);
	private boolean disabled;

	public Button(int x, int y, int width, int height, String name) {
		buttonShape = new Rectangle(x, y, width, height);
		buttonName = name;
		xLoc = x;
		yLoc = y;
		this.width = width;
		this.height = height;
		disabled = false;
	}

	/**
	 * checks whether the x and y value are inside the buttons rectangle
	 * 
	 * @param x
	 * @param y
	 * @return boolean, true if the x and y point is in the rectangle
	 */
	// where x and y define the location of the mouse cursor
	public boolean checkClick(int x, int y) {
		if (!disabled)
			return buttonShape.contains(x, y);
		return false;
	}

	/**
	 * getter for button name
	 * 
	 * @return String, the name of the button
	 */
	public String getButtonName() {
		return buttonName;
	}

	/**
	 * getter the the rectangle that makes the outside of the button
	 * 
	 * @return Rectangle, the button parameters
	 */
	public Rectangle getButtonShape() {
		return buttonShape;
	}

	/**
	 * setter for button name
	 * 
	 * @param name
	 */
	public void setButtonName(String name) {
		this.buttonName = name;
	}

	/**
	 * This method paint the button component
	 * 
	 * @param g
	 *            Graphics
	 * @param c
	 *            Color
	 */
	public void drawButton(Graphics g, Color c) {
		if (!disabled) {
			g.setColor(c);
		} else {
			g.setColor(Color.gray);
		}
		g.fill(buttonShape);
		g.setColor(Color.white);
		g.setFont(h2);
		g.drawString(buttonName, xLoc + width / 10, yLoc + height / 4);
	}

	/**
	 * Check to see if where someone clicks is in the button
	 * 
	 * @param gc
	 *            GameContainer
	 * @return boolean on if it the click was in the button
	 */
	public boolean leftMouseClick(GameContainer gc) {
		int x;
		int y;

		Input input = gc.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			x = input.getMouseX();
			y = input.getMouseY();
			return checkClick(x, y);
		}

		return false;
	}

	/**
	 * Disables the functionality of the button
	 */
	public void disable() {
		disabled = true;
	}

	/**
	 * Enables the functionality of the button
	 */
	public void enable() {
		disabled = false;
	}

}