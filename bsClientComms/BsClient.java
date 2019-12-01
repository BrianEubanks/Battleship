package bsClientComms;

import battleshipClient.*;
import java.awt.Color;

import javax.swing.*;

import ocsf.client.*;

public class BsClient extends AbstractClient {

	private JLabel status;
	private JTextArea serverMsg;
	private JTextField clientID;

	private boolean loggedIn;
	
	private InitialPanel startWindow;
	
	private BattleshipController bsc;
	private BattleshipView bsview;
	private BattleshipData bsdata;
	
	public BsClient() {
		super("localhost",8300);
		loggedIn = false;
	}
	public void handleMessageFromServer(Object msg) {
		System.out.println("Message Class: " + msg.getClass());
		System.out.println("Are we connected? "+ this.isConnected());
		if (msg instanceof battleshipServer.battleshipComm){
			System.out.println("Server BattleshipComm received");
			System.out.println(this.isConnected());
			bsc.receiveDataFromServer((battleshipServer.battleshipComm) msg);
			System.out.println(this.isConnected());
		}
		else if(msg instanceof LoginData) {
			System.out.println("LoginData received  "+msg+" Status: "+((LoginData) msg).getStatus());
			startWindow.setClient(this);
			if(((LoginData) msg).getStatus()) {
				//login successful
				JOptionPane.showMessageDialog(new JFrame(), "Login Successful");

				//start game
				  startWindow.dispose();
			      bsc = new BattleshipController();
			      bsc.setp1(((LoginData) msg).getp1());
				  bsview = new BattleshipView(bsc.getp1());
				  bsdata = new BattleshipData();
				  bsdata.setClient(this);
				  bsc.setBattleshipData(bsdata);
				  bsc.setBattleshipView(bsview);
				  
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Incorrect Login");

				System.out.println("incorrect Login");
			}
		}
		else if(msg instanceof CreateAccountData) {
			System.out.println("CreateAccountData received  "+msg+" Status: "+((CreateAccountData) msg).getStatus());
			startWindow.setClient(this);
			if(((CreateAccountData) msg).getStatus()) {
				//account create successfully
				JOptionPane.showMessageDialog(new JFrame(), "Account Created Successfully");
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Account Create Error");

			}
		}
		else{
			System.out.println("Server Message received  "+msg);	
			System.out.println(msg.getClass());
			System.out.println(this.isConnected());
			String message = (String)msg;
			if (message.startsWith("username")){
				message = message.substring(9);
				clientID.setText(message);
				if (!loggedIn) {
					System.out.println("New Panel"+this.isConnected());
					startWindow = new InitialPanel("Battleship Client", this);
					//loggedIn = true;
				}
				
			}
		}
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
		
		
		if (!loggedIn) {
			//validate user first
			//loggedIn = true;
			//this is adjusted after logged in above in handlemessagefrom server
		//start game
//	      bsc = new BattleshipController();
//		  bsview = new BattleshipView();
//		  bsdata = new BattleshipData();
//		  bsdata.setClient(this);
//		  bsc.setBattleshipData(bsdata);
//		  bsc.setBattleshipView(bsview);
		}
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
