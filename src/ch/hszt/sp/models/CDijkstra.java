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
		for (CNode CNode : cNodeSet) {
			if (minimum == null) {
				minimum = CNode;
			} else {
				if (getShortestDistance(CNode) < getShortestDistance(minimum)) {
					minimum = CNode;
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
		// Put it into the correct order
		//Collections.reverse(path);
		//Collections.reverseOrder(path);
		return path;
	}
}
