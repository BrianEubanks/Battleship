package battleshipServer;

public class playGame {

	
	private battleshipComm bsc;
	
	/* Board Data is used to store the contents of each player's 
			board. Each number element of the array corresponds to the
			tile on the board. 
			
	// 0 - ship length 2
	// 1 - ship length 3
	// 2 - ship length 3
	// 3 - ship length 4
	// 4 - ship length 5
	// 5 - ship hit
	// 6 - water
	// 7 - water miss
	 
	*/
	private int[] p1boardData = new int [100];
	private int[] p2boardData = new int [100];
	
	/* Ship Data is used to store the player's ships.
	 * Each INDEX of the shipData array corresponds to 
	 * a player's ship. The data in the INDEX element is an int
	 * that stores how many hits remain until the ship is sunk.
	 * ie. (the lenght of the ship)
	 * 
	 * Each time a ship is hit, it's data is decremented.
	 * When a ships data = 0, the ship is sunk
	 * When all the ships are sunk, the game is over.
	 * 
	 * ShipCount stores the number of ships
	
	
	
	*/
	private int[] p1shipData = new int[5];
	private int[] p2shipData = new int[5];
	private int p1shipCount;
	private int p2shipCount;
	
	//Turn
	// used to determine whose turn it is
	// used to determine which board to check and edit.
	// true - player1
	// false - player 2
	public boolean placeShips;
	private boolean placeShipsp1;
	private boolean placeShipsp2;
	private boolean p1Turn;
	private boolean validMove;
	
	//Message to Client
	// Used to display the result of the turn to the player
	private String messageToPlayer;
	
	
	public playGame() {
		//Set p1 and p2 Boards to All Water
		for(int index = 0; index < p1boardData.length; index++) {
			p1boardData[index]=6;
			p2boardData[index]=6;
		}
		//Set p1 and p2 Ships to 0
//		for(int index = 0; index < p1shipData.length; index++) {
//			p1shipData[index]=0;
//			p2shipData[index]=0;
//		}
		//Set ShipData to initial lenghts
		//Set Counts to 0; Count will be initialized as the player adds ships to the board
		p1shipData[0]=2;
		p1shipData[1]=3;
		p1shipData[2]=3;
		p1shipData[3]=4;
		p1shipData[4]=4;
		p1shipCount=0;
		p2shipData[0]=2;
		p2shipData[1]=3;
		p2shipData[2]=3;
		p2shipData[3]=4;
		p2shipData[4]=4;		
		p2shipCount=0;
		
		//Players will place ships
		
		//Players will guess where their opponents ships are
		//When all ships are placed, set to false
		
		placeShipsp1 = true;
		placeShipsp2 = true;
		placeShips = placeShipsp1 || placeShipsp2;
		
		//when place ships is false, the game starts.
		
		placeShips=true;
		
		//Set player1 turn to true. Use this to alternate turns in gameplay
		p1Turn = true;
		validMove=false;

	}
	
	//For now!

	// Add Check to rotate ships vertical?
	
