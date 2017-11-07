package gamebase;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import common.Colors;
import common.Construction;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;
import common.InvalidInputException;

public class CoordinatesTileTest {
	private CoordinatesTile coordTile;
	private Tile testTile;
	
	protected static Map<Direction, Set<Direction>> createCustomConnections(){
		Map<Direction, Set<Direction>> connections = new HashMap<Direction, Set<Direction>>();
		connections.put(Direction.NORTH, new HashSet<Direction>());
		connections.get(Direction.NORTH).add(Direction.WEST);
		
		connections.put(Direction.WEST, new HashSet<Direction>());
		connections.get(Direction.WEST).add(Direction.NORTH);
		
		connections.put(Direction.SOUTH, new HashSet<Direction>());
		connections.get(Direction.SOUTH).add(Direction.EAST);
		
		connections.put(Direction.EAST, new HashSet<Direction>());
		connections.get(Direction.EAST).add(Direction.SOUTH);
		
		return connections;
	}
	
	protected static Map<Direction, Construction> createCustomConstructions(){
		Map<Direction, Construction> constructions = new HashMap<Direction, Construction>();
		constructions.put(Direction.NORTH, Construction.ROAD);
		constructions.put(Direction.SOUTH, Construction.CITY);
		constructions.put(Direction.EAST, Construction.ROAD);
		constructions.put(Direction.WEST, Construction.CITY);
		
		return constructions;
	}
	
	protected static CoordinatesTile createCustomCoordinatesTile(){
		Map<Direction, Colors> pieces = new HashMap<Direction, Colors>();
		pieces.put(Direction.NORTH, Colors.RED);
		CoordinatesTile coordTile = new CoordinatesTile(new Coordinates(0, 0), createCustomConnections(), createCustomConstructions(), pieces);
		
		return coordTile;
	}
	
	protected static Tile createCustomTile(){
		return new Tile(createCustomConstructions(), createCustomConnections());
	}
	
	@Before
	public void setUp() throws Exception {
		this.coordTile = createCustomCoordinatesTile();
		this.testTile = createCustomTile();
		this.testTile.setPiece(Direction.NORTH, Colors.RED);
	}
	
	@Test
	public void testTileConversion(){
		CoordinatesTile tempTile = testTile.convertToCommonObject(new Coordinates(0, 0));
		assertTrue(tempTile.equals(coordTile));
	}
	
	@Test
	public void testGetCoordinates(){
		assertEquals(coordTile.getCoordinates(), new Coordinates(0, 0));
	}
	
	@Test
	public void getDirectionOfPieceTest(){
		Set<Direction> pieces = new HashSet<Direction>();
		pieces.add(Direction.NORTH);
		assertEquals(coordTile.getDirectionOfPiece(), pieces);
	}
	
	@Test
	public void getPieceColorByDirectionTest(){
		assertEquals(coordTile.getPieceColorByDirection(Direction.NORTH), Colors.RED);
	}
	
	@Test
	public void hasPieceTest(){
		assertTrue(coordTile.hasPiece());
	}
	
	@Test
	public void equalsTest(){
		assertEquals("Due oggetti CoordinatesTile forgiati ugualmente non coincidono",createCustomCoordinatesTile(), createCustomCoordinatesTile());
	}
	
	@Test
	public void connectionByDirectionTest(){
		assertTrue(coordTile.getConnectionsByDirection(Direction.NORTH).contains(Direction.WEST));
		assertTrue(coordTile.getConnectionsByDirection(Direction.WEST).contains(Direction.NORTH));
		assertTrue(coordTile.getConnectionsByDirection(Direction.SOUTH).contains(Direction.EAST));
		assertTrue(coordTile.getConnectionsByDirection(Direction.EAST).contains(Direction.SOUTH));
	}
	
	@Test
	public void constructionByDirectionTest(){
		assertTrue(coordTile.getConstructionByDirection(Direction.NORTH).equals(Construction.ROAD));
		assertTrue(coordTile.getConstructionByDirection(Direction.SOUTH).equals(Construction.CITY));
		assertTrue(coordTile.getConstructionByDirection(Direction.EAST).equals(Construction.ROAD));
		assertTrue(coordTile.getConstructionByDirection(Direction.WEST).equals(Construction.CITY));
	}
	
	@Test
	public void toStringTest(){
		assertEquals("N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0", coordTile.toString());
	}
	
	@Test
	public void fromStringTest(){
		try {
			assertEquals("N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0",
					(new CoordinatesTile("N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0").toString()));
		} catch (InvalidInputException e) {
			fail("Error in tile parsing");
			e.printStackTrace();
		}
		try {
			assertEquals("N=S S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0",
					(new CoordinatesTile("N=S S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0").toString()));
		} catch (InvalidInputException e) {
			fail("Error in tile parsing");
			e.printStackTrace();
		}
		try {
			CoordinatesTile coordTileTemp = new CoordinatesTile("N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0,1,2");
			assertEquals("N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0",	coordTileTemp.toString());
			assertEquals(new Coordinates(1,2), coordTileTemp.getCoordinates());
		} catch (InvalidInputException e) {
			fail("Error in tile parsing");
			e.printStackTrace();
		}
		try {
			CoordinatesTile coordTileTemp = new CoordinatesTile("N=S+R S=C W=C E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0,-4,0");
			assertEquals("N=S+R S=C W=C E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0",	coordTileTemp.toString());
			assertEquals(new Coordinates(-4,0), coordTileTemp.getCoordinates());
		} catch (InvalidInputException e) {
			fail("Error in tile parsing");
			e.printStackTrace();
		}
		
	}
}
