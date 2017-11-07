package gamebase;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import common.Coordinates;
import common.Direction;

/* Classe di test creata solo come prova */
public class CoordinatesTest {
	private Coordinates coordinates;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void getXTest(){
		coordinates = new Coordinates(0, 0);
		assertEquals("La coordinata x cambia valore dopo essere inizializzata", 0, coordinates.getX());
	}
	
	@Test
	public void getYTest(){
		coordinates = new Coordinates(0, 0);
		assertEquals("La coordinata y cambia valore dopo essere inizializzata", 0, coordinates.getY());
	}
	
	@Test
	public void toStringTest(){
		assertEquals((new Coordinates(1,0)).toString(), "1,0");
		assertEquals((new Coordinates(0,1)).toString(), "0,1");
	}
	
	@Test
	public void equalsTest(){
		assertEquals((new Coordinates(0, 2)), (new Coordinates(0, 2)));
		assertEquals((new Coordinates(3, 2)), (new Coordinates(3, 2)));
	}
	
	@Test
	public void getMapNearPositionsTest(){
		Map<Direction, Coordinates> adiacenze = (new Coordinates(1,2)).getMapNearPositions();
		
		assertEquals((new Coordinates(1, 3)),adiacenze.get(Direction.NORTH));
		assertEquals((new Coordinates(1, 1)),adiacenze.get(Direction.SOUTH));
		assertEquals((new Coordinates(2, 2)),adiacenze.get(Direction.EAST));
		assertEquals((new Coordinates(0, 2)),adiacenze.get(Direction.WEST));
	}
}
