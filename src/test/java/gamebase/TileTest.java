package gamebase;

import static org.junit.Assert.*;

import gamebase.Tile;

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
import common.PieceException;

public class TileTest {
	private Tile tile;
	
	@Before
	public void setUp() throws Exception {
		Map<Direction, Construction> costruzioni = new HashMap<Direction, Construction>();
		Map<Direction, Set<Direction>> connessioni = new HashMap<Direction, Set<Direction>>();
		
		costruzioni.put(Direction.NORTH, Construction.ROAD);
		costruzioni.put(Direction.SOUTH, Construction.CITY);
		costruzioni.put(Direction.EAST, Construction.ROAD);
		costruzioni.put(Direction.WEST, Construction.CITY);
		
		connessioni.put(Direction.NORTH, new HashSet<Direction>());
		connessioni.get(Direction.NORTH).add(Direction.WEST);
		
		connessioni.put(Direction.WEST, new HashSet<Direction>());
		connessioni.get(Direction.WEST).add(Direction.NORTH);
		
		connessioni.put(Direction.SOUTH, new HashSet<Direction>());
		connessioni.get(Direction.SOUTH).add(Direction.EAST);
		
		connessioni.put(Direction.EAST, new HashSet<Direction>());
		connessioni.get(Direction.EAST).add(Direction.SOUTH);
		
		tile = new Tile(costruzioni, connessioni);
	}
	
	
	@Test
	public void testGetConstructionByDirection(){
		assertEquals("La costruzione NORD non è uguale alla inizializzazione", tile.getCostructionByDirection(Direction.NORTH), Construction.ROAD);
		assertEquals("La costruzione SUD non è uguale alla inizializzazione", tile.getCostructionByDirection(Direction.SOUTH), Construction.CITY);
		assertEquals("La costruzione EST non è uguale alla inizializzazione", tile.getCostructionByDirection(Direction.EAST), Construction.ROAD);
		assertEquals("La costruzione OVEST non è uguale alla inizializzazione", tile.getCostructionByDirection(Direction.WEST), Construction.CITY);
	}
	
	@Test
	public void testGetConnectionsByDirection(){
		Set<Direction> antani = tile.getConnectionByDirection(Direction.NORTH);
		antani.contains(Direction.WEST);
		assertTrue("La connessione NORD non è uguale alla inizializzazione", tile.getConnectionByDirection(Direction.NORTH).contains(Direction.WEST));
		assertTrue("La connessione OVEST non è uguale alla inizializzazione", tile.getConnectionByDirection(Direction.WEST).contains(Direction.NORTH));
		assertTrue("La connessione SUD non è uguale alla inizializzazione", tile.getConnectionByDirection(Direction.SOUTH).contains(Direction.EAST));
		assertTrue("La connessione EST non è uguale alla inizializzazione", tile.getConnectionByDirection(Direction.EAST).contains(Direction.SOUTH));
	}
	
	@Test
	public void testRotateClockWise(){
		tile.rotate();
		assertEquals("La costruzione NORD non corrisponde dopo la rotazione", tile.getCostructionByDirection(Direction.NORTH), Construction.CITY);
		assertEquals("La costruzione SUD non corrisponde dopo la rotazione", tile.getCostructionByDirection(Direction.SOUTH), Construction.ROAD);
		assertEquals("La costruzione EST non corrisponde dopo la rotazione", tile.getCostructionByDirection(Direction.EAST), Construction.ROAD);
		assertEquals("La costruzione OVEST non corrisponde dopo la rotazione", tile.getCostructionByDirection(Direction.WEST), Construction.CITY);
		
		assertTrue("La connessione NORD non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.NORTH).contains(Direction.EAST));
		assertTrue("La connessione SUD non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.SOUTH).contains(Direction.WEST));
		assertTrue("La connessione EST non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.EAST).contains(Direction.NORTH));
		assertTrue("La connessione OVEST non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.WEST).contains(Direction.SOUTH));
	}
	
	@Test
	public void testRotateCompletly(){
		for (int i=0; i<=3; i++){
			tile.rotate();
		}
		assertEquals("La costruzione NORD non corrisponde dopo la rotazione completa", tile.getCostructionByDirection(Direction.NORTH), Construction.ROAD);
		assertEquals("La costruzione SUD non corrisponde dopo la rotazione completa", tile.getCostructionByDirection(Direction.SOUTH), Construction.CITY);
		assertEquals("La costruzione EST non corrisponde dopo la rotazione completa", tile.getCostructionByDirection(Direction.EAST), Construction.ROAD);
		assertEquals("La costruzione OVEST non corrisponde dopo la rotazione completa", tile.getCostructionByDirection(Direction.WEST), Construction.CITY);
		
		assertTrue("La connessione NORD non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.NORTH).contains(Direction.WEST));
		assertTrue("La connessione SUD non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.WEST).contains(Direction.NORTH));
		assertTrue("La connessione EST non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.SOUTH).contains(Direction.EAST));
		assertTrue("La connessione OVEST non corrisponde dopo la rotazione", tile.getConnectionByDirection(Direction.EAST).contains(Direction.SOUTH));
	}
	
	@Test
	public void testRotateCompletlyClone(){
		tile = tile.cloneTile();
		testRotateCompletly();
	}
	
	@Test
	public void hasConstructionCompatibleTest(){
		Tile rotatedTile = tile.cloneTile();
		rotatedTile.rotate();
		
		assertTrue(rotatedTile.hasConstructionCompatible(tile, Direction.NORTH));
		//TODO more test
	}
	
	@Test
	public void setPieceTest(){
		try {
			tile.setPiece(Direction.NORTH, Colors.BLUE);
		} catch (PieceException e) {
			fail("Non viene consentito il posizionamento di un segnalino");
		}
	}
	
	@Test
	public void hasPieceByDirectionTest(){
		setPieceTest();
		assertTrue(tile.hasPieceByDirection(Direction.NORTH));
	}
	
	@Test
	public void getPieceByDirectionTest(){
		hasPieceByDirectionTest();
		try {
			assertEquals(Colors.BLUE, tile.getPieceByDirection(Direction.NORTH));
		} catch (PieceException e) {
			fail("Segnalino non trovato dopo il posizionamento");
		}
	}
	
	@Test
	public void cancelPieceByDirectionTest(){
		setPieceTest();
		try {
			tile.cancelPieceByDirection(Direction.NORTH);
		} catch (PieceException e) {
			fail("Errore nella cancellazione del segnalino appena posizionato");
		}
		try {
			tile.cancelPieceByDirection(Direction.SOUTH);
			fail("Viene permessa la cancellazione di un segnalino non esistente");
		} catch (PieceException e) {
			//All ok.
		}
	}
	
	@Test
	public void convertToCommonObjectTest(){
		try {
			tile.setPiece(Direction.NORTH, Colors.BLACK);
		} catch (PieceException e) {
			fail("Non viene permessa l'aggiunta di un nuovo segnalino dove non e' presente");
		}
		CoordinatesTile coordTile = tile.convertToCommonObject(new Coordinates(0, 1));
		assertEquals(new Coordinates(0, 1), coordTile.getCoordinates());
		assertTrue("L'oggetto tessera convertito non ha il segnalino nel punto corretto",coordTile.getDirectionOfPiece().contains(Direction.NORTH));
	}
	
	
}
