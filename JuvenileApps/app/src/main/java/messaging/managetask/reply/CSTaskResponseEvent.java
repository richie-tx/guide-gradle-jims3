/*
 * Created on May 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.managetask.reply;

import java.util.Date;
import messaging.task.reply.TaskResponseEvent;

/**
 * @author ryoung
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences -
 * Java - Code Style - Code Templates
 */
public class CSTaskResponseEvent extends TaskResponseEvent
{

    public static final String NEARING_24_HOURS = "24";

    public static final String OVER_7_DAYS = "over7days";

    private Date acceptedDate;
    private String caseAssignIds;
    private Date closedDate;
    private String courtId;
    private String courtId2;
    private Date createDate;
    private String createUserId;
    private String criminalCaseId;
    private String defendantId;
    private Date dueDate;
    private Date lastTransferDate;
    private String lastTransferUser;
    private String lastTransferUserId;
    private String ownerId;
    private String scenario;
    private String staffPositionId;
    private String status;
    private String statusId;
    private String subject2;
    private Date submittedDate;
    private String superviseeName;
    private String taskNearingDue;
    private boolean taskPastDueInd;
    private String taskSubject;
    private String taskText;
    private Date statusChangeDate;
	private String statusChangeUser;
	private String statusChangeUserId;
	private String workGroupId;
	private String ncResponseId;
	private String sprvisionPlanId;
	private String supervisionOrderIds;
	
	/**
     * @return Returns the getAcceptedDate.
     */
    public Date getAcceptedDate()
    {
        return acceptedDate;
    }

    /**
     * @return Returns the getClosedDate.
     */
    public Date getClosedDate()
    {
        return closedDate;
    }

    /**
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
        return courtId;
    }

    /**
     * @return Returns the courtId2.
     */
    public String getCourtId2()
    {
        return courtId2;
    }

    /**
     * @return Returns the getCreateDate.
     */
    public Date getCreateDate()
    {
        return createDate;
    }

    public String getCaseAssignIds() {
		return caseAssignIds;
	}

	public void setCaseAssignIds(String caseAssignId) {
		this.caseAssignIds = caseAssignId;
	}

	/**
     * @return Returns the getCreateUserId.
     */
    public String getCreateUserId()
    {
        return createUserId;
    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @return Returns the getDueDate.
     */
    public Date getDueDate()
    {
        return dueDate;
    }

    /**
     * @return Returns the getOwnerId.
     */
    public String getOwnerId()
    {
        return ownerId;
    }
    
    /**
     * @return Returns the scenario.
     */
    public String getScenario()
    {
        return scenario;
    }

    /**
     * @return Returns the getStatus.
     */
    public String getStatusId()
    {
        return statusId;
    }

    /**
     * @return Returns the getSubmittedDate.
     */
    public Date getSubmittedDate()
    {
        return submittedDate;
    }

    /**
     * @return Returns the superviseeName.
     */
    public String getSuperviseeName()
    {
        return superviseeName;
    }

    /**
     * @return Returns the taskNearingDue.
     */
    public String getTaskNearingDue()
    {
        return taskNearingDue;
    }

    /**
     * @return Returns the getTaskSubject.
     */
    public String getTaskSubject()
    {
        return taskSubject;
    }

    /**
     * @return Returns the taskPastDueInd.
     */
    public boolean isTaskPastDueInd()
    {
        return taskPastDueInd;
    }

    /**
     * @param getAcceptedDate
     *            The getAcceptedDate to set.
     */
    public void setAcceptedDate(Date getAcceptedDate)
    {
        this.acceptedDate = getAcceptedDate;
    }

    /**
     * @param getClosedDate
     *            The getClosedDate to set.
     */
    public void setClosedDate(Date getClosedDate)
    {
        this.closedDate = getClosedDate;
    }

    /**
     * @param courtId
     *            The courtId to set.
     */
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }

