/**
 * 
 */
package ch.hszt.sp.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hszt.sp.dao.IGisDAO;
import ch.hszt.sp.dao.XmlGisDAO;
import ch.hszt.sp.exceptions.DataAccessException;

/**
 * @author Ramy Hasan
 *
 */
public class TestCShortestPathModel {
	private static ArrayList<CNode> cNode;
	private static ArrayList<CEdge> cEdge;
	private static Map<Integer,CNode> mpNode;
	private static Map<Integer,CEdge> mpEdge;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		testExecut();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#execut()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testExecut() throws DataAccessException {
		testSetNodes();
		testSetEdges();
		
		testSetNodesAsMap();
		testSetEdgesAsMap();
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#getNodes()}.
	 */
	@Test
	public final void testGetNodes() {
		assertFalse("Object should be contains a list of data!", cNode.isEmpty());
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#setNodes()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testSetNodes() throws DataAccessException {
		//set cNode as ArrayList
		cNode = new ArrayList<CNode>();
		IGisDAO xgd = new XmlGisDAO();
		cNode = (ArrayList<CNode>) xgd.getNodes();
		
		//execute tests
		assertNotNull(cNode);
		assertNotNull(xgd);		
		assertTrue("Object should be contains a list of data!", cNode.size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#getEdges()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testGetEdges() {
		assertFalse("Object should be contains a list of data!", cEdge.isEmpty());
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#setEdges()}.
	 */
	@Test
	public final void testSetEdges() throws DataAccessException {
		//set cEdge
		cEdge = new ArrayList<CEdge>();
		IGisDAO xgd = new XmlGisDAO();
		cEdge = (ArrayList<CEdge>) xgd.getEdges();
		
		//execute tests
		assertNotNull(cEdge);
		assertNotNull(xgd);		
		assertTrue("Object should be contains a list of data!", cEdge.size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#getShortestPath(int, int)}.
	 */
	@Test
	public final void testGetShortestPath() {
		CDijkstra cd = new CDijkstra(cNode, cEdge);		
		cd.execute(cNode.get(0));
		LinkedList<CNode> path = cd.getPath(cNode.get(3));
		
		assertNotNull(cd);
		assertNotNull(path);
		assertFalse("Path list should contains data!", path.isEmpty());
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#getNodesAsMap()}.
	 */
	@Test
	public final void testGetNodesAsMap() {
		assertFalse("Object should be contains a map of data!", mpNode.isEmpty());
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#setNodesAsMap()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testSetNodesAsMap() throws DataAccessException {
		//set mpNode as HashMap
		mpNode = new HashMap<Integer, CNode>();
		IGisDAO xgd = new XmlGisDAO();
		mpNode = xgd.getNodesAsMap();
		
		//execute tests
		assertNotNull(mpNode);
		assertNotNull(xgd);		
		assertTrue("Object should be contains a map of data!", mpNode.size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#getEdgesAsMap()}.
	 */
	@Test
	public final void testGetEdgesAsMap() {
		assertFalse("Object should be contains a map of data!", mpEdge.isEmpty());
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#setEdgesAsMap()}.
	 * @throws DataAccessException 
	 */
	@Test
	public final void testSetEdgesAsMap() throws DataAccessException {
		//set mpEdge as HashMap
		mpEdge = new HashMap<Integer, CEdge>();
		IGisDAO xgd = new XmlGisDAO();
		mpEdge = xgd.getEdgesAsMap();
		
		//execute tests
		assertNotNull(mpEdge);
		assertNotNull(xgd);		
		assertTrue("Object should be contains a map of data!", mpEdge.size() > 0);
	}

	/**
	 * Test method for {@link ch.hszt.sp.models.CShortestPathModel#getDistance(int, int)}.
	 */
	@Test
	public final void testGetDistance() {
		CDijkstra cd = new CDijkstra(cNode, cEdge);		
		cd.execute(cNode.get(0));
		double distance = cd.getDistanceOfShortestPath(cNode.get(3));
		
		assertNotNull(cd);
		assertTrue("Distance must be bigger than 0!", distance > 0);
	}

}
