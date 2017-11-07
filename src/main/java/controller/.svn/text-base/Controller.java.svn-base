package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Colors;
import common.InvalidInputException;

/**
 * Interfaccia controller per controllare il model
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public interface Controller extends Remote{
	
	/**
	 * Metodo per settare il model per iniziare una nuova partita
	 * @param string
	 * @throws RemoteException
	 * @throws InvalidInputException
	 */
	void sendInitialInput(String string) throws RemoteException, InvalidInputException;
	
	/**
	 * Metodo per modificare il model in base all'input dell'utente
	 * @param string
	 * @throws RemoteException
	 * @throws InvalidInputException
	 */
	void sendInput(String string) throws RemoteException, InvalidInputException;
    
	/**
	 * Metodo per settare il controller per essere abilitato a inviare comandi al model
	 * @param val
	 * @throws RemoteException
	 */
	void enable(boolean val) throws RemoteException;
	
	/**
	 * Metodo per eliminare un giocatore dalla partita in base al suo colore
	 * @param Color Colore del giocatore da eliminare
	 */
	void delPlayerByColor(Colors color) throws RemoteException;
	
	/**
	 * Metodo per effettuare la riconnessione alla partita in caso di disconnessione da una partita remota 
	 */
	void reconnect() throws RemoteException;
}
