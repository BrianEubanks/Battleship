package bsClientComms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginPanel extends JFrame{
	
	private LoginControl lControl;
	private JLabel prompt;
	private JLabel uname;
	private JLabel pword;
	private JTextField usern;
	private JTextField passw;
	private JButton submit;
	private JButton cancel;
	private BsClient client;
	
	
	public LoginPanel(String title,BsClient client) {
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usern = new JTextField();
		passw = new JTextField();
		lControl=new LoginControl();
		lControl.setLP(this);
		lControl.setUN(usern);
		lControl.setPW(passw);
		this.client = client;
		lControl.setClient(client);
		
    
		//ADD YOUR CODE HERE TO CREATE THE STATUS JLABEL AND THE JBUTTONS
    
		// Status Label, north JPanel
		prompt = new JLabel("Enter your username and password to log in.");
		prompt.setForeground(Color.black);
		JPanel north = new JPanel(); //Flowlayout Default
		north.add(prompt);
    
		JPanel center = new JPanel(new GridLayout(3,2)); //Flowlayout Default
		uname = new JLabel("Username:");
		pword = new JLabel("Password:");
		center.add(uname);
		center.add(usern);
		center.add(pword);
		center.add(passw);
    
		//Buttons, south JPanel
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");

		JPanel south = new JPanel();
		center.add(submit);
		center.add(cancel);
    

		this.add(north,BorderLayout.NORTH);
		this.add(center,BorderLayout.SOUTH);
		//this.add(south,BorderLayout.SOUTH);
    
		//Add action Listener
		EventHandler click = new EventHandler();
    	submit.addActionListener(click);
    	cancel.addActionListener(click);

    
    	setSize(400,300);
    	setVisible(true);
	}
  
	private class EventHandler implements ActionListener{
	  
	  public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == submit) {
	        System.out.println("Login Button Pressed");
	        lControl.loginSubmit();
	        lControl.loginCancel();
	      }
	      else if(e.getSource() == cancel) {
	    	System.out.println("Create Button Pressed");
	    	lControl.loginCancel();
	      }
	  }
	}
  
}

