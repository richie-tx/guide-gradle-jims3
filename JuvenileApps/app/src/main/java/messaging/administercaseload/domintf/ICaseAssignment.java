package messaging.administercaseload.domintf;

import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;

/**
 * @author Jim Fisher
 */
public interface ICaseAssignment
{
    String getDivisionId();
    
    String getDisplayCaseNum();
    
	String getAcknowledgePositionId();

    String getAcknowledgePositionName();

    String getAcknowledgeRoleCode();
    
    String getAcknowledgeStatusCode();

    String getAcknowledgeUserId();

    IName getAcknowledgeUserName();
    
    Date getAcknowledgeDate();

    String getCaseAssignmentId();
    
    String getCaseAssignmentHistId();

    String getCourtId();

    String getCriminalCaseId();

    String getDefendantId();

    IName getDefendantName();
    
    String getDefendantNameStr();
    
    String getSuperviseeName();

    String getDegreeOfOffense();
    
    IName getOfficerName();
    
    Date getOfficerAssignDate();

    String getOrderStatusCode();

    String getProgramUnitId();

    String getProgramUnitName();

    String getSupervisionOrderId();

    String getSupervisionStyleCode();
    
    IName getSupervisorName();

    Date getTerminationDate();
    
    Date getProgramUnitAssignDate();
    
    String getProbationOfficerInd();
    
    String getCdi();
    
    void setDivisionId(String divisionId);
    
    void setDisplayCaseNum( String displayCaseNum );

    void setAcknowledgePositionId(String aPositionId);

    void setAcknowledgePositionName(String aPositionName);

    void setAcknowledgeRoleCode(String aCode);

    void setAcknowledgeStatusCode(String aCode);

    void setAcknowledgeUserId(String aUserId);

    void setAcknowledgeUserName(IName aName);
    
    void setAcknowledgeDate(Date aAcknowledgeDate);

    void setCaseAssignmentId(String caseAssignmentId);
    
    void setCaseAssignmentHistId(String caseAssignmentHistId);

    void setCourtId(String aCourtId);

    void setCriminalCaseId(String criminalCaseId);

    void setDefendantId(String defendantId);

    void setDefendantName(IName aName);
    
    void setDefendantNameStr(String defendantName);
    
    void setSuperviseeName(String name);

    void setDegreeOfOffense(String aDegreeOfOffense);
    
    void setOfficerName(IName aName);
    
    void setOfficerAssignDate(Date aDate);

    void setOrderStatusCode(String anOrderStatusCode);

    void setProgramUnitId(String programUnitId);

    void setProgramUnitName(String programUnitName);

    void setSupervisionOrderId(String supervisionOrderId);

    void setSupervisionStyleCode(String supervisionStyleCode);
    
    void setSupervisorName(IName aName);

    void setTerminationDate(Date terminationDate);
    
    void setProgramUnitAssignDate(Date programUnitAssignDate);
    
    void setProbationOfficerInd(String probationOfficerInd);
    
    void setCdi(String cdi);
    
	/**
	 * @return Returns the caseStatus.
	 */
	public String getCaseStatus(); 
	/**
	 * @param caseStatus The caseStatus to set.
	 */
	public void setCaseStatus(String caseStatus) ;
	/**
	 * @return Returns the daysLeft.
	 */
	public long getDaysLeft() ;
	/**
	 * @param daysLeft The daysLeft to set.
	 */
	public void setDaysLeft(long daysLeft) ;
	/**
	 * @return Returns the defendantStatus.
	 */
	public String getDefendantStatus() ;
	/**
	 * @param defendantStatus The defendantStatus to set.
	 */
	public void setDefendantStatus(String defendantStatus) ;
	/**
	 * @return Returns the probationEndDate.
	 */
	public String getProbationEndDate();
	/**
	 * @param probationEndDate The probationEndDate to set.
	 */
	public void setProbationEndDate(String probationEndDate);
	/**
	 * @return Returns the probationStartDate.
	 */
	public String getProbationStartDate();
	/**
	 * @param probationStartDate The probationStartDate to set.
	 */
	public void setProbationStartDate(String probationStartDate);
	/**
	 * @return Returns the supervisorPositionId.
	 */
	public String getSupervisorPositionId();
	/**
	 * @param supervisorPositionId The supervisorPositionId to set.
	 */
	public void setSupervisorPositionId(String supervisorPositionId);

    String getAssignedStaffPositionId();

    void setAssignedStaffPositionId(String assignedStaffPositionId);

    String getCaseAssignmentState();
    
    void setCaseAssignmentState(String caseAssignmentState);

    String getAllocatedStaffPositionId();

	void setAllocatedStaffPositionId(String allocatedStaffPositionId);
	
	String getAssignedStaffPositionName();
	
	void setAssignedStaffPositionName(String positionName);

	/**
	 * @return the supervisorAllocationDate
	 */
	public Date getSupervisorAllocationDate();

	/**
	 * @param supervisorAllocationDate the supervisorAllocationDate to set
	 */
	public void setSupervisorAllocationDate(Date supervisorAllocationDate);
	
	public void setOfficerPhoneNumber(IPhoneNumber iPhone);
	
	public IPhoneNumber getOfficerPhoneNumber();
	
	/**
	 * Returns the transaction date of program unit assignment.
	 * @return
	 */
	public Date getProgramUnitAssignmentTranactionDate();

	/**
	 * Sets the transaction date of program unit assignment.
	 * @param programUnitAssignmentTranactionDate
	 */
	public void setProgramUnitAssignmentTranactionDate(Date programUnitAssignmentTranactionDate);

	/**
	 * Returns the transaction date of officer assignment.
	 * @return
	 */
	public Date getOfficerAssignmentTransactionDate();

	/**
	 * Sets the transaction date of officer assignment.
	 * @param officerAssignmentTransactionDate
	 */
	public void setOfficerAssignmentTransactionDate(Date officerAssignmentTransactionDate);

}