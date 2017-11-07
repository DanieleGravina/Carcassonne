package gamebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.Colors;
import common.Coordinates;
import common.Direction;
import common.PieceException;

public class ConstructionAlgorithm implements Algorithm{
	
	private Map<Coordinates, Tile> tiles;
	private Set<CoordinatesDirection> setCoordinatedDirectionStructure;
	private Set<Coordinates> setCoordinatesStructure;
	private Coordinates coordTile;
	private Direction directionStructure;
	private List<Colors> listPiece;
	private Map<Coordinates, Tile> changedTiles;
	private boolean completeStructure;
	
	/**
	 * Costruttore della classe che si occupa di implementare l'algoritmo per controllare la completezza di una struttura di tipo citta'.
	 * 
	 * @param tilesGrid E' l'oggetto che contiene tutta la mappa di gioco
	 * @param coordTile E' la coordinata da cui iniziare l'analisi
	 * @param directionOfStructure E' la direzione da cui cominciare la ricerca
	 */
	public ConstructionAlgorithm(Map<Coordinates, Tile> tilesGrid, Coordinates coordTile, Direction directionOfStructure){
		this.tiles = tilesGrid;
		this.coordTile = coordTile;
		this.directionStructure = directionOfStructure;
		this.listPiece = new ArrayList<Colors>();
		this.setCoordinatedDirectionStructure = new HashSet<CoordinatesDirection>();
		
		this.setCoordinatesStructure = new HashSet<Coordinates>();
		this.changedTiles = new HashMap<Coordinates, Tile>();
		this.completeStructure = false;
	}
	
	/*
	 * Classe privata che serve per creare una lista privata di elementi unici coordinate-direzione 
	 * per controllare se e' gia stato fatto il passaggio della coppia tessera-coordinata nell'algoritmo
	 */
	private static class CoordinatesDirection{
		
		private Coordinates mycoord;
		private Direction mydirection;
		
		public CoordinatesDirection(Coordinates coord, Direction direction){
			this.mycoord = coord;
			this.mydirection = direction;
		}
		
		public Coordinates getCoordinates(){
			return mycoord;
		}
		
		public Direction getDirection(){
			return mydirection;
		}
		
		@Override
		public boolean equals(Object obj){
			if (this == obj){
				return true;
			}
			if (obj == null){
				return false;
			}
			if (getClass() != obj.getClass()){
				return false;
			}
			CoordinatesDirection other = (CoordinatesDirection) obj;
			if (!mycoord.equals(other.mycoord)){
				return false;
			}
			if (!mydirection.equals(other.mydirection)){
				return false;
			}
			return true;
		}
		
		@Override
		public int hashCode(){
			final int prime = 31;
			int result = 1;
			result = prime * result + mycoord.hashCode();
			result = prime * result + mydirection.hashCode();
			return result;
		}
		
		@Override
		public String toString(){
			return mycoord.getX()+","+mycoord.getY()+" "+mydirection.toString();
		}
	}

	@Override
	public boolean checkCompleteConstruction() {
        completeStructure = true;

        setCoordinatedDirectionStructure.add(new CoordinatesDirection(coordTile, directionStructure));
		for(Direction dir : tiles.get(coordTile).getConnectionByDirection(directionStructure)){
			setCoordinatedDirectionStructure.add(new CoordinatesDirection(coordTile, dir));
			if(!tiles.containsKey(coordTile.getMapNearPositions().get(dir)) || 
					!recursiveAlgorithm(coordTile.getMapNearPositions().get(dir), dir.getOpposite())){
				completeStructure = false;
			}
		}
		if(!tiles.containsKey(coordTile.getMapNearPositions().get(directionStructure)) || 
				!recursiveAlgorithm(coordTile.getMapNearPositions().get(directionStructure), directionStructure.getOpposite())){
			completeStructure = false;
		}

		for(CoordinatesDirection coordDir : setCoordinatedDirectionStructure){
			setCoordinatesStructure.add(coordDir.getCoordinates()); //serve per calcolare di quante diverse tessere e' composta la struttura
			
			Tile actualTile;
			actualTile = (Tile)tiles.get(coordDir.getCoordinates()).cloneTile();
			if(actualTile.hasPieceByDirection(coordDir.getDirection())){ //aggiungo pedine alla lista e aggiungo alla mappa le tessere cambiate
				try{
					listPiece.add(actualTile.getPieceByDirection(coordDir.getDirection()));
					actualTile.cancelPieceByDirection(coordDir.getDirection());
					changedTiles.put(coordDir.getCoordinates(), actualTile);
				}
				catch(PieceException e){
				}
			}

		}
		return completeStructure;
	}

	@Override
	public List<Colors> getListPieces() {
		
		return listPiece;
	}

	@Override
	public Map<Coordinates, Tile> getTilesChanged() {
		
		return changedTiles;
	}

	@Override
	public int numberOfTilesConstruction() {

		return setCoordinatesStructure.size();
	}

	@Override
	public int pointsForTile() {
		if(completeStructure){
			return tiles.get(coordTile).getCostructionByDirection(directionStructure).getScore();
		}
		else{
			return tiles.get(coordTile).getCostructionByDirection(directionStructure).getFinalScore();
		}
	}
	
	
	private boolean recursiveAlgorithm(Coordinates coordinates, Direction direction) {
		Tile actualTile = tiles.get(coordinates);
		CoordinatesDirection coordActualDirection = new CoordinatesDirection(coordinates, direction);
		
		
		if(setCoordinatedDirectionStructure.contains(coordActualDirection)){
			return true;
		}
		else{
			setCoordinatedDirectionStructure.add(new CoordinatesDirection(coordinates, direction));
			for(Direction dir : actualTile.getConnectionByDirection(direction)){
				if(!setCoordinatedDirectionStructure.contains(new CoordinatesDirection(coordinates, dir))){
					setCoordinatedDirectionStructure.add(new CoordinatesDirection(coordinates, dir));
					Coordinates coordNextTile = coordinates.getMapNearPositions().get(dir);
					if(!tiles.containsKey(coordNextTile) || 
							!recursiveAlgorithm(coordNextTile, dir.getOpposite())){
						return false;
					}
				}
			}
			return true;
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((setCoordinatesStructure == null) ? 0
						: setCoordinatesStructure.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		ConstructionAlgorithm other = (ConstructionAlgorithm) obj;
		if (setCoordinatesStructure == null) {
			if (other.setCoordinatesStructure != null){
				return false;
			}
		} else if (!setCoordinatesStructure
				.equals(other.setCoordinatesStructure)){
			return false;
		}
		return true;
	}
	
	

}