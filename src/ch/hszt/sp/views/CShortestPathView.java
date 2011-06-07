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
	private ShortestPathsPannel mapPanel;
	private Observable observer;
	private ArrayList<CNode> cnlist;
	private LinkedList<CNode> uNode;
	private Map<Integer, CNode> lnode;
	private Map<Integer, CEdge> ledge;
	private CShortestPathController spc;
	private Map<String, Integer> selectedNodes;

	
	public CShortestPathView(Observable obs){
		this.observer = obs;
		observer.addObserver(this);
		if(obs instanceof CShortestPathController){
			this.spc = (CShortestPathController) obs;
		}		
	}
	
	//Die Methode viewGUI verpasst dem Frame zwei Panels die dann alle GUI Komponenten enthalten.
	public void viewGUI(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton searchPathBtn = new JButton("Search Path");
		
		searchPathBtn.setMaximumSize(new Dimension(235, 50));

		searchPathBtn.addActionListener(new ShowPathListener());
		
		ShortestPathsPannel mapPanel = new ShortestPathsPannel(this.cnlist, this.lnode, this.ledge);
		this.mapPanel = mapPanel;
		this.selectedNodes = mapPanel.getSelectedNodes();
		
		JTextArea textArea = new JTextArea();
		textArea.setMaximumSize(new Dimension(235, 570));
		textArea.setBackground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(225, 570));
		textArea.setText("Die Id's der Knoten: \n");
		for(CNode cnod : cnlist){
			textArea.setText("\n"+cnod.getId());
		}
        textArea.setEditable(false);
		
		JPanel bPanel = new JPanel();
		
		//BoxLayout um die Buttons untereinander anzuordnen
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.PAGE_AXIS));
		bPanel.add(searchPathBtn);
		bPanel.add(textArea);
		
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
/*
	@Override
	public void setShortestPath() {
		// TODO Auto-generated method stub
		
	}*/

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
			this.spc = csc;
			this.cnlist = csc.getNodesAsList();
			this.lnode = csc.getNodesAsMap();
			this.ledge = csc.getEdgesAsMap();
		}
	}
		
	class ShowPathListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				uNode = spc.getPath(selectedNodes.get("start"), selectedNodes.get("target"));
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			mapPanel.addUEdge(mapPanel.getGraphics(), uNode);
		}
	}
}