package model;

import common.Colors;
import common.Coordinates;
import common.Direction;
import common.InvalidActionException;
import common.InvalidCoordinatesException;
import common.PieceException;
import common.NumberOfPlayerException;
import common.PositioningNotCompatibleException;

public interface MutableModel{
	

	/**
	 * Funzione utilizzata per ritornare l'evento relativo alle informazioni sulla prima tessera di gioco e la prima pescata dal primo giocatore
	 */
	void ready();
	
	/**
	 * Metodo per settare il nome della partita.
	 * @param gameName
	 */
	void setGameName(String gameName);
	
	/**
	 * Posiziona la tessera attuale a delle determinate coordinate
	 * @param coord Le coordinate a cui posizionare la tessera
	 * @throws PositioningNotCompatibleException Indica che il posizionamento in quelle coordinate non e' compatibile con le tessere presenti
	 * @throws InvalidCoordinatesException Indica delle coordinate non valide per il posizionamento della tessera
	 * @throws InvalidActionException Lanciata se l'azione non e' valida nello stato attuale di gioco
	 */
	void addTile(Coordinates coord) throws InvalidCoordinatesException, PositioningNotCompatibleException, InvalidActionException;
	
	/**
	 * Ruota in senso orario la tessera attuale
	 * @throws InvalidActionException Lanciata se l'azione non e' valida nello stato attuale di gioco
	 */
	void rotateTile() throws InvalidActionException;
	
	/**
	 * Aggiunge un segnalino del giocatore attuale nella posizione attuale, nella direzione scelta
	 * @param dir E' la direzione in cui posizionare il segnalino all'interno della tessera attuale
	 * @throws PieceException 
	 * @throws InvalidActionException 
	 */
	void addPiece(Direction dir) throws PieceException, InvalidActionException;
	
	/**
	 * Aggiunge nuovi giocatori alla partita
	 * @param num Indica il numero di giocatori da aggiungere
	 * @throws NumberOfPlayerException Indica che il numero di giocatori scelto non e' valido
	 * @throws InvalidActionException Lanciata se l'azione non e' valida nello stato attuale di gioco
	 */
	void addNewPlayer(int num) throws NumberOfPlayerException, InvalidActionException;
	
	/**
	 * Rimuove un giocatore in base al suo colore
	 * @param color Colore del giocatore da eliminare
	 */
	void delPlayerByColor(Colors color) throws NumberOfPlayerException;
	
	/**
	 * Inizializza il controllo delle strutture complete
	 * @throws InvalidActionException Lanciata se l'azione non e' valida nello stato attuale di gioco
	 */
	void checkCompleteConstructions() throws InvalidActionException;

	/**
	 * Indica una operazione di passo turno che tralascia il posizionamento di un segnalino
	 */
	void pass() throws InvalidActionException;
	
	/**
	 * Passa al turno successivo
	 * @throws NumberOfPlayerException Lanciata se il numero di giocatori nella partita non e' valido
	 */
	void nextRound() throws NumberOfPlayerException;
	
}
