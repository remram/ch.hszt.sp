package ch.hszt.sp.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class CDijkstra {
	private final Map<Integer,CNode> nodeMap;
	private final Map<Integer,CEdge> edgeMap;
	private Set<CNode> settledNodes;
	private Set<CNode> unSettledNodes;
	private Map<CNode, CNode> predecessors;
	private Map<CNode, Double> distance;

	public CDijkstra(Map<Integer,CNode> nodeMap, Map<Integer,CEdge> edgeMap) {
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
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
		Map<Integer, CNode> adjacentNodes = getNeighbors(cNode);
		
		Iterator<Entry<Integer, CNode>> Iterator = adjacentNodes.entrySet().iterator();
		while (Iterator.hasNext()) {
			Map.Entry<Integer, CNode> edgeEntry = (Map.Entry<Integer, CNode>)Iterator.next();
			CNode cn = (CNode) edgeEntry.getValue();
			
			if (getShortestDistance(cn) > getShortestDistance(cNode)
					+ getDistance(cNode, cn)) {
				distance.put(cn,
						getShortestDistance(cNode) + getDistance(cNode, cn));
				predecessors.put(cn, cNode);
				unSettledNodes.add(cn);
			}
		}
	}

	private double getDistance(CNode cNode, CNode target) {
		Iterator<Entry<Integer, CEdge>> Iterator = this.edgeMap.entrySet().iterator();		
		while (Iterator.hasNext()) {
			Map.Entry<Integer, CEdge> edgeEntry = (Map.Entry<Integer, CEdge>)Iterator.next();
			CEdge cEdge = (CEdge) edgeEntry.getValue();
			
			if((cEdge.getStartNode() == cNode.getId()) && (cEdge.getTargetNode() == target.getId())) {
				return cEdge.getWeight();
			}
		}
		throw new RuntimeException("Something is wrong!");
	}

	private Map<Integer,CNode> getNeighbors(CNode cNode) {
		Map<Integer,CNode> neighbors = new HashMap<Integer,CNode>();	
		
		Iterator<Entry<Integer, CEdge>> Iterator = this.edgeMap.entrySet().iterator();		
		while (Iterator.hasNext()) {
			CNode cn = new CNode();
			
			Map.Entry<Integer, CEdge> edgeEntry = (Map.Entry<Integer, CEdge>)Iterator.next();
			CEdge cEdge = (CEdge) edgeEntry.getValue();
			
			if (cEdge.getStartNode() == cNode.getId()	&& !isSettled(cEdge.getTargetNode())) {
				cn.setId(cEdge.getTargetNode());
				cn.setName(this.nodeMap.get(cn.getId()).getName());
				neighbors.put(cn.getId(), cn);
			}
		}
		return neighbors;
	}

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
	 * Returns the shortest distance
	 * @param destination CNode
	 * @return distanceLength
	 */
	private double getShortestDistance(CNode destination) {
		Double distanceLength = distance.get(destination);
		if (distanceLength == null) {
			return Double.MAX_VALUE;
		} else {
			return distanceLength;
		}
	}
	
	/**
	 * Returns the shortest path depending to the target
	 * @param traget CNode
	 * @return path
	 */
	public Map<Integer,CNode> getPath(CNode target) {
		Map<Integer,CNode> path = new HashMap<Integer,CNode>();

		// Check if a path exists
		if (predecessors.get(target) == null) {
			return null;
		}
		
		path.put(target.getId(),target);
		
		while (predecessors.get(target) != null) {
			target = predecessors.get(target);			
			path.put(target.getId(),target);
		}
		return path;
	}
	
	public double getDistance(CNode cNode) {
		return getShortestDistance(cNode);
	}
}
