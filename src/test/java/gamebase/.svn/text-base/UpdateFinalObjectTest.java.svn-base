package gamebase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import common.Colors;
import common.CoordinatesTile;
import common.UpdateFinalObject;

public class UpdateFinalObjectTest {
	
	private Set<CoordinatesTile> coordTile;
	private Map<Colors, Integer> points;
	private UpdateFinalObject finalObj;
	private Colors leftPlayer;
	
	@Before
	public void setUp() throws Exception{
		this.coordTile = new HashSet<CoordinatesTile>();
		this.leftPlayer = Colors.BLUE;
		this.coordTile.add(CoordinatesTileTest.createCustomCoordinatesTile());
		this.points = new HashMap<Colors, Integer>();
		this.points.put(Colors.BLUE, new Integer(5));
		this.points.put(Colors.RED, new Integer(6));
		this.finalObj = new UpdateFinalObject(this.coordTile, this.points, this.leftPlayer);
	}
	
	@Test
	public void getTilesTest(){
		assertEquals(this.coordTile,finalObj.getTiles());
	}
	
	@Test
	public void getPointsTest(){
		assertEquals(this.points, this.finalObj.getPoints());
	}
	
	@Test
	public void getLeftPlayer(){
		assertTrue(this.finalObj.getLeftPlayer().equals(Colors.BLUE));
	}
}
