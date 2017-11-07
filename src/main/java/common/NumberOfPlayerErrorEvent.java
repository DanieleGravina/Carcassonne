package common;

import java.io.Serializable;


/**
 * Evento di errore che indica un numero non corretto di giocatori richiesto
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class NumberOfPlayerErrorEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3125926959223460322L;
	private final Exception source;

	public NumberOfPlayerErrorEvent(Exception source){
		this.source = source;
	}
	
	public Exception getSource(){
		return source;
	}
}
