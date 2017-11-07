package gamebase;

import static org.junit.Assert.*;

import gamebase.GameBoard;
import gamebase.Tile;
import gamebase.TileDeck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import common.Construction;
import common.Coordinates;
import common.Direction;
import common.InvalidCoordinatesException;

public class GameBoardTest {
	private GameBoard gameboard;
	private Tile firstTile;
	
	@Before
	public void setUp() throws Exception {
		TileDeck lista = new TileDeck(0, "src/test/java/data/carcassonneTest.txt");
		firstTile = lista.getFirstTile();
		gameboard = new GameBoard(firstTile);
	}
	
	@Test
	public void checkFirstTile(){
		try{
			assertTrue("La prima tessera in (0,0) non corrisponde",
				gameboard.getTileByCoordinates(new Coordinates(0,0)).equals(firstTile));
		}
		catch(InvalidCoordinatesException e){
			fail("Non esiste la tessera iniziale in (0,0)");
		}
	}
	
	/* Viene utilizzato all'interno del test per avere una tessera campione */
	public Tile getCustomTile(){
		Map<Direction, Construction> costruzioni = new HashMap<Direction, Construction>();
		Map<Direction, Set<Direction>> connessioni = new HashMap<Direction, Set<Direction>>();
		
		costruzioni.put(Direction.NORTH, Construction.ROAD);
		costruzioni.put(Direction.SOUTH, Construction.ROAD);
		costruzioni.put(Direction.EAST, Construction.ROAD);
		costruzioni.put(Direction.WEST, Construction.ROAD);
		
		connessioni.put(Direction.NORTH, new HashSet<Direction>());
		connessioni.get(Direction.NORTH).add(Direction.WEST);
		
		connessioni.put(Direction.WEST, new HashSet<Direction>());
		connessioni.get(Direction.WEST).add(Direction.NORTH);
		
		connessioni.put(Direction.SOUTH, new HashSet<Direction>());
		connessioni.get(Direction.SOUTH).add(Direction.EAST);
		
		connessioni.put(Direction.EAST, new HashSet<Direction>());
		connessioni.get(Direction.EAST).add(Direction.SOUTH);
		
		Tile tess = new Tile(costruzioni, connessioni);
		
		return tess;
	}
	
	@Test
	public void addTileTest() throws Exception {
		Tile tess = this.getCustomTile();
		
		gameboard.addTile(new Coordinates(1,0), tess);
		
		Tile next_tessera = gameboard.getTileByCoordinates(new Coordinates(1,0));
		
		assertEquals("La tessera inserita nel tabellone in (1,0 non è uguale a quella ripescata",
				tess, next_tessera);
		
	}
	
	@Test
	public void checkMapTest() throws Exception {
		Tile tess1 = this.getCustomTile();
		Tile tess2 = this.getCustomTile();
		Tile tess3 = this.getCustomTile();
		Tile tess4 = this.getCustomTile();
		
		gameboard.addTile(new Coordinates(0,1), tess1);
		gameboard.addTile(new Coordinates(0,-1), tess2);
		gameboard.addTile(new Coordinates(1,0), tess3);
		gameboard.addTile(new Coordinates(-1,0), tess4);
		
		
		Set<Coordinates> free = gameboard.getFreePositions();
		
		assertEquals("La lunghezza della lista delle posizioni libere non e' corretta", free.size(), 8);
		assertTrue(free.contains(new Coordinates(-2,0)));
		assertTrue(free.contains(new Coordinates(-1,1)));
		assertTrue(free.contains(new Coordinates(0,2)));
		assertTrue(free.contains(new Coordinates(1,1)));
		assertTrue(free.contains(new Coordinates(2,0)));
		assertTrue(free.contains(new Coordinates(1,-1)));
		assertTrue(free.contains(new Coordinates(0,-2)));
		assertTrue(free.contains(new Coordinates(-1,-1)));
		
	}
	
	
	@Test
	public void getTileMarginTest() throws Exception {
		Map<Direction, Coordinates> bordo; 
		
		gameboard.addTile(new Coordinates(0,1), this.getCustomTile());
		gameboard.addTile(new Coordinates(0,-1), this.getCustomTile());
		gameboard.addTile(new Coordinates(1,0), this.getCustomTile());
		gameboard.addTile(new Coordinates(-1,0), this.getCustomTile());
		bordo = gameboard.getCoordinatesMargin();
				
		assertEquals("Il bordo NORD non corrisponde", bordo.get(Direction.NORTH), new Coordinates(0, 2));
		assertEquals("Il bordo SUD non corrisponde", bordo.get(Direction.SOUTH), new Coordinates(0, -2));
		assertEquals("Il bordo OVEST non corrisponde", bordo.get(Direction.WEST), new Coordinates(-2, 0));
		assertEquals("Il bordo EST non corrisponde", bordo.get(Direction.EAST), new Coordinates(2, 0));
	}
	
	@Test
	public void getTileByCoordinatesTest() throws Exception{
		Tile tess1 = this.getCustomTile();
		gameboard.addTile(new Coordinates(0,1), tess1);
		assertEquals("La tessera posizionata in (0,1) non coincide quanto riletta", gameboard.getTileByCoordinates(new Coordinates(0, 1)), tess1);
	}
	
	@Test
	public void checkValidPoisitionsTest(){
		
		assertTrue("La tessera di prova non e' compatibile con il tabellone", gameboard.checkValidPositions(this.getCustomTile()));
	
	}
	
	
	
}
