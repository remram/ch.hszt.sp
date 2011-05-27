package ch.hszt.sp.models;

/**
 * CNode is a node class
 * 
 * @author Ramy Hasan
 * @since 10.04.2011
 */
public class CNode {
	/**
	 * @uml.property  name="id"
	 */
	private int id;
	/**
	 * @uml.property  name="xCoordinate"
	 */
	private int xCoordinate;
	/**
	 * @uml.property  name="yCoordinate"
	 */
	private int yCoordinate;
	/**
	 * @uml.property  name="name"
	 */
	private String name;

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
	 * @return  the xCoordinate
	 * @uml.property  name="xCoordinate"
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}

	/**
	 * @param xCoordinate  the xCoordinate to set
	 * @uml.property  name="xCoordinate"
	 */
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	/**
	 * @return  the yCoordinate
	 * @uml.property  name="yCoordinate"
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}

	/**
	 * @param yCoordinate  the yCoordinate to set
	 * @uml.property  name="yCoordinate"
	 */
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((new Integer(this.id) == null) ? 0 : new Integer(this.id)
						.hashCode());
		return result;*/
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CNode other = (CNode) obj;
		if (new Integer(this.id) == null) {
			if (new Integer(other.id) != null)
				return false;
		} else if (!new Integer(this.id).equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
