package battleshipServer;

import java.io.IOException;
import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

public class BattleshipGame {

	//This class will interface the Server Comms to the playGame class
	
	//This list will store all active games
	private ArrayList<playGame> games;
	
	private playGame pg;
	
	
	private ConnectionToClient playerOne; //client id?
	private ConnectionToClient playerTwo;
	private long playerOneID; //client id?
	private long playerTwoID;
	
	public BattleshipGame() {
		playerOneID = 0;
		playerTwoID = 0;
		//pg = new playGame();
	}
	
	public boolean addPlayer(ConnectionToClient c) {
		
		if (playerOneID == 0) {
			playerOneID = c.getId();
			playerOne = c;
			System.out.println(playerOne + " Connected");
			return true;
		}
		else if (playerTwoID == 0) {
			playerTwoID = c.getId();
			playerTwo = c;
			System.out.println(playerTwo + " Connected");
			pg = new playGame();
			return false;
			//start game when two players are in
		}
		else {
			//game full
			return false;
		}
	}
	
	public void handleCommunication(battleshipComm arg0, long l) {
		
		System.out.println("start handle Comms");
		//pg = games.get(arg0.gameIndex);
		//if (pg.placeShips) {
		//	pg.placeShips(arg0.getp1Turn(), arg0.getboardIndex());
		//}
		//else {
		//	pg.turn(arg0.getp1Turn(), arg0.getboardIndex());
		//}
		
		//update bsc
		//pg = new playGame();
		System.out.println(pg);
		System.out.println(pg.getClass());
		pg.turn(arg0.getp1Turn(), arg0.getboardIndex(), arg0.getp1BoardClick());
		
		//send to server new bsc with updated values
		System.out.println("Send battleshipcomm to client: "+ arg0.getClass());
		
		try {
			playerOne.sendToClient(pg.getComms());
			playerTwo.sendToClient(pg.getComms());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//return pg.getComms();
	}
	
}
