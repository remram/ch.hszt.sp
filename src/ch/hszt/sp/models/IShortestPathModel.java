package ch.hszt.sp.models;

import java.util.Map;

import ch.hszt.sp.exceptions.DataAccessException;

public interface IShortestPathModel {	
	public Map<Integer,CNode> getNodes();
	public void setNodes() throws DataAccessException;	
	public Map<Integer,CEdge> getEdges();
	public void setEdges() throws DataAccessException;
	public Map<Integer,CNode> getShortestPath(int start, int target);
	public double getDistance(int start, int target);
}