import org.newdawn.slick.Image;


public class Mountain2 extends BasicTyle{
	private final String imageDir = "/resources/images/tiles/mt2.png";
	public Mountain2(){
		try{
			
			if(getImage() == null)	setImage(new Image(imageDir));
		}
		catch(Exception ex){
			System.out.println(ex.toString());
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
	public int getOreProduction(){
		if((m != null) && (m.getType() == Mule.ORE)){
			return 3;
		}
		else{
			return 0;
		}
	}
}
