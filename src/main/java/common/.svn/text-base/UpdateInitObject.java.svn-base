package common;

import java.io.Serializable;

/**
 * E' l'oggetto che viene inviato all'inizio del turno alla View per fornirle le informazioni iniziali.
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateInitObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4607505135254249214L;
	private final CoordinatesTile coordTile;
	private final Colors playerColor;
	private final String gameName;

	public UpdateInitObject(CoordinatesTile coordTile, Colors playerColor, String gameName){
		this.coordTile = coordTile;
		this.playerColor = playerColor;
		this.gameName = gameName;
	}
	
	public String getGameName() {
		return gameName;
	}

	public CoordinatesTile getCoordTile() {
		return coordTile;
	}
	
	public Colors getPlayerColor() {
		return playerColor;
	}
	
}
