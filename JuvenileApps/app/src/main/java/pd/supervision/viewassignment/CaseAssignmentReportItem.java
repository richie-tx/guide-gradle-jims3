/*
 * Created on Jan 17, 2008
 *
 */
package pd.supervision.viewassignment;

import java.util.Date;
import java.util.Iterator;

import naming.CaseloadConstants;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_rbhat
 *  
 */
public class CaseAssignmentReportItem extends PersistentObject {
	private String defendantId;

	private String criminalCaseId;

	private String courtId;

	private String programUnitId;

	private String programUnitName;

	private Date programUnitAssignDate;

	private String assignedStaffPositionId;
	
	private String assignedStaffPositionName;

	private Date officerAssignDate;

	private String acknowledgeUserPositionId;

	private String acknowledgeUserId;
	
	private String caseAssignmentHistoryType;
	
	private String caseStatus;
	
	private Date historyTransactionDate;
	
	private String caseAssignmentId;
	
	private String caseAssignmentHistId;
	
	private Date acknowledgeUserDate;

	private String poi;
	
	private String officerUserId;

	private String supervisorUserId;
	
	private Date supervisorAllocationDate;
	
	public Date getAcknowledgeUserDate() {
		fetch();
		return acknowledgeUserDate;
	}

	public String getPoi() {
		fetch();
		return poi;
	}


	public String getOfficerUserId() {
		fetch();
		return officerUserId;
	}
	
	public Date getSupervisorAllocationDate() {
		fetch();
		return supervisorAllocationDate;
	}

	public String getSupervisorUserId() {
		fetch();
		return supervisorUserId;
	}
	
	public String getCaseAssignmentId() {
		fetch();
		return caseAssignmentId;
	}

	public String getCaseAssignmentHistId() {
		fetch();
		return caseAssignmentHistId;
	}

	/**
	 * @return Returns the acknowledgeUserId.
	 */
	public String getAcknowledgeUserId() {
        fetch();
		return acknowledgeUserId;
	}

	/**
	 * @return Returns the acknowledgeUserPositionId.
	 */
	public String getAcknowledgeUserPositionId() {
        fetch();
		return acknowledgeUserPositionId;
	}

	public String getAssignedStaffPositionName() {
        fetch();
		return assignedStaffPositionName;
	}
	
