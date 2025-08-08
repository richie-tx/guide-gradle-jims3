/*
 * Created on May 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ResidentialExitPlanPrintBean
{
	private String juvenileNumber;
	
	private Map answerMap;
	private ArrayList goalDetailsList;

	private String juvenileFacilityStaySpecialNotes;
	
	public String getAnswers(String answerKey)
	{
		if (answerMap == null)
		{
			answerMap = new HashMap();
		}
		return (String) answerMap.get(answerKey);
	}

	public void addAnswer(String answerKey, String answer)
	{
		if (answerMap == null)
		{
			answerMap = new HashMap();
		}
		answerMap.put(answerKey, answer);
	}
	/**
	 * @return Returns the goalDetailsList.
	 */
	public ArrayList getGoalDetailsList() {
		return goalDetailsList;
	}
	/**
	 * @param goalDetailsList The goalDetailsList to set.
	 */
	public void setGoalDetailsList(ArrayList goalDetailsList) {
		this.goalDetailsList = goalDetailsList;
	}
	/**
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	/**
	 * @return Returns the juvenileFacilityStaySpecialNotes.
	 */
	public String getJuvenileFacilityStaySpecialNotes() {
		return juvenileFacilityStaySpecialNotes;
	}
	/**
	 * @param juvenileFacilityStaySpecialNotes The juvenileFacilityStaySpecialNotes to set.
	 */
	public void setJuvenileFacilityStaySpecialNotes(String juvenileFacilityStaySpecialNotes) {
		this.juvenileFacilityStaySpecialNotes = juvenileFacilityStaySpecialNotes;
	}
	
	public String getAnswer1() {
		String answer = (String)answerMap.get("2005_14_12_RESIDENTIALEXITPLAN_Q1");
		if(answer != null && answer.equalsIgnoreCase("true")) {
			return "Yes"; 
		}
		else {
			return "No";
		}
			
	}
	public String getAnswer2() {
		return (String)answerMap.get("2005_14_12_RESIDENTIALEXITPLAN_Q2");
	}
	public String getAnswer3() {
		String answer = (String)answerMap.get("2005_14_12_RESIDENTIALEXITPLAN_Q3");
		if(answer != null && answer.equalsIgnoreCase("true")) {
			return "Yes"; 
		}
		else {
			return "No";
		}
	}
	public String getAnswer4() {
		return (String)answerMap.get("2005_14_12_RESIDENTIALEXITPLAN_Q4");
	}
}
