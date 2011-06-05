package ch.hszt.sp.views;
import javax.swing.*;

import ch.hszt.sp.controllers.CShortestPathController;
import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class CShortestPathView implements IShortestPathListener, IShortestPathGui, Observer{
	
	/**
	 * Die Klasse CShortestPathView sorgt fuer das GUI in unserer Applikation
	 * Sie reagiert auf die Events die in der GUI ausgeloest werden.
	 */
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel mapPanel;
	private Observable observer;
	private ArrayList<CNode> cnlist;
	private ArrayList<CEdge> cEdge;
	private LinkedList<CNode> uNode;
	private Map<Integer, CNode> lnode;
	private Map<Integer, CEdge> ledge;
	private CShortestPathController spc;

	
	public CShortestPathView(Observable obs){
		this.observer = obs;
		observer.addObserver(this);
		if(obs instanceof CShortestPathController){
			this.spc = (CShortestPathController) obs;
		}		
	}
	
	//Die Methode viewGUI verpasst dem Frame zwei Panels die dann alle GUI Komponenten enthalten.
	public void viewGUI(){
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton searchPathBtn = new JButton("Search Path");
		/*try{
		Image img = new ImageIcon("img/osm-zurich.png").getImage();
		}catch(IOError err){
			err.getCause();
		}*/
		searchPathBtn.setMaximumSize(new Dimension(170, 50));

		searchPathBtn.addActionListener(new ShowPathListener());
		
		ShortestPathsPannel mapPanel = new ShortestPathsPannel(this.cnlist, this.cEdge, this.uNode, this.lnode, this.ledge);
		this.mapPanel = mapPanel;
		
		JPanel bPanel = new JPanel();
		
		//BoxLayout um die Buttons untereinander anzuordnen
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.PAGE_AXIS));
		bPanel.add(searchPathBtn);
		
		frame.add(BorderLayout.EAST, bPanel);
		frame.add(BorderLayout.CENTER, mapPanel);
		
		frame.setResizable(false);
		frame.setSize(848, 615);
		frame.setVisible(true);
		
	}
	
	//Diese Klasse wird das Panel bieten welches die Buttons enthaelt die in der App benoetigt werden
	class ButtonPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g){
			g.setColor(Color.green);
			
		}
		
	}
	public Map<Integer, CNode> getSelectedNode(int in){
		
		return this.lnode;
	}

	@Override
	public void getNodePosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getEdge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNodePositions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEdges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShortestPath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calcDistanceBtn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewPathBtn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectNode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectNodeBtn() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(Observable obs, Object alf){
		if(obs instanceof CShortestPathController){
			CShortestPathController csc = (CShortestPathController) obs;
			this.cnlist = csc.getNodesAsList();
			this.cEdge = csc.getEdgesAsList();
			this.lnode = csc.getNodesAsMap();
			this.ledge = csc.getEdgesAsMap();
			//this.uNode = csc.getPath(0,4);
		}
		//viewGUI();
	}
		
	class ShowPathListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				uNode = spc.getPath(5, 21);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//mapPanel.getGraphics().draw3DRect(50, 50, 100, 100, false);
			spc.notifyObserver();
			
		}
	}
		
	class CalcDistanceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			
		}
		
	}
}