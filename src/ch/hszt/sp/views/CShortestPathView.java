package ch.hszt.sp.views;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CShortestPathView implements IShortestPathListener, IShortestPathGui{
	
	/**
	 * Die Klasse CShortestPathView sorgt fuer das GUI in unserer Applikation
	 * Sie reagiert auf die Events die in der GUI ausgeloest werden.
	 */
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JLabel label;
	private ShortestPathsPannel mapPanel;
	
	//Die Methode viewGUI verpasst dem Frame zwei Panels die dann alle GUI Komponenten enthalten.
	public void viewGUI(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton calcDistanceBtn = new JButton("Calc Distance");
		JButton searchPathBtn = new JButton("Search Path");
		label = new JLabel("Ich bin hier..");
		Image img = new ImageIcon("img/testmap.jpg").getImage();
		
		calcDistanceBtn.setSize(100, 50);
		searchPathBtn.setSize(100, 50);
		
		calcDistanceBtn.addActionListener(new CalcDistListener());
		searchPathBtn.addActionListener(new SearchPathListener());
		
		ShortestPathsPannel mapPanel = new ShortestPathsPannel("img/testmap.jpg");
		this.mapPanel = mapPanel;
		ButtonPanel bPanel = new ButtonPanel();
		
		//BoxLayout um die Buttons untereinander anzuordnen
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));
		
		frame.add(BorderLayout.EAST, bPanel);
		frame.add(BorderLayout.CENTER, mapPanel);
		mapPanel.add(BorderLayout.NORTH, label);
		bPanel.add(BorderLayout.NORTH, calcDistanceBtn);
		bPanel.add(BorderLayout.SOUTH, searchPathBtn);
		
		int wide = 1200+img.getWidth(mapPanel);
		int height = img.getWidth(mapPanel);
		frame.setSize(height, wide);
		frame.setVisible(true);
	}
	
	//Diese Klasse wird das Panel bieten welches die Buttons enthaelt die in der App benoetigt werden
	class ButtonPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g){
			g.setColor(Color.orange);
			
		}
		
	}
	
	//
	class SearchPathListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(label.getText()=="Ich bin hier.."){
			frame.repaint();
			label.setText("nicht mehr!!!");			
			}else{
				frame.repaint();
				label.setText("Ich bin hier..");
			}
		}
		
	}
	
	class CalcDistListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			frame.setBackground(Color.BLACK);
		}
		
	}
	
	public static void main(String[] args) {
		CShortestPathView shortest = new CShortestPathView();
		shortest.viewGUI();
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
	
}
