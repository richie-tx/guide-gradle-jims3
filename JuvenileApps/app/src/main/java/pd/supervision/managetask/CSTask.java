package pd.supervision.managetask;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import naming.PDCodeTableConstants;

import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.SimpleCodeTableHelper;
import pd.contact.user.UserProfile;

/**
 * @roseuid 463F2FF10146
 */
public class CSTask extends PersistentObject
{
	private static final String NOT_CLOSED = "NOTCLOSEDCSTASKS";

    /**
     * @roseuid 463F17100119
     */
     public CSTask find(String csTaskId)
    {
        IHome home = new Home();
        return (CSTask) home.find(csTaskId, CSTask.class);
    }

    /**
     * @return Iterator Condition
     * @param event
     */
     public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, CSTask.class);
    }

    /**
     * Finds all Conditions by an attribute value
     * 
     * @return
     * @param attributeName
     * @param attributeValue
     */
     public Iterator findAll(String attributeName, String attributeValue)
    {
        IHome home = new Home();
        Iterator csTasks = home.findAll(attributeName, attributeValue, CSTask.class);
        return csTasks;
    }
    
    /**
     * 
     * @param attributeName
     * @param attributeValue
     * @param contextKey
     * @return
     */
	 public List findAllByContext( String attributeName, String attributeValue, String contextKey ) 
	{
		 IHome home = new Home();
	     List csTasks = home.findAllList(attributeName, attributeValue, NOT_CLOSED, CSTask.class);
	     
	     return csTasks;
	}
	
    private String courtId;
    private String courtId2;
    private String defendantId;    
    private Date lastTransferDate;    
    private String lastTransferUser;
    private int staffPositionId;
    private int workGroupId;
    private Date dueDate;
    private String criminalCaseId;
    private String statusId;
    private String scenario;
    private String superviseeName;
    private String taskSubject;
    private String taskText;
    private String topic;
    private int ncResponseId;
    private String caseAssignIds;
    private String supervisionOrderIds;
    private String supervisionPlanId;
    public int getStaffPositionId() {
		
		fetch();
		return staffPositionId;
	}

	public void setStaffPositionId(int staffPositionId) {
		
		this.staffPositionId = staffPositionId;
	}

	public int getWorkGroupId() {
		
		fetch();
		return workGroupId;
	}

	public void setWorkGroupId(int workGroupId) {
		
		this.workGroupId = workGroupId;
	}

	public Date getDueDate() {
		
		 fetch();
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		
		if (this.dueDate == null || !this.dueDate.equals(dueDate))
        {
            markModified();
        }
		this.dueDate = dueDate;
	}

	public String getCriminalCaseId() {
		
		 fetch();
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId))
        {
            markModified();
        }
		this.criminalCaseId = criminalCaseId;
	}

	public String getStatusId() {
		
		 fetch();
		return statusId;
	}

	public void setStatusId(String statusId) {
		
		if (this.statusId == null || !this.statusId.equals(statusId))
        {
            markModified();
        }
		this.statusId = statusId;
	}

	public String getScenario() {
		
		 fetch();
		return scenario;
	}

	public void setScenario(String scenario) {
		
		if (this.scenario == null || !this.scenario.equals(scenario))
        {
            markModified();
        }
		this.scenario = scenario;
	}

	/**
	 * 
	 * @return
	 */
	public String getSuperviseeName() {
		
		fetch();
		return superviseeName;
	}

	/**
	 * 
	 * @param superviseeName
	 */
	public void setSuperviseeName(String superviseeName) {
		
		if (this.superviseeName == null || !this.superviseeName.equals(superviseeName))
        {
            markModified();
        }
		this.superviseeName = superviseeName;
	}

	public String getTaskSubject() {
		
		 fetch();
		return taskSubject;
	}

	public void setTaskSubject(String taskSubject) {
		
		if (this.taskSubject == null || !this.taskSubject.equals(taskSubject))
        {
            markModified();
        }
		this.taskSubject = taskSubject;
	}

	public String getTaskText() {
		
		 fetch();
		return taskText;
	}

	public void setTaskText(String taskText) {
		
		if (this.taskText == null || !this.taskText.equals(taskText))
        {
            markModified();
        }
		
		this.taskText = taskText;
	}
	private String subject2;

    /**
     * @roseuid 463F2FF10146
     */
    public CSTask()
    {
    }

    /**
     * @roseuid 463F17100118
     */
    public void bind()
    {
        markModified();
    }

    /**
     * 
     * @return
     */
    public String getCaseAssignIds() {
		
    	 fetch();
    	return caseAssignIds;
	}
    /**
     * 
     * @param caseAssignIds
     */
	public void setCaseAssignIds(String caseAssignIds) {
		
		if (this.caseAssignIds == null || !this.caseAssignIds.equals(caseAssignIds))
        {
            markModified();
        }
		this.caseAssignIds = caseAssignIds;
	}

	public String getSupervisionOrderIds() {
		
		 fetch();
		return supervisionOrderIds;
	}

	public void setSupervisionOrderIds(String supervisionOrderIds) {
		
		if (this.supervisionOrderIds == null || !this.supervisionOrderIds.equals(supervisionOrderIds))
        {
            markModified();
        }
		this.supervisionOrderIds = supervisionOrderIds;
	}

	private void calculateDueDateStatus(CSTaskResponseEvent resp, Date dueDate)
    {
        final String NEARING_24_HOURS = "24";

        Date taskDate = dueDate;
        if (taskDate != null)
        {

            long adjDate = taskDate.getTime();
            long compDate = new Date().getTime();

            if ((adjDate) < (compDate))
            {
                resp.setTaskPastDueInd(true);
            }
            else
            {
                resp.setTaskNearingDue(NEARING_24_HOURS);
            }

        }
    }

    public String getTopic() {
		
    	fetch();
    	return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getNcResponseId() {
		
		fetch();
		return ncResponseId;
	}

	public void setNcResponseId(int ncResponseId) {
		this.ncResponseId = ncResponseId;
	}

	/**
     * 
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
        fetch();
        return courtId;
    }

    /**
     * 
     * @return Returns the courtId2.
     */
    public String getCourtId2()
    {
        fetch();
        return courtId2;
    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        fetch();
        return defendantId;
    }

    /**
     * 
     * @return Returns the lastTransferDate.
     */
    public Date getLastTransferDate()
    {
        fetch();
        return lastTransferDate;
    }

    public String getSupervisionPlanId() {
		
    	fetch();
    	return supervisionPlanId;
	}

	public void setSupervisionPlanId(String supervisionPlanId) {
		
		if (this.supervisionPlanId == null || !this.supervisionPlanId.equals( supervisionPlanId ))
        {
            markModified();
        }
		
		this.supervisionPlanId = supervisionPlanId;
	}

	/**
     * Creates response event
     * 
     * @return
     */
    public CSTaskResponseEvent getResponseEvent()
    {
        CSTaskResponseEvent csTaskRespEvt = new CSTaskResponseEvent();
      
        csTaskRespEvt.setCreateDate(getCreateTimestamp());
        csTaskRespEvt.setStatusId(getStatusId());
        csTaskRespEvt.setStatus( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASK_STATUS, getStatusId()));
        csTaskRespEvt.setTaskSubject(getTaskSubject());
        csTaskRespEvt.setSubject2( getSubject2() );
        csTaskRespEvt.setTaskText( getTaskText() );
        csTaskRespEvt.setCourtId(getCourtId());
        csTaskRespEvt.setTaskId( getOID() );
        csTaskRespEvt.setCourtId2(getCourtId2());
        csTaskRespEvt.setSuperviseeName( getSuperviseeName());
        csTaskRespEvt.setStaffPositionId( String.valueOf( getStaffPositionId() ));
        csTaskRespEvt.setDefendantId(getDefendantId());
        csTaskRespEvt.setTopic(getTopic());
        csTaskRespEvt.setStatusChangeDate(getUpdateTimestamp());
        csTaskRespEvt.setScenario( getScenario() );
        csTaskRespEvt.setCriminalCaseId( getCriminalCaseId() );
        csTaskRespEvt.setCaseAssignIds( getCaseAssignIds() );
        csTaskRespEvt.setSupervisionOrderIds( getSupervisionOrderIds() );

        String updateUserId = getUpdateUserID();
        csTaskRespEvt.setStatusChangeUserId(updateUserId);
        if(updateUserId != null && "".equals(updateUserId)){
        	
        	 UserProfile user = UserProfile.find(updateUserId);
             if (user != null)
             {
                 csTaskRespEvt.setStatusChangeUser(user.getLastName() + ", " + user.getFirstName());
             }
        }
    
        String transferUserId = getLastTransferUser();
        csTaskRespEvt.setLastTransferUserId(transferUserId);
        if(transferUserId !=null && !transferUserId.equals("")){
        	UserProfile transUser = UserProfile.find(transferUserId);
        	
        	if (transUser != null)
            {
                csTaskRespEvt.setLastTransferUser(transUser.getLastName() + ", " + transUser.getFirstName());
            }
        }
        
        csTaskRespEvt.setLastTransferDate(getLastTransferDate());
        csTaskRespEvt.setDueDate(getDueDate());
        this.calculateDueDateStatus(csTaskRespEvt, getDueDate());

        csTaskRespEvt.setNcResponseId( String.valueOf( getNcResponseId() ));
        csTaskRespEvt.setSprvisionPlanId( getSupervisionPlanId() );

        return csTaskRespEvt;
    }
    
    /**
     * 
     * @return Returns the subject2.
     */
    public String getSubject2()
    {
        fetch();
        return subject2;
    }

  
    /**
     * 
     * @param courtId
     *            The courtId to set.
     */
    public void setCourtId(String courtId)
    {
        if (this.courtId == null || !this.courtId.equals(courtId))
        {
            markModified();
        }
        this.courtId = courtId;
    }

    /**
     * 
     * @param courtId2
     *            The courtId2 to set.
     */
    public void setCourtId2(String courtId2)
    {
        if (this.courtId2 == null || !this.courtId2.equals(courtId2))
        {
            markModified();
        }
        this.courtId2 = courtId2;
    }

    /**
     * @param defendantId
     *            The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        if (this.defendantId == null || !this.defendantId.equals(defendantId))
        {
            markModified();
        }
        this.defendantId = defendantId;
    }

    /**
     * 
     * @param lastTransferDate
     *            The lastTransferDate to set.
     */
    public void setLastTransferDate(Date lastTransferDate)
    {
        if (this.lastTransferDate == null || !this.lastTransferDate.equals(lastTransferDate))
        {
            markModified();
        }
        this.lastTransferDate = lastTransferDate;
    }

    /**
     * 
     * @param subject2
     *            The subject2 to set.
     */
    public void setSubject2(String subject2)
    {
        if (this.subject2 == null || !this.subject2.equals(subject2))
        {
            markModified();
        }
        this.subject2 = subject2;
    }

    public String getLastTransferUser() {
		return lastTransferUser;
	}

	public void setLastTransferUser(String lastTransferUser) {
		this.lastTransferUser = lastTransferUser;
	}

	public CSTaskResponseEvent getResponseEvent(Map partyMap) {
        CSTaskResponseEvent csTaskRespEvt = new CSTaskResponseEvent();
        
        csTaskRespEvt.setCreateDate(getCreateTimestamp());
        csTaskRespEvt.setStatusId(getStatusId());
        csTaskRespEvt.setTaskSubject(getTaskSubject());
        csTaskRespEvt.setSubject2( getSubject2() );
        StringBuffer padCrt = null;
        if ( StringUtils.isNotEmpty(getCourtId()) ){
        	padCrt = new StringBuffer(getCourtId());
	    	while (padCrt.length() < 3){
	    		padCrt.insert(0, "0");
	    	}
	    	csTaskRespEvt.setCourtId(padCrt.toString());
        } else {
        	csTaskRespEvt.setCourtId("");
        }
        csTaskRespEvt.setTaskId( getOID() );
        csTaskRespEvt.setCourtId2(getCourtId2());
        String defendantId = getDefendantId();
        if (defendantId != null && defendantId.length() < 8){
        	StringBuffer sb = new StringBuffer(defendantId);
        	while (sb.length() < 8){
        		sb.insert(0, 0);
        	}
        	defendantId = sb.toString();
        }

        csTaskRespEvt.setSuperviseeName( getSuperviseeName() );
        csTaskRespEvt.setDefendantId(defendantId);
        csTaskRespEvt.setCriminalCaseId( getCriminalCaseId() );
        csTaskRespEvt.setSupervisionOrderIds( getSupervisionOrderIds() );
        csTaskRespEvt.setNcResponseId( String.valueOf( getNcResponseId() ));
        csTaskRespEvt.setStaffPositionId( String.valueOf( getStaffPositionId() ));
        csTaskRespEvt.setCaseAssignIds( getCaseAssignIds() );
        csTaskRespEvt.setWorkGroupId( String.valueOf( getWorkGroupId() ));
        csTaskRespEvt.setScenario( getScenario() );
        csTaskRespEvt.setTopic(getTopic());
        csTaskRespEvt.setTaskText( getTaskText());
        csTaskRespEvt.setStatusChangeDate(getUpdateTimestamp());
        csTaskRespEvt.setStatus( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASK_STATUS, getStatusId()));
        String updateUserId = getUpdateUserID();
        csTaskRespEvt.setStatusChangeUserId(updateUserId);
   
        String transferUserId = getLastTransferUser();
        csTaskRespEvt.setLastTransferUserId(transferUserId);
        csTaskRespEvt.setLastTransferDate(getLastTransferDate());
        csTaskRespEvt.setDueDate(getDueDate());
        this.calculateDueDateStatus(csTaskRespEvt, getDueDate());

        csTaskRespEvt.setSprvisionPlanId( getSupervisionPlanId());

        return csTaskRespEvt;
	}
}
