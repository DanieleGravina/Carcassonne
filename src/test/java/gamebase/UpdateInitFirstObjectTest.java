package gamebase;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import common.Colors;
import common.CoordinatesTile;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;

public class UpdateInitFirstObjectTest {
	
	private UpdateInitFirstObject updateObj;
	private CoordinatesTile coordTile;
	private List<Colors> players;
	private UpdateInitObject initObj;
	
	@Before
	public void setUp(){
		coordTile = CoordinatesTileTest.createCustomCoordinatesTile();
		players = new ArrayList<Colors>();
		players.add(Colors.BLACK);
		players.add(Colors.BLUE);
		initObj = UpdateInitObjectTest.createCustomUpdateInitObject();
		
		updateObj = new UpdateInitFirstObject(coordTile, players, Colors.RED, initObj);
	}
	
	@Test
	public void getPlayerListTest(){
		assertEquals(players,updateObj.getPlayerList());
	}
	
	@Test
	public void getFirstTileTest(){
		assertEquals(coordTile, updateObj.getFirstTile());
	}
	
	@Test
	public void getInitObjectTest(){
		assertEquals(initObj, updateObj.getInitObject());
	}
	
	@Test
	public void getYourColorTest(){
		assertEquals(Colors.RED, updateObj.getYourColor());
	}
	
	public static UpdateInitFirstObject getCustomInitFirstObject(){
		
		List<Colors> customPlayers;
		customPlayers = new ArrayList<Colors>();
		customPlayers.add(Colors.BLACK);
		customPlayers.add(Colors.BLUE);
		
		return new UpdateInitFirstObject(CoordinatesTileTest.createCustomCoordinatesTile(), customPlayers, Colors.RED, UpdateInitObjectTest.createCustomUpdateInitObject());
	}
	
}
