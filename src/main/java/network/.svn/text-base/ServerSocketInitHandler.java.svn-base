package network;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;

import common.Colors;

/**
 * E' la classe che gestisce la primissima fase di connessione di un client, in cui il client puo' scegliere
 * se collegarsi ad una nuova partita in avvio o ricollegarsi ad una partita gia' avviata
 * @author Daniele Iamartino
 *
 */
public class ServerSocketInitHandler implements Runnable{
	
	private SocketChannel socket;
	private Map<String,ServerSocket> games;
	private List<ServerSocket> serverList;
	
	public ServerSocketInitHandler(SocketChannel sock, Map<String,ServerSocket> games, List<ServerSocket> serverList){
		System.out.println("ServerSocketInitHandler: Avviato il costruttore");
		this.socket = sock;
		this.games = games;
		this.serverList = serverList;
	}
	
	@Override
	public void run() {
		System.out.println("ServerSocketInitHandler: Avviato il run()");
		ByteBuffer buffer = ByteBuffer.allocate(8192);
		int size;
		try {
			socket.socket().setKeepAlive(true);
		} catch (SocketException e1) {
			System.out.print("Cannot set keepalive option");
			e1.printStackTrace();
		}
		
		try {
			System.out.println("ServerSocketInitHandler: leggo sul socket del client");
			while ((size = socket.read(buffer)) != -1) {
				System.out.println("ServerSocketInitHandler: sono entrato nel while");
				buffer.flip();
				String received = new String(buffer.array(), 0, size);
				System.out.println("ServerSocketInitHandler: Ho ricevuto "+received.length()+" caratteri: \""+received.replace("\n", "")+"\" dal client");
				if(received.equals("connect\n")){
					System.out.println("ServerSocketInitHandler: Ricevuta una richiesta di connessione");
					serverList.get(serverList.size()-1).addClient(socket);
					break;
				}
				else if(received.contains("reconnect:")){ //reconnect: red,nomePartita
					System.out.println("ServerSocketInitHandler: Ricevuta una richiesta di riconnessione");
					String[] reconnTok = received.split("reconnect:");

					if(reconnTok.length == 2){
						System.out.println("Ricevuta una roba che ha dentro reconnect");
						reconnTok = reconnTok[1].replace(" ", "").replace("\n", "").split(",");
						if(reconnTok.length == 2){
							System.out.println("Due elementi dentro la reconnect");
							try{
								Colors playerColor = Colors.valueOf(reconnTok[0].toUpperCase());
								System.out.println("ServerSocketInitHandler: Richiesta di riconnessione di "+playerColor+" per "+reconnTok[1]);
								if(games.keySet().contains(reconnTok[1])){ //Il nome della partita e' presente nelle istanze
									System.out.println("Trovata la partita, ricollego il giocatore");
									games.get(reconnTok[1]).reconnect(socket, playerColor);
									break;
								}
								else{
									System.out.println("Il tentativo di riconnessione non e' andato a successo perche' non trovo la partita");
								}
							}
							catch(IllegalArgumentException e){
							}
						}
					}
				}
				buffer.clear();
			}
			buffer.clear();
		}
	    catch (IOException e) {
	    	System.out.println("ServerSocketInitHandler: errore di I/O sul socket");
		}
		System.out.println("ServerSocketInitHandler: terminato il thread");
	    //termina il thread
	}

}
