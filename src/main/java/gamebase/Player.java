package gamebase;

import common.Colors;

/**
 * Definisce gli attributi relativi ad un giocatore, rilevanti ai fini della gestione della partita.
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class Player {
	private int points;
	private int numPiece;
	private final Colors color;
	private final int maxPieces = 7;
	
	/**
	 * Inizializza l'oggetto giocatore
	 * @param col Specifica il colore associato al giocatore
	 */
	public Player(Colors col){
		this.color = col;
		this.numPiece = maxPieces;
		this.points = 0;
	}
	
	/**
	 * @return Il numero di punti accumulati dal giocatore
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Incrementa il punteggio di un giocatore
	 * 
	 * @param increment Definisce il valore di cui incrementare il punteggio
	 */
	public void incrementPoint(int increment) {
		this.points += increment;
	}
	
	/**
	 * @return Il numero di pedine disponibili al giocatore
	 */
	public int getNumPiece() {
		return numPiece;
	}
	
	/**
	 * Incrementa e decrementa (con valori negativi) il numero di pedine disponibili ad un giocatore
	 * @param increment Valore di incremento/decremento delle pedine
	 */
	public void variationNumPieces(int increment) {
		int newValue = this.numPiece + increment;
		if(newValue >= 0 && newValue <= maxPieces){
			this.numPiece = newValue;
		}
	}

	/**
	 * @return Valore del colore associato al giocatore dato
	 */
	public Colors getColor() {
		return color;
	}
}
