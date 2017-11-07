package gamebase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import common.Colors;
import common.CoordinatesTile;
import common.UpdateInitObject;

public class UpdateInitObjectTest {
	private CoordinatesTile coordTile;
	private UpdateInitObject updateObj;
	
	protected static UpdateInitObject createCustomUpdateInitObject(){
		return new UpdateInitObject(CoordinatesTileTest.createCustomCoordinatesTile(), Colors.GREEN, "Antani");
	}
	
	@Before
	public void setUp(){
		this.coordTile = CoordinatesTileTest.createCustomCoordinatesTile();
		this.updateObj = createCustomUpdateInitObject();
	}
	
	@Test
	public void getGameNameTest(){
		assertEquals("Antani", updateObj.getGameName());
	}
	
	@Test
	public void getCoordTileTest(){
		assertEquals(coordTile, updateObj.getCoordTile());
	}
	
	@Test
	public void getPlayerColorTest(){
		assertEquals(Colors.GREEN, updateObj.getPlayerColor());
	}
	
}
