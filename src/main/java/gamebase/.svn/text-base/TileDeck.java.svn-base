package gamebase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import common.Construction;
import common.Direction;
import common.InvalidActionException;
import common.InvalidConstructionException;
import common.InvalidDirectionException;
import common.InvalidFileFormatException;

/**
 * Classe che effettua il parsing del file contenente le tessere di gioco e genera la lista di tessere utilizzabili.
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class TileDeck {
	private LinkedList<Tile> deck;
	private int firstTileOffset;
	private boolean firstTile;
	
	/**
	 * Funzione che effettua il parsing di una singola riga formattata come presente all'interno del file del mazzo
	 * @param str La stringa contenente una riga
	 * @return L'oggetto tessera
	 * @throws InvalidFileFormatException Lanciato se il formato dato in ingresso non e' valido
	 */
	private Tile lineParser(String str) throws InvalidFileFormatException {
		if(!str.equals("")){
			Map<Direction, Construction> constructions = new HashMap<Direction, Construction>();
			Map<Direction, Set<Direction>> connections = new HashMap<Direction, Set<Direction>>();
			
			for (String s :str.split(" ")){
				String[] tok = s.split("=");
				
				if(tok.length == 2){

					/*
					 * tok[0] è la parte a sinistra dell'uguale
					 * tok[1] è la parte a destra dell'uguale
					 * 
					 */
					if (tok[0].length() == 1){
						try{

							Character in1 = Character.valueOf(tok[0].charAt(0)); /* Parte prima dell'uguale */
							Character in2 = Character.valueOf(tok[1].charAt(0)); /* Parte dopo l'uguale*/

							try {
								constructions.put(Direction.convertFromChar(in1), Construction.convertFromChar(in2));
							} catch (InvalidDirectionException e) {
								throw new InvalidFileFormatException();
							} catch (InvalidConstructionException e) {
								throw new InvalidFileFormatException();
							} 
						}
						catch(IllegalArgumentException e){
							throw new InvalidFileFormatException();
						}
					}
					else if (tok[0].length() == 2){
						try{
							Character in1 = Character.valueOf(tok[0].charAt(0)); /* Parte prima dell'uguale, prima lettera */
							Character in2 = Character.valueOf(tok[0].charAt(1)); /* Parte prima dell'uguale, seconda lettera */
							Character esisteCollegamento = Character.valueOf(tok[1].charAt(0)); /* Parte dopo l'uguale */

							if (esisteCollegamento.equals(Character.valueOf('1'))){
								try {
									if(!connections.containsKey(Direction.convertFromChar(in1))){
										connections.put(Direction.convertFromChar(in1), new HashSet<Direction>());
									}
									if(!connections.containsKey(Direction.convertFromChar(in2))){
										connections.put(Direction.convertFromChar(in2), new HashSet<Direction>());
									}
									connections.get(Direction.convertFromChar(in1)).add(Direction.convertFromChar(in2));
									connections.get(Direction.convertFromChar(in2)).add(Direction.convertFromChar(in1));
								} catch (InvalidDirectionException e) {
									throw new InvalidFileFormatException();
								}
							}
						}
						catch(IllegalArgumentException e){
							throw new InvalidFileFormatException();
						}

					}
					else{
						throw new InvalidFileFormatException();
					}
				}
				else{
					throw new InvalidFileFormatException();
				}
			}
			if (constructions.size() != Direction.values().length){
				//Se non ho esattamente 4 costruzioni c'è stato qualche problema nel parsing, lanciamo eccezioni? Sì
				throw new InvalidFileFormatException();
			}

			/* Instanzio la tessera */
			Tile tess = new Tile(constructions, connections);
			/* Aggiungo la tessera alla lista */
			return tess;
		}
		else{
			/* Se la riga è vuota, non facciamo niente */
			throw new InvalidFileFormatException();
		}

	}
	
	/* Costruttore che utilizza il file di default. Viene utilizzato un costruttore diverso per poter fare i test con file diversi */
	public TileDeck(int primaTessera) throws InvalidFileFormatException,IOException {
		this(primaTessera, "src/main/java/data/carcassonne.txt");
	}
	
	public TileDeck(int primaTessera, String filename) throws InvalidFileFormatException,IOException {
		deck = new LinkedList<Tile>();
		this.firstTileOffset = primaTessera;
		firstTile = false;
		
		/* Viene effettuato il parsing del file che contiene le tessere */
	    BufferedReader in = new BufferedReader(new FileReader(filename));
	    String str;
	    
	    try{
		    while ((str = in.readLine()) != null) {
		    	Tile parsedTessera = this.lineParser(str);
		    	deck.add(parsedTessera);
		    }
	    }
	    catch(IOException e){
	    	in.close();
	    	throw e;
	    }
	    catch(InvalidFileFormatException e){
	    	in.close();
	    	throw e;
	    }
	    in.close();
		
		
	}
	
	/**
	 * @return La dimensione del mazzo
	 */
	public int size(){
		return deck.size();
	}
	
	/**
	 * Metodo per mescolare il mazzo
	 */
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	/**
	 * Metodo per ottenere la prima tessera del mazzo
	 * @return Un oggetto Tile pescato da un offset arbitrario fisso
	 */
	public Tile getFirstTile() throws InvalidActionException{
		if(!firstTile){
			Tile toReturn = deck.get(firstTileOffset);
			deck.remove(firstTileOffset);
			firstTile = true;
			return toReturn;
		}
		else{
			throw new InvalidActionException();
		}
	}
	
	/**
	 * Metodo per ottenere la prossima tessera del mazzo
	 * @return Un oggetto Tile pescato dal mazzo
	 * @throws InvalidActionException Se il mazzo e' vuoto
	 */
	public Tile getNextTile() throws InvalidActionException{
		if(!deck.isEmpty()){
			Tile to_return = deck.getFirst();
			deck.removeFirst();
			return to_return;
		}
		else{
			throw new InvalidActionException();
		}
	}
	
	
}
