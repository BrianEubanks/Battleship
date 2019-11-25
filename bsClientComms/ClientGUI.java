package bsClientComms;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class ClientGUI extends JFrame
{
	  private JLabel status;
	  private JButton connect;
	  private JButton submit;
	  private JButton stop;
	  private String[] labels = {"Client ID", "Server URL", "Server Port"};
	  private JTextField[] textFields = new JTextField[labels.length];
	  private JTextArea clientArea;
	  private JTextArea serverArea;
	  private BsClient client;

	  

	  
	  public ClientGUI(String title)
	  {
	    
	    
	    this.setTitle(title);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    
	    //ADD YOUR CODE HERE TO CREATE THE STATUS JLABEL AND THE JBUTTONS
	    
	    // Status Label, north JPanel
	    status = new JLabel("Not Connected");
	    status.setForeground(Color.red);
	    JPanel north = new JPanel(); //Flowlayout Default
	    north.add(status);
	    
	    //Buttons, south JPanel
	    connect = new JButton("Connect");
	    submit = new JButton("Submit");
	    stop = new JButton("Stop");
	    JPanel south = new JPanel(); //Flowlayout Default
	    south.add(connect);
	    south.add(submit);
	    south.add(stop);
	    
//	    //Jpanel Center
	    // Init JText Areas
	    for(int i=0;i<labels.length;i++) {
	    	textFields[i] = new JTextField(20);
	    }
	    
	    JPanel center = new JPanel(new GridLayout(7,1,5,10));
	    JPanel clientid = new JPanel();
	    JPanel servid	= new JPanel();
	    JPanel servport = new JPanel();
	    JPanel CD = new JPanel();
	    JPanel CDText = new JPanel();
	    JPanel SD = new JPanel();
	    JPanel SDText = new JPanel();
	    JLabel cid = new JLabel(labels[0]);
	    JLabel sid = new JLabel(labels[1]);
	    JLabel sp = new JLabel(labels[2]);
	    JLabel cArea = new JLabel("Enter Client Data Below");
	    JLabel sArea = new JLabel("Enter Server Data Below");
	    clientArea = new JTextArea(8,20);
	    serverArea = new JTextArea(8,20);
	    serverArea.setEditable(false);
	    JScrollPane cScroll = new JScrollPane(clientArea);
	    JScrollPane sScroll = new JScrollPane(serverArea);
//	    center.add(cid,BorderLayout.NORTH);
//	    center.add(textFields[0],BorderLayout.NORTH);
//	    center.add(sid,BorderLayout.CENTER);
//	    center.add(textFields[1],BorderLayout.CENTER);
//	    center.add(sp,BorderLayout.SOUTH);
//	    center.add(textFields[2],BorderLayout.SOUTH);
	    
	    clientid.add(cid);
	    clientid.add(textFields[0]);
	    textFields[0].setEditable(false);
	    servid.add(sid);
	    servid.add(textFields[1]);
	    servport.add(sp);
	    servport.add(textFields[2]);
	    
	    CD.add(cScroll);
	    CDText.add(cArea);
	    SD.add(sScroll);
	    SDText.add(sArea);
	    center.add(clientid);
	    center.add(servid);
	    center.add(servport);
	    center.add(CDText);
	    center.add(CD);
	    center.add(SDText);
	    center.add(SD);
	    
	    //Add Panels
	    
	    this.add(north,BorderLayout.NORTH);
	    this.add(south,BorderLayout.SOUTH);
	    this.add(center,BorderLayout.CENTER);
	    
	    //set default host and port
	    String defaultHost = "localhost";
		textFields[1].setText(defaultHost);
		String defaultPort = "8300";
		textFields[2].setText(defaultPort);
	    
	    //Add action Listener
	    EventHandler click = new EventHandler();
	    connect.addActionListener(click);
	    submit.addActionListener(click);
	    stop.addActionListener(click);
	    
	    client=new BsClient();
	    //sethost
	    client.setHost(textFields[1].getText());
	    //setport
	    //client.setPort(Integer.parseInt(textFields[2].getText()));
	    //setstatus
	    client.setStatus(status);
	    //setservermsg
	    client.setServerMsg(serverArea);
	    //setclientid
	    client.setClientID(textFields[0]);
	    
	    setSize(400,600);
	    setVisible(true);
	    //new InitialPanel("Battleship Client",client);
	    //new LoginPanel("Battleship Client");
	    
	    
	  }
	  
	  private class EventHandler implements ActionListener{
		  
		  public void actionPerformed(ActionEvent e) {
		      if (e.getSource() == connect) {
		        System.out.println("Connect Button Pressed");
		        try {
					client.openConnection();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		      else if(e.getSource() == submit) {
				System.out.println("Client Data: "+clientArea.getText());
		      	try {
					client.sendToServer(clientArea.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		      else if(e.getSource() == stop) {
		    	System.out.println("Stop Button Pressed");
		    	try {
					client.closeConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		  }
	  }
	  
	  public static void main(String[] args)
	  {
		
	    new ClientGUI("Battleship Client"); //args[0] represents the title of the GUI
	  }
}