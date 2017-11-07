package model;

import common.Listener;
import common.UpdateInitFirstEvent;
import common.UpdateInitFirstObject;
/**
 * Classe per inviare agli osservatori l'evento di inizio partita.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class UpdateInitFirstHandler extends EventHandler{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4966551096089987726L;

	@Override
	public void action(Listener listener, Object obj){
		listener.notifyUpdateInitFirst(new UpdateInitFirstEvent((UpdateInitFirstObject) obj));
	}
}
