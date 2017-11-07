package common;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import controller.NetworkSocketController;
import network.ClientModel;

/**
 * Reverse controller utilizzato per ricevere e parsare i messaggi dal socket
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class SocketReverseController implements ReverseController,Runnable{
	
	private SocketChannel socket;
	private ClientModel clientModel;
	private String tempString;
	private Colors myColor;
	private String gameName;
	private NetworkSocketController controller;
	private boolean connected;
	private Map<Colors, Integer> scoresCache;

	public SocketReverseController(ClientModel clientModel, SocketChannel socket, NetworkSocketController controller){
		this.socket = socket;
		this.clientModel = clientModel;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(8192);
		int size;
		while(true){
			try{
				while ((size = socket.read(buffer)) != -1) {
					buffer.flip();
					parse(new String(buffer.array(), 0, size));
					buffer.clear();
				}
				System.out.println("Sono uscito dal while");
			}
			catch(IOException e){
			}
			connected = false;
			controller.reconnect();
			while(!connected){
				try {
					System.out.print("Disconnected from server. Waiting for reconnection.\n");
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) {
				}
			}
		}
	}
	
	public void reconnect(SocketChannel socket){
		this.socket = socket;
		try {
			send("reconnect:"+myColor.toString().toUpperCase()+","+gameName+"\n");
			controller.setsocket(socket);
		} catch (IOException e) {
			try{
				Thread.sleep(2000); //attendo due secondi
				controller.reconnect(); //ritento
			}
			catch(InterruptedException e1){
			}
		}
		connected = true;
	}
	
	/**
	 * Invia una messaggio sul socket di rete
	 * @param string Il messaggio da inviare
	 * @throws IOException In caso di errori nell'invio
	 */
	private void send(String string) throws IOException{
	    ByteBuffer header = ByteBuffer.wrap(string.getBytes());
		socket.write(header);
	}
	
	/**
	 * Effettua il parsing dei messaggi ricevuti dal client e lancia i relativi eventi
	 * @param string Stringa su cui effetuare il parsing
	 */
	public void parse(String string){
		boolean recycle = true;
		tempString += string;
		while(recycle){
			recycle = false;
			boolean flush = true;
			System.out.print("Faccio il parsing di:\n---\n"+tempString+"\n---\n");

			if(tempString.contains("start:")){
				String[] commatok = tempString.split("start:")[1].split(",");
				if(commatok.length == 4){
					try{
						CoordinatesTile tempCoordTile = new CoordinatesTile(commatok[0]);
						gameName = commatok[1];
						myColor = Colors.valueOf(commatok[2].toUpperCase());
						int numPlayers = Integer.valueOf(commatok[3].split("\n")[0]);
						List<Colors> colorList = Colors.getList();
						if(colorList.size() >= numPlayers){
							colorList = colorList.subList(0, numPlayers);
							if(tempString.contains("turn:") && tempString.contains("next:")){
								String[] turntok = tempString.split("turn:");
								if(turntok.length == 2){
									Colors nextPlayer = Colors.valueOf(turntok[1].split("\n")[0].toUpperCase());
									String[] nexttok = turntok[1].split("next:");
									if(nexttok.length == 2){
										CoordinatesTile nextCoordTile = new CoordinatesTile(nexttok[1].split("\n")[0]);
										UpdateInitObject obj = new UpdateInitObject(nextCoordTile, nextPlayer, gameName);
										try{
											notifyUpdateInitFirst(new UpdateInitFirstEvent(new UpdateInitFirstObject(tempCoordTile, colorList, myColor, obj)));
											scoresCache = new HashMap<Colors, Integer>();
											for(Colors col: colorList){
												scoresCache.put(col, Integer.valueOf(0));
											}
										}
										catch(RemoteException e){
										}
									}
									else{
										throw new InvalidInputException();
									}
								}
								else{
									throw new InvalidInputException();
								}
							}
							else{
								flush = false;
							}
						}
					}
					catch(InvalidInputException e){
					}
					catch(NumberFormatException e){
					}
				}
			}
			else if(tempString.contains("turn:") && !tempString.contains("\nturn:")){
				String[] turntok = tempString.split("turn:");
				if(turntok.length == 2){
					String[] turntok1 = turntok[1].split("\n");
					try{
						Colors actualColor = Colors.valueOf(turntok1[0].toUpperCase());
						if(tempString.contains("next:") && turntok1.length > 1){
							String[] nexttok = turntok1[1].split("next:");
							if(nexttok.length == 2){
								String[] nexttok1 = nexttok[1].split("\n");
								try{
									CoordinatesTile actualTile = new CoordinatesTile(nexttok1[0]);
									notifyUpdateInit(new UpdateInitEvent(new UpdateInitObject(actualTile, actualColor, gameName)));
								}
								catch(InvalidInputException e){
								}
								catch(RemoteException e){
								}
							}
						}
						else{
							flush = false;
						}
					}
					catch(IllegalArgumentException e){
					}
				}
			}
			else if(tempString.contains("rotated:")){
				tempString = tempString.replace("\n", "");
				String[] stringtok = tempString.split("rotated:");
				if(stringtok.length == 2){
					try{
						UpdateRotationEvent event = new UpdateRotationEvent(new CoordinatesTile(stringtok[1]));
						notifyUpdateRotate(event);					
					}
					catch(InvalidInputException e){
					}
					catch(RemoteException e){
					}
				}
			}
			else if(tempString.startsWith("update:") && !tempString.contains(",\n") && tempString.endsWith("\n")){
				//caso di update dopo posizionamento tessera
				try{
					String coordTile = tempString.split("update:")[1].split("\n")[0];
					notifyUpdateTile(new UpdateTileEvent(new CoordinatesTile(coordTile)));
				}
				catch(InvalidInputException e){
				}
				catch(RemoteException e){
				}
			}
			else if(tempString.contains("update:") && tempString.contains(",\n")){
				//update piece oppure di fine turno o leave

				List<String> updates = Arrays.asList(tempString.split("update:"));
				List<CoordinatesTile> coordTileChanged = new ArrayList<CoordinatesTile>();
				try{
					for(String update: updates.subList(1, updates.size())){
						String tempCoordTileString = update.split(",\n")[0];
						CoordinatesTile tmpCoordTile = new CoordinatesTile(tempCoordTileString);
						coordTileChanged.add(tmpCoordTile);
					}


					if(tempString.contains("score:") && tempString.endsWith("\n")){ // caso punteggi fine turno
						if(coordTileChanged.size() > 0){ //solo qua devo gestire l'evento notifyUpdatePiece(new UpdatePieceEvent(firstCoordTile));
							if(coordTileChanged.get(0).hasPiece()){
								notifyUpdatePiece(new UpdatePieceEvent(coordTileChanged.get(0)));
								coordTileChanged.remove(0);
							}
							String scoreText = tempString.split("score:")[1].split("\n")[0];
							notifyUpdateFinal(new UpdateFinalEvent(new UpdateFinalObject(new HashSet<CoordinatesTile>(coordTileChanged), parseScores(scoreText), null)));
							List<String> strarray = Arrays.asList(tempString.split("score:")[1].split("\n"));
							StringBuffer buffer = new StringBuffer();
							for(String tok: strarray.subList(1, strarray.size())){
								buffer.append(tok);
								buffer.append("\n");
							}
							try{
								tempString = buffer.toString().substring(0, buffer.toString().length()-1);
								if(tempString.length() > 0){
									recycle = true;
									flush = false;
								}
							}
							catch(IndexOutOfBoundsException e){
							}
						}
					}
					else if(tempString.contains("end:") && tempString.endsWith("\n")){ // caso punteggi fine partita
						if(coordTileChanged.size() > 0){
							String scoreText = tempString.split("end:")[1].split("\n")[0];
							parseScores(scoreText);
							notifyUpdateFinish(new UpdateGameFinishedEvent(parseScores(scoreText)));
						}
					}
					else if(tempString.contains("leave:") && tempString.endsWith("\n")){ //caso aggiornamento (senza punteggi) quando qualcuno se ne va
						if(coordTileChanged.size() > 0){ // leave:red\n
							Colors leftPlayer = Colors.valueOf(tempString.split("leave:")[1].split("\n")[0]);
							scoresCache.remove(leftPlayer);
							notifyUpdateFinal(new UpdateFinalEvent(new UpdateFinalObject(new HashSet<CoordinatesTile>(coordTileChanged), scoresCache, leftPlayer))); //TODO verificare se funziona sta roba (non abbiamo i punteggi...)
						}
					}
					else{
						flush = false;
					}
				}
				catch(InvalidInputException e){					
				}
				catch(IllegalArgumentException e){
				}
				catch(RemoteException e){
				}
			}
			else if(tempString.contains("end:") && tempString.endsWith("\n")){
				String[] endtok = tempString.split("end:");
				if(endtok.length == 2 && (endtok[1].length() > 0)){
					try{
						notifyUpdateFinish(new UpdateGameFinishedEvent(parseScores(endtok[1])));
					}
					catch(InvalidInputException e){
					}
					catch(RemoteException e){
					}
				}

			}
			else if(tempString.equals("lock\n")){
				try {
					notifyError(new ErrorEvent(new Exception("Locking message")));
					controller.enable(false);
				} catch (RemoteException e) {
				}
			}
			else if(tempString.equals("unlock\n")){
				try {
					notifyError(new ErrorEvent(new Exception("Unlocking message")));
					controller.enable(true);
				} catch (RemoteException e) {
				}
			}
			else if(tempString.contains("move not valid")){
				try {
					notifyError(new ErrorEvent(new Exception("move not valid")));
				} 
				catch (RemoteException e) {
				}
			}


			if(flush){
				tempString = "";
			}
		}
	}
	
	/**
	 * Funzione di parsing per il formato dei punteggi
	 * @param string Una stringa che rappresenta i punteggi associati ai giocatori
	 * @return Map<Colors,Integer> mappa di punteggi associati ai giocatori
	 * @throws InvalidInputException In caso in cui la stringa di ingresso non sia parsabile in modo corretto
	 */
	private Map<Colors,Integer> parseScores(String string) throws InvalidInputException{
		Map<Colors,Integer> toreturn = new HashMap<Colors, Integer>(); 
		String[] tmp = string.replace(" ","").replace("\n","").split(",");
		try{
			for(String token: tmp){
				String[] equaltok = token.split("=");
				if(equaltok.length == 2){
					Colors col = Colors.valueOf(equaltok[0].toUpperCase());
					Integer points = Integer.valueOf(equaltok[1]);
					toreturn.put(col, points);
				}
				else{
					throw new InvalidInputException();
				}
			}
		}
		catch(IllegalArgumentException e){
			throw new InvalidInputException();
		}
		scoresCache = toreturn;
		return toreturn;
	}

	@Override
	public void notifyUpdateInit(UpdateInitEvent event) throws RemoteException {
		System.out.println("Update init");
		clientModel.notifyUpdateInit(event);
	}

	@Override
	public void notifyUpdateRotate(UpdateRotationEvent event) throws RemoteException {
		System.out.println("Update rotate");
		clientModel.notifyUpdateRotate(event);
	}

	@Override
	public void notifyUpdateTile(UpdateTileEvent event) throws RemoteException {
		System.out.println("Update tile");
		clientModel.notifyUpdateTile(event);
	}

	@Override
	public void notifyUpdatePiece(UpdatePieceEvent event) throws RemoteException {
		System.out.println("Update piece");
		clientModel.notifyUpdatePiece(event);
	}

	@Override
	public void notifyUpdateFinal(UpdateFinalEvent event) throws RemoteException {
		System.out.println("Update final");
		clientModel.notifyUpdateFinal(event);
	}

	@Override
	public void notifyUpdateFinish(UpdateGameFinishedEvent event) throws RemoteException {
		System.out.println("Update finish");
		clientModel.notifyUpdateFinish(event);
	}

	@Override
	public void notifyerrorNumberOfPlayers(NumberOfPlayerErrorEvent event) throws RemoteException {
		// Non implementato nella comunicazione via socket
	}

	@Override
	public void notifyError(ErrorEvent event) throws RemoteException {
		System.out.println("Update error");
		clientModel.notifyError(event);
	}

	@Override
	public void notifyUpdateInitFirst(UpdateInitFirstEvent event) throws RemoteException {
		System.out.println("Update init first");
		clientModel.notifyUpdateInitFirst(event);
	}

}
