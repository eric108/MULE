import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public interface Tile {
	public Image getImage();
	public void setX(int xCoord);
	public int getX();
	public void setY(int yCoord);
	public int getY();
	public void setSize(int size);
	public int getSize();
	public boolean isClicked(int x, int y);
	public Player getOwner();
	public void setOwner(Player p);
	public void draw(Graphics g);
	public void setMule(Mule m);
	public Mule getMule();
	public int getFoodProduction();
	public int getOreProduction();
	public int getEnergyProduction();
}
