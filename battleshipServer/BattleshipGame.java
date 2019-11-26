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
	}
	
	public void addPlayer(int clientID) {
		if (playerOne != 0) {
			playerOne = clientID;
			System.out.println(playerOne + " Connected");
		}
		else if (playerTwo != 0) {
			playerTwo = clientID;
			System.out.println(playerTwo + " Connected");
			pg = new playGame();
			//start game when two players are in
		}
		else {
			//game full
		}
	}
	
	public battleshipComm handleCommunication(battleshipComm arg0, long l) {
		
		pg = games.get(arg0.gameIndex);
		if (pg.placeShips) {
			pg.placeShips(arg0.getp1Turn(), arg0.getboardIndex());
		}
		else {
			pg.turn(arg0.getp1Turn(), arg0.getboardIndex());
		}
		
		//update bsc
		//bsc.setp1Turn(pg.);
		//bsc.setBoarIndex(indexValue);
		//bsc.setDataValue(dataValue);
		//bsc.setGameOver(gameOver);
		//bsc.setMessage(message);
		//bsc.setplaceShips(placeShips);

		//send to server new bsc with updated values
		System.out.println("Send battleshipcomm to client: "+ arg0.getClass());
		return arg0;
	}
	
}
