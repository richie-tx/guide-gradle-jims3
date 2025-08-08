/*
 * Created on Feb 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.form;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LSIRAssessmentForm extends AssessmentTypeForm
{
//	set to "INITIAL_ASSESSMENT" or "REASSESSMENT"
	private String lsirAssessmentType = "";
	
	private Collection lsirAssessmentQuestionsList = new ArrayList();	

	private String comments = "";

//	set to "INITIAL_ASSESSMENT" or "REASSESSMENT"
	private String selectedAssessmentType = "";
	
	private String lsirScreenType = "";
	
	
	public void clear()
	{
		action = "";
		secondaryAction = "";
		
		defendantId = "";
		supervisionBeginDate = null;
		supervisionEndDate = null;
		
		supervisionPeriod = "";
		
		assessmentTypeId = "";
		masterAssessmentId = "";
		
		versionId = "";
		assessmentId = "";
		
		assessmentDate = null;
		assessmentDateStr = "";
		migrated = "";
		
		islatestVersionShown = false;
		
		versionDetailsList = new ArrayList(); // map of prior version with versionId - key, and a collection of transaction date, assessment date and assessmentId(OID) - maintain an Inner class for the collection
		questionAnswerList = new ArrayList();
		
		lsirAssessmentType = "";
		
		lsirAssessmentQuestionsList = new ArrayList();	
		comments = "";
		
		lsirScreenType="";
		
		sentToState = false;
	}
	
	
	public void clearSelected()
	{
		selectedAssessmentBeanId = "";
		selectedAssessmentType = "";
		selectedAssessSupervisionPrd = "";
		selectedVersionNumber = "";
		selectedAssessmentBeanObj = null;
	}
	
	public void clearAll()
	{
		clear();
		clearSelected();
	}
	
	
	
	/**
	 * @return Returns the lsirScreenType.
	 */
	public String getLsirScreenType() {
		return lsirScreenType;
	}
	/**
	 * @param lsirScreenType The lsirScreenType to set.
	 */
	public void setLsirScreenType(String lsirScreenType) {
		this.lsirScreenType = lsirScreenType;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the lsirAssessmentQuestionsList.
	 */
	public Collection getLsirAssessmentQuestionsList() {
		return lsirAssessmentQuestionsList;
	}
	/**
	 * @param lsirAssessmentQuestionsList The lsirAssessmentQuestionsList to set.
	 */
	public void setLsirAssessmentQuestionsList(Collection lsirAssessmentQuestionsList) {
		this.lsirAssessmentQuestionsList = lsirAssessmentQuestionsList;
	}
/**
 * @return Returns the lsirAssessmentType.
 */
public String getLsirAssessmentType() {
	return lsirAssessmentType;
}
/**
 * @param lsirAssessmentType The lsirAssessmentType to set.
 */
public void setLsirAssessmentType(String lsirAssessmentType) {
	this.lsirAssessmentType = lsirAssessmentType;
}
/**
 * @return Returns the selectedAssessmentType.
 */
public String getSelectedAssessmentType() {
	return selectedAssessmentType;
}
/**
 * @param selectedAssessmentType The selectedAssessmentType to set.
 */
public void setSelectedAssessmentType(String selectedAssessmentType) {
	this.selectedAssessmentType = selectedAssessmentType;
}
}
