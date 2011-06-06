package ch.hszt.sp.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.hszt.sp.exceptions.DataAccessException;

public class CDijkstra {
	private final List<CNode> nodeList;
	private final List<CEdge> edgeList;
	private Set<CNode> settledNodes;
	private Set<CNode> unSettledNodes;
	private Map<CNode, CNode> predecessors;
	private Map<CNode, Double> distance;

	public CDijkstra(ArrayList<CNode> cNode, ArrayList<CEdge> cEdge) {
		this.nodeList = (List<CNode>) cNode;
		this.edgeList = (List<CEdge>) cEdge;
	}

	public void execute(CNode startNode) {
		settledNodes = new HashSet<CNode>();
		unSettledNodes = new HashSet<CNode>();
		distance = new HashMap<CNode, Double>();
		predecessors = new HashMap<CNode, CNode>();
		distance.put(startNode, (double) 0);		
		unSettledNodes.add(startNode);
		while (unSettledNodes.size() > 0) {
			CNode minNode = getMinimum(unSettledNodes);
			settledNodes.add(minNode);
			unSettledNodes.remove(minNode);
			findMinimalDistances(minNode);
		}
	}

	private void findMinimalDistances(CNode cNode) {
		List<CNode> adjacentNodes = getNeighbors(cNode);
		
		for (CNode target : adjacentNodes) {			
			if (getShortestDistance(target) > getShortestDistance(cNode) + getDistance(cNode, target)) {
				distance.put(target, getShortestDistance(cNode) + getDistance(cNode, target));
				predecessors.put(target, cNode);
				unSettledNodes.add(target);
			}
		}
	}

	/**
	 * Returns the distance between start and target node 
	 * @param startNode
	 * @param targetNode
	 * @return weight as double
	 */
	private double getDistance(CNode startNode, CNode targetNode) {
		for (CEdge cEdge : edgeList) {
			if ((cEdge.getStartNode() == startNode.getId())	&& (cEdge.getTargetNode() == targetNode.getId())) {
				return cEdge.getWeight();
			}
		}
		
		throw new RuntimeException("An error has been occurred.");
	}

	/**
	 * Returns a list of neighbors of the node
	 * @param cNode
	 * @return neighbors
	 */
	private List<CNode> getNeighbors(CNode cNode) {
		List<CNode> neighbors = new ArrayList<CNode>();

		for (CEdge cEdge : edgeList) {
			CNode cn = new CNode();
			
			if (cEdge.getStartNode() == cNode.getId() && !isSettled(cEdge.getTargetNode())) {
				int nodeId = cEdge.getTargetNode();
				cn.setId(nodeId);				
				cn.setName(nodeList.get(--nodeId).getName());
				neighbors.add(cn);
			}
		}
		return neighbors;
	}

	/**
	 * Returns the minimal node
	 * @param cNodeSet
	 * @return minimum
	 */
	private CNode getMinimum(Set<CNode> cNodeSet) {
		CNode minimum = null;
		for (CNode cNode : cNodeSet) {
			if (minimum == null) {
				minimum = cNode;
			} else {
				if (getShortestDistance(cNode) < getShortestDistance(minimum)) {
					minimum = cNode;
				}
			}
		}
		return minimum;
	}

	/**
	 * Checks if the node id is already settled
	 * @param nodeId Integer
	 * @return boolean
	 */
	private boolean isSettled(int nodeId) {
		return settledNodes.contains(nodeId);
	}

	/**
	 * 
	 * @param destination CNode
	 * @return
	 */
	private double getShortestDistance(CNode destination) {
		Double d = distance.get(destination);
		if (d == null) {
			return Double.MAX_VALUE;
		} else {
			return d;
		}
	}
	
	/**
	 * Returns the shortest path depending to the target as LinkedList
	 * @param traget CNode
	 * @return path LinkedList
	 */
	public LinkedList<CNode> getPath(CNode target) {
		LinkedList<CNode> path = new LinkedList<CNode>();

		// Check if a path exists try to throw an DataAccessException
		if (predecessors.get(target) == null) {
			try {
				throw new DataAccessException("Target (" + target.getId() + ": " + target.getName() + ") was unreachable!");
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		path.add(target);
		while (predecessors.get(target) != null) {
			target = predecessors.get(target);			
			path.add(target);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}
	
	public double getDistanceOfShortestPath(CNode cNode) {
		return getShortestDistance(cNode);
	}
}
