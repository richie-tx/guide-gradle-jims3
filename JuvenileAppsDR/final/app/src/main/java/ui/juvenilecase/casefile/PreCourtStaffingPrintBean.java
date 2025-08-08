/*
 * Created on Sept 9th, 2019
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PreCourtStaffingPrintBean
{
	private String juvenileNumber;
	private String juvenileName;
	private String race;
	private String sex;
	private String age;
	private String dob;
	private String jpoOfRec;	
	private Map answerMap;
	private String enteredBy;
	private String completionDate;
	
	//Bug #93469
	private String modReason;
	private String filteredModReason;
	private String action;
	
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

	public String getJuvenileName()
	{
	    return juvenileName;
	}

	public void setJuvenileName(String juvenileName)
	{
	    this.juvenileName = juvenileName;
	}

	public String getRace()
	{
	    return race;
	}

	public void setRace(String race)
	{
	    this.race = race;
	}

	public String getSex()
	{
	    return sex;
	}

	public void setSex(String sex)
	{
	    this.sex = sex;
	}

	public String getAge()
	{
	    return age;
	}

	public void setAge(String age)
	{
	    this.age = age;
	}

	public String getDob()
	{
	    return dob;
	}

	public void setDob(String dob)
	{
	    this.dob = dob;
	}

	public String getJpoOfRec()
	{
	    return jpoOfRec;
	}

	public void setJpoOfRec(String jpoOfRec)
	{
	    this.jpoOfRec = jpoOfRec;
	}

	public Map getAnswerMap()
	{
	    return answerMap;
	}

	public void setAnswerMap(Map answerMap)
	{
	    this.answerMap = answerMap;
	}

	public String getEnteredBy()
	{
	    return enteredBy;
	}

	public void setEnteredBy(String enteredBy)
	{
	    this.enteredBy = enteredBy;
	}

	public String getCompletionDate()
	{
	    return completionDate;
	}

	public void setCompletionDate(String completionDate)
	{
	    this.completionDate = completionDate;
	}

	/**
	 * @return the modReason
	 */
	public String getModReason()
	{
	    return modReason;
	}

	/**
	 * @param modReason the modReason to set
	 */
	public void setModReason(String modReason)
	{
	    this.modReason = modReason;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
	    return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
	    this.action = action;
	}

	public String getFilteredModReason()
	{
	    return filteredModReason;
	}

	public void setFilteredModReason(String filteredModReason)
	{
	    this.filteredModReason = filteredModReason;
	}

	
}
