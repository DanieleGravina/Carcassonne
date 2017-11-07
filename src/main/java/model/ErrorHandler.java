package model;

import common.ErrorEvent;
import common.Listener;
/**
 * Classe per inviare agli osservatori l'evento di errore.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class ErrorHandler extends EventHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3893074753258320444L;

	@Override
	public void action(Listener listener, Object obj) {
		
		listener.notifyError(new ErrorEvent((Exception)obj));
	
	}

}
