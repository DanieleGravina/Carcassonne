package gamebase;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import common.Colors;
import common.Coordinates;
import common.Direction;
import common.InvalidActionException;
import common.InvalidCoordinatesException;
import common.InvalidInputException;
import common.NumberOfPlayerException;
import common.PieceException;
import common.PositioningNotCompatibleException;
import controller.ImplementedController;

import model.MutableModel;

public class ImplementedControllerTest {
	
	ImplementedController controllerTest;
	ModelForTest modelTest;
	
	public class ModelForTest implements MutableModel{
		
		String string;

		@Override
		public void ready() {
			string = "ready";
			
		}

		@Override
		public void setGameName(String gameName) {
			string = gameName;
			
		}

		@Override
		public void addTile(Coordinates coord)
				throws InvalidCoordinatesException,
				PositioningNotCompatibleException, InvalidActionException {
			string = coord.toString();
			
		}

		@Override
		public void rotateTile() throws InvalidActionException {
			string = "rotate";
			
		}

		@Override
		public void addPiece(Direction dir) throws PieceException,
				InvalidActionException {
			string = dir.toString();
			
		}

		@Override
		public void addNewPlayer(int num) throws NumberOfPlayerException,
				InvalidActionException {
			string = ""+num;
			
		}

		@Override
		public void delPlayerByColor(Colors color)
				throws NumberOfPlayerException {
			string = color.toString();
			
		}

		@Override
		public void checkCompleteConstructions() throws InvalidActionException {
			string = "checkCompleteconstruction";
			
		}

		@Override
		public void pass() throws InvalidActionException {
			string = "pass";
			
		}

		@Override
		public void nextRound() throws NumberOfPlayerException {
			string = "nextRound";
			
		}
		
	}
	
	@Before
	public void setUp() throws Exception {
		modelTest = new ModelForTest();
		controllerTest = new ImplementedController(modelTest);
	}
	
	@Test
	public void sendInitialInputTest(){
		
		try {
			controllerTest.sendInitialInput("5");
		} catch (RemoteException e) {
		} catch (InvalidInputException e) {
		}
		
		assertEquals("errore nel lancio di setInitialInput", "5", modelTest.string );
	}
	
	@Test
	public void sendInputTest(){
		
		try {
			controllerTest.sendInput("add tile 0 1");
		} catch (RemoteException e) {
		} catch (InvalidInputException e) {
		}
		Coordinates coord = new Coordinates(0,1);
		assertEquals("errore nel lancio di setInput", coord.toString(), modelTest.string );
		
		try {
			controllerTest.sendInput("pass");
		} catch (RemoteException e) {
		} catch (InvalidInputException e) {
		}
		assertEquals("errore nel lancio di setInput", "pass", modelTest.string );
		
		try {
			controllerTest.sendInput("rotate");
		} catch (RemoteException e) {
		} catch (InvalidInputException e) {
		}
		assertEquals("errore nel lancio di setInput", "rotate", modelTest.string );
		
		try {
			controllerTest.sendInput("add piece north");
		} catch (RemoteException e) {
		} catch (InvalidInputException e) {
		}
		assertEquals("errore nel lancio di setInput", Direction.NORTH.toString() , modelTest.string );
		
	}
	
	public void delPlayerByColorTest(){
		
		controllerTest.delPlayerByColor(Colors.GREEN);
		assertEquals("errore nel lancio di setInput", Colors.GREEN.toString() , modelTest.string );
	}
	
	

}
