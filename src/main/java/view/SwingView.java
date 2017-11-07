package view;

import java.rmi.RemoteException;
import java.util.Map;

import javax.swing.JFrame;

import common.Colors;
import common.CoordinatesTile;
import common.UpdateFinalObject;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;
import controller.Controller;

/**
 * Graphical User Interface con l'utilizzo di librerie Swing
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class SwingView implements View{
	
	private final static String TITLE = "Carcassonne";
	protected SwingBoard swingBoard;
	private Controller controller;
	private Colors myColor;
	
	/**
	 * Costruttore con parametro il controller a cui mandare gli input dell'utente
	 * @param controller
	 */
	public SwingView(Controller controller){
		this.controller = controller;
		swingBoard = new SwingBoard(TITLE, controller);
	}
	
	@Override
	public void start(){ //TODO menu iniziale
		swingBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void updateTile(CoordinatesTile tile) {
		swingBoard.putTileToBoard(tile);
		swingBoard.drawBoard();
		
	}

	@Override
	public void updateRotation(CoordinatesTile tile) {
		swingBoard.rotateTile();
		swingBoard.drawBoard();
		
	}

	@Override
	public void updatePiece(CoordinatesTile tile) {
		swingBoard.putPieceToTile(tile);
		swingBoard.drawBoard();
		
	}

	@Override
	public void updateInit(UpdateInitObject obj) {
		try {
			controller.enable(obj.getPlayerColor().equals(myColor));
		} catch (RemoteException e) {
			// Purtroppo dobbiamo gestire cosi se vogliamo usare meno interfacce
		}
		swingBoard.updateCurrentPlayer(obj.getPlayerColor());
		swingBoard.drawTile(obj.getCoordTile());
		swingBoard.drawBoard();
	}

	@Override
	public void updateInitFirst(UpdateInitFirstObject obj) {
		swingBoard.putTileToBoard(obj.getFirstTile());
		myColor = obj.getYourColor();
		swingBoard.addPlayers(obj);
		swingBoard.updateMyColor(myColor);
		updateInit(obj.getInitObject());
		swingBoard.setVisible(true);
	}

	@Override
	public void error(Exception e) {
		swingBoard.updateError(e);
	}

	@Override
	public void updateFinal(UpdateFinalObject obj) {
		boolean leftPlayer = false;
		if(obj.getLeftPlayer() != null){
			leftPlayer = true;	
	    }
		for(CoordinatesTile tile : obj.getTiles()){
			swingBoard.resetTileToBoard(tile, leftPlayer);
		}
		swingBoard.updatePlayers(obj.getPoints());
		swingBoard.drawBoard();
		
	}

	@Override
	public void updateFinished(Map<Colors, Integer> finalPoints) {
		swingBoard.updatePlayers(finalPoints);
		swingBoard.updateError(new Exception("Game Over"));
		swingBoard.resetAll();
		try {
			controller.enable(false);
		} catch (RemoteException e) {
		}
		
	}

}
