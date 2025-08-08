package pd.supervision.administercaseload;

import java.util.Date;
import java.util.Iterator;

import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.supervision.administercasenotes.CasenoteConditions;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @roseuid 46435FE9003E
 */
public class CaseAssignmentHistory extends PersistentObject {
	private String caseAssignmentId;

	/**
	 * Type of case assignment history
	 */
	private String historyType;

	/**
	 * Termination date of case
	 */
	private Date terminationDate;

	/**
	 * Properties for ProgramUnit
	 */
	private Organization programUnit;

	private String programUnitId;

	private Date programUnitAssignDate;

	/**
	 * Assigned staff position i.e. officer
	 */
	private CSCDStaffPosition assignedStaffPosition;

	private String assignedStaffPositionId;

	private Date officerAssignDate;

	/**
	 * Allocated staff position i.e. supervisor
	 */
	private CSCDStaffPosition allocatedStaffPosition;

	private String allocatedStaffPositionId;

	/**
	 * Properties for acknowledgePosition
	 */
	private CSCDStaffPosition acknowledgePosition;

	private String acknowledgePositionId;

    private Date supervisorAllocationDate;

	/**
	 * Properties for assignedAcknowledgeUser
	 */
	private UserProfile acknowledgeUser;

	private String acknowledgeUserId;

    private Date acknowledgeDate;

	/**
	 * Properties for supervisionStyle
	 */
	private Code supervisionStyle;

	private String supervisionStyleId;

	/**
	 * Acknowledge status id
	 */
	private String acknowledgeStatusId;

	private String courtNum;

	/**
	 * @roseuid 46435FE9003E
	 */
	public CaseAssignmentHistory() {
	}

	public static CaseAssignmentHistory find(String anOid) {
		IHome home = new Home();
		return (CaseAssignmentHistory) home.find(anOid, CaseAssignmentHistory.class);
	}

	public static Iterator findAll(IEvent anEvent) {
		IHome home = new Home();
		return home.findAll(anEvent, CaseAssignmentHistory.class);
	}

