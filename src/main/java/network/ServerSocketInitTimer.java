package network;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe thread che gestisce il timer per le finestre temporali di avvio delle partite
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ServerSocketInitTimer implements Runnable {
	
	private Map<String,ServerSocket> games;
	private List<ServerSocket> serverList;
	private int numGame;
	
	public ServerSocketInitTimer(Map<String,ServerSocket> games, ServerSocket lastServer, List<ServerSocket> serverList){
		this.games = games;
		this.numGame = 0;
		this.serverList = serverList;
		
		createNewServer();
	}
	
	/**
	 * Funzione che crea un nuovo ServerSocket e lo aggiunge alla lista delle istanze di ServerSocket avviate
	 */
	public void createNewServer(){
		ServerSocket server = new ServerSocket();
		server.setName("Game"+numGame);
		games.put("Game"+numGame, server);
		serverList.add(server);
		numGame += 1;
	}

	@Override
	public void run() {
		ExecutorService executor = Executors.newCachedThreadPool();
		while(true){
			//System.out.println("Timer: timer avviato");
			//TODO: gestire timer in modo intelligente
			try {
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) {
				
			}
			//System.out.println("Timer: timer scattato");
			ServerSocket lastServer = serverList.get(serverList.size()-1);
			if(lastServer.isEnoughNumberOfClient()){
				//System.out.println("Timer: Ci sono abbastanza clients, lancio un nuovo server");
				executor.submit(lastServer);
				createNewServer();
			}
		}
	}
	

}
