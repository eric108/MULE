import org.newdawn.slick.Image;


public class Bonzoid implements Race {

	private final String imageDir = "/resources/images/races/bonzoid.png";
	private static Image img;
	
	public Bonzoid(){
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
