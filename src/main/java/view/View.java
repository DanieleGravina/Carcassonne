package view;

import java.util.Map;

import common.Colors;
import common.CoordinatesTile;
import common.UpdateFinalObject;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;

/**
 * Interfaccia view della partita
 * 
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public interface View {
	
	/**
	 * Metodo per far partire la view 
	 */
	void start();
	
	/**
	 * Metodo per aggiornare l'aggiunta o la modifica di una tessera
	 * @param tile
	 */
	void updateTile(CoordinatesTile tile);
	
	/**
	 * Metodo per aggiornare la rotazione di una tessera
	 * @param tile
	 */
	void updateRotation(CoordinatesTile tile);
	
	/**
	 * Metodo per aggiornare l'aggiunta di una pedina a una tessera
	 * @param tile
	 */
	void updatePiece(CoordinatesTile tile);
	
	/**
	 * Metodo per aggiornare lo stato iniziale del turno
	 * @param obj
	 */
	void updateInit(UpdateInitObject obj);
	
	/**
	 * Metodo per aggiornare lo stato all'avvio della partita
	 * @param obj
	 */
	void updateInitFirst(UpdateInitFirstObject obj);
	
	
	/**
	 * Metodo per notificare all'utente un errore
	 * @param e
	 */
	void error(Exception e);
	
	/**
	 * Metodo per aggiornare lo stato alla fine del turno
	 * @param obj
	 */
	void updateFinal(UpdateFinalObject obj);

	/**
	 * Metodo per aggiornare lo stato finale della partita
	 * @param finalPoints
	 */
	void updateFinished(Map<Colors, Integer> finalPoints);

	
}

