package common;


/**
 * Indica che la direzione scelta non e' valida
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class InvalidDirectionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3015612107323428082L;

	public InvalidDirectionException(){
		super();
	}
	
	public String toString(){
		return "Invalid direction";
	}
}
