package controller;

import java.rmi.RemoteException;

import common.Colors;
import common.InvalidInputException;

public class NetworkClientController implements Controller{

	private Controller remoteController;
	private boolean enabled;
	
	public NetworkClientController(Controller remoteController){
		this.remoteController = remoteController;
	}
	
	@Override
	public void sendInitialInput(String string) throws RemoteException,	InvalidInputException {
		remoteController.sendInitialInput(string);
	}

	@Override
	public void sendInput(String string) throws RemoteException, InvalidInputException {
		if(enabled){
			remoteController.sendInput(string);
		}
		else{
			throw new InvalidInputException();
		}
		
	}

	@Override
	public void enable(boolean val) {
		this.enabled = val;
	}

	@Override
	public void delPlayerByColor(Colors color) throws RemoteException{
	}

	@Override
	public void reconnect() throws RemoteException{
		// Non implementato per RMI
	}
	
}
