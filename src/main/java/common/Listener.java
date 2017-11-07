package common;

import java.util.EventListener;

/**
 * E' l'interfaccia che definisce le funzioni di callback chiamate
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public interface Listener extends EventListener{
	
	/**
	 * Notifica associata all'aggiornamento di inizio turno
	 * @param updateEvent
	 */
	void notifyUpdateInit(UpdateInitEvent updateEvent);
	
	/**
	 * Notifica associata all'azione di rotazione di una tessera
	 * @param updateEvent
	 */
	void notifyUpdateRotate(UpdateRotationEvent updateEvent);
	
	/**
	 * Notifica associata alla modifica di un insieme di tessere
	 * @param updateEvent
	 */
	void notifyUpdateTile(UpdateTileEvent updateEvent);
	
	/**
	 * Notifica associata all'aggiunta di un segnalino
	 * @param updateEvent
	 */
	void notifyUpdatePiece(UpdatePieceEvent updateEvent);
	
	/**
	 * Notifica associata all'evento di fine un turno
	 * @param updateFinalEvent
	 */
	void notifyUpdateFinal(UpdateFinalEvent updateFinalEvent);
	
	/**
	 * Notifica associata all'evento di fine partita
	 * @param updateFinalEvent
	 */
	void notifyUpdateFinish(UpdateGameFinishedEvent updateFinalEvent);
	
	/**
	 * Notifica dell'errore relativo ad un numero non valido di giocatori
	 * @param updateErrorEvent
	 */
	void notifyerrorNumberOfPlayers(NumberOfPlayerErrorEvent updateErrorEvent);
	
	/**
	 * Notifica di un errore generico
	 * @param event
	 */
	void notifyError(ErrorEvent event);
	
	/**
	 * Notifica associata all'evento di inizio partita
	 * @param updateInitFirstHandler
	 */
	void notifyUpdateInitFirst(UpdateInitFirstEvent updateInitFirstHandler);

}
