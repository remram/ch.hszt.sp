package ch.hszt.sp.dao;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;

import ch.hszt.sp.data.CReadXmlDom;
import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.*;

public class XmlGisDAO implements IGisDAO {
	
	

	@Override
	public List<CNode> getNodes() throws DataAccessException {
		List<CNode> cNodeList = new ArrayList<CNode>();
		
		try {
			
			CReadXmlDom rxd = new CReadXmlDom("nodes.xml", "*");
			NodeList xmlNodeList =  rxd.parseXmlFile();
			
			int listCounter = 0;
			CNode cNodeObj = new CNode();
			for (int i = 0; i < xmlNodeList.getLength(); i++) {				
				Node node = xmlNodeList.item(i);
				
				//get and set the id of the node
				if(node.hasAttributes() && node.getAttributes().getLength() == 1) {
					int id = Integer.valueOf( node.getAttributes().item(0).getNodeValue() ).intValue();
					cNodeObj.setId( id );
				}
				
				//get and set coordinates of nodes 
				if(node.hasAttributes() && node.getAttributes().getLength() == 2) {
					double x = Double.valueOf( node.getAttributes().item(0).getNodeValue() ).doubleValue();
					double y = Double.valueOf( node.getAttributes().item(1).getNodeValue() ).doubleValue();
					cNodeObj.setxCoordinate(x);
					cNodeObj.setyCoordinate(y);
				}
				
				//get and set node names
				if (node.hasChildNodes()) {
					cNodeObj.setName(node.getFirstChild().getNodeValue());
				}
				
				/**
				 * each 4 iterations save the data to the ArrayList of CNode Class
				 * Afterward rest the object and create a new one for the new row!
				 */
				if(listCounter == 3) {
					cNodeList.add(cNodeObj);
					cNodeObj = null;
					cNodeObj = new CNode();
					listCounter = 0;
				}
				
				listCounter++;
			}
		} catch (Exception e) {
			throw new DataAccessException();
		}
		
		/*for (CNode cNode : cNodeList) {
			System.out.print("ID: " + cNode.getId());
			System.out.print(", x: " + cNode.getxCoordinate());
			System.out.print(", y: " + cNode.getyCoordinate());
			System.out.println(", name: " + cNode.getName());
		}*/
		
		return cNodeList;
	}

	@Override
	public List<CEdge> getEdges() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws DataAccessException {
		CNode cNod = new CNode();
		XmlGisDAO xgd = new XmlGisDAO();
		cNod = (CNode) xgd.getNodes();
		System.out.println(cNod);
	}

}
