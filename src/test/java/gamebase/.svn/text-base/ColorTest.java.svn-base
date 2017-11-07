package gamebase;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.Colors;

public class ColorTest {
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testNext(){
		assertEquals("Il successivo di BLACK dovrebbe essere RED", Colors.BLACK.next(), Colors.RED);
		assertEquals("Il successivo di BLUE dovrebbe essere GREEN", Colors.BLUE.next(), Colors.GREEN);
	}
	
	public void testPrev(){
		assertEquals("Il precedente di RED dovrebbe essere BLACK", Colors.RED.prev(), Colors.BLACK);
		assertEquals("Il precedente di GREEN dovrebbe essere BLUE", Colors.GREEN.prev(), Colors.BLUE);
	}
	
	@Test
	public void testgetListByFirst(){ // TODO: Migliorare il test
		List<Colors> colorlist;
		List<Colors> colorlist2;
		colorlist = Colors.getListByFirst(Colors.RED);
		assertEquals("La lunghezza della lista di colori riodinata non e' corretta",Colors.values().length, colorlist.size());
		colorlist2 = Colors.getListByFirst(Colors.BLACK);
		assertEquals("La lunghezza della lista di colori riodinata non e' corretta",Colors.values().length, colorlist2.size());
	}
	
	@Test
	public void testRandom(){
		Colors.getRandom(); 
	}
	
	@Test
	public void getListTest(){
		List<Colors> list = Colors.getList();
		assertEquals(Colors.RED, list.get(0));
		assertEquals(Colors.BLUE, list.get(1));
		assertEquals(Colors.GREEN, list.get(2));
		assertEquals(Colors.YELLOW, list.get(3));
		assertEquals(Colors.BLACK, list.get(4));
	}
	
	@Test
	public void convertToCharTest(){
		for(Colors col: Colors.values()){
			col.convertToChar();
		}
	}
	
	@Test
	public void convertToColorTest(){
		for(Colors col: Colors.values()){
			col.convertToColor();
		}
	}
	
}
