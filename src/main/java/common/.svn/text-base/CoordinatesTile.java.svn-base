package common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe immutabile che rappresenta le tessere e le coordinate in cui si trovano. 
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class CoordinatesTile implements Serializable{

	private static final long serialVersionUID = -1601287192814198291L;
	private final Coordinates coord;
	private final Map<Direction, Set<Direction>> connections;
	private final Map<Direction, Construction> constructions;
	private final Map<Direction, Colors> pieces;
	
	public CoordinatesTile(Coordinates coord,
			Map<Direction, Set<Direction>> connections,
			Map<Direction, Construction> constructions,
			Map<Direction, Colors> pieces){
		this.coord = coord;
		this.connections = connections;
		this.constructions = constructions;
		this.pieces = pieces;
	}
	
	public CoordinatesTile(String coordTileString) throws InvalidInputException{
		//"N=R+R S=C W=C E=R NS=0 NE=0 NW=1 WE=0 SE=1 SW=0"
		//"N=R+R S=C W=C E=R NS=0 NE=0 NW=1 WE=0 SE=1 SW=0,0,0"
		connections = new HashMap<Direction, Set<Direction>>();
		for(Direction dir: Direction.values()){
			connections.put(dir, new HashSet<Direction>());
		}
		constructions = new HashMap<Direction, Construction>();
		pieces = new HashMap<Direction, Colors>();
		
		Set<String> directionstrings = new HashSet<String>();
		directionstrings.add("N");
		directionstrings.add("S");
		directionstrings.add("W");
		directionstrings.add("E");
		
		Set<String> connectionstring = new HashSet<String>();
		connectionstring.add("NS");
		connectionstring.add("NE");
		connectionstring.add("NW");
		connectionstring.add("WE");
		connectionstring.add("SE");
		connectionstring.add("SW");
		
		String[] stringtok = coordTileString.split(" ");
		if(stringtok.length == 10){
			for(String tok: stringtok){
				String[] parts = tok.split("=");
				if(parts.length == 2){
					if(directionstrings.contains(parts[0])){
						try{
							Direction dir = Direction.convertFromChar(parts[0].charAt(0));
							String[] value = parts[1].split("\\+");
							Construction constr = Construction.convertFromChar(value[0].charAt(0));
							//ho in mano costruzione e direzione
							constructions.put(dir, constr);
							if(value.length == 2){
								//c'e' un segnalino
								pieces.put(dir, Colors.convertFromChar(value[1].charAt(0)));
							}
						}
						catch(Exception e){
							throw new InvalidInputException();
						}
					}
					else if(connectionstring.contains(parts[0])){
						try{
							if((parts[1].charAt(0) == '1' || parts[1].charAt(0) == '0')){
								Direction dir1 = Direction.convertFromChar(parts[0].charAt(0));
								Direction dir2 = Direction.convertFromChar(parts[0].charAt(1));
								if(parts[1].equals("1")){
									connections.get(dir1).add(dir2);
									connections.get(dir2).add(dir1);
								}
							}
						}
						catch(Exception e){
							throw new InvalidInputException();
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
			//controllo presenza coordinate
			String[] coordtok = coordTileString.split("=");
			String[] coordlasttok = coordtok[coordtok.length-1].split(",");
			
			if(coordlasttok.length == 3){
				//tua madre
				try{
					this.coord = new Coordinates(Integer.valueOf(coordlasttok[1]), Integer.valueOf(coordlasttok[2]));
				}
				catch(Exception e){
					throw new InvalidInputException();
				}
			}
			else{
				//coordinate fuffa
				this.coord = new Coordinates(0, 0);
			}
		}
		else{
			throw new InvalidInputException();
		}
	}
	
	/**
	 * @return Le coordinate dell'oggetto
	 */
	public Coordinates getCoordinates(){
		return this.coord;
	}
	
	/**
	 * @param dir La direzione della struttura interna ad una tessera
	 * @return Il set di direzioni a cui e' collegata la costruzione della tessera nella direzione dir
	 */
	public Set<Direction> getConnectionsByDirection(Direction dir){
		return connections.get(dir);
	}
	
	/**
	 * @return True se la tessera possiede un segnalino
	 */
	public boolean hasPiece(){
		return !pieces.isEmpty();
	}
	
	/**
	 * Stabilisce se e' presente un segnalino in una certa direzione della tessera
	 * @param dir La direzione dove cercare il segnalino
	 * @return True se e' presente un segnalino in quella direzione
	 */
	public boolean hasPieceByDirection(Direction dir){
		return pieces.containsKey(dir);
	}
	
	/**
	 * @return Le direzioni nella tessera dove sono presenti segnalini
	 */
	public Set<Direction> getDirectionOfPiece(){
		return pieces.keySet();
	}
	
	/**
	 * Metodo per ottenre il colore del segnalino in una determinata direzione
	 * @param dir La direzione di ricerca
	 * @return Il colore del segnalino
	 */
	public Colors getPieceColorByDirection(Direction dir){ 
		return pieces.get(dir);
	}
	
	/**
	 * Metodo per ottenere la costruzione presente in una determinata direzione
	 * @param dir La direzione di ricerca
	 * @return La costruzione presente nella direzione
	 */
	public Construction getConstructionByDirection(Direction dir){
		return constructions.get(dir);
	}
	
	@Override
	public String toString(){
		String s = "";
		StringBuffer buf = new StringBuffer();
		String piece;
		for(Direction dir: Direction.values()){
			if(this.hasPiece() && getDirectionOfPiece().contains(dir)){
				piece = "+"+getPieceColorByDirection(dir).convertToChar();
			}
			else{
				piece = "";
			}
			buf.append(String.valueOf(dir.toChar()));
			buf.append("=");
			buf.append(getConstructionByDirection(dir).toString());
			buf.append(piece);
			buf.append(" ");
		}
		for(String str:  new String[] {"NS", "NE", "NW", "WE", "SE", "SW"}){
			try{
				Direction from = Direction.convertFromChar(Character.valueOf(str.charAt(0)));
				Direction to = Direction.convertFromChar(Character.valueOf(str.charAt(1)));
				buf.append(str);
				buf.append("=");
				buf.append(getConnectionsByDirection(from).contains(to) ? "1" : "0");
				buf.append(" ");
			}
			catch(InvalidDirectionException e){
			}
		}
		s = buf.toString();
		s = s.substring(0, s.length()-1);
		return s;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((connections == null) ? 0 : connections.hashCode());
		result = prime * result
				+ ((constructions == null) ? 0 : constructions.hashCode());
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		CoordinatesTile other = (CoordinatesTile) obj;
		if (connections == null) {
			if (other.connections != null){
				return false;
			}
		} else if (!connections.equals(other.connections)){
			return false;
		}
		if (constructions == null) {
			if (other.constructions != null){
				return false;
			}
		} else if (!constructions.equals(other.constructions)){
			return false;
		}
		if (coord == null) {
			if (other.coord != null){
				return false;
			}
		} else if (!coord.equals(other.coord)){
			return false;
		}
		if (pieces == null) {
			if (other.pieces != null){
				return false;
			}
		} else {
			for(Direction dir: pieces.keySet()){
				if(!(other.pieces.containsKey(dir) && pieces.get(dir).equals(other.pieces.get(dir)))){
					return false;
				}
			}
			return true;
		}
		return true;
	}
}
