package ch.hszt.sp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import ch.hszt.sp.dao.IGisDAO;
import ch.hszt.sp.dao.XmlGisDAO;
import ch.hszt.sp.exceptions.DataAccessException;

/**
 * The class CShortestPathModel helps you to finde the shortest path. It's
 * provide you different methode to handle different functionality.
 * 
 * @author Ramy Hasan
 */
public class CShortestPathModel extends Observable implements
		IShortestPathModel {
	private ArrayList<CNode> cNode;
	private ArrayList<CEdge> cEdge;
	private Map<Integer, CNode> mpNode;
	private Map<Integer, CEdge> mpEdge;
	private LinkedList<CNode> path;

	private int start;
	private int target;

	public CShortestPathModel() {
	}

	public void notifyObserver() {
		setChanged();
		notifyObservers();
	}

	public void execute() throws DataAccessException {
		// set data
		setNodes();
		setEdges();
		setNodesAsMap();
		setEdgesAsMap();

		// notify observer
		notifyObserver();
	}

	@Override
	public ArrayList<CNode> getNodes() {
		return this.cNode;
	}

	@Override
	public void setNodes() throws DataAccessException {
		this.cNode = new ArrayList<CNode>();
		IGisDAO xgd = new XmlGisDAO();
		this.cNode = (ArrayList<CNode>) xgd.getNodes();
	}

	@Override
	public ArrayList<CEdge> getEdges() {
		return this.cEdge;
	}

	@Override
	public void setEdges() throws DataAccessException {
		this.cEdge = new ArrayList<CEdge>();
		IGisDAO xgd = new XmlGisDAO();
		this.cEdge = (ArrayList<CEdge>) xgd.getEdges();
	}

	@Override
	public LinkedList<CNode> getShortestPath(int start, int target) throws DataAccessException {
		// set the start and target node and convert it anyway to positive
		// integer
		setStart(Math.abs(start));
		setTarget(Math.abs(target));
		
		if(!this.mpNode.containsKey(Math.abs(start))) 
			throw new DataAccessException("Start node was illegal.");
		if(!this.mpNode.containsKey(Math.abs(target))) 
			throw new DataAccessException("Target node was illegal.");
		if(getStart() == getTarget())
			throw new DataAccessException("Start and target node were the same node!");
		
		LinkedList<CNode> cNodePath = null;
		
		try {
			CDijkstra cd = new CDijkstra(getNodes(), getEdges());
			cd.execute(getNodes().get(getStart()));
			cNodePath = cd.getPath(getNodes().get(getTarget()));
			
			if(cNodePath.equals(null)) {
				throw new DataAccessException("Returned path was empty");
			}
			// set the path
			setPath(cNodePath);
		} catch (Exception e) {
			try {
				throw new DataAccessException("An error has been triggered.");
			} catch (DataAccessException edae) {
				edae.printStackTrace();
			}
		}
		return cNodePath;
	}

	@Override
	public Map<Integer, CNode> getNodesAsMap() {
		return this.mpNode;
	}

	@Override
	public void setNodesAsMap() throws DataAccessException {
		this.mpNode = new HashMap<Integer, CNode>();
		IGisDAO xgd = new XmlGisDAO();
		this.mpNode = xgd.getNodesAsMap();
	}

	@Override
	public Map<Integer, CEdge> getEdgesAsMap() {
		return this.mpEdge;
	}

	@Override
	public void setEdgesAsMap() throws DataAccessException {
		this.mpEdge = new HashMap<Integer, CEdge>();
		IGisDAO xgd = new XmlGisDAO();
		this.mpEdge = xgd.getEdgesAsMap();
	}

	@Override
	public double getDistance() throws DataAccessException {
		if (getStart() == getTarget()) {
			return 0;
		}
		CDijkstra cd = new CDijkstra(getNodes(), getEdges());
		cd.execute(getNodes().get(start));
		return cd.getDistanceOfShortestPath(getNodes().get(target));
	}

	/**
	 * Get shortest path as a List This list conatins 
	 * - an ID 
	 * - a start node id
	 * - a start node name 
	 * - a target node id 
	 * - a target node name 
	 * - the distance between nodes
	 * 
	 * @return cPath
	 */
	@Override
	public ArrayList<CPath> getShortestPathList() {
		try {
			LinkedList<CNode> path = this.path;
			ArrayList<CPath> cPath = new ArrayList<CPath>();

			if (path.get(0).getId() != 0) {
				for (int i = 0; i < (path.size() - 1); i++) {
					CNode cnStart = path.get(i);
					CNode cnTarget = path.get(i);
					if ((i + 1) < path.size()) {
						cnTarget = path.get(++i);
						--i;
					}

					// set start and target node
					setStart(cnStart.getId());
					setTarget(cnTarget.getId());
					int pathId = i + 1;
					double distance = this.getDistance();

					// build the shortest bath as CPath ArrayList
					CPath cp = new CPath();

					cp.setPathId(pathId);
					cp.setStartNodeId(cnStart.getId());
					cp.setStartNode(cnStart.getName());
					cp.setTargetNodeId(cnTarget.getId());
					cp.setTargetNode(cnTarget.getName());
					cp.setDistance(distance);

					cPath.add(cp);

					cp = null;
				}
			}
			return cPath;
		} catch (Exception e) {
			try {
				throw new DataAccessException("An error has been triggered.");
			} catch (DataAccessException edae) {
				// TODO Auto-generated catch block
				edae.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @return the path
	 */
	public LinkedList<CNode> getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	private void setPath(LinkedList<CNode> path) {
		this.path = path;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	private void setStart(int start) {
		this.start = --start;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	private void setTarget(int target) {
		this.target = --target;
	}
}
