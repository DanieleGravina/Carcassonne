package model;

import common.CoordinatesTile;
import common.Listener;
import common.UpdateTileEvent;
/**
 * Classe per inviare agli osservatori l'evento di update della tessera.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class UpdateTileHandler extends EventHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8673257556829509101L;

	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyUpdateTile(new UpdateTileEvent((CoordinatesTile) obj));
	}

}