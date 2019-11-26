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
	private battleshipComm bscomm;
	
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
		
		
		//This code changes the icon of whatever square was clicked
//		for (int i = 0; i < opponent.length; i++)
//	    {
//	       if (source == opponent[i]) {
//	    	   //System.out.println("Opponent: "+ i + " "+ opponentData[i]);
//	    	   msgText.setText("Opponent: "+ i + " "+ opponentData[i]);
//	    	   opponent[i].setIcon(water);
//	    	   bsdata.setboardIndex(i);
//	    	   bsdata.setboardDataValue(opponentData[i]);
//	    	   bsdata.setMessage(msgText.getText());
//	       }
//	    }
//		
//		for (int i = 0; i < player.length; i++)
//	    {
//	       if (source == player[i]) {
//	    	   //System.out.println("Player: "+ i + " "+ playerData[i]);
//	    	   msgText.setText("Player: "+ i + " "+ playerData[i]);
//	    	   player[i].setIcon(bshiphit);
//	    	   bsdata.setboardIndex(i);
//	    	   bsdata.setboardDataValue(playerData[i]);
//	    	   bsdata.setMessage(msgText.getText());
//	       }
//	    }
		
		//This code sets the data value to send to server
		for (int i = 0; i < opponent.length; i++)
	    {
	       if (source == opponent[i]) {
	    	   //System.out.println("Opponent: "+ i + " "+ opponentData[i]);
	    	   msgText.setText("Opponent: "+ i + " "+ opponentData[i]);
//	    	   opponent[i].setIcon(water);
	    	   bsdata.setboardIndex(i);
	    	  // bsdata.setboardDataValue(opponentData[i]);
	    	   bsdata.setMessage(msgText.getText());
	       }
	    }
		
		for (int i = 0; i < player.length; i++)
	    {
	       if (source == player[i]) {
	    	   //System.out.println("Player: "+ i + " "+ playerData[i]);
	    	   msgText.setText("Player: "+ i + " "+ playerData[i]);
	    	  // player[i].setIcon(bshiphit);
	    	   bsdata.setboardIndex(i);
	    	   //bsdata.setboardDataValue(playerData[i]);
	    	   bsdata.setMessage(msgText.getText());
	       }
	    }
		bsdata.sendToServer();
	
	}
	
	public void receiveDataFromServer(battleshipComm bscomm) {
		this.bscomm = bscomm;
		System.out.println("datafromserver");
		int i = bscomm.getboardIndex();
		int sprite = bscomm.getDataValue();
		ImageIcon newSprite;
		switch (sprite){
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			//ship
			//change to shiphit
			newSprite=bshiphit;
			opponent[i].setIcon(newSprite);
			//decrement ship no
			//messageToPlayer = new String("There is already a ship here!");
			//p1shipData[p1boardData[boardIndex]]--;
			//p1boardData[boardIndex]=5;
			//p1Turn = false;
			break;
		case 5:
			//already hit here
			//does not change turn
			//messageToPlayer = new String("Board Data Error: 5");
			break;
		case 6:
			//water
			//Place a ship here********************************************
			//messageToPlayer = new String("AddShipToP1Board");
			//p1boardData[boardIndex]=7;
			//p1Turn = addShipToP1Board(boardIndex);
			newSprite=watermiss;
			opponent[i].setIcon(newSprite);
			break;
		case 7:
			//watermiss
			//already missed here
			//does not change turn
			//messageToPlayer = new String("Board Data Error: 7");
			break;
		default:
			//data error
			//messageToPlayer = new String("Board Data Error, something went wrong");
			break;
		}
		
		
		
		//update data and screen with comm object
		//set player or opponent?
		 //player[i].setIcon(newSprite);
		 //opponent[i].setIcon(newSprite);
		//bsdata.setboardDataValue(playerData[i]); Maybe not these two lines?
		// bsdata.setboardDataValue(opponentData[i]);
	}
	
}
