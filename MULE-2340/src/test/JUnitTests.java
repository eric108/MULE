package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import game.EventHandler;
import game.GameData;
import map.*;

import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import player.Mule;
import player.Player;
import states.MainController;


public class JUnitTests {



	@Test
	public void testPlayerSoldFood() {
		Player p1 = new Player();
		p1.getResources().setEasy();
		Assert.assertEquals("Trade successfully went through and shouldn't have.", false, p1.soldFood(9));
		Assert.assertEquals("Trade didn't successfully go through.", true, p1.soldFood(5));
		Assert.assertEquals("Player sold negative food.", false, p1.soldFood(-5));
		p1.setGold(200);
		p1.setFood(3);
		p1.soldFood(2);
		Assert.assertEquals("Correct gold didn't get added to player.", 260, p1.getGold());
	
		
	}
	@Test
	public void testFoodRequirements() throws SlickException
	{	

		GameData.getInstance().setCurrRound(0);
		Assert.assertEquals("Round 0 =has the right food requirements", 3, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(1);
		Assert.assertEquals("Round 0 =has the right food requirements", 3, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(2);
		Assert.assertEquals("Round 0 =has the right food requirements", 3, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(3);
		Assert.assertEquals("Round 0 =has the right food requirements", 3, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(4);
		Assert.assertEquals("Round 0 =has the right food requirements", 3, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(5);
		Assert.assertEquals("Round 0 =has the right food requirements", 4, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(6);
		Assert.assertEquals("Round 0 =has the right food requirements", 4, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(7);
		Assert.assertEquals("Round 0 =has the right food requirements", 4, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(8);
		Assert.assertEquals("Round 0 =has the right food requirements", 4, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(9);
		Assert.assertEquals("Round 0 =has the right food requirements", 5, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(10);
		Assert.assertEquals("Round 0 =has the right food requirements", 5, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(11);
		Assert.assertEquals("Round 0 =has the right food requirements", 5, GameData.getInstance().getFoodReq());
		GameData.getInstance().setCurrRound(12);
		Assert.assertEquals("Round 0 =has the right food requirements", 5, GameData.getInstance().getFoodReq());
		
		
	
	
	@Test
	public void testEventHandler(){
		GameData.getInstance().setActivePlayer(new Player());
		GameData.getInstance().getActivePlayer().setEnergy(10);
		GameData.getInstance().getActivePlayer().setFood(10);
		GameData.getInstance().getActivePlayer().setGold(500);
		GameData.getInstance().getActivePlayer().setOre(10);
		Player secPlayer =new Player();
		secPlayer.setEnergy(10);
		secPlayer.setFood(10);
		secPlayer.setGold(1000);
		secPlayer.setOre(10);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(GameData.getInstance().getActivePlayer());
		players.add(secPlayer);
		GameData.getInstance().setPlayers(players);
		
		Assert.assertNotNull(EventHandler.generation(0.2, 1));
		Assert.assertEquals("Event fails to happen due to small chance.",
				"", EventHandler.generation(0.5, 1));

	}
	@Test
	public void testRandomMapGenerator(){

		//constructor calls generateTileMap(boolean random)
		Map aMap = new Map(true);
		int[][] notRandom = aMap.arrayGenerator();
		int[][] random = aMap.randArrayGenerator();
		int[][] random2 = aMap.randArrayGenerator();
		int[][] random3 = aMap.randArrayGenerator();
		int[][] random4 = aMap.randArrayGenerator();
		
		assertFalse("random generated array is different then the standard map",Arrays.equals(random, notRandom) );
		assertFalse(Arrays.equals(random2, notRandom) );
		assertFalse(Arrays.equals(random2, random) );
		
		assertFalse(Arrays.equals(random3, notRandom) );
		assertFalse(Arrays.equals(random3, random) );
		assertFalse(Arrays.equals(random3, random2) );
		
		assertFalse(Arrays.equals(random4, notRandom) );
		assertFalse(Arrays.equals(random4, random) );
		assertFalse(Arrays.equals(random4, random2) );
		assertFalse(Arrays.equals(random4, random3) );
		
	}
	
	@Test
	public void testPlayerOreRoundProd(){
		Player ply = new Player();
		ply.buyMule("ORE");
		GameData data = GameData.getInstance();
		data.map = new Map(false);
		//Tile t = data.map.getFirstAvailableTile();
		Tile t = data.map.getTile(4, 2);
		t.setMule(ply.getLastMule());
		t.setOwner(ply);
		ply.setEnergy(100);
		ply.setFood(100);
		ply.setGold(100);
		ply.setOre(100);
		
		ply.addPlayerRoundProd();
		if(t instanceof Mountain1){
			assertEquals(102, ply.getOre());
		}
		else if(t instanceof Mountain2){
			assertEquals(103, ply.getOre());
		}
		else if(t instanceof Mountain3){
			assertEquals(104, ply.getOre());
		}
		else if(t instanceof River){
			assertEquals(100, ply.getOre());
		}
		else if(t instanceof Plain){
			assertEquals(101, ply.getOre());
		}
		
	}
}
