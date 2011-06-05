package ch.hszt.sp.controllers;


import ch.hszt.sp.exceptions.DataAccessException;

/**
 * @author Ramy Hasan, Miroslav Mirković
 * @since 08.05.11
 * Der SPController Instanziert die fuer unser Programm benoetigten Klassen.
 * 
 **/
public class SPController {
	

	public static void main(String[] args) throws DataAccessException {
		new CShortestPathController();
		
		
	}

}
	/*static CShortestPathModel spm = new CShortestPathModel();
	static CShortestPathView cspv = new CShortestPathView(spm);
	
	
	public static void main(String[] args) {
		//Erstellt ein Model Objekt auf welchem die executIt Methode aufgerufen wird.
		CShortestPathModel spm = new CShortestPathModel();
		try {
			spm.execute();
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//CShortestPathView cspv = new CShortestPathView(spm);
		cspv.update(spm, null);
	
	}*/
	