	public boolean addShipToP1Board(int boardIndex,boolean rightclick) {
		int index = 0;
		int checkIndex = 0;
		int shipDataPoints = p1shipData[p1shipCount];
		System.out.println("Right Click? "+rightclick);
		
		if(!placeShipsp1) {
			messageToPlayer = new String("All of your ships are placed, waiting on Player 2");
			return false;
			
		}
		//Left Click, add Horizontal ship
		if(!rightclick) {
			//check for wrapping at end of line
			if((boardIndex%10)+shipDataPoints>9) {
				messageToPlayer = new String("Can't add a ship here, Player 1");

				return false;
			}
			
			//check for overlap before adding ship
			while (checkIndex < shipDataPoints) {
				if(p1boardData[boardIndex+checkIndex]!=6){
					//if any data point is not water, there is an overlap with another ship
					messageToPlayer = new String("Can't add a ship here, Player 1");
					return false;
				}
				checkIndex++;
			}
			// add the ship to the boardData
			while (index < shipDataPoints) {
				//p1boardData[boardIndex+index]=p1shipData[p1shipCount];
				p1boardData[boardIndex+index]=p1shipCount;
				index++;
			}
		}
		//right click, add vertical ship
		else if(rightclick) {
			//check for wrapping at end of line
			if(boardIndex+(shipDataPoints*10)>99) {
				messageToPlayer = new String("Can't add a ship here, Player 1");

				return false;
			}
			
			//check for overlap before adding ship
			while (checkIndex < shipDataPoints) {
				if(p1boardData[boardIndex+(checkIndex*10)]!=6){
					//if any data point is not water, there is an overlap with another ship
					messageToPlayer = new String("Can't add a ship here, Player 1");
					return false;
				}
				checkIndex++;
			}
			// add the ship to the boardData
			while (index < shipDataPoints) {
				//p1boardData[boardIndex+index]=p1shipData[p1shipCount];
				p1boardData[boardIndex+(index*10)]=p1shipCount;
				index++;
			}	
		}
		
		System.out.println("P1ShipData & Count: "+p1shipData[p1shipCount]+" "+p1shipCount);
		System.out.println("P1BoardData"+p1boardData[boardIndex]);
		
		p1shipCount++;
		messageToPlayer = new String("Player 1: Place "+(4-p1shipCount)+" more ships.");
		if(p1shipCount>3) {
			placeShipsp1 = false;
			messageToPlayer = new String("PLAYER 1 SHIPS DONE, waiting on player 2");
			System.out.println("PLAYER 1 SHIPS DONE");
		}
		if((!placeShipsp1) && (!placeShipsp2)) {
			placeShips = false;
			p1Turn = true;
			messageToPlayer = new String("DONE placing ships! Your move player 1");
			System.out.println("PLAYER 1 SHIPS DONE");
			//validMove=false;
		}
		
		
		return true;
	}
	
	public boolean addShipToP2Board(int boardIndex,boolean rightclick) {
		int index = 0;
		int checkIndex = 0;
		int shipDataPoints = p2shipData[p2shipCount];
		System.out.println("ShipDataPoints: "+shipDataPoints);
		System.out.println("Right Click? "+rightclick);
		
		if(!placeShipsp2) {
			messageToPlayer = new String("All of your ships are placed, waiting on Player 1");
			return false;
		}
		
		if(!rightclick) {
			//check for wrapping at end of line
			if((boardIndex%10)+shipDataPoints>9) {
				messageToPlayer = new String("Can't add a ship here, Player 2");

				return false;
			}
			
			//check for overlap before adding ship
			while (checkIndex < shipDataPoints) {
				if(p2boardData[boardIndex+checkIndex]!=6){
					//if any data point is not water, there is an overlap with another ship
					messageToPlayer = new String("Can't add a ship here, Player 2");
					return false;
				}
				checkIndex++;
			}
			// add the ship to the boardData
			while (index < shipDataPoints) {
				//p2boardData[boardIndex+index]=p2shipData[p2shipCount];
				p2boardData[boardIndex+index]=p2shipCount;
				System.out.println(p2boardData[boardIndex+index]);
				index++;
			}
		}
		else if(rightclick) {
			//check for wrapping at end of line
			if(boardIndex+(shipDataPoints*10)>99) {
				messageToPlayer = new String("Can't add a ship here, Player 2");

				return false;
			}
			
			//check for overlap before adding ship
			while (checkIndex < shipDataPoints) {
				if(p2boardData[boardIndex+(checkIndex*10)]!=6){
					//if any data point is not water, there is an overlap with another ship
					messageToPlayer = new String("Can't add a ship here, Player 2");
					return false;
				}
				checkIndex++;
			}
			// add the ship to the boardData
			while (index < shipDataPoints) {
				//p2boardData[boardIndex+index]=p2shipData[p2shipCount];
				p2boardData[boardIndex+(index*10)]=p2shipCount;
				System.out.println(p2boardData[boardIndex+index]);
				index++;
			}
		}

		p2shipCount++;
		messageToPlayer = new String("Player 2: Place "+(4-p2shipCount)+" more ships.");
		
		if(p2shipCount>3) {
			placeShipsp2 = false;
			messageToPlayer = new String("PLAYER 2 SHIPS DONE, waiting on player 1");
			System.out.println("PLAYER 2 SHIPS DONE");
		}
		if((!placeShipsp1) && (!placeShipsp2))  {
			placeShips = false;
			p1Turn = true;
			messageToPlayer = new String("DONE placing ships! Your move player 1");
			//validMove=false;
		}
		
		return true;
	}
	
