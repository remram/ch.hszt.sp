package ch.hszt.sp.views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class ShortestPathsPannel extends JPanel implements ImageObserver{

	private int x;
	private int y;
	private Image img;
	private static final long serialVersionUID = 1L;
	
	//Falls keine x,y koordinaten uebergeben werden setzt dieser Konstruktor sie auf 50, 50 px
	public ShortestPathsPannel(){
		this.x = 50;
		this.y = 50;
	}
	
	public ShortestPathsPannel(int x, int y,ShortestPathsPannel obs){
		this.x = x;
		this.y = y;
	}
	//Hier wird schlussendlich die Karte geladen werden und im Panel dargestellt.
	public void paintComponent(Graphics g){
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.fillRect(x, y, 33, 33);
		img = new ImageIcon("img/testmap.jpg").getImage();
		g.drawImage(img,0,0,this);
	}
	
	public int returnWidth(){
		return img.getWidth(null);
	}
	
	public int returnHeight(){
		return img.getHeight(null);
	}

}
