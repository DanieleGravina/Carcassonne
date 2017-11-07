package network;

import java.nio.channels.SocketChannel;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Colors;
import common.InvalidInputException;

import controller.Controller;
import controller.ImplementedController;
import gamebase.Game;
import model.ObservableModelImplementation;

/**
 * Classe server socket che si occupa della gestione di una singola istanza di partita.
 * Si occupa di istanziare il model, il controller, aggiunge i giocatori che si collegano
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ServerSocket implements Runnable {
	
	private ObservableModelImplementation gameModel;
	private Controller gameController;
	private List<SocketChannel> clients = new CopyOnWriteArrayList<SocketChannel>();
	private List<ServerViewSocket> serverViews = new CopyOnWriteArrayList<ServerViewSocket>();
	
	public ServerSocket(){
		gameModel = new ObservableModelImplementation(new Game());
		gameController = new ImplementedController(gameModel);
	}
	
	/**
	 * Aggiunge il socket di un client collegato all'interno di una lista di clients
	 * @param client Socket del nuovo client collegato
	 */
	synchronized public void addClient(SocketChannel client){
		if(clients.size() <= 5){
			System.out.println("ServerSocket: Aggiunto un client");
			clients.add(client);
			serverViews.add(new ServerViewSocket(client, gameController, gameModel, serverViews));
		}
	}
	
	/**
	 * Funzione che dato un socket di un client e un colore, avvisa tutte le ServerViewSocket del nuovo riconnesso
	 * @param socket Il socket del client riconnesso
	 * @param color Il colore dle client riconnesso
	 */
	public void reconnect(SocketChannel socket, Colors color){
		for(ServerViewSocket client: serverViews){
			client.reconnect(color, socket);
		}
	}
	
	/**
	 * Funzione che stabilisce se il numero di client collegati alla partita attualmente avviata e' sufficiente o no.
	 * Se si collegano troppi client ritorna false
	 * @return True se il numero di client collegati e' valido
	 */
	public boolean isEnoughNumberOfClient(){
		return (clients.size() >= 2 && clients.size() <= 5);
	}
	
	/**
	 * Funzione che imposta il nome della partita
	 * @param name Nome della partita da impostare
	 */
	public void setName(String name){
		gameModel.setGameName(name);
	}
	
	@Override
	public void run() {
		System.out.println("ServerSocket: Avviato il run() di ServerSocket");
		ExecutorService executor = Executors.newCachedThreadPool();
		for(ServerViewSocket client: serverViews){
			System.out.println("ServerSocket: Avviato un thread di serverViewSocket");
			executor.submit(client);
		}

		try {
			gameController.sendInitialInput(Integer.valueOf(clients.size()).toString());
		} 
		catch (RemoteException e) {
		} 
		catch (InvalidInputException e) {
		}
	}

}
