package common;


/**
 * Definisce i tipi di costruzione che possono essere presenti all'interno di una tessera
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public enum Construction {
	ROAD,
	CITY,
	FIELD;
	
	/**
	 * Converte un carattere, che rappresenta il contenuto di un punto cardinale, in un oggetto Cardinale.
	 * 
	 * @param carattere di input
	 * @return Oggetto costruzione associato alla stringa di input
	 */
	public static Construction convertFromChar(Character toConvert) throws InvalidConstructionException {
		if(toConvert.equals(Character.valueOf('N'))){ return Construction.FIELD; }
		else if(toConvert.equals(Character.valueOf('S'))){ return Construction.ROAD; }
		else if(toConvert.equals(Character.valueOf('C'))){ return Construction.CITY; }
		else{
			throw new InvalidConstructionException();
		}
	}
	
	/**
	 * Converte l'oggetto nella sua rappresentazione in singolo carattere maiuscolo
	 * @return Il carattere ascii maiuscolo che rappresenta l'oggetto
	 */
	public char convertToChar(){
		if(this.equals(ROAD)){
			return 'S';
		}
		else if (this.equals(CITY)){
			return 'C';
		}
		else{
			return 'N';
		}
	}
	
	public String toString(){
		return String.valueOf(convertToChar());
	}
	
	/**
	 * @return Il punteggio associato ad ogni elemento dell'oggetto
	 */
	public int getScore(){
		if(this.equals(ROAD)){
			return 1;
		}
		else if (this.equals(CITY)){
			return 2;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * @return Il punteggio finale per costruzione nel caso di strutture non complete
	 */
	public int getFinalScore(){
		if(this.equals(CITY)){
			return 1;
		}
		else {
			return getScore();
		}
	}
}
