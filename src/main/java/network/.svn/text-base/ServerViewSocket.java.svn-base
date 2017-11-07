package network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.Colors;
import common.CoordinatesTile;
import common.Direction;
import common.ErrorEvent;
import common.UpdateGameFinishedEvent;
import common.InvalidDirectionException;
import common.InvalidInputException;
import common.NumberOfPlayerErrorEvent;
import common.ObservableModel;
import common.UpdateFinalEvent;
import common.UpdateFinalObject;
import common.UpdateInitEvent;
import common.UpdateInitFirstEvent;
import common.UpdateInitFirstObject;
import common.UpdatePieceEvent;
import common.UpdateRotationEvent;
import common.UpdateTileEvent;
import controller.Controller;

public class ServerViewSocket implements ServerView,Runnable{

	private static final long serialVersionUID = -2039125671291940500L;
	private SocketChannel socket;
	private Colors myColor;
	private Controller controller;
	private boolean hasColor;
	private boolean connected;
	private List<ServerViewSocket> listViews;

	public ServerViewSocket(SocketChannel socket, Controller controller, ObservableModel model, List<ServerViewSocket> listViews){
		System.out.println("ServerViewSocket: istanziato");
		this.socket = socket;
		this.controller = controller;
		this.listViews = listViews;
		this.hasColor = false;
		this.connected = true;
		
		model.addListener(this);
	}
	
	/**
	 * Funzione che gestisce la riconnessione di un client a lato server.
	 * Si occupa di aggiornare i dati relativi al socket nella ServerView
	 * @param color Il colore del client ricollegato
	 * @param socket Il nuovo socket del client ricollegato
	 */
	public void reconnect(Colors color, SocketChannel socket){
		if(hasColor && color.equals(myColor)){
			this.socket = socket;
			this.connected = true;
		}
	}
	
	public void lock(){
		send("lock\n");
	}
	
	public void unlock(){
		send("unlock\n");
	}
	
	public void sendToAll(String s){
		for(ServerViewSocket serverView: this.listViews){
			if(!(serverView.equals(this))){ //Mandiamo in broadcast ma non mandiamo a noi stessi...
				serverView.send(s);
			}
		}
	}

	@Override
	public void run() {
		System.out.println("ServerViewSocket: chiamato il run()");
		int size;
		ByteBuffer buffer = ByteBuffer.allocate(8192);
		try {
			this.socket.configureBlocking(true);
			this.socket.socket().setKeepAlive(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while(true){
			try {
				while ((size = socket.read(buffer)) != -1) {
					buffer.flip();
					parse(new String(buffer.array(), 0, size));
					buffer.clear();
				}
			} catch (IOException e) {
				// Gestiamo il fatto che si e' scollegato il client?
				System.out.println("ServerViewSocket: sono caduto nella IOException");
			}

			System.out.println("ServerViewSocket: sopraggiunge la morte e "+socket.isConnected());
			this.connected = false;
			if(hasColor){
				sendToAll("lock\n");
				
				try {
					Thread.sleep(10000); //aspetto 10 secondi
				} 
				catch (InterruptedException e) {
				}
				
				sendToAll("unlock\n");
				
				if(!this.connected){
					try {
						controller.delPlayerByColor(myColor); //genera di per se un evento che viene inviato a tutti
					} catch (RemoteException e) {
					}
					break;
				}
			}
		}
		
	}
	
	public void parse(String buffer) {
		buffer = buffer.replace("\n","");
		System.out.println(buffer);
		try{
			if(buffer.equals("rotate")){
				sendToController("rotate");
			}
			else if(buffer.contains("place")){
				String[] strings = buffer.split("place:");
				if(strings.length == 2){
					strings = strings[1].split(",");
					if(strings.length == 2){
						int x = Integer.parseInt(strings[0]);
						int y = Integer.parseInt(strings[1]);
						sendToController("add tile "+x+" "+y);
					}
				}
			}
			else if(buffer.contains("tile")){
				String[] strings = buffer.split("tile:");
				sendToController("add piece "+Direction.convertFromChar(strings[1].charAt(0)).toString().toLowerCase());
			}
			else if(buffer.contains("pass")){
				sendToController("pass");
			}

		}
		catch( NumberFormatException e){
			System.out.print("Error:invalid input by client \n");
		}
		catch( InvalidDirectionException e){
			System.out.print("Error: invalid input by client \n");
		}
		
	}
	
	protected void sendToController(String string){
		try {
			controller.sendInput(string);
		} catch (RemoteException e) {
		} catch (InvalidInputException e) {
		}
	}

	@Override
	public void notifyUpdateInit(UpdateInitEvent updateEvent) {
		send(socketNotifyUpdateInit(updateEvent));
	}

	@Override
	public void notifyUpdateRotate(UpdateRotationEvent updateEvent) {
		send(socketNotifyRotate(updateEvent));
	}

	@Override
	public void notifyUpdateTile(UpdateTileEvent updateEvent) {
		send(printCoordTile(updateEvent.getSource())+"\n");
	}

	@Override
	public void notifyUpdatePiece(UpdatePieceEvent updateEvent) {
		send(printCoordTile(updateEvent.getSource())+",\n");
	}

	@Override
	public void notifyUpdateFinal(UpdateFinalEvent updateFinalEvent) {
		UpdateFinalObject obj = updateFinalEvent.getSource();
		StringBuffer buf = new StringBuffer();
		//Set<CoordinatesTile> tiles, Map<Colors, Integer> points
		for(CoordinatesTile coordTile: obj.getTiles()){
			buf.append(printCoordTile(coordTile)+","+"\n");
		}
		if(obj.getLeftPlayer() == null){
			buf.append("score:"+getScores(obj.getPoints())+"\n");
		}
		else{
			buf.append("leave:"+obj.getLeftPlayer().toString().toUpperCase()+"\n");
		}
		
		send(buf.toString());
	}

	@Override
	public void notifyUpdateFinish(UpdateGameFinishedEvent updateFinalEvent) {
		StringBuffer buf = new StringBuffer();
		Map<Colors,Integer> points = updateFinalEvent.getSource();
		buf.append("end:");
		buf.append(getScores(points));
		send(buf.toString()+"\n");
	}

	@Override
	public void notifyerrorNumberOfPlayers(
			NumberOfPlayerErrorEvent updateErrorEvent) {
		//Evento che non riceveremo mai in remoto
	}

	@Override
	public void notifyError(ErrorEvent event) {
		send(socketNotifyError());
	}

	@Override
	public void notifyUpdateInitFirst(UpdateInitFirstEvent updateInitFirstHandler) {
		System.out.println("ServerViewSocket: arrivato l'evento di update iniziale");
		UpdateInitFirstObject obj = updateInitFirstHandler.getSource();
		this.myColor = obj.getYourColor();
		this.hasColor = true;
		send(socketNotifyInitFirst(obj));
		this.notifyUpdateInit(new UpdateInitEvent(obj.getInitObject()));
	}
	
	synchronized private void send(String string){
	    ByteBuffer header = ByteBuffer.wrap(string.getBytes());
	    try {
			socket.write(header);
		} catch (IOException e) {
			// TODO: Errore nella scrittura
		}
	}
	
	public String socketNotifyError(){
		return "move not valid\n";
	}
	
	public String socketNotifyInitFirst(UpdateInitFirstObject obj){
		return "start:"+obj.getFirstTile().toString()+","+obj.getInitObject().getGameName()+","+obj.getYourColor()+","+obj.getPlayerList().size()+"\n";
	}
	
	public String printCoordTile(CoordinatesTile coordTile){
		return "update:"+coordTile.toString()+","+coordTile.getCoordinates().getX()+","+coordTile.getCoordinates().getY();
	}
	
	public String getScores(Map<Colors, Integer> points){
		StringBuffer buf = new StringBuffer();

		for(Entry<Colors, Integer> col: points.entrySet()){
			buf.append(col.getKey().toString().toLowerCase());
			buf.append("=");
			buf.append(String.valueOf(col.getValue()));
			buf.append(", ");
		}
		return buf.toString().substring(0, buf.length()-2);
	}
	
	public String socketNotifyRotate(UpdateRotationEvent event){
		return "rotated:"+event.getSource().toString()+"\n";
	}
	
	public String socketNotifyUpdateInit(UpdateInitEvent updateEvent) {
		String string = "turn:"+updateEvent.getSource().getPlayerColor().toString().toLowerCase()+"\n";
		string += "next:"+updateEvent.getSource().getCoordTile().toString()+"\n";
		return string;
	}


}
