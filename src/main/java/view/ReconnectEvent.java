package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import controller.Controller;
/**
 *Evento per effettuare la reconnect in caso di perdita di connesione con il server
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class ReconnectEvent implements ActionListener {

	private Controller controller;

	/**
	 * Il costruttore con parametro il controller
	 * @param controller
	 */
	public ReconnectEvent(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			controller.reconnect();
		} catch (RemoteException e) {
		}

	}

}
