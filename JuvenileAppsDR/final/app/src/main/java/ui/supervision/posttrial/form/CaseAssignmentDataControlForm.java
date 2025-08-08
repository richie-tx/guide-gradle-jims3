/*
 * Created on February 25, 2009
 */
package ui.supervision.posttrial.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 */
public class CaseAssignmentDataControlForm extends ActionForm
{
//	 begin common form variables
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
//  end common form variables	
	private String userAgencyId;
	private String caseAssignmentState;
	private String caseNum;
	private String cdi;
	private String confirmMsg;
	private String currentDivisionPgmUnitId;
	private String currentOfficerId;	
	private String divisionPgmUnitDesc;
	private String divisionPgmUnitId;
	private Date latestPositionAssignmentDate;
	private String pgmUnitAssignmentDateStr;
	private String positionAssignmentDateStr;
	private String officerName;
	private String selectedOfficerId;
	
	private ICaseAssignment currentCaseAssignment;
	
	private List caseAssignmentHistoryList;
	private List divisionPgmUnitsList;
	private List officerList;
	
	public String getAction() {
		return action;
	}
	
    public void clear()
    {
    	this.caseAssignmentState = "";
    	this.caseNum = "";
    	this.cdi = "";
    	this.confirmMsg = "";
    	this.currentDivisionPgmUnitId = "";
    	this.currentOfficerId = "";	
    	this.divisionPgmUnitDesc = "";
    	this.divisionPgmUnitId = "";
    	this.pgmUnitAssignmentDateStr = "";
    	this.positionAssignmentDateStr = "";
    	this.officerName = "";
    	this.selectedOfficerId = "";
     	this.caseAssignmentHistoryList = new ArrayList();
    	this.divisionPgmUnitsList = new ArrayList();
    	this.officerList = new ArrayList();
    }

	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @return the update
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * @return the delete
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
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
	 * @return the caseNum
	 */
	public String getCaseNum() {
		return caseNum;
	}

	/**
	 * @param caseNum the caseNum to set
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	/**
	 * @return the cdi
	 */
	public String getCdi() {
		return cdi;
	}

	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}

	/**
	 * @return the confirmMsg
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}

	/**
	 * @param confirmMsg the confirmMsg to set
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	/**
	 * @return the caseAssignmentState
	 */
	public String getCaseAssignmentState() {
		return caseAssignmentState;
	}

	/**
	 * @param caseAssignmentState the caseAssignmentState to set
	 */
	public void setCaseAssignmentState(String caseAssignmentState) {
		this.caseAssignmentState = caseAssignmentState;
	}

	/**
	 * @return the divisionPgmUnitDesc
	 */
	public String getDivisionPgmUnitDesc() {
		return divisionPgmUnitDesc;
	}

	/**
	 * @param divisionPgmUnitDesc the divisionPgmUnitDesc to set
	 */
	public void setDivisionPgmUnitDesc(String divisionPgmUnitDesc) {
		this.divisionPgmUnitDesc = divisionPgmUnitDesc;
	}

	
	public String getDivisionPgmUnitId() {
		return divisionPgmUnitId;
	}

	public void setDivisionPgmUnitId(String divisionPgmUnitId) {
		this.divisionPgmUnitId = divisionPgmUnitId;
	}

	/**
	 * @return the latestPositionAssignmentDate
	 */
	public Date getLatestPositionAssignmentDate() {
		return latestPositionAssignmentDate;
	}

	/**
	 * @param latestPositionAssignmentDate the latestPositionAssignmentDate to set
	 */
	public void setLatestPositionAssignmentDate(Date latestPositionAssignmentDate) {
		this.latestPositionAssignmentDate = latestPositionAssignmentDate;
	}

	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}

	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	
	/**
	 * @return the currentOfficerId
	 */
	public String getCurrentOfficerId() {
		return currentOfficerId;
	}

	/**
	 * @param currentOfficerId the currentOfficerId to set
	 */
	public void setCurrentOfficerId(String currentOfficerId) {
		this.currentOfficerId = currentOfficerId;
	}

	public String getSelectedOfficerId() {
		return selectedOfficerId;
	}

	public void setSelectedOfficerId(String selectedOfficerId) {
		this.selectedOfficerId = selectedOfficerId;
	}

	/**
	 * @return the pgmUnitAssignmentDateStr
	 */
	public String getPgmUnitAssignmentDateStr() {
		return pgmUnitAssignmentDateStr;
	}

	/**
	 * @param pgmUnitAssignmentDateStr the pgmUnitAssignmentDateStr to set
	 */
	public void setPgmUnitAssignmentDateStr(String pgmUnitAssignmentDateStr) {
		this.pgmUnitAssignmentDateStr = pgmUnitAssignmentDateStr;
	}

	/**
	 * @return the positionAssignmentDateStr
	 */
	public String getPositionAssignmentDateStr() {
		return positionAssignmentDateStr;
	}

	/**
	 * @param positionAssignmentDateStr the positionAssignmentDateStr to set
	 */
	public void setPositionAssignmentDateStr(String positionAssignmentDateStr) {
		this.positionAssignmentDateStr = positionAssignmentDateStr;
	}

	/**
	 * @return the userAgencyId
	 */
	public String getUserAgencyId() {
		return userAgencyId;
	}

	/**
	 * @param userAgencyId the userAgencyId to set
	 */
	public void setUserAgencyId(String userAgencyId) {
		this.userAgencyId = userAgencyId;
	}

	/**
	 * @return the caseAssignmentHistoryList
	 */
	public List getCaseAssignmentHistoryList() {
		return caseAssignmentHistoryList;
	}

	/**
	 * @param caseAssignmentHistoryList the caseAssignmentHistoryList to set
	 */
	public void setCaseAssignmentHistoryList(List caseAssignmentHistoryList) {
		this.caseAssignmentHistoryList = caseAssignmentHistoryList;
	}

	/**
	 * @return the divisionPgmUnitsList
	 */
	public List getDivisionPgmUnitsList() {
		return divisionPgmUnitsList;
	}

	/**
	 * @param divisionPgmUnitsList the divisionPgmUnitsList to set
	 */
	public void setDivisionPgmUnitsList(List divisionPgmUnitsList) {
		this.divisionPgmUnitsList = divisionPgmUnitsList;
	}

	/**
	 * @return the officerList
	 */
	public List getOfficerList() {
		return officerList;
	}

	/**
	 * @param officerList the officerList to set
	 */
	public void setOfficerList(List officerList) {
		this.officerList = officerList;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return the currentDivisionPgmUnitId
	 */
	public String getCurrentDivisionPgmUnitId() {
		return currentDivisionPgmUnitId;
	}
	/**
	 * @param currentDivisionPgmUnitId the currentDivisionPgmUnitId to set
	 */
	public void setCurrentDivisionPgmUnitId(String currentDivisionPgmUnitId) {
		this.currentDivisionPgmUnitId = currentDivisionPgmUnitId;
	}
	/**
	 * @return Returns the currentCaseAssignment.
	 */
	public ICaseAssignment getCurrentCaseAssignment() {
		return currentCaseAssignment;
	}
	/**
	 * @param currentCaseAssignment The currentCaseAssignment to set.
	 */
	public void setCurrentCaseAssignment(ICaseAssignment currentCaseAssignment) {
		this.currentCaseAssignment = currentCaseAssignment;
	}   
}