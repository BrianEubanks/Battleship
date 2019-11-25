package bsServerComms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;



public class ServerGUI extends JFrame
{
   //Data Fields go here
	private BsServer server;
	
	private JLabel status; //Initialized to â€œNot Connectedâ€�
	private JButton listen;
	private JButton close;
	private JButton stop;
	private JButton quit;
	private String[] labels = {"Port #", "Timeout"};
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea log;
	
	private int port;
	private int timeout;
	boolean portset;
	boolean timeset;
	
	
	
   //Methods go here
	public ServerGUI() throws IOException {
		
	    this.setTitle("Battleship Server");
	    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    
	    
	    // Status Label, north JPanel
	    status = new JLabel("Not Connected");
	    status.setForeground(Color.red);
	    JPanel north = new JPanel(); //Flowlayout Default
	    north.add(status);	
	    
	    //Buttons, south JPanel
	    listen = new JButton("Listen");
	    close = new JButton("Close");
	    quit = new JButton("Quit");
	    stop = new JButton("Stop");
	    JPanel south = new JPanel(); //Flowlayout Default
	    south.add(listen);
	    south.add(close);
	    south.add(stop);
	    south.add(quit);
	    
	    // Init JText Areas
	    for(int i=0;i<labels.length;i++) {
	    	textFields[i] = new JTextField(20);
	    }
	    
	    log = new JTextArea(8,20);
	    JScrollPane logScroll = new JScrollPane(log);
	    
	    //Center Jpanel
	    JPanel center = new JPanel(new GridLayout(4,1,5,10));
	    JPanel port = new JPanel();
	    JPanel to	= new JPanel();
	    JPanel logt = new JPanel();
	    JPanel logp = new JPanel();
	    port.add(new JLabel(labels[0]));
	    port.add(textFields[0]);
		center.add(port);
		to.add(new JLabel(labels[1]));
	    to.add(textFields[1]);
		center.add(to);
		logt.add(new JLabel("Server Log Below"));
		center.add(logt);
		logp.add(logScroll);
		center.add(logp);
	    
	    this.add(north,BorderLayout.NORTH);
	    this.add(south,BorderLayout.SOUTH);
	    this.add(center,BorderLayout.CENTER);
	    
	    
	    //Add action Listener
	    EventHandler click = new EventHandler();
	    listen.addActionListener(click);
	    close.addActionListener(click);
	    stop.addActionListener(click);
	    quit.addActionListener(click);
	    
	    
	    //Instantiate Battleship Server
		server = new BsServer();
		server.setLog(log);
		server.setStatus(status);
		
	    setSize(400,600);
	    setVisible(true);
	    
	    //set default values for port and timeout
	    String defaultPort = "8300";
		textFields[0].setText(defaultPort);
		String defaultTimeout = "500";
		textFields[1].setText(defaultTimeout);
		
	}
	
	private class EventHandler implements ActionListener{
		  
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == listen) {
				//System.out.println("Listen Button Pressed");
				try
				{
;					port = Integer.parseInt(textFields[0].getText());
					server.setPort(port);
					portset = true;
					

				}
				catch (NumberFormatException ex)
				{
					//System.out.println("Enter Port");
					log.append("LISTEN: Port Number not valid\n");
					portset = false;
				}
				try
				{
					timeout = Integer.parseInt(textFields[1].getText());
					server.setTimeout(timeout);
					timeset = true;
				}
				catch (NumberFormatException ex)
				{
					//System.out.println("Enter Timeout");
					log.append("LISTEN: Timeout Number not valid\n");
					timeset = false;
				}
				if(portset && timeset) {
					try {
						server.listen();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		    else if(ae.getSource() == close) 
				//System.out.println("Close Button Pressed");
				if (!server.isListening()) {
					log.append("CLOSE: Server is not currently started\n");
				}
				else {
					server.stopListening();
				}
			
		    else if(ae.getSource() == stop)
		    	//System.out.println("Stop Button Pressed");
				if (!server.isListening()) {
					log.append("STOP: Server is not currently started\n");
				}
				else {
					server.stopListening();
				}
			
		    else if(ae.getSource() == quit) {
		    	System.out.println("Quit Button Pressed");
				System.exit(0);
		    }
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		new ServerGUI(); //args[0] represents the title of the GUI
	}
}
