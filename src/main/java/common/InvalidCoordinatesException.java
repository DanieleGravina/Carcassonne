package common;


/**
 * Indica che le coordinate scelte non sono valide.
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class InvalidCoordinatesException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8302464574323387103L;

	public InvalidCoordinatesException(){
		super();
	}
	
	public String toString(){
		return "Invalid coordinates";
	}
}
