package battleshipClient;

import javax.swing.*;

import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BattleshipView extends JFrame{
	
	private JButton[] opponent = new JButton[100];
	private JButton[] player = new JButton [100];
	private int[] opponentData = new int[100];
	private int[] playerData = new int [100];
	
	private ImageIcon icon1 = new ImageIcon("water.ico");
	private ImageIcon icon2 = new ImageIcon("bship.ico");
	private ImageIcon icon3 = new ImageIcon("watermiss.ico");
	private ImageIcon icon4 = new ImageIcon("bshiphit.ico");
	private ImageIcon water;
	private ImageIcon bship;
	private ImageIcon watermiss;
	private ImageIcon bshiphit;
	
	private JLabel msgText;
	
	protected JButton[] getOpponentButton() {
		return opponent;
	}
	protected JButton[] getPlayerButton() {
		return player;
	}
	protected int[] getOpponentData() {
		return opponentData;
	}
	protected int[] getPlayerData() {
		return playerData;
	}
	protected JLabel getMessage() {
		return msgText;
	}
	protected ImageIcon getwaterIcon() {
		return water;
	}
	protected ImageIcon getbshipIcon() {
		return bship;
	}
	protected ImageIcon getbshiphitIcon() {
		return bshiphit;
	}
	protected ImageIcon getwatermissIcon() {
		return watermiss;
	}
	
	
	public BattleshipView() {
		
		int i = 0;
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(500,700);
	    this.setResizable(false);
	    this.setTitle("Battleship");
	    
	    
	    Image img = icon1.getImage();
	    Image newimg = img.getScaledInstance(this.getSize().height/12, this.getSize().width/12,  java.awt.Image.SCALE_SMOOTH);
	    water = new ImageIcon(newimg);

	    Image img2 = icon2.getImage();
	    Image newimg2 = img2.getScaledInstance(this.getSize().height/12, this.getSize().width/12,  java.awt.Image.SCALE_SMOOTH);
	    bship = new ImageIcon(newimg2);

	    Image img3 = icon3.getImage();
	    Image newimg3 = img3.getScaledInstance(this.getSize().height/12, this.getSize().width/12,  java.awt.Image.SCALE_SMOOTH);
	    watermiss = new ImageIcon(newimg3);

	    Image img4 = icon4.getImage();
	    Image newimg4 = img4.getScaledInstance(this.getSize().height/12, this.getSize().width/12,  java.awt.Image.SCALE_SMOOTH);
	    bshiphit = new ImageIcon(newimg4);
	    
		// 0 - ship length 2
		// 1 - ship length 3
		// 2 - ship length 3
		// 3 - ship length 4
		// 4 - ship length 5
		// 5 - ship hit
		// 6 - water
		// 7 - water miss
	    
	 // Message Panel
	 	JPanel msgPanel = new JPanel();
	 	getContentPane().add(msgPanel, BorderLayout.NORTH);
	 	//Add MSG String to Menu Panel
	 	msgText = new JLabel("Battleship Messages from server");
	 	msgPanel.add(msgText);

	    
		// Menu Panel
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		//Add Buttons to Menu Panel
		JButton btnNewButton = new JButton("Forfeit");
		panel.add(btnNewButton);
		JButton btnQuit = new JButton("Quit");
		panel.add(btnQuit);
		
		
		
		// BoardPanels
		JPanel boardPanel = new JPanel();
		getContentPane().add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(20, 10, 0, 0));
		
		// Add Buttons to board Panel
	    //Create Buttons and add to the grid
	    for (i = 0; i < opponent.length; i++)
	    {
	       opponentData[i]=(i%2)+2;
	    	if(opponentData[i]==7) {
	    		opponent[i]=new JButton(watermiss);
		    	boardPanel.add(opponent[i]);
	    	}
	    	else {
	    		opponent[i]=new JButton(bshiphit);
	    		boardPanel.add(opponent[i]);
	    	}
	    }
	    for (i = 0; i < player.length; i++)
	    {
	    	playerData[i]=i%2;
	    	if(playerData[i]==6) {
	    		player[i]=new JButton(water);
		    	boardPanel.add(player[i]);
	    	}
	    	else {
	    		player[i]=new JButton(bship);
	    		boardPanel.add(player[i]);
	    	}
	    }
	    
	    
	    this.setVisible(true);

		
		
	}
//	  public static void main(String[] args)
//	  {
//	      BattleshipController bsc = new BattleshipController();
//		  BattleshipView bsview = new BattleshipView();
//		  BattleshipData bsdata = new BattleshipData();
//		  bsc.setBattleshipData(bsdata);
//		  bsc.setBattleshipView(bsview);
//	    
//	  }
}
