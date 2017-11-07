package model;

import common.Listener;
import common.UpdateInitEvent;
import common.UpdateInitObject;
/**
 * Classe per inviare agli osservatori l'evento di inizio turno.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class UpdateInitHandler extends EventHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1904792430594443702L;

	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyUpdateInit(new UpdateInitEvent((UpdateInitObject)obj));
	}

}
