/*
 * Created on July 25, 2008
 */
package ui.supervision.posttrial.form;

import java.util.Collection;
import java.util.Date;

import org.apache.struts.action.ActionMapping;

import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author mchowdhury
 */
public class CSCDTaskForm extends AdminStaffSearchForm 
{
	private String agencyId;
	private String workgroupName;
	private String workgroupTypeId;
	private String workgroupId;
	private Date taskDueDate;
	private String taskDueDateStr;
	private String taskCaseNumber;
	private String taskCdi;
	private String taskSubject;
	private String taskSeverityLevel;
	private String taskSeverityLevelId;
	private String taskNextActionGroup;
	private String taskNextAction;
	private String taskText;	
	private String taskTo;
	private Collection workgroupList;
	private Collection taskSeverityLevels;
	private Collection taskNextActions;
	private Collection taskNextActionGroups;
	private String taskNextActionId;
	private String taskNextActionGroupId;
	private String searchById;
	private String cstsOfficerTypeId;
	private String selectedValue;
	private int totalResults;
	private String spn;
	private String superviseeName;
	private String officerName;
	private String courtNumber;
	private String ncResponseId;
    private String caseAssignIds;
    private String supervisionOrderIds;
		
	/**
	 * @return the ncResponseId
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}

	/**
	 * @param ncResponseId the ncResponseId to set
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}

	/**
	 * @return the cstsOfficerTypeId
	 */
	public String getCstsOfficerTypeId() {
		return cstsOfficerTypeId;
	}

	/**
	 * @param cstsOfficerTypeId the cstsOfficerTypeId to set
	 */
	public void setCstsOfficerTypeId(String cstsOfficerTypeId) {
		this.cstsOfficerTypeId = cstsOfficerTypeId;
	}

	/**
	 * @return the taskSeverityLevels
	 */
	public Collection getTaskSeverityLevels() {
		return taskSeverityLevels;
	}

	/**
	 * @param taskSeverityLevels the taskSeverityLevels to set
	 */
	public void setTaskSeverityLevels(Collection taskSeverityLevels) {
		this.taskSeverityLevels = taskSeverityLevels;
	}

	public void clearDefaultFormValues() {
		
		this.taskCaseNumber = "";
		this.taskCdi = "";
		this.taskSubject = "";
		this.taskDueDate = null;
		this.taskText = "";
		this.taskTo = "";
	}

