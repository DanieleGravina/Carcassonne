package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import common.CoordinatesTile;
import common.Direction;

/**
 * Classe JPanel per rappresentare le tessere
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
@SuppressWarnings("serial")
public class TilePanel extends JPanel{
	
	public BufferedImage bufferedImage;
	private int width;
	private int height;
	private String fileString;
	
	/**
	 * Costruttore per creare una immagine della tessera 
	 * a partire dalla sua rappresentazione
	 * @param coordTile
	 */
	public TilePanel(CoordinatesTile coordTile) {
		this(getPath(coordTile));
	}
	
	/**
	 * Costruttore per creare l'immagine della tessera con 
	 * l'immagine contenuta nel file passato per parametro
	 * @param filePath
	 */
	public TilePanel(String filePath){
		width = 80;
		height = 80;
		this.fileString = filePath;
		File fileImg = new File(filePath);
		try {
			bufferedImage = ImageIO.read(fileImg);
		} catch (IOException e) {
			bufferedImage = null;
		}
		setVisible(true);
	}
	
	/**
	 * Costruttore per creare l'immagine della tessera con 
	 * l'immagine passata per parametro
	 * @param img
	 */
	public TilePanel(BufferedImage img){
		width = 80;
		height = 80;
		this.bufferedImage = img;
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, width, height, null);
	}
	
	/**
	 * Metodo per ruotare la tessera
	 */	
	public void rotate()
	{
		double degree = Math.PI/2;
		double sin = Math.sin(degree);

		AffineTransform tx = new AffineTransform();
		tx.rotate(degree, this.bufferedImage.getWidth() / (double)2, this.bufferedImage.getHeight() / (double)2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Rectangle rect = op.getBounds2D(this.bufferedImage).getBounds();
		tx.translate(sin * rect.getX(), -sin * rect.getY());
		op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		this.bufferedImage = op.filter(this.bufferedImage, op.createCompatibleDestImage(this.bufferedImage, null) );


		this.setBounds( getX(), getY(), this.bufferedImage.getWidth(), this.bufferedImage.getHeight()  );
		this.width = this.bufferedImage.getWidth();
		this.height = this.bufferedImage.getHeight();
		
	}

	 /**
	  * Metodo per disegnare in una tessera una pedina nella direzione e nel colore voluto 
	  * @param dir
	  * @param clr
	  */
	 public void addIcon(Direction dir, Color clr){
		 
		 Graphics2D g = bufferedImage.createGraphics();
		 BufferedImage imgPiece;
		 File fileImg = new File("src/main/java/data/smallfollower_0.png");
	  	 try {
			imgPiece = ImageIO.read(fileImg);

			 Graphics2D g1 = imgPiece.createGraphics();	
			 for (int x = 0; x < imgPiece.getWidth(); x++) {
				 for (int y = 0; y < imgPiece.getHeight(); y++) {
					 int color = imgPiece.getRGB(x,y);
					 if(color == -65281){
						 g1.setColor(clr);
						 g1.fillRect(x,y,1,1);
					 }
				 }
			 }
		 g.drawImage(imgPiece, getX(dir), getY(dir), 20, 20, null);
		 }
		 catch (IOException e) {
		 }
		 
		 
	 }
	 
	 private int getX(Direction dir){
		 if(dir.equals(Direction.NORTH) || dir.equals(Direction.SOUTH)){
			 return 30;
		 }
		 else{
			 if(dir.equals(Direction.WEST)){
				 return 5;
			 }
			 else{
				 return 55;
			 }
		 }
	 }
	 
	 private int getY(Direction dir){
		 if(dir.equals(Direction.WEST) || dir.equals(Direction.EAST)){
			 return 25;
		 }
		 else{
			 if(dir.equals(Direction.NORTH)){
				 return 5;
			 }
			 else{
				 return 50;
			 }
		 }
	 }
	 
	 private static String getPath(CoordinatesTile coordTile){
		 
		 BufferedReader in = null;
		 String input; 
		 String tile;
		 
		 tile = coordTile.toString();
		 
		 try {
			 try{
			 in = new BufferedReader(new FileReader("src/main/java/data/pathForTiles.txt"));
			 while ((input = in.readLine()) != null ) {
				 if(input.contains(tile)){
					 String[] inputStrings = input.split(" ");
					 return inputStrings[10];
				 }
			 }
			 }
			 finally{
				 in.close();
			 }
		 } catch (FileNotFoundException e) {
			 System.out.print("Errore nella lettura del file pathForTiles");
			 return "";
		 } catch (IOException e) {
			 System.out.print("Errore nella lettura del file");
		 } catch(NullPointerException e){
			 System.out.println("Errore nella inizializzazione del BufferedReader");
		 }
		 
		 
		 return "";
	 }

}
