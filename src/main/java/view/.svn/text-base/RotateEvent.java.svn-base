package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import common.InvalidInputException;

import controller.Controller;

public class RotateEvent implements ActionListener{
	
	private Controller controller;
	
	public RotateEvent(Controller controller){
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			controller.sendInput("rotate");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InvalidInputException e) {
		}
	}

}
