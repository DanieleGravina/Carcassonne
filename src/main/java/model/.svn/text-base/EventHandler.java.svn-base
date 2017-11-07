package model;

import java.io.Serializable;

import common.Listener;

/**
 * Classe astratta per mandare agli osservatori(le view) un particolare evento.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public abstract class EventHandler implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6219908050328882186L;

	public abstract void action(Listener listener, Object obj);
	
	public void notify(Listener[] listOfListeners, Object obj){
		for(Listener listener : listOfListeners){
			action(listener, obj);
		}
	}

}