	/**
	 * @return Returns the assignedStaffPositionId.
	 */
	public String getAssignedStaffPositionId() {
        fetch();
		return assignedStaffPositionId;
	}

	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
        fetch();
		return courtId;
	}

	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
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
	 * @return Returns the officerAssignDate.
	 */
	public Date getOfficerAssignDate() {
        fetch();
		return officerAssignDate;
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
	
	public String getCaseStatus() {
		fetch();
		return caseStatus;
	}
	
	/**
	 * @return Returns the programUnitName.
	 */
	public String getProgramUnitName() {
        fetch();
		return programUnitName;
	}

	public String getCaseAssignmentHistoryType() {
        fetch();
		return caseAssignmentHistoryType;
	}

	public Date getHistoryTransactionDate() {
        fetch();
		return historyTransactionDate;
	}

	/**
	 * @param acknowledgeUserId
	 *            The acknowledgeUserId to set.
	 */
	public void setAcknowledgeUserId(String acknowledgeUserId) {
        if (this.acknowledgeUserId == null || !this.acknowledgeUserId.equals(acknowledgeUserId)) {
            markModified();
        }
		this.acknowledgeUserId = acknowledgeUserId;
	}

	/**
	 * @param acknowledgeUserPositionId
	 *            The acknowledgeUserPositionId to set.
	 */
	public void setAcknowledgeUserPositionId(String acknowledgeUserPositionId) {
        if (this.acknowledgeUserPositionId == null || !this.acknowledgeUserPositionId.equals(acknowledgeUserPositionId)) {
            markModified();
        }
		this.acknowledgeUserPositionId = acknowledgeUserPositionId;
	}

	/**
	 * @param assignedStaffPositionId
	 *            The assignedStaffPositionId to set.
	 */
	public void setAssignedStaffPositionId(String assignedStaffPositionId) {
        if (this.assignedStaffPositionId == null || !this.assignedStaffPositionId.equals(assignedStaffPositionId)) {
            markModified();
        }
		this.assignedStaffPositionId = assignedStaffPositionId;
	}

	/**
	 * @param courtId
	 *            The courtId to set.
	 */
	public void setCourtId(String courtId) {
        if (this.courtId == null || !this.courtId.equals(courtId)) {
            markModified();
        }
		this.courtId = courtId;
	}

	/**
	 * @param criminalCaseId
	 *            The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
        if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId)) {
            markModified();
        }
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * @param defendantId
	 *            The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
        if (this.defendantId == null || !this.defendantId.equals(defendantId)) {
            markModified();
        }
		this.defendantId = defendantId;
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
		this.programUnitId = programUnitId;
	}

	/**
	 * @param programUnitName
	 *            The programUnitName to set.
	 */
	public void setProgramUnitName(String programUnitName) {
        if (this.programUnitName == null || !this.programUnitName.equals(programUnitName)) {
            markModified();
        }
		this.programUnitName = programUnitName;
	}
	
	public void setAssignedStaffPositionName(String assignedStaffPositionName) {
        if (this.assignedStaffPositionName == null || !this.assignedStaffPositionName.equals(assignedStaffPositionName)) {
            markModified();
        }
		this.assignedStaffPositionName = assignedStaffPositionName; 
	}

	public void setCaseAssignmentHistoryType(String caseAssignmentHistoryType) {
        if (this.caseAssignmentHistoryType == null || !this.caseAssignmentHistoryType.equals(caseAssignmentHistoryType)) {
            markModified();
        }
		this.caseAssignmentHistoryType = caseAssignmentHistoryType;
	}

	public void setHistoryTransactionDate(Date historyTransactionDate) {
        if (this.historyTransactionDate == null || !this.historyTransactionDate.equals(historyTransactionDate)) {
            markModified();
        }
		this.historyTransactionDate = historyTransactionDate;
	}
	
	public void setCaseStatus(String caseStatus) {
       if (this.caseStatus == null || !this.caseStatus.equals(caseStatus)) {
            markModified();
        }
		this.caseStatus = caseStatus;
	}
	
	public void setCaseAssignmentId(String caseAssignmentId) {
	   if (this.caseAssignmentId == null || !this.caseAssignmentId.equals(caseAssignmentId)) {
	       markModified();
	   }
	   this.caseAssignmentId = caseAssignmentId;
	}

	public void setCaseAssignmentHistId(String caseAssignmentHistId) {
	    if (this.caseAssignmentHistId == null || !this.caseAssignmentHistId.equals(caseAssignmentHistId)) {
	        markModified();
	    }
		this.caseAssignmentHistId = caseAssignmentHistId;
	}
	
	public void setSupervisorUserId(String supervisorUserId) {
	    if (this.supervisorUserId == null || !this.supervisorUserId.equals(supervisorUserId)) {
	        markModified();
	    }
		this.supervisorUserId = supervisorUserId;
	}
	
	public void setAcknowledgeUserDate(Date acknowledgeUserDate) {
	    if (this.acknowledgeUserDate == null || !this.acknowledgeUserDate.equals(acknowledgeUserDate)) {
	        markModified();
	    }
		this.acknowledgeUserDate = acknowledgeUserDate;
	}

	public void setOfficerUserId(String officerUserId) {
	    if (this.officerUserId == null || !this.officerUserId.equals(officerUserId)) {
	        markModified();
	    }
		this.officerUserId = officerUserId;
	}
	
	public void setPoi(String poi) {
	    if (this.poi == null || !this.poi.equals(poi)) {
	        markModified();
	    }
		this.poi = poi;
	}

	public void setSupervisorAllocationDate(Date supervisorAllocationDate) {
	    if (this.supervisorAllocationDate == null || !this.supervisorAllocationDate.equals(supervisorAllocationDate)) {
	        markModified();
	    }
		this.supervisorAllocationDate = supervisorAllocationDate;
	}


	public static CaseAssignmentReportItem find(String anOid) {
		IHome home = new Home();
		return (CaseAssignmentReportItem) home.find(anOid, CaseAssignmentReportItem.class);
	}

	public static Iterator findAll(IEvent anEvent) {
		IHome home = new Home();
		return home.findAll(anEvent, CaseAssignmentReportItem.class);
	}

	public static Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, attrValue, CaseAssignmentReportItem.class);
	}

    public ICaseAssignment valueObject() {
        ICaseAssignment caseAssignment = new CaseAssignmentTO();
        
        caseAssignment.setDefendantId(this.getDefendantId());
        caseAssignment.setCriminalCaseId(this.getCriminalCaseId());
        caseAssignment.setCourtId(this.getCourtId());
        caseAssignment.setProgramUnitId(this.getProgramUnitId());
        caseAssignment.setProgramUnitName(this.getProgramUnitName());
        caseAssignment.setProgramUnitAssignDate(this.getProgramUnitAssignDate());
        caseAssignment.setAssignedStaffPositionId(this.getAssignedStaffPositionId());
        caseAssignment.setAssignedStaffPositionName(this.getAssignedStaffPositionName());
        caseAssignment.setOfficerAssignDate(this.getOfficerAssignDate());
        caseAssignment.setAcknowledgePositionId(this.getAcknowledgeUserPositionId());
        caseAssignment.setAcknowledgeUserId(this.getAcknowledgeUserId());
        caseAssignment.setAcknowledgeDate(this.getAcknowledgeUserDate());
        caseAssignment.setProbationOfficerInd(this.getPoi());
        caseAssignment.setSupervisorAllocationDate(this.getSupervisorAllocationDate());

        if (CaseloadConstants.PROGRAM_UNIT_ASSIGNED.equals(this.getCaseAssignmentHistoryType())) {
        	caseAssignment.setProgramUnitAssignmentTranactionDate(this.getHistoryTransactionDate());
        } else {
        	caseAssignment.setOfficerAssignmentTransactionDate(this.getHistoryTransactionDate());
        }
        
        return caseAssignment;
    }

}
