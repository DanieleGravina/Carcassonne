package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import common.InvalidInputException;


import controller.Controller;

/**
 * Classe per mandare al controller le coordinate
 *  nelle quali il giocatore vuole mettere la tessera
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class AddTileEvent implements ActionListener{
	
	private int x;
	private int y;
	private Controller controller;
	
	public AddTileEvent(int x, int y, Controller controller){
		this.x = x;
		this.y = y;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			controller.sendInput("add tile "+x+" "+y);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (InvalidInputException e1) {
		}
	}

}
