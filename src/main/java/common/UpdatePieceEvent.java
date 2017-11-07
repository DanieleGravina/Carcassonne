package common;

import java.io.Serializable;

/**
 * E' l'evento che viene generato in seguito all'aggiunta di un segnalino
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdatePieceEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4616010012894190149L;
	private final CoordinatesTile source;

	public UpdatePieceEvent(CoordinatesTile source){
		this.source = source;
	}
	
	public CoordinatesTile getSource(){
		return source;
	}

}