package model;

import common.CoordinatesTile;
import common.Listener;
import common.UpdatePieceEvent;
/**
 * Classe per inviare agli osservatori l'evento di update del pezzo.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class UpdatePieceHandler extends EventHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2955400917538881803L;

	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyUpdatePiece(new UpdatePieceEvent((CoordinatesTile) obj));
	}

}