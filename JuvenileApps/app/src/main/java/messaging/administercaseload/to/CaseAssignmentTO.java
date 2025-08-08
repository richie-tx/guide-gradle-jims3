package messaging.administercaseload.to;

import java.util.Date;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.utilities.DateUtil;

/**
 * @author Jim Fisher
 */
public class CaseAssignmentTO implements ICaseAssignment
{
    private String acknowledgePositionId;

    private String acknowledgePositionName;

    private String acknowledgeRoleCode;

    private String acknowledgeStatusCode;

    private String acknowledgeUserId;

    private IName acknowledgeUserName;

    private Date acknowledgeDate;
    
    private String caseAssignmentId;
    
    private String caseAssignmentHistId;
    
	private String cdi;
	
	private String cdiandCaseNumber;

    private String courtId;

    private String criminalCaseId;

    private String defendantId;

    private IName defendantName;
    
    private String defendantNameStr;
    
	private String degreeOfOffense;
	
	private String displayCourtId;
	
	private String displayCaseNum;
	
	private String divisionId;
    
    private IName officerName;
    
    private Date officerAssignDate;

    private String orderStatusCode;

    private String programUnitId;

    private String programUnitName;
    
    private String superviseeName;

    private String supervisionOrderId;

    private String supervisionStyleCode;
    
    private String probationOfficerInd;

    private IName supervisorName;

    private Date terminationDate;    
    
    private String terminationDateStr;
    
    private Date programUnitAssignDate;
    
    private IPhoneNumber officerPhoneNumber;

	/**
	 * Probation begin date for the active case
	 */
	private String probationStartDate;
    /**
	 * Probation end date for the active case.
	 */
	private String probationEndDate;
	/**
	 * Number of probation days left for the case i.e. terminationDate - today
	 */
	private long daysLeft;
	/**
	 * Status of the active case
	 */
	private String caseStatus;
	/**
	 * Status of the defendant.
	 */
	private String defendantStatus;
	/**
	 * Position id of the supervisor
	 */
	private String supervisorPositionId;
	
	private String assignedStaffPositionId;
	
	private String caseAssignmentState;
	
    private String allocatedStaffPositionId;
    
    private String assignedStaffPositionName;
    
    private Date supervisorAllocationDate;
    
    private Date programUnitAssignmentTranactionDate;
    
    private Date officerAssignmentTransactionDate;

    /**
     * @return Returns the acknowledgePositionId.
     */
    public String getAcknowledgePositionId()
    {
        return acknowledgePositionId;
    }

    /**
     * @return Returns the acknowledgePositionName.
     */
    public String getAcknowledgePositionName()
    {
        return acknowledgePositionName;
    }

    /**
     * @return Returns the paperFileReceivedRoleCode.
     */
    public String getAcknowledgeRoleCode()
    {
        return acknowledgeRoleCode;
    }

    /**
     * @return Returns the acknowledgeUserId.
     */
    public String getAcknowledgeUserId()
    {
        return acknowledgeUserId;
    }

    /**
     * @return Returns the acknowledgeUserName.
     */
    public IName getAcknowledgeUserName()
    {
        return acknowledgeUserName;
    }

    /**
     * @return Returns the caseAssignmentId.
     */
    public String getCaseAssignmentId()
    {
        return caseAssignmentId;
    }

    /**
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
        return courtId;
    }

    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId()
    {
        return criminalCaseId;
    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @return Returns the defendantName.
     */
    public IName getDefendantName()
    {
        return defendantName;
    }

    /**
     * @return Returns the degreeOfOffense.
     */
    public String getDegreeOfOffense()
    {
        return degreeOfOffense;
    }

    /**
     * @return Returns the officerName.
     */
    public IName getOfficerName()
    {
        return officerName;
    }

    /**
     * @return Returns the orderStatusCode.
     */
    public String getOrderStatusCode()
    {
        return orderStatusCode;
    }

    /**
     * @return Returns the programUnitId.
     */
    public String getProgramUnitId()
    {
        return programUnitId;
    }

    /**
     * @return Returns the programUnitName.
     */
    public String getProgramUnitName()
    {
        return programUnitName;
    }

