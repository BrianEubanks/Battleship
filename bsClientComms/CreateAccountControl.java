package bsClientComms;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateAccountControl {
	private CreateAccountPanel cap;
	private JTextField usern;
	private JTextField passw;
	private JTextField passwconf;
	private CreateAccountData capData;
	
	public CreateAccountControl(JTextField usern,JTextField passw,JTextField passwconf) {
		capData = new CreateAccountData();
		this.usern=usern;
		this.passw=passw;
		this.passwconf=passwconf;
		
	}
	
	public void loginSubmit() {
		
		capData.setUsername(usern.getText());
		capData.setPassword(passw.getText());
		capData.setPasswordConf(passwconf.getText());
		
		
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
}
