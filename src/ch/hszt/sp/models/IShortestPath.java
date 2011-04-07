package ch.hszt.sp.models;

import java.util.ArrayList;

public interface IShortestPath {
	
	/*Test functionality still testing.*/
	
	public double setStart();
	public double getStart();
	public double setTarget();
	public double getTarget();
	
	public ArrayList<CNode> getNode();
	public ArrayList<CEdge> getEdge();	
}
