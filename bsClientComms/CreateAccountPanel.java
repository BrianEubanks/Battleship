package bsClientComms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bsServerComms.Database;


public class CreateAccountPanel extends JFrame {
	
	private Database db;
	private CreateAccountControl caControl;
	private JLabel prompt;
	private JLabel uname;
	private JLabel pword;
	private JLabel pwordconf;
	private JTextField usern;
	private JTextField passw;
	private JTextField passwconf;
	private JButton submit;
	private JButton cancel;
	private BsClient client;
	
	
	public CreateAccountPanel(String title, BsClient client) {
		
		this.client = client;
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usern = new JTextField();
		passw = new JTextField();
		passwconf = new JTextField();
		caControl= new CreateAccountControl(usern,passw,passwconf);
		caControl.setCAP(this);
		caControl.setUN(usern);
		caControl.setPW(passw);
		
		
    
		//ADD YOUR CODE HERE TO CREATE THE STATUS JLABEL AND THE JBUTTONS
    
		// Status Label, north JPanel
		prompt = new JLabel("Create New Account: passwords must be more than 4 characters");
		prompt.setForeground(Color.black);
		JPanel north = new JPanel(); //Flowlayout Default
		north.add(prompt);
    
		JPanel center = new JPanel(new GridLayout(4,2)); //Flowlayout Default
		uname = new JLabel("Username:");
		pword = new JLabel("Password:");
		pwordconf= new JLabel("Password Confirm:");
		center.add(uname);
		center.add(usern);
		center.add(pword);
		center.add(passw);
		center.add(pwordconf);
		center.add(passwconf);
    
		//Buttons, south JPanel
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");

		JPanel south = new JPanel(); //Flowlayout Default
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
	    	  if (true);
	    	  {
	    		  System.out.println("Submit Button Pressed");
	    		  caControl.loginSubmit();
	    	  }
	      }
	      else if(e.getSource() == cancel) {
	    	System.out.println("Cancel Button Pressed");
	    	caControl.loginCancel();
	      }
	  }
	}
  
}

