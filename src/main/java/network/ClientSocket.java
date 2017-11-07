package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import view.BasicView;
import view.ObserverView;
import view.SwingView;
import view.View;

import common.ObservableModel;
import common.SocketReverseController;

import controller.Controller;
import controller.NetworkSocketController;

/**
 * Classe client che si occupa di insturare la connessione con il server socket e
 * di inizializzare il controller di rete, il reverse controller per ricevere  i dati dal socket
 * e la view.
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ClientSocket {
	private SocketChannel socket;
	private Controller controller;
	private String host;
	private int port;
	private SocketReverseController reverseController;
	
	public ClientSocket(String host, int port, boolean useGUI){
		ExecutorService executor = Executors.newCachedThreadPool();
		
		this.host = host;
		this.port = port;
		
		try{
			socket = SocketChannel.open();
			try{
		    	socket.connect(new InetSocketAddress(host, port));
		    	socket.socket().setKeepAlive(true);
		    	send("connect\n");
		    	controller = new NetworkSocketController(socket, this);
		    	
		    	
		    	// ClientModel
				ObservableModel clientModel = new ClientModel();
				
				// View
				View gameView;
				if(useGUI){
					gameView = new ObserverView(new SwingView(controller), clientModel);
				}
				else{
					gameView = new ObserverView(new BasicView(controller), clientModel);
				}
				
				//Reverse Controller
				reverseController = new SocketReverseController((ClientModel) clientModel, socket,(NetworkSocketController) controller);
				
				executor.submit(reverseController);
				
				gameView.start();

		    }
		    catch(IOException e){
		    	System.out.println("La morte sopraggiunse");
		    	e.printStackTrace();
		    }
		    /*
		    ByteBuffer buffer = ByteBuffer.allocate(8192);
		    while (socket.read(buffer) != -1) {
				buffer.flip();
				System.out.println("Arrivata robaccia sul socket");
				buffer.clear();
			}
			*/
		    System.out.println("Sono uscito dal while");
		}
	    catch(IOException e){
	    	System.out.println("Connection error");
	    }
	}
	
	/**
	 * Funzione di riconnessione del socket. Chiude il socket attualmente aperto e ne crea uno nuovo, che
	 * passa al reverseController per permettergli di ricevere i nuovi dati
	 */
	public void reconnect(){
		try{
			socket.close();
		}
		catch(IOException e){
		}
		
		try{
			socket = SocketChannel.open();
	    	socket.connect(new InetSocketAddress(host, port));
	    	socket.socket().setKeepAlive(true);
	    	reverseController.reconnect(socket);
	    	
		}
    	catch(IOException e){
    		// Che si fa?
    	}
	}
	
	/**
	 * Invia una stringa all'interno del socket aperto
	 * @param string La stringa da inviare
	 * @throws IOException In caso di errore nella scrittura sul socket
	 */
	private void send(String string) throws IOException{
	    ByteBuffer header = ByteBuffer.wrap(string.getBytes());
		socket.write(header);
	}
	
	public static void main(String[] args){
		new ClientSocket("127.0.0.1",3389, false);
	}
}
