package model;

import common.Listener;
import common.NumberOfPlayerErrorEvent;

/**
 * Handler per gestire gli eventi che segnalano un errore nel settaggio del numero di giocatori.
 * @author Daniele Gravina, Daniele Iamrtino
 *
 */
public class NumberOfPlayerErrorHandler extends EventHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6095166534958314182L;

	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyerrorNumberOfPlayers(new NumberOfPlayerErrorEvent((Exception) obj));
	}

}
