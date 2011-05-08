package ch.hszt.sp.controllers;


import ch.hszt.sp.exceptions.DataAccessException;
import ch.hszt.sp.models.CNode;
import ch.hszt.sp.models.CShortestPathModel;
import ch.hszt.sp.views.CShortestPathView;

public class SPController {
	
	
	
	public SPController(){
		
		
	}
	public static void main(String[] args) {
		CShortestPathModel spm = new CShortestPathModel();
		try {
			spm.executIt();
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CShortestPathView cspv = new CShortestPathView(spm);
		cspv.update(spm, null);
		cspv.viewGUI();
	}

}
