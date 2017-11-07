package gamebase;

import java.util.List;
import java.util.Map;

import common.Colors;
import common.Coordinates;

public interface Algorithm {
	
	/**
	 * controlla se una struttura e' completa. Nel caso lo sia
	 * ritorna true, se no false
	 */
	boolean checkCompleteConstruction();
	
	/**
	 * ritorna una lista che contiene le pedine 
	 * trovate nella struttura
	 */
	List<Colors> getListPieces();
	
	/**
	 * ritorna una Mappa di coordinate e tessere, le quali sono
	 * state cambiate dall'algoritmo (le tessere dove sono stati rimossi i segnalini)
	 */
	Map<Coordinates, Tile> getTilesChanged();
	
	/**
	 * ritorna il numero di tessere 
	 * di cui si compone la struttura
	 */
	int numberOfTilesConstruction();
	
	/**
	 * ritorna il numero di punti ottenuti
	 *  per tessera della struttura
	 */
	int pointsForTile();

}
