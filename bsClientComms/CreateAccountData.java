package bsClientComms;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
	private String username;
	private String password;
	private String passwordconf;
	
	public CreateAccountData() {
//		this.username = username;
//		this.password = password;
//		this.passwordconf = passwordconf;
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
}
