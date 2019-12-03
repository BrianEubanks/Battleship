package battleshipServer;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import battleshipClient.BattleshipController;
import battleshipClient.BattleshipData;
import battleshipClient.BattleshipView;

public class BattleshipServerTest {

	

	private static playGame pg;
	private static battleshipComm startData;
	private static battleshipComm expectedData;
	private static battleshipComm returnData;
	
	 @BeforeClass
	  public static void setUp()
	  {
	   
		 
		 pg = new playGame();
		 startData= new battleshipComm(0);
	    
	    
	  }
	
	@Test
	
	public void validMovePlaceShips() {
		if(pg.placeShips) {
			pg.placeShips(true, 0, true, false);
		}
		assertNotEquals("PlaceShipsError",false,pg.getComms().getValidMove());
		
	}
	@Test
	public void placeShips() {
		pg.placeShips(true, 0, true, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(true, 10, true, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(true, 20, true, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(true, 30, true, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(false, 0, false, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(false, 10, false, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(false, 20, false, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.placeShips(false, 30, false, false);
		assertEquals(pg.getComms().getValidMove(),true);
		
	}
	
	public void move() {
		pg.turn(true, 0, false);
		assertEquals(pg.getComms().getValidMove(),true);
		pg.turn(false, 0, true);
		assertEquals(pg.getComms().getValidMove(),true);
		
	}
	
	

}
