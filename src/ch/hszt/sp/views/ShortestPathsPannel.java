package ch.hszt.sp.views;

import javax.swing.*;


import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

import java.awt.*;
import java.io.IOError;
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

	private static final long serialVersionUID = 1L;
	private ArrayList<CNode> cnlist;
	private Map<Integer, CEdge> ledg;
	private Map<Integer, CNode> lnod;
	private Image img;
	private ShowEdge she;
	
	
	public ShortestPathsPannel(List<CNode> cnlist, Map<Integer, CNode> lnode, Map<Integer, CEdge>ledge){
		this.cnlist = (ArrayList<CNode>) cnlist;
		this.ledg = ledge;
		this.lnod = lnode;
		this.she = new ShowEdge(lnod, ledg);
	}

	//paintComponent stellt die Komponenten auf dem Pannel dar.
	public void paintComponent(Graphics g){
		
		addBackground(g);
		addEdge(g);
		addNode(g);
		
	}
	
	//Mit dieser Methode kann ein Kartenausschnitt geladen werden.
	public void addBackground(Graphics g){System.out.println();
		try{
			this.img = new ImageIcon("img/osm-zurich.png").getImage();
			}catch(IOError err){
				err.getCause();
			}
		g.drawImage(img, 0,0, null);
	}
	
	//Methode die die Kanten auf der Karte darstellt.
	public void addEdge(Graphics g){
		
		try{
		she.showEdges(g);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void addNode(Graphics g){
		try {
			for (CNode cnode : this.cnlist) {
				ShowNode shn = new ShowNode(cnode.getxCoordinate(), cnode.getyCoordinate(), cnode.getId(), cnode.getName());
				shn.paintNode(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addUEdge(Graphics g, LinkedList<CNode> unod){
		she.addUNode(unod);
		try{
			she.showUsedEdges(g);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		addNode(g);
	}
}
