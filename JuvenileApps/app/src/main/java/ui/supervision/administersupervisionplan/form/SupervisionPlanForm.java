//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisionplan\\form\\SupervisionPlanForm.java

package ui.supervision.administersupervisionplan.form;

import java.util.Date;
import java.util.Hashtable;

import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;

import ui.security.SecurityUIHelper;


public class SupervisionPlanForm extends ActionForm
{
   
	public String getTaskId() {
		return taskId;
	}




	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	private String action = "";	
	private String secondaryAction = "";
	
	private String defendantId = "";
	private boolean activeSupervisionPeriod = false; 
	
	private Date supervisionBeginDate = null;	
	private Date supervisionEndDate = null;
	
	private String supervisionPlanId = "";	
	private Date supervisionPlanDate = null;	
	private String supervisionPlanDateStr = "";
	
	private String statusCd = "";
	private String statusDesc = "";
	
	private String lastChageDateStr = "";	
	private String lastChangeUserId = "";	
	private String lastChangeUserName = "";
	
	private String problem = "";	
	private String behaviorObjective = "";	
	private String offenderActionPlan = "";	
	private String csoActionPlan = "";
	
	private String selectedSupervisionPlanId = "";
	private Hashtable conditionsMap = null;
	
	private boolean draftSupervisionPlanExists = false;
	private String pageType = "";
	private String agencyId = "";
	private String taskId = "";
	
	public void clearAll()
	{
		action = "";
		secondaryAction = "";
		
		defendantId = "";
		activeSupervisionPeriod=false; 
		
		supervisionBeginDate = null;	
		supervisionEndDate = null;
		
		supervisionPlanId = "";
		supervisionPlanDate = null;
		supervisionPlanDateStr = "";
		statusCd = "";
		statusDesc = "";
		lastChageDateStr = "";
		lastChangeUserId = "";
		lastChangeUserName = "";
		
		problem = "";
		behaviorObjective = "";
		offenderActionPlan = "";
		csoActionPlan = "";
		
		selectedSupervisionPlanId = "";
		
		conditionsMap = new Hashtable();
		
		draftSupervisionPlanExists = false;
		pageType="";
		agencyId = "";
		taskId = "";
	}
	
	
	
	
	public String getAgencyId()
	{
		if (agencyId == null || agencyId.equals(""))
		{
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * @return the draftSupervisionPlanExists
	 */
	public boolean isDraftSupervisionPlanExists() {
		return draftSupervisionPlanExists;
	}

	/**
	 * @param draftSupervisionPlanExists the draftSupervisionPlanExists to set
	 */
	public void setDraftSupervisionPlanExists(boolean draftSupervisionPlanExists) {
		this.draftSupervisionPlanExists = draftSupervisionPlanExists;
	}

	/**
	 * @return the conditionsMap
	 */
	public Hashtable getConditionsMap() {
		return conditionsMap;
	}

	/**
	 * @param conditionsMap the conditionsMap to set
	 */
	public void setConditionsMap(Hashtable conditionsMap) {
		this.conditionsMap = conditionsMap;
	}

	/**
	 * @return Returns the statusDesc.
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc The statusDesc to set.
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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
	 * @return Returns the activeSupervisionPeriod.
	 */
	public boolean isActiveSupervisionPeriod() {
		return activeSupervisionPeriod;
	}
	/**
	 * @param activeSupervisionPeriod The activeSupervisionPeriod to set.
	 */
	public void setActiveSupervisionPeriod(boolean activeSupervisionPeriod) {
		this.activeSupervisionPeriod = activeSupervisionPeriod;
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
	 * @return Returns the behaviorObjective.
	 */
	public String getBehaviorObjective() {
		return behaviorObjective;
	}
	/**
	 * @param behaviorObjective The behaviorObjective to set.
	 */
	public void setBehaviorObjective(String behaviorObjective) {
		this.behaviorObjective = behaviorObjective;
	}
	/**
	 * @return Returns the csoActionPlan.
	 */
	public String getCsoActionPlan() {
		return csoActionPlan;
	}
	/**
	 * @param csoActionPlan The csoActionPlan to set.
	 */
	public void setCsoActionPlan(String csoActionPlan) {
		this.csoActionPlan = csoActionPlan;
	}
	/**
	 * @return Returns the lastChageDateStr.
	 */
	public String getLastChageDateStr() {
		return lastChageDateStr;
	}
	/**
	 * @param lastChageDateStr The lastChageDateStr to set.
	 */
	public void setLastChageDateStr(String lastChageDateStr) {
		this.lastChageDateStr = lastChageDateStr;
	}
	/**
	 * @return Returns the lastChangeUserId.
	 */
	public String getLastChangeUserId() {
		return lastChangeUserId;
	}
	/**
	 * @param lastChangeUserId The lastChangeUserId to set.
	 */
	public void setLastChangeUserId(String lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}
	/**
	 * @return Returns the lastChangeUserName.
	 */
	public String getLastChangeUserName() {
		return lastChangeUserName;
	}
	/**
	 * @param lastChangeUserName The lastChangeUserName to set.
	 */
	public void setLastChangeUserName(String lastChangeUserName) {
		this.lastChangeUserName = lastChangeUserName;
	}
	/**
	 * @return Returns the offenderActionPlan.
	 */
	public String getOffenderActionPlan() {
		return offenderActionPlan;
	}
	/**
	 * @param offenderActionPlan The offenderActionPlan to set.
	 */
	public void setOffenderActionPlan(String offenderActionPlan) {
		this.offenderActionPlan = offenderActionPlan;
	}
	/**
	 * @return Returns the problem.
	 */
	public String getProblem() {
		return problem;
	}
	/**
	 * @param problem The problem to set.
	 */
	public void setProblem(String problem) {
		this.problem = problem;
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
	 * @return Returns the statusCd.
	 */
	public String getStatusCd() {
		return statusCd;
	}
	/**
	 * @param statusCd The statusCd to set.
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	/**
	 * @return Returns the supervisionPlanDate.
	 */
	public Date getSupervisionPlanDate() {
		return supervisionPlanDate;
	}
	/**
	 * @param supervisionPlanDate The supervisionPlanDate to set.
	 */
	public void setSupervisionPlanDate(Date supervisionPlanDate)
	{
		this.supervisionPlanDate = supervisionPlanDate;
		this.supervisionPlanDateStr = "";

		if (supervisionPlanDate != null) {

			try {
				this.supervisionPlanDateStr = DateUtil.dateToString(supervisionPlanDate, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.supervisionPlanDateStr = "";
			}
		}
	}
	/**
	 * @return Returns the supervisionPlanDateStr.
	 */
	public String getSupervisionPlanDateStr() {
		return supervisionPlanDateStr;
	}
	/**
	 * @param supervisionPlanDateStr The supervisionPlanDateStr to set.
	 */
	public void setSupervisionPlanDateStr(String supervisionPlanDateStr)
	{
		this.supervisionPlanDateStr = "";
		this.supervisionPlanDate = null;
		if ((supervisionPlanDateStr != null) && (!supervisionPlanDateStr.equalsIgnoreCase(""))) {
			try {
				this.supervisionPlanDateStr = supervisionPlanDateStr;
				this.supervisionPlanDate = DateUtil.stringToDate(supervisionPlanDateStr, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.supervisionPlanDate = null;
			}
		}
	}
	/**
	 * @return Returns the supervisionPlanId.
	 */
	public String getSupervisionPlanId() {
		return supervisionPlanId;
	}
	/**
	 * @param supervisionPlanId The supervisionPlanId to set.
	 */
	public void setSupervisionPlanId(String supervisionPlanId) {
		this.supervisionPlanId = supervisionPlanId;
	}
}
