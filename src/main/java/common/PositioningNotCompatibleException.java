package common;


/**
 * Indica che il posizionamento di una tessera non e' valido all'interno della mappa.
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class PositioningNotCompatibleException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2072146090030513475L;

	public PositioningNotCompatibleException(){
		super();
	}
	
	public String toString(){
		return "Positioning not compatible";
	}
}
