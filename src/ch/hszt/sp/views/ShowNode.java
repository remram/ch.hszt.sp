package ch.hszt.sp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

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
	private EventListenerList nodeListeners = new EventListenerList();
	
	//Konstruktor erwartet die jeweilige Position der Knoten. Soll auch die Namen der Nodes anzeigen.
	public ShowNode(int x, int y, int id, String nodeName){
		this.x = x;
		this.y = y;
		sizx = 23; 
		sizy = 23;
		this.nodeName = nodeName;
	}
	//Hier werden die Knoten gezeichnet momentan Rot
	public void paintNode(Graphics g){
		g.setColor(Color.red);
		g.fillOval(x-5, y-5, sizx, sizy);
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 9));
		g.drawString(nodeName, x-3, y+10);
		
	}
	
	public void addnodeListener(nodeListener listener){
		nodeListeners.add(nodeListener.class, listener);
	}
	
	public void removenodeListener(nodeListener listener){
		nodeListeners.remove(nodeListener.class, listener);
	}
	
	protected void fireNode(ShowNode nEvent) 
	{
	     Object[] listeners = nodeListeners.getListenerList();
	     // loop through each listener and pass on the event if needed
	     int numListeners = listeners.length;
	     for (int i = 0; i < numListeners; i+=2) 
	     {
	          if (listeners[i]==nodeListener.class) 
	          {
	               // pass the event to the listeners event dispatch method
	                ((nodeListener)listeners[i+1]).dispatchNode("Hallo");
	          }            
	     }
	}

	class nodeListener implements EventListener{

		public void dispatchNode(String a) {
			// TODO Auto-generated method stub
			System.out.println(a);
		}
		
	}
}