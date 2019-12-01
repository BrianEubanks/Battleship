package bsClientComms;

import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateAccountControl {
	private CreateAccountPanel cap;
	private JTextField usern;
	private JTextField passw;
	private JTextField passwconf;
	private CreateAccountData capData;
	private BsClient client;
	
	public CreateAccountControl(JTextField usern,JTextField passw,JTextField passwconf) {
		capData = new CreateAccountData();
		this.usern=usern;
		this.passw=passw;
		this.passwconf=passwconf;
		
	}
	
	public void loginSubmit() {
		
		if((usern.getText().equals(""))||(passw.getText().equals(""))||(passwconf.getText().equals(""))) {
			System.out.println("Enter a username and password");
		}
		else if(!(passw.getText().equals(passwconf.getText()))) {
			System.out.println("Passwords Must Match");
		}
		else {
			capData.setUsername(usern.getText());
			capData.setPassword(passw.getText());
			capData.setPasswordConf(passwconf.getText());
		
			try {
				client.sendToServer(capData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void loginCancel() {
		cap.dispose();
	}
	
	public void setCAP(CreateAccountPanel cap) {
		this.cap = cap;
	}
	public void setUN(JTextField usern2) {
		this.usern = usern2;
	}

	public void setPW(JTextField passw2) {
		this.passw = passw2;
		
	}
	public void setPWC(JTextField passwconf) {
		this.passwconf = passwconf;
		
	}
	public void setClient(BsClient client) {
		this.client = client;
	}
}
