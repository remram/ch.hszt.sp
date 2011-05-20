package ch.hszt.sp.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import ch.hszt.sp.controllers.CShortestPathController;
import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;
import ch.hszt.sp.models.CShortestPathModel;

public class CSPTest {
	public static void main(String[] args) throws DataAccessException {		
		int start = 1;
		int target = 1;
		
		CShortestPathModel csp = new CShortestPathModel();
		csp.execut();
		
		Iterator<Entry<Integer, CEdge>> edgeIter = csp.getEdgesAsMap().entrySet().iterator();
		while (edgeIter.hasNext()) {
			Map.Entry<Integer, CEdge> m = (Map.Entry<Integer, CEdge>)edgeIter.next();
			CEdge ce = (CEdge) m.getValue();

			System.out.print("Id: " + ce.getId());
			System.out.print(", {" + ce.getStartNode());
			System.out.print(" : " + ce.getTargetNode());
			System.out.println("}, weight: " + ce.getWeight());
			
		}
		
		LinkedList<CNode> path = csp.getShortestPath(--start,--target);
		System.out.println("\nShortestpath:\n=============\nID\tNameList\tNameMap");
		System.out.println("==\t========\t=======");
		if(path.get(0).getId() != 0) {
			for (CNode cNode : path) {			
				System.out.print(cNode.getId() + "\t" + cNode.getName());
				System.out.println("\t\t" + csp.getNodesAsMap().get(cNode.getId()).getName());
			}
		}
		
		
		System.out.println("Distance: " + csp.getDistance(start, target));
		
		
		//arbeiten mit dem controller!!!
		System.out.println("\n\n=====================\nController output:\n=====================");
		CShortestPathController spc = new CShortestPathController();
		System.out.println("ShortestPath from Controller: " + spc.getPath(start, target));
		System.out.println("ShortestDist from Controller: " + spc.getDistance(start, target));
		System.out.println("==============================================\nEnd of Controller output!\n==============================================\n\n");
	}
}
