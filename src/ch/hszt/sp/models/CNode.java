package ch.hszt.sp.models;

/**
 * CNode is a node class
 * 
 * @author Ramy Hasan
 * @since 10.04.2011
 */
public class CNode {
	private int id;
	private int xCoordinate;
	private int yCoordinate;
	private String name;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}

	/**
	 * @param xCoordinate
	 *            the xCoordinate to set
	 */
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}

	/**
	 * @param yCoordinate
	 *            the yCoordinate to set
	 */
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((new Integer(this.id) == null) ? 0 : new Integer(this.id)
						.hashCode());
		return result;
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
