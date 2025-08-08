/*
 * Created on Jan 23, 2008
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
public class AssessmentForm extends ActionForm
{
	private String action = "";	
	private String secondaryAction = "";
	
	private String parentPage = "";
	
//	Details of a Supervisee(DefendantId and supervision period)
	private String defendantId = "";	
	private Date supervisionBeginDate = null;	
	private Date supervisionEndDate = null;
	
//	set to "ACTIVE" or "INACTIVE"
	private String supervisionPeriod=""; 
	
//	collection of Initial Assessments and Reassessments
//	more than 1 Initial Assessment for Inactive Supervsion Period, only 1 for active supervision period
	private Collection initialAssessmentsList = new ArrayList(); // collection of AssessmentLightBean	
	private Collection reassessmentsList = new ArrayList(); //collection of AssessmentLightBean
	private String initialAssessmentExist = "";
	private boolean incompleteWisconsinInitialExist = false;
	
//	more than 1 SCS assessment for historical assessment period, only 1 for active supervision period
	private Collection scsAssessmentsList = new ArrayList(); 
	private String scsAssessmentExist= "";
	private String scsInventoryExist = "";
	private String scsInterviewExist = "";
	
//	holds the unique AssessmentBeanId of the selected Assessment link on DisplayAssessmentSummary page	
	private String selectedInitialAssessmentBeanId = "";
//	holds the unique AssessmentBeanId of the selected Assessment link on DisplayAssessmentSummary page	
	private String selectedRessessmentBeanId = "";
//	holds "INITIAL" or "REASSESSMENT" depending on the selected Assess and update button clicked
	private String selectedInitialorReassess = "";
	
	private AssessmentLightBean selectedAssessmentBeanObj = null;
	
//	The begin and end date range to view the historical data
	private Date historicalBeginDate = null;	
	private String historicalBeginDateStr = "";	
	private Date historicalEndDate = null;	
	private String historicalEndDateStr = "";

	private Collection allHistoricalInitialAssessmentsList = new ArrayList(); // collection of AssessmentLightBean	
	private Collection allHistoricalReassessmentsList = new ArrayList(); 
	private Collection allHistoricalScsAssessmentsList = new ArrayList(); 
	private Collection allHistoricalSupervisionPlansList = new ArrayList(); // collection of SupervisionPlanBean
	
	private boolean hasHistoricalAssessments = false;
	
//	list of Supervision Plans
	private Collection supervisionPlansList = new ArrayList();
	
	private String selectedSupervisionPlanId = "";
	private boolean draftSupPlanExists = false;
	private boolean actvInactvSupPlanExists = false;
	private boolean activeSupPlanFound = false;
	private boolean incompleteWisconsinReassessExist = false;
	
	public void clear(){
		
		action = "";
		secondaryAction = "";	

		supervisionPeriod = "";
				
		initialAssessmentsList = new ArrayList();
		reassessmentsList = new ArrayList();
		initialAssessmentExist = "";
		incompleteWisconsinInitialExist = false;
		
		scsAssessmentsList = new ArrayList();
		scsAssessmentExist = "";
		scsInventoryExist = "";
		scsInterviewExist = "";
		
		selectedInitialAssessmentBeanId = "";
		selectedRessessmentBeanId = "";
		selectedInitialorReassess = "";
		
		selectedAssessmentBeanObj = null;
		
		historicalBeginDate = null;
		historicalBeginDateStr = "";
		historicalEndDate = null;
		historicalEndDateStr = "";
		
		allHistoricalInitialAssessmentsList = new ArrayList();
		allHistoricalReassessmentsList = new ArrayList();
		allHistoricalScsAssessmentsList = new ArrayList();
		allHistoricalSupervisionPlansList = new ArrayList();
		
		hasHistoricalAssessments = false;
		
		supervisionPlansList = new ArrayList();
		selectedSupervisionPlanId = "";
		
		incompleteWisconsinReassessExist = false;
	}
	
	public void clearSuperviseeDetails()
	{
		defendantId ="";
		supervisionBeginDate = null;
		supervisionEndDate = null;
		
		draftSupPlanExists = false;
		actvInactvSupPlanExists = false;
		activeSupPlanFound = false;
	}
	
	
	public void clearParentInfo()
	{
		parentPage = "";
	}

	/**
	 * @return the activeSupPlanFound
	 */
	public boolean isActiveSupPlanFound() {
		return activeSupPlanFound;
	}

	/**
	 * @param activeSupPlanFound the activeSupPlanFound to set
	 */
	public void setActiveSupPlanFound(boolean activeSupPlanFound) {
		this.activeSupPlanFound = activeSupPlanFound;
	}

	public boolean isActvInactvSupPlanExists() {
		return actvInactvSupPlanExists;
	}

	public void setActvInactvSupPlanExists(boolean actvInactvSupPlanExists) {
		this.actvInactvSupPlanExists = actvInactvSupPlanExists;
	}

	public boolean isDraftSupPlanExists() {
		return draftSupPlanExists;
	}

	public void setDraftSupPlanExists(boolean draftSupPlanExists) {
		this.draftSupPlanExists = draftSupPlanExists;
	}

	/**
	 * @return Returns the allHistoricalSupervisionPlansList.
	 */
	public Collection getAllHistoricalSupervisionPlansList() {
		return allHistoricalSupervisionPlansList;
	}
	/**
	 * @param allHistoricalSupervisionPlansList The allHistoricalSupervisionPlansList to set.
	 */
	public void setAllHistoricalSupervisionPlansList(Collection allHistoricalSupervisionPlansList) {
		this.allHistoricalSupervisionPlansList = allHistoricalSupervisionPlansList;
	}
	/**
	 * @return Returns the selectedSupervisionPlanId.
	 */
	public String getSelectedSupervisionPlanId() {
		return selectedSupervisionPlanId;
	}
	/**
	 * @param selectedSupervisionPlanId The selectedSupervisionPlanId to set.
	 */
	public void setSelectedSupervisionPlanId(String selectedSupervisionPlanId) {
		this.selectedSupervisionPlanId = selectedSupervisionPlanId;
	}
	/**
	 * @return Returns the hasHistoricalAssessments.
	 */
	public boolean isHasHistoricalAssessments() {
		return hasHistoricalAssessments;
	}
	/**
	 * @param hasHistoricalAssessments The hasHistoricalAssessments to set.
	 */
	public void setHasHistoricalAssessments(boolean hasHistoricalAssessments) {
		this.hasHistoricalAssessments = hasHistoricalAssessments;
	}
	/**
	 * @return Returns the parentPage.
	 */
	public String getParentPage() {
		return parentPage;
	}
	/**
	 * @param parentPage The parentPage to set.
	 */
	public void setParentPage(String parentPage) {
		this.parentPage = parentPage;
	}
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
 * @return Returns the historicalBeginDate.
 */
