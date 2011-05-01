package ch.hszt.sp.models;

import java.util.ArrayList;

import ch.hszt.sp.exceptions.DataAccessException;

public interface IShortestPath {	
	public ArrayList<CNode> getNodes() throws DataAccessException;
	public ArrayList<CEdge> getEdges() throws DataAccessException;	
}