package gamebase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import common.Colors;


public class PlayerTest {
	private Player player;
	
	@Before
	public void setUp() throws Exception{
		player = new Player(Colors.RED);
	}
	
	@Test
	public void initValuesTest(){
		assertEquals("Punteggio iniziale errato",0, player.getPoints());
		assertEquals("Numero di segnalini iniziali errato",7, player.getNumPiece());
		assertEquals("Colore riletto errato", Colors.RED, player.getColor());
	}
	
	@Test
	public void incrementPointTest(){
		player.incrementPoint(1);
		assertEquals("Punteggio incrementato riletto in modo errato", 1, player.getPoints());
	}
	
	@Test
	public void variationNumPieces(){
		player.variationNumPieces(-1);
		assertEquals("Variazione numero segnalini riletta in modo errato", 6, player.getNumPiece());
		player.variationNumPieces(-7);
		assertEquals("Variazione numero segnalini sotto valore 0 accettata in modo errato", 6, player.getNumPiece());
		player.variationNumPieces(+7);
		assertEquals("Variazione numero segnalini supra valore 7 accettata in modo errato", 6, player.getNumPiece());
	}
}
