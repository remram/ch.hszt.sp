package ch.hszt.sp.dao;

import java.util.List;
import java.util.Map;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.*;

public interface IGisDAO {
	public List<CNode> getNodes() throws DataAccessException;
	public List<CEdge> getEdges() throws DataAccessException;
	public Map<Integer,CNode> getNodesAsMap() throws DataAccessException;
	public Map<Integer,CEdge> getEdgesAsMap() throws DataAccessException;
}
