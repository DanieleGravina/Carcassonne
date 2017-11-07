package common;

import java.io.Serializable;
import java.util.Map;

/**
 * E' l'evento generato al termine della partita
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class UpdateGameFinishedEvent implements Serializable {

	private static final long serialVersionUID = -3000017726813465878L;
	private final Map<Colors,Integer> source;

	public UpdateGameFinishedEvent(Map<Colors,Integer> source) {
		this.source = source;
	}
	
	public Map<Colors,Integer> getSource(){
		return source;
	}

}