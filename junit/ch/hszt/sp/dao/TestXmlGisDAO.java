/**
 * 
 */
package ch.hszt.sp.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CEdge;
import ch.hszt.sp.models.CNode;

/**
 * @author Ramy Hasan
 *
 */
public class TestXmlGisDAO {
	
	private static XmlGisDAO xgd;
	private static List<CNode> xmlNodeList;
	private static List<CEdge> xmlEdgeList;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		xgd = new XmlGisDAO();
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
		xmlNodeList = xgd.getNodes();
		xmlEdgeList = xgd.getEdges();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ch.hszt.sp.dao.XmlGisDAO#getNodes()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testGetNodes() throws DataAccessException {
		assertNotNull(xmlNodeList);		
		assertTrue("The Node list is emtpy!", xmlNodeList.size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.dao.XmlGisDAO#getEdges()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testGetEdges() throws DataAccessException {
		assertNotNull(xmlEdgeList);
		assertTrue("The Edge list is emtpy!", xmlEdgeList.size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.dao.XmlGisDAO#getNodesAsMap()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testGetNodesAsMap() throws DataAccessException {
		assertNotNull(xgd.getNodesAsMap());
		assertTrue("The Node Map is emtpy!", xgd.getNodesAsMap().size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.dao.XmlGisDAO#getEdgesAsMap()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testGetEdgesAsMap() throws DataAccessException {
		assertNotNull(xgd.getEdgesAsMap());
		assertTrue("The Edge Map is emtpy!", xgd.getEdgesAsMap().size() > 0);
	}

}
