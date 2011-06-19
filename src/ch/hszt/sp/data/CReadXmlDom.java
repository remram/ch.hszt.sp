package ch.hszt.sp.data;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * The class tries to open a xml file and return a NodeList  
 * @author Ramy Hasan
 */
public class CReadXmlDom {
	private String xmlFile;
	private String tagName;
	private NodeList xmlNodeList;
	
	/**
	 * Constructor to setup the xml file and the tag name
	 * @param xmlFile
	 * @param tagName
	 */
	public CReadXmlDom(String xmlFile, String tagName) {
		this.xmlFile = xmlFile;
		this.tagName = tagName;
	}
	
	/**
	 * Parse the xml file
	 * @return NodeList
	 */
	public NodeList parseXmlFile() {
		try {
			//Parse XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			InputStream propIn = getClass().getResourceAsStream(this.xmlFile);
			Document document = builder.parse(propIn);
			
			/*URL url = this.getClass().getClassLoader().getResource("ch/hszt/sp/data/" + this.xmlFile);
			Document document = builder.parse(new File(url.getFile()));*/
			
			
			
			//Get list of nodes by tag name			
			this.xmlNodeList = document.getElementsByTagName(this.tagName);
			//Error and exception handling
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
		
		return this.xmlNodeList;
	}

	/**
	 * @return xml file name
	 */
	public String getXmlFile() {
		return xmlFile;
	}

	/**
	 * @param xmlFile
	 */
	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	/**
	 * @return tag name
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
