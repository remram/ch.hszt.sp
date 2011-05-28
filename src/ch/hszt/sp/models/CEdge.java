package ch.hszt.sp.models;

public class CEdge {
	private int id;
	private double weight;
	private String directionType;
	private int startNode;
	private int targetNode;

	/**
	 * @return  the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id  the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return  the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight  the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return  the directionType
	 */
	public String getDirectionType() {
		return directionType;
	}

	/**
	 * @param directionType  the directionType to set
	 */
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	/**
	 * @return  the startNode
	 */
	public int getStartNode() {
		return startNode;
	}

	/**
	 * @param startNode  the startNode to set
	 */
	public void setStartNode(int startNode) {
		this.startNode = startNode;
	}

	/**
	 * @return  the targetNode
	 */
	public int getTargetNode() {
		return targetNode;
	}

	/**
	 * @param targetNode  the targetNode to set
	 */
	public void setTargetNode(int targetNode) {
		this.targetNode = targetNode;
	}

}
