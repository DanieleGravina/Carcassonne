package network;

import java.io.Serializable;
import common.Listener;

/**
 * Una interfaccia per una "View" finta, utilizzata nel server per intercettare l'invio di eventi alla view e inoltrarli poi alla view "vera"
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public interface ServerView extends Listener, Serializable{

}
