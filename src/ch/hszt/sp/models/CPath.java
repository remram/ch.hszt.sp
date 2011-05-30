package ch.hszt.sp.models;

public class CPath {
	private int pathId;
	private int startNodeId;
	private String startNode;
	private int targetNodeId;
	private String targetNode;
	private double distance;
	
	
	/**
	 * @return the pathId
	 */
	public int getPathId() {
		return pathId;
	}	
	/**
	 * @return the startNodeId
	 */
	public int getStartNodeId() {
		return startNodeId;
	}
	/**
	 * @param startNodeId the startNodeId to set
	 */
	public void setStartNodeId(int startNodeId) {
		this.startNodeId = startNodeId;
	}
	/**
	 * @param pathId the pathId to set
	 */
	public void setPathId(int pathId) {
		this.pathId = pathId;
	}
	/**
	 * @return the startNode
	 */
	public String getStartNode() {
		return startNode;
	}
	/**
	 * @param startNode the startNode to set
	 */
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}
	/**
	 * @return the targetNodeId
	 */
	public int getTargetNodeId() {
		return targetNodeId;
	}
	/**
	 * @param targetNodeId the targetNodeId to set
	 */
	public void setTargetNodeId(int targetNodeId) {
		this.targetNodeId = targetNodeId;
	}
	/**
	 * @return the targetNode
	 */
	public String getTargetNode() {
		return targetNode;
	}
	/**
	 * @param targetNode the targetNode to set
	 */
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
