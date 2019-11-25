package bsClientComms;

import java.awt.Color;

import javax.swing.*;

public class InitialControl {

	//private JButton login;
	//private JButton create;
	private InitialPanel ip;
	private BsClient client;
	
	public InitialControl() {}
	
	public void accountLogin() {
		new LoginPanel("Battleship Client",client);
	}
	
	public void accountCreate() {
		new CreateAccountPanel("Battleship Client",client);
	}
	public void setInitialPanel(InitialPanel ip) {
		this.ip = ip;
	}
	public void setClient(BsClient client) {
		this.client=client;
	}
	
	
}
