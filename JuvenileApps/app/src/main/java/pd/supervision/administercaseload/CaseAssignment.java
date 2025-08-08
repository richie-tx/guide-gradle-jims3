package pd.supervision.administercaseload;

import java.util.Date;
import java.util.Iterator;

import naming.CaseloadConstants;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCase;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * This is the date the supervision of the Supervisee ends.
 */
public class CaseAssignment extends PersistentObject
{
    /**
     * 
     * @roseuid 46433E1900C1
     */
    public static CaseAssignment find(String anOid)
    {
        IHome home = new Home();
        return (CaseAssignment) home.find(anOid, CaseAssignment.class);
    }
    public static Iterator findAll(IEvent anEvent){
        IHome home = new Home();
        return home.findAll(anEvent, CaseAssignment.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        IHome home = new Home();
        return home.findAll(attrName, attrValue, CaseAssignment.class);
    }
    /**
     * Properties for officerPosition
     * 
     * @referencedType pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
     * @detailerDoNotGenerate true
     */
    private CSCDStaffPosition acknowledgePosition;

    private String acknowledgePositionId;

    /**
     * Properties for acknowledgeRole
     * 
     * @referencedType pd.codetable.Code
     * @contextKey CSCASEASSIGNACKNOWLEDGEROLE
     * @detailerDoNotGenerate true
     */
    private Code acknowledgeRole;

    private String acknowledgeRoleId;
    
    /**
     * Properties for acknowledgeRole
     * 
     * @referencedType pd.codetable.Code
     * @contextKey CSCASEASSIGNACKNOWLEDGMENTSTATUS
     * @detailerDoNotGenerate true
     */
    private Code acknowledgeStatus;

    private String acknowledgeStatusId;

    private UserProfile acknowledgeUser;

    private String acknowledgeUserId;
    
    private Date acknowledgeDate;
    
    /**
     * Properties for criminalCase
     * 
     * @referencedType pd.criminalcase.CriminalCase
     * @detailerDoNotGenerate true
     */
    private CriminalCase criminalCase;
    private String criminalCaseId;
    private String defendantId;

    /**
     * Properties for history
     * 
     * @associationType simple
     * @referencedType pd.supervision.administercaseload.CaseAssignmentHistory
     * @detailerDoNotGenerate true
     */
    private java.util.Collection history = null;
    private Date officerAssignDate;

    /**
     * Properties for programUnit
     * 
     * @referencedType pd.supervision.supervisionstaff.Organization
     * @detailerDoNotGenerate true
     */
    private Organization programUnit;
    private Date programUnitAssignDate;

    private String programUnitId;

    /**
     * Properties for supervisionOrder
     * 
     * @referencedType pd.supervision.supervisionorder.SupervisionOrder
     * @detailerDoNotGenerate true
     */
    private SupervisionOrder supervisionOrder;

    private String supervisionOrderId;

    /**
     * Properties for supervisionStyle
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SUPERVISIONSTYLE
     * @detailerDoNotGenerate true
     */
    private Code supervisionStyle = null;

    private String supervisionStyleId;

    /**
     * This is the date the supervision of the Supervisee ends.
     */
    private Date terminationDate;

    private CSCDStaffPosition assignedStaffPosition;
    
    private String assignedStaffPositionId;
    
    private String caseState;

    private CSCDStaffPosition allocatedStaffPosition;

    private String allocatedStaffPositionId;
    
    private Date supervisorAllocationDate;

	/**
     * 
     * @roseuid 46435FE1001E
     */
    public CaseAssignment()
    {
    }

    /**
     * @param caseAssignmentBean
     */
    public void acknowledge(ICaseAssignment aCaseAssignment) {
        this.setAcknowledgeRoleId(aCaseAssignment.getAcknowledgeRoleCode());
        this.setAcknowledgePositionId(aCaseAssignment.getAcknowledgePositionId());
        this.setAcknowledgeUserId(aCaseAssignment.getAcknowledgeUserId());
        this.setAcknowledgeStatusId(aCaseAssignment.getAcknowledgeStatusCode());
        this.setAcknowledgeDate(aCaseAssignment.getAcknowledgeDate());
    }

    /**
     *  
     */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }

    /**
     * Clears all pd.supervision.administercaseload.CaseAssignmentHistory from class relationship
     * collection.
     */
    public void clearHistory()
    {
        initHistory();
        history.clear();
    }

    public void fill(ICaseAssignment aCaseAssignment)
    {
        this.setAcknowledgePositionId(aCaseAssignment.getAcknowledgePositionId());
        this.setAcknowledgeRoleId(aCaseAssignment.getAcknowledgeRoleCode());
        this.setAcknowledgeUserId(aCaseAssignment.getAcknowledgeUserId());
        this.setAcknowledgeDate(aCaseAssignment.getAcknowledgeDate());
        this.setProgramUnitId(aCaseAssignment.getProgramUnitId());
        this.setSupervisionOrderId(aCaseAssignment.getSupervisionOrderId());
        this.setSupervisionStyleId(aCaseAssignment.getSupervisionStyleCode());
        this.setDefendantId(aCaseAssignment.getDefendantId());
        this.setCriminalCaseId(aCaseAssignment.getCriminalCaseId());
    }

    /**
     * @return Returns the acknowledgePositionId.
     */
    public CSCDStaffPosition getAcknowledgePosition()
    {
        initAcknowledgePosition();
        return acknowledgePosition;
    }


    /**
     * @return Returns the acknowledgePositionId.
     */
    public String getAcknowledgePositionId()
    {
        fetch();
        return acknowledgePositionId;
    }

    /**
     * @return Returns the acknowledgeRoleId.
     */
    public String getAcknowledgeRoleId()
    {
        fetch();
        return acknowledgeRoleId;
    }
    /**
     * @return Returns the acknowledgeStatusId.
     */
    public String getAcknowledgeStatusId()
    {
        fetch();
        return acknowledgeStatusId;
    }

    /**
     * @return
     */
    public UserProfile getAcknowledgeUser()
    {
        initAcknowledgeUser();
        return acknowledgeUser;
    }

    /**
     * @return Returns the acknowledgeUserId.
     */
    public String getAcknowledgeUserId()
    {
        fetch();
        return acknowledgeUserId;
    }
    /**
     * @return Returns the criminalCase.
     */
    public CriminalCase getCriminalCase()
    {
        initCriminalCase();
        return criminalCase;
    }
    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId()
    {
        fetch();
        return criminalCaseId;
    }
    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId() {
        fetch();
        return defendantId;
    }

    /**
     * returns a collection of pd.supervision.administercaseload.CaseAssignmentHistory
     */
    public java.util.Collection getHistory()
    {
        initHistory();
        return history;
    }
    /**
     * @return Returns the officerAssignDate.
     */
    public Date getOfficerAssignDate() {
        fetch();
        return officerAssignDate;
    }

    /**
     * Gets referenced type pd.supervision.supervisionstaff.Organization
     */
    public Organization getProgramUnit()
    {
        initProgramUnit();
        return programUnit;
    }
    /**
     * @return Returns the programUnitAssignDate.
     */
    public Date getProgramUnitAssignDate() {
        fetch();
        return programUnitAssignDate;
    }

    /**
     * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
     */
    public String getProgramUnitId()
    {
        fetch();
        return programUnitId;
    }

    /**
     * Gets referenced type pd.supervision.supervisionorder.SupervisionOrder
     */
    public SupervisionOrder getSupervisionOrder()
    {
        initSupervisionOrder();
        return supervisionOrder;
    }