    /**
     * @param courtId2
     *            The courtId2 to set.
     */
    public void setCourtId2(String courtId2)
    {
        this.courtId2 = courtId2;
    }

    /**
     * @param getCreateDate
     *            The getCreateDate to set.
     */
    public void setCreateDate(Date getCreateDate)
    {
        this.createDate = getCreateDate;
    }

    /**
     * @param getCreateUserId
     *            The getCreateUserId to set.
     */
    public void setCreateUserId(String getCreateUserId)
    {
        this.createUserId = getCreateUserId;
    }

    /**
     * @param defendantId
     *            The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

    /**
     * @param getDueDate
     *            The getDueDate to set.
     */
    public void setDueDate(Date getDueDate)
    {
        this.dueDate = getDueDate;
    }

    /**
     * @param getOwnerId
     *            The getOwnerId to set.
     */
    public void setOwnerId(String getOwnerId)
    {
        this.ownerId = getOwnerId;
    }
    
    /**
     * @param scenario The scenario to set.
     */
    public void setScenario(String scenario)
    {
        this.scenario = scenario;
    }

    /**
     * @param getStatus
     *            The getStatus to set.
     */
    public void setStatusId(String getStatus)
    {
        this.statusId = getStatus;
    }

    /**
     * @param getSubmittedDate
     *            The getSubmittedDate to set.
     */
    public void setSubmittedDate(Date getSubmittedDate)
    {
        this.submittedDate = getSubmittedDate;
    }

    /**
     * @param superviseeName
     *            The superviseeName to set.
     */
    public void setSuperviseeName(String superviseeName)
    {
        this.superviseeName = superviseeName;
    }

    /**
     * @param taskNearingDue
     *            The taskNearingDue to set.
     */
    public void setTaskNearingDue(String taskNearingDue)
    {
        this.taskNearingDue = taskNearingDue;
    }

    /**
     * @param taskPastDueInd
     *            The taskPastDueInd to set.
     */
    public void setTaskPastDueInd(boolean taskPastDueInd)
    {
        this.taskPastDueInd = taskPastDueInd;
    }

    /**
     * @param getTaskSubject
     *            The getTaskSubject to set.
     */
    public void setTaskSubject(String getTaskSubject)
    {
        this.taskSubject = getTaskSubject;
    }

    public String getStatus() {
		return status;

	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastTransferDate() {
		return lastTransferDate;
	}

	public void setLastTransferDate(Date lastTransferDate) {
		this.lastTransferDate = lastTransferDate;
	}

	public String getLastTransferUser() {
		return lastTransferUser;
	}

	public void setLastTransferUser(String lastTransferUser) {
		this.lastTransferUser = lastTransferUser;
	}

	public Date getStatusChangeDate() {
		return statusChangeDate;
	}

	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	public String getStatusChangeUser() {
		return statusChangeUser;
	}

	public void setStatusChangeUser(String statusChangeUser) {
		this.statusChangeUser = statusChangeUser;
	}
	public String getLastTransferUserId() {
		return lastTransferUserId;
	}

	public void setLastTransferUserId(String lastTransferUserId) {
		this.lastTransferUserId = lastTransferUserId;
	}

	public String getStatusChangeUserId() {
		return statusChangeUserId;
	}

	public void setStatusChangeUserId(String statusChangeUserId) {
		this.statusChangeUserId = statusChangeUserId;
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public String getStaffPositionId() {
		
		return staffPositionId;
	}

	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}

	public String getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}

	public String getNcResponseId() {
		return ncResponseId;
	}

	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}

	public String getSprvisionPlanId() {
		return sprvisionPlanId;
	}

	public void setSprvisionPlanId(String sprvisionPlanId) {
		this.sprvisionPlanId = sprvisionPlanId;
	}

	public String getSupervisionOrderIds() {
		return supervisionOrderIds;
	}

	public void setSupervisionOrderIds(String supervisionOrderId) {
		this.supervisionOrderIds = supervisionOrderId;
	}

}

