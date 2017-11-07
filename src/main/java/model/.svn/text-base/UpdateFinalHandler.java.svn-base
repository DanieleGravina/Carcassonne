package model;

import common.Listener;
import common.UpdateFinalEvent;
import common.UpdateFinalObject;
/**
 * Classe per inviare agli osservatori l'evento di fine turno.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class UpdateFinalHandler extends EventHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7302613725561532904L;

	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyUpdateFinal(new UpdateFinalEvent((UpdateFinalObject) obj));
	}

}
