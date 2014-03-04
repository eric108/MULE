package map;

public class TileFactory {
	public static Tile createTile(String type){
		if(type == "PLAIN") return new Plain();
		else if(type == "MOUNTAIN1") return new Mountain1();
		else if(type == "MOUNTAIN2") return new Mountain2();
		else if(type == "MOUNTAIN3") return new Mountain3();
		else if(type == "RIVER") return new River();
		else if(type == "TOWN") return new Town();
		return null;
	}
}
