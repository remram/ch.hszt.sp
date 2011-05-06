package ch.hszt.sp.models;

import java.util.ArrayList;

import ch.hszt.sp.exceptions.DataAccessException;

public interface IShortestPathModel {	
	public ArrayList<CNode> getNodes();
	public void setNodes() throws DataAccessException;	
	public ArrayList<CEdge> getEdges();
	public void setEdges() throws DataAccessException;
	public double getDistance(CEdge start, CEdge target);
}