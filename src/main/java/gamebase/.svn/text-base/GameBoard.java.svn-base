package gamebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import common.Colors;
import common.Construction;
import common.Coordinates;
import common.Direction;
import common.InvalidCoordinatesException;
import common.InvalidInputException;
import common.PieceException;
import common.PositioningNotCompatibleException;

/**
 * Contiene tutti i dati utilizzati dalla logica di gioco. 
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class GameBoard {
	private Map<Coordinates, Tile> tilePositioned;
	private Set<Coordinates> freePositions;
	private Map<Coordinates,Tile> tilesChanged;
	
	/**
	 * Algoritmo che verifica tutti i posizionamenti possibili per la tessera fornita in ingresso
	 * in tutte le posizioni libere della tabella
	 * @param tile La tessera di cui si vuole verificare il posizionamento
	 * @return True se esiste almeno un posizionamento valido all'interno della mappa per la tessera data
	 */
	public boolean checkValidPositions(Tile tile){
		Tile actualTile = tile.cloneTile();
		boolean isvalid = true;
		
		for (int i=0; i<Direction.values().length; i++){ // Provo tutte le rotazioni possibili della tessera
			for(Coordinates c : freePositions){ // Guardo tutte le posizioni libere
				Map<Direction, Coordinates> adiacenze = c.getMapNearPositions(); // Prendo le coordinate delle caselle adiacenti alla posizione libera per ogni direzione
				isvalid = true;
				
				for(Direction dir : Direction.values()){ // Guardo per ogni direzione adiacente
					Coordinates coordinataAdiacenza = adiacenze.get(dir); // Prendo la coordinata della casella adiacente scelta
					
					if(tilePositioned.containsKey(coordinataAdiacenza) && // Se nella posizione scelta c'e' una tessera gia' posizionata
						!actualTile.hasConstructionCompatible(tilePositioned.get(coordinataAdiacenza), dir)){ // Se la tessera posizionata adiacente non ha costruzioni compatibili con questa che ho
						
						isvalid = false; // Per questa rotazione c'e' almeno una tessera adiacente che non ha una costruzione compatibile
					}
				}
				if(isvalid){
					return true;
				}
			}
			actualTile.rotate();
		}
		
		return false; //non ho trovato neanche una corrispondenza
	}
	/*
	public Map<Coordinates, Set<Integer>> getValidPositions(Tile tile, boolean fast){
		Tile actualTile = tile.cloneTile();
		Map<Coordinates, Set<Integer>> to_return = new HashMap<Coordinates, Set<Integer>>();
		for(int i=0; i<4; i++){
			for(Coordinates coord: freePositions){
				Map<Direction, Coordinates> adiacenze = coord.getMapNearPositions();
				for(Direction dir: Direction.values()){
					Coordinates coordinata_adiacenza = adiacenze.get(dir);
					if(tilePositioned.containsKey(coordinata_adiacenza)){
						if(actualTile.hasConstructionCompatible(tilePositioned.get(coordinata_adiacenza), dir)){
							
						}
						else{
							if (fast){
								
							}
						}
					}
					else{
						
					}
				}
			}
		}
	}
	*/
	
	/**
	 * @return Un set di coordinate dove e' possibile posizionare delle tessere
	 */
	public Set<Coordinates> getFreePositions(){
		return freePositions;
	}
	
	/**
	 * Metodo per ottenere l'oggetto Tile di una tessera posizionata a partire da una coordinata
	 * @param coord Coordinata di ricerca
	 * @return Oggetto Tile
	 * @throws InvalidCoordinatesException Lanciata se la coordinata non e' associata a nessuna tessera posizionata
	 */
	public Tile getTileByCoordinates(Coordinates coord) throws InvalidCoordinatesException{
		if(tilePositioned.containsKey(coord)){
			return tilePositioned.get(coord);
		}
		else{
			throw new InvalidCoordinatesException();
		}
	}
	
	/**
	 * Metodo per ricercare le coordinate associate ad un oggetto Tile posizionato
	 * @param tile La Tile posizionata
	 * @return Le coordinate dove si trova la tessera
	 * @throws InvalidInputException Se la tessera fornita in ingresso non esiste tra le posizionate
	 */
	public Coordinates getCoordinatesFromTile(Tile tile) throws InvalidInputException {
		for (Coordinates coord: tilePositioned.keySet()){
			if(tilePositioned.get(coord).equals(tile)){
				return coord;
			}
		}
		throw new InvalidInputException();
	}
	
	/**
	 * Calcola gli spazi liberi che compongono i "bordi" della mappa, utilizzati per il disegno dalle varie view
	 * @return Ritorna una Mappa <Direzione,Coordinate> dove c'è associata per ogni direzione la coordinata di una posizione libera di bordo. 
	 */
	public Map<Direction,Coordinates> getCoordinatesMargin(){
		Map<Direction,Coordinates> resultMap = new HashMap<Direction, Coordinates>();
		
		for(Direction d: Direction.values()){
			resultMap.put(d, new Coordinates(0, 0));
		}
		
		for(Coordinates coordinata: freePositions){			
			if(coordinata.getX() >= resultMap.get(Direction.EAST).getX()){ resultMap.put(Direction.EAST, coordinata); }
			if(coordinata.getX() <= resultMap.get(Direction.WEST).getX()){ resultMap.put(Direction.WEST, coordinata); }
			if(coordinata.getY() >= resultMap.get(Direction.NORTH).getY()){	resultMap.put(Direction.NORTH, coordinata); }
			if(coordinata.getY() <= resultMap.get(Direction.SOUTH).getY()){ resultMap.put(Direction.SOUTH, coordinata); }
		}
		
		return resultMap;
	}
	
	/**
	 * Aggiunge la tessera all'interno della griglia di gioco alle coordinate scelte, effettuando un controllo di posizionamento
	 * @param coord Le coordinate dove posizionare la tessera
	 * @param tess La tessera da posizionare
	 * @throws InvalidCoordinatesException Indica che le coordinate fornite non sono valide (tipicamente non sono tra quelle libere)
	 * @throws PositioningNotCompatibleException Indica che il posizionamento scelto ha coordinate valide ma le costruzioni non sono compatibili
	 */
	public void addTile(Coordinates coord, Tile tess) throws InvalidCoordinatesException, PositioningNotCompatibleException{
		if((freePositions.size() > 0) && (!freePositions.contains(coord))){
			throw new InvalidCoordinatesException();
		}
		
		/* 
		 * controllo che la tessera sia compatabile con il tabellone, non ha senso chiamare CheckValidPosition perche' la rotazione e' stata data dal giocatore.
		 */
		Map<Direction, Coordinates> adiacenze = coord.getMapNearPositions();
		for(Direction dir : Direction.values())
		{
			if(tilePositioned.containsKey(adiacenze.get(dir))){
				Coordinates coordinataAdiacenza = adiacenze.get(dir); // Prendo la coordinata della casella adiacente scelta
				if(!tess.hasConstructionCompatible(tilePositioned.get(coordinataAdiacenza), dir)){
					throw new PositioningNotCompatibleException();
				}
			}
		}
		
        freePositions.remove(coord); /* Cancella la coordinata dalla lista di quelle libere */
		tilePositioned.put(coord, tess);
		for(Direction dir : Direction.values())
		{
			Coordinates coordinataAdiacenza = adiacenze.get(dir);
			if(!tilePositioned.containsKey(coordinataAdiacenza)){
				freePositions.add(coordinataAdiacenza);
			}
		}
	}
	
	/**
	 * Aggiunge un segnalino all'interno di una tessera
	 * @param actualTile La tessera su cui posizionare il segnalino
	 * @param coord La coordinata della tessera
	 * @param playerColor Il colore del segnalino da posizionare
	 * @param dir La direzione dove posizionare il segnalino all'interno della tessera
	 * @throws PieceException Indica un errore di posizionamento del segnalino in un campo o di segnalini gia' presenti nella struttura associata
	 */
	public void addPiece(Tile actualTile, Coordinates coord, Colors playerColor, Direction dir) throws PieceException{
		if(!actualTile.getCostructionByDirection(dir).equals(Construction.FIELD)){
			//Verifico che la costruzione associata non contenga gia pedine
			ConstructionAlgorithm algo = new ConstructionAlgorithm(tilePositioned, coord, dir);
			algo.checkCompleteConstruction();
			if (algo.getListPieces().size() == 0){
				// inserisci la pedina
				actualTile.setPiece(dir, playerColor);
			}
			else{
				throw new PieceException();
			}
		}
		else{
			// Non puoi posizionare una pedina in un campo.
			throw new PieceException();
		}
	}
	
	/**
	 * Effettua il controllo di completezza delle strutture lanciando gli algoritmi necessari
	 * @param coord Le coordinate da cui avviare l'algoritmo
	 * @param successfulOnly Se True indica di ritornare come risultato solo gli algoritmi che hanno trovato strutture complete
	 * @return Un set di algoritmi
	 */
	public Set<Algorithm> checkCompleteConstruction(Coordinates coord, boolean successfulOnly){
		
		Tile actualTile = tilePositioned.get(coord);
		Set<Algorithm> setOfAlgorithm = new HashSet<Algorithm>();
		Set<Direction> directionAvaible = new HashSet<Direction>();
		
		for(Direction dir : Direction.values()){
			directionAvaible.add(dir);
		}
		
		for(Direction dir : Direction.values()){
			if(directionAvaible.contains(dir)){
				for(Direction direction : actualTile.getConnectionByDirection(dir)){
					directionAvaible.remove(direction);
				}
				Algorithm algorithm = new ConstructionAlgorithm(tilePositioned, coord, dir);
				if(algorithm.checkCompleteConstruction() || !successfulOnly){
					setOfAlgorithm.add(algorithm);
				}
			}
		}
		
		return setOfAlgorithm;
	}
	
	/**
	 * Effettua il lancio degli algoritmi per la completezza delle strutture.
	 * @param coord Le coordinate di ricerca
	 * @return Gli algoritmi che hanno trovato strutture complete
	 */
	public Set<Algorithm> checkCompleteConstruction(Coordinates coord){
		return checkCompleteConstruction(coord, true);
	}
	
	/**
	 * Lancia l'algoritmo ricerca strutture complete su tutte le tessere posizionate nel gioco
	 * @return Il set di algoritmi (anche quelli che non hanno trovato strutture complete)
	 */
	public Set<Algorithm> getAllStructures(){
		Set<Algorithm> allAlgorithms = new HashSet<Algorithm>();
		for(Coordinates coord : tilePositioned.keySet()){
			for(Algorithm algo : checkCompleteConstruction(coord, false)){
				allAlgorithms.add(algo);
			}
		}
		return allAlgorithms;
	}
	
	
	
	public GameBoard(Tile firstTile){
		tilePositioned = new HashMap<Coordinates, Tile>();
		freePositions = new HashSet<Coordinates>();
		
		try{
			addTile(new Coordinates(0, 0), firstTile);
		}
		catch(InvalidCoordinatesException e){
		}
		catch(Exception e){
		}
		//TODO
	}
	
	/**
	 * Funzione che si occupa di rimuovere i segnalini di un certo giocatore (che e' stato rimosso dalla partita)
	 * @param col Il colore del giocatore di cui si vogliono rimuovere i segnalini
	 */
	public void removePiecesOfPlayer(Colors col) {
		tilesChanged = new HashMap<Coordinates,Tile>();
		for(Coordinates coord: tilePositioned.keySet()){
			Tile tile = tilePositioned.get(coord);
			for(Direction dir: Direction.values()){
				if(tile.hasPieceByDirection(dir)){
					try {
						Colors pieceColor = tile.getPieceByDirection(dir);
						if(pieceColor.equals(col)){
							tile.cancelPieceByDirection(dir);
							tilesChanged.put(coord, tile);
						}
					} 
					catch (PieceException e) {
					}
				}
			}
		}
	}
	
	/**
	 * Funzione per ottenere le tessere modificate dopo la rimozione dei segnalini di un certo giocatore
	 * @return Map<Coordinates,Tile> Le tessere modificate
	 */
	public Map<Coordinates,Tile> getChangedTiles(){
		return tilesChanged;
	}
	
}
