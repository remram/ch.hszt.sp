package ch.hszt.sp.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;
import ch.hszt.sp.models.CShortestPathModel;
//import ch.hszt.sp.views.CShortestPathView;

public class CShortestPathController {
	private CShortestPathModel spm;
	
	public CShortestPathController() throws DataAccessException {
		this.spm = new CShortestPathModel();
		this.spm.execut();
		/*CShortestPathView spv = new CShortestPathView(this.spm); 
		spv.update(this.spm, this);
		this.spm.addObserver(spv);*/
	}
	
	public ArrayList<CNode> getNodesAsList() {
		return spm.getNodes();
	}
	
	public ArrayList<CEdge> getEdgesAsList() {
		return spm.getEdges();
	}
	
	public Map<Integer,CNode> getNodesAsMap() {
		return spm.getNodesAsMap();
	}
	
	public Map<Integer,CEdge> getEdgesAsMap() {
		return spm.getEdgesAsMap();
	}
	
	public LinkedList<CNode> getPath(int start, int target) {
		return spm.getShortestPath(start, target);
	}
	
	public double getDistance(int start, int target) {
		return spm.getDistance(start, target);
	}
}
