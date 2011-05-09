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
	/*
	 * private List<CEdge> spList; private List<CEdge> settledList;
	 * 
	 * private List<CNode> nodeList; private List<CEdge> edgeList;
	 * 
	 * private int startNode; private int targetNode;
	 */

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
			if (new Integer(cEdge.getStartNode()).equals(cNode)
					&& !isSettled(cEdge.getTargetNode())) {
				cn.setId(cEdge.getTargetNode());
				neighbors.add(cn);
			}
		}
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
		CNode step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

	/*
	 * public ArrayList<CEdge> getShortestPath(int startNode, int targetNode) {
	 * this.startNode = startNode; this.targetNode = targetNode; CEdge cEdge =
	 * new CEdge();
	 * 
	 * for (CNode cNode : this.nodeList) { cEdge.setId(cNode.getId());
	 * System.out.println(cNode.getId()); this.spList.add(cEdge); }
	 * 
	 * 
	 * 
	 * return (ArrayList<CEdge>) this.spList; }
	 */

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
