package common;

/**
 * Indica che il numero di giocatori fornito non e' valido
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class NumberOfPlayerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1074405885344984203L;

	public NumberOfPlayerException() {
		super();
	}
	
	@Override
	public String toString(){
		return "Invalid number of players";
	}
}
