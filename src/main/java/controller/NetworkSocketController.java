package controller;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.rmi.RemoteException;

import network.ClientSocket;

import common.Direction;
import common.InvalidInputException;

public class NetworkSocketController extends ImplementedController{
	
	private boolean enabled;
	private SocketChannel socket;
	private ClientSocket clienthandler;
	
	public NetworkSocketController(SocketChannel socket, ClientSocket clienthandler){
		super(null);
		this.socket = socket;
		this.enabled = true;
		this.clienthandler = clienthandler;
	}

	@Override
	public void sendInitialInput(String string) throws RemoteException,
			InvalidInputException {
		// Non abilitato nel gioco via rete
	}	
	
	@Override
	protected void rotateTile(){
		send("rotate\n");
	}
	
	/**
	 * metodo per aggiungere una tessera al tabellone
	 */
	@Override
	protected void addTile(int x, int y) {
		send("place:"+x+","+y+"\n");
	}
	
	/**
	 * metodo per aggiungere una pedina a un lato della tessera
	 */
	@Override
	protected void addPiece(Direction dir) {
		send("tile:"+String.valueOf(dir.toChar()).toUpperCase()+"\n");
	}
	
	/**
	 * Metodo per fare passo
	 */
	@Override
	protected void pass(){
		send("pass\n");
	}

	@Override
	public void enable(boolean val) {
		this.enabled = val;
	}	
	
	private void send(String string){
		if(enabled){
		    ByteBuffer header = ByteBuffer.wrap(string.getBytes());
		    try {
				socket.write(header);
			} catch (IOException e) {
				System.out.print("Write error: Reconnecting\n");
				reconnect();
			}
		}
	}
	
	@Override
	public void reconnect(){
		clienthandler.reconnect();
	}
	
	public void setsocket(SocketChannel socket){
		this.socket = socket;
	}
	
}
