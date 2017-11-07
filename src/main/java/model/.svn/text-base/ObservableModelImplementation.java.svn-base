package model;


import gamebase.Game;

import javax.swing.event.EventListenerList;

import common.Colors;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;
import common.InvalidActionException;
import common.InvalidCoordinatesException;
import common.Listener;
import common.ObservableModel;
import common.PieceException;
import common.NumberOfPlayerException;
import common.PositioningNotCompatibleException;
import common.UpdateFinalObject;
import common.UpdateInitFirstEvent;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;
/**
 * Implementazione del decoratore ObservableModel che aggiunge al model la funzionalità di essere osservato.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class ObservableModelImplementation implements MutableModel,ObservableModel{
	
	private Game model;
	private EventListenerList eventList;
	private Listener[] listOfListeners;
	private EventHandler handler;
	private EventHandler errorHandler;
	
	/**
	 * Costruttore con parametro il model da osservare.
	 * @param gameModel
	 */
	public ObservableModelImplementation(Game gameModel){
		model = gameModel;
		eventList = new EventListenerList();
		errorHandler = new ErrorHandler();
	}
	
	@Override
	synchronized public void addListener(Listener listener) {
		System.out.println("Aggiunto un nuovo listener nel ObservableModelImplementation");
		eventList.add(Listener.class, listener);
		listOfListeners = eventList.getListeners(Listener.class);
	}
	
	@Override
	public void ready() {
		CoordinatesTile firstTile = model.getActualCoordinatesTile();
		try{
			model.nextRound();
		}
		catch (NumberOfPlayerException e) {
			sendNumberOfPlayerToAll(e);
		}
		UpdateInitObject updateobj = new UpdateInitObject(model.getActualCoordinatesTile(), model.getActualPlayerColor(), model.getGameName());
		
		if(listOfListeners.length == 1){
			UpdateInitFirstObject firstobj = new UpdateInitFirstObject(firstTile, model.getColorListOfPlayers(), model.getColorListOfPlayers().get(0),updateobj);
			handler = new UpdateInitFirstHandler();
			handler.notify(listOfListeners, firstobj);
		}
		else if(listOfListeners.length == model.getColorListOfPlayers().size()){
			System.out.println("Invio un evento iniziale differenziato");
			for(int i = 0; i<listOfListeners.length; i++){
				UpdateInitFirstObject firstobj = new UpdateInitFirstObject(firstTile, model.getColorListOfPlayers(), model.getColorListOfPlayers().get(i),updateobj);
				listOfListeners[i].notifyUpdateInitFirst(new UpdateInitFirstEvent((UpdateInitFirstObject) firstobj));
			}
		}
		else{
			sendNumberOfPlayerToAll(new NumberOfPlayerException());
		}
	}
	
	private void sendErrorToAll(Exception e){
		errorHandler.notify(listOfListeners, e);
	}
	
	private void sendEndGameToAll(){
		model.gameEndPointCounting();
		model.getMapChanged();
		handler = new GameFinishedUpdateHandler();
		handler.notify(listOfListeners, model.getPlayersPoints());
	}
	
	private void sendNumberOfPlayerToAll(Exception e){
		handler = new NumberOfPlayerErrorHandler();
		handler.notify(listOfListeners, e);
	}

	@Override
	public void addTile(Coordinates coord) {
		try{
			model.addTile(coord);
			handler = new UpdateTileHandler();
			handler.notify(listOfListeners, model.getActualCoordinatesTile());
		}
		catch(InvalidCoordinatesException e){
			sendErrorToAll(e);      
		}
		catch (PositioningNotCompatibleException e) {
			sendErrorToAll(e);
		}
		catch (InvalidActionException e) {
			sendErrorToAll(e);
		}
	}

	@Override
	public void rotateTile() {
		try {
			model.rotateTile();
			handler = new UpdateRotationHandler();
			handler.notify(listOfListeners, model.getActualCoordinatesTile());
		} catch (InvalidActionException e) {
			sendErrorToAll(e);
		}
	}
	
	@Override
	public void pass(){
		try {
			model.pass();
			finalActions();
		} 
		catch (InvalidActionException e) {
			sendErrorToAll(e);
		}
		
	}
	
	/**
	 * Invia evento per segnalare la fine del turno agli osservatori, cioè alle view.
	 */
	public void finalActions(){
		try{
			model.checkCompleteConstructions();
			
			handler = new UpdateFinalHandler();
			handler.notify(listOfListeners, new UpdateFinalObject(model.getMapChanged(), model.getPlayersPoints(), null)); 
			this.nextRound();
		}
		catch(InvalidActionException e){
			sendErrorToAll(e);
		}
		catch(NumberOfPlayerException e){
			
		}
	}

	@Override
	public void addPiece(Direction dir) {
		try{
			model.addPiece(dir);
			handler = new UpdatePieceHandler();
			handler.notify(listOfListeners, model.getActualCoordinatesTile());
			//Invio l'evento final
			this.finalActions();
		}
		catch(PieceException e){
			sendErrorToAll(e);
		} 
		catch (InvalidActionException e) {
			sendErrorToAll(e);
		}
	}

	@Override
	public void addNewPlayer(int num) {
		try{
			model.addNewPlayer(num);
			
			ready(); //Messo qua perche' supponiamo che non ci siano altre chiamate. Ha senso?
		}
		catch(NumberOfPlayerException e){
			sendNumberOfPlayerToAll(e);
		}
		catch(InvalidActionException e){
			sendErrorToAll(e);
		}
	}

	@Override
	public void checkCompleteConstructions() {
		try{
			model.checkCompleteConstructions();
		}
		catch(InvalidActionException e){
			sendErrorToAll(e);
		}
	}

	@Override
	public void setGameName(String gameName) {
		model.setGameName(gameName);
	}

	@Override
	public void nextRound() throws NumberOfPlayerException {
		if(!model.isGameFinished()){
			//Invia l'evento UpdateInit ai client, per notificare le info del nuovo turno
			try{
				model.nextRound();
			}
			catch (NumberOfPlayerException e) {
				sendEndGameToAll(); // Sto tentando di fare next round con un numero di giocatori non valido, finisce il gioco.
			}
			UpdateInitObject updateobj = new UpdateInitObject(model.getActualCoordinatesTile(), model.getActualPlayerColor(), model.getGameName());
			handler = new UpdateInitHandler();
			handler.notify(listOfListeners, updateobj); 
		}
		else{
			sendEndGameToAll();
		}
	}

	@Override
	public void delPlayerByColor(Colors color) throws NumberOfPlayerException {
		Colors actualColor = model.getActualPlayerColor();
		model.delPlayerByColor(color);
		
		handler = new UpdateFinalHandler();
		handler.notify(listOfListeners, new UpdateFinalObject(model.getChangedTileOfRemovedPlayer(), model.getPlayersPoints(), color));
		if(color.equals(actualColor)){
			try{
				this.nextRound();
			}
			catch(NumberOfPlayerException e){
			}
		}
		if(model.isGameFinished()){
			sendEndGameToAll();
		}
	}

}

