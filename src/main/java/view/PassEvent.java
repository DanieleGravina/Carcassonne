package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import common.InvalidInputException;

import controller.Controller;
/**
 * Classe che implementa il listener dell'evento passo.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class PassEvent implements ActionListener {
	
	private Controller controller;
	
	public PassEvent(Controller controller){
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			controller.sendInput("pass");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InvalidInputException e) {
		}
	}

}
