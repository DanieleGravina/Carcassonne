package common;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * E' l'oggetto che viene utilizzato per veicolare le informazioni di fine turno alla View
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateFinalObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5009727266071114663L;
	private final Set<CoordinatesTile> tiles;
	private final Map<Colors, Integer> points;
	private final Colors leftPlayer;
	
	public UpdateFinalObject(Set<CoordinatesTile> tiles, Map<Colors, Integer> points, Colors leftPlayer){
		this.tiles = tiles;
		this.points = points;
		this.leftPlayer = leftPlayer;
	}
	
	public Set<CoordinatesTile> getTiles() {
		return tiles;
	}
	
	public Map<Colors, Integer> getPoints() {
		return points;
	}
	
	public Colors getLeftPlayer(){
		return leftPlayer;
	}
	
}
