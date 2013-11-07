import org.newdawn.slick.Image;


public class Buzzite implements Race {

	private final String imageDir = "/resources/images/races/buzzite.png";
	private static Image img;
	
	public Buzzite(){
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
