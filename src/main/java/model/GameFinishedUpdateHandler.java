package model;

import java.util.Map;

import common.Colors;
import common.Listener;
import common.UpdateGameFinishedEvent;
/**
 * Classe per inviare agli osservatori l'evento di fine della partita.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class GameFinishedUpdateHandler extends EventHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2512530181753632770L;

	@SuppressWarnings("unchecked")
	@Override
	public void action(Listener listener, Object obj) {
		listener.notifyUpdateFinish(new UpdateGameFinishedEvent((Map<Colors,Integer>) obj));
	}

}