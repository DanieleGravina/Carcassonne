package common;

import java.io.Serializable;

/**
 * E' l'evento che viene generato al termine di ogni turno per inviare i dati modificati all'interno del turno
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateFinalEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5978026087421468504L;
	private final UpdateFinalObject source;

	public UpdateFinalEvent(UpdateFinalObject source){
		this.source = source;
	}
	
	public UpdateFinalObject getSource(){
		return source;
	}

}