package network;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import common.InvalidInputException;
import common.Listener;
import common.ReverseController;
import controller.Controller;
import controller.ImplementedController;
import model.ObservableModelImplementation;

/**
 * Implementazione RMI del server
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class ServerNetworkImpl implements ServerNetwork {
	
	private ObservableModelImplementation gameModel;
	private Controller controller;
	private List<ReverseController> clients = new CopyOnWriteArrayList<ReverseController>();
	
	public ServerNetworkImpl(ObservableModelImplementation gameModel, Registry registry){
		
		this.gameModel = gameModel;
		controller = new ImplementedController(gameModel);
		
		 try {
			// esporto il server e creo lo stub
			Controller serverController = (Controller) UnicastRemoteObject.exportObject(controller, 0);
			ServerNetwork thisServer = (ServerNetwork) UnicastRemoteObject.exportObject(this, 0);
			// esporto lo stub del server nel registry
			registry.rebind("CarcassonneServer", thisServer);
			registry.rebind("CarcassonneServerController", serverController);
			
			System.out.println("Server ready");
		} catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		}
		while(clients.size() < 2){
			System.out.print("Waiting for players...\n");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				System.out.print("Interrupted\n");
			}
		}
		
		try {
			System.out.print("Starting new game with "+Integer.valueOf(clients.size())+" players");
			controller.sendInitialInput(Integer.valueOf(clients.size()).toString());
		} 
		catch (InvalidInputException e) {
			System.out.print("Invalid Input");
		}
		catch (RemoteException e) {
			System.out.print("Remote Exception");
		}

		 
	}
	
	/**
	 * Funzione che accetta connessioni da clients remoti e riceve i relativi reverseController per l'inivio delle risposte
	 */
	@Override
	public void connect(ReverseController client) throws RemoteException{
		clients.add(client);
		Listener listener = new ServerViewRMI(client);
		System.out.println("Aggiungo un listener");
		gameModel.addListener(listener);
	}

}