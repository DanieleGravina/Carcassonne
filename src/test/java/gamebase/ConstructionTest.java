package gamebase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.Construction;
import common.InvalidConstructionException;

public class ConstructionTest {
	
	Construction city;
	Construction road;
	Construction field;
	
	@Before
	public void setUp(){
		city = Construction.CITY;
		road = Construction.ROAD;
		field = Construction.FIELD;
	}
	
	@Test 
	public void convertFromCharTest(){
		try {
			assertTrue("La conversione in char non e' corretta", Construction.convertFromChar('C').equals(city));
			assertTrue("La conversione in char non e' corretta", Construction.convertFromChar('S').equals(road));
			assertTrue("La conversione in char non e' corretta", Construction.convertFromChar('N').equals(field));
		} 
		catch (InvalidConstructionException e) {
			fail("Non e' stata trovata nessuna conversione");
		}
	}
	
	@Test
	public void convertToCharTest(){
		assertTrue("La conversione da enum in char non e' corretta", field.convertToChar() == 'N');
		assertTrue("La conversione da enum in char non e' corretta", city.convertToChar() == 'C');
		assertTrue("La conversione da enum in char non e' corretta", road.convertToChar() == 'S');
	}
	
	@Test
	public void getScoreTest(){
		assertTrue("Il punteggio associato alla struttura non e' corretto", field.getScore() == 0); 
		assertTrue("Il punteggio associato alla struttura non e' corretto", road.getScore() == 1);
		assertTrue("Il punteggio associato alla struttura non e' corretto", city.getScore() == 2);
	}
	
	@Test
	public void getFinalScoreTest(){
		assertTrue("Il punteggio finale associato alla struttura non e' corretto", field.getFinalScore() == 0); 
		assertTrue("Il punteggio finale associato alla struttura non e' corretto", road.getFinalScore() == 1);
		assertTrue("Il punteggio finale associato alla struttura non e' corretto", city.getFinalScore() == 1);
	}
	
	@Test
	public void toStringTest(){
		for(Construction c: Construction.values()){
			c.toString();
		}
	}
}
