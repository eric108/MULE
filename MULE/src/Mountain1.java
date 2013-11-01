import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Mountain1 extends BasicTyle{
	private final String imageDir = "/resources/images/tiles/mt1.png";
	public Mountain1(){
		try{
			if(getImage() == null) setImage(new Image(imageDir));
		}
		catch(Exception ex){
			//TODO: implement exception handling
			System.out.println(ex.toString());
		}
	}
	public int getOreProduction(){
		if((m != null) && (m.getType() == Mule.ORE)){
			return 2;
		}
		else{
			return 0;
		}
	}
	public int getFoodProduction(){
		if((m != null) && (m.getType() == Mule.FOOD)){
			return 1;
		}
		else{
			return 0;
		}
	}
	public int getEnergyProduction(){
		if((m != null) && (m.getType() == Mule.ENERGY)){
			return 1;
		}
		else{
			return 0;
		}
	}
}
