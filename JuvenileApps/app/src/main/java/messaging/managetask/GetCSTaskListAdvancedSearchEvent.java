//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managetask\\DisplayTasksSearchEvent.java

package messaging.managetask;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetCSTaskListAdvancedSearchEvent extends RequestEvent 
{
    private Date beginCreateDate;
    private Date beginDueDate;
    private String courtId;
    private String defendantId;
    private Date endCreateDate;
    private Date endDueDate;
    private String ownerId;
    private String severityLevelId;
    private String staffPositionId;
    private String statusId;
    
    private String taskListTypeId;
    private String userDepartmentDesc;
    private String workgroupId;
    
    private List workgroupIds;
    private List taskStatusIds;
    private List severityLevelIds;
    
    private boolean hasMultiples;
    
    
   /**
    * @roseuid 463F301402AE
    */
   public GetCSTaskListAdvancedSearchEvent() 
   {
       
    
   }
    /**
     * @return Returns the beginCreateDate.
     */
    public Date getBeginCreateDate()
    {
        return beginCreateDate;
    }
    /**
     * @return Returns the beginDueDate.
     */
    public Date getBeginDueDate()
    {
        return beginDueDate;
    }
    /**
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
        return courtId;
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
     * @return Returns the endCreateDate.
     */
    public Date getEndCreateDate()
    {
        return endCreateDate;
    }
    /**
     * @return Returns the endDueDate.
     */
    public Date getEndDueDate()
    {
        return endDueDate;
    }
    /**
     * @return Returns the ownerId.
     */
    public String getOwnerId()
    {
        return ownerId;
    }
    /**
     * @return Returns the severityLevelId.
     */
    public String getSeverityLevelId()
    {
        return severityLevelId;
    }
    /**
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId()
    {
        return staffPositionId;
    }
    /**
     * @return Returns the statusId.
     */
    public String getStatusId()
    {
        return statusId;
    }
    /**
     * @return Returns the taskListTypeId.
     */
    public String getTaskListTypeId()
    {
        return taskListTypeId;
    }
    
    /**
     * @return Returns the userDepartmentDesc.
     */
    public String getUserDepartmentDesc()
    {
        return userDepartmentDesc;
    }
    /**
     * @return Returns the workgroupId.
     */
    public String getWorkgroupId()
    {
        return workgroupId;
    }
    /**
     * @param beginCreateDate The beginCreateDate to set.
     */
    public void setBeginCreateDate(Date beginCreateDate)
    {
        this.beginCreateDate = beginCreateDate;
    }
    /**
     * @param beginDueDate The beginDueDate to set.
     */
    public void setBeginDueDate(Date beginDueDate)
    {
        this.beginDueDate = beginDueDate;
    }
    /**
     * @param courtId The courtId to set.
     */
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }
    /**
     * @param endCreateDate The endCreateDate to set.
     */
    public void setEndCreateDate(Date endCreateDate)
    {
        this.endCreateDate = endCreateDate;
    }
    /**
     * @param endDueDate The endDueDate to set.
     */
    public void setEndDueDate(Date endDueDate)
    {
        this.endDueDate = endDueDate;
    }
    /**
     * @param ownerId The ownerId to set.
     */
    public void setOwnerId(String ownerId)
    {
        this.ownerId = ownerId;
    }
    /**
     * @param severityLevelId The severityLevelId to set.
     */
    public void setSeverityLevelId(String severityLevelId)
    {
        this.severityLevelId = severityLevelId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId)
    {
        this.staffPositionId = staffPositionId;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId)
    {
        this.statusId = statusId;
    }
    /**
     * @param taskListTypeId The taskListTypeId to set.
     */
    public void setTaskListTypeId(String taskListTypeId)
    {
        this.taskListTypeId = taskListTypeId;
    }
    /**
     * @param userDepartmentDesc The userDepartmentDesc to set.
     */
    public void setUserDepartmentDesc(String userDepartmentDesc)
    {
        this.userDepartmentDesc = userDepartmentDesc;
    }
    /**
     * @param workgroupId The workgroupId to set.
     */
    public void setWorkgroupId(String workgroupId)
    {
        this.workgroupId = workgroupId;
    }
	/**
	 * @return the workgroupIds
	 */
	public List getWorkgroupIds() {
		return workgroupIds;
	}
	/**
	 * @param workgroupIds the workgroupIds to set
	 */
	public void setWorkgroupIds(List workgroupIds) {
		this.workgroupIds = workgroupIds;
	}
	/**
	 * @return the taskStatusIds
	 */
	public List getTaskStatusIds() {
		return taskStatusIds;
	}
	/**
	 * @param taskStatusIds the taskStatusIds to set
	 */
	public void setTaskStatusIds(List taskStatusIds) {
		this.taskStatusIds = taskStatusIds;
	}
	/**
	 * @return the severityLevelIds
	 */
	public List getSeverityLevelIds() {
		return severityLevelIds;
	}
	/**
	 * @param severityLevelIds the severityLevelIds to set
	 */
	public void setSeverityLevelIds(List severityLevelIds) {
		this.severityLevelIds = severityLevelIds;
	}
	/**
	 * @return the hasMultiples
	 */
	public boolean isHasMultiples() {
		return hasMultiples;
	}
	/**
	 * @param hasMultiples the hasMultiples to set
	 */
	public void setHasMultiples(boolean hasMultiples) {
		this.hasMultiples = hasMultiples;
	}
    
}
