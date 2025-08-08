/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;

import ui.supervision.administerassessments.AssessmentLightBean;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentTypeForm extends ActionForm
{
	protected String action = "";	
	protected String secondaryAction = "";
	
	
//	Details of a Supervisee(DefendantId and supervision period)
	protected String defendantId = "";	
	protected Date supervisionBeginDate = null;	
	protected Date supervisionEndDate = null;
	
//	set to "ACTIVE" or "INACTIVE"
	protected String supervisionPeriod=""; 
	
//	same for all the versions of an assessment	
	protected String assessmentTypeId = "";	
	protected String masterAssessmentId = "";  
	
//	set to true, if the assessment status is INCOMPLETE
	protected boolean assessmentIncomplete = false;
	
//	particular to each assessment version	
	protected String versionId = "";
	protected String assessmentId = "";  //refers to the OID of an assesment version
	
	protected Date assessmentDate = null;	
	protected Date actualAssessmentDate = null;
	protected String assessmentDateStr = "";
	protected String migrated = "";
	
	protected boolean islatestVersionShown = false;
	
	protected Collection versionDetailsList = new ArrayList(); 
	
	protected Collection questionAnswerList = new ArrayList();
	
//	holds the unique AssessmentBeanId of the selected Assessment link on DisplayAssessmentSummary page	
	protected String selectedAssessmentBeanId = "";
//	set to "ACTIVE" or "INACTIVE"	
	protected String selectedAssessSupervisionPrd = "";
//	hold the version number of the assement whose details are displayed
	protected String selectedVersionNumber = "";
//	holds the assessment Light Bean Obj from the Assessment Summary Page
	protected AssessmentLightBean selectedAssessmentBeanObj = null;
//	set to true, if the assessment is sent to state
	protected boolean sentToState = false; 
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the assessmentId.
	 */
	public String getAssessmentId() {
		return assessmentId;
	}
	/**
	 * @param assessmentId The assessmentId to set.
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
/**
 * @return Returns the assessmentTypeId.
 */
public String getAssessmentTypeId() {
	return assessmentTypeId;
}
/**
 * @param assessmentTypeId The assessmentTypeId to set.
 */
public void setAssessmentTypeId(String assessmentTypeId) {
	this.assessmentTypeId = assessmentTypeId;
}
/**
 * @return Returns the defendantId.
 */
public String getDefendantId() {
	return defendantId;
}
/**
 * @param defendantId The defendantId to set.
 */
public void setDefendantId(String defendantId) {
	this.defendantId = defendantId;
}
	/**
	 * @return Returns the islatestVersionShown.
	 */
	public boolean isIslatestVersionShown() {
		return islatestVersionShown;
	}
	/**
	 * @param islatestVersionShown The islatestVersionShown to set.
	 */
	public void setIslatestVersionShown(boolean islatestVersionShown) {
		this.islatestVersionShown = islatestVersionShown;
	}
	/**
	 * @return Returns the masterAssessmentId.
	 */
	public String getMasterAssessmentId() {
		return masterAssessmentId;
	}
	/**
	 * @param masterAssessmentId The masterAssessmentId to set.
	 */
	public void setMasterAssessmentId(String masterAssessmentId) {
		this.masterAssessmentId = masterAssessmentId;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the supervisionBeginDate.
	 */
	public Date getSupervisionBeginDate() {
		return supervisionBeginDate;
	}
	/**
	 * @param supervisionBeginDate The supervisionBeginDate to set.
	 */
	public void setSupervisionBeginDate(Date supervisionBeginDate) {
		this.supervisionBeginDate = supervisionBeginDate;
	}
	/**
	 * @return Returns the supervisionEndDate.
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}
	/**
	 * @param supervisionEndDate The supervisionEndDate to set.
	 */
	public void setSupervisionEndDate(Date supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}
/**
 * @return Returns the supervisionPeriod.
 */
public String getSupervisionPeriod() {
	return supervisionPeriod;
}
/**
 * @param supervisionPeriod The supervisionPeriod to set.
 */
public void setSupervisionPeriod(String supervisionPeriod) {
	this.supervisionPeriod = supervisionPeriod;
}
	/**
	 * @return Returns the versionDetailsList.
	 */
	public Collection getVersionDetailsList() {
		return versionDetailsList;
	}
	/**
	 * @param versionDetailsList The versionDetailsList to set.
	 */
	public void setVersionDetailsList(Collection versionDetailsList) {
		this.versionDetailsList = versionDetailsList;
	}
/**
 * @return Returns the versionId.
 */
public String getVersionId() {
	return versionId;
}
/**
 * @param versionId The versionId to set.
 */
public void setVersionId(String versionId) {
	this.versionId = versionId;
}
	/**
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	/**
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
		this.assessmentDateStr = "";

		if (assessmentDate != null) {

			try {
				this.assessmentDateStr = DateUtil.dateToString(assessmentDate, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.assessmentDateStr = "";
			}
		}
	}
	/**
	 * @return Returns the assessmentDateStr.
	 */
	public String getAssessmentDateStr() {
		return assessmentDateStr;
	}
	/**
	 * @param assessmentDateStr The assessmentDateStr to set.
	 */
	public void setAssessmentDateStr(String assessmentDateStr) {
		this.assessmentDateStr = "";
		this.assessmentDate = null;
		if ((assessmentDateStr != null) && (!assessmentDateStr.equalsIgnoreCase(""))) {
			try {
				this.assessmentDateStr = assessmentDateStr;
				this.assessmentDate = DateUtil.stringToDate(assessmentDateStr, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.assessmentDate = null;
			}
		}
	}
	
	/**
	 * @return Returns the questionAnswerList.
	 */
	public Collection getQuestionAnswerList() {
		return questionAnswerList;
	}
	/**
	 * @param questionAnswerList The questionAnswerList to set.
	 */
	public void setQuestionAnswerList(Collection questionAnswerList) {
		this.questionAnswerList = questionAnswerList;
	}
/**
 * @return Returns the selectedAssessmentBeanId.
 */
public String getSelectedAssessmentBeanId() {
	return selectedAssessmentBeanId;
}
/**
 * @param selectedAssessmentBeanId The selectedAssessmentBeanId to set.
 */
public void setSelectedAssessmentBeanId(String selectedAssessmentBeanId) {
	this.selectedAssessmentBeanId = selectedAssessmentBeanId;
}
/**
 * @return Returns the selectedAssessSupervisionPrd.
 */
public String getSelectedAssessSupervisionPrd() {
	return selectedAssessSupervisionPrd;
}
/**
 * @param selectedAssessSupervisionPrd The selectedAssessSupervisionPrd to set.
 */
public void setSelectedAssessSupervisionPrd(String selectedAssessSupervisionPrd) {
	this.selectedAssessSupervisionPrd = selectedAssessSupervisionPrd;
}
/**
 * @return Returns the selectedVersionNumber.
 */
public String getSelectedVersionNumber() {
	return selectedVersionNumber;
}
/**
 * @param selectedVersionNumber The selectedVersionNumber to set.
 */
public void setSelectedVersionNumber(String selectedVersionNumber) {
	this.selectedVersionNumber = selectedVersionNumber;
}
/**
 * @return Returns the selectedAssessmentBeanObj.
 */
public AssessmentLightBean getSelectedAssessmentBeanObj() {
	return selectedAssessmentBeanObj;
}
/**
 * @param selectedAssessmentBeanObj The selectedAssessmentBeanObj to set.
 */
public void setSelectedAssessmentBeanObj(AssessmentLightBean selectedAssessmentBeanObj) {
	this.selectedAssessmentBeanObj = selectedAssessmentBeanObj;
}
/**
 * @return Returns the sentToState.
 */
public boolean isSentToState() {
	return sentToState;
}
/**
 * @param sentToState The sentToState to set.
 */
public void setSentToState(boolean sentToState) {
	this.sentToState = sentToState;
}
/**
 * @return the assessmentIncomplete
 */
public boolean isAssessmentIncomplete() {
	return assessmentIncomplete;
}
/**
 * @param assessmentIncomplete the assessmentIncomplete to set
 */
public void setAssessmentIncomplete(boolean assessmentIncomplete) {
	this.assessmentIncomplete = assessmentIncomplete;
}
public Date getActualAssessmentDate() {
	return actualAssessmentDate;
}
public void setActualAssessmentDate(Date actualAssessmentDate) {
	this.actualAssessmentDate = actualAssessmentDate;
}
public String getMigrated() {
	return migrated;
}
public void setMigrated(String migrated) {
	this.migrated = migrated;
}


}
