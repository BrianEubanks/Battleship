package battleshipServer;

import java.util.ArrayList;

public class BattleshipGame {

	//This class will interface the Server Comms to the playGame class
	
	//This list will store all active games
	private ArrayList<playGame> games;
	
	private playGame pg;
	
	
	private long playerOne; //client id?
	private long playerTwo;
	
	public BattleshipGame() {
		playerOne = 0;
		playerTwo = 0;
		//pg = new playGame();
	}
	
	public void addPlayer(long l) {
		if (playerOne != 0) {
			playerOne = l;
			System.out.println(playerOne + " Connected");
		}
		else if (playerTwo != 0) {
			playerTwo = l;
			System.out.println(playerTwo + " Connected");
			pg = new playGame();
			//start game when two players are in
		}
		else {
			//game full
		}
	}
	
	public battleshipComm handleCommunication(battleshipComm arg0, long l) {
		
		System.out.println("start handle Comms");
		//pg = games.get(arg0.gameIndex);
		//if (pg.placeShips) {
		//	pg.placeShips(arg0.getp1Turn(), arg0.getboardIndex());
		//}
		//else {
		//	pg.turn(arg0.getp1Turn(), arg0.getboardIndex());
		//}
		
		//update bsc
		pg = new playGame();
		System.out.println(pg);
		System.out.println(pg.getClass());
		pg.turn(arg0.getp1Turn(), arg0.getboardIndex());
		
		//send to server new bsc with updated values
		System.out.println("Send battleshipcomm to client: "+ arg0.getClass());
		return pg.getComms();
	}
	
}
