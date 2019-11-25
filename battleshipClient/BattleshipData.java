package battleshipClient;

import java.io.IOException;

import battleshipServer.battleshipComm;
import bsClientComms.*;

public class BattleshipData {

	//send data to client sub system
	//clent send data to server
	
	//receive data from client sub system
	//
	
	private int boardIndex;
	private int boardDataValue;
	private String message;
	private boolean gameOver;
	
	
	private battleshipComm bsc;
	
	private BsClient bsclient;
	
	public void setboardIndex(int bi) {
		boardIndex = bi;
	}
	
	public void setboardDataValue(int bdv) {
		boardDataValue = bdv;
	}
	
	public void setgameOver(boolean go) {
		gameOver = go;
	}
	
	public void setMessage(String msgText) {
		message = msgText;
	}
	
	public void setClient(BsClient bsclient) {
		this.bsclient=bsclient;
	}
	
	public void sendToServer() throws IOException {
		System.out.println(message);
		System.out.println("Send To Server "+boardIndex+" "+boardDataValue);
		bsc = new battleshipComm(boardIndex);
		bsclient.sendToServer(bsc);
	}
	
}
