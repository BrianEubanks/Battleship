package battleshipServer;

import java.util.ArrayList;

public class BattleshipGame {

	//This class will interface the Server Comms to the playGame class
	
	//This list will store all active games
	private ArrayList<playGame> games;
	
	private playGame pg;
	
	
	private int playerOne; //client id?
	private int playerTwo;
	
	public BattleshipGame() {
		games = new ArrayList();
		games.add(new playGame());
	}
	
	
	public void handleCommunication(battleshipComm bsc, int clientID) {
		
		pg = games.get(bsc.gameIndex);
		if (pg.placeShips) {
			pg.placeShips(bsc.getp1Turn(), bsc.getboardIndex());
		}
		else {
			pg.turn(bsc.getp1Turn(), bsc.getboardIndex());
		}
		
		//update bsc
		//bsc.setp1Turn(pg.);
		//bsc.setBoarIndex(indexValue);
		//bsc.setDataValue(dataValue);
		//bsc.setGameOver(gameOver);
		//bsc.setMessage(message);
		//bsc.setplaceShips(placeShips);

		//send to server new bsc with updated values
		
	}
	
}
