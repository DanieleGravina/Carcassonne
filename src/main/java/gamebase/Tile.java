package gamebase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import common.Colors;
import common.Construction;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;
import common.PieceException;

/**
 * Definisce tutti gli attributi associati ad una tessera (oggetto mutabile). Viene definita la struttura presente in ciascun punto cardinale
 * e l'eventuale presenza di una pedina posizionata.
 * 
 * 
 * Il costruttore prende come parametri una mappa che definisce le costruzioni presenti e una maschera di bit per definire le connessioni
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class Tile implements Cloneable{
	
	private Map<Direction, Cardinal> cardinals;
	private boolean hasPiece;
	private Direction pieceDirection;
	private Colors pieceColor;
	
	protected Object clone() throws CloneNotSupportedException {
		Tile clone = (Tile) super.clone();
		
		clone.cardinals = new HashMap<Direction, Cardinal>();
		for(Direction dir: cardinals.keySet()){
			clone.cardinals.put(dir, (Cardinal) cardinals.get(dir).clone());
		}
		
		// Sono tipi primitivi, li posso clonare in questo modo, no?
		clone.hasPiece = hasPiece;
		clone.pieceDirection = pieceDirection;
		clone.pieceColor = pieceColor;
		return clone;
	}
	
	/**
	 * Metodo per clonare l'oggetto mutabile della tessera in un altro
	 * @return La tessera clonata
	 */
	public Tile cloneTile(){
		try {
			return (Tile)this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public Tile(Map<Direction, Construction> construction,
				   Map<Direction, Set<Direction>> connections){
		
		hasPiece = false;
		pieceColor = null;
		pieceDirection = null;
		
		cardinals = new HashMap<Direction, Cardinal>(Direction.values().length);
		for(Direction dir: Direction.values()){
			cardinals.put(dir, new Cardinal(dir, construction.get(dir)));
		}
		
		for (Direction directionKey: connections.keySet()){
				cardinals.get(directionKey).setConnection(connections.get(directionKey));
		}
		
	}
	
	/**
	 * Peremtte di verificare se una tessera adiacente ha costruzioni compatibili.
	 * 
	 * @param tile
	 * @param dir Indica la posizione della seconda tessera rispetto alla prima. Se ad esempio tess è posizionata a nord, questo parametro sara' Direzione.NORD
	 * @return True se la costruzione e' compatibile
	 */
	public boolean hasConstructionCompatible(Tile tile, Direction dir){
		return this.getCostructionByDirection(dir).equals(tile.getCostructionByDirection(dir.getOpposite()));
	}
	
	/**
	 * Ritorna il Set di direzioni collegate alla direzione data come parametro sulla tessera in oggetto
	 * @param direction La direzione dove ci sono i collegamenti di nostro interesse
	 * @return Set<Direction> delle direzioni a cui e' collegata quella data come parametro
	 */
	public Set<Direction> getConnectionByDirection(Direction direction){
		return cardinals.get(direction).getConnections();
	}
	
	/**
	 * Posiziona una pedina all'interno della tessera
	 * @param direction Indica la direzione dove posizionare la pedina
	 * @param color Indica il colore della pedina posizionata
	 * @throws PieceException Indica che il posizionamento della pedina non e' valido
	 */
	public void setPiece(Direction direction, Colors color) throws PieceException {
		if(!hasPiece){ //Perché ci può essere solo una pedina per tessera
			pieceDirection = direction;
			pieceColor = color;
			hasPiece = true;
		}
		else{
			// Nel caso in cui la pedina è già posizionata viene lanciata una eccezione
			throw new PieceException();
		}
	}
	
	/**
	 * Data una direzione restituisce il colore del segnalino posizionato (se presente)
	 * @param direction Indica la direzione dove cercare il segnalino
	 * @return Un oggetto Color che indica il colore del segnalino
	 * @throws PieceException Viene lanciato se non e' presente nessuna pedina nella direzione data
	 */
	public Colors getPieceByDirection(Direction direction) throws PieceException {
		if(hasPieceByDirection(direction)){
			return pieceColor;
		}
		else{
			throw new PieceException();
		}
	}
	
	/**
	 * Indica se e' presente un segnalino nella direzione data
	 * @param direction La direzione dove cercare il segnalino
	 * @return True se e' presente un segnalino
	 */
	public boolean hasPieceByDirection(Direction direction){
		if(hasPiece && (pieceDirection.equals(direction))){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Cancella un segnalino da una data direzione
	 * @param direction La direzione dove prendere il segnalino
	 * @return Il colore del segnalino cancellato
	 * @throws PieceException Se non e' presente nessun segnalino nella direzione scelta
	 */
	public Colors cancelPieceByDirection(Direction direction) throws PieceException {
		if(hasPieceByDirection(direction)){
			hasPiece = false;
			return pieceColor;
		}
		else {
			throw new PieceException();
		}
	}
	
	/**
	 * Restituisce una costruzione in base alla direzione scelta
	 * @param direction La direzione scelta
	 * @return La costruzione presente in quella direzione
	 */
	public Construction getCostructionByDirection(Direction direction){
		return cardinals.get(direction).getConstruction();
	}
	
	/**
	 * Ruota l'oggetto tessera su cui viene invocato il metodo, nel verso indicato come parametro
	 */
	public void rotate(){
		Map<Direction, Cardinal> oldcardinali = new HashMap<Direction, Cardinal>(Direction.values().length);
		Map<Direction, Set<Direction>> oldconnections = new HashMap<Direction, Set<Direction>>();
		Map<Direction, Set<Direction>> newconnections = new HashMap<Direction, Set<Direction>>();
		
		for(Direction dir: Direction.values()){
			oldcardinali.put(dir, cardinals.get(dir));
			oldconnections.put(dir,cardinals.get(dir).getConnections());
		}
		
		for(Direction dir: oldconnections.keySet()){
			Set<Direction> temp = new HashSet<Direction>();
			for(Direction dir2: oldconnections.get(dir)){
				temp.add(dir2.next());
			}
			newconnections.put(dir.next(), temp);
		}
		
		for(Direction dir: Direction.values()){
			cardinals.put(dir, oldcardinali.get(dir.prev()));
			cardinals.get(dir).setConnection(newconnections.get(dir));
		}
	}
	
	/**
	 * Metodo per convertire l'oggetto Tile istanziato in un oggetto CoordinatesTile
	 * @param coord La coordinata da utilizzare per la conversione
	 * @return L'oggetto CoordinatesTile coorispondente
	 */
	public CoordinatesTile convertToCommonObject(Coordinates coord){

		Map<Direction, Set<Direction>> commonConnections = new HashMap<Direction, Set<Direction>>();
		Map<Direction, Construction> commonConstruction = new HashMap<Direction, Construction>();
		Map<Direction, Colors> commonPieces = new HashMap<Direction, Colors>();
		
		for(Direction dir : cardinals.keySet()){
			
			if(hasPiece && dir.equals(pieceDirection)){
				commonPieces.put(pieceDirection, pieceColor);
			}
			
			commonConstruction.put(dir, cardinals.get(dir).getConstruction());
			
			if(cardinals.get(dir).getConnections().isEmpty()){
				commonConnections.put(dir, new HashSet<Direction>());
			}
			else{
				commonConnections.put(dir, cardinals.get(dir).getConnections());
			}
		}
		
		return new CoordinatesTile(coord, commonConnections, commonConstruction, commonPieces);
		
	}
	
}

/**
 * Classe che rappresenta il punto cardinale all'interno della tessera
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
class Cardinal implements Cloneable{
	private Direction puntocardinale;
	private Construction construction;
	private Set<Direction> connections;
	
	protected Object clone() throws CloneNotSupportedException {
		Cardinal clone = (Cardinal) super.clone();
		
		clone.connections = new HashSet<Direction>();
		for(Direction dir: connections){
			clone.connections.add(dir);
		}
		// Sono tipi primitivi, posso clonarli cosi, no?
		clone.puntocardinale = puntocardinale;
		clone.construction = construction;
		return clone;
	}
	
	public Cardinal(Direction direction, Construction construction){
		this.connections = new HashSet<Direction>();
		this.puntocardinale = direction;
		this.construction = construction;
	}
	
	/**
	 * Ritorna le connessioni del punto cardinale
	 * @return
	 */
	public Set<Direction> getConnections(){
		return connections;
	}
	
	/**
	 *Per settare le connessioni del punto cardinale 
	 * @param setDirections
	 */
	public void setConnection(Set<Direction> setDirections){
		this.connections = setDirections;
	}
	
	/**
	 * Ritorna la direzione del punto cardinale
	 * @return
	 */
	public Direction getDirection(){
		return this.puntocardinale;
	}

	/**
	 * Ritorna che tipo di costruzione c'è nel punto cardinale
	 * @return
	 */
	public Construction getConstruction() {
		return this.construction;
	}
	
	/**
	 * Converte in stringa la costruzione associata al cardinale
	 */
	public String toString(){
		return getConstruction().toString();
	}
}

