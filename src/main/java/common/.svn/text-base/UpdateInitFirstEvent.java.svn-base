package common;

import java.io.Serializable;

/**
 * E' l'evento inviato al primo turno della partita alla view per passare la prima tessera posizionata al centro
 * e la tessera pescata dal primo giocatore
 * @author Daniele Iamartino
 *
 */
public class UpdateInitFirstEvent implements Serializable{
	
	private static final long serialVersionUID = 4965572395207507911L;
	private final UpdateInitFirstObject source;

	public UpdateInitFirstEvent(UpdateInitFirstObject source){
		this.source = source;
	}
	
	public UpdateInitFirstObject getSource(){
		return source;
	}
}
