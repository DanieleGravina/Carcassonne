package gamebase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.Direction;
import common.InvalidDirectionException;

public class DirectionTest {
	Direction dirN;
	Direction dirW;
	Direction dirE;
	Direction dirS;
	
	@Before
	public void setUp() throws Exception {
		dirN = Direction.NORTH;
		dirW = Direction.WEST;
		dirE = Direction.EAST;
		dirS = Direction.SOUTH;
	}
	
	@Test
	public void getOppositeTest(){
		assertTrue("La direzione opposta non e' corretta", dirN.getOpposite().equals(Direction.SOUTH));
		assertTrue("La direzione opposta non e' corretta", dirW.getOpposite().equals(Direction.EAST));
		assertTrue("La direzione opposta non e' corretta", dirE.getOpposite().equals(Direction.WEST));
		assertTrue("La direzione opposta non e' corretta", dirS.getOpposite().equals(Direction.NORTH));
	}
	
	@Test
	public void nextTest(){
		assertTrue("La direzione successiva non e' corretta", dirN.next().equals(Direction.EAST));
		assertTrue("La direzione successiva non e' corretta", dirW.next().equals(Direction.NORTH));
		assertTrue("La direzione successiva non e' corretta", dirE.next().equals(Direction.SOUTH));
		assertTrue("La direzione successiva non e' corretta", dirS.next().equals(Direction.WEST));
	}
	
	@Test
	public void prevTest(){
		assertTrue("La direzione precedente non e' corretta", dirN.prev().equals(Direction.WEST));
		assertTrue("La direzione precedente non e' corretta", dirW.prev().equals(Direction.SOUTH));
		assertTrue("La direzione precedente non e' corretta", dirE.prev().equals(Direction.NORTH));
		assertTrue("La direzione precedente non e' corretta", dirS.prev().equals(Direction.EAST));
	}
	
	@Test
	public void convertFromCharTest(){
		try {
			assertTrue("La conversione in char non e' corretta", Direction.convertFromChar('N').equals(dirN));
			assertTrue("La conversione in char non e' corretta", Direction.convertFromChar('S').equals(dirS));
			assertTrue("La conversione in char non e' corretta", Direction.convertFromChar('E').equals(dirE));
			assertTrue("La conversione in char non e' corretta", Direction.convertFromChar('W').equals(dirW));
		} catch (InvalidDirectionException e) {
			fail("Non e' stata trovata nessuna conversione");
		}
	}
	
	@Test
	public void toCharTest(){
		assertTrue("La conversione da enum in char non e' corretta", dirN.toChar() == 'N');
		assertTrue("La conversione da enum in char non e' corretta", dirS.toChar() == 'S');
		assertTrue("La conversione da enum in char non e' corretta", dirE.toChar() == 'E');
		assertTrue("La conversione da enum in char non e' corretta", dirW.toChar() == 'W');
	}

}