    /**
     * Get the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
     */
    public String getSupervisionOrderId()
    {
        fetch();
        return supervisionOrderId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getSupervisionStyle()
    {
        initSupervisionStyle();
        return supervisionStyle;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getSupervisionStyleId()
    {
        fetch();
        return supervisionStyleId;
    }

    /**
     * @return Returns the terminationDate.
     */
    public Date getTerminationDate()
    {
        fetch();
        return terminationDate;
    }

	public CSCDStaffPosition getAssignedStaffPosition() {
		if (assignedStaffPosition == null) {
			assignedStaffPosition = (CSCDStaffPosition) new Reference(assignedStaffPositionId, CSCDStaffPosition.class)
					.getObject();
		}
		return assignedStaffPosition;
	}

	/**
	 * @return Returns the assignedStaffPositionId.
	 */
	public String getAssignedStaffPositionId() {
        fetch();
		return assignedStaffPositionId;
	}
	/**
	 * @return Returns the caseState.
	 */
	public String getCaseState() {
        fetch();
		return caseState;
	}

	/**
	 * @return Returns the allocatedStaffPositionId.
	 */
	public String getAllocatedStaffPositionId() {
        fetch();
		return allocatedStaffPositionId;
	}

	/**
	 * @return Returns the allocateStaffPosition.
	 */
	public CSCDStaffPosition getAllocatedStaffPosition() {
		if (allocatedStaffPosition == null) {
			allocatedStaffPosition = (CSCDStaffPosition) new Reference(allocatedStaffPositionId, CSCDStaffPosition.class)
					.getObject();
		}
		return allocatedStaffPosition;
	}

	/**
	 * @return the supervisorAllocationDate
	 */
	public Date getSupervisorAllocationDate() {
        fetch();
		return this.supervisorAllocationDate;
	}

	/**
	 * @return the acknowledgeDate
	 */
	public Date getAcknowledgeDate() {
        fetch();
		return acknowledgeDate;
	}

    /**
     *  
     */
    private void initAcknowledgePosition()
    {
        if (acknowledgePosition == null)
        {
            acknowledgePosition = (CSCDStaffPosition) new Reference(acknowledgePositionId, CSCDStaffPosition.class)
                    .getObject();
        }
    }

    /**
     *  
     */
    private void initAcknowledgeUser()
    {
        if (acknowledgeUser == null)
        {
            acknowledgeUser = (UserProfile) new Reference(acknowledgeUserId, UserProfile.class).getObject();
        }
    }
    private void initCriminalCase()
    {
        if (criminalCase == null)
        {
            criminalCase = (CriminalCase) new Reference(criminalCaseId, CriminalCase.class)
                    .getObject();
        }
    }

    /**
     * Initialize class relationship implementation for
     * pd.supervision.administercaseload.CaseAssignmentHistory
     */
    private void initHistory()
    {
        if (history == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }
            history = new mojo.km.persistence.ArrayList(CaseAssignmentHistory.class, "caseAssignmentId", (String) getOID());
        }
    }

    /**
     * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
     */
    private void initProgramUnit()
    {
        if (programUnit == null)
        {
            programUnit = (Organization) new Reference(programUnitId, Organization.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.supervision.supervisionorder.SupervisionOrder
     */
    private void initSupervisionOrder()
    {
        if (supervisionOrder == null)
        {
            supervisionOrder = (SupervisionOrder) new Reference(this.supervisionOrderId, SupervisionOrder.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSupervisionStyle()
    {
        if (supervisionStyle == null)
        {
            supervisionStyle = (Code) new Reference(supervisionStyleId, Code.class).getObject();
        }
    }

    /**
     * insert a pd.supervision.administercaseload.CaseAssignmentHistory into class relationship
     * collection.
     */
    public void insertHistory(CaseAssignmentHistory anObject)
    {
        initHistory();
        history.add(anObject);
    }

    /**
     * Removes a pd.supervision.administercaseload.CaseAssignmentHistory from class relationship
     * collection.
     */
    public void removeHistory(CaseAssignmentHistory anObject)
    {
        initHistory();
        history.remove(anObject);
    }

    /**
     * @param acknowledgePositionId
     *        The acknowledgePositionId to set.
     */
    public void setAcknowledgePositionId(String acknowledgePositionId)
    {
        if (this.acknowledgePositionId == null || !this.acknowledgePositionId.equals(acknowledgePositionId))
        {
            markModified();
        }
        acknowledgePosition = null;
        this.acknowledgePositionId = acknowledgePositionId;
    }

    /**
     * @param acknowledgeRoleCode
     *        The acknowledgeRoleCode to set.
     */
    public void setAcknowledgeRoleId(String acknowledgeRoleId)
    {
        if (this.acknowledgeRoleId == null || !this.acknowledgeRoleId.equals(acknowledgeRoleId))
        {
            markModified();
        }
        acknowledgeRole = null;
        this.acknowledgeRoleId = acknowledgeRoleId;
    }
    /**
     * @param acknowledgeStatusCode
     *        The acknowledgeStatusCode to set.
     */
    public void setAcknowledgeStatusId(String acknowledgeStatusId)
    {
        if (this.acknowledgeStatusId == null || !this.acknowledgeStatusId.equals(acknowledgeStatusId))
        {
            markModified();
        }
        acknowledgeStatus = null;
        this.acknowledgeStatusId = acknowledgeStatusId;
    }

    /**
     * @param acknowledgeUserId
     *        The acknowledgeUserId to set.
     */
    public void setAcknowledgeUserId(String acknowledgeUserId)
    {
        if (this.acknowledgeUserId == null || !this.acknowledgeUserId.equals(acknowledgeUserId))
        {
            markModified();
        }
        acknowledgeUser = null;
        this.acknowledgeUserId = acknowledgeUserId;
    }

    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId))
        {
            markModified();
        }
        criminalCase = null;
        this.criminalCaseId = criminalCaseId;
    }
    /**
     * @param defendantId The defendantId to set.
     */
    public void setDefendantId(String defendantId) {
        if (this.defendantId == null || !this.defendantId.equals(defendantId))
        {
            markModified();
        }
        this.defendantId = defendantId;
    }
    /**
     * @param officerAssignDate The officerAssignDate to set.
     */
    public void setOfficerAssignDate(Date officerAssignDate) {
        if (this.officerAssignDate == null || !this.officerAssignDate.equals(officerAssignDate))
        {
            markModified();
        }
        this.officerAssignDate = officerAssignDate;
    }

    /**
     * set the type reference for class member programUnit
     */
    public void setProgramUnit(Organization programUnit)
    {
        if (this.programUnit == null || !this.programUnit.equals(programUnit))
        {
            markModified();
        }
        if (programUnit.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(programUnit);
        }
        setProgramUnitId((String) programUnit.getOID());
        this.programUnit = (Organization) new Reference(programUnit).getObject();
    }
    /**
     * @param programUnitAssignDate The programUnitAssignDate to set.
     */
    public void setProgramUnitAssignDate(Date programUnitAssignDate) {
        if (this.programUnitAssignDate == null || !this.programUnitAssignDate.equals(programUnitAssignDate))
        {
            markModified();
        }
        this.programUnitAssignDate = programUnitAssignDate;
    }

    /**
     * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
     */
    public void setProgramUnitId(String programUnitId)
    {
        if (this.programUnitId == null || !this.programUnitId.equals(programUnitId))
        {
            markModified();
        }
        programUnit = null;
        this.programUnitId = programUnitId;
    }

    /**
     * set the type reference for class member supervisionOrder
     */
    public void setSupervisionOrder(SupervisionOrder supervisionOrder)
    {
        if (this.supervisionOrder == null || !this.supervisionOrder.equals(supervisionOrder))
        {
            markModified();
        }
        if (supervisionOrder.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(supervisionOrder);
        }
        setSupervisionOrderId((String) supervisionOrder.getOID());
        this.supervisionOrder = (SupervisionOrder) new Reference(supervisionOrder).getObject();
    }

    /**
     * Set the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
     */
    public void setSupervisionOrderId(String supervisionOrderId)
    {
        if (this.supervisionOrderId == null || !this.supervisionOrderId.equals(supervisionOrderId))
        {
            markModified();
        }
        supervisionOrder = null;
        this.supervisionOrderId = supervisionOrderId;
    }

    /**
     * set the type reference for class member supervisionStyle
     */
    public void setSupervisionStyle(Code supervisionStyle)
    {
        if (this.supervisionStyle == null || !this.supervisionStyle.equals(supervisionStyle))
        {
            markModified();
        }
        if (supervisionStyle.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(supervisionStyle);
        }
        setSupervisionStyleId((String) supervisionStyle.getOID());
        this.supervisionStyle = (Code) new Reference(supervisionStyle).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setSupervisionStyleId(String supervisionStyleId)
    {
        if (this.supervisionStyleId == null || !this.supervisionStyleId.equals(supervisionStyleId))
        {
            markModified();
        }
        supervisionStyle = null;
        this.supervisionStyleId = supervisionStyleId;
    }

    /**
     * @param terminationDate
     *        The terminationDate to set.
     */
    public void setTerminationDate(Date terminationDate)
    {
        if (this.terminationDate == null || !this.terminationDate.equals(terminationDate))
        {
            markModified();
        }
        this.terminationDate = terminationDate;
    }

	/**
	 * @param assignStaffPosition
	 *            The assignStaffPosition to set.
	 */
	public void setAssignedStaffPosition(CSCDStaffPosition assignStaffPosition) {
		if (this.assignedStaffPosition == null || !this.assignedStaffPosition.equals(assignStaffPosition)) {
			markModified();
		}
		if (assignStaffPosition.getOID() == null) {
			new mojo.km.persistence.Home().bind(assignStaffPosition);
		}
		setAssignedStaffPositionId((String) assignStaffPosition.getOID());
		this.assignedStaffPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(assignStaffPosition)
				.getObject();
	}

	/**
	 * @param assignStaffPositionId
	 *            The assignStaffPositionId to set.
	 */
	public void setAssignedStaffPositionId(String assignStaffPositionId) {
		if (this.assignedStaffPositionId == null || !this.assignedStaffPositionId.equals(assignStaffPositionId)) {
			markModified();
		}
		this.assignedStaffPosition = null;
		this.assignedStaffPositionId = assignStaffPositionId;
	}

	/**
	 * @param caseState The caseState to set.
	 */
	public void setCaseState(String caseState) {
        if (this.caseState == null || !this.caseState.equals(caseState))
        {
            markModified();
        }
		this.caseState = caseState;
	}
	
	/**
	 * @param allocatedStaffPositionId The allocatedStaffPositionId to set.
	 */
	public void setAllocatedStaffPositionId(String allocateStaffPositionId) {
		if (this.allocatedStaffPositionId == null || !this.allocatedStaffPositionId.equals(allocateStaffPositionId)) {
			markModified();
		}
		this.allocatedStaffPosition = null;
		this.allocatedStaffPositionId = allocateStaffPositionId;
	}
	
	public void setAllocatedStaffPosition(CSCDStaffPosition allocateStaffPosition) {
		if (this.allocatedStaffPosition == null || !this.allocatedStaffPosition.equals(allocateStaffPosition)) {
			markModified();
		}
		if (allocateStaffPosition.getOID() == null) {
			new mojo.km.persistence.Home().bind(allocateStaffPosition);
		}
		setAllocatedStaffPositionId((String) allocateStaffPosition.getOID());
		this.allocatedStaffPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(allocateStaffPosition)
				.getObject();
	}
	
	/**
	 * @param supervisorAllocationDate the supervisorAllocationDate to set
	 */
	public void setSupervisorAllocationDate(Date supervisorAllocationDate) {
        if (this.supervisorAllocationDate == null || !this.supervisorAllocationDate.equals(supervisorAllocationDate))
        {
             markModified();
        }
		this.supervisorAllocationDate = supervisorAllocationDate;
	}

	/**
	 * @param acknowledgeDate the acknowledgeDate to set
	 */
	public void setAcknowledgeDate(Date acknowledgeDate) {
        if (this.acknowledgeDate == null || !this.acknowledgeDate.equals(acknowledgeDate))
        {
            markModified();
        }
		this.acknowledgeDate = acknowledgeDate;
	}
	
    public ICaseAssignment valueObject() {
        ICaseAssignment caseAssignment = new CaseAssignmentTO();
        caseAssignment.setCaseAssignmentId((String) this.getOID());
        caseAssignment.setSupervisionOrderId(this.getSupervisionOrderId());
        caseAssignment.setProgramUnitId(this.getProgramUnitId());
        caseAssignment.setAcknowledgePositionId(this.getAcknowledgePositionId());
        caseAssignment.setAcknowledgeUserId(this.getAcknowledgeUserId());
        caseAssignment.setAcknowledgeDate(this.getAcknowledgeDate());
//        caseAssignment.setAcknowledgeRoleCode(this.getAcknowledgeRoleId());
        caseAssignment.setSupervisionStyleCode(this.getSupervisionStyleId());
        caseAssignment.setAcknowledgeStatusCode(this.getAcknowledgeStatusId());
        caseAssignment.setCriminalCaseId(this.getCriminalCaseId());
        caseAssignment.setDefendantId(this.getDefendantId());
        caseAssignment.setAssignedStaffPositionId(this.getAssignedStaffPositionId());
        caseAssignment.setCaseAssignmentState(this.getCaseState());
        caseAssignment.setAllocatedStaffPositionId(this.getAllocatedStaffPositionId());
        caseAssignment.setProgramUnitAssignDate(this.getProgramUnitAssignDate());
        caseAssignment.setOfficerAssignDate(this.getOfficerAssignDate());
        caseAssignment.setSupervisorAllocationDate(this.getSupervisorAllocationDate());
       return caseAssignment;
    }
    
    public void update(ICaseAssignment caseAssignmentTO) {
    	this.setSupervisionOrderId(caseAssignmentTO.getSupervisionOrderId());
    	this.setAcknowledgePositionId(caseAssignmentTO.getAcknowledgePositionId());
    	this.setAcknowledgeUserId(caseAssignmentTO.getAcknowledgeUserId());
    	this.setAcknowledgeStatusId(caseAssignmentTO.getAcknowledgeStatusCode());
        this.setAcknowledgeDate(caseAssignmentTO.getAcknowledgeDate());
    	this.setSupervisionStyleId(caseAssignmentTO.getSupervisionStyleCode());
    	this.setCriminalCaseId(caseAssignmentTO.getCriminalCaseId());
    	this.setDefendantId(caseAssignmentTO.getDefendantId());
    	this.setCaseState(caseAssignmentTO.getCaseAssignmentState());
    	
    	if (this.caseState.equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)) {
        	this.setProgramUnitId(caseAssignmentTO.getProgramUnitId());
        	this.setProgramUnitAssignDate(caseAssignmentTO.getProgramUnitAssignDate());
        	this.setAllocatedStaffPositionId(null);
        	this.setSupervisorAllocationDate(null);
        	this.setAssignedStaffPositionId(null);
        	this.setOfficerAssignDate(null);
    	} else if (this.caseState.equals(CaseloadConstants.SUPERVISOR_ALLOCATED)) {
        	this.setProgramUnitId(caseAssignmentTO.getProgramUnitId());
        	this.setProgramUnitAssignDate(caseAssignmentTO.getProgramUnitAssignDate());
        	this.setAllocatedStaffPositionId(caseAssignmentTO.getAllocatedStaffPositionId());
        	this.setSupervisorAllocationDate(caseAssignmentTO.getSupervisorAllocationDate());
        	this.setAssignedStaffPositionId(null);
        	this.setOfficerAssignDate(null);
    	} else if (this.caseState.equals(CaseloadConstants.OFFICER_ASSIGNED)) {
        	this.setAssignedStaffPositionId(caseAssignmentTO.getAssignedStaffPositionId());
        	this.setOfficerAssignDate(caseAssignmentTO.getOfficerAssignDate());
    	} 
    	else if(this.caseState.equalsIgnoreCase(CaseloadConstants.CASE_CLOSED))
    	{
    		this.setTerminationDate(caseAssignmentTO.getTerminationDate());
    	}
    	
        CaseAssignmentHistory history = new CaseAssignmentHistory();
        history.update(caseAssignmentTO);
    }
}