	public void placeShips(boolean player1, int boardIndex, boolean boardClick,boolean rightclick) {
		System.out.println("Player1 placeShips: "+ p1Turn+" --------------------------");
		System.out.println("Data Comms Received:");
		System.out.println("player1: "+player1);
		System.out.println("boardIndex: "+boardIndex);
		System.out.println("p1boardClick "+ boardClick);
		//assign ship to boarddata
		//initialize shipdata to length
		if (player1 != boardClick) {
			// its not your turn!!
			// send back to client
			if(player1) {
				messageToPlayer = new String("Player 1! Place Ships on your own board!!!");
			
			}
			else {
				messageToPlayer = new String("Player 2! Place Ships on your own board!!!");
				
			}
			validMove=false;
			
			System.out.println(messageToPlayer);
		}
		//place player 1's ships
		else if(player1) {
			switch(p1boardData[boardIndex]) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				//ship
				//change to shiphit
				//decrement ship no
				messageToPlayer = new String("There is already a ship here!");
				validMove=false;
				//p1shipData[p1boardData[boardIndex]]--;
				//p1boardData[boardIndex]=5;
				//p1Turn = false;
				break;
			case 5:
				//already hit here
				//does not change turn
				messageToPlayer = new String("Board Data Error: 5");
				validMove=false;
				break;
			case 6:
				//water
				//Place a ship here********************************************
				//messageToPlayer = new String("AddShipToP1Board");
				//p1boardData[boardIndex]=7;
				validMove = addShipToP1Board(boardIndex, rightclick);
				System.out.println(messageToPlayer+" "+validMove);
				break;
			case 7:
				//watermiss
				//already missed here
				//does not change turn
				messageToPlayer = new String("Board Data Error: 7");
				validMove=false;
				break;
			default:
				//data error
				messageToPlayer = new String("Board Data Error, something went wrong");
				validMove=false;
				break;
			}
		}
		//place player 2's ships
		else if(!player1) {
			switch(p2boardData[boardIndex]) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				//ship
				//change to shiphit
				//decrement ship no
				messageToPlayer = new String("There is already a ship here!");
				//p1shipData[p1boardData[boardIndex]]--;
				//p1boardData[boardIndex]=5;
				validMove=false;
				break;
			case 5:
				//already hit here
				//does not change turn
				messageToPlayer = new String("Board Data Error: 5");
				validMove=false;
				break;
			case 6:
				//water
				//Place a ship here********************************************
				//messageToPlayer = new String("AddShiptoP2Board");
				//p1boardData[boardIndex]=7;
				validMove = addShipToP2Board(boardIndex, rightclick);
				System.out.println(messageToPlayer+" "+validMove);
				break;
			case 7:
				//watermiss
				//already missed here
				//does not change turn
				messageToPlayer = new String("Board Data Error: 7");
				validMove=false;
				break;
			default:
				//data error
				messageToPlayer = new String("Board Data Error, something went wrong");
				validMove=false;
				break;
			}
		}
		bsc = new battleshipComm(boardIndex);
		if(player1) {
			bsc.setDataValue(p1boardData[boardIndex]);
			System.out.println("p1board data at click: "+p1boardData[boardIndex]);
			System.out.println("p1shipcount: "+p1shipCount);
		}
		else {
			bsc.setDataValue(p2boardData[boardIndex]);
			System.out.println("p2board data at click: "+p2boardData[boardIndex]);
			System.out.println("p2shipcount: "+p2shipCount);
		}
		
		
		bsc.setMessage(messageToPlayer);
		bsc.setp1Turn(p1Turn);
		bsc.setp1(player1);
		bsc.setValidMove(validMove);
		bsc.setp1BoardClick(boardClick);
		bsc.setrightClick(rightclick);
		System.out.println(validMove+" " + messageToPlayer+" "+bsc.getDataValue()+" "+ boardIndex);
		System.out.println("P1 Ship Data: "+p1shipData[p1shipCount]+" "+p1shipCount);
		System.out.println("P2 Ship Data: "+p2shipData[p2shipCount]+" "+p2shipCount);
		System.out.println("P1placeships: "+placeShipsp1);
		System.out.println("P2placeships: "+placeShipsp2);
		System.out.println("Placeships: "+placeShips);
		
		
	}
	
	
	//Check to make sure a ship wasn't sunk during turn
	//If all ships are sunk game over
	// run endGame
	public void checkShips(boolean player1, int shipNo, int shipSize){
		System.out.println("CheckShips: "+player1);
		if (player1) {
			//check player 2 ships
			if(p2shipData[shipNo]==0) {
				p2shipCount--;
			}
			System.out.println("ShipNo: "+shipNo);
			System.out.println("ShipSize: "+shipSize);
			System.out.println(p2shipCount);
			if(p2shipCount==0) {
				p1Turn=true;
				endGame(new battleshipComm(0));
			}
			
		}
		else {
			//check player 1 ships
			int i = 0;
			while (i < 4) {
				if(p1shipData[i]==0) {
					p1shipCount--;
					p1shipData[i]=-1;//don't double count
				}
				i++;
			}
			if(p1shipCount==0) {
				p1Turn=false;
				endGame(new battleshipComm(0));
			}
		}
	}
	
	public void turn(boolean player1, int boardIndex, boolean boardClick) {
		System.out.println("Player1 Turn: "+ p1Turn+" --------------------------");
		System.out.println("Data Comms Received:");
		System.out.println("player1: "+player1);
		System.out.println("boardIndex: "+boardIndex);
		System.out.println("p1boardClick "+ boardClick);
		// This first if should not ever be true
		// We Check for placeships in BattleshipGame before calling turn() or placeShips();
		//
		if (placeShips) {
			
			messageToPlayer = new String("Waiting on placing Ships?");
			System.out.println(messageToPlayer);
		}
		
		else if (player1 != p1Turn) {
			// its not your turn!!
			// send back to client
			if(player1) {
				messageToPlayer = new String("Player 1, It's Not Your Turn!!!");
			}
			else {
				messageToPlayer = new String("Player 2, It's Not Your Turn!!!");
			}
			
			validMove=false;
			System.out.println(messageToPlayer);
		}
		else if (player1 == boardClick) {
			// its not your turn!!
			// send back to client
			if(player1) {
				messageToPlayer = new String("Player 1, Don't Shoot your own ships!!!");

			}
			else {
				messageToPlayer = new String("Player 2, Don't Shoot your own ships!!!");

			}
			validMove=false;
			System.out.println(messageToPlayer);
		}
		//player2s turn
		//player 2 is attacking player 1s board
		else if(!player1) {
			switch(p1boardData[boardIndex]) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				//ship
				//change to shiphit
				//decrement ship no
				messageToPlayer = new String("HIT!!!");
				p1shipData[p1boardData[boardIndex]]--;
				checkShips(player1,p1boardData[boardIndex],p1shipData[p1boardData[boardIndex]]);
				p1boardData[boardIndex]=5;
				p1Turn = true;
				validMove=true;
				break;
			case 5:
				//already hit here
				//does not change turn
				messageToPlayer = new String("You've Already Shot Here.");
				validMove=false;
				break;
			case 6:
				//water
				//change to miss
				messageToPlayer = new String("MISS!!!");
				p1boardData[boardIndex]=7;
				p1Turn = true;
				validMove=true;
				break;
			case 7:
				//watermiss
				//already missed here
				//does not change turn
				messageToPlayer = new String("You've Already Missed Here.");
				validMove=false;
				break;
			default:
				//data error
				messageToPlayer = new String("Board Data Error, something went wrong");
				System.out.println(messageToPlayer);
				validMove=false;
				break;
			}
			
		}
		//player1s turn
		//player 1 is attacking player2s board
		else if (player1){
			switch(p2boardData[boardIndex]) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				//ship
				//change to shiphit
				//decrement ship no
				messageToPlayer = new String("HIT!!!");
				System.out.println(messageToPlayer);
				System.out.println("BoardData: "+p2boardData[boardIndex]);
				System.out.println("ShipNo: "+p2boardData[boardIndex]);
				System.out.println("ShipSize: "+p2shipData[p2boardData[boardIndex]]);
				p2shipData[p2boardData[boardIndex]]--;
				System.out.println("NewShipSize: "+p2shipData[p2boardData[boardIndex]]);
				checkShips(player1,p2boardData[boardIndex],p2shipData[p2boardData[boardIndex]]);
				p2boardData[boardIndex]=5;
				p1Turn=false;
				validMove=true;
				break;
			case 5:
				//already hit here
				//does not change turn
				messageToPlayer = new String("You've Already Shot Here.");
				validMove=false;
				break;
			case 6:
				//water
				//change to miss
				messageToPlayer = new String("MISS!!!");
				p2boardData[boardIndex]=7;
				p1Turn=false;
				validMove=true;
				break;
			case 7:
				//watermiss
				//already missed here
				//does not change turn
				messageToPlayer = new String("You've Already Missed Here.");
				validMove=false;
				break;
			default:
				//data error
				messageToPlayer = new String("Board Data Error, something went wrong");
				System.out.println(messageToPlayer);
				validMove=false;
				break;
			}
		}
		//checkShips(player1);
		bsc = new battleshipComm(boardIndex);
		if(player1) {
			bsc.setDataValue(p2boardData[boardIndex]);
		}
		else {
			bsc.setDataValue(p1boardData[boardIndex]);
		}
		bsc.setMessage(messageToPlayer);
		bsc.setp1Turn(p1Turn);
		bsc.setp1(player1);
		bsc.setValidMove(validMove);
		bsc.setp1BoardClick(boardClick);
		System.out.println(messageToPlayer);
	}
	
	public battleshipComm getComms() {
		return bsc;
	}
	
	
	//End Game
	public void endGame(battleshipComm bsc) {
		this.bsc = bsc;
		if(p1Turn) {
			messageToPlayer = new String("Player1 Wins!!! ");
		}
		else {
			messageToPlayer = new String("Player2 Wins!!! ");
		}
		bsc.setMessage(messageToPlayer);
		bsc.setp1Turn(p1Turn);
		bsc.setGameOver(true);
		System.out.println(messageToPlayer);
	}
	public void forfeitGame(battleshipComm bsc) {
		this.bsc = bsc;
		if(!bsc.getp1Turn()) {
			messageToPlayer = new String("Player1 Wins!!! ");
		}
		else {
			messageToPlayer = new String("Player2 Wins!!! ");
		}
		bsc.setMessage(messageToPlayer);
		bsc.setp1Turn(p1Turn);
		bsc.setGameOver(true);
		System.out.println(messageToPlayer);
	}
	
}
