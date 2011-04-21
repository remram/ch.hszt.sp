package ch.hszt.sp.dao;

import java.util.List;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.*;

public interface IGisDAO {
	public List<CNode> getNodes() throws DataAccessException;
	public List<CEdge> getEdges() throws DataAccessException;
}
