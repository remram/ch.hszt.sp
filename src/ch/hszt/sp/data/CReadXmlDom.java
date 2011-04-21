package ch.hszt.sp.data;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CReadXmlDom {
	private String xmlFile;
	private String tagName;
	private NodeList nodeList;
	
	public CReadXmlDom(String xmlFile, String tagName) {
		this.xmlFile = xmlFile;
		this.tagName = tagName;
	}
	
	public NodeList parseXmlFile() {
		try {
			// Parse XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("src/ch/hszt/sp/data/"
					+ this.xmlFile));
			// Get list of nodes by tag name
			this.nodeList = document.getElementsByTagName(this.tagName);
			printNodesFromList(this.nodeList); // printNodesFromList see below
			// Error and exception handling
		} catch (SAXParseException spe) {
			System.out.println("\n** Parsing error, line "
					+ spe.getLineNumber() + ", uri " + spe.getSystemId());
			System.out.println("   " + spe.getMessage());
			Exception e = (spe.getException() != null) ? spe.getException()
					: spe;
			e.printStackTrace();
		} catch (SAXException sxe) {
			Exception e = (sxe.getException() != null) ? sxe.getException()
					: sxe;
			e.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return this.nodeList;
	}

	// ---- Helper methods ----

	private static void printObjIfVisible(String sValName, Object obj) {
		if (null == obj)
			return;
		String s = obj.toString();
		if (null != s && 0 < s.trim().length() && !s.trim().equals("\n"))
			System.out.println(sValName + s);
	}

	public static void printNodeInfos(String sNodeName, Node node) {
		System.out.println("\n---------------------- " + sNodeName);
		if (null != node) {
			printObjIfVisible("getNodeType()        = ",
					"" + node.getNodeType());
			printObjIfVisible("getNodeName()        = ", node.getNodeName());
			printObjIfVisible("getLocalName()       = ", node.getLocalName());
			printObjIfVisible("getNodeValue()       = ", node.getNodeValue());
			if (node.hasAttributes())
				printObjIfVisible("getAttributes()      = ",
						node.getAttributes());
			if (node.hasChildNodes()) {
				printObjIfVisible("getChildNodes()      = ",
						node.getChildNodes());
				printObjIfVisible("getFirstChild()      = ",
						node.getFirstChild());
			}
			printObjIfVisible("getPreviousSibling() = ",
					node.getPreviousSibling());
			printObjIfVisible("getNextSibling()     = ", node.getNextSibling());
		}
		System.out.println("----------------------\n");
	}

	public static void printNodesFromList(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++)
			printNodeInfos("nodeList.item(" + i + ")", nodeList.item(i));
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	// Test the class
	public static void main(String[] args) {
		CReadXmlDom rxd = new CReadXmlDom("nodes.xml", "name");
		rxd.parseXmlFile();
		rxd.setXmlFile("edges.xml");
		rxd.setTagName("edge");
		rxd.parseXmlFile();
	}
}
