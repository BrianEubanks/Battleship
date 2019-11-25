package battleshipClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import battleshipServer.battleshipComm;


public class BattleshipController implements ActionListener{

	private BattleshipData bsdata;
	private BattleshipView bsview;
	
	private JButton[] opponent;
	private JButton[] player;
	private int[] opponentData;
	private int[] playerData;
	
	private ImageIcon water;
	private ImageIcon bship;
	private ImageIcon watermiss;
	private ImageIcon bshiphit;
	
	
	JLabel msgText;

	public void setBattleshipView(BattleshipView bsview) {
		this.bsview = bsview;
		msgText = bsview.getMessage();
		opponent = bsview.getOpponentButton();
		player = bsview.getPlayerButton();
		opponentData = bsview.getOpponentData();
		playerData = bsview.getPlayerData();
		water = bsview.getwaterIcon();
		bship = bsview.getbshipIcon();
		watermiss = bsview.getwatermissIcon();
		bshiphit = bsview.getbshiphitIcon();
		
	    for (int i = 0; i < opponent.length; i++)
	    {
	       opponent[i].addActionListener(this);
	       player[i].addActionListener(this);
	    }
		
	}
	
	public void setBattleshipData(BattleshipData bsdata) {
		this.bsdata = bsdata;
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		Object source = ae.getSource();
		
		for (int i = 0; i < opponent.length; i++)
	    {
	       if (source == opponent[i]) {
	    	   //System.out.println("Opponent: "+ i + " "+ opponentData[i]);
	    	   msgText.setText("Opponent: "+ i + " "+ opponentData[i]);
	    	   opponent[i].setIcon(water);
	    	   bsdata.setboardIndex(i);
	    	   bsdata.setboardDataValue(opponentData[i]);
	    	   bsdata.setMessage(msgText.getText());
	       }
	    }
		
		for (int i = 0; i < player.length; i++)
	    {
	       if (source == player[i]) {
	    	   //System.out.println("Player: "+ i + " "+ playerData[i]);
	    	   msgText.setText("Player: "+ i + " "+ playerData[i]);
	    	   player[i].setIcon(bshiphit);
	    	   bsdata.setboardIndex(i);
	    	   bsdata.setboardDataValue(playerData[i]);
	    	   bsdata.setMessage(msgText.getText());
	       }
	    }
		
		try {
			bsdata.sendToServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void receiveDataFromServer(battleshipComm bsc) {
		this.bsdata = bsdata;
	}
	
}
