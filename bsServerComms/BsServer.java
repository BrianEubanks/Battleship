package bsServerComms;

import ocsf.server.AbstractServer;
import bsClientComms.LoginData;
import bsClientComms.CreateAccountData;
import bsClientComms.User;
import battleshipServer.*;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

import ocsf.server.*;

public class BsServer extends AbstractServer {
	
	private JTextArea log; //Corresponds to JTextArea of ServerGUI
	private JLabel status; //Corresponds to the JLabel of ServerGUI
	private Database userdb;

	
	public BsServer() throws IOException {
		super(12345);
		setTimeout(500);
		userdb = new Database();
		
	}
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		System.out.println("Message from Client Received!");
		if(arg0 instanceof LoginData) {
			
		}
		if(arg0 instanceof CreateAccountData) {
			if(validateCreateAccount((CreateAccountData)arg0)) {
				try {
					userdb.addUser(new User((int)arg1.getId(),((CreateAccountData) arg0).getUsername(), ((CreateAccountData)arg0).getPassword()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				log.append("Client " + arg1.getId() + ": Create Account Error");
				try {
					arg1.sendToClient("Create Account Error");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if (arg0 instanceof battleshipComm) {
			System.out.println("battleshipComm");
			//handle game
			
		}
		try {
			log.append("Client " + arg1.getId() + " " + arg0);
			arg1.sendToClient(arg0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if(userdb.verifyUsername(usern))
			return true;
		else
			return false;
	}

}
