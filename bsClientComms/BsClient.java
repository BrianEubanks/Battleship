package bsClientComms;

import battleshipClient.*;
import java.awt.Color;

import javax.swing.*;

import ocsf.client.*;

public class BsClient extends AbstractClient {

	private JLabel status;
	private JTextArea serverMsg;
	private JTextField clientID;
	
	private BattleshipController bsc;
	private BattleshipView bsview;
	private BattleshipData bsdata;
	
	public BsClient() {
		super("localhost",8300);
	}
	public void handleMessageFromServer(Object msg) {
		if (msg instanceof battleshipComm){
			System.out.println("Server BattleshipComm received");
			bsc.receiveDataFromServer((battleshipServer.battleshipComm) msg);
			
		}
		else{
			System.out.println("Server Message received  "+msg);	
		
			String message = (String)msg;
			if (message.startsWith("username")){
				message = message.substring(9);
				clientID.setText(message);
				new InitialPanel("Battleship Client", this);
			}
		}
		//else {
		//	serverMsg.append("Server: " + (String) msg+"\n");
		//}
		//else battlehsipcomms
	}
	public void connectionException(Throwable exception) {
		System.out.println("Connection Exception Occured");
		System.out.println(exception.getMessage());
		exception.printStackTrace();
	}
	
	public void connectionEstablished() {
		//System.out.println("Client Connected");
		status.setText("Connected");
		status.setForeground(Color.GREEN);
		//validate user first
		//start game
	      bsc = new BattleshipController();
		  bsview = new BattleshipView();
		  bsdata = new BattleshipData();
		  bsdata.setClient(this);
		  bsc.setBattleshipData(bsdata);
		  bsc.setBattleshipView(bsview);
		
	}
	
	public void setStatus(JLabel status) {
		this.status = status;
	}
	public void setServerMsg(JTextArea serverMsg) {
		this.serverMsg = serverMsg;
	}
	public void setClientID(JTextField clientID) {
		this.clientID = clientID;
	}
	
	public int getID()
	{
		String s = clientID.getText();
		int ID = Integer.parseInt(s.replaceAll("[\\D]", ""));
		return ID;
	}
}
