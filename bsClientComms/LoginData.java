package bsClientComms;

import java.io.Serializable;

public class LoginData implements Serializable {

	private String username;
	private String password;
	private boolean status;
	private boolean p1;
	
	public LoginData(String username, String password) {
		this.username = username;
		this.password = password;
		status=false;
		p1=false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setStatus(boolean s) {
		status=s;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setp1(boolean p) {
		p1=p;
	}
	public boolean getp1() {
		return p1;
	}
	
}
