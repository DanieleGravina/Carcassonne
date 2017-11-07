package model;

import common.CoordinatesTile;
import common.Listener;
import common.UpdateRotationEvent;
/**
 * Classe per inviare agli osservatori l'evento di rotazione della tessera corrente.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class UpdateRotationHandler extends EventHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5857538272736618318L;

	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyUpdateRotate(new UpdateRotationEvent((CoordinatesTile) obj));
	}

}
