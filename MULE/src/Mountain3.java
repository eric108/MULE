import org.newdawn.slick.Image;


public class Mountain3 extends BasicTyle {
	private final String imageDir = "/resources/images/tiles/mt3.png";
	public Mountain3(){
		try{
			if(getImage() == null) setImage(new Image(imageDir));
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
	@Override
	public int getFoodProduction() {
		if((m != null) && (m.getType() == Mule.FOOD)){
			return 1;
		}
		else{
			return 0;
		}
	}
	@Override
	public int getOreProduction() {
		if((m != null) && (m.getType() == Mule.ORE)){
			return 4;
		}
		else{
			return 0;
		}
	}
	@Override
	public int getEnergyProduction() {
		if((m != null) && (m.getType() == Mule.ENERGY)){
			return 1;
		}
		else{
			return 0;
		}
	}

}
