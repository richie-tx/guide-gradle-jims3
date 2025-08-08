package messaging.administercaseload.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 */
public class LightSuperviseeResponseEvent extends ResponseEvent
{
	private String assignedProgramUnitId;
	private String creditStaffPositionId;
    private boolean currentlySupervised;
    private String defendantId;
    private String defendantName;
    private Date losEffectiveDate;
    private String officerName;
    private String positionId;
    private String probationOfficerInd;
    private String programUnit;
    private Date programUnitAssignmentDate;
    private String superviseeId;    
    private String supervisionLevelId;
	
    /**
     * 
     * @return
     */
    public String getAssignedProgramUnitId() {
		return assignedProgramUnitId;
	}
    
    /**
     * 
     * @param assignedProgramUnitId
     */
	public void setAssignedProgramUnitId(String assignedProgramUnitId) {
		this.assignedProgramUnitId = assignedProgramUnitId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCreditStaffPositionId() {
		return creditStaffPositionId;
	}
	
	/**
	 * 
	 * @param caseloadCreditStaffPositionId
	 */
	public void setCreditStaffPositionId(
			String caseloadCreditStaffPositionId) {
		this.creditStaffPositionId = caseloadCreditStaffPositionId;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCurrentlySupervised() {
		return currentlySupervised;
	}
	
	/**
	 * 
	 * @param currentlySupervised
	 */
	public void setCurrentlySupervised(boolean currentlySupervised) {
		this.currentlySupervised = currentlySupervised;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDefendantId() {
		return defendantId;
	}
	
	/**
	 * 
	 * @param defendantId
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDefendantName() {
		return defendantName;
	}
	
	/**
	 * 
	 * @param defendantName
	 */
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getLosEffectiveDate() {
		return losEffectiveDate;
	}
	
	/**
	 * 
	 * @param losEffectiveDate
	 */
	public void setLosEffectiveDate(Date losEffectiveDate) {
		this.losEffectiveDate = losEffectiveDate;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
	public String getProgramUnit() {
		return programUnit;
	}
	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}
	public Date getProgramUnitAssignmentDate() {
		return programUnitAssignmentDate;
	}
	public void setProgramUnitAssignmentDate(Date programUnitAssignmentDate) {
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}
	public String getSuperviseeId() {
		return superviseeId;
	}
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	public String getSupervisionLevelId() {
		return supervisionLevelId;
	}
	public void setSupervisionLevelId(String supervisionLevelId) {
		this.supervisionLevelId = supervisionLevelId;
	}
    

}
