import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;


	/**
	 * 
	 * @author Ziyi Jiang
	 *
	 */
	public class Button {

		String buttonName;
		Rectangle buttonShape;
	    int xLoc, yLoc, width, height;
	    Font categoryFont= new Font("Impact", Font.PLAIN, 20);
	    TrueTypeFont h2 = new TrueTypeFont(categoryFont, true);
	    
		public Button(int x, int y, int width, int height,String name) {
	        buttonShape = new Rectangle(x, y, width, height);
	        buttonName = name;
	        xLoc = x;
	        yLoc = y;
	        this.width = width;
	        this.height = height;
	
	    }
	    /**
	     *  checks whether the x and y value are inside the buttons rectangle
	     * @param x  
	     * @param y
	     * @return boolean, true if the x and y point is in the rectangle
	     */
	    //where x and y define the location of the mouse cursor
	    public boolean checkClick(int x, int y) {
	        return buttonShape.contains(x, y);
	    }
	    /**
	     * getter for button name
	     * @return String, the name of the button
	     */
	    public String getButtonName(){
	    	return buttonName;
	    }
	    /**
	     * getter the the rectangle that makes the outside of the button
	     * @return Rectangle, the button parameters
	     */
	    public Rectangle getButtonShape(){
	    	return buttonShape;
	    }
	    /**
	     * setter for button name
	     * @param name
	     */
	    public void setButtonName(String name){
	    	this.buttonName = name;
	    }
	    
	    
	    
	    public void drawButton(Graphics g) {
	    	g.setColor(Color.blue);
	    	g.fill(buttonShape);
	    	g.setColor(Color.white);
	    	g.setFont(h2);
	    	g.drawString(buttonName, xLoc+width/10, yLoc+height/4);
	    }
	    
	    public boolean leftMouseClick(GameContainer gc) {
	    	int x;
	    	int y;
	    	
	    	Input input = gc.getInput();
	    	if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				x = input.getMouseX();
				y = input.getMouseY();
				return checkClick(x,y);
			}
	    	
	    	return false;
	    }

	}
