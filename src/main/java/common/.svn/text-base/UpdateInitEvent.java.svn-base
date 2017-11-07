package common;

import java.io.Serializable;

/**
 * E' l'evento di update che viene inviato all'inizio del turno alla view per comunicare le informazioni relative al turno
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateInitEvent implements Serializable{

	private static final long serialVersionUID = -1564240505685681748L;
	private final UpdateInitObject source;

	public UpdateInitEvent(UpdateInitObject source){
		this.source = source;
	}
	
	public UpdateInitObject getSource(){
		return source;
	}

}