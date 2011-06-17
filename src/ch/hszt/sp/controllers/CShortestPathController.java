package ch.hszt.sp.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;
import ch.hszt.sp.models.CPath;
import ch.hszt.sp.models.CShortestPathModel;
import ch.hszt.sp.views.CShortestPathView;
//import ch.hszt.sp.views.CShortestPathView;

public class CShortestPathController extends Observable{
	/**
	 * @uml.property  name="spm"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private CShortestPathModel spm;
	
	public CShortestPathController() throws DataAccessException {
		this.spm = new CShortestPathModel();
		//CShortestPathView spv = 
		CShortestPathView spv = new CShortestPathView(this);
		//this.spm.addObserver(spv);
		this.spm.execute();
		notifyObserver();
		spv.viewGUI();
	}
	
	public void notifyObserver() {
			setChanged();
			notifyObservers();
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
	
	public LinkedList<CNode> getPath(int start, int target) throws DataAccessException {
		return spm.getShortestPath(start, target);
	}
	
	public double getDistance() throws DataAccessException {
		return spm.getDistance();
	}
	
	public ArrayList<CPath> getShortestPathList() {
		return spm.getShortestPathList();
	}
}
