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
			CReadXmlDom rxd = new CReadXmlDom("edges.xml", "*");
			NodeList xmlNodeList = rxd.parseXmlFile();

			int listCounter = 0;
			CEdge cEdgeObj = new CEdge();
			for (int i = 0; i < xmlNodeList.getLength(); i++) {
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
					int directionId = Integer.valueOf(
							node.getFirstChild().getNodeValue()).intValue();
					cEdgeObj.setDirectionId(directionId);
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
}
