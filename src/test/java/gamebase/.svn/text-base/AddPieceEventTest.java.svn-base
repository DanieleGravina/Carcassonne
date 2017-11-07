package gamebase;


import static org.junit.Assert.assertEquals;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import view.AddPieceEvent;

import common.Colors;
import common.InvalidInputException;

import controller.Controller;

public class AddPieceEventTest {
	
	AddPieceEvent testAddPiece;
	ControllerForTest controllerTest;
	
	@Before
	public void setUp() throws Exception{
		controllerTest = new ControllerForTest();
	    testAddPiece = new AddPieceEvent(controllerTest);
	}
	
	private class ControllerForTest implements Controller{
		
		String string;

		@Override
		public void sendInitialInput(String string) throws RemoteException,
				InvalidInputException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendInput(String string) throws RemoteException,
				InvalidInputException {
			this.string = string;
			
		}

		@Override
		public void enable(boolean val) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delPlayerByColor(Colors color) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void reconnect() throws RemoteException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Test
	public void mouseClickedTest(){
		JComponent component = new JPanel();
		component.setSize(80, 80);
		MouseEvent event = new MouseEvent(component, 80, 80, 0, 0, 0, 0, false);
		testAddPiece.mouseClicked(event);
		assertEquals("la direzione è errata", controllerTest.string, "add piece north");
		
		event = new MouseEvent(component, 0, 0, 0, 30, 70, 0, false);
		testAddPiece.mouseClicked(event);
		assertEquals("la direzione è errata", controllerTest.string, "add piece south");
		
		event = new MouseEvent(component, 0, 0, 0, 70, 30, 0, false);
		testAddPiece.mouseClicked(event);
		assertEquals("la direzione è errata", controllerTest.string, "add piece east");
		
	    event = new MouseEvent(component, 0, 0, 0, 10, 60, 0, false);
		testAddPiece.mouseClicked(event);
		assertEquals("la direzione è errata", controllerTest.string, "add piece west");
		
	}

}
