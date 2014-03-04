package map;

import org.newdawn.slick.Image;

public class LandOffice {
	private final String imageDir = "/resources/images/town/landoffice.png";
	private static Image img;

	public LandOffice() {
		try {
			if (img == null)
				img = new Image(imageDir);
		} catch (Exception ex) {
			// TODO: implement exception handling
			System.out.println(ex.toString());
		}
	}

	public Image getImage() {
		return img;
	}

}