	public static Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, attrValue, CaseAssignmentHistory.class);
	}
	
    public static Iterator findAllByNumericParam(String attributeName, String attributeValue){
	    return new Home().findAll(attributeName, new Integer(attributeValue), CaseAssignmentHistory.class);
    }

	/**
	 * @return Returns the acknowledgePosition.
	 */
	public CSCDStaffPosition getAcknowledgePosition() {
		if (acknowledgePosition == null) {
			acknowledgePosition = (CSCDStaffPosition) new Reference(acknowledgePositionId, CSCDStaffPosition.class)
					.getObject();
		}
		return acknowledgePosition;
	}

	/**
	 * @return Returns the acknowledgePositionId.
	 */
	public String getAcknowledgePositionId() {
		fetch();
		return acknowledgePositionId;
	}

	/**
	 * @return Returns the acknowledgeStatusId.
	 */
	public String getAcknowledgeStatusId() {
		fetch();
		return acknowledgeStatusId;
	}

	/**
	 * @return Returns the acknowledgeUser.
	 */
	public UserProfile getAcknowledgeUser() {
		if (acknowledgeUser == null) {
			acknowledgeUser = (UserProfile) new Reference(acknowledgeUserId, UserProfile.class).getObject();
		}
		return acknowledgeUser;
	}

	/**
	 * @return Returns the acknowledgeUserId.
	 */
	public String getAcknowledgeUserId() {
		fetch();
		return acknowledgeUserId;
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
	 * @return Returns the allocateStaffPositionId.
	 */
	public String getAllocatedStaffPositionId() {
		fetch();
		return allocatedStaffPositionId;
	}

	/**
	 * @return Returns the assignStaffPosition.
	 */
	public CSCDStaffPosition getAssignedStaffPosition() {
		if (assignedStaffPosition == null) {
			assignedStaffPosition = (CSCDStaffPosition) new Reference(assignedStaffPositionId, CSCDStaffPosition.class)
					.getObject();
		}
		return assignedStaffPosition;
	}

	/**
	 * @return Returns the assignStaffPositionId.
	 */
	public String getAssignedStaffPositionId() {
		fetch();
		return assignedStaffPositionId;
	}

	/**
	 * @return Returns the caseAssignmentId.
	 */
	public String getCaseAssignmentId() {
		fetch();
		return caseAssignmentId;
	}

	/**
	 * @return Returns the courtNum.
	 */
	public String getCourtNum() {
		fetch();
		return courtNum;
	}

	/**
	 * @return Returns the historyType.
	 */
	public String getHistoryType() {
		fetch();
		return historyType;
	}

	/**
	 * @return Returns the officerAssignDate.
	 */
	public Date getOfficerAssignDate() {
		fetch();
		return officerAssignDate;
	}

	/**
	 * @return Returns the programUnit.
	 */
	public Organization getProgramUnit() {
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
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		fetch();
		return programUnitId;
	}

	/**
	 * @return Returns the supervisionStyle.
	 */
	public Code getSupervisionStyle() {
		if (supervisionStyle == null) {
			supervisionStyle = (Code) new mojo.km.persistence.Reference(supervisionStyleId,
					Code.class).getObject();
		}
		return supervisionStyle;
	}

	/**
	 * @return Returns the supervisionStyleId.
	 */
	public String getSupervisionStyleId() {
		fetch();
		return supervisionStyleId;
	}

	/**
	 * @return Returns the terminationDate.
	 */
	public Date getTerminationDate() {
		fetch();
		return terminationDate;
	}

	/**
	 * @return the acknowledgeDate
	 */
	public Date getAcknowledgeDate() {
		fetch();
		return acknowledgeDate;
	}

	/**
	 * @return the supervisorAllocationDate
	 */
	public Date getSupervisorAllocationDate() {
		fetch();
		return supervisorAllocationDate;
	}

	/**
	 * @param acknowledgePosition
	 *            The acknowledgePosition to set.
	 */
	public void setAcknowledgePosition(CSCDStaffPosition acknowledgePosition) {
		if (this.acknowledgePosition == null || !this.acknowledgePosition.equals(acknowledgePosition)) {
			markModified();
		}
		if (acknowledgePosition.getOID() == null) {
			new mojo.km.persistence.Home().bind(acknowledgePosition);
		}
		setAcknowledgePositionId((String) acknowledgePosition.getOID());
		this.acknowledgePosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(acknowledgePosition)
				.getObject();
	}

	/**
	 * @param acknowledgePositionId
	 *            The acknowledgePositionId to set.
	 */
	public void setAcknowledgePositionId(String acknowledgePositionId) {
		if (this.acknowledgePositionId == null || !this.acknowledgePositionId.equals(acknowledgePositionId)) {
			markModified();
		}
		acknowledgePosition = null;
		this.acknowledgePositionId = acknowledgePositionId;
	}

	/**
	 * @param acknowledgeStatusId
	 *            The acknowledgeStatusId to set.
	 */
	public void setAcknowledgeStatusId(String acknowledgeStatusId) {
		this.acknowledgeStatusId = acknowledgeStatusId;
	}

	/**
	 * @param acknowledgeUser
	 *            The acknowledgeUser to set.
	 */
	public void setAcknowledgeUser(UserProfile acknowledgeUser) {
		if (this.acknowledgeUser == null || !this.acknowledgeUser.equals(acknowledgeUser)) {
			markModified();
		}
		if (acknowledgeUser.getOID() == null) {
			new mojo.km.persistence.Home().bind(acknowledgeUser);
		}
		setAcknowledgeUserId((String) acknowledgeUser.getOID());
		this.acknowledgeUser = (UserProfile) new Reference(acknowledgeUser).getObject();
	}

	/**
	 * @param acknowledgeUserId
	 *            The acknowledgeUserId to set.
	 */
	public void setAcknowledgeUserId(String acknowledgeUserId) {
		if (this.acknowledgeUserId == null || !this.acknowledgeUserId.equals(acknowledgeUserId)) {
			markModified();
		}
		this.acknowledgeUser = null;
		this.acknowledgeUserId = acknowledgeUserId;
	}

	/**
	 * @param allocatedStaffPosition
	 *            The allocatedStaffPosition to set.
	 */
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
	 * @param allocateStaffPositionId
	 *            The allocateStaffPositionId to set.
	 */
	public void setAllocatedStaffPositionId(String allocateStaffPositionId) {
		if (this.allocatedStaffPositionId == null || !this.allocatedStaffPositionId.equals(allocateStaffPositionId)) {
			markModified();
		}
		this.allocatedStaffPosition = null;
		this.allocatedStaffPositionId = allocateStaffPositionId;
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
	 * @param caseAssignmentId
	 *            The caseAssignmentId to set.
	 */
	public void setCaseAssignmentId(String caseAssignmentId) {
		if (this.caseAssignmentId == null || !this.caseAssignmentId.equals(caseAssignmentId)) {
			markModified();
		}
		this.caseAssignmentId = caseAssignmentId;
	}

	/**
	 * @param courtNum
	 *            The courtNum to set.
	 */
	public void setCourtNum(String courtNum) {
		if (this.courtNum == null || !this.courtNum.equals(courtNum)) {
			markModified();
		}
		this.courtNum = courtNum;
	}

	/**
	 * @param historyType
	 *            The historyType to set.
	 */
	public void setHistoryType(String historyType) {
		if (this.historyType == null || !this.historyType.equals(historyType)) {
			markModified();
		}
		this.historyType = historyType;
	}

	/**
	 * @param officerAssignDate
	 *            The officerAssignDate to set.
	 */
	public void setOfficerAssignDate(Date officerAssignDate) {
		if (this.officerAssignDate == null || !this.officerAssignDate.equals(officerAssignDate)) {
			markModified();
		}
		this.officerAssignDate = officerAssignDate;
	}

	/**
	 * @param programUnit
	 *            The programUnit to set.
	 */
	public void setProgramUnit(Organization programUnit) {
		if (this.programUnit == null || !this.programUnit.equals(programUnit)) {
			markModified();
		}
		if (programUnit.getOID() == null) {
			new mojo.km.persistence.Home().bind(programUnit);
		}
		setProgramUnitId(programUnit.getOID().toString());
		this.programUnit = (Organization) new mojo.km.persistence.Reference(programUnit)
				.getObject();
	}

	/**
	 * @param programUnitAssignDate
	 *            The programUnitAssignDate to set.
	 */
	public void setProgramUnitAssignDate(Date programUnitAssignDate) {
		if (this.programUnitAssignDate == null || !this.programUnitAssignDate.equals(programUnitAssignDate)) {
			markModified();
		}
		this.programUnitAssignDate = programUnitAssignDate;
	}

	/**
	 * @param programUnitId
	 *            The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		if (this.programUnitId == null || !this.programUnitId.equals(programUnitId)) {
			markModified();
		}
		this.programUnit = null;
		this.programUnitId = programUnitId;
	}

	/**
	 * @param supervisionStyle
	 *            The supervisionStyle to set.
	 */
	public void setSupervisionStyle(Code supervisionStyle) {
		if (this.supervisionStyle == null || !this.supervisionStyle.equals(supervisionStyle)) {
			markModified();
		}
		if (supervisionStyle.getOID() == null) {
			new mojo.km.persistence.Home().bind(supervisionStyle);
		}
		setSupervisionStyleId((String) supervisionStyle.getOID());
		this.supervisionStyle = (Code) new Reference(supervisionStyle).getObject();
	}

	/**
	 * @param supervisionStyleId
	 *            The supervisionStyleId to set.
	 */
	public void setSupervisionStyleId(String supervisionStyleId) {
		if (this.supervisionStyleId == null || !this.supervisionStyleId.equals(supervisionStyleId)) {
			markModified();
		}
		this.supervisionStyle = null;
		this.supervisionStyleId = supervisionStyleId;
	}

	/**
	 * @param terminationDate
	 *            The terminationDate to set.
	 */
	public void setTerminationDate(Date terminationDate) {
		if (this.terminationDate == null || !this.terminationDate.equals(terminationDate)) {
			markModified();
		}
		this.terminationDate = terminationDate;
	}

	/**
	 * @param acknowledgeDate the acknowledgeDate to set
	 */
	public void setAcknowledgeDate(Date acknowledgeDate) {
		if (this.acknowledgeDate == null || !this.acknowledgeDate.equals(acknowledgeDate)) {
			markModified();
		}
		this.acknowledgeDate = acknowledgeDate;
	}

	/**
	 * @param supervisorAllocationDate the supervisorAllocationDate to set
	 */
	public void setSupervisorAllocationDate(Date supervisorAllocationDate) {
		if (this.supervisorAllocationDate == null || !this.supervisorAllocationDate.equals(supervisorAllocationDate)) {
			markModified();
		}
		this.supervisorAllocationDate = supervisorAllocationDate;
	}
	

	public void update(String caseAssignmentId) {
		CaseAssignment caseAssignment = CaseAssignment.find(caseAssignmentId);
		this.setCaseAssignmentId(caseAssignment.getOID());
		this.setHistoryType(caseAssignment.getCaseState());
		
		this.setTerminationDate(caseAssignment.getTerminationDate());
		this.setProgramUnitId(caseAssignment.getProgramUnitId());
		this.setProgramUnitAssignDate(caseAssignment.getProgramUnitAssignDate());
		this.setAssignedStaffPositionId(caseAssignment.getAssignedStaffPositionId());
		this.setOfficerAssignDate(caseAssignment.getOfficerAssignDate());
		this.setAllocatedStaffPositionId(caseAssignment.getAllocatedStaffPositionId());
		this.setAcknowledgePositionId(caseAssignment.getAcknowledgePositionId());
		this.setAcknowledgeUserId(caseAssignment.getAcknowledgeUserId());
		this.setSupervisionStyleId(caseAssignment.getSupervisionStyleId());
		this.setAcknowledgeStatusId(caseAssignment.getAcknowledgeStatusId());
		this.setAcknowledgeDate(caseAssignment.getAcknowledgeDate());
		this.setSupervisorAllocationDate(caseAssignment.getSupervisorAllocationDate());
	}
	
	public void update(ICaseAssignment caseAssignment) {		
		this.setCaseAssignmentId(caseAssignment.getCaseAssignmentId());
		this.setHistoryType(caseAssignment.getCaseAssignmentState());		
		this.setTerminationDate(caseAssignment.getTerminationDate());
		this.setProgramUnitId(caseAssignment.getProgramUnitId());
		this.setProgramUnitAssignDate(caseAssignment.getProgramUnitAssignDate());
		this.setAssignedStaffPositionId(caseAssignment.getAssignedStaffPositionId());
		this.setOfficerAssignDate(caseAssignment.getOfficerAssignDate());
		this.setAllocatedStaffPositionId(caseAssignment.getAllocatedStaffPositionId());
		this.setAcknowledgePositionId(caseAssignment.getAcknowledgePositionId());
		this.setAcknowledgeUserId(caseAssignment.getAcknowledgeUserId());
		this.setSupervisionStyleId(caseAssignment.getSupervisionStyleCode());
		this.setAcknowledgeStatusId(caseAssignment.getAcknowledgeStatusCode());
		this.setAcknowledgeDate(caseAssignment.getAcknowledgeDate());
		this.setSupervisorAllocationDate(caseAssignment.getSupervisorAllocationDate());
	}

	public void update(CaseAssignment ca) {
	   this.setCaseAssignmentId(ca.getOID());
	   this.setHistoryType(ca.getCaseState());		
	   this.setTerminationDate(ca.getTerminationDate());
	   this.setProgramUnitId(ca.getProgramUnitId());
	   this.setProgramUnitAssignDate(ca.getProgramUnitAssignDate());
	   this.setAssignedStaffPositionId(ca.getAssignedStaffPositionId());
	   this.setOfficerAssignDate(ca.getOfficerAssignDate());
	   this.setAllocatedStaffPositionId(ca.getAllocatedStaffPositionId());
	   this.setAcknowledgePositionId(ca.getAcknowledgePositionId());
	   this.setAcknowledgeUserId(ca.getAcknowledgeUserId());
	   this.setSupervisionStyleId(ca.getSupervisionStyleId());
	   this.setAcknowledgeStatusId(ca.getAcknowledgeStatusId());
	   this.setAcknowledgeDate(ca.getAcknowledgeDate());
	   this.setSupervisorAllocationDate(ca.getSupervisorAllocationDate());
	}
}
