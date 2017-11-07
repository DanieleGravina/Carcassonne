package gamebase;

import static org.junit.Assert.*;

import gamebase.Tile;
import gamebase.TileDeck;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import common.Construction;
import common.Direction;
import common.InvalidActionException;
import common.InvalidFileFormatException;


public class TileDeckTest {
	private TileDeck list;
	
	@Before
	public void setUp() throws Exception{
		try{
			list = new TileDeck(0, "src/test/java/data/carcassonneTest.txt");
		}
		catch(IOException e){
			fail("Errore nella lettura del file");
		}
		catch(InvalidFileFormatException e){
			fail("Errore nel formato del file");
		}
	}
	
	@Test
	public void checkTileInserted(){
		try{
			Tile tess = list.getFirstTile();
			assertEquals("Costruzione NORD errata", Construction.ROAD, tess.getCostructionByDirection(Direction.NORTH));
			assertEquals("Costruzione SUD errata", Construction.ROAD, tess.getCostructionByDirection(Direction.SOUTH));
			assertEquals("Costruzione OVEST errata", Construction.ROAD, tess.getCostructionByDirection(Direction.WEST));
			assertEquals("Costruzione EST errata", Construction.ROAD, tess.getCostructionByDirection(Direction.EAST));

			assertTrue("Connessione NORD errata "+tess.getConnectionByDirection(Direction.NORTH), tess.getConnectionByDirection(Direction.NORTH).size() == 0);
			assertTrue("Connessione SUD errata "+tess.getConnectionByDirection(Direction.SOUTH), tess.getConnectionByDirection(Direction.SOUTH).size() == 0);
			assertTrue("Connessione OVEST errata "+tess.getConnectionByDirection(Direction.WEST), tess.getConnectionByDirection(Direction.WEST).contains(Direction.EAST));
			assertTrue("Connessione EST errata "+tess.getConnectionByDirection(Direction.EAST), tess.getConnectionByDirection(Direction.EAST).contains(Direction.WEST));
		}
		catch(InvalidActionException e){
			fail("Cannot get the first tile from deck");
		}
	}
}
