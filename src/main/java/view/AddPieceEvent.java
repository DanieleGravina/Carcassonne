package view;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import common.InvalidInputException;

import controller.Controller;

/**
 * Listener per vedere in che direzione l'utente vuole mettere la pedina.
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class AddPieceEvent implements MouseListener{
	
	private int half;
	private int max;
	private Controller controller;
	private int x;
	private int y;
	
	public AddPieceEvent(Controller controller){
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		half = arg0.getComponent().getHeight()/2;
		max = arg0.getComponent().getHeight();
		String direction;
		if((x<half && y<half && x>y) || (x>half && y<half && y<max-x)){
			direction = "north";
		}
		else{
			if((x>half && y<half && y>max-x) || (x>half && y>half && x>y)){
				direction = "east";
			}
			else{
				if((x<half && y>half && y>max-x) || (x>half && y>half && y>x)){
					direction = "south";
				}else{
					if((x<half && y<half && y>x) || (x<half && y>half && y< max-x )){
						direction = "west";
					}
					else{
						direction = "north";
					}
				}
			}
		}
		
		try {
			controller.sendInput("add piece "+ direction);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InvalidInputException e) {
	    }
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
