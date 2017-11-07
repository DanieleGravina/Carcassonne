package common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Definisce i colori utilizzabili per le pedine dei vari giocatori
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public enum Colors { // L'ordine di dichiarazione e' fondamentale per le precedenze.
	RED,
	BLUE,
	GREEN,
	YELLOW,
	BLACK;
	
	/**
	 * @return Un oggetto Color scelto a caso.
	 */
	public static Colors getRandom(){
		return Colors.values()[(new Random()).nextInt(Colors.values().length)];
	}
	
	/**
	 * @return Il colore successivo in base all'ordine convenzionalmente scelto
	 */
	public Colors next(){
		return Colors.values()[(this.ordinal()+1)%(Colors.values().length)];
	}
	
	/**
	 * @return Il colore precedente in base all'ordine convenzionalmente scelto
	 */
	public Colors prev(){
		return Colors.values()[(this.ordinal()-1)%(Colors.values().length)];
	}
	
	public static List<Colors> getListByFirst(Colors first) {
		ArrayList<Colors> templist = new ArrayList<Colors>();
		templist.add(first);
		for(int i=0; i<(Colors.values().length-1);i++){
			templist.add(templist.get(templist.size()-1).next());
		}
		return templist; 
	}
	
	/**
	 * @return Restituisce una lista di colori ordinata nello stesso modo della enum
	 */
	public static List<Colors> getList(){
		return Arrays.asList(Colors.values());
	}
	
	/**
	 * Converte l'oggetto nella sua rappresentazione in singolo carattere maiuscolo
	 * @return Il carattere ascii maiuscolo che rappresenta l'oggetto
	 */
	public char convertToChar(){
		if(this.equals(RED)){
			return 'R';
		}
		else if(this.equals(BLUE)){
			return 'B';
		}
		else if(this.equals(BLACK)){
			return 'K';
		}
		else if(this.equals(GREEN)){
			return 'G';
		}
		else {
			return 'Y'; //YELLOW
		}
	}
	
	/**
	 * Converte un char nella sua rappresentazione in Colors,se esiste
	 * @param toConvert
	 * @return
	 */
	public static Colors convertFromChar(char toConvert){
		if(toConvert == 'R'){
			return Colors.RED;
		}
		else if(toConvert == 'B'){
			return Colors.BLUE;
		}
		else if(toConvert == 'K'){
			return Colors.BLACK;
		}
		else if(toConvert == 'G'){
			return Colors.GREEN;
		}
		else{
			return Colors.YELLOW;
		}
	}
	
	/**
	 * Converte la nostra rappresentazione di Colors nella rappresentazione di java.
	 * @return
	 */
	public Color convertToColor(){
		if(this.equals(RED)){
			return Color.RED;
		}
		else if(this.equals(BLUE)){
			return Color.BLUE;
		}
		else if(this.equals(BLACK)){
			return Color.BLACK;
		}
		else if(this.equals(GREEN)){
			return Color.GREEN;
		}
		else {
			return Color.YELLOW; //YELLOW
		}
	}
	
}
