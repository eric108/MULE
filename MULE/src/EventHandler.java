import java.util.Random;


public class EventHandler {

private static int m;
private static String e1;
private static String e2;
private static String e3;
private static String e4;
private static String e5;
private static String e6;
private static String e7;


	public EventHandler(){
		m = 0;
		e1 = "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI "
				+ "CONTAINING 3 FOOD AND 2 ENERGY UNITS.";
		e2 = "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY"
				+ " BY LEAVING TWO BARS OF ORE.";
		e3 = "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR";
		e4 = "YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR";
		e5 = "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST";
		e6 = "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED "
				+ "AND STOLE HALF YOUR FOOD.";
		e7 = "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN."
				+ " IT COST YOU";

		
	}
	public static String generation(double chance, int currentRound) {
		System.out.println("event happended");
		if (chance >=0.27){
			return "";
		}
		System.out.println("event really happended");
		if(currentRound==0 || currentRound==1 || currentRound==2){
			m = 25;
		}else if(currentRound==3 || currentRound==4 || currentRound==5|| 
				currentRound==6){
			m = 50;
		}else if(currentRound==7 || currentRound==8 || currentRound==9|| 
				currentRound==10){
			m = 75;
		}else{
			m = 100;
		}
		Random rand = new Random();
		int index = rand.nextInt(7)+1;
		// record the info and save time below
		int food = GameData.getActivePlayer().getResources().getFood();
		int energy = GameData.getActivePlayer().getResources().getEnergy();
		int ore = GameData.getActivePlayer().getResources().getOre();
		int gold = GameData.getActivePlayer().getResources().getGold();
		if(index==1){
			GameData.getActivePlayer().getResources().setFood(food+3);
			GameData.getActivePlayer().getResources().setFood(energy+2);
			return e1;
		}else if(index ==2){
			GameData.getActivePlayer().getResources().setOre(ore+2);
			return e2;
		}else if(index ==3){
			GameData.getActivePlayer().getResources().setGold(gold+8*m);
			return e3+ " " + 8*m+"!";
		}else if(index ==4){
			GameData.getActivePlayer().getResources().setGold(gold+2*m);
			return e4 + " " + 2*m + "!";
		}
		//check if the player has lowest score
		for (Player player: GameData.getPlayers()){
			if(player.getScore()< score){
				return "";
			}
		}
		for (Player player: GameData.getPlayers()){
			if(player.getResources().getGold()< gold){
				return "";
			}
		}
		if(index==5){
			if(gold>=4*m){
				GameData.getActivePlayer().getResources().setGold(gold-4*m);		
			}else{
				GameData.getActivePlayer().getResources().setGold(0);		

			}
			return e5+ " "+ 4*m+ "!";
		}else if(index==6){
			GameData.getActivePlayer().getResources().setFood(food/2);
			return e6;
		}else if (index ==7){
			if(gold>=6*m){
				GameData.getActivePlayer().getResources().setGold(gold-6*m);		
			}else{
				GameData.getActivePlayer().getResources().setGold(0);		

			}
			return e7+ " "+ 6*m+ "!";
		}
		return "";
		
		
		
	}

}
