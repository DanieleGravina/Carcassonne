package gamebase;

import static org.junit.Assert.*;

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

import view.BoardText;

public class BoardTextTest {
	
	BoardText testBoard;
	
	@Before
	public void setUp() {
		testBoard = new BoardText();
 	}
	
	@Test
	public void getTileTest(){
		
		boolean isEqual = true;
		
		char[][] tileText;
		
		char[][] testTileText = { {'+','#','#','#','#','#','#','#','#','#','#','#','#','#','+'},
				                  {'#',' ',' ',' ',' ',' ',' ','S','1','(','R',')',' ',' ','#'},
				                  {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
				                  {'#','C','1',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','1','#'},
				                  {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
				                  {'#',' ',' ',' ',' ',' ',' ','C','1',' ',' ',' ',' ',' ','#'},
				                  {'+','#','#','#','#','#','#','#','#','#','#','#','#','#','+'},};
		
		tileText = testBoard.getTile(getCoordTile());
		
		for(int i=0; i<7; i++){
			for(int j=0; j<15; j++){
				if(testTileText[i][j] != tileText[i][j]){
					isEqual = false;
				}
			}
		}
		
		assertTrue("la tessera testuale non è stata costruita correttamente", isEqual);
		
		
	}
	
	@Test 
	public void addTileToBoardTest(){
		
		boolean isEqual = true;
		
		char[][] boardText;
		
		testBoard.addTileToBoard(getCoordTile(), false);
		
		boardText = testBoard.getBoard();
		
		char[][] testBoardText = { {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','+','.','.','.','.','.','.','.','.','.','.','.','.','.','+',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.','(',' ','0','0',',',' ','0','1',')',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {'+','.','.','.','.','.','.','.','.','.','.','.','.','.','+','#','#','#','#','#','#','#','#','#','#','#','#','#','+','.','.','.','.','.','.','.','.','.','.','.','.','.','+'},
		                           {'.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ','S','1','(','R',')',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.'},
		                           {'.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.'},
		                           {'.','(','-','0','1',',',' ','0','0',')',' ',' ',' ',' ','#','C','1',' ',' ',' ',' ',' ',' ',' ',' ',' ','S','1','#','(',' ','0','1',',',' ','0','0',')',' ',' ',' ',' ','.'},
		                           {'.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.'},
		                           {'.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ','C','1',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.'},
		                           {'+','.','.','.','.','.','.','.','.','.','.','.','.','.','+','#','#','#','#','#','#','#','#','#','#','#','#','#','+','.','.','.','.','.','.','.','.','.','.','.','.','.','+'},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.','(',' ','0','0',',','-','0','1',')',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		                           {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','+','.','.','.','.','.','.','.','.','.','.','.','.','.','+',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
		
		for(int i=0; i<testBoardText.length; i++){
			for(int j=0; j<testBoardText[i].length; j++){
				if(boardText[i][j] != '\u0000' && testBoardText[i][j] != boardText[i][j]){
					isEqual = false;
				}
			}
		}
		
		assertTrue("il tebellone testuale non è stato costruito correttamente", isEqual);
		
	}
	
	private CoordinatesTile getCoordTile(){
		
		Map<Direction, Set<Direction>> connections = new HashMap<Direction, Set<Direction>>();
		Map<Direction, Construction> constructions = new HashMap<Direction, Construction>();
		Map<Direction, Colors> pieces = new HashMap<Direction, Colors>();
		
		constructions.put(Direction.NORTH, Construction.ROAD);
		constructions.put(Direction.SOUTH, Construction.CITY);
		constructions.put(Direction.EAST, Construction.ROAD);
		constructions.put(Direction.WEST, Construction.CITY);
		
		connections.put(Direction.NORTH, new HashSet<Direction>());
		connections.get(Direction.NORTH).add(Direction.EAST);
		
		connections.put(Direction.EAST, new HashSet<Direction>());
		connections.get(Direction.EAST).add(Direction.NORTH);
		
		connections.put(Direction.SOUTH, new HashSet<Direction>());
		connections.get(Direction.SOUTH).add(Direction.WEST);
		
		connections.put(Direction.WEST, new HashSet<Direction>());
		connections.get(Direction.WEST).add(Direction.SOUTH);
		
		pieces.put(Direction.NORTH, Colors.RED);
		
		return new CoordinatesTile(new Coordinates(0, 0), connections, constructions, pieces);
		
	}

}
