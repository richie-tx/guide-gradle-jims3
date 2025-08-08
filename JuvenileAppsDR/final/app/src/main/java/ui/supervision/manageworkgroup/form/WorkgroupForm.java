/*
 * Created on Feb 21, 2007
 */
package ui.supervision.manageworkgroup.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import naming.PDCodeTableConstants;

import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;

/**
 * @author hrodriguez
 */
public class WorkgroupForm extends ActionForm 
{
//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";

	//	Fields
	private String agencyId;
	private String divisionDesc;
	private String divisionId;  //Codetable Drop Down Lists
	private String jobTitleDesc;
	private String jobTitleId;  //Codetable Drop Down Lists
	private boolean openedTaskInd;
	private String positionTypeDesc;
	private String positionTypeId;  //Codetable Drop Down Lists
	private String previousWorkgroupName;
	private String programUnitDesc;
	private String programUnitId;  //Codetable Drop Down Lists	
	private String userId;
	private String userFirstName;
	private String userLastName;
	private Collection userResultList = new ArrayList();  // Search result list
	private String userResultListSize;
	private Collection userSelectedList = new ArrayList();  // Users associated to this group
	private String workgroupDescription;
	private String workgroupId;
	private String workgroupName;
	private String workgroupTypeDesc;
	private String workgroupTypeId;  //Codetable Drop Down Lists
	private String[] selectedUsers;
	
	private String programSectionId="";
	private String programSectionDesc="";
	private static Collection organizations=null;  //group of organizations

	public void clearDefaultFormValues() {
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
	}

	 public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
	    {
	        Object obj = request.getAttribute("clearSelectedUsersCheckBoxes");
	        if (obj != null)
	        {
	            clearSelectedUsers();
	        }
	        obj = null;
	        obj = request.getParameter("clearSelectedUsersCheckBoxes");
	        if (obj != null)
	        {
	        	  clearSelectedUsers();
	        }
//	        userSelectedList = new ArrayList();
	    }
	
	 public void clearSelectedUsers(){
	 	selectedUsers=null;
	 }

	 public void clearSelectedUserList(){
	     userSelectedList = new ArrayList();
		 }
	 
	 public void clearUserResultList(){
	 	userResultList = new ArrayList();
	 }
	 
	 public void refreshSearchUser(){
	 	userFirstName = "";
		userLastName = "";
		
		clear();
	 }
	 
	public void clear() {
		agencyId = "";
		divisionId = "";
		jobTitleId = "";
		openedTaskInd = false;
		positionTypeId = "";
		previousWorkgroupName = "";
		programUnitId = "";
		userFirstName = "";
		userLastName = "";
		userResultListSize = "";
		workgroupId = "";
		workgroupName = "";
		workgroupDescription = "";
		workgroupTypeId = "";
		programSectionId="";
		programSectionDesc="";

		// Collections and Drop Down Lists
		userResultList = new ArrayList();
		userSelectedList = new ArrayList();
		selectedUsers = null;
	}
	
	public void clearAll() {
		clearDefaultFormValues();
		clear();
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the divisionDesc.
	 */
	public String getDivisionDesc() {
		return divisionDesc;
	}

	/**
	 * @param divisionDesc
	 *            The division to set.
	 */
	public void setDivisionDesc(String divisionDesc) {
		this.divisionDesc = divisionDesc;
	}

	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}

	/**
	 * @param divisionId
	 *            The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
//		divisionDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DIVISION, divisionId);
	}

		/**
	 * @return Returns the jobTitleDesc.
	 */
	public String getJobTitleDesc() {
		return jobTitleDesc;
	}

	/**
	 * @param jobTitleDesc
	 *            The jobTitle to set.
	 */
	public void setJobTitleDesc(String jobTitleDesc) {
		this.jobTitleDesc = jobTitleDesc;
	}

	/**
	 * @return Returns the jobTitleId.
	 */
	public String getJobTitleId() {
		return jobTitleId;
	}

	/**
	 * @param jobTitleId
	 *            The jobTitleId to set.
	 */
	public void setJobTitleId(String jobTitleId) {
		this.jobTitleId = jobTitleId;
//		jobTitleDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JOB_TITLE, jobTitleId);
	}

	/**
	 * @return Returns the openedTaskInd.
	 */
	public boolean isOpenedTaskInd() {
		return openedTaskInd;
	}

	/**
	 * @param openedTaskInd
	 *            The openedTaskInd to set.
	 */
	public void setOpenedTaskInd(boolean openedTaskInd) {
		this.openedTaskInd = openedTaskInd;
	}

		/**
	 * @return Returns the positionTypeId.
	 */
	public String getPositionTypeId() {
		return positionTypeId;
	}

	/**
	 * @param positionTypeId
	 *            The positionTypeId to set.
	 */
	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	//	positionTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.POSITION_TYPE, positionTypeId);
	}

	/**
	 * @return Returns the userFirstName.
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName
	 *            The userFirstName to set.
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @return Returns the userLastName.
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName
	 *            The userLastName to set.
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return Returns the userResultList.
	 */
	public Collection getUserResultList() {
		return userResultList;
	}

	/**
	 * @param userResultList
	 *            The userResultList to set.
	 */
	public void setUserResultList(Collection userResultList) {
		this.userResultList = userResultList;
	}

	/**
	 * @param userResultList
	 *            The userResultList to set.
	 */
	public void addUserResult(WorkgroupUserBean wgUserBean) {
		this.userResultList.add(wgUserBean);
	}

	/**
	 * @return Returns the userResultListSize.
	 */
	public String getUserResultListSize() {
		return userResultListSize;
	}

	/**
	 * @param userResultListSize
	 *            The userResultListSize to set.
	 */
	public void setUserResultListSize(String userResultListSize) {
		this.userResultListSize = userResultListSize;
	}

	/**
	 * @return Returns the userSelectedList.
	 */
	public Collection getUserSelectedList() {
		return userSelectedList;
	}

	/**
	 * @param userSelectedList
	 *            The userSelectedList to set.
	 */
	public void setUserSelectedList(Collection userSelectedList) {
		this.userSelectedList = userSelectedList;
	}

	public void addUserSelected(WorkgroupUserBean wgUserBean) {
		this.userSelectedList.add(wgUserBean);
	}

	/**
	 * @return Returns the workgroupDescription.
	 */
	public String getWorkgroupDescription() {
		return workgroupDescription;
	}

	/**
	 * @param workgroupDescription
	 *            The workgroupDescription to set.
	 */
	public void setWorkgroupDescription(String workgroupDescription) {
		this.workgroupDescription = workgroupDescription;
	}

	/**
	 * @return Returns the workgroupId.
	 */
	public String getWorkgroupId() {
		return workgroupId;
	}

	/**
	 * @param workgroupId
	 *            The workgroupId to set.
	 */
	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}

	/**
	 * @return Returns the workgroupName.
	 */
	public String getWorkgroupName() {
		return workgroupName;
	}

	/**
	 * @param workgroupName
	 *            The workgroupName to set.
	 */
	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}

	/**
	 * @return Returns the workgroupTypeId.
	 */
	public String getWorkgroupTypeId() {
		return workgroupTypeId;
	}

	/**
	 * @param workgroupTypeId
	 *            The workgroupTypeId to set.
	 */
	public void setWorkgroupTypeId(String workgroupTypeId) {
		this.workgroupTypeId = workgroupTypeId;
		workgroupTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, workgroupTypeId);
	}
	
	/**
	 * @return Returns the previousWorkgroupName.
	 */
	public String getPreviousWorkgroupName() {
		return previousWorkgroupName;
	}
	/**
	 * @param previousWorkgroupName The previousWorkgroupName to set.
	 */
	public void setPreviousWorkgroupName(String previousWorkgroupName) {
		this.previousWorkgroupName = previousWorkgroupName;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		if (agencyId == null || agencyId.equals("")) {
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
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
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
/**
 * @return Returns the emptyColl.
 */
public static Collection getEmptyColl() {
	return emptyColl;
}
	
	/**
	 * @return Returns the positionTypeDesc.
	 */
	public String getPositionTypeDesc() {
		return positionTypeDesc;
	}
	/**
	 * @param positionTypeDesc The positionTypeDesc to set.
	 */
	public void setPositionTypeDesc(String positionTypeDesc) {
		this.positionTypeDesc = positionTypeDesc;
	}
	
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
//		programUnitDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PROGRAM_UNIT, programUnitId);
	}
	/**
	 * @return Returns the workgroupTypeDesc.
	 */
	public String getWorkgroupTypeDesc() {
		return workgroupTypeDesc;
	}
	/**
	 * @param workgroupTypeDesc The workgroupTypeDesc to set.
	 */
	public void setWorkgroupTypeDesc(String workgroupType) {
		this.workgroupTypeDesc = workgroupType;
	}
	/**
	 * @return Returns the programUnitDesc.
	 */
	public String getProgramUnitDesc() {
		return programUnitDesc;
	}
	/**
	 * @param programUnitDesc The programUnitDesc to set.
	 */
	public void setProgramUnitDesc(String programUnitDesc) {
		this.programUnitDesc = programUnitDesc;
	}
    /**
     * @return Returns the selectedUsers.
     */
    public String[] getSelectedUsers() {
        return selectedUsers;
    }
    /**
     * @param selectedUsers The selectedUsers to set.
     */
    public void setSelectedUsers(String[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }    
	/**
	 * @return Returns the organizations.
	 */
	public Collection getOrganizations() {
		if(organizations==null || organizations.size()<1){
			organizations=UIAdminStaffHelper.getActiveOrganizationalHeirarchy();
		}
		return organizations;
	}
	/**
	 * @return Returns the programSectionId.
	 */
	public String getProgramSectionId() {
		return programSectionId;
		
	}
	/**
	 * @param programSectionId The programSectionId to set.
	 */
	public void setProgramSectionId(String programSectionId) {
		this.programSectionId = programSectionId;
		programSectionDesc=UIAdminStaffHelper.getOrganizationName(programSectionId,organizations);
	}
	/**
	 * @return Returns the programSectionDesc.
	 */
	public String getProgramSectionDesc() {
		return programSectionDesc;
	}
	/**
	 * @param programSectionDesc The programSectionDesc to set.
	 */
	public void setProgramSectionDesc(String programSectionDesc) {
		this.programSectionDesc = programSectionDesc;
	}
	
	public Collection getJobTitles(){
		return ComplexCodeTableHelper.getSupervisionStaffJobTitles(SecurityUIHelper.getUserAgencyId());
	}
	
	public Collection getPositionTypes(){
		return ComplexCodeTableHelper.getSupervisionStaffPositionTypes(SecurityUIHelper.getUserAgencyId());
	}
}
