package bsClientComms;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class InitialPanel extends JFrame{
	
	private JLabel account;
	private JButton login;
	private JButton create;
	private InitialControl iControl;
	
	public InitialPanel(String title, BsClient client){
	    
	    
		
	    this.setTitle(title);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    iControl=new InitialControl();
	    iControl.setClient(client);
	    
	    //ADD YOUR CODE HERE TO CREATE THE STATUS JLABEL AND THE JBUTTONS
	    
	    // Status Label, north JPanel
	    account = new JLabel("Account Information");
	    account.setForeground(Color.black);
	    JPanel north = new JPanel(); //Flowlayout Default
	    north.add(account);
	    
	    //Buttons, south JPanel
	    login = new JButton("Login");
	    create = new JButton("Create");

	    JPanel south = new JPanel(); //Flowlayout Default
	    south.add(login);
	    south.add(create);
	    

	    this.add(north,BorderLayout.NORTH);
	    this.add(south,BorderLayout.SOUTH);
	    
	    //Add action Listener
	    EventHandler click = new EventHandler();
	    login.addActionListener(click);
	    create.addActionListener(click);

	    
	    setSize(400,300);
	    setVisible(true);
	    
	  
	  }
	  private class EventHandler implements ActionListener{
		  
		  public void actionPerformed(ActionEvent e) {
		      if (e.getSource() == login) {
		        System.out.println("Login Button Pressed");
		        iControl.accountLogin();
		      }
		      else if(e.getSource() == create) {
		    	System.out.println("Create Button Pressed");
		    	iControl.accountCreate();
		      }
		  }
	  }
	
}
