package ch.hszt.sp.views;

import javax.swing.*;


import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

import java.awt.*;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Mirković Miroslav
 * @class ShortestPathsPannel
 * @since 27.04.11
 * @version 1.0
 * ShortestPathsPannel dient zur Darstellung der Nodes und der Edges in dieser Applikation.
 * 
 **/
public class ShortestPathsPannel extends JPanel{

	//private Image img;
	private static final long serialVersionUID = 1L;
	private ArrayList<CNode> cnlist;
	private ArrayList<CEdge> cEdge;
	
	
	//Der Konstruktor kann ein img path annehmen und dieses im Pannel darstellen.
	public ShortestPathsPannel(List<CNode> cnlist, List<CEdge> cEdge){
		this.cnlist = (ArrayList<CNode>) cnlist;
		
		this.cEdge = (ArrayList<CEdge>) cEdge;
	}

	//paintComponent stellt die Komponenten auf dem Pannel dar.
	public void paintComponent(Graphics g){
		//g.drawImage(img,0,0,this);
		
		
		
		try {
			for (CNode cnode : this.cnlist) {
				ShowNode shn = new ShowNode(cnode.getxCoordinate(), cnode.getyCoordinate(), cnode.getId(), cnode.getName());
				shn.paintNode(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			for(CEdge cedg : this.cEdge){
				cEdge.add(cedg);
			}
			for (CNode cnod : this.cnlist){
				cnlist.add(cnod);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		int i;
		
		for (i=0; i < (cnlist.size()-1); i++){
			CEdge cedg = cEdge.get(i);
			CNode cnod = cnlist.get(i);
			System.out.println(cedg.getId() + " ist die Kante mit folgendem Startknoten: "+cedg.getStartNode() +" : "+cedg.getTargetNode());
			CNode cnoda = cnlist.get(i+1);
			ShowEdge she = new ShowEdge(cnod.getxCoordinate()+5, cnod.getyCoordinate()+5, cnoda.getxCoordinate()+5, cnoda.getyCoordinate()+5);
			she.paintComponent(g);
		}
	}
	
	/**
	 * 
	 * @author miro
	 * @version 1.0
	 * Darstellung der Nodes im Pannel
	 **/
	class ShowNode extends JPanel
	{
		/**
		 * 
		 */
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
			g.drawString(nodeName + ": ", x-30, y+10);
		}
		//Soll die Position des Knotens anzeigen
		public int returnposition(){
			return x;
		}
	}
	
	class ShowEdge extends JPanel
	{
		/**
		 * @author miro
		 * @version 1.0
		 * Die Klasse ShowEdge ist fuer die Dartsellung der Kanten zustaendig.
		 * 
		 * */
		private static final long serialVersionUID = 1L;
		private final Stroke stroke = new BasicStroke(2.0F);
		private int ax, ay, bx, by;
		public ShowEdge(int ax, int ay, int bx, int by){
			this.ax = ax;
			this.ay = ay;
			this.bx = bx;
			this.by = by;
			
		}
		
		public void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(stroke);
	        g2d.setColor(Color.cyan);
	        g2d.drawLine(ax, ay, bx, by);
			
			}
		}

}
