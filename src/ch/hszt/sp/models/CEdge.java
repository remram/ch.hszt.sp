package ch.hszt.sp.models;

public class CEdge {
	/**
	 * @uml.property  name="id"
	 */
	private int id;
	/**
	 * @uml.property  name="weight"
	 */
	private double weight;
	/**
	 * @uml.property  name="directionType"
	 */
	private String directionType;
	/**
	 * @uml.property  name="directionId"
	 */
	private int directionId;
	/**
	 * @uml.property  name="startNode"
	 */
	private int startNode;
	/**
	 * @uml.property  name="targetNode"
	 */
	private int targetNode;

	/**
	 * @return  the id
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id  the id to set
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return  the weight
	 * @uml.property  name="weight"
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight  the weight to set
	 * @uml.property  name="weight"
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return  the directionType
	 * @uml.property  name="directionType"
	 */
	public String getDirectionType() {
		return directionType;
	}

	/**
	 * @param directionType  the directionType to set
	 * @uml.property  name="directionType"
	 */
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	/**
	 * @return  the directionId
	 * @uml.property  name="directionId"
	 */
	public int getDirectionId() {
		return directionId;
	}

	/**
	 * @param directionId  the directionId to set
	 * @uml.property  name="directionId"
	 */
	public void setDirectionId(int directionId) {
		this.directionId = directionId;
	}

	/**
	 * @return  the startNode
	 * @uml.property  name="startNode"
	 */
	public int getStartNode() {
		return startNode;
	}

	/**
	 * @param startNode  the startNode to set
	 * @uml.property  name="startNode"
	 */
	public void setStartNode(int startNode) {
		this.startNode = startNode;
	}

	/**
	 * @return  the targetNode
	 * @uml.property  name="targetNode"
	 */
	public int getTargetNode() {
		return targetNode;
	}

	/**
	 * @param targetNode  the targetNode to set
	 * @uml.property  name="targetNode"
	 */
	public void setTargetNode(int targetNode) {
		this.targetNode = targetNode;
	}

}
