package test;
import static org.junit.Assert.*;
import game.EventHandler;

import org.junit.Assert;
import org.junit.Test;

import player.Player;


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
		
		// event handler Ziyi
		EventHandler eh = new EventHandler();
		Assert.assertNotSame("Event happened when it should not.",
				"", eh.generation(0.1, 1));
		
	}

}
