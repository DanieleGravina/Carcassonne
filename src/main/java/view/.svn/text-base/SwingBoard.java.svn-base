package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import common.Colors;
import common.Coordinates;
import common.CoordinatesTile;
import common.Direction;
import common.UpdateInitFirstObject;
import controller.Controller;

/**
 * Rappresenta il tabellone con le librerie swing
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
@SuppressWarnings("serial")
public class SwingBoard extends JFrame{
	
	private Map<Coordinates, BufferedImage> mapOfImages;
	private Map<Colors, Integer> pieces;
	private Controller controller;
	private JButton[][] boardSwing;
	private JScrollPane scrollBoard;
	private List<JLabel> jlabelPlayers;
	private JLabel myColor;
	private JButton rotateButton;
	private JButton passButton;
	private JButton reconnectButton;
	private TilePanel currentTile;
	private TilePanel cpyOfcurrentTile;
	private JLabel error;
	private Box verticalLabel;
	private int minY;
	private int minX;
	private int maxX;
	private int maxY;
	private JLabel colorCurrentPlayer;
	private Colors colorOfPlayer;
	private static final int HEIGHT_MIN = 800;
	private static final int WIDTH_MIN = 600;
	
	/**
	 * Costruttore con parametri il controller e il titolo della finestra
	 * @param title
	 * @param controller
	 */
	public SwingBoard(String title, Controller controller){
		super(title);
		this.controller = controller;
		mapOfImages = new HashMap<Coordinates,BufferedImage>();
		pieces = new HashMap<Colors, Integer>();
		minY = -1;
		minX = -1;
		maxX = 1;
		maxY = 1;
		rotateButton = new JButton("rotate");
		rotateButton.addActionListener(new RotateEvent(controller));
		passButton = new JButton("pass");
		passButton.addActionListener(new PassEvent(controller));
		reconnectButton = new JButton("reconnect");
		reconnectButton.addActionListener(new ReconnectEvent(controller));
		boardSwing = new JButton[Math.abs(maxY-minY)+1][Math.abs(maxX-minX)+1];
		jlabelPlayers = new ArrayList<JLabel>();
		verticalLabel = Box.createVerticalBox();
		cpyOfcurrentTile = new TilePanel("src/main/java/data/tile-a.png");
		currentTile = new TilePanel("src/main/java/data/tile-a.png");
		error = new JLabel("                                       ");
		this.setPreferredSize(new Dimension(1000, 750));
		this.setMinimumSize(new Dimension(HEIGHT_MIN, WIDTH_MIN));
		this.pack();
	}
	
	/**
	 * Metodo per aggiungere l'immagine della tessera attuale nel tabellone di bottoni
	 * @param coordTile
	 */
	public void putTileToBoard(CoordinatesTile coordTile){
		int x, y;		
		
		x = coordTile.getCoordinates().getX();
		y = -coordTile.getCoordinates().getY();
		if(x<0){
			x -= 1;
		}
		else{
			x += 1;
		}
		if(y<0){
			y -= 1;
		}
		else{
			y += 1;
		}
		updateLimits(x, y);
		int boardY = -coordTile.getCoordinates().getY()+Math.abs(minY);
		int boardX = coordTile.getCoordinates().getX()+Math.abs(minX);
	    x = coordTile.getCoordinates().getX();
	    y = coordTile.getCoordinates().getY();
		boardSwing[boardY][boardX] = new JButton();
		boardSwing[boardY][boardX].setIcon(new ImageIcon(cpyOfcurrentTile.bufferedImage));
		mapOfImages.put(new Coordinates(x, y), currentTile.bufferedImage);
		boardSwing[boardY][boardX].addMouseListener(new AddPieceEvent(controller));
		if(boardSwing[boardY+1][boardX] == null){
			boardSwing[boardY+1][boardX] = new JButton();
			boardSwing[boardY+1][boardX].addActionListener(new AddTileEvent(x,y-1,controller));
		}
		if(boardSwing[boardY][boardX+1] == null){
			boardSwing[boardY][boardX+1] = new JButton();
			boardSwing[boardY][boardX+1].addActionListener(new AddTileEvent(x+1,y,controller));
		}
		if(boardSwing[boardY-1][boardX] == null){
			boardSwing[boardY-1][boardX] = new JButton();
			boardSwing[boardY-1][boardX].addActionListener(new AddTileEvent(x,y+1,controller));
		}
		if(boardSwing[boardY][boardX-1] == null){
			boardSwing[boardY][boardX-1] = new JButton();
			boardSwing[boardY][boardX-1].addActionListener(new AddTileEvent(x-1,y,controller));
		}
		
		currentTile = new TilePanel("src/main/java/data/card-back.png");
		error.setText("                                       ");
	}
	
	/**
	 * Metodo per fare il reset della tessera quando si tolgono le pedine
	 * @param coordTile
	 */
	public void resetTileToBoard(CoordinatesTile coordTile, boolean leftPlayer){
		int x = coordTile.getCoordinates().getX();
		int y = coordTile.getCoordinates().getY();
		int boardY = -coordTile.getCoordinates().getY()+Math.abs(minY);
		int boardX = coordTile.getCoordinates().getX()+Math.abs(minX);
		boardSwing[boardY][boardX].setIcon(new ImageIcon(mapOfImages.get(new Coordinates(x, y))));
		if(!leftPlayer){
			Integer numOfPieces = pieces.get(colorOfPlayer);
			pieces.put(colorOfPlayer, ++numOfPieces);
		}
	}
	
	/**
	 * Metodo per visualizzare una pedina nella tessera passata per parametro 
	 * @param coordTile
	 * @param direction
	 */
	public void putPieceToTile(CoordinatesTile coordTile){
		int boardX = coordTile.getCoordinates().getX()+Math.abs(minX);
		int boardY = -coordTile.getCoordinates().getY()+Math.abs(minY);
		for(Direction dir : coordTile.getDirectionOfPiece()){
			cpyOfcurrentTile.addIcon(dir, coordTile.getPieceColorByDirection(dir).convertToColor());
			boardSwing[boardY][boardX].removeAll();
			boardSwing[boardY][boardX].setIcon(new ImageIcon(cpyOfcurrentTile.bufferedImage));
		}
		Integer numOfPieces = pieces.get(colorOfPlayer);
		pieces.put(colorOfPlayer, --numOfPieces);
		error.setText("                                       ");
	}
	
	private JPanel buildContent(){
		JPanel panel = new JPanel(new BorderLayout());
		JPanel board;
		board = new JPanel(new GridLayout(Math.abs(maxY-minY)+1, Math.abs(maxX-minX)+1));
		for (int i = 0; i< boardSwing.length; i++) {
			for (int j = 0; j < boardSwing[i].length; j++) {
				if(boardSwing[i][j] != null){
					boardSwing[i][j].setBackground(Color.LIGHT_GRAY);
				    boardSwing[i][j].setMargin(new Insets(-6, -6, -6, -6));
					board.add(boardSwing[i][j]);
				}
				else{
					JPanel temp = new JPanel();
					temp.setSize(80, 80);
					board.add(temp);
				}
			}
		}
		
		board.setSize(80*(Math.abs(maxY-minY)+1),80*(Math.abs(maxX-minX)+1));
		
		JPanel ui = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		ui.add(board, c);
		ui.setBorder(BorderFactory.createTitledBorder("Board"));
		scrollBoard = new JScrollPane(ui);
		
		verticalLabel.removeAll();
		for (JLabel labelPlayer : jlabelPlayers) {
			verticalLabel.add(labelPlayer);
		}
		Box points = Box.createHorizontalBox();
		points.add(verticalLabel);
		points.setBorder(BorderFactory.createTitledBorder("Points"));
		
		Box players = Box.createVerticalBox();
		Box boxPlayers = Box.createHorizontalBox();
		if(myColor != null){
			players.add(myColor);
		}
		players.add(colorCurrentPlayer);
		boxPlayers.add(players);
		players.setBorder(BorderFactory.createTitledBorder("Players"));
		
		Box boxCurrentTile = Box.createHorizontalBox();
		boxCurrentTile.setMinimumSize(new Dimension(80, 80));
		boxCurrentTile.add(Box.createHorizontalStrut(25));
		boxCurrentTile.add(currentTile);
		boxCurrentTile.setBorder(BorderFactory.createTitledBorder("Current Tile"));
		
		Box rotate = Box.createHorizontalBox();
		rotate.add(rotateButton);
		
		Box pass = Box.createHorizontalBox();
		pass.add(passButton);
		
		Box reconnect = Box.createHorizontalBox();
		reconnect.add(reconnectButton);
		
		Box errorMessage = Box.createHorizontalBox();
		errorMessage.add(error);
		errorMessage.setBorder(BorderFactory.createTitledBorder("Error message"));
		
		JPanel rightPanelContent = new JPanel();
		rightPanelContent.setLayout(new BoxLayout(rightPanelContent,
				BoxLayout.Y_AXIS));
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(rotate, Box.CENTER_ALIGNMENT);
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(pass, Box.CENTER_ALIGNMENT);
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(reconnect, Box.CENTER_ALIGNMENT);
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(boxPlayers, Box.CENTER_ALIGNMENT);
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(points, Box.CENTER_ALIGNMENT);
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(errorMessage, Box.CENTER_ALIGNMENT);
		
		rightPanelContent.add(Box.createVerticalStrut(10));
		rightPanelContent.add(boxCurrentTile, Box.RIGHT_ALIGNMENT);
		
		rightPanelContent.setBorder(BorderFactory.createTitledBorder("Menu"));
		
		panel.add(scrollBoard, BorderLayout.CENTER);

		panel.add(rightPanelContent, BorderLayout.EAST);
		
		return panel;
	}

	/**
	 * Metodo per visualizzare il contenuto aggiornato del tabellone
	 */
	public void drawBoard(){
		setContentPane(buildContent());
		pack();
	}
	
	/**
	 * Metodo per visualizzare l'immagine della tessera corrente
	 * @param coordTile
	 */
	public void drawTile(CoordinatesTile coordTile){
		currentTile = new TilePanel(coordTile);
		cpyOfcurrentTile = new TilePanel(coordTile);
		error.setText("                                       ");
	}
	
	/**
	 * Metodo per ruotare l'immagine della tessera corrente
	 */
	public void rotateTile(){
		currentTile.rotate();
		cpyOfcurrentTile.rotate();
		error.setText("                                       ");
	}
	
	/**
	 * Metodo per aggiungere la visualizzazione dei giocatori della partita
	 * @param obj
	 */
	public void addPlayers(UpdateInitFirstObject obj){
		for(Colors col: obj.getPlayerList()){
			pieces.put(col, 7);
		    String text = col.toString()+" points: "+"0 , pieces:"+pieces.get(col);
		    jlabelPlayers.add(new JLabel(text));
		}
	}
	
	/**
	 * Metodo per aggiornare la visualizzazione dei punti dei giocatori
	 * @param points
	 */
	public void updatePlayers(Map<Colors, Integer> points){
		jlabelPlayers.clear();
		for(Entry<Colors, Integer> col: points.entrySet()){
		    String text = "Player : "+col.getKey().toString()+" points: "+col.getValue()+" , pieces:"+ pieces.get(col.getKey());
		    jlabelPlayers.add(new JLabel(text));
		}
	}
	
	private void updateLimits(int x, int y){

		int oldMaxX = maxX;
		int oldMaxY = maxY;
		int oldMinX = minX;
		int oldMinY = minY;

		if(x>maxX){
			maxX = x;
		}
		else{
			if(x<minX){
				minX = x;
			}
		}

		if(y>maxY){
			maxY = y;
		}
		else{
			if(y<minY){
				minY = y;
			}
		}

		if(x == minX || x == maxX || y == minY || y == maxY){
			JButton[][] temp = new JButton[Math.abs(maxY-minY)+1][Math.abs(maxX-minX)+1]; 

			int difMinY = Math.abs(minY)-Math.abs(oldMinY);
			int difMaxY = Math.abs(maxY)-Math.abs(oldMaxY);
			int difMinX = Math.abs(minX)-Math.abs(oldMinX);
			int difMaxX = Math.abs(maxX)-Math.abs(oldMaxX);

			for(int i = difMinY; i< temp.length-difMaxY; i++){
				for(int j = difMinX; j< temp[i].length-difMaxX; j++){
					temp[i][j] = boardSwing[i-difMinY][j-difMinX];
				}
			}

			boardSwing = temp;
		}

	}
	
	/**
	 *  Metodo per aggiornare a schermo gli errori
	 * @param e
	 */
	public void updateError(Exception e){
		error.setText(e.toString());
	}
    
	/**
	 * metodo per aggiornare il colore assegnato alla view
	 * @param yourColor
	 */
	public void updateMyColor(Colors yourColor) {
		myColor = new JLabel("Your color : "+yourColor.toString());
		
	}
    
	/**
	 * metodo per aggiornare il colore dell'attuale giocatore
	 * @param playerColor
	 */
	public void updateCurrentPlayer(Colors playerColor) {
		colorCurrentPlayer = new JLabel("Current Player : "+playerColor.toString());
		colorOfPlayer = playerColor;
	}
    
	/**
	 * Metodo per resettare tutte le tessere allo stato originale senza pedine.
	 */
	public void resetAll() {
		for(Entry<Coordinates, BufferedImage> element : mapOfImages.entrySet()){
			int boardY = -element.getKey().getY()+Math.abs(minY);
			int boardX = element.getKey().getX()+Math.abs(minX);
			boardSwing[boardY][boardX].setIcon(new ImageIcon(element.getValue()));
		}
	}
}