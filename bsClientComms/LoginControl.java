package bsClientComms;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginControl {
	
	private LoginPanel lp;
	private JTextField usern;
	private JTextField passw;
	private LoginData larrydavid;
	private BsClient client;

	
	//Null Pointer
	//Add references to JTextFields
	
	public LoginControl() {}
	
	public void loginSubmit() {
		if((usern.getText().equals(""))||(passw.getText().equals(""))) {
			System.out.println("Enter a username and password");
			JOptionPane.showMessageDialog(new JFrame(), "Enter a Username and Password");

		}
		else {
			larrydavid = new LoginData(usern.getText(),passw.getText());
			System.out.println(client.isConnected());
		
			try {
				client.sendToServer(larrydavid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void loginCancel() {
		lp.dispose();
	}
	
	public void setLP(LoginPanel lp) {
		this.lp = lp;
	}
	public void setUN(JTextField usern2) {
		this.usern = usern2;
	}

	public void setPW(JTextField passw2) {
		this.passw = passw2;
	}
	public void setClient(BsClient client) {
		this.client = client;
	}
}