	public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request){

    }
	 
	 public void refreshSearchUser(){
		 clear();
	 }
	 
	public void clear() {

	}
	
	public void clearAll() {
		clearDefaultFormValues();
		clear();
	}

	/**
	 * @return the agencyId
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * @return the workGroupName
	 */
	public String getWorkgroupName() {
		return workgroupName;
	}

	/**
	 * @return the workGroupTypeId
	 */
	public String getWorkgroupTypeId() {
		return workgroupTypeId;
	}

	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * @param workGroupName the workGroupName to set
	 */
	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}

	/**
	 * @param workGroupTypeId the workGroupTypeId to set
	 */
	public void setWorkgroupTypeId(String workgroupTypeId) {
		this.workgroupTypeId = workgroupTypeId;
	}

	/**
	 * @return the workGroupList
	 */
	public Collection getWorkgroupList() {
		return workgroupList;
	}

	/**
	 * @param workGroupList the workGroupList to set
	 */
	public void setWorkgroupList(Collection workgroupList) {
		this.workgroupList = workgroupList;
	}

	/**
	 * @return the workgroupId
	 */
	public String getWorkgroupId() {
		return workgroupId;
	}

	/**
	 * @param workgroupId the workgroupId to set
	 */
	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}

	/**
	 * @return the taskText
	 */
	public String getTaskText() {
		return taskText;
	}

	/**
	 * @return the taskCaseNumber
	 */
	public String getTaskCaseNumber() {
		return taskCaseNumber;
	}

	/**
	 * @return the taskCdi
	 */
	public String getTaskCdi() {
		return taskCdi;
	}

	/**
	 * @return the taskDueDate
	 */
	public Date getTaskDueDate() {
		return taskDueDate;
	}

	/**
	 * @return the taskNextAction
	 */
	public String getTaskNextAction() {
		return taskNextAction;
	}

	/**
	 * @return the taskSeverityLevel
	 */
	public String getTaskSeverityLevel() {
		return taskSeverityLevel;
	}

	/**
	 * @return the taskSubject
	 */
	public String getTaskSubject() {
		return taskSubject;
	}

	/**
	 * @param taskCaseNumber the taskCaseNumber to set
	 */
	public void setTaskCaseNumber(String taskCaseNumber) {
		this.taskCaseNumber = taskCaseNumber;
	}

	/**
	 * @param taskCdi the taskCdi to set
	 */
	public void setTaskCdi(String taskCdi) {
		this.taskCdi = taskCdi;
	}

	/**
	 * @param taskDueDate the taskDueDate to set
	 */
	public void setTaskDueDate(Date taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	/**
	 * @param taskNextAction the taskNextAction to set
	 */
	public void setTaskNextAction(String taskNextAction) {
		this.taskNextAction = taskNextAction;
	}

	/**
	 * @param taskSeverityLevel the taskSeverityLevel to set
	 */
	public void setTaskSeverityLevel(String taskSeverityLevel) {
		this.taskSeverityLevel = taskSeverityLevel;
	}

	/**
	 * @param taskSubject the taskSubject to set
	 */
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}

	/**
	 * @param taskText the taskText to set
	 */
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	/**
	 * @return the taskTo
	 */
	public String getTaskTo() {
		return taskTo;
	}

	/**
	 * @param taskTo the taskTo to set
	 */
	public void setTaskTo(String taskTo) {
		this.taskTo = taskTo;
	}

	/**
	 * @return the taskSeverityLevelId
	 */
	public String getTaskSeverityLevelId() {
		return taskSeverityLevelId;
	}

	/**
	 * @param taskSeverityLevelId the taskSeverityLevelId to set
	 */
	public void setTaskSeverityLevelId(String taskSeverityLevelId) {
		this.taskSeverityLevelId = taskSeverityLevelId;
	}

	/**
	 * @return the taskNextActions
	 */
	public Collection getTaskNextActions() {
		return taskNextActions;
	}

	/**
	 * @return the taskNextActionId
	 */
	public String getTaskNextActionId() {
		return taskNextActionId;
	}

	/**
	 * @param taskNextActionId the taskNextActionId to set
	 */
	public void setTaskNextActionId(String taskNextActionId) {
		this.taskNextActionId = taskNextActionId;
	}

	/**
	 * @return the searchById
	 */
	public String getSearchById() {
		return searchById;
	}

	/**
	 * @param searchById the searchById to set
	 */
	public void setSearchById(String searchById) {
		this.searchById = searchById;
	}


	/**
	 * @return the taskDueDateStr
	 */
	public String getTaskDueDateStr() {
		return taskDueDateStr;
	}

	/**
	 * @param taskDueDateStr the taskDueDateStr to set
	 */
	public void setTaskDueDateStr(String taskDueDateStr) {
		this.taskDueDateStr = taskDueDateStr;
	}
	

	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return the totalResults
	 */
	public int getTotalResults() {
		return totalResults;
	}

	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * @param taskNextActions the taskNextActions to set
	 */
	public void setTaskNextActions(Collection taskNextActions) {
		this.taskNextActions = taskNextActions;
	}

	/**
	 * @return the courtNumber
	 */
	public String getCourtNumber() {
		return courtNumber;
	}

	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}

	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}

	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param courtNumber the courtNumber to set
	 */
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}

	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}

	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return the taskNextActionGroupId
	 */
	public String getTaskNextActionGroupId() {
		return taskNextActionGroupId;
	}

	/**
	 * @param taskNextActionGroupId the taskNextActionGroupId to set
	 */
	public void setTaskNextActionGroupId(String taskNextActionGroupId) {
		this.taskNextActionGroupId = taskNextActionGroupId;
	}

	/**
	 * @return the taskNextActionGroups
	 */
	public Collection getTaskNextActionGroups() {
		return taskNextActionGroups;
	}

	/**
	 * @param taskNextActionGroups the taskNextActionGroups to set
	 */
	public void setTaskNextActionGroups(Collection taskNextActionGroups) {
		this.taskNextActionGroups = taskNextActionGroups;
	}

	/**
	 * @return the taskNextActionGroup
	 */
	public String getTaskNextActionGroup() {
		return taskNextActionGroup;
	}

	/**
	 * @param taskNextActionGroup the taskNextActionGroup to set
	 */
	public void setTaskNextActionGroup(String taskNextActionGroup) {
		this.taskNextActionGroup = taskNextActionGroup;
	}

	public String getCaseAssignIds() {
		return caseAssignIds;
	}

	public void setCaseAssignIds(String caseAssignIds) {
		this.caseAssignIds = caseAssignIds;
	}

	public String getSupervisionOrderIds() {
		return supervisionOrderIds;
	}

	public void setSupervisionOrderIds(String supervisionOrderIds) {
		this.supervisionOrderIds = supervisionOrderIds;
	}
	
}
