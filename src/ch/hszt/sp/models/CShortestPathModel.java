package ch.hszt.sp.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import ch.hszt.sp.dao.IGisDAO;
import ch.hszt.sp.dao.XmlGisDAO;
import ch.hszt.sp.exceptions.DataAccessException;

public class CShortestPathModel extends Observable implements IShortestPathModel {
	private Map<Integer,CNode> mpNode;
	private Map<Integer,CEdge> mpEdge;
	
	public CShortestPathModel(){}
	
	public void notifyObserver() {
		setChanged();
		notifyObservers();
	}
	
	public void executIt() throws DataAccessException {
		//set data
		setNodes();
		setEdges();
		
		//notify observer
		notifyObserver();
	}

	@Override
	public Map<Integer,CNode> getNodes() {
		return this.mpNode;
	}

	@Override
	public void setNodes() throws DataAccessException {
		this.mpNode = new HashMap<Integer, CNode>();
		IGisDAO xgd = new XmlGisDAO();
		this.mpNode = xgd.getNodes();
	}

	@Override
	public Map<Integer,CEdge> getEdges() {
		return this.mpEdge;
	}

	@Override
	public void setEdges() throws DataAccessException {
		this.mpEdge = new HashMap<Integer, CEdge>();
		IGisDAO xgd = new XmlGisDAO();
		this.mpEdge = xgd.getEdges();
	}

	@Override
	public Map<Integer,CNode> getShortestPath(int start, int target) {
		CDijkstra cd = new CDijkstra(getNodes(), getEdges());		
		cd.execute(getNodes().get(start));
		return cd.getPath(getNodes().get(target));
	}

	@Override
	public double getDistance(CEdge start, CEdge target) {
		return 0;
	}
}
