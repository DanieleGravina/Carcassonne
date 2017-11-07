package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Controller ausiliario per inviare le notify alla view del client
 * @author Daniele Gravina, Daniele Iamrtino
 *
 */
public interface ReverseController extends Remote{
	
	/**
	 * Notifica associata all'aggiornamento di inizio turno
	 * @param updateEvent
	 */
	void notifyUpdateInit(UpdateInitEvent event) throws RemoteException;
	
	/**
	 * Notifica associata all'azione di rotazione di una tessera
	 * @param updateEvent
	 */
	void notifyUpdateRotate(UpdateRotationEvent event) throws RemoteException;
	
	/**
	 * Notifica associata alla modifica di un insieme di tessere
	 * @param updateEvent
	 */
	void notifyUpdateTile(UpdateTileEvent event) throws RemoteException;
	
	/**
	 * Notifica associata all'aggiunta di un segnalino
	 * @param updateEvent
	 */
	void notifyUpdatePiece(UpdatePieceEvent event) throws RemoteException;

	/**
	 * Notifica associata all'evento di fine un turno
	 * @param updateFinalEvent
	 */
	void notifyUpdateFinal(UpdateFinalEvent event) throws RemoteException;
	
	/**
	 * Notifica associata all'evento di fine partita
	 * @param updateFinalEvent
	 */
	void notifyUpdateFinish(UpdateGameFinishedEvent event) throws RemoteException;
	
	/**
	 * Notifica dell'errore relativo ad un numero non valido di giocatori
	 * @param updateErrorEvent
	 */
	void notifyerrorNumberOfPlayers(NumberOfPlayerErrorEvent event) throws RemoteException;

	/**
	 * Notifica di un errore generico
	 * @param event
	 */
	void notifyError(ErrorEvent event) throws RemoteException;

	/**
	 * Notifica associata all'evento di inizio partita
	 * @param updateInitFirstHandler
	 */
	void notifyUpdateInitFirst(UpdateInitFirstEvent event) throws RemoteException;
	
}
