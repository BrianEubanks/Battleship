package bsClientComms;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
	private String username;
	private String password;
	private String passwordconf;
	private boolean status;
	
	public CreateAccountData() {
//		this.username = username;
//		this.password = password;
//		this.passwordconf = passwordconf;
		status=false;
	}

	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setPasswordConf(String passwordconf) {
		this.passwordconf=passwordconf;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPasswordConf() {
		return passwordconf;
	}
	public void setStatus(boolean s) {
		status=s;
	}
	
	public boolean getStatus() {
		return status;
	}
}
