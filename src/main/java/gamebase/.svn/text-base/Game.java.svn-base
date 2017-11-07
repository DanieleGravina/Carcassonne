package gamebase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import common.Colors;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;
import common.InvalidActionException;
import common.InvalidCoordinatesException;
import common.InvalidFileFormatException;
import common.PieceException;
import common.NumberOfPlayerException;
import common.PositioningNotCompatibleException;

import model.MutableModel;

/**
 * Gestisce la logica di gioco relativa ai turni per l'implementazione base.
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class Game implements MutableModel{
	private List<Player> playerList;
	private TileDeck tileList;
	private GameBoard gameboard;
	// Informazioni di stato del gioco:
	private int roundNumber;
	private String gameName;
	private Tile actualTile;
	private Coordinates actualCoordinates;
	private Player actualPlayer;
	private Phase gamePhase;
	private Set<Algorithm> successfulAlgorithms;
	
	public Game(){
		this("", true);
	}
	
	public Game(String deckFilename, boolean shuffle) {
		//Il numero massimo di giocatori e' dettato dal numero di colori disponibili.
		playerList = new ArrayList<Player>(Colors.values().length);
		
		/*
		 * Daremo la possibilita' all'utente di scegliere il mazzo? Altrimenti bisogna fare un altro costruttore.
		 * In caso di problema nell'apertura del file mazzo, avvisare in qualche modo il chiamante. Ha senso avvisare direttamente la view/server
		 * Idem se c'è un problema nel formato del file. Avvisare in qualche modo il chiamante.
		 */
		try{
			if(!deckFilename.equals("")){
				tileList = new TileDeck(0, deckFilename); // Inizializziamo un nuovo mazzo con il file di default
			}
			else{
				tileList = new TileDeck(0);
			}
		}
		catch(IOException e){
			//TODO Errore nell'apertura del file tessere. 
		}
		catch(InvalidFileFormatException e){
			//TODO File tessere non valido
		}
		
		try {
			actualTile = tileList.getFirstTile();
		} catch (InvalidActionException e) {
			System.out.print("Can't get the first tile from the deck\n");
		}
		if(shuffle){
			tileList.shuffle(); // Mescoliamo il mazzo
		}
		actualCoordinates = new Coordinates(0, 0);
		gameboard = new GameBoard(actualTile); //Passa al GameBoard la prima tile da piazzare in 0,0
		
		this.roundNumber = 0;
		//TODO ? Dobbiamo fare altro?
	}
	
	/**
	 * Metodo per ottenere il turno attuale
	 * @return Un intero che indica il numero progressivo del turno
	 */
	public int getRoundNumber() {
		return roundNumber;
	}
	
	/**
	 * Permette di trovare un giocatore a partire dal suo colore
	 * @param color Colore del giocatore cercato
	 * @return Giocatore trovato
	 * @throws NumberOfPlayerException Lanciata se non esiste nessun giocatore con quel colore.
	 */
	public Player getPlayerByColor(Colors color) throws NumberOfPlayerException{
		for(Player p: playerList){
			if(p.getColor().equals(color)){
				return p;
			}
		}
		throw new NumberOfPlayerException();
	}
	
	/**
	 * @return Ritorna una lista di colori associata alla lista di giocatori, ordinata nello stesso modo.
	 */
	public List<Colors> getColorListOfPlayers(){
		List<Colors> templist = new ArrayList<Colors>();
		for(Player p: playerList){
			templist.add(p.getColor());
		}
		return templist;
	}
	
	/**
	 * 
	 * @return Una lista di colori disponibili nell'ordine di priorita' corretto
	 */
	public List<Colors> getAvailableColors(){
		List<Colors> colorsPlayers = getColorListOfPlayers();
		List<Colors> availableColors = new ArrayList<Colors>();
		for(Colors c: Colors.values()){
			availableColors.add(c);
		}
		for(Colors c: colorsPlayers){
			availableColors.remove(c);
		}
		return availableColors;
	}
	
	/**
	 * Aggiunge un nuovo giocatore all'oggetto Gioco attuale. Al nuovo giocatore
	 * viene assegnato il colore successivo disponibile in base all'ordine
	 * definito nella enum Colore.
	 * @param num Numero di giocatori da aggiungere
	 * 
	 */
	public void addNewPlayer(int num) throws NumberOfPlayerException, InvalidActionException{
		if (this.roundNumber == 0){
			//TODO: In caso di gioco via rete il controllo num>=2 non adrebbe fatto, perche' questa funzione
			// Puo' essere chiamata piu' volte. Come gestiamo la cosa? Facciamo un altro metodo?
			if(getAvailableColors().size() >= num && num >= 2){ //Se ci sono colori disponibili e ho richiesto di aggiungere almeno 2 giocatori
				for(int i = 0; i<num; i++){
					List<Colors> colorAvail = getAvailableColors();
					playerList.add(new Player(colorAvail.get(0)));
					this.actualPlayer = playerList.get(0);
				}
			}
			else{
				throw new NumberOfPlayerException();
			}
		}
		else{
			throw new InvalidActionException();
		}
	}
	
	/**
	 * Imposta il nome della partita
	 * @param gameName Nome della partita
	 */
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	
	/**
	 * Cancella un giocatore dalla partita
	 * @param col Il colore del giocatore da cancellare
	 * @throws NumberOfPlayerException Viene lanciata nel caso il colore dato come parametro non sia presente nella lista giocatori.
	 */
	public void delPlayerByColor(Colors col) throws NumberOfPlayerException {
		if(getColorListOfPlayers().contains(col)){
			playerList.remove(getPlayerByColor(col));
			gameboard.removePiecesOfPlayer(col);
		}
		else{
			throw new NumberOfPlayerException();
		}
	}
	
	/**
	 * Restituisce un Set contenente le tessere di tipo CoordinatesTile modificate in seguito
	 * all'operazione di rimozione di un giocatore
	 * @return
	 */
	public Set<CoordinatesTile> getChangedTileOfRemovedPlayer(){
		Set<CoordinatesTile> coordTiles = new HashSet<CoordinatesTile>();
		Map<Coordinates, Tile> tiles = gameboard.getChangedTiles();
		for(Entry<Coordinates, Tile> tile: tiles.entrySet()){
			coordTiles.add(tile.getValue().convertToCommonObject(tile.getKey()));
		}
		return coordTiles;
	}
	
	/**
	 * @return Ritorna una lista di colori ordinata in base ai turni. Il primo elemento e' il giocatore attuale e il secondo elemento e' il prossimo giocatore
	 */
	public List<Colors> getPlayerColorListRounds(){
		return Colors.getListByFirst(actualPlayer.getColor());
	}
	
	/**
	 * Passa al turno successivo. Si occupa di impostare il nuovo giocatore attuale e la tessera pescata
	 * Il chiamante deve occuparsi di controllare se la partita e' terminata prima e dopo la chiamata a questo metodo.
	 */
	public void nextRound() throws NumberOfPlayerException{
		//Nota: Non faccio nessun controllo perche' il giocatore puo' fare "passo"
		gamePhase = Phase.INIT; 

		if(playerList.size() >= 2){
			if (this.roundNumber == 0){
				actualPlayer = playerList.get(0);
			}
			else{
				actualPlayer = playerList.get((playerList.indexOf(actualPlayer)+1)%(playerList.size())); // Prende il successivo oggetto giocatore
			}
			this.roundNumber += 1;
			if(!isGameFinished()){
				do{
					try{
						actualTile = tileList.getNextTile(); // Pesca la tessera e la salva come tessera attuale
					}
					catch(InvalidActionException e){
						break;
					}
				}while((!gameboard.checkValidPositions(actualTile)) && (!isGameFinished()));
			}
		}
		else{
			throw new NumberOfPlayerException();
		}
	}
	
	/**
	 * Indica se una partita e' conclusa. Una partita viene considerata conclusa se non ci sono piu' carte o ci sono meno di due giocatori
	 * @return True se la partita e' giunta al termine. 
	 */
	public boolean isGameFinished(){
		return !(tileList.size() > 0 && (playerList.size() >= 2)); // Se non ci sono piu' tessere nel mazzo o ci sono meno di 2 giocatori, il gioco e' finito.

	}
	
	/**
	 * Inizializza le funzioni per il conteggio dei punti di fine gioco (anche per le strutture non complete)
	 */
	public void gameEndPointCounting(){
		if(isGameFinished()){
			this.successfulAlgorithms = gameboard.getAllStructures();
		}
	}
	
	/**
	 * Restituisce i punteggi dei giocatori
	 * @return Una mappa <Color,Integer> che indica il punteggio associato ad ogni giocatore
	 */
	public Map<Colors, Integer> getPlayersPoints(){
		Map<Colors, Integer> toReturn = new HashMap<Colors, Integer>();
		for(Player p: playerList){
			toReturn.put(p.getColor(), p.getPoints());
		}
		return toReturn;
	}
	
	/**
	 * Aggiunge la tessera attuale (quella pescata) alla griglia di gioco
	 * @param coord E' la coordinata dove piazzare la tessera pescata
	 */
	@Override
	public void addTile(Coordinates coord) throws InvalidCoordinatesException,
				PositioningNotCompatibleException, InvalidActionException { //Gestire ripescaggio. Se non esistono posizionamenti validi la scartiamo??
		if(gamePhase.equals(Phase.INIT)){
			gameboard.addTile(coord, actualTile);
			actualCoordinates = coord;
			gamePhase = Phase.TILEPLACED; //Se ci sono eccezioni non viene eseguito.
		}
		else{
			throw new InvalidActionException();
		}
	}
	
	/**
	 * Restituisce la tessera piazzata o da piazzare
	 * @return Un oggetto CoordinatesTile associato alla tessera da piazzare o piazzata nelle coordinati attuali
	 */
	public CoordinatesTile getActualCoordinatesTile(){
		return actualTile.convertToCommonObject(actualCoordinates); //TODO: Attenzione a quando a' null
	}
	
	/**
	 * Restituisce il giocatore attualmente in gioco
	 * @return Il colore del giocatore attuale
	 */
	public Colors getActualPlayerColor(){
		return actualPlayer.getColor();
	}
	
	/**
	 * @return Il nome della partita
	 */
	public String getGameName(){
		return gameName;
	}
	
	/**
	 * Metodo per ruotare la tessera attuale (prima di piazzarla nella griglia di gioco)
	 */
	@Override
	public void rotateTile() throws InvalidActionException{
		if(gamePhase.equals(Phase.INIT)){
			actualTile.rotate();
		}
		else{
			throw new InvalidActionException();
		}
	}
	
	/**
	 * Metodo per assegnare i punteggi delle strutture completate
	 * @param algo L'algoritmo che ha effettuato i conteggi
	 */
	private void assignStructurePoints(Algorithm algo){
		//assegna i punti guardando chi domina
		Integer max = 0;
		Map<Colors, Integer> rating = new HashMap<Colors, Integer>();
		for (Colors c: algo.getListPieces()){
			if(rating.containsKey(c)){ 
				rating.put(c, (rating.get(c)+1));
			}
			else{
				rating.put(c, 1);
			}
			if(rating.get(c)>max){
				max = rating.get(c);
			}
		}
		for (Entry<Colors,Integer> rate: rating.entrySet()){
			if(rate.getValue().equals(max)){ //Ho trovato il colore di uno dei vincitori
				Player aWinner;
				try {
					aWinner = getPlayerByColor(rate.getKey());
					//assegno i punti
					aWinner.incrementPoint(algo.pointsForTile()*algo.numberOfTilesConstruction());
					//restituisco i segnalini
					aWinner.variationNumPieces(max);
				} catch (NumberOfPlayerException e) { // L'eccezione non puo' mai essere lanciata dato che il giocatore esiste sempre
				}

			}
		}
	}
	
	/**
	 * Metodo per inizializzare il calcolo delle strutture complete
	 */
	public void checkCompleteConstructions() throws InvalidActionException{
		if(gamePhase.equals(Phase.TILEPLACED) || gamePhase.equals(Phase.PIECEPLACED)){
			this.successfulAlgorithms = gameboard.checkCompleteConstruction(actualCoordinates);
		}
		else{
			throw new InvalidActionException();
		}
		
	}
	
	/**
	 * Metodo per ottenere gli oggetti modificati dall'algoritmo di ricerca strutture complete
	 * @return Un set di oggetti CoordinatesTile modificati dall'algoritmo
	 */
	public Set<CoordinatesTile> getMapChanged(){
		Set<CoordinatesTile> setTilesChanged = new HashSet<CoordinatesTile>();
		
		for(Algorithm algo: successfulAlgorithms){
			assignStructurePoints(algo);
			for(Coordinates coord : algo.getTilesChanged().keySet()){
				CoordinatesTile temp = algo.getTilesChanged().get(coord).convertToCommonObject(coord);
				setTilesChanged.add(temp);
			}
		}
		
		return setTilesChanged;
	}
	
	/**
	 * Aggiunge una pedina nella direzione scelta all'interno della tessera appena posizionata
	 * @param dir Direzione interna alla tessera attuale dove posizionare la pedina
	 */
	@Override
	public void addPiece(Direction dir) throws PieceException, InvalidActionException{
		if(gamePhase.equals(Phase.TILEPLACED)){
			if(actualPlayer.getNumPiece() > 0){ // Il giocatore ha almeno un segnalino disponibile
				gameboard.addPiece(actualTile, actualCoordinates, actualPlayer.getColor(), dir);
				// Se c'è un errore viene ritornata una eccezione.
				
				actualPlayer.variationNumPieces(-1);
				
				gamePhase = Phase.PIECEPLACED;
			}
			else{
				// Il giocatore non ha segnalini disponibili
				throw new PieceException();
			}
		}
		else{
			// Il giocatore e' nello stato sbagliato
			throw new InvalidActionException();
		}
	}
	
	@Override
	public void ready() {
	}

	@Override
	public void pass() throws InvalidActionException {
		if(!gamePhase.equals(Phase.TILEPLACED)){
			throw new InvalidActionException();
		}
	}

	
}
