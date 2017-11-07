package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.Construction;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;

/**
 * Classe che fornisce le funzionalità per la rappresentazione testuale del tabellone.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class BoardText {
	
	private char [][] textBoard;
	private int boardMinY;
	private int boardMaxY;
	private int boardMinX;
	private int boardMaxX;
	
	public BoardText(){
		boardMinY = -1;
		boardMaxY = 1;
		boardMinX = -1;
		boardMaxX = 1;
		textBoard = new char[getY(3)+1][getX(3)+1];
		
	}
	
	/**
	 * Metodo che ritorna la matrice di char che rappresenta il tabellone testuale
	 * @return matrice di char 
	 */
	public char[][] getBoard(){
		return textBoard;
	}
	
	/**
	 * Metodo che ritorna la rappresentazione testuale della tessera passata per parametro
	 * @param tile
	 * @return matrice di char
	 */
	public char[][] getTile(CoordinatesTile tile){
		char[][] textTile = new char[getY(1)+1][getX(1)+1];
		char[] structures;
		fillTiles(textTile, 0, 0, 0, 0, 0, 0);
		structures = getStructures(tile);
		fillWithStructure(0,0, structures, textTile, 0, 0);
		return textTile;
	}
	
	private void fillTiles(char[][] board, int x, int y, int minY, int minX, int maxX, int maxY){
		
		for(int i = getY(y+Math.abs(minY)); i<=getY(y+1+Math.abs(minY)); i++){
			for(int j = getX(x+Math.abs(minX)); j<=getX(x+1+Math.abs(minX)); j++){
				
				if( (i==getY(y+Math.abs(minY)) || i==getY(y+1+Math.abs(minY))) && (j==getX(x+Math.abs(minX)) || j==getX(x+1+Math.abs(minX)))){
					board[i][j] = '+';
				}
				else{
					if(i==getY(y+Math.abs(minY)) || i==getY(y+1+Math.abs(minY))){
						if(board[i][j] == '#'){
							board[i][j] = '.';
						}
						else{
							board[i][j] = '#';
						}
					}
					else{
						if(j==getX(x+Math.abs(minX)) || j==getX(x+1+Math.abs(minX))){
							if(board[i][j] == '#'){
								board[i][j] = '.';
							}
							else{
								board[i][j] = '#';
							}
						}
						else{
							board[i][j] = ' ';
						}
					}
				}
				
			}
		}
		
	}
	
	/**
	 * Metodo per aggiungere una tessera alla rappresentazione 
	 * testuale del tabellone
	 * @param coordTile
	 * @param overwrite
	 */
	public void addTileToBoard(CoordinatesTile coordTile, boolean overwrite){
		
		char [] structures;
		int x = coordTile.getCoordinates().getX();
		int y = -coordTile.getCoordinates().getY();
		
		if(x<0){
			x -= 1;
		}
		else{
			x += 1;
		}
		if(y<0){
			y -= 1;
		}
		else{
			y += 1;
		}
		
		updateLimits(x, y);
		
		x = coordTile.getCoordinates().getX();
		y = -coordTile.getCoordinates().getY();
		
		if(!overwrite){
			fillTiles(textBoard, x, y, boardMinY, boardMinX, boardMaxX, boardMaxY);
		}
		
		structures = getStructures(coordTile);
		fillWithStructure(x,y, structures, textBoard, boardMinY, boardMinX);
		
		addHolesToBoard(new Coordinates(x+1,y));
		addHolesToBoard(new Coordinates(x,y+1));
		addHolesToBoard(new Coordinates(x-1,y));
		addHolesToBoard(new Coordinates(x,y-1));
		
	}
	
	private void addHolesToBoard(Coordinates coord){

		int x = coord.getX();
		int y = coord.getY();

		for(int i = getY(y+Math.abs(boardMinY)); i<=getY(y+1+Math.abs(boardMinY)); i++){
			for(int j = getX(x+Math.abs(boardMinX)); j<=getX(x+1+Math.abs(boardMinX)); j++){

				if(textBoard[i][j]=='\u0000'){ 

					if((i==getY(y+Math.abs(boardMinY)) || i==getY(y+1+Math.abs(boardMinY))) && (j==getX(x+Math.abs(boardMinX)) || j==getX(x+1+Math.abs(boardMinX)))){
						textBoard[i][j] = '+';
					}
					else{
						if(i==getY(y+Math.abs(boardMinY)) || i==getY(y+1+Math.abs(boardMinY))){
							textBoard[i][j] = '.';
						}
						else{
							if(j==getX(x+Math.abs(boardMinX)) || j==getX(x+1+Math.abs(boardMinX))){
								textBoard[i][j] = '.';
							}
							if(i==getY(y+Math.abs(boardMinY))+3){
								if(j==getX(x+Math.abs(boardMinX))+1){
									textBoard[i][j] = '(';
								}
								if((j==getX(x+Math.abs(boardMinX))+2) && x<0){
									textBoard[i][j] = '-';
								}
								if(j==getX(x+Math.abs(boardMinX))+3){
									textBoard[i][j] = Character.forDigit(Math.abs((x/10)%10), 10);
								}
								if(j==getX(x+Math.abs(boardMinX))+4){
									textBoard[i][j] = Character.forDigit(Math.abs(x%10), 10);
								}
								if(j==getX(x+Math.abs(boardMinX))+5){
									textBoard[i][j] = ',';
								}
								if(j==getX(x+Math.abs(boardMinX))+6){
									if(y>0){ textBoard[i][j] = '-';}
								}
								if(j==getX(x+Math.abs(boardMinX))+7){
									textBoard[i][j] = Character.forDigit(Math.abs((y/10)%10), 10);
								}
								if(j==getX(x+Math.abs(boardMinX))+8){
									textBoard[i][j] = Character.forDigit(Math.abs(y%10), 10);
								}
								if(j==getX(x+Math.abs(boardMinX))+9){
									textBoard[i][j] = ')';
								}

							}
						}
					}
				}

			}
		}

	}
	
	private void updateLimits(int x, int y){
		
		int oldMaxX = boardMaxX;
		int oldMaxY = boardMaxY;
		int oldMinX = boardMinX;
		int oldMinY = boardMinY;
		
		if(x>boardMaxX){
			boardMaxX = x;
		}
		else{
			if(x<boardMinX){
				boardMinX = x;
			}
		}
		
		if(y>boardMaxY){
			boardMaxY = y;
		}
		else{
			if(y<boardMinY){
				boardMinY = y;
			}
		}
		
		if(x == boardMinX || x == boardMaxX || y == boardMinY || y == boardMaxY){
			char[][] temp = new char[getY(boardMaxY-boardMinY+1)+1][getX(boardMaxX-boardMinX+1)+1]; 
			
			int difMinY = Math.abs(boardMinY)-Math.abs(oldMinY);
			int difMaxY = Math.abs(boardMaxY)-Math.abs(oldMaxY);
			int difMinX = Math.abs(boardMinX)-Math.abs(oldMinX);
			int difMaxX = Math.abs(boardMaxX)-Math.abs(oldMaxX);
			
			for(int i = getY(difMinY); i< temp.length-getY(difMaxY); i++){
				for(int j = getX(difMinX); j< temp[i].length-getX(difMaxX); j++){
					temp[i][j] = textBoard[i-getY(difMinY)][j-getX(difMinX)];
				}
			}
			
			textBoard = temp;
		}
		
		
	}
	
	private char[] getStructures(CoordinatesTile coordTile){

		Set<Direction> avaible = new HashSet<Direction>();
		Set<Construction> visitedConstruction = new HashSet<Construction>();
		char[] structures = new char[20];
		int conn = 1;
		for(Direction dir : Direction.values()){
			avaible.add(dir);
		}

		for(Direction dir : Direction.values()){

			if(avaible.contains(dir)){
				int pos = getPosByDirection(dir);
				if(!coordTile.getConstructionByDirection(dir).equals(Construction.FIELD)){
					if(visitedConstruction.contains(coordTile.getConstructionByDirection(dir))){
						conn += 1;
					}
					visitedConstruction.add(coordTile.getConstructionByDirection(dir));
					structures[pos] = coordTile.getConstructionByDirection(dir).convertToChar();
					structures[pos+1] = Character.forDigit(conn, 10);
					if(coordTile.hasPieceByDirection(dir)){
						structures[pos+2] = '(';
						structures[pos+3] = coordTile.getPieceColorByDirection(dir).convertToChar();
						structures[pos+4] = ')';
					}
					else{
						structures[pos+2] = ' ';
						structures[pos+3] = ' ';
						structures[pos+4] = ' ';
					}
					for(Direction connection : coordTile.getConnectionsByDirection(dir) ){
						avaible.remove(connection);
						int posConn = getPosByDirection(connection);
						structures[posConn] = coordTile.getConstructionByDirection(connection).convertToChar();
						structures[posConn+1] = Character.forDigit(conn, 10);
						if(coordTile.hasPieceByDirection(connection)){
							structures[posConn+2] = '(';
							structures[posConn+3] = coordTile.getPieceColorByDirection(connection).convertToChar();
							structures[posConn+4] = ')';
						}
						else{
							structures[posConn+2] = ' ';
							structures[posConn+3] = ' ';
							structures[posConn+4] = ' ';
						}
					}
				}
				else{
					for(int i = 0; i<5; i++ ){
						structures[pos+i] = ' ';
					}
				}
			}

		}

		return structures;
	}
	
	private void fillWithStructure(int x, int y, char [] structures,char[][] board, int minY, int minX){

		List<Coordinates> arrayCoord = new ArrayList<Coordinates>();
		arrayCoord.add(new Coordinates(getX(x+Math.abs(minX)) + 7, getY(y+Math.abs(minY)) + 1 ));
		arrayCoord.add(new Coordinates(getX(x+Math.abs(minX)) + 1, getY(y+Math.abs(minY)) + 3 ));
		arrayCoord.add(new Coordinates(getX(x+Math.abs(minX)) + 9, getY(y+Math.abs(minY)) + 3 ));
		arrayCoord.add(new Coordinates(getX(x+Math.abs(minX)) + 7, getY(y+Math.abs(minY)) + 5 ));
		int i = 0;
		for(Coordinates coord : arrayCoord){
			if(i==10 && structures[i+2] != '('){
				board[coord.getY()][coord.getX()+3] = structures[i];
				board[coord.getY()][coord.getX()+4] = structures[i+1];
			}
			else{
				board[coord.getY()][coord.getX()] = structures[i];
				board[coord.getY()][coord.getX()+1] = structures[i+1];
				board[coord.getY()][coord.getX()+2] = structures[i+2];
				board[coord.getY()][coord.getX()+3] = structures[i+3];
				board[coord.getY()][coord.getX()+4] = structures[i+4];
			}
			i += 5;
		}

	}
	
	private int getX(int num){
		return num*14;
	}
	
	private int getY(int num){
		return num*6;
	}
	                                              // 0           5          10          15
	private int getPosByDirection(Direction dir){ //[][][][][], [][][][][], [][][][][], [][][][][]
		                                          //  NORTH        WEST        EAST        SOUTH
		if(dir.equals(Direction.NORTH)){
			return 0;
		}
		else{
			if(dir.equals(Direction.WEST)){
				return 5;
			}
			else{
				if(dir.equals(Direction.EAST)){
					return 10;
				}
				else{
					return 15; //SOUTH
				}
			}
		}
	}

}
