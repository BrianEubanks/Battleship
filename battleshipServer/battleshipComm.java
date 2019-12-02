package battleshipServer;

import java.io.Serializable;

public class battleshipComm  implements Serializable {

	//this stores the gameIndex used on the server side
	//to link with the appropriate game in the list
	public int gameIndex;
	
	private int boardIndex;
	private int boardDataValue;
	private String message;
	private boolean validmove;
	private boolean gameOver;
	private boolean placeShips;
	private boolean p1Turn;
	private boolean p1BoardClick;
	private boolean rightClick;
	private int shipLength;
	
	private boolean p1;
	private boolean newGame;
	
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
	public void setValidMove(boolean vm) {
		this.validmove = vm;
	}
	public void setShipLength(int s) {
		this.shipLength=s;
	}
	public void setrightClick(boolean rc) {
		this.rightClick=rc;
	}
	public void setp1(boolean p1) {
		this.p1 = p1;
	}
	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
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
	public boolean getValidMove() {
		return validmove;
	}
	public int getShipLength() {
		return shipLength;
	}
	public boolean getrightclick() {
		return rightClick;
	}
	public boolean getp1() {
		return p1;
	}
	public boolean getNewGame() {
		return newGame;
	}


}