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
	private JComboBox start, target;
	private JLabel startLabel, targetLabel;
	private JPanel jcbPanel;
	private Dimension labelDimension, spbtnDimension, jcbDimension;
	private JFrame frame;
	
	public CShortestPathView(Observable obs){
		this.observer = obs;
		observer.addObserver(this);
		if(obs instanceof CShortestPathController){
			this.spc = (CShortestPathController) obs;
		}
		this.jcbDimension = new Dimension(60, 50);
		this.jcbPanel = new JPanel();
		jcbPanel.setPreferredSize(new Dimension(403, 50));
		jcbPanel.setMaximumSize(new Dimension(403, 50));
		this.startLabel = new JLabel("Start:");
		this.targetLabel = new JLabel("Ziel:");		
		this.labelDimension = new Dimension(20,30);
		this.spbtnDimension = new Dimension(246,50);
		this.frame = new JFrame();
	}
	
	//Die Methode viewGUI verpasst dem Frame zwei Panels die dann alle GUI Komponenten enthalten.
	public void viewGUI(){
		//JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton searchPathBtn = new JButton("Search Path");
		searchPathBtn.setPreferredSize(spbtnDimension);
		searchPathBtn.setMaximumSize(spbtnDimension);
		searchPathBtn.addActionListener(new ShowPathListener());
		searchPathBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		/*searchPathBtn.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createLineBorder(
				Color.red),searchPathBtn.getBorder()));*/
		
		ShortestPathsPannel mapPanel = new ShortestPathsPannel(this.cnlist, this.lnode, this.ledge);
		this.mapPanel = mapPanel;
		this.selectedNodes = mapPanel.getSelectedNodes();
		mapPanel.setPreferredSize(new Dimension(725, 1000));
		mapPanel.setMaximumSize(new Dimension(725, 1000));
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.white);
		textArea.setEditable(false);		
		JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setPreferredSize(new Dimension(246, 478));
        //scrollPane.setMaximumSize(new Dimension(246, 478));
		
		JPanel bPanel = new JPanel();
		bPanel.setPreferredSize(new Dimension(246, 580));
		bPanel.setMaximumSize(new Dimension(246, 580));
		showNodeMenue();
		
		startLabel.setPreferredSize(labelDimension);
		startLabel.setMaximumSize(labelDimension);
		targetLabel.setPreferredSize(labelDimension);
		targetLabel.setMaximumSize(labelDimension);
		
		jcbPanel.add(startLabel);
		jcbPanel.add(start);
		jcbPanel.add(targetLabel);
		jcbPanel.add(target);
		
		bPanel.add(searchPathBtn);
		bPanel.add(jcbPanel);
		bPanel.add(scrollPane);
		//BoxLayout um die Buttons untereinander anzuordnen
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.PAGE_AXIS));

		frame.add(BorderLayout.EAST, bPanel);
		frame.add(BorderLayout.WEST, mapPanel);
		
		frame.setResizable(false);
		frame.setSize(975, 600);
		frame.setVisible(true);
		
	}
	
	protected void showNodeMenue(){
		int numbNodes;
		numbNodes = cnlist.size();
		String[] nodesStart = new String[numbNodes];
		
		for(int i = 0; i < numbNodes; i++){	
				nodesStart[i] = cnlist.get(i).getName();
			}
		
		start = new JComboBox(nodesStart);
		start.setFont(new Font("Verdana", Font.BOLD, 14));
		start.setPreferredSize(jcbDimension);
		start.setMaximumSize(jcbDimension);
		showTargetNodeMenue(cnlist);
		
	}
	
	protected void showTargetNodeMenue(ArrayList<CNode> cnod){
		int numbNodes;
		numbNodes = cnod.size();
		String[] nodesTarget = new String[numbNodes];
		
		for(int i = 0; i < numbNodes; i++){	
			nodesTarget[i] = cnlist.get(i).getName();
		}
		target = new JComboBox(nodesTarget);
		target.setFont(new Font("Verdana", Font.BOLD, 14));
		target.setPreferredSize(jcbDimension);
		target.setMaximumSize(jcbDimension);
	}
	
	class NodeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
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