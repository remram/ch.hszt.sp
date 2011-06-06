package ch.hszt.sp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.*;

import ch.hszt.sp.data.CReadXmlDom;
import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.*;

public class XmlGisDAO implements IGisDAO {

	private boolean isBiDirection;
	private int xmlNodeLength;
	private static final String FNAME_SUFFIX = ""; 
	
	@Override
	public List<CNode> getNodes() throws DataAccessException {
		List<CNode> cNodeList = new ArrayList<CNode>();

		try {

			CReadXmlDom rxd = new CReadXmlDom("nodes" + FNAME_SUFFIX + ".xml", "*");
			NodeList xmlNodeList = rxd.parseXmlFile();

			int listCounter = 0;
			CNode cNodeObj = new CNode();
			for (int i = 0; i < xmlNodeList.getLength(); i++) {
				Node node = xmlNodeList.item(i);

				//Get and set the id of the node
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 1) {
					int id = Integer.valueOf(
							node.getAttributes().item(0).getNodeValue())
							.intValue();
					cNodeObj.setId(id);
				}

				//Get and set coordinates of nodes
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 2) {
					int x = Integer.valueOf(
							node.getAttributes().item(0).getNodeValue())
							.intValue();
					int y = Integer.valueOf(
							node.getAttributes().item(1).getNodeValue())
							.intValue();
					cNodeObj.setxCoordinate(x);
					cNodeObj.setyCoordinate(y);
				}

				//Get and set node names
				if (node.hasChildNodes()) {
					cNodeObj.setName(node.getFirstChild().getNodeValue());
				}

				/**
				 * Each 4 iterations save the data to the ArrayList of CNode
				 * Class Afterward rest the object and create a new one for the
				 * new row!
				 */
				if (listCounter == 3) {
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

		return cNodeList;
	}

	@Override
	public List<CEdge> getEdges() throws DataAccessException {
		List<CEdge> cEdgeList = new ArrayList<CEdge>();

		try {
			CReadXmlDom rxd = new CReadXmlDom("edges" + FNAME_SUFFIX + ".xml", "*");
			NodeList xmlNodeList = rxd.parseXmlFile();

			int listCounter = 0;
			setBiDirection(false);
			setXmlNodeLength( xmlNodeList.getLength() );
			CEdge cEdgeObj = new CEdge();
			for (int i = 0 ; i < xmlNodeList.getLength() ; i++) {
				Node node = xmlNodeList.item(i);

				//Get and set edge id
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 1
						&& node.getNodeName().equals("edge")) {
					int id = Integer.valueOf(
							node.getAttributes().item(0).getNodeValue())
							.intValue();
					cEdgeObj.setId(id);
				}

				//Get and set weight of edge
				if (node.hasChildNodes() && node.getNodeName().equals("weight")) {
					double weight = Double.valueOf(
							node.getFirstChild().getNodeValue()).doubleValue();
					cEdgeObj.setWeight(weight);
				}

				//Get and set direction type and direction id
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 1
						&& node.getNodeName().equals("direction")) {
					cEdgeObj.setDirectionType(node.getAttributes().item(0)
							.getNodeValue());
					
					if(!cEdgeObj.getDirectionType().equals("one")) {
						setBiDirection(true);
					}
				}

				//Get and set start node
				if (node.getNodeName().equals("node-start")) {
					int startNode = Integer.valueOf(
							node.getFirstChild().getNodeValue()).intValue();
					cEdgeObj.setStartNode(startNode);
				}

				//Get and set target node
				if (node.getNodeName().equals("node-target")) {
					int targetNode = Integer.valueOf(
							node.getFirstChild().getNodeValue()).intValue();
					cEdgeObj.setTargetNode(targetNode);
				}

				/**
				 * Each 6 iterations save the data to the ArrayList of CEdge
				 * Class Afterward rest the object and create a new one for the
				 * new row!
				 */				
				if (listCounter == 6) {
					cEdgeList.add(cEdgeObj);
					/**
					 * If direction of edge is none
					 * We create a virtual edge for that
					 */
					if(isBiDirection()) {
						cEdgeList.add( this.createVritualEdge(cEdgeObj) );
						setBiDirection(false);
					}
					
					cEdgeObj = null;
					cEdgeObj = new CEdge();
					listCounter = 0;
				}

				listCounter++;
			}
		} catch (Exception e) {
			throw new DataAccessException();
		}

		return cEdgeList;
	}

	@Override
	public Map<Integer, CNode> getNodesAsMap() throws DataAccessException {
		Map<Integer,CNode> cNodeMap = new HashMap<Integer, CNode>();

		try {

			CReadXmlDom rxd = new CReadXmlDom("nodes" + FNAME_SUFFIX + ".xml", "*");
			NodeList xmlNodeList = rxd.parseXmlFile();

			int listCounter = 0;
			CNode cNodeObj = new CNode();
			for (int i = 0; i < xmlNodeList.getLength(); i++) {
				Node node = xmlNodeList.item(i);

				//Get and set the id of the node
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 1) {
					int id = Integer.valueOf(
							node.getAttributes().item(0).getNodeValue())
							.intValue();
					cNodeObj.setId(id);
				}

				//Get and set coordinates of nodes
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 2) {
					int x = Integer.valueOf(
							node.getAttributes().item(0).getNodeValue())
							.intValue();
					int y = Integer.valueOf(
							node.getAttributes().item(1).getNodeValue())
							.intValue();
					cNodeObj.setxCoordinate(x);
					cNodeObj.setyCoordinate(y);
				}

				//Get and set node names
				if (node.hasChildNodes()) {
					cNodeObj.setName(node.getFirstChild().getNodeValue());
				}

				/**
				 * Each 4 iterations save the data to the ArrayList of CNode
				 * Class Afterward rest the object and create a new one for the
				 * new row!
				 */
				if (listCounter == 3) {
					cNodeMap.put(cNodeObj.getId(), cNodeObj);
					cNodeObj = null;
					cNodeObj = new CNode();
					listCounter = 0;
				}

				listCounter++;
			}
		} catch (Exception e) {
			throw new DataAccessException();
		}

		return cNodeMap;
	}

	@Override
	public Map<Integer, CEdge> getEdgesAsMap() throws DataAccessException {		
		Map<Integer,CEdge> cEdgeMap = new HashMap<Integer, CEdge>();

		try {
			CReadXmlDom rxd = new CReadXmlDom("edges" + FNAME_SUFFIX + ".xml", "*");
			NodeList xmlNodeList = rxd.parseXmlFile();

			int listCounter = 0;
			setXmlNodeLength( xmlNodeList.getLength() );
			CEdge cEdgeObj = new CEdge();
			for (int i = 0 ; i < xmlNodeList.getLength() ; i++) {
				Node node = xmlNodeList.item(i);

				//Get and set edge id
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 1
						&& node.getNodeName().equals("edge")) {
					int id = Integer.valueOf(
							node.getAttributes().item(0).getNodeValue())
							.intValue();
					cEdgeObj.setId(id);
				}

				//Get and set weight of edge
				if (node.hasChildNodes() && node.getNodeName().equals("weight")) {
					double weight = Double.valueOf(
							node.getFirstChild().getNodeValue()).doubleValue();
					cEdgeObj.setWeight(weight);
				}

				//Get and set direction type and direction id
				if (node.hasAttributes()
						&& node.getAttributes().getLength() == 1
						&& node.getNodeName().equals("direction")) {
					cEdgeObj.setDirectionType(node.getAttributes().item(0)
							.getNodeValue());
					
					if(!cEdgeObj.getDirectionType().equals("one")) {
						setBiDirection(true);
					}
				}

				//Get and set start node
				if (node.getNodeName().equals("node-start")) {
					int startNode = Integer.valueOf(
							node.getFirstChild().getNodeValue()).intValue();
					cEdgeObj.setStartNode(startNode);
				}

				//Get and set target node
				if (node.getNodeName().equals("node-target")) {
					int targetNode = Integer.valueOf(
							node.getFirstChild().getNodeValue()).intValue();
					cEdgeObj.setTargetNode(targetNode);
				}

				/**
				 * Each 6 iterations save the data to the ArrayList of CEdge
				 * Class Afterward rest the object and create a new one for the
				 * new row!
				 */
				if (listCounter == 6) {
					cEdgeMap.put(cEdgeObj.getId(), cEdgeObj);
					/**
					 * If direction of edge is none
					 * We create a virtual edge for that
					 */
					if(isBiDirection()) {
						cEdgeMap.put( (cEdgeObj.getId() + getXmlNodeLength()), this.createVritualEdge( cEdgeObj ) );
						setBiDirection(false);
					}
					cEdgeObj = null;
					cEdgeObj = new CEdge();
					listCounter = 0;
				}

				listCounter++;
			}
		} catch (Exception e) {
			throw new DataAccessException();
		}

		return cEdgeMap;
	}
	
	/**
	 * Prepare a virtual edge with new id and opposite start and target ids
	 * @param cEdge
	 * @return cEdge virtual
	 */
	private CEdge createVritualEdge(CEdge cEdge) {
		CEdge virtualEdge = new CEdge();
		virtualEdge.setId(getXmlNodeLength() + cEdge.getId() );
		virtualEdge.setWeight(cEdge.getWeight());
		virtualEdge.setDirectionType(cEdge.getDirectionType());
		virtualEdge.setStartNode(cEdge.getTargetNode());
		virtualEdge.setTargetNode(cEdge.getStartNode());
		return virtualEdge;
	}

	/**
	 * Set Edge to bi direction edge
	 * @param isBiDirection
	 * @return isBiDirection boolean
	 */
	private boolean setBiDirection(boolean isBiDirection) {
		this.isBiDirection = isBiDirection;
		return isBiDirection;
	}

	/**
	 * Returns the boolean value of bi direction edge
	 * @return isBiDirection
	 */
	private boolean isBiDirection() {
		return isBiDirection;
	}

	/**
	 * Set the length of the xml node
	 * @param xmlNodeLength
	 */
	private void setXmlNodeLength(int xmlNodeLength) {
		this.xmlNodeLength = (--xmlNodeLength/6);
	}

	private int getXmlNodeLength() {
		return xmlNodeLength;
	}
}
