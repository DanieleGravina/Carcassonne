package gamebase;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import common.Colors;
import common.CoordinatesTile;
import common.UpdateInitEvent;
import common.UpdateInitFirstEvent;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;
import common.UpdateRotationEvent;

import static org.junit.Assert.*;

import controller.Controller;
import model.ObservableModelImplementation;
import network.ServerViewSocket;

public class ServerViewSocketTest {
	
	private TestServerView myServerView;
	
	@Before
	public void setUp() throws Exception{
		myServerView = new TestServerView(null, null);
	}
	
	@SuppressWarnings("serial")
	private class TestServerView extends ServerViewSocket{
	
		public TestServerView(SocketChannel socket, Controller controller) {
			super(socket, controller, new ObservableModelImplementation(new Game()), null);
		}

		private String string;
		
		@Override
		protected void sendToController(String string){
			this.string = string;
		}
		
		public String getString(){
			return this.string;
		}
	}
	
	@Test
	public void parseTest(){
		String buffer = new String("place:1,2\n");
		myServerView.parse(buffer);
		assertEquals("Errore nel parsing di place", myServerView.getString(), "add tile 1 2");
		
		buffer= new String("pass\n");
		myServerView.parse(buffer);
		assertEquals("Errore nel parsing di place", myServerView.getString(), "pass");
		
		buffer = new String("tile:N\n");
		myServerView.parse(buffer);
		assertEquals("Errore nel parsing di place", myServerView.getString(), "add piece north");
		
		buffer = new String("rotate\n");
		myServerView.parse(buffer);
		assertEquals("Errore nel parsing di place", myServerView.getString(), "rotate");
	}
	
	@Test
	public void socketNotifyInitFirstTest(){
		CoordinatesTile coordTile = CoordinatesTileTest.createCustomCoordinatesTile();
		List<Colors> players = new ArrayList<Colors>();
		players.add(Colors.BLACK);
		players.add(Colors.BLUE);
		UpdateInitObject initObj = UpdateInitObjectTest.createCustomUpdateInitObject();
		
		UpdateInitFirstObject obj = new UpdateInitFirstObject(coordTile, players, Colors.RED, initObj);
		String string = "start:N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0,Antani,RED,2\n";
		myServerView.socketNotifyInitFirst(obj);
		assertEquals("Errore nella traduzione in string di UpdateInitFirstObject", myServerView.socketNotifyInitFirst(obj), string );
	}
	
	@Test
	public void printCoordTileTest(){
		CoordinatesTile coordTile = CoordinatesTileTest.createCustomCoordinatesTile();
		String string = "update:N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0,0,0";
		myServerView.printCoordTile(coordTile);
		assertTrue("Errore nella traduzione in string di printCoordTile", myServerView.printCoordTile(coordTile).equals(string) );
	}
	
	@Test
	public void getScoresTest(){
		Map<Colors, Integer> points = new HashMap<Colors, Integer>();
		points.put(Colors.BLACK, 3);
		points.put(Colors.RED, 4);
		String string = "red=4, black=3";
		String string2 = "black=3, red=4";
		String string3 = myServerView.getScores(points);
		assertTrue("Errore nella traduzione in string di getScores", string3.equals(string) || string3.equals(string2));
	}
	
	@Test
	public void socketNotifyRotatetesTest(){
		String string = "rotated:N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0\n";
		String string2 = myServerView.socketNotifyRotate(new UpdateRotationEvent(CoordinatesTileTest.createCustomCoordinatesTile()));
		assertTrue("Errore nella traduzione in string di notifyRotate", string.equals(string2));
	}
	
	@Test
	public void socketNotifyUpdateInitTest(){
		String string = "turn:green\nnext:N=S+R S=C W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0\n";
		String string2 = myServerView.socketNotifyUpdateInit(new UpdateInitEvent(UpdateInitObjectTest.createCustomUpdateInitObject()));
		assertTrue("Errore nella traduzione in string di notifyUpdateInit", string.equals(string2));
	}

}


