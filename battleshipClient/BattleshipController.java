package battleshipClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import battleshipServer.battleshipComm;


public class BattleshipController implements ActionListener, MouseListener{

	private BattleshipData bsdata;
	private BattleshipView bsview;
	private battleshipComm bscomm;
	
	private JButton[] opponent;
	private JButton[] player;
	private JButton btnForfeit;
	private JButton btnQuit;
	private int[] opponentData;
	private int[] playerData;
	
	private ImageIcon water;
	private ImageIcon bship;
	private ImageIcon watermiss;
	private ImageIcon bshiphit;
	
	private boolean p1;
	
	JLabel msgText;
	
	// 0 - ship length 2
	// 1 - ship length 3
	// 2 - ship length 3
	// 3 - ship length 4
	// 4 - ship length 5
	private int[] shipData = new int[5];
	

	public void setBattleshipView(BattleshipView bsview) {
		this.bsview = bsview;
		msgText = bsview.getMessage();
		opponent = bsview.getOpponentButton();
		player = bsview.getPlayerButton();
		btnForfeit = bsview.getForfeitButton();
		btnQuit = bsview.getQuitButton();
		opponentData = bsview.getOpponentData();
		playerData = bsview.getPlayerData();
		water = bsview.getwaterIcon();
		bship = bsview.getbshipIcon();
		watermiss = bsview.getwatermissIcon();
		bshiphit = bsview.getbshiphitIcon();
		
		shipData[0]=2;
		shipData[1]=3;
		shipData[2]=3;
		shipData[3]=4;
		shipData[4]=4;
		
	    for (int i = 0; i < opponent.length; i++)
	    {
	       opponent[i].addActionListener(this);
	       player[i].addActionListener(this);
	       opponent[i].addMouseListener(this);
	       player[i].addMouseListener(this);
	    }
	    
	    btnForfeit.addActionListener(this);
	    btnQuit.addActionListener(this);
	    
		JOptionPane.showMessageDialog(new JFrame(), "Place Your Ships on the bottom half! Left Click, Horizontal. Right Click, Vertical");

		
	}
	
	public void setBattleshipData(BattleshipData bsdata) {
		this.bsdata = bsdata;
	}
	
