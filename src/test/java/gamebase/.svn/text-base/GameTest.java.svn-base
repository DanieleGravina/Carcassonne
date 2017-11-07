package gamebase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.Colors;
import common.Construction;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;
import common.InvalidActionException;
import common.InvalidCoordinatesException;
import common.NumberOfPlayerException;
import common.PieceException;
import common.PositioningNotCompatibleException;

public class GameTest {
	private Game game;
	
	@Before
	public void setUp() throws Exception{
		game = new Game("src/test/java/data/carcassonneGameTest.txt", false);
	}
	
	@Test
	public void initCoordinatesTile(){
		CoordinatesTile coordTile = game.getActualCoordinatesTile();
		assertEquals("La prima tessera di gioco non corrisponde (errore a NORD)",
				coordTile.getConstructionByDirection(Direction.NORTH), Construction.FIELD);
		assertEquals("La prima tessera di gioco non corrisponde (errore a OVEST)",
				coordTile.getConstructionByDirection(Direction.WEST), Construction.ROAD);
		assertEquals("La prima tessera di gioco non corrisponde (errore a EST)",
				coordTile.getConstructionByDirection(Direction.EAST), Construction.ROAD);
		assertEquals("La prima tessera di gioco non corrisponde (errore a SUD)",
				coordTile.getConstructionByDirection(Direction.SOUTH), Construction.CITY);
	}
	
	@Test
	public void addNewPlayersNumberTest(){
		try {
			game.addNewPlayer(2);
		} 
		catch (NumberOfPlayerException e) {
			fail("2 viene considerato un numero di giocatori non valido");
		} 
		catch (InvalidActionException e) {
			fail("l'aggiunta di 2 giocatori viene considerata una azione non valida");
		}
		
		try {
			game.addNewPlayer(1);
		} 
		catch (NumberOfPlayerException e) {
			//OK
			return;
		} 
		catch (InvalidActionException e) {
			fail("L'aggiunta di un giocatore viene considerata una operazione non valida");
		}
		fail("Ho consentito l'aggiunta di un numero di giocatori pari a 1");
		
	}
	
	private void addPlayers(int num){
		try {
			game.addNewPlayer(num);
		} catch (NumberOfPlayerException e) {
			fail("Errore: Numero di giocatori non valido");
		} catch (InvalidActionException e) {
			fail("Errore: Azione non valida");
		}
	}
	
	@Test
	public void getAvailableColorsTest(){
		assertEquals(game.getAvailableColors(), Arrays.asList(Colors.values()));
		this.addPlayers(2);
		assertTrue("Colore RED presente all'interno della lista di colori disponibili",!game.getAvailableColors().contains(Colors.RED));
		assertTrue("Colore BLUE presente all'interno della lista di colori disponibili", !game.getAvailableColors().contains(Colors.BLUE));
	}
	
	@Test
	public void getColorListOfPlayersTest(){
		this.addPlayers(2);
		List<Colors> list_color = game.getColorListOfPlayers();
		assertTrue("Colore RED mancante all'interno della lista di colori occupati",list_color.contains(Colors.RED));
		assertTrue("Colore BLUE mancante all'interno della lista di colori occupati", list_color.contains(Colors.BLUE));
	}
	
	public void delPlayerByColorTest(){
		this.addPlayers(3);
		try {
			game.delPlayerByColor(Colors.RED);
		} catch (NumberOfPlayerException e) {
			fail("La cancellazione del primo dei tre giocatori aggiunti viene consierata un errore");
		}
		List<Colors> list_color = game.getColorListOfPlayers();
		assertTrue("Colore RED mancante all'interno della lista di colori occupati",list_color.contains(Colors.RED));
		assertTrue("Colore BLUE mancante all'interno della lista di colori occupati", list_color.contains(Colors.BLUE));
	} 
	
	private void nextRound(){
		this.addPlayers(2);
		try {
			game.nextRound();
		} catch (NumberOfPlayerException e) {
			fail("Errore sul numero dei giocatori durante la chiamata al turno successivo");
		}
	}
	
	@Test
	public void nextRoundTest(){
		this.addPlayers(3);
		try {
			game.nextRound();
		} 
		catch (NumberOfPlayerException e) {
			fail("Il numero di giocatori non e' valido per il lancio del turno seguente");
		}
		assertEquals("Il primo turno non viene associato al giocatore RED",game.getActualPlayerColor(),Colors.RED);
		assertEquals("Il numero del round non e' corretto", game.getRoundNumber(), 1);
	}
	
	@Test
	public void nextRoundInvalidNumberTest(){
		this.addPlayers(2);
		
		try {
			game.delPlayerByColor(game.getColorListOfPlayers().get(0));
		} 
		catch (NumberOfPlayerException e) {
			fail("Sto tentando di rimuovere un giocatore dei due ma l'operazione genera un errore");
		}
		
		try {
			game.nextRound();
		} catch (NumberOfPlayerException e) {
			return;
		}
		fail("Non viene lanciata nessuna eccezione quando richiedo il turno seguente con un unico giocatore");
	}
	
	@Test
	public void isGameFinishedTest(){
		this.addPlayers(2);
		try {
			game.delPlayerByColor(game.getColorListOfPlayers().get(0));
		} catch (NumberOfPlayerException e) {
			fail("Vengo bloccato quando tento di cancellare uno dei due giocatori della partita (il primo)");
		}
		assertTrue("Il gioco non risulta terminato con un unico giocatore in campo",game.isGameFinished());
	}
	
	@Test
	public void rotateTileTest(){
		this.nextRound();
		CoordinatesTile firstTile = game.getActualCoordinatesTile();
		for(int i = 0; i<4; i++){
			try {
				game.rotateTile();
			} catch (InvalidActionException e) {
				fail("La rotazione della tessera dopo averla pescata viene considerata una azione non valida");
			}
		}
		assertEquals("La tessera ruotata completamente non corrisponde a quella precedente", firstTile, game.getActualCoordinatesTile());
	}
	
	@Test
	public void addTileTest(){
		this.nextRound();
		try {
			game.addTile(new Coordinates(-1, 0));
		} catch (InvalidCoordinatesException e) {
			fail("Le coordinate scelte per piazzare la tessera non vengono considerate valide");
		} catch (PositioningNotCompatibleException e) {
			fail("Posizionamento non valido della tessera nella partita");
		} catch (InvalidActionException e) {
			fail("L'azione di aggiunta di una tessera viene considerata non valida");
		}
	}
	
	@Test
	public void addPieceTest(){
		this.addTileTest();
		try {
			game.addPiece(Direction.EAST);
		} catch (PieceException e) {
			fail("Non viene permesso il posizionamento del segnalino nella direzione scelta");
		} catch (InvalidActionException e) {
			fail("L'aggiunta del segnalino non viene considerta una azione valida");
		}
	}
	
}
