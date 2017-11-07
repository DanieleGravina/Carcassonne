package view;

import java.io.IOException;

import common.CoordinatesTile;
import common.InvalidInputException;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;

import controller.Controller;

/**
 * Classe che implementa la view testuale locale
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class LocalBasicView extends BasicView{

	public LocalBasicView(Controller controller) {
		super(controller);
	}
	
	@Override
	public void updateInitFirst(UpdateInitFirstObject obj) {
		boardText.addTileToBoard(obj.getFirstTile(), false);
		updateInit(obj.getInitObject());
	}
	
	@Override
	public void updateInit(UpdateInitObject obj){
		super.updateInit(obj);
		System.out.print("\ninsert add tile \"x\" \"y\"  or rotate \n");
	}
	
	@Override
	public void updateTile(CoordinatesTile tile) {
        super.updateTile(tile);
        System.out.print("\ninsert add piece \"direction\" or pass \n");
	}
	
	/**
	 * Metodo per notificare l'errore nell'inserimento del numero di giocatori
	 * @param e
	 */
	public void errorNumberOfPlayers(Exception e){
		System.out.print(e+"\n");
		getInit();
	}
	
	@Override
	public void error(Exception e){
		System.out.print(e+"\n");
	}
	
	private void getInit(){
		System.out.println("Choose the number of players (min 2, max 5) :");
		
		boolean validInput = true;
		do{
        	try {
        		userLine = userInput.readLine();
        		if(userLine != null){
        			gameController.sendInitialInput(userLine);
        		}
        		validInput = true;
        	} 
        	catch (IOException e) {}
        	catch (InvalidInputException e) {
        		System.out.println("Error: invalid input");
        		validInput = false;
        	}
        }while(!validInput);
	}
	
	@Override
	public void start(){
		getInit();
		super.start();
	}

}
