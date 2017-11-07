package common;

/**
 * Indica un errore relativo al segnalino
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class PieceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4744046983215712620L;

	public PieceException(){
		super();
	}
	
	public String toString(){
		return "Piece error";
	}
}
