package gamebase;

import static org.junit.Assert.*;

import gamebase.Algorithm;
import gamebase.ConstructionAlgorithm;
import gamebase.Tile;
import gamebase.TileDeck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.Colors;
import common.Construction;
import common.Coordinates;
import common.Direction;
import common.InvalidActionException;
import common.InvalidFileFormatException;
import common.PieceException;

public class ConstructionAlgorithmTest {
	
	private List<Algorithm> algorithms;
	private LinkedList<Coordinates> listCoordinates;
		
	@Before
	public void setUp() throws Exception{
		
		listCoordinates = new LinkedList<Coordinates>();
		algorithms = new ArrayList<Algorithm>();
		
		for(int j=1; j>-2; j--){
			for(int i=-1; i<2; i++){
				listCoordinates.addLast((new Coordinates(i,j)));
			}
		}
		
		algorithms.add(0, new ConstructionAlgorithm(makeTiles("src/test/java/data/completedCityAlgorithmTest.txt"), new Coordinates(0,0), Direction.SOUTH ));
		algorithms.add(1, new ConstructionAlgorithm(makeTiles("src/test/java/data/completedRoadAlgorithmTest.txt"), new Coordinates(-1,1), Direction.SOUTH ));
		algorithms.add(2, new ConstructionAlgorithm(makeTiles("src/test/java/data/notCompletedRoadAlgorithmTest.txt"), new Coordinates(-1,1), Direction.SOUTH ));
		
	}
	
	@Test
	public void checkStructureCompleteTest(){
		assertTrue("Non e' stata trovata la struttura citta' completa", algorithms.get(0).checkCompleteConstruction());
		assertTrue("Non e' stata trovata la struttura strada completa", algorithms.get(1).checkCompleteConstruction());
		assertFalse("E' stata trovata la struttura strada completa", algorithms.get(2).checkCompleteConstruction());
	}
	
	@Test 
	public void getListPiecesTest(){
		algorithms.get(0).checkCompleteConstruction();
		algorithms.get(1).checkCompleteConstruction();
		algorithms.get(2).checkCompleteConstruction();
		assertTrue("Non è stata trovata una lista di pedine contenente la pedina rossa e contenente otto elementi", algorithms.get(0).getListPieces().contains(Colors.RED) && algorithms.get(0).getListPieces().size()==8);
		assertTrue("Non è stata trovata una lista di pedine contenente la pedina rossa e contenente otto elementi", algorithms.get(1).getListPieces().contains(Colors.RED) && algorithms.get(1).getListPieces().size()==8);
		assertTrue("Non è stata trovata una lista di pedine contenente la pedina rossa e contenente sei elementi", algorithms.get(2).getListPieces().contains(Colors.RED) && algorithms.get(2).getListPieces().size()==6);
	}
	
	@Test
	public void getTilesChangedTest(){
		algorithms.get(0).checkCompleteConstruction();
		algorithms.get(1).checkCompleteConstruction();
		algorithms.get(2).checkCompleteConstruction();
		assertTrue("Non è stato trovato il numero giusto di tessere cambiate", algorithms.get(0).getTilesChanged().size()==8);
		assertTrue("Non è stato trovato il numero giusto di tessere cambiate", algorithms.get(1).getTilesChanged().size()==8);
		assertTrue("Non è stato trovato il numero giusto di tessere cambiate", algorithms.get(2).getTilesChanged().size()==6);
	}
	
	
	@Test
	public void numberOfTileConstructionTest(){
	    algorithms.get(0).checkCompleteConstruction();
		algorithms.get(1).checkCompleteConstruction();
		algorithms.get(2).checkCompleteConstruction();
		assertTrue("Non è stato trovato il numero corretto di tiles che compongono la struttura", algorithms.get(0).numberOfTilesConstruction() == 8);
		assertTrue("Non è stato trovato il numero corretto di tiles che compongono la struttura", algorithms.get(1).numberOfTilesConstruction() == 8);
		assertTrue("Non è stato trovato il numero corretto di tiles che compongono la struttura", algorithms.get(2).numberOfTilesConstruction() == 6);
	}
	
	@Test
	public void pointsForTileTest(){
		algorithms.get(0).checkCompleteConstruction();
	    algorithms.get(1).checkCompleteConstruction();
		algorithms.get(2).checkCompleteConstruction();
		assertTrue("Non è stato dato il corretto numero di punti per la struttura trovata", algorithms.get(0).pointsForTile() == 2);
		assertTrue("Non è stato dato il corretto numero di punti per la struttura trovata", algorithms.get(1).pointsForTile() == 1);
		assertTrue("Non è stato dato il corretto numero di punti per la struttura trovata", algorithms.get(2).pointsForTile() == 1);
	}
	private HashMap<Coordinates, Tile> makeTiles(String path){

		TileDeck deck = null;
		Tile tile;
		HashMap<Coordinates, Tile> tiles;
		tiles = new HashMap<Coordinates, Tile>();

		try {
			deck = new TileDeck(4, path);
		} catch (InvalidFileFormatException e) {
			fail("Errore nel formato del file");
		} catch (IOException e) {
			fail("Errore nella lettura del file");
		}
		try {
			tile = deck.getFirstTile();

			for(Direction dir : Direction.values()){
				if(tile.getCostructionByDirection(dir).equals(Construction.CITY) || tile.getCostructionByDirection(dir).equals(Construction.ROAD)){
					try {
						tile.setPiece(dir, Colors.RED);
					} catch (PieceException e) {
					}
				}
			}
			tiles.put(listCoordinates.get(4), tile);
			listCoordinates.remove(4);
			for(Coordinates coord : listCoordinates){
				tile = deck.getNextTile();
				for(Direction dir : Direction.values()){
					if(tile.getCostructionByDirection(dir).equals(Construction.CITY) || tile.getCostructionByDirection(dir).equals(Construction.ROAD)){
						try {
							tile.setPiece(dir, Colors.RED);
						} catch (PieceException e) {
						}
					}
				}
				tiles.put(coord, tile);

			}
			listCoordinates.add(4, new Coordinates(0,0));
		} catch (InvalidActionException e1) {
			fail("Can't take the first tile from the deck");
		}
		
		return tiles;
	}
	

}
