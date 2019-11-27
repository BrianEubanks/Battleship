package battleshipClient;

import java.io.Serializable;

public class battleshipComm  implements Serializable {

	//this stores the gameIndex used on the server side
	//to link with the appropriate game in the list
	public int gameIndex;
	
	private int boardIndex;
	private int boardDataValue;
	private String message;
	private boolean gameOver;
	private boolean placeShips;
	private boolean p1Turn;
	private boolean p1BoardClick;
	
	
	//This constructor assigns the index of the button the player clicked
	
	
	public battleshipComm(int boardIndex) {
		this.boardIndex = boardIndex;
	}
	
	//setters
	//The server must assign the datavalue, message, and gameover vars
	public void setBoarIndex(int indexValue) {
		boardIndex = indexValue;
	}
	public void setDataValue(int dataValue) {
		boardDataValue = dataValue;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public void setplaceShips(boolean placeShips) {
		this.placeShips = placeShips;
	}
	public void setp1Turn(boolean p1Turn) {
		this.p1Turn = p1Turn;
	}
	public void setp1BoardClick(boolean p1Board) {
		this.p1BoardClick = p1Board;
	}
	
	// Getters
	public int getboardIndex() {
		return boardIndex;
	}
	
	public int getDataValue() {
		return boardDataValue;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public boolean getplaceShips() {
		return placeShips;
	}
	
	public boolean getp1Turn() {
		return p1Turn;
	}
	public boolean getp1BoardClick() {
		return p1BoardClick;
	}

}
	

	
