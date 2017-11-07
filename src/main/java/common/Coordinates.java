package common;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Definisce il tipo coordinate utilizzato per il posizionamento delle tessere
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class Coordinates implements Serializable{

	private static final long serialVersionUID = 2347448692619342428L;
	private int x;
	private int y;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return Il valore della coordinata X
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * 
	 * @return Il valore della coordinata Y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Invocata su una certa coordinata, restituisce una mappa che associa ad ogni direzione la coordinata adiacente.
	 * 
	 * @return Map<Direzione, Coordinate>
	 */
	public Map<Direction, Coordinates> getMapNearPositions(){
		Map<Direction, Coordinates> adiacenze = new HashMap<Direction, Coordinates>(Direction.values().length);
		
		adiacenze.put(Direction.NORTH, new Coordinates(this.getX(), this.getY()+1));
		adiacenze.put(Direction.SOUTH, new Coordinates(this.getX(), this.getY()-1));
		adiacenze.put(Direction.WEST, new Coordinates(this.getX()-1, this.getY()));
		adiacenze.put(Direction.EAST, new Coordinates(this.getX()+1, this.getY()));
		
		return adiacenze;
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
		Coordinates other = (Coordinates) obj;
		if (x != other.x){
			return false;
		}
		if (y != other.y){
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public String toString(){
		return getX() + "," + getY();
	}
}