    /**
     * @return Returns the supervisionOrderId.
     */
    public String getSupervisionOrderId()
    {
        return supervisionOrderId;
    }

    /**
     * @return Returns the supervisionStyleCode.
     */
    public String getSupervisionStyleCode()
    {
        return supervisionStyleCode;
    }

    /**
     * @return Returns the supervisorName.
     */
    public IName getSupervisorName()
    {
        return supervisorName;
    }

    /**
     * @return Returns the terminationDate.
     */
    public Date getTerminationDate()
    {
        return terminationDate;
    }

    /**
     * @param acknowledgePositionId
     *        The acknowledgePositionId to set.
     */
    public void setAcknowledgePositionId(String acknowledgePositionId)
    {
        this.acknowledgePositionId = acknowledgePositionId;
    }

    /**
     * @param acknowledgePositionName
     *        The acknowledgePositionName to set.
     */
    public void setAcknowledgePositionName(String acknowledgePositionName)
    {
        this.acknowledgePositionName = acknowledgePositionName;
    }

    /**
     * @param paperFileReceivedRoleCode
     *        The paperFileReceivedRoleCode to set.
     */
    public void setAcknowledgeRoleCode(String acknowledgeRoleCode)
    {
        this.acknowledgeRoleCode = acknowledgeRoleCode;
    }

    /**
     * @param acknowledgeUserId
     *        The acknowledgeUserId to set.
     */
    public void setAcknowledgeUserId(String acknowledgeUserId)
    {
        this.acknowledgeUserId = acknowledgeUserId;
    }

    /**
     * @param acknowledgeName
     *        The acknowledgeName to set.
     */
    public void setAcknowledgeUserName(IName acknowledgeName)
    {
        this.acknowledgeUserName = acknowledgeName;
    }
    
    /**
     * @param caseAssignmentId
     *        The caseAssignmentId to set.
     */
    public void setCaseAssignmentId(String caseAssignmentId)
    {
        this.caseAssignmentId = caseAssignmentId;
    }

    /**
     * @param courtId
     *        The courtId to set.
     */
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }

    /**
     * @param criminalCaseId
     *        The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId)
    {
        this.criminalCaseId = criminalCaseId;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

    /**
     * @param defendantName
     *        The defendantName to set.
     */
    public void setDefendantName(IName defendantName)
    {
        this.defendantName = defendantName;
    }

    /**
     * @param degreeOfOffense
     *        The degreeOfOffense to set.
     */
    public void setDegreeOfOffense(String degreeOfOffense)
    {
        this.degreeOfOffense = degreeOfOffense;
    }

    /**
     * @param officerName
     *        The officerName to set.
     */
    public void setOfficerName(IName officerName)
    {
        this.officerName = officerName;
    }

    /**
     * @param orderStatusCode
     *        The orderStatusCode to set.
     */
    public void setOrderStatusCode(String orderStatusCode)
    {
        this.orderStatusCode = orderStatusCode;
    }

    /**
     * @param programUnitId
     *        The programUnitId to set.
     */
    public void setProgramUnitId(String programUnitId)
    {
        this.programUnitId = programUnitId;
    }

    /**
     * @param programUnitName
     *        The programUnitName to set.
     */
    public void setProgramUnitName(String programUnitName)
    {
        this.programUnitName = programUnitName;
    }

    /**
     * @param supervisionOrderId
     *        The supervisionOrderId to set.
     */
    public void setSupervisionOrderId(String supervisionOrderId)
    {
        this.supervisionOrderId = supervisionOrderId;
    }

    /**
     * @param supervisionStyleCode
     *        The supervisionStyleCode to set.
     */
    public void setSupervisionStyleCode(String supervisionStyleCode)
    {
        this.supervisionStyleCode = supervisionStyleCode;
    }

    /**
     * @param supervisorName
     *        The supervisorName to set.
     */
    public void setSupervisorName(IName supervisorName)
    {
        this.supervisorName = supervisorName;
    }

    /**
     * @param terminationDate
     *        The terminationDate to set.
     */
    public void setTerminationDate(Date terminationDate)
    {
        this.terminationDate = terminationDate;
    	this.terminationDateStr = "";

    	if (terminationDate != null) {

    		try {
    			this.terminationDateStr = DateUtil.dateToString(terminationDate, DateUtil.DATE_FMT_1);
    		} catch (Exception e) {
    			this.terminationDateStr = "";
    		}
    	}
    }
    
    /**
     * @return Returns the acknowledgeDate.
     */
    public Date getAcknowledgeDate()
    {
        return acknowledgeDate;
    }
    /**
     * @param acknowledgeDate The acknowledgeDate to set.
     */
    public void setAcknowledgeDate(Date acknowledgeDate)
    {
        this.acknowledgeDate = acknowledgeDate;
    }

    /* (non-Javadoc)
     * @see messaging.administercaseload.domintf.ICaseAssignment#getAcknowledgeStatusCode()
     */
    public String getAcknowledgeStatusCode() {
        return acknowledgeStatusCode;
    }

    /* (non-Javadoc)
     * @see messaging.administercaseload.domintf.ICaseAssignment#setAcknowledgeStatusCode(java.lang.String)
     */
    public void setAcknowledgeStatusCode(String acknowledgeStatusCode) {
        this.acknowledgeStatusCode = acknowledgeStatusCode;
        
    }
	/**
	 * @return Returns the caseStatus.
	 */
	public String getCaseStatus() {
		return caseStatus;
	}
	/**
	 * @param caseStatus The caseStatus to set.
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	/**
	 * @return Returns the daysLeft.
	 */
	public long getDaysLeft() {
		return daysLeft;
	}
	/**
	 * @param daysLeft The daysLeft to set.
	 */
	public void setDaysLeft(long daysLeft) {
		this.daysLeft = daysLeft;
	}
	/**
	 * @return Returns the defendantStatus.
	 */
	public String getDefendantStatus() {
		return defendantStatus;
	}
	/**
	 * @param defendantStatus The defendantStatus to set.
	 */
	public void setDefendantStatus(String defendantStatus) {
		this.defendantStatus = defendantStatus;
	}
	/**
	 * @return Returns the probationEndDate.
	 */
	public String getProbationEndDate() {
		return probationEndDate;
	}
	/**
	 * @param probationEndDate The probationEndDate to set.
	 */
	public void setProbationEndDate(String probationEndDate) {
		this.probationEndDate = probationEndDate;
	}
	/**
	 * @return Returns the probationStartDate.
	 */
	public String getProbationStartDate() {
		return probationStartDate;
	}
	/**
	 * @param probationStartDate The probationStartDate to set.
	 */
	public void setProbationStartDate(String probationStartDate) {
		this.probationStartDate = probationStartDate;
	}
	/**
	 * @return Returns the supervisorPositionId.
	 */
	public String getSupervisorPositionId() {
		return supervisorPositionId;
	}
	/**
	 * @param supervisorPositionId The supervisorPositionId to set.
	 */
	public void setSupervisorPositionId(String supervisorPositionId) {
		this.supervisorPositionId = supervisorPositionId;
	}

    /* (non-Javadoc)
     * @see messaging.administercaseload.domintf.ICaseAssignment#getOfficerAssignDate()
     */
    public Date getOfficerAssignDate() {
        return officerAssignDate;
    }

    /* (non-Javadoc)
     * @see messaging.administercaseload.domintf.ICaseAssignment#setOfficerAssignDate(java.util.Date)
     */
    public void setOfficerAssignDate(Date aDate) {
        this.officerAssignDate = aDate;
    }

    public String getAssignedStaffPositionId() {
    	return this.assignedStaffPositionId;
    }

    public void setAssignedStaffPositionId(String assignedStaffPositionId) {
    	this.assignedStaffPositionId = assignedStaffPositionId;
    }

    public String getCaseAssignmentState() {
    	return this.caseAssignmentState;
    }
    
    public void setCaseAssignmentState(String caseAssignmentState) {
    	this.caseAssignmentState = caseAssignmentState; 
    }
	/**
	 * @return Returns the allocatedStaffPositionId.
	 */
	public String getAllocatedStaffPositionId() {
		return allocatedStaffPositionId;
	}
	/**
	 * @param allocatedStaffPositionId The allocatedStaffPositionId to set.
	 */
	public void setAllocatedStaffPositionId(String allocatedStaffPositionId) {
		this.allocatedStaffPositionId = allocatedStaffPositionId;
	}
	
	/**
	 * @return Returns the programUnitAssignDate.
	 */
	public Date getProgramUnitAssignDate() {
		return programUnitAssignDate;
	}
	/**
	 * @param programUnitAssignDate The programUnitAssignDate to set.
	 */
	public void setProgramUnitAssignDate(Date programUnitAssignDate) {
		this.programUnitAssignDate = programUnitAssignDate;
	}
	
	public String getAssignedStaffPositionName() {
		return this.assignedStaffPositionName;
	}
	
	public void setAssignedStaffPositionName(String positionName) {
		this.assignedStaffPositionName = positionName; 
	}

	/**
	 * @return the probationOfficerInd
	 */
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}

	/**
	 * @param probationOfficerInd the probationOfficerInd to set
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}

	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
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
	 * @return the supervisorAllocationDate
	 */
	public Date getSupervisorAllocationDate() {
		return supervisorAllocationDate;
	}

	/**
	 * @param supervisorAllocationDate the supervisorAllocationDate to set
	 */
	public void setSupervisorAllocationDate(Date supervisorAllocationDate) {
		this.supervisorAllocationDate = supervisorAllocationDate;
	}

	public String getTerminationDateStr() {
		return terminationDateStr;
	}

	public void setTerminationDateStr(String terminationDateStr) {
		this.terminationDateStr = "";
		this.terminationDate = null;
		if ((terminationDateStr != null) && (!terminationDateStr.equalsIgnoreCase(""))) {
			try {
				this.terminationDateStr = terminationDateStr;
				this.terminationDate = DateUtil.stringToDate(terminationDateStr, DateUtil.DATE_FMT_1);
			} catch (Exception e) {
				this.terminationDate = null;
			}
		}
	}
	
    public String getCaseAssignmentHistId() {
		return caseAssignmentHistId;
	}

	public void setCaseAssignmentHistId(String caseAssignmentHistId) {
		this.caseAssignmentHistId = caseAssignmentHistId;
	}
	
	public IPhoneNumber getOfficerPhoneNumber() {
		return officerPhoneNumber;
	}

	public void setOfficerPhoneNumber(IPhoneNumber officerPhoneNumber) {
		this.officerPhoneNumber = officerPhoneNumber;
	}

	public Date getProgramUnitAssignmentTranactionDate() {
		return programUnitAssignmentTranactionDate;
	}

	public void setProgramUnitAssignmentTranactionDate(
			Date programUnitAssignmentTranactionDate) {
		this.programUnitAssignmentTranactionDate = programUnitAssignmentTranactionDate;
	}

	public Date getOfficerAssignmentTransactionDate() {
		return officerAssignmentTransactionDate;
	}

	public void setOfficerAssignmentTransactionDate(
			Date officerAssignmentTransactionDate) {
		this.officerAssignmentTransactionDate = officerAssignmentTransactionDate;
	}
	
    public String getDefendantNameStr() {
		return defendantNameStr;
	}

	public void setDefendantNameStr(String defendantNameStr) {
		this.defendantNameStr = defendantNameStr;
	}
	
	/**
	 * @return the displayCourtId
	 */
	public String getDisplayCourtId() {
		return displayCourtId;
	}

	/**
	 * @param displayCourtId the displayCourtId to set
	 */
	public void setDisplayCourtId(String displayCourtId) {
		this.displayCourtId = displayCourtId;
	}

	/**
	 * @return the displayCaseNum
	 */
	public String getDisplayCaseNum() {
		return displayCaseNum;
	}

	/**
	 * @param displayCaseNum the displayCaseNum
	 */
	public void setDisplayCaseNum(String displayCaseNum) {
		this.displayCaseNum = displayCaseNum;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	/**
	 * @return the cdiandCaseNumber
	 */
	public String getCdiandCaseNumber() {
		String str = this.criminalCaseId;
		if (str != null && str.length() > 3){
			str = str.substring(0, 3) + " " + str.substring(3, str.length());
		}	
		return str;
	}
	
}