public Date getHistoricalBeginDate() {
	return historicalBeginDate;
}
/**
 * @param historicalBeginDate The historicalBeginDate to set.
 */
public void setHistoricalBeginDate(Date historicalBeginDate) {
	this.historicalBeginDate = historicalBeginDate;
	this.historicalBeginDateStr = "";

	if (historicalBeginDate != null) {

		try {
			this.historicalBeginDateStr = DateUtil.dateToString(historicalBeginDate, DateUtil.DATE_FMT_1);
		} catch (Exception e) {
			this.historicalBeginDateStr = "";
		}
	}
}
	/**
	 * @return Returns the historicalBeginDateStr.
	 */
	public String getHistoricalBeginDateStr() {
		return historicalBeginDateStr;
	}
	/**
	 * @param historicalBeginDateStr The historicalBeginDateStr to set.
	 */
	public void setHistoricalBeginDateStr(String historicalBeginDateStr) {
		this.historicalBeginDateStr = "";
		this.historicalBeginDate = null;
		if ((historicalBeginDateStr != null) && (!historicalBeginDateStr.equalsIgnoreCase(""))) {
			try {
				this.historicalBeginDateStr = historicalBeginDateStr;
				this.historicalBeginDate = DateUtil.stringToDate(historicalBeginDateStr, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.historicalBeginDate = null;
			}
		}
	}
	/**
	 * @return Returns the historicalEndDate.
	 */
	public Date getHistoricalEndDate() {
		return historicalEndDate;
	}
	/**
	 * @param historicalEndDate The historicalEndDate to set.
	 */
	public void setHistoricalEndDate(Date historicalEndDate) {
		this.historicalEndDate = historicalEndDate;
		this.historicalEndDateStr = "";

		if (historicalEndDate != null) {

			try {
				this.historicalEndDateStr = DateUtil.dateToString(historicalEndDate, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.historicalEndDateStr = "";
			}
		}
	}
	/**
	 * @return Returns the historicalEndDateStr.
	 */
	public String getHistoricalEndDateStr() {
		return historicalEndDateStr;
	}
	/**
	 * @param historicalEndDateStr The historicalEndDateStr to set.
	 */
	public void setHistoricalEndDateStr(String historicalEndDateStr) {
		this.historicalEndDateStr = "";
		this.historicalEndDate = null;
		if ((historicalEndDateStr != null) && (!historicalEndDateStr.equalsIgnoreCase(""))) {
			try {
				this.historicalEndDateStr = historicalEndDateStr;
				this.historicalEndDate = DateUtil.stringToDate(historicalEndDateStr, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.historicalEndDate = null;
			}
		}
	}
/**
 * @return Returns the initialAssessmentsList.
 */
public Collection getInitialAssessmentsList() {
	return initialAssessmentsList;
}
/**
 * @param initialAssessmentsList The initialAssessmentsList to set.
 */
public void setInitialAssessmentsList(Collection initialAssessmentsList) {
	this.initialAssessmentsList = initialAssessmentsList;
}
	/**
	 * @return Returns the reassessmentsList.
	 */
	public Collection getReassessmentsList() {
		return reassessmentsList;
	}
	/**
	 * @param reassessmentsList The reassessmentsList to set.
	 */
	public void setReassessmentsList(Collection reassessmentsList) {
		this.reassessmentsList = reassessmentsList;
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
 * @return Returns the scsAssessmentsList.
 */
public Collection getScsAssessmentsList() {
	return scsAssessmentsList;
}
/**
 * @param scsAssessmentsList The scsAssessmentsList to set.
 */
public void setScsAssessmentsList(Collection scsAssessmentsList) {
	this.scsAssessmentsList = scsAssessmentsList;
}
/**
 * @return Returns the scsAssessmentExist.
 */
public String getScsAssessmentExist() {
	return scsAssessmentExist;
}
/**
 * @param scsAssessmentExist The scsAssessmentExist to set.
 */
public void setScsAssessmentExist(String scsAssessmentExist) {
	this.scsAssessmentExist = scsAssessmentExist;
}

/**
 * @return Returns the initialAssessmentExist.
 */
public String getInitialAssessmentExist() {
	return initialAssessmentExist;
}
/**
 * @param initialAssessmentExist The initialAssessmentExist to set.
 */
public void setInitialAssessmentExist(String initialAssessmentExist) {
	this.initialAssessmentExist = initialAssessmentExist;
}
/**
 * @return Returns the allHistoricalInitialAssessmentsList.
 */
public Collection getAllHistoricalInitialAssessmentsList() {
	return allHistoricalInitialAssessmentsList;
}
/**
 * @param allHistoricalInitialAssessmentsList The allHistoricalInitialAssessmentsList to set.
 */
public void setAllHistoricalInitialAssessmentsList(Collection allHistoricalInitialAssessmentsList) {
	this.allHistoricalInitialAssessmentsList = allHistoricalInitialAssessmentsList;
}
/**
 * @return Returns the allHistoricalReassessmentsList.
 */
public Collection getAllHistoricalReassessmentsList() {
	return allHistoricalReassessmentsList;
}
/**
 * @param allHistoricalReassessmentsList The allHistoricalReassessmentsList to set.
 */
public void setAllHistoricalReassessmentsList(Collection allHistoricalReassessmentsList) {
	this.allHistoricalReassessmentsList = allHistoricalReassessmentsList;
}
/**
 * @return Returns the allHistoricalScsAssessmentsList.
 */
public Collection getAllHistoricalScsAssessmentsList() {
	return allHistoricalScsAssessmentsList;
}
/**
 * @param allHistoricalScsAssessmentsList The allHistoricalScsAssessmentsList to set.
 */
public void setAllHistoricalScsAssessmentsList(Collection allHistoricalScsAssessmentsList) {
	this.allHistoricalScsAssessmentsList = allHistoricalScsAssessmentsList;
}
/**
 * @return Returns the selectedInitialAssessmentBeanId.
 */
public String getSelectedInitialAssessmentBeanId() {
	return selectedInitialAssessmentBeanId;
}
/**
 * @param selectedInitialAssessmentBeanId The selectedInitialAssessmentBeanId to set.
 */
public void setSelectedInitialAssessmentBeanId(String selectedInitialAssessmentBeanId) {
	this.selectedInitialAssessmentBeanId = selectedInitialAssessmentBeanId;
}
/**
 * @return Returns the selectedRessessmentBeanId.
 */
public String getSelectedRessessmentBeanId() {
	return selectedRessessmentBeanId;
}
/**
 * @param selectedRessessmentBeanId The selectedRessessmentBeanId to set.
 */
public void setSelectedRessessmentBeanId(String selectedRessessmentBeanId) {
	this.selectedRessessmentBeanId = selectedRessessmentBeanId;
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
 * @return Returns the selectedInitialorReassess.
 */
public String getSelectedInitialorReassess() {
	return selectedInitialorReassess;
}
/**
 * @param selectedInitialorReassess The selectedInitialorReassess to set.
 */
public void setSelectedInitialorReassess(String selectedInitialorReassess) {
	this.selectedInitialorReassess = selectedInitialorReassess;
}
/**
 * @return Returns the supervisionPlansList.
 */
public Collection getSupervisionPlansList() {
	return supervisionPlansList;
}
/**
 * @param supervisionPlansList The supervisionPlansList to set.
 */
public void setSupervisionPlansList(Collection supervisionPlansList) {
	this.supervisionPlansList = supervisionPlansList;
}
/**
 * @return the incompleteWisconsinInitialExist
 */
public boolean isIncompleteWisconsinInitialExist() {
	return incompleteWisconsinInitialExist;
}

/**
 * @param incompleteWisconsinInitialExist the incompleteWisconsinInitialExist to set
 */
public void setIncompleteWisconsinInitialExist(
		boolean incompleteWisconsinInitialExist) {
	this.incompleteWisconsinInitialExist = incompleteWisconsinInitialExist;
}

/**
 * @return the incompleteWisconsinReassessExist
 */
public boolean isIncompleteWisconsinReassessExist() {
	return incompleteWisconsinReassessExist;
}

/**
 * @param incompleteWisconsinReassessExist the incompleteWisconsinReassessExist to set
 */
public void setIncompleteWisconsinReassessExist(
		boolean incompleteWisconsinReassessExist) {
	this.incompleteWisconsinReassessExist = incompleteWisconsinReassessExist;
}

/**
 * @return the scsInterviewExist
 */
public String getScsInterviewExist() {
	return scsInterviewExist;
}

/**
 * @param scsInterviewExist the scsInterviewExist to set
 */
public void setScsInterviewExist(String scsInterviewExist) {
	this.scsInterviewExist = scsInterviewExist;
}

/**
 * @return the scsInventoryExist
 */
public String getScsInventoryExist() {
	return scsInventoryExist;
}

/**
 * @param scsInventoryExist the scsInventoryExist to set
 */
public void setScsInventoryExist(String scsInventoryExist) {
	this.scsInventoryExist = scsInventoryExist;
}

}
