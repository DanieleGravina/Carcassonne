package common;

import java.io.Serializable;

/**
 * Generico evento di errore che contiene la eccezione associata ad esso
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ErrorEvent implements Serializable{

	private static final long serialVersionUID = -52247933667063848L;
	private final Exception source;

	public ErrorEvent(Exception source) {
		this.source = source;
	}
	
	public Exception getSource(){
		return source;
	}

}
