package ch.hszt.sp.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public void execute(CNode source) {
		System.out.println("EXEC: " + source.getName());
		settledNodes = new HashSet<CNode>();
		unSettledNodes = new HashSet<CNode>();
		distance = new HashMap<CNode, Double>();
		predecessors = new HashMap<CNode, CNode>();
		distance.put(source, (double) 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			CNode node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(CNode cNode) {
		List<CNode> adjacentNodes = getNeighbors(cNode);
		
		for (CNode cNode2 : adjacentNodes) {
			System.out.println("findMinimalDistances: " + cNode2.getId());
		}
		
		for (CNode target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(cNode)
					+ getDistance(cNode, target)) {				
				distance.put(target,
						getShortestDistance(cNode) + getDistance(cNode, target));
				predecessors.put(target, cNode);
				unSettledNodes.add(target);
			}
		}
	}

	private double getDistance(CNode cNode, CNode target) {
		for (CEdge cEdge : edgeList) {
			if (new Integer(cEdge.getStartNode()).equals(cNode)
					&& new Integer(cEdge.getTargetNode()).equals(target)) {
				return cEdge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<CNode> getNeighbors(CNode cNode) {
		List<CNode> neighbors = new ArrayList<CNode>();
		CNode cn = new CNode();
		for (CEdge cEdge : edgeList) {
			System.out.println(cNode.getId() + ". getNeighbors to: " + cEdge.getStartNode());
			
			//if (new Integer(cEdge.getStartNode()).equals(cNode.getId())	&& !isSettled(cEdge.getTargetNode())) {
			if (cEdge.getStartNode() == cNode.getId()	&& !isSettled(cEdge.getTargetNode())) {
				
				System.out.println("2. getNeighbors: " + cNode.getId());
				
				cn.setId(cEdge.getTargetNode());
				neighbors.add(cn);
			}
		}
		System.out.println("neighbors.size(): "+neighbors.size());
		return neighbors;
	}

	private CNode getMinimum(Set<CNode> CNodees) {
		CNode minimum = null;
		for (CNode CNode : CNodees) {
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

	private boolean isSettled(int nodeId) {
		return settledNodes.contains(nodeId);
	}

	private double getShortestDistance(CNode destination) {
		Double d = distance.get(destination);
		if (d == null) {
			return Double.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<CNode> getPath(CNode target) {
		LinkedList<CNode> path = new LinkedList<CNode>();

		// Check if a path exists
		if (predecessors.get(target) == null) {
			return null;
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
	
	public void hasValue(int value) {
		for (CEdge cEdge : this.edgeList) {
			// System.out.println(cEdge.getStartNode() + " - " +
			// cEdge.getTargetNode());

			System.out.println("[" + cEdge.getStartNode() + "]"
					+ this.nodeList.get(cEdge.getStartNode()).getId() + " - "
					+ "[" + cEdge.getTargetNode() + "]"
					+ this.nodeList.get(cEdge.getTargetNode()).getId());
			System.out.println(this.nodeList.get(cEdge.getStartNode())
					.getName()
					+ " - "
					+ this.nodeList.get(cEdge.getTargetNode()).getName());

			// System.out.println(this.nodeList.get(cEdge.getTargetNode()).getName());

			/*
			 * if(this.nodeList.contains(new Integer(cEdge.getStartNode()))) {
			 * System.out.println(cEdge.getStartNode());
			 * System.out.println(this.nodeList.get(cEdge.getStartNode())); }
			 */
		}
	}
}
