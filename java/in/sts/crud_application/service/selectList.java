package in.sts.crud_application.service;

import java.util.ArrayList;

public class selectList {
	/*
	 * 
	 * educationList method is used  for return the educationList for UI
	 * 
	 * 
	 */

	public ArrayList<String> qualificationList(){
		ArrayList<String> selectEducation=new ArrayList<String>();
		selectEducation.add("MA");
		selectEducation.add("BA");
		selectEducation.add("BCOM");
		selectEducation.add("MBA");
		selectEducation.add("MSC");
		selectEducation.add("BSC");
		selectEducation.add("BBA");
		selectEducation.add("MCA");
		selectEducation.add("BMM");
		selectEducation.add("MCOM");
		selectEducation.add("BE");
		return selectEducation;


	}
}
