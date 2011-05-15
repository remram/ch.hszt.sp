package ch.hszt.sp.views;

import javax.swing.*;


import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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
	private LinkedList<CNode> uNode;
	private Map<Integer, CEdge> ledg;
	private Map<Integer, CNode> lnod;
	//private CNode cnoda, cnodb, unoda, unodb;
	/*
	//Der Konstruktor kann ein img path annehmen und dieses im Pannel darstellen.
	public ShortestPathsPannel(List<CNode> cnlist, List<CEdge> cEdge, List<CNode> uNode){
		this.cnlist = (ArrayList<CNode>) cnlist;		
		this.cEdge = (ArrayList<CEdge>) cEdge;
		//this.uNode = (LinkedList<CNode>) uNode;
	}*/
	
	public ShortestPathsPannel(List<CNode> cnlist, List<CEdge> cEdge, List<CNode> uNode, Map<Integer, CNode> lnode, Map<Integer, CEdge>ledge){
		//Map<Integer, CEdge> ledg = new HashMap<Integer, CEdge>();
		//Map<Integer, CNode> lnod =  new HashMap<Integer, CNode>();
		this.cnlist = (ArrayList<CNode>) cnlist;		
		this.cEdge = (ArrayList<CEdge>) cEdge;
		this.ledg = ledge;
		this.lnod = lnode;
	}

	//paintComponent stellt die Komponenten auf dem Pannel dar.
	public void paintComponent(Graphics g){
		try {
			for (CNode cnode : this.cnlist) {
				ShowNode shn = new ShowNode(cnode.getxCoordinate(), cnode.getyCoordinate(), cnode.getId(), cnode.getName());
				shn.paintNode(g);/*
					for (CEdge ced : this.cEdge){
						ShowNode sho = new ShowNode(unod.getxCoordinate(), unod.getyCoordinate(), unod.getId(), unod.getName());
						sho.paintNode(g);
					}
				}
				ShowEdge sha = new ShowEdge(cnlist);
			}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		
		for (CEdge cedg : this.cEdge){
			for(CNode cnodq : this.cnlist){
				if(cedg.getStartNode() == cnodq.getId()){
					this.cnoda = cnodq;
				}
				if(cedg.getTargetNode() == cnodq.getId()){
					this.cnodb = cnodq;
				}
		}
		//ShowEdge she = new ShowEdge(cnoda.getxCoordinate()+5, cnoda.getyCoordinate()+5, cnodb.getxCoordinate()+5, cnodb.getyCoordinate()+5, cedg);
		ShowEdge she = new ShowEdge(lnod, ledg);
		she.paintComponent(g);
*/
		ShowEdge she = new ShowEdge(lnod, ledg);
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
		//private int ax, ay, bx, by, weight;
		private Map<Integer,CNode> lnod;
		private Map<Integer,CEdge> ledg;
		
		/*public ShowEdge(int ax, int ay, int bx, int by, CEdge cedge){
			this.weight = (int) cedge.getWeight();
			this.ax = ax;
			this.ay = ay;
			this.bx = bx;
			this.by = by;
			
		}
		*/
		public ShowEdge(Map<Integer, CNode> lnod, Map<Integer, CEdge> ledg){
			//lnod = new HashMap<Integer, CNode>();
			//ledg = new HashMap<Integer, CEdge>();
			this.lnod = lnod;
			this.ledg = ledg;
		}
		/*public void paintComponent(Graphics g){
			String weight = ""+ this.weight;
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(stroke);
	        g2d.setColor(Color.BLUE);
	        g2d.drawLine(ax, ay, bx, by);	
	        g2d.setColor(Color.gray);
	        //g2d.drawString(weight, ax, bx+50);
			}*/
		public void paintComponent(Graphics g){
			System.out.println(ledg.get(1));
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(stroke);
	        g2d.setColor(Color.BLUE);
	        Iterator ite = ledg.keySet().iterator();
	        //Iterator itn =  lnod.keySet().iterator();
	        while(ite.hasNext()) { 
	        	Object ekey = ite.next(); 
	        	CEdge eval = ledg.get(ekey);
	        	//CNode ckey = (CNode) itn.next();
	        	//lnod.get(eval.getStartNode()).getxCoordinate();
	        	System.out.println(eval.getStartNode() + "<- Start : Target ->" +eval.getTargetNode());
	        	g2d.drawLine(lnod.get(eval.getStartNode()).getxCoordinate()+5, lnod.get(eval.getStartNode()).getyCoordinate()+5, lnod.get(eval.getTargetNode()).getxCoordinate()+5,lnod.get(eval.getTargetNode()).getyCoordinate()+5);
	        	}
	        g2d.setColor(Color.gray);
			}
}

