package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import gamebase.Game;
import model.ObservableModelImplementation;

/**
 * Classe che si occupa di creare il registry RMI e l'istanza del server RMI con il relativo model all'interno. 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ServerNetworkStarter {
	
	public static void main(String[] args){
		
		Registry registry;
		try {
			registry = LocateRegistry.createRegistry(1337);
			while(true){
				new ServerNetworkImpl(new ObservableModelImplementation(new Game()), registry);
			}
		} catch (RemoteException e) {
			System.out.print("Error creating registry\n");
		}
		
		
	}
	
}
