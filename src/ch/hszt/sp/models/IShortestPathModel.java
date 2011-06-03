package ch.hszt.sp.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import ch.hszt.sp.exceptions.DataAccessException;

/**
 * This interface define different methodes which are need for
 * our shortest path model
 * 
 * @author Ramy Hasan
 *
 */
public interface IShortestPathModel {
	/**
	 * Retruns a node list
	 * @return ArrayList<CNode>
	 */
	public ArrayList<CNode> getNodes();
	
	/**
	 * Sets a node list
	 * @throws DataAccessException
	 */
	public void setNodes() throws DataAccessException;	
	
	/**
	 * Returns a edge list
	 * @return ArrayList<CEdge>
	 */
	public ArrayList<CEdge> getEdges();
	
	/**
	 * Sets a edge list
	 * @throws DataAccessException
	 */
	public void setEdges() throws DataAccessException;
	
	/**
	 * Returns the shortest path as LinkedList
	 * @param start int
	 * @param target int
	 * @return LinkedList<CNode>
	 */
	public LinkedList<CNode> getShortestPath(int start, int target) throws DataAccessException;
	
	/**
	 * Returns the distance between start and target node
	 * @return distance double
	 * @throws DataAccessException 
	 */
	public double getDistance() throws DataAccessException;
	
	/**
	 * Returns a node map
	 * @return Map<Integer, CNode>
	 */
	public Map<Integer, CNode> getNodesAsMap();
	
	/**
	 * Sets node map
	 * @throws DataAccessException
	 */
	public void setNodesAsMap() throws DataAccessException;
	
	/**
	 * Returns a edge map
	 * @return Map<Integer, CEdge>
	 */
	public Map<Integer, CEdge> getEdgesAsMap();
	
	/**
	 * Sets edge map
	 * @throws DataAccessException
	 */
	public void setEdgesAsMap() throws DataAccessException;
	
	/**
	 * Returns the shortest path as list
	 * @return ArrayList<CPath>
	 */
	public ArrayList<CPath> getShortestPathList();	
}