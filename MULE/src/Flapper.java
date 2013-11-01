import org.newdawn.slick.Image;


public class Flapper implements Race {

	
	private final String imageDir = "/resources/images/races/flapper.png";
	private static Image img;
	
	public Flapper(){
		try{
			if(img == null) img = new Image(imageDir);
		}
		catch(Exception ex){
			//TODO: implement exception handling
			System.out.println(ex.toString());
		}
	}
	public Image getImage() {
		return img;
	}
}
