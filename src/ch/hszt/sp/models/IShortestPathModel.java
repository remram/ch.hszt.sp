package ch.hszt.sp.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import ch.hszt.sp.exceptions.DataAccessException;

public interface IShortestPathModel {	
	public ArrayList<CNode> getNodes();
	public void setNodes() throws DataAccessException;	
	public ArrayList<CEdge> getEdges();
	public void setEdges() throws DataAccessException;
	public LinkedList<CNode> getShortestPath(int start, int target);
	public double getDistance(int start, int target);
	public Map<Integer, CNode> getNodesAsMap();
	public void setNodesAsMap() throws DataAccessException;
	public Map<Integer, CEdge> getEdgesAsMap();
	public void setEdgesAsMap() throws DataAccessException;
}