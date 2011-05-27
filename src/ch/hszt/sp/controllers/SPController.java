package ch.hszt.sp.controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CShortestPathModel;
import ch.hszt.sp.views.CShortestPathView;

/**
 * @author Ramy Hasan, Miroslav Mirković
 * @since 08.05.11
 * Der SPController Instanziert die fuer unser Programm benoetigten Klassen.
 * 
 **/
public class SPController {
	
	static CShortestPathModel spm = new CShortestPathModel();
	static CShortestPathView cspv = new CShortestPathView(spm);
	
	
	public static void main(String[] args) {
		//Erstellt ein Model Objekt auf welchem die executIt Methode aufgerufen wird.
		CShortestPathModel spm = new CShortestPathModel();
		try {
			spm.execut();
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//CShortestPathView cspv = new CShortestPathView(spm);
		cspv.update(spm, null);
	
	}
	
	
	
	
	
	
	public class SearchPathListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int in = 1;
			System.out.println(cspv.getSelectedNode(in));
		}
		
	}

}
