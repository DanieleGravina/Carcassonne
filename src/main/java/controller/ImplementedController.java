package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import common.Colors;
import common.Coordinates;
import common.Direction;
import common.InvalidActionException;
import common.InvalidCoordinatesException;
import common.InvalidInputException;
import common.PieceException;
import common.NumberOfPlayerException;
import common.PositioningNotCompatibleException;

import model.MutableModel;

/**
 * Classe controller per eseguire le azioni sul model di gioco
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ImplementedController implements Controller{
	
	private MutableModel controlledModel;
	
	public ImplementedController(MutableModel model){
		controlledModel = model;
	}
	
	/**
	 * Metodo per il parsing del numero di giocatori della partita
	 * @param string La stringa su cui effettuare il parsing
	 * @throws InvalidInputException
	 */
	public void sendInitialInput(String string) throws RemoteException,InvalidInputException{
		int numOfPlayers;
		try{
			numOfPlayers = Integer.parseInt(string);
			setNumberOfPlayer(numOfPlayers);
		}catch(NumberFormatException e){
			throw new InvalidInputException();
		}
	}
	
	/**
	 * Metodo per effettuare il parsing dell'input in arrivo dall'utente
	 * @param string La stringa inserita dall'utente
	 * @throws InvalidInputException
	 */
	public void sendInput(String string) throws RemoteException,InvalidInputException{
		String[] strings = string.split(" ");
		
		if(strings.length >= 1){
			if (strings[0].equals("rotate")){
				rotateTile();
			}
			else if(strings[0].equals("pass")){
				pass();
			}
			else if(strings[0].equals("reconnect")){
				reconnect();
			}
			else{
				if(strings[0].equals("add") && (strings.length >= 2)){
					if(strings[1].equals("tile") && (strings.length == 4)){
						int x,y;
						try{
							x = Integer.parseInt(strings[2]);
							y = Integer.parseInt(strings[3]);
							addTile(x,y);
						}catch(NumberFormatException e){
							throw new InvalidInputException();
						}
					}
					else{
						if(strings[1].equals("piece") && (strings.length == 3)){
							List<String> directions = new ArrayList<String>();
							for(Direction dir : Direction.values()){
								directions.add(dir.toString());
							}
							if(directions.contains(strings[2].toUpperCase())){
								addPiece(Direction.valueOf(strings[2].toUpperCase()));
							}
							else{
								throw new InvalidInputException();
							}
						}
						else{
							throw new InvalidInputException();
						}
					}
				}
				else{
					throw new InvalidInputException();
				}			
			}
		}
	}
	
	/**
	 * metodo per modificare il numero iniziale di giocatori
	 * @throws InvalidActionException
	 */
	private void setNumberOfPlayer(int numOfPlayers){
		
		try {
			controlledModel.addNewPlayer(numOfPlayers);
		} catch (NumberOfPlayerException e) {
		} catch (InvalidActionException e) {
		}
	}
	/**
	 * metodo per ruotare la tessera attuale
	 * @throws InvalidActionException
	 */
	protected void rotateTile(){
		try {
			controlledModel.rotateTile();
		} catch (InvalidActionException e) {
		}
	}
	
	/**
	 * metodo per aggiungere una tessera al tabellone
	 */
	protected void addTile(int x, int y) {
		
		try {
			controlledModel.addTile(new Coordinates(x,y));
		} catch (InvalidCoordinatesException e) {
		} catch (PositioningNotCompatibleException e) {
		} catch (InvalidActionException e) {
		}
	}
	
	/**
	 * metodo per aggiungere una pedina a un lato della tessera
	 */
	protected void addPiece(Direction dir) {
		
		try {
			controlledModel.addPiece(dir);
		} catch (PieceException e) {
		} catch (InvalidActionException e) {
		}
	}
	
	/**
	 * Metodo per fare passo
	 */
	protected void pass(){
		try {
			controlledModel.pass();
		} catch (InvalidActionException e) {

		}
	}

	@Override
	public void enable(boolean val) {
		//Non si fa nulla
	}

	@Override
	public void delPlayerByColor(Colors color) {
		try {
			controlledModel.delPlayerByColor(color);
		} catch (NumberOfPlayerException e) {
			
		}
	}

	@Override
	public void reconnect() throws RemoteException{
		// Non implementato in locale
	}

}
