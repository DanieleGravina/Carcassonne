package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Inizializzatore del server socket. Riceve le connessioni dai clients che si collegano e genera
 * dei thread di ServerSocketInitHandler per ciascun nuovo connesso.
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ServerSocketInit {
	private Map<String, ServerSocket> games;
	private List<ServerSocket> serverList;
	private ServerSocket lastServer;
	private ServerSocketChannel serverSocket;
	
	public ServerSocketInit(){
		this(3389);
	}
	
	public ServerSocketInit(int port){
		games = new ConcurrentHashMap<String,ServerSocket>();
		serverList = new CopyOnWriteArrayList<ServerSocket>();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		try{
			serverSocket = ServerSocketChannel.open();
			serverSocket.socket().bind(new InetSocketAddress(port));
		}
		catch(IOException e){
			System.out.println("Error opening server socket");
			e.printStackTrace();
		}
		
		System.out.println("ServerSocketInit: Aperto e bindato");
		
		ServerSocketInitTimer timerThread = new ServerSocketInitTimer(games, lastServer, serverList);
		executor.submit(timerThread);
		
		System.out.println("ServerSocketInit: Inizio il ciclo di ascolto");
		while(true){
		    try {
				SocketChannel sock = serverSocket.accept();
				System.out.println("ServerSocketInit: Nuova connessione accettata");
				ServerSocketInitHandler handler = new ServerSocketInitHandler(sock, games, serverList);
				executor.submit(handler);
				
			} catch (IOException e) {
				System.out.print("Address already in use\n");
				break;
			}
		}
		
		
	}
	
	
	public static void main(String[] args){
		new ServerSocketInit();
	}

}

