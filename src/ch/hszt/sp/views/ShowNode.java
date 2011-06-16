package ch.hszt.sp.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

/**
 * 
 * @author miro
 * @version 1.0
 * Darstellung der Nodes im Pannel
 **/
class ShowNode extends AbstractButton
{
	private static final long serialVersionUID = 1L;
	private int x, y, sizx, sizy, id;
	private String nodeName;
	
	//Konstruktor erwartet die jeweilige Position der Knoten. Soll auch die Namen der Nodes anzeigen.
	public ShowNode(int x, int y, int id, String nodeName){
		this.x = x;
		this.y = y;
		this.id = id;
		sizx = 23; 
		sizy = 23;
		this.nodeName = nodeName;
		//enableInputMethods(true);
		//setEnabled(true);
	}
	//Setzt die Knoten auf den Panel.
	public void paintComponent(Graphics g){
		paintNode(g);
	}
	
	//Hier werden die Knoten gezeichnet momentan Rot
	public void paintNode(Graphics g){
		g.setColor(Color.red);
		g.fillOval(x-5, y-5, sizx, sizy);
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 9));
		g.drawString(nodeName, x-3, y+10);
	}
	
	//Gibt die Id des Knotens zurueck.
	public int getId(){
		return id;
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(sizx, sizy);
	}
	
	public Dimension getMinimumSize(){
		return new Dimension(sizx-5, sizy-5);
	}
	
	public Dimension getMaximumSize(){
		return new Dimension(sizx+5, sizy+5);
	}
	public void addActionListener(ActionListener arg0){
		this.fireActionPerformed(null);
	}
}