package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Map.Entry;

import common.CoordinatesTile;
import common.Colors;
import common.InvalidInputException;
import common.UpdateFinalObject;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;
import controller.Controller;

/**
 * Implementa la view di gioco da linea di comando
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class BasicView implements View{
	protected Controller gameController;
	protected BufferedReader userInput;
	protected String userLine;
	protected boolean gameFinished;
	protected BoardText boardText;
	private Colors myColor;
	private Colors actualColor;
	
	/**
	 * Costruttore con parametro il controller a cui inviare gli input dell'utente
	 * @param controller
	 */
	public BasicView(Controller controller){
		gameController = controller;
		
		gameFinished = false;
		
		boardText = new BoardText();
		
		userInput = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("###########################################\n");
		System.out.print("############## CARCASSONNE ################\n");
		System.out.print("###########################################\n\n");
	}
	
	@Override
	public void updateInitFirst(UpdateInitFirstObject obj) {
		myColor = obj.getYourColor();
		System.out.print("You are the player"+" "+myColor+"\n");
		boardText.addTileToBoard(obj.getFirstTile(), false);
		updateInit(obj.getInitObject());
	}
	
	@Override
	public void updateInit(UpdateInitObject obj){
		char[][] textTile;
		print(boardText.getBoard());
		System.out.print("\n");
		textTile = boardText.getTile(obj.getCoordTile());
		System.out.print("Actual tile:\n");
		print(textTile);
		actualColor = obj.getPlayerColor();
		try {
			gameController.enable(actualColor.equals(myColor));
		} catch (RemoteException e) {
			// Purtroppo dobbiamo gestire cosi se vogliamo usare meno interfacce
		}
		System.out.print("Actual player:"+" "+obj.getPlayerColor().toString().toLowerCase()+"\n");
		if(actualColor.equals(myColor)){
        	System.out.print("\ninsert add tile \"x\" \"y\"  or rotate \n");
        }
	}
	
	
	@Override
	public void updateTile(CoordinatesTile tile) {
        print(updateBoard(tile, false));
        if(actualColor.equals(myColor)){
        	System.out.print("\ninsert add piece \"direction\" or pass \n");
        }
	}
	
	@Override
	public void updatePiece(CoordinatesTile tile) {
		updateBoard(tile, true);
	}
	
	public void error(Exception e){
		if(actualColor.equals(myColor)){
			System.out.print(e+"\n");
		}
	}
	
	@Override
	public void updateFinished(Map<Colors,Integer> finalPoints){
		System.out.print("The game is finished\n");
		for(Entry<Colors,Integer> col : finalPoints.entrySet()){
			System.out.print(col.getKey().toString()+" "+col.getValue()+"\n");
		}
		gameFinished = true;
	}
	
	@Override
	public void updateFinal(UpdateFinalObject obj){
		
		char[][] board;
		
		for(CoordinatesTile tile : obj.getTiles()){
			boardText.addTileToBoard(tile, true);
		}
		
		board = boardText.getBoard();
        print(board);
		
		for(Colors player : obj.getPoints().keySet()){
			System.out.print(player.toString()+" "+obj.getPoints().get(player)+"\n");
		}
		
	}
	
	private void print(char[][] object){
		
		for(int i=0; i<object.length; i++){
			for(int j=0; j<object[i].length; j++){
				if(object[i][j] == '\u0000'){
					System.out.print(" ");
				}
				else{
					System.out.print(object[i][j]);
				}
			}
			System.out.print("\n");
		}
	}
	
	private char[][] updateBoard(CoordinatesTile tile, boolean overwrite){
		char[][] board;
		boardText.addTileToBoard(tile, overwrite);
		board = boardText.getBoard();
		return board;
	}
	
	/**
	 * E' il loop infinito di gioco. Chiede il numero di giocatori, inizializza la partita e cicla i vari turni.
	 */
	public void start(){
		
        do{
        	try {
        		userLine = userInput.readLine();
    			gameController.sendInput(userLine);
        	} 
        	catch (IOException e) {}
        	catch (InvalidInputException e) {
        		System.out.print("Error: invalid input\n");
        	}
        }
		while(!gameFinished);
		
		
	}

	@Override
	public void updateRotation(CoordinatesTile tile) {
		char[][] textTile;
		textTile = boardText.getTile(tile);
		print(textTile);
	}

}
