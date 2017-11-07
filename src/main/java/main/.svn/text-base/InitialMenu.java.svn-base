package main;

import gamebase.Game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import network.ClientNetworkImpl;
import network.ClientSocket;

import view.LocalSwingView;
import view.ObserverView;
import view.View;

import common.InvalidInputException;
import common.ObservableModel;

import controller.Controller;
import controller.ImplementedController;

import model.ObservableModelImplementation;

/**
 * Menu iniziale per scegliere gioco in locale o in rete
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class InitialMenu extends JFrame{
	
	private JCheckBox local ;
	private JCheckBox network ;
	private final ButtonGroup groupOfButton;
	private JFrame window;
	private ImageIcon icon;
	private JLabel title;
    
	public InitialMenu(){
		JCheckBox local = new JCheckBox("Local");
		JCheckBox network = new JCheckBox("Network");
		local.setActionCommand("Local");
		network.setActionCommand("Network");
		icon = new ImageIcon("src/main/java/data/Carcasssonne.jpg");
		title = new JLabel(icon);
		
		JPanel menuPanel = new JPanel(new GridLayout(2, 1, 8, 8));
		menuPanel.add(local);
		menuPanel.add(network);
		menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

		
		groupOfButton = new ButtonGroup();
		groupOfButton.add(local);
		groupOfButton.add(network);
		local.setSelected(true);
		
		JButton okButton = new JButton("ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkChoice(groupOfButton, (JComponent)e.getSource());
			}
		});

		
		window = new JFrame("Menu");
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.add(menuPanel, BorderLayout.CENTER);
		window.add(okButton, BorderLayout.SOUTH);
		window.add(title, BorderLayout.NORTH);
		window.pack();
		window.setVisible(true);



	}

	private void checkChoice(ButtonGroup group, JComponent source) {
		boolean ok = false;
		String stringLocal;
		ButtonModel selection = group.getSelection();
		String actionCommand = selection.getActionCommand();
		if("Local".equals(actionCommand)) {
			Integer numOfPlayer = null;
			
			ObservableModelImplementation gameModel = new ObservableModelImplementation(new Game());
			Controller controller = new ImplementedController(gameModel);
			View gameView = new ObserverView(new LocalSwingView(controller), gameModel);
			
			do{
				stringLocal = JOptionPane.showInputDialog(source, "Choose the number of players", "2");
				try{
					numOfPlayer = Integer.parseInt(stringLocal);
					if(numOfPlayer<2 || numOfPlayer>5){
						ok = false;
					}
					else{
						ok = true;
					}
				}
			catch(NumberFormatException e){
				ok = false;
			}
			}while(!ok);
			
			try {
				window.setVisible(false);
				gameView.start();
				controller.sendInitialInput(stringLocal);
			} catch (RemoteException e) {
			} catch (InvalidInputException e) {
			}
			
		} else if("Network".equals(actionCommand)) {
			String stringNetwork;
			String type;
			Integer port = null;
			ok = false;
			do{
			stringNetwork = JOptionPane.showInputDialog(source, "IP-port", "127.0.0.1 3389");
			if(stringNetwork.split(" ").length == 2){
				try{
					port = Integer.parseInt(stringNetwork.split(" ")[1]);
					ok = true;
				}
				catch(NumberFormatException e){
					ok = false;
				}
			}
			}while(!ok);
			
			do{
				type = JOptionPane.showInputDialog(source, "RMI or Socket", "Socket");
			}while(!(type.equals("RMI") || type.equals("Socket")));
			
			if(type.equals("RMI")){
				window.setVisible(false);
			    new ClientNetworkImpl(stringNetwork.split(" ")[0], port, true);	
			}
			else{
				window.setVisible(false);
				new ClientSocket(stringNetwork.split(" ")[0], port,true);
			}
		    
		}

		
	}

}
