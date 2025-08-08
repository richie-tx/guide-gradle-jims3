/**
 * 
 */
package ui.supervision.administersupervisee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.administersupervisee.reply.SuperviseeHistoryResponseEvent;
import messaging.contact.domintf.IName;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cc_cwalters
 *
 */
public class SuperviseeCreditDataControlForm extends ActionForm
{
	private String spn;
	private IName superviseeName;
	private String lastName;
	private String firstName;
	private String middleName;
	private String ssn;
	private String sex;
	private Date dateOfBirth;
	private String dateOfBirthStr;
	private String programUnitId;
	private String programUnitName;
	private String programUnitAssignDate;
	private String workloadCreditPositionId;
	private String workloadCreditStaffPositionName;
	private String createDate;
	private List<SuperviseeHistoryResponseEvent> superviseeHistory = new ArrayList();
	
	private String selectedSuperviseeHistoryId;
	private String selectedDivisionPgmUnitId;
	private String selectedDivisionPgmUnitName;
	private String selectedOfficerId;
	private String selectedOfficerName;
	
	private List divisionPgmUnitsList = new ArrayList();
	private List programUnitPositions = new ArrayList();
	
	private String userAgencyId;
	private String updateAction;
	private boolean currentlySupervised;

	public boolean isCurrentlySupervised() {
		return currentlySupervised;
	}

	public void setCurrentlySupervised(boolean currentlySupervised) {
		this.currentlySupervised = currentlySupervised;
	}	

	public String getUserAgencyId() {
		return userAgencyId;
	}

	public void setUserAgencyId(String userAgencyId) {
		this.userAgencyId = userAgencyId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getSpn() {
		return spn;
	}

	public void setSpn(String spn) {
		this.spn = spn;
	}

	public IName getSuperviseeName() {
		return superviseeName;
	}

	public void setSuperviseeName(IName superviseeName) {
		this.superviseeName = superviseeName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirthStr() {
		return dateOfBirthStr;
	}

	public void setDateOfBirthStr(String dateOfBirthStr) {
		this.dateOfBirthStr = dateOfBirthStr;
	}

	public void clear()
	{
		spn = "";
		selectedSuperviseeHistoryId = "";
		selectedDivisionPgmUnitId = "";
		selectedOfficerId = "";
	}

	public String getProgramUnitId() {
		return programUnitId;
	}

	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	
	public String getProgramUnitName() {
		return programUnitName;
	}

	public void setProgramUnitName(String programUnitName) {
		this.programUnitName = programUnitName;
	}

	public String getProgramUnitAssignDate() {
		return programUnitAssignDate;
	}

	public void setProgramUnitAssignDate(String programUnitAssignDate) {
		this.programUnitAssignDate = programUnitAssignDate;
	}

	public String getWorkloadCreditPositionId() {
		return workloadCreditPositionId;
	}

	public void setWorkloadCreditPositionId(String workloadCreditPositionId) {
		this.workloadCreditPositionId = workloadCreditPositionId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<SuperviseeHistoryResponseEvent> getSuperviseeHistory() {
		return superviseeHistory;
	}

	public void setSuperviseeHistory(
			List<SuperviseeHistoryResponseEvent> superviseeHistory) {
		this.superviseeHistory = superviseeHistory;
	}

	public String getWorkloadCreditStaffPositionName() {
		return workloadCreditStaffPositionName;
	}

	public void setWorkloadCreditStaffPositionName(
			String workloadCreditStaffPositionName) {
		this.workloadCreditStaffPositionName = workloadCreditStaffPositionName;
	}

	public String getSelectedSuperviseeHistoryId() {
		return selectedSuperviseeHistoryId;
	}

	public void setSelectedSuperviseeHistoryId(String selectedSuperviseeHistoryId) {
		this.selectedSuperviseeHistoryId = selectedSuperviseeHistoryId;
	}

	public List getDivisionPgmUnitsList() {
		return divisionPgmUnitsList;
	}

	public void setDivisionPgmUnitsList(List divisionPgmUnitsList) {
		this.divisionPgmUnitsList = divisionPgmUnitsList;
	}

	public String getSelectedDivisionPgmUnitId() {
		return selectedDivisionPgmUnitId;
	}

	public void setSelectedDivisionPgmUnitId(String selectedDivisionPgmUnitId) {
		this.selectedDivisionPgmUnitId = selectedDivisionPgmUnitId;
	}

	public String getSelectedDivisionPgmUnitName() {
		return selectedDivisionPgmUnitName;
	}

	public void setSelectedDivisionPgmUnitName(String selectedDivisionPgmUnitName) {
		this.selectedDivisionPgmUnitName = selectedDivisionPgmUnitName;
	}

	public List getProgramUnitPositions() {
		return programUnitPositions;
	}

	public void setProgramUnitPositions(List programUnitPositions) {
		this.programUnitPositions = programUnitPositions;
	}

	public String getSelectedOfficerId() {
		return selectedOfficerId;
	}

	public void setSelectedOfficerId(String selectedOfficerId) {
		this.selectedOfficerId = selectedOfficerId;
	}

	public String getSelectedOfficerName() {
		return selectedOfficerName;
	}

	public void setSelectedOfficerName(String selectedOfficerName) {
		this.selectedOfficerName = selectedOfficerName;
	}

	public String getUpdateAction() {
		return updateAction;
	}

	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}
	
}
