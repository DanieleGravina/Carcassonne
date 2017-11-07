package gamebase;

import static org.junit.Assert.*;

import java.nio.channels.SocketChannel;
import java.rmi.RemoteException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import view.AddPieceEvent;

import network.ClientModel;
import network.ClientSocket;
import common.Colors;
import common.CoordinatesTile;
import common.ErrorEvent;
import common.UpdateGameFinishedEvent;
import common.InvalidInputException;
import common.NumberOfPlayerErrorEvent;
import common.ReverseController;
import common.SocketReverseController;
import common.UpdateFinalEvent;
import common.UpdateFinalObject;
import common.UpdateInitEvent;
import common.UpdateInitFirstEvent;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;
import common.UpdatePieceEvent;
import common.UpdateRotationEvent;
import common.UpdateTileEvent;
import controller.Controller;
import controller.NetworkSocketController;

public class SocketReverseControllerTest {
	
	SocketReverseControllerForTest controllerTest;
	
	@Before
	public void setUp() throws Exception{
		controllerTest = new SocketReverseControllerForTest();
	}
	
	private class ControllerForTest extends NetworkSocketController{

		public ControllerForTest() {
			super(null, null);
		}

		@Override
		public void sendInitialInput(String string) throws InvalidInputException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendInput(String string) throws InvalidInputException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void enable(boolean val) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delPlayerByColor(Colors color)  {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void reconnect() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class SocketReverseControllerForTest  extends SocketReverseController{
		
		Object event;
		Object event2;

		public SocketReverseControllerForTest() {
			super(null, null, new ControllerForTest() );
		}

		@Override
		public void notifyUpdateInit(UpdateInitEvent event)
				throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyUpdateRotate(UpdateRotationEvent event)
				throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyUpdateTile(UpdateTileEvent event)
				throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyUpdatePiece(UpdatePieceEvent event)
				throws RemoteException {
			this.event2 = event;
			
		}

		@Override
		public void notifyUpdateFinal(UpdateFinalEvent event)
				throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyUpdateFinish(UpdateGameFinishedEvent event)
				throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyerrorNumberOfPlayers(NumberOfPlayerErrorEvent event)
				throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyError(ErrorEvent event) throws RemoteException {
			this.event = event;
			
		}

		@Override
		public void notifyUpdateInitFirst(UpdateInitFirstEvent event)
				throws RemoteException {
			this.event = event;
			
		}
		
		public Object getEvent(){
			return event;
		}
		
	}
	
	@Test
	public void parseOfInitFirstEventTest(){
		boolean ok = true;
		
		String stringTest = "start:N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,Game0,RED,2\n";
		String stringTest2 = "turn:red\n";
		String stringTest3 = "next:N=C S=N W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0\n";

		
		controllerTest.parse(stringTest);
		controllerTest.parse(stringTest2);
		controllerTest.parse(stringTest3);
		UpdateInitFirstEvent event = (UpdateInitFirstEvent)controllerTest.getEvent();
		
		CoordinatesTile coordTile = null;
		CoordinatesTile coordTile2 = null;
		
		try {
			coordTile = new CoordinatesTile("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
			coordTile2 = new CoordinatesTile("N=C S=N W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0");
		} catch (InvalidInputException e) {
		}
		
		UpdateInitFirstObject obj = event.getSource();
		
		if(!obj.getYourColor().equals(Colors.RED)){
			ok = false;
		}
		
		if(obj.getPlayerList().size() != 2){
			ok = false;
		}
		
		if(!(obj.getFirstTile().equals(coordTile))){
			ok = false;
		}
		
		if(!(obj.getInitObject().getCoordTile().equals(coordTile2))){
			ok = false;
		}
		
		if(!(obj.getInitObject().getGameName().equals("Game0"))){
			ok = false;
		}
		
		if(!(obj.getInitObject().getPlayerColor().equals(Colors.RED))){
			ok = false;
		}
		
		assertTrue("Errore nel parsing della stringa di UpdateInitFirstEvent", ok);
	}
	
	@Test
	public void parseOfInitEventTest(){
		boolean ok = true;
        CoordinatesTile coordTile1 = null;

		String stringTest1 = "turn:red\n";
		String stringTest2 = "next:N=C S=N W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0";

		controllerTest.parse(stringTest1);
		controllerTest.parse(stringTest2);

		UpdateInitEvent event = (UpdateInitEvent)controllerTest.getEvent();

		UpdateInitObject obj = event.getSource();

		try {
			coordTile1 = new CoordinatesTile("N=C S=N W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0");
		} catch (InvalidInputException e) {
		}

		if(!(obj.getCoordTile().equals(coordTile1))){
			ok = false;
		}

		if(!(obj.getPlayerColor().equals(Colors.RED))){
			ok = false;
		}
		
		assertTrue("Errore nel parsing della stringa di UpdateInitEvent", ok);
	}
	
	@Test
	public void parseOfRotateEventTest(){
		boolean ok = true;
		CoordinatesTile coordTile = null;
		String stringTest = "rotated:N=N S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0\n";
		controllerTest.parse(stringTest);
		
		try {
			coordTile = new CoordinatesTile("N=N S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		} catch (InvalidInputException e) {
		}
		
		UpdateRotationEvent event = (UpdateRotationEvent)controllerTest.getEvent();
		
		CoordinatesTile obj = event.getSource();
		
		assertEquals("Errore nel parsing della stringa di UpdateRotationEvent",obj, coordTile);
	}
	
	@Test 
	public void parseOfUpdatePieceEvent(){
		CoordinatesTile coordTile = null;
		String stringTest = "update:N=C+R S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,1,0,\n";
		String stringTest2 = "score:blue=0, red=0\n";
		controllerTest.parse(stringTest);
		controllerTest.parse(stringTest2);
		
		try {
			coordTile = new CoordinatesTile("N=C+R S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,1,0");
		} catch (InvalidInputException e) {
		}
		
		
		UpdatePieceEvent event = (UpdatePieceEvent)controllerTest.event2;
		
		CoordinatesTile obj = event.getSource();
		
		assertEquals("Errore nel parsing della stringa di UpdatePieceEvent",obj, coordTile);
	}
	
	@Test
	public void parseOfUpdateFinalEvent(){
		CoordinatesTile coordTile = null;
		String stringTest = "update:N=C+R S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,1,0,\n";
		String stringTest2 = "update:N=N S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,1,1,\n";
		String stringTest3 = "score:blue=0, red=0\n";
		controllerTest.parse(stringTest);
		controllerTest.parse(stringTest2);
		controllerTest.parse(stringTest3);
		
		try {
			coordTile = new CoordinatesTile("N=N S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,1,1");
		} catch (InvalidInputException e) {
		}
		
		
		UpdateFinalEvent event = (UpdateFinalEvent)controllerTest.getEvent();
		
		UpdateFinalObject obj = event.getSource();
		
		CoordinatesTile coordTile2 = null;
		
		for(CoordinatesTile coord : obj.getTiles()){
			coordTile2 = coord;
		}
		
		assertTrue("Errore nel parsing della stringa di UpdateFinalEvent",coordTile.equals(coordTile2));
		
		assertTrue("Errore nel parsing della stringa di UpdateFinalEvent",obj.getPoints().containsKey(Colors.RED) && obj.getPoints().containsKey(Colors.BLUE));
		
		assertTrue("Errore nel parsing della stringa di UpdateFinalEvent",obj.getPoints().get(Colors.RED) == 0 && obj.getPoints().get(Colors.BLUE) == 0);
		
		
		
	}
	
	@Test
	public void parseOfUpdateFinishEvent(){
		String stringTest = "end:blue=5, red=10\n";
		controllerTest.parse(stringTest);
		
		UpdateGameFinishedEvent event = (UpdateGameFinishedEvent)controllerTest.getEvent();
		
		Map<Colors,Integer> obj = (Map<Colors,Integer>) event.getSource();
		
		assertTrue("Errore nel parsing della stringa di UpdateFinalEvent",obj.get(Colors.RED) == 10 && obj.get(Colors.BLUE) == 5);
		
	}
	

}
