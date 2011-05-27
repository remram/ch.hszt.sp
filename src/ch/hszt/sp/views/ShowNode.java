package ch.hszt.sp.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * 
 * @author miro
 * @version 1.0
 * Darstellung der Nodes im Pannel
 **/
class ShowNode extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private int x, y, sizx, sizy;
	private String nodeName;
	
	//Konstruktor erwartet die jeweilige Position der Knoten. Soll auch die Namen der Nodes anzeigen.
	public ShowNode(int x, int y, int id, String nodeName){
		this.x = x;
		this.y = y;
		sizx = 10; 
		sizy = 10;
		this.nodeName = nodeName;
	}
	//Hier werden die Knoten gezeichnet momentan Rot
	public void paintNode(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(x, y, sizx, sizy);
		g.setColor(Color.black);
		g.drawString(nodeName + ": ", x-25, y+11);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Hallo");
	}
}