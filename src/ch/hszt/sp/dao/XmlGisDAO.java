package ch.hszt.sp.dao;

import java.util.ArrayList;
import java.util.List;

import ch.hszt.sp.data.CReadXmlDom;
import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.*;

public class XmlGisDAO implements IGisDAO {
	
	CNode node = new CNode();

	@Override
	public List<CNode> getNodes() throws DataAccessException {
		List<CNode> nodeList = new ArrayList<CNode>();
		
		CReadXmlDom rxd = new CReadXmlDom("nodes.xml", "name");
		rxd.parseXmlFile();
		
		try {
			
			for (; rs.next();) {
				CNode node = new CNode();
				nodeList.add(node);
			}
			
			node.setId(1);
			node.setxCoordinate(5);
			node.setyCoordinate(20.4);
			node.setName("Node1");
		} catch (Exception e) {
			throw new DataAccessException();
		}
		return node;
	}

	@Override
	public List<CEdge> getEdges() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
