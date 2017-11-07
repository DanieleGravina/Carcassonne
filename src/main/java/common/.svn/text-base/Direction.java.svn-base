package common;


/**
 * Definisce le possibili direzioni cardinali
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public enum Direction { // L'ordine di dichiarazione e' fondamentale per le precedenze.
	NORTH,
	SOUTH,
	WEST,
	EAST;
	
	/**
	 * @return La direzione opposta a quella istanziata
	 */
	public Direction getOpposite(){
		if (this.equals(Direction.NORTH)){ return Direction.SOUTH; }
		else if (this.equals(Direction.SOUTH)){ return Direction.NORTH; }
		else if (this.equals(Direction.WEST)){ return Direction.EAST; }
		else{ return Direction.WEST; }
	}
	
	/**
	 * Calcola la direzione "successiva" in ordine orario rispetto all'attuale
	 * @return Prossima direzione
	 */
	public Direction next(){
		if(this.equals(Direction.NORTH)){ return Direction.EAST; }
		else if(this.equals(Direction.EAST)){ return Direction.SOUTH; }
		else if(this.equals(Direction.SOUTH)){ return Direction.WEST; }
		else { return Direction.NORTH; }
	}
	
	/**
	 * Calcola la direzione "precedente" in ordine orario rispetto all'attuale
	 * @return Precedente direzione
	 */
	public Direction prev(){
		if(this.equals(Direction.NORTH)){ return Direction.WEST; }
		else if(this.equals(Direction.WEST)){ return Direction.SOUTH; }
		else if(this.equals(Direction.SOUTH)){ return Direction.EAST; }
		else { return Direction.NORTH; }
	}
	
	/**
	 * Converte un carattere, che rappresenta il contenuto di un punto cardinale, in un oggetto Cardinale.
	 * 
	 * @param carattere di input
	 * @return Oggetto direzione associato alla stringa di input
	 */
	public static Direction convertFromChar(Character toConvert) throws InvalidDirectionException {
		if(toConvert.equals(Character.valueOf('N'))){ return Direction.NORTH; }
		else if(toConvert.equals(Character.valueOf('S'))){ return Direction.SOUTH; }
		else if(toConvert.equals(Character.valueOf('W'))){ return Direction.WEST; }
		else if(toConvert.equals(Character.valueOf('E'))){ return Direction.EAST; }
		else{
			throw new InvalidDirectionException();
		}
	}
	
	/**
	 * Converte l'oggetto Direzione nella sua rappresentazione in carattere
	 */
	public char toChar(){
		if(this.equals(Direction.NORTH)){
			return 'N';
		}
		else if(this.equals(Direction.SOUTH)){
			return 'S';
		}
		else if(this.equals(Direction.WEST)){
			return 'W';
		}
		else{
			return 'E';
		}
	}
	
}