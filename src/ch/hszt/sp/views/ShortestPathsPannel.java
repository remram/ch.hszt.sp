package ch.hszt.sp.views;

import javax.swing.*;

import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

import java.awt.*;
import java.io.IOError;
import java.util.HashMap;
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
	private Map<String, Integer> selectedNodes;
	private Image img;
	private ShowEdge she;
	
	//Der Konstruktor initiiert die Listen die zur Darstellung der Knoten und Kanten benoetigt werden.
	public ShortestPathsPannel(List<CNode> cnlist, Map<Integer, CNode> lnode, Map<Integer, CEdge>ledge){
		this.cnlist = (ArrayList<CNode>) cnlist;
		this.ledg = ledge;
		this.lnod = lnode;
		this.she = new ShowEdge(lnod, ledg);
		this.selectedNodes = new HashMap<String, Integer>();
		this.selectedNodes.put("start", 1);
		this.selectedNodes.put("target", 15);
	}

	//paintComponent stellt die Komponenten auf dem Pannel dar.
	public void paintComponent(Graphics g){
		addBackground(g);
		addEdge(g);
		addNode(g);
	}
	
	//Gibt die Map mit den Gewaehlten Knoten an den Aufrufer zurueck.
	public Map<String, Integer> getSelectedNodes(){
		return selectedNodes;
	}
	
	//Setzt in die snMap den Startknoten
	public void setStartNode(Integer from){
		selectedNodes.put("Start", from);
	}
	
	//Setzt in die snMap den Zielknoten.
	public void setTargetNode(Integer to){
		selectedNodes.put("Target", to);
	}
	
	//Mit dieser Methode kann ein Kartenausschnitt geladen werden.
	public void addBackground(Graphics g){
		try{
			this.img = new ImageIcon("img/osm-zurich.png").getImage();
			}catch(IOError err){
				err.getCause();
			}
		g.drawImage(img, 0,0, null);
	}
	
	//Methode, die die ShowEdge Methode showEdges aufruft -> zeichnet die Kanten.
	public void addEdge(Graphics g){
		try{
		she.showEdges(g);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//Methode, die die ShowNode Klasse instanziert und alle Knoten die in der cnlist sind auf der Karte darstellt.
	public void addNode(Graphics g){
		try {
			for (CNode cnode : this.cnlist) {
				ShowNode shn = new ShowNode(cnode.getxCoordinate(), cnode.getyCoordinate(), cnode.getId(), cnode.getName());
				//Hier bekommt der knoten den Listener.
				shn.paintNode(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Diese Methode zeichnet die besuchten Kanten und setzt die Knoten erneut ueber die Kanten.
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