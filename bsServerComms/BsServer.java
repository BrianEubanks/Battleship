package bsServerComms;

import ocsf.server.AbstractServer;
import bsClientComms.LoginData;
import bsClientComms.CreateAccountData;
import bsClientComms.User;
import bsDatabase.BSDatabaseServer;
import battleshipServer.*;

import java.awt.Color;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

import javax.swing.*;

import ocsf.server.*;

public class BsServer extends AbstractServer {
	
	private JTextArea log; //Corresponds to JTextArea of ServerGUI
	private JLabel status; //Corresponds to the JLabel of ServerGUI
	private BSDatabaseServer userdb;
	
	private BattleshipGame bg;
	private battleshipComm returnData;

	
	public BsServer() throws IOException {
		super(12345);
		setTimeout(500);
		//userdb = new BSDatabaseServer();
		bg = new BattleshipGame();
		
		
	}
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		System.out.println("Message from Client Received! "+arg1.getId());
		System.out.println("Data type: "+arg0.getClass());
		if(arg0 instanceof LoginData) 
		{
			System.out.println("Before query");
			//validate user
			try {
				userdb = new BSDatabaseServer("select * from user where username='"+((LoginData) arg0).getUsername()+"';","Q");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Before validate player");
			//Validate User and Password
			if(userdb.validateUser(((LoginData) arg0).getUsername(),((LoginData) arg0).getPassword())) {
				System.out.println("Validate Player: "+((LoginData) arg0).getUsername());
				System.out.println(userdb.validateUser(((LoginData) arg0).getUsername(),((LoginData) arg0).getPassword()));
			
				//Before add player, they  must login 
				((LoginData) arg0).setStatus(true);
				System.out.println("Before add player");
				((LoginData) arg0).setp1(bg.addPlayer(arg1));
				System.out.println("After add player: P1: "+((LoginData) arg0).getp1());
			}
			else {
				((LoginData) arg0).setStatus(false);
			}
			
			try {
				log.append("Client " + arg1.getId() + " " + arg0);
				arg1.sendToClient(arg0);
				System.out.println("How about now?");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(arg0 instanceof CreateAccountData) {
			
			System.out.println("Before DML");
			String dml = new String("insert into user values('"+arg1.getId()+"','"+((CreateAccountData) arg0).getUsername()+"','"+((CreateAccountData) arg0).getPassword()+"');");
			//validate user
			try {
				userdb = new BSDatabaseServer(dml,"D");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				log.append("Client " + arg1.getId() + ": Create Account Error");
				try {
					((CreateAccountData) arg0).setStatus(false);
					arg1.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			log.append("Client " + arg1.getId() + ": Account added");
			try {
				((CreateAccountData) arg0).setStatus(true);
				arg1.sendToClient(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (arg0 instanceof battleshipComm) {
			System.out.println("battleshipComm");
			//handle game
			//returnData = bg.handleCommunication((battleshipComm)arg0, arg1.getId());
			
			bg.handleCommunication((battleshipComm)arg0, arg1.getId());
			//arg1.sendToClient(returnData);
			//System.out.println(returnData.getClass());
			//System.out.println("How about now?");
			//System.out.println(bdata.getClass());
		}
		else{
			System.out.println("Other data: "+arg0.getClass());
			try {
				log.append("Client " + arg1.getId() + " " + arg0);
				arg1.sendToClient(arg0);
				System.out.println("How about now?");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void clientConnected(ConnectionToClient client) {
		String connected = "username:Client-"+client.getId();
		
		log.append("Client "+connected+" Connected\n");
		try {
			client.sendToClient(connected);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void listeningException(Throwable exception) {
		System.out.println("Listening Exception Occured");
		System.out.println(exception.getMessage());
		exception.printStackTrace();
	}
	public void serverStarted() {
		//System.out.println("Server Started");
		log.append("Server Started\n");
		status.setText("Listening");
		status.setForeground(Color.GREEN);
	}
	public void serverStopped() {
		//System.out.println("Server Stopped");
		log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients");
		status.setText("Stopped");
		status.setForeground(Color.RED);
	}
	public void serverClosed() {
		//System.out.println("Server Closed");
		log.append("Server and all current clients are closed - Press Listen to Restart");
		status.setText("Close");
		status.setForeground(Color.RED);
	}
	public void setLog(JTextArea log) {
		this.log = log;
		
	}
	public void setStatus(JLabel status) {
		this.status = status;
	}
	public boolean validateCreateAccount(CreateAccountData cad) {
		int passl = cad.getPassword().length();
		if(passl>4)
			return true;
		else 
			return false;	
	}
	public boolean validateAccount(LoginData ld) {
		String usern = ld.getUsername();
		//if(userdb.verifyUsername(usern))
		if(true)
			return true;
		else
			return false;
	}

}
