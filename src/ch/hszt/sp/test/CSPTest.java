package ch.hszt.sp.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;
import ch.hszt.sp.models.CShortestPathModel;

public class CSPTest {
	public static void main(String[] args) throws DataAccessException {
		CShortestPathModel cs = new CShortestPathModel();
		cs.executIt();
		
		Map<Integer, CNode> mpNode = cs.getNodes();
		System.out.println(mpNode.get(5).getxCoordinate());
		
		Iterator<Entry<Integer, CNode>> Iterator = cs.getNodes().entrySet().iterator();
		
		while (Iterator.hasNext()) {
			Map.Entry<Integer, CNode> m = (Map.Entry<Integer, CNode>)Iterator.next();
			CNode cn = (CNode) m.getValue();

			System.out.print("Key: " + m.getKey());
			System.out.print("  --  Id: " + cn.getId());
			System.out.print(", Name: " + cn.getName());
			System.out.print(", X: " + cn.getxCoordinate());
			System.out.println(", Y: " + cn.getyCoordinate());
			
		}
		
		Iterator<Entry<Integer, CEdge>> edgeIter = cs.getEdges().entrySet().iterator();
		while (edgeIter.hasNext()) {
			Map.Entry<Integer, CEdge> m = (Map.Entry<Integer, CEdge>)edgeIter.next();
			CEdge ce = (CEdge) m.getValue();

			System.out.print("Id: " + ce.getId());
			System.out.print(", {" + ce.getStartNode());
			System.out.print(" : " + ce.getTargetNode());
			System.out.println("}, weight: " + ce.getWeight());
			
		}
		
		int start = 1;
		int target = 6;
		
		Map<Integer, CNode> nodeMap = cs.getShortestPath(start, target);
		System.out.println(nodeMap);
		System.out.println(cs.getDistance(start, target));
	}
}
