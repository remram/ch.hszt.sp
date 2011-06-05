package ch.hszt.sp.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;

import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

public class ShowEdge extends JPanel
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
	private LinkedList<CNode> unod;
	
	public ShowEdge(Map<Integer, CNode> lnod, Map<Integer, CEdge> ledg){
		this.lnod = lnod;
		this.ledg = ledg;
		//this.unod = unod;
	}
	
	public ShowEdge(Map<Integer, CNode> lnod, Map<Integer, CEdge> ledg, LinkedList<CNode> unod){
		this.lnod = lnod;
		this.ledg = ledg;
		this.unod = unod;
	}
	
	public void paintComponent(Graphics g){
		showEdges(g);
		try{
		showUsedEdges(g);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void showEdges(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(stroke);
        g2d.setColor(Color.BLUE);
        Iterator<Integer> ite = ledg.keySet().iterator();
        while(ite.hasNext()) { 
        	Object ekey = ite.next(); 
        	CEdge eval = ledg.get(ekey);
        	g2d.setStroke(new BasicStroke(4));
        	g2d.drawLine(lnod.get(eval.getStartNode()).getxCoordinate()+5, lnod.get(eval.getStartNode()).getyCoordinate()+5, lnod.get(eval.getTargetNode()).getxCoordinate()+5,lnod.get(eval.getTargetNode()).getyCoordinate()+5);
        	}
        
       /* try{
        	showUsedEdges(g);
        }catch(Exception ex){
        	//ex.printStackTrace();
        }*/
	}
    public void showUsedEdges(Graphics g) throws InterruptedException{	
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setStroke(stroke);
    	g2d.setColor(Color.GREEN);
    	Iterator<Integer> ite = ledg.keySet().iterator();
    	CNode start;
    	CNode target;
    	while(ite.hasNext()){
    		Object key = ite.next();
    		CEdge eval = ledg.get(key);    		
    		for(int i = 0; i < unod.size()-1; i++){
    			System.out.println(unod.get(i));
    			start = lnod.get(unod.get(i).getId());
	        	target = lnod.get(unod.get(i+1).getId());
				if(eval.getStartNode() == start.getId() && eval.getTargetNode() == target.getId()){
					g2d.setStroke(new BasicStroke(5));
					g2d.drawLine(lnod.get(unod.get(i).getId()).getxCoordinate()+5, lnod.get(unod.get(i).getId()).getyCoordinate()+5,
							lnod.get(unod.get(i+1).getId()).getxCoordinate()+5, lnod.get(unod.get(i+1).getId()).getyCoordinate()+5);
	        	}
			}
    	}
    }
}