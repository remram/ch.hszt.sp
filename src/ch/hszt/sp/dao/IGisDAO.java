package ch.hszt.sp.dao;

import java.util.Map;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.*;

public interface IGisDAO {
	public Map<Integer,CNode> getNodes() throws DataAccessException;
	public Map<Integer,CEdge> getEdges() throws DataAccessException;
}
