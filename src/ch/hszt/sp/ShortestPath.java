package ch.hszt.sp;

import ch.hszt.sp.controllers.CShortestPathController;
import ch.hszt.sp.exceptions.DataAccessException;

/**
 * @author Ramy Hasan, Miroslav Mirković
 * @since 08.05.11
 * Der SPController Instanziert die fuer unser Programm benoetigten Klassen.
 * 
 **/
public class ShortestPath {
	public static void main(String[] args) throws DataAccessException {
		new CShortestPathController();
	}
}