	public void mouseClicked(MouseEvent me) {
		if(SwingUtilities.isLeftMouseButton(me)) {	
			bsdata.setrightclick(false);
			System.out.println("Left Button");
		}
		else if(SwingUtilities.isRightMouseButton(me)) {
			bsdata.setrightclick(true);
			System.out.println("Right Button");
		}
		Object source = me.getSource();
		for (int i = 0; i < opponent.length; i++)
	    {
	       if (source == opponent[i]) {
	    	   //System.out.println("Opponent: "+ i + " "+ opponentData[i]);
	    	   msgText.setText("Opponent: "+ i + " "+ opponentData[i]);
//	    	   opponent[i].setIcon(water);
	    	   bsdata.setboardIndex(i);
	    	   bsdata.setp1BoardClick(false);
	    	   bsdata.setp1(p1);
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
	    	   bsdata.setp1BoardClick(true);
	    	   bsdata.setp1(p1);
	    	   bsdata.setMessage(msgText.getText());
	       }
	    }
		bsdata.sendToServer();
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		Object source = ae.getSource();
		
		if(source == btnQuit) {
			//JOptionPane.showMessageDialog(new JFrame(), "Quit");
			bsdata.setgameOver(true);
			bsdata.sendToServer();
			bsview.dispose();
			try {
				bsdata.getClient().closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		if(source == btnForfeit) {
			//JOptionPane.showMessageDialog(new JFrame(), "Forfeit");
			bsdata.setgameOver(true);
			bsdata.sendToServer();
			//bsview.dispose();
		}
		
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
//		for (int i = 0; i < opponent.length; i++)
//	    {
//	       if (source == opponent[i]) {
//	    	   //System.out.println("Opponent: "+ i + " "+ opponentData[i]);
//	    	   msgText.setText("Opponent: "+ i + " "+ opponentData[i]);
////	    	   opponent[i].setIcon(water);
//	    	   bsdata.setboardIndex(i);
//	    	   bsdata.setp1BoardClick(false);
//	    	   bsdata.setMessage(msgText.getText());
//	       }
//	    }
//		
//		for (int i = 0; i < player.length; i++)
//	    {
//	       if (source == player[i]) {
//	    	   //System.out.println("Player: "+ i + " "+ playerData[i]);
//	    	   msgText.setText("Player: "+ i + " "+ playerData[i]);
//	    	  // player[i].setIcon(bshiphit);
//	    	   bsdata.setboardIndex(i);
//	    	   bsdata.setp1BoardClick(true);
//	    	   bsdata.setp1(p1);
//	    	   bsdata.setMessage(msgText.getText());
//	       }
//	    }
//		bsdata.sendToServer();
	
	}
	
	public void receiveDataFromServer(battleshipComm bscomm) {
		this.bscomm = bscomm;
		System.out.println("Data from Server");
		System.out.println("Client Player 1: "+p1);
		System.out.println("P1 Board Click: "+bscomm.getp1BoardClick());
		System.out.println("P1 Turn: "+bscomm.getp1Turn());
		System.out.println("P1: "+bscomm.getp1());
		int i = bscomm.getboardIndex();
		int sprite = bscomm.getDataValue();
		System.out.println("Sprite value: "+sprite);
		ImageIcon newSprite;
		if(bscomm.getGameOver()) {
			System.out.println(bscomm.getMessage());
			msgText.setText(bscomm.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), bscomm.getMessage());
//			int choice = JOptionPane.showOptionDialog(null, 
//				      bscomm.getMessage()+"", 
//				      "Quit?", 
//				      JOptionPane.YES_NO_OPTION, 
//				      JOptionPane.QUESTION_MESSAGE, 
//				      null, null, null);
//			 if (choice == JOptionPane.YES_OPTION) {
//				 bsdata.setNewGame(true);
//				 bsdata.setgameOver(false);
//				 bsdata.sendToServer();
//			 }	
//			 else {
//					bsdata.setgameOver(true);
//					bsdata.sendToServer();
//					bsview.dispose();
//					try {
//						bsdata.getClient().closeConnection();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			 }
		}
		else {
		if (bscomm.getValidMove()) {
			if (p1) {
				if (bscomm.getp1BoardClick()) {
					switch (sprite){
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
						//ship
						//change to ship
						int y=0;
						newSprite=bship;
						if(!bscomm.getrightclick()) {
							while(y<shipData[sprite]) {
								
								player[i+y].setIcon(newSprite);
								y++;
							}
						}
						else if(bscomm.getrightclick()) {
							while(y<shipData[sprite]) {
								
								player[i+(y*10)].setIcon(newSprite);
								y++;
							}							
						}
						msgText.setText(bscomm.getMessage());
						System.out.println(sprite);
						//decrement ship no
						//messageToPlayer = new String("There is already a ship here!");
						//p1shipData[p1boardData[boardIndex]]--;
						//p1boardData[boardIndex]=5;
						//p1Turn = false;
						break;
					case 5:
						//hit sprite
						newSprite=bshiphit;
						player[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						break;
					case 6:
						//water
						//Place a ship here********************************************
						//messageToPlayer = new String("AddShipToP1Board");
						//p1boardData[boardIndex]=7;
						//p1Turn = addShipToP1Board(boardIndex);
//						newSprite=watermiss;
//						player[i].setIcon(newSprite);
//						msgText.setText(bscomm.getMessage());
						break;
					case 7:
						//watermiss
						newSprite=watermiss;
						player[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						break;
					default:
						//data error
						//messageToPlayer = new String("Board Data Error, something went wrong");
						break;
					}
				}
				else { //(!p1 boardclick)
					switch (sprite){
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
						//ship
						//change to shiphit
						
//						int y=0;
//						newSprite=bship;
//						if(!bscomm.getrightclick()) {
//							while(y<shipData[sprite]) {
//								
//								opponent[i+y].setIcon(newSprite);
//								y++;
//							}
//						}
//						else if(bscomm.getrightclick()) {
//							while(y<shipData[sprite]) {
//								
//								opponent[i+(y*10)].setIcon(newSprite);
//								y++;
//							}							
//						}
//
//						msgText.setText(bscomm.getMessage());
						//decrement ship no
						//messageToPlayer = new String("There is already a ship here!");
						//p1shipData[p1boardData[boardIndex]]--;
						//p1boardData[boardIndex]=5;
						//p1Turn = false;
						break;
					case 5:
						//hit here
						newSprite=bshiphit;
						opponent[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						//does not change turn
						//messageToPlayer = new String("Board Data Error: 5");
						break;
					case 6:
						//water
						//Place a ship here********************************************
						//messageToPlayer = new String("AddShipToP1Board");
						//p1boardData[boardIndex]=7;
						//p1Turn = addShipToP1Board(boardIndex);
//						newSprite=watermiss;
//						opponent[i].setIcon(newSprite);
						break;
					case 7:
						//watermiss
						newSprite=watermiss;
						opponent[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						break;
						//already missed here
						//does not change turn
						//messageToPlayer = new String("Board Data Error: 7");
						
					default:
						//data error
						//messageToPlayer = new String("Board Data Error, something went wrong");
						break;
					}
				}
			}
			else { //(!p1)
				if (bscomm.getp1BoardClick()) {
					switch (sprite){
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
						//ship
						//change to ship
//						int y=0;
//						newSprite=bship;
//						if(!bscomm.getrightclick()) {
//							while(y<shipData[sprite]) {
//								
//								player[i+y].setIcon(newSprite);
//								y++;
//							}
//						}
//						else if(bscomm.getrightclick()) {
//							while(y<shipData[sprite]) {
//								
//								player[i+(y*10)].setIcon(newSprite);
//								y++;
//							}							
//						}
//						msgText.setText(bscomm.getMessage());
//						//decrement ship no
//						//messageToPlayer = new String("There is already a ship here!");
//						//p1shipData[p1boardData[boardIndex]]--;
//						//p1boardData[boardIndex]=5;
//						//p1Turn = false;
						break;
					case 5:
						//hit here
						//does not change turn
						//messageToPlayer = new String("Board Data Error: 5");
						newSprite=bshiphit;
						player[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						break;
					case 6:
						//water
						//Place a ship here********************************************
						//messageToPlayer = new String("AddShipToP1Board");
						//p1boardData[boardIndex]=7;
						//p1Turn = addShipToP1Board(boardIndex);
//						newSprite=watermiss;
//						player[i].setIcon(newSprite);
						break;
					case 7:
						//watermiss
						newSprite=watermiss;
						player[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						break;
						//already missed here
						//does not change turn
						//messageToPlayer = new String("Board Data Error: 7");
						
					default:
						//data error
						//messageToPlayer = new String("Board Data Error, something went wrong");
						break;
					}
				}
				else { //(!p1 boardclick)
					switch (sprite){
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
						//ship
						//change to ship
						int y=0;
						newSprite=bship;
						if(!bscomm.getrightclick()) {
							while(y<shipData[sprite]) {
								
								opponent[i+y].setIcon(newSprite);
								y++;
							}
						}
						else if(bscomm.getrightclick()) {
							while(y<shipData[sprite]) {
								
								opponent[i+(y*10)].setIcon(newSprite);
								y++;
							}							
						}

						msgText.setText(bscomm.getMessage());
						//decrement ship no
						//messageToPlayer = new String("There is already a ship here!");
						//p1shipData[p1boardData[boardIndex]]--;
						//p1boardData[boardIndex]=5;
						//p1Turn = false;
						break;
					case 5:
						newSprite=bshiphit;
						opponent[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
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
//						newSprite=watermiss;
//						opponent[i].setIcon(newSprite);
//						msgText.setText(bscomm.getMessage());
						break;
					case 7:
						//watermiss
						newSprite=watermiss;
						opponent[i].setIcon(newSprite);
						msgText.setText(bscomm.getMessage());
						//already missed here
						//does not change turn
						//messageToPlayer = new String("Board Data Error: 7");
						break;
					default:
						//data error
						//messageToPlayer = new String("Board Data Error, something went wrong");
						break;
					}
				}
			}
		}
		

		else {
			System.out.println("Invalid Move: Commsp1turn, commsp1, clientp1");
			System.out.println(bscomm.getp1Turn());
			System.out.println(bscomm.getp1());
			System.out.println(p1);

			if(bscomm.getp1()==p1) {
				msgText.setText("Invalid Move: " + bscomm.getMessage());
				JOptionPane.showMessageDialog(new JFrame(), bscomm.getMessage());
			}
			
		}
		
		}
		
		
		//update data and screen with comm object
		//set player or opponent?
		 //player[i].setIcon(newSprite);
		 //opponent[i].setIcon(newSprite);
		//bsdata.setboardDataValue(playerData[i]); Maybe not these two lines?
		// bsdata.setboardDataValue(opponentData[i]);
	}
	
	public void setp1(boolean p) {
		p1=p;
	}
	public boolean getp1() {
		return p1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
