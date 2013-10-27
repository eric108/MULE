import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public abstract class BasicTyle implements Tile{
	private int x, y, size;
	public Player owner;
	private Image img;
	
	public void setX(int xCoord) {
		x = xCoord;
	}
	public int getX() {
		return x;
	}
	public void setY(int yCoord) {
		y = yCoord;
		
	}
	public int getY() {
		return y;
	}
	public void setSize(int tileSize){
		size = tileSize;
	}
	public int getSize(){
		return size;
	}
	public boolean isClicked(int mX, int mY){
		return ((mX >= x) && (mX <= (x + size)) && (mY >= y) && (mY <= (y+size)));
	}
	public void setOwner(Player p){
		owner = p;
	}
	public Player getOwner(){
		return owner;
	}
	public Image getImage(){
		return img;
	}
	public void setImage(Image image){
		img = image;
	}
	public void draw(Graphics g){
		if(owner == null){
			// just draw the image in the tiles normal location
			img.draw(x, y, size, size);
		}
		else{
			// add owners color around edges of tile
			g.setColor(owner.color);
			g.fillRect(x, y, size, size);
			img.draw(x+7, y+7, size-15, size-15);
		}
	}
}
