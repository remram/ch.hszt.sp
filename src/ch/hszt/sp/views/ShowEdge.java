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
	 * @author Miroslav Mirkovic
	 * @version 1.0
	 * Die Klasse ShowEdge ist fuer die Dartsellung der Kanten zustaendig.
	 * 
	 * */
	private static final long serialVersionUID = 1L;
	private final Stroke stroke = new BasicStroke(2.0F);
	private Map<Integer,CNode> lnod;
	private Map<Integer,CEdge> ledg;
	private LinkedList<CNode> unod;
	
	//Hier werden die Listen mit Knoten und Kanten initialisiert.
	public ShowEdge(Map<Integer, CNode> lnod, Map<Integer, CEdge> ledg){
		this.lnod = lnod;
		this.ledg = ledg;
	}
	
	//Dieser Methode werden die besuchten Knoten uebergeben.
	public void addUNode(LinkedList<CNode> unod){
		this.unod = unod;
	}
	
	//Die Komponenten werden auf den Panel geheftet.
	public void paintComponent(Graphics g){
		showEdges(g);
		try{
		showUsedEdges(g);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//Zeigt die vorhandenen Kanten aus der XML auf dem Panel (Map).
	public void showEdges(Graphics g){
		int startx, targetx, starty, targety;
		String test = "one";
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(stroke);
        g2d.setColor(Color.BLUE);
        Iterator<Integer> ite = ledg.keySet().iterator();
        while(ite.hasNext()) { 
        	Object ekey = ite.next(); 
        	CEdge eval = ledg.get(ekey);
        	g.setColor(Color.BLUE);
        	g2d.setStroke(new BasicStroke(4));
        	
        	this.drawArrow(g2d,
        			lnod.get(eval.getStartNode()).getxCoordinate()+5, 
        			lnod.get(eval.getStartNode()).getyCoordinate()+5, 
        			lnod.get(eval.getTargetNode()).getxCoordinate()+5,
        			lnod.get(eval.getTargetNode()).getyCoordinate()+5,
        			eval.getDirectionType());
        	
        	//g2d.drawLine(lnod.get(eval.getStartNode()).getxCoordinate()+5, lnod.get(eval.getStartNode()).getyCoordinate()+5, lnod.get(eval.getTargetNode()).getxCoordinate()+5,lnod.get(eval.getTargetNode()).getyCoordinate()+5);
	        	//Falls es sich, um einweg Strasse handelt.
        		if(eval.getDirectionType().toString().equals(test)){
	        		startx  = lnod.get(eval.getStartNode()).getxCoordinate();
	        		targetx = lnod.get(eval.getTargetNode()).getxCoordinate();
	        		starty  = lnod.get(eval.getStartNode()).getyCoordinate();
	        		targety = lnod.get(eval.getTargetNode()).getyCoordinate();
	        		showEdgeDirection(g, startx, targetx, starty, targety);
	        	}
        	}
	}
	
	/**
	* This function raws an arrowed line
	* @param g the Graphics2D context to draw on
	* @param x the x location of the "tail" of the arrow
	* @param y the y location of the "tail" of the arrow
	* @param xx the x location of the "head" of the arrow
	* @param yy the y location of the "head" of the arrow
	* @param direction the type of edge direction
	*/
	private void drawArrow( Graphics2D g, int x, int y, int xx, int yy, String direction) {
		float arrowWidth = 15.0f ;
		float theta = 0.623f ;
		int[] xPoints = new int[ 3 ] ;
		int[] yPoints = new int[ 3 ] ;
		float[] vecLine = new float[ 2 ] ;
		float[] vecLeft = new float[ 2 ] ;
		float fLength;
		float th;
		float ta;
		float baseX, baseY ;
	
		xPoints[ 0 ] = xx ;
		yPoints[ 0 ] = yy ;
	
		// build the line vector
		vecLine[ 0 ] = (float)xPoints[ 0 ] - x ;
		vecLine[ 1 ] = (float)yPoints[ 0 ] - y ;
	
		// build the arrow base vector - normal to the line
		vecLeft[ 0 ] = -vecLine[ 1 ] ;
		vecLeft[ 1 ] = vecLine[ 0 ] ;
	
		// setup length parameters
		fLength = (float)Math.sqrt( vecLine[0] * vecLine[0] + vecLine[1] * vecLine[1] ) ;
		th = arrowWidth / ( 2.0f * fLength ) ;
		ta = arrowWidth / ( 2.0f * ( (float)Math.tan( theta ) / 2.0f ) * fLength ) ;
	
		// find the base of the arrow
		baseX = ( (float)xPoints[ 0 ] - ta * vecLine[0]);
		baseY = ( (float)yPoints[ 0 ] - ta * vecLine[1]);
	
		// build the points on the sides of the arrow
		xPoints[ 1 ] = (int)( baseX + th * vecLeft[0] );
		yPoints[ 1 ] = (int)( baseY + th * vecLeft[1] );
		xPoints[ 2 ] = (int)( baseX - th * vecLeft[0] );
		yPoints[ 2 ] = (int)( baseY - th * vecLeft[1] );
	
		//if direction one, than draw an arrow else not
		if(direction.equals("one")) {
			g.drawLine( x, y, (int)baseX, (int)baseY );
			g.fillPolygon( xPoints, yPoints, 3 );
		} else {
			g.drawLine( x, y, xx, yy );
		}
		
	}
	
	protected void showEdgeDirection(Graphics g, int startx, int targetx, int starty, int targety){
		int xone, yone;
		double tana;
		xone = targetx - startx;
		yone = targety - starty;
		g.setColor(Color.red);
		//g.drawOval(xone, yone, 10, 10);
		tana = Math.atan2(yone , xone);
		System.out.println(tana);
		//g.drawArc(xone, yone, 10, 10, 270, 90);
	}
	
	//Zeichnet die Besuchten Kanten auf dem Panel.
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
					this.drawArrow(g2d,
		        			lnod.get(eval.getStartNode()).getxCoordinate()+5, 
		        			lnod.get(eval.getStartNode()).getyCoordinate()+5, 
		        			lnod.get(eval.getTargetNode()).getxCoordinate()+5,
		        			lnod.get(eval.getTargetNode()).getyCoordinate()+5,
		        			eval.getDirectionType());
					/*g2d.drawLine(lnod.get(unod.get(i).getId()).getxCoordinate()+5, lnod.get(unod.get(i).getId()).getyCoordinate()+5,
							lnod.get(unod.get(i+1).getId()).getxCoordinate()+5, lnod.get(unod.get(i+1).getId()).getyCoordinate()+5);*/
					//g2d.drawString(">>>", lnod.get(unod.get(i).getId()).getxCoordinate()+100, lnod.get(unod.get(i).getId()).getyCoordinate()+50);
	        	}
			}
    	}
    }
}