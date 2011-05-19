/**
 * 
 */
package ch.hszt.sp.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.NodeList;

import ch.hszt.sp.data.CReadXmlDom;

/**
 * @author Ramy Hasan
 *
 */
public class TestCReadXmlDom {
	
	private static CReadXmlDom crxdNodes;
	private static CReadXmlDom crxdEdges;
	private static NodeList xmlNodeList;
	private static NodeList xmlEdgeList;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		crxdNodes = new CReadXmlDom("nodes.xml", "*");
		crxdEdges = new CReadXmlDom("edges.xml", "*");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ch.hszt.sp.data.CReadXmlDom#parseXmlFile()}.
	 */
	@Test
	public final void testParseXmlFile() {
		xmlNodeList = crxdNodes.parseXmlFile();
		xmlEdgeList = crxdEdges.parseXmlFile();
		
		assertNotNull(xmlNodeList);
		assertNotNull(xmlEdgeList);
		
		assertNotSame(xmlNodeList, xmlEdgeList);
		
		assertTrue("Length of Node list should be bigger than: 0", xmlNodeList.getLength() > 0);
		assertTrue("Length of Edge list should be bigger than: 0", xmlEdgeList.getLength() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.data.CReadXmlDom#getXmlFile()}.
	 */
	@Test
	public final void testGetXmlFile() {
		assertEquals("nodes.xml", crxdNodes.getXmlFile());
		assertEquals("edges.xml", crxdEdges.getXmlFile());
	}

	/**
	 * Test method for {@link ch.hszt.sp.data.CReadXmlDom#setXmlFile(java.lang.String)}.
	 */
	@Test
	public final void testSetXmlFile() {
		crxdNodes.setXmlFile("nodes.xml");
		assertEquals("nodes.xml", crxdNodes.getXmlFile());
		
		crxdEdges.setXmlFile("edges.xml");
		assertEquals("edges.xml", crxdEdges.getXmlFile());
	}

	/**
	 * Test method for {@link ch.hszt.sp.data.CReadXmlDom#getTagName()}.
	 */
	@Test
	public final void testGetTagName() {
		assertEquals("*", crxdNodes.getTagName());
		assertEquals("*", crxdEdges.getTagName());
	}

	/**
	 * Test method for {@link ch.hszt.sp.data.CReadXmlDom#setTagName(java.lang.String)}.
	 */
	@Test
	public final void testSetTagName() {
		crxdNodes.setTagName("*");
		assertEquals("*", crxdNodes.getTagName());
		
		crxdEdges.setTagName("*");
		assertEquals("*", crxdEdges.getTagName());
	}

}
