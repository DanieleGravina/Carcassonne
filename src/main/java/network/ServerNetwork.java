package network;

import java.rmi.Remote;	
import java.rmi.RemoteException;	

import common.ReverseController;


public interface ServerNetwork extends Remote{
	
	void connect(ReverseController client) throws RemoteException;
	
}

