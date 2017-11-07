package common;

import java.io.Serializable;
import java.util.List;

/**
 * E' l'oggetto che viene passato nel primo turno della prima partita
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateInitFirstObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1356583913559904962L;
	private final CoordinatesTile firstTile;
	private final UpdateInitObject initObject;
	private final List<Colors> playerList;
	private final Colors yourColor;

	public UpdateInitFirstObject(CoordinatesTile firstTile, List<Colors> players, Colors yourColor, UpdateInitObject initObject){
		this.firstTile = firstTile;
		this.initObject = initObject;
		this.playerList = players;
		this.yourColor = yourColor;
	}

	public List<Colors> getPlayerList() {
		return playerList;
	}
	
	public CoordinatesTile getFirstTile() {
		return firstTile;
	}

	public UpdateInitObject getInitObject() {
		return initObject;
	}
	
	public Colors getYourColor(){
		return yourColor;
	}

}
