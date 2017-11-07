package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import network.ClientNetworkImpl;
import network.ClientSocket;

import gamebase.Game;
import view.LocalBasicView;
import view.ObserverView;
import view.View;
import model.ObservableModelImplementation;
import controller.ImplementedController;

/**
 * Classe principale che istanzia il model,controller e view di gioco. Lancia la view di gioco scelta.
 * 
 * @author Daniele Iamartino, Daniele Gravina
 *			
 */
public final class Main {
	
	public static void main(String args[]) {

		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		String userLine = "";

		do{
			try {
				System.out.print("Welcome !\nText or graphical mode? (write \"text\" or \"graphical\")\n");
				userLine = userInput.readLine();
				if(userLine == null){
					userLine = "";
				}
			} catch (IOException e) {
				userLine = "";
			}
			
		}while(!(userLine.equals("text") || userLine.equals("graphical")));

		if(userLine.equals("text")){

			do{
				try {
					System.out.print("Do you want to play in local or with network? (write \"local\" or \"network\")\n");
					userLine = userInput.readLine();
					if(userLine == null){
						userLine = "";
					}
				} catch (IOException e) {
					userLine = "";
				} 
			}while(!(userLine.equals("local") || userLine.equals("network")));

			if(userLine.equals("local")){

				ObservableModelImplementation gameModel = new ObservableModelImplementation(new Game());

				ImplementedController gameController = new ImplementedController(gameModel);

				View gameView = new ObserverView(new LocalBasicView(gameController), gameModel);

				/* Avvia l'interfaccia grafica */
				gameView.start();
			}

			else{
				
		    		boolean ok = false;
		    		String address = "";
		    		Integer port = 0;
		    		do{
		    			try {
		    				System.out.print("Insert IP and port (ex. 127.0.0.1 1337)\n");
		    				userLine = userInput.readLine();
		    				if(userLine == null){
		    					userLine = "";
		    				}
		    				if(userLine.split(" ").length == 2){
		    					address = userLine.split(" ")[0];
		    					port = Integer.parseInt(userLine.split(" ")[1]);
		    					ok = true;
		    				}
		    			} 
		    			catch (IOException e) {
		    				userLine = "";
		    			} 
		    			catch (NumberFormatException e){
		    				ok = false;
		    			}
		    		}while(!ok);


				do{	
					try {
						System.out.print("Do you want to connect with RMI or with Socket? (write \"RMI\" or \"socket\")\n");
						userLine = userInput.readLine();
						if(userLine == null){
							userLine = "";
						}
					} catch (IOException e) {
						userLine = "";
					} 
				}while(!(userLine.equals("RMI") || userLine.equals("socket")));
				
				if(userLine.equals("RMI")){
					new ClientNetworkImpl(address, port, false);
				}
				else{
					new ClientSocket(address, port, false);
				}



			}
		}

		else{
			new InitialMenu();
		}
	}

}
