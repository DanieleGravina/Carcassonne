package common;

import java.io.Serializable;

/**
 * E' l'evento che viene generato in risposta all'azione di rotazione di una tessera
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateRotationEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3904538059459161654L;
	private final CoordinatesTile source;

	public UpdateRotationEvent(CoordinatesTile source){
		this.source = source;
	}
	
	public CoordinatesTile getSource(){
		return source;
	}

}