/*
 * Created on Dec 4, 2007
 *
 */
package messaging.administercaseload;

import java.util.Date;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.messaging.RequestEvent;

/**
 * @author cc_rbhat
 *  
 */
public class UpdateSuperviseeEvent extends RequestEvent {

	private String defendantId;

	private Date losEffectiveDate;

	private String supervisionLevelId;

	private String assignedProgramUnitId;

	private String assignedStaffPositionId;
	private String caseloadCreditStaffPositionId;

	private String updateType;

	private ICaseAssignment newCaseToAcknowledge;

	private List activeCases;
	
	private Date programUnitAssignmentDate;

	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @return Returns the losEffectiveDate.
	 */
	public Date getLosEffectiveDate() {
		return losEffectiveDate;
	}

	/**
	 * @return Returns the supervisionLevelId.
	 */
	public String getSupervisionLevelId() {
		return supervisionLevelId;
	}

	/**
	 * @return Returns the assignedProgramUnitId.
	 */
	public String getAssignedProgramUnitId() {
		return assignedProgramUnitId;
	}

	/**
	 * @return Returns the assignedStaffPositionId.
	 */
	public String getAssignedStaffPositionId() {
		return assignedStaffPositionId;
	}

	/**
	 * @return Returns the updateType.
	 */
	public String getUpdateType() {
		return updateType;
	}

	/**
	 * @return Returns the newCaseToAcknowledge.
	 */
	public ICaseAssignment getNewCaseToAcknowledge() {
		return newCaseToAcknowledge;
	}

	/**
	 * @return Returns the activeCases.
	 */
	public List getActiveCases() {
		return activeCases;
	}

	public Date getProgramUnitAssignmentDate() {
		return programUnitAssignmentDate;
	}

	/**
	 * @param defendantId
	 *            The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	/**
	 * @param losEffectiveDate
	 *            The losEffectiveDate to set.
	 */
	public void setLosEffectiveDate(Date losEffectiveDate) {
		this.losEffectiveDate = losEffectiveDate;
	}

	/**
	 * @param supervisionLevelId
	 *            The supervisionLevelId to set.
	 */
	public void setSupervisionLevelId(String supervisionLevelId) {
		this.supervisionLevelId = supervisionLevelId;
	}

	/**
	 * @param assignedProgramUnitId
	 *            The assignedProgramUnitId to set.
	 */
	public void setAssignedProgramUnitId(String assignedProgramUnitId) {
		this.assignedProgramUnitId = assignedProgramUnitId;
	}

	/**
	 * @param assignedStaffPositionId
	 *            The assignedStaffPositionId to set.
	 */
	public void setAssignedStaffPositionId(String assignedStaffPositionId) {
		this.assignedStaffPositionId = assignedStaffPositionId;
	}

	/**
	 * @param updateType
	 *            The updateType to set.
	 */
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	/**
	 * @param newCaseToAcknowledge The newCaseToAcknowledge to set.
	 */
	public void setNewCaseToAcknowledge(ICaseAssignment newCaseToAcknowledge) {
		this.newCaseToAcknowledge = newCaseToAcknowledge;
	}

	/**
	 * @param activeCases The activeCases to set.
	 */
	public void setActiveCases(List activeCases) {
		this.activeCases = activeCases;
	}

	public void setProgramUnitAssignmentDate(Date programUnitAssignmentDate) {
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}

	public String getCaseloadCreditStaffPositionId() {
		return caseloadCreditStaffPositionId;
	}

	public void setCaseloadCreditStaffPositionId(
			String caseloadCreditStaffPositionId) {
		this.caseloadCreditStaffPositionId = caseloadCreditStaffPositionId;
	}

}
