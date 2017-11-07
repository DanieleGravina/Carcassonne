package network;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.ObservableModel;
import common.ReverseController;
import common.ReverseControllerImpl;


import view.BasicView;
import view.ObserverView;
import view.SwingView;
import view.View;
import controller.Controller;
import controller.NetworkClientController;

/**
 * Classe che implementa ClientNetwork con RMI;
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class ClientNetworkImpl implements ClientNetwork,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1529290480609122070L;
	private transient View gameView;
	
	public ClientNetworkImpl(String registryAddress,int port, boolean useGUI){
    	Controller gameController;
    	
        try {
        	// prendo un riferimento al registry
            Registry registry = LocateRegistry.getRegistry(registryAddress, port);

            ServerNetwork serverStub = (ServerNetwork) registry.lookup("CarcassonneServer");
            
            gameController = new NetworkClientController((Controller) registry.lookup("CarcassonneServerController"));
			// ClientModel
			ObservableModel clientModel = new ClientModel();
			
			// View
			if(useGUI){
			gameView = new ObserverView(new SwingView(gameController), clientModel);
			}
			else{
				gameView = new ObserverView(new BasicView(gameController), clientModel);
			}
			
			//Reverse Controller
			ReverseController reverseController = new ReverseControllerImpl((ClientModel) clientModel);
			
			ReverseController clientStub = (ReverseController) UnicastRemoteObject.exportObject(reverseController,0);
			
			serverStub.connect(clientStub);
			
			gameView.start();
			
        } 
        catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
	}
	
}
