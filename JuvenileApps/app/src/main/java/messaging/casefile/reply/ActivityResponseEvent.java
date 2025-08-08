package messaging.casefile.reply;

import java.util.Comparator;
import java.util.Date;

import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author C_NAggarwal
 *  
 */
public class ActivityResponseEvent extends ResponseEvent implements Comparable
{

    private Date activityDate;
    
    private Date inactiveDate;

    private String activityDesc;

    private String activityId;

    private String casefileId;

    private String categoryDesc;

    private String categoryId;
    
    private String permissionId;
    
    private String permissionDesc;

	private String codeId;

    private String comments;

    private String title;

    private String typeDesc;

    private String typeId;
    
    private String createdBy;
    // US 105411
    private String time;   
    private String activityTime;
    
    
    private String latitude;
    private String longitude;
    

	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	private String	detentionId;
	private String updateComments;	
	private String detentionTime;	
	private String activityendTime;

	
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o)
    {
        if (o == null)
            return 1; // this makes any null objects go to the bottom change this to -1 if you want
        // the top of the list
        if (this.activityDate == null)
            return -1; // this makes any null objects go to the bottom change this to 1 if you want
        // the top of the list
        ActivityResponseEvent evt = (ActivityResponseEvent) o;
        return evt.getActivityDate().compareTo(activityDate);
        //return this.activityDate.compareTo(evt.getActivityDate());

    }

    /**
     * @return Returns the entryDate.
     */
    public Date getActivityDate()
    {
        return activityDate;
    }

    public String getActivityDateAsStr()
    {
        if (activityDate == null)
            return "";
        else
        {
            try
            {
                return DateUtil.dateToString(activityDate, "MM/dd/yyyy");
            }
            catch (Exception e)
            {
                return "";
            }
        }
    }
    
    /**
  	 * @return the permissionDesc
  	 */
  	public String getPermissionDesc() {
  		return permissionDesc;
  	}

  	/**
  	 * @param permissionDesc the permissionDesc to set
  	 */
  	public void setPermissionDesc(String permissionDesc) {
  		this.permissionDesc = permissionDesc;
  	}

    /**
	 * @return the inactiveDate
	 */
	public Date getInactiveDate() {
		return inactiveDate;
	}

	/**
	 * @param inactiveDate the inactiveDate to set
	 */
	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	/**
     * @return Returns the activityDesc.
     */
    public String getActivityDesc()
    {
        return activityDesc;
    }

    /**
     * @return Returns the activityId.
     */
    public String getActivityId()
    {
        return activityId;
    }

    /**
     * @return Returns the casefileId.
     */
    public String getCasefileId()
    {
        return casefileId;
    }

    /**
     * @return Returns the categoryDesc.
     */
    public String getCategoryDesc()
    {
        return categoryDesc;
    }

    /**
     * @return Returns the categoryId.
     */
    public String getCategoryId()
    {
        return categoryId;
    }

    /**
     * @return Returns the codeId.
     */
    public String getCodeId()
    {
        return codeId;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments()
    {
        return comments;
    }

    public String getTitle()
    {
        return this.title;
    }

    /**
     * @return Returns the typeDesc.
     */
    public String getTypeDesc()
    {
        return typeDesc;
    }

    /**
     * @return Returns the typeId.
     */
    public String getTypeId()
    {
        return typeId;
    }

    /**
     * @param entryDate
     *            The entryDate to set.
     */
    public void setActivityDate(Date activityDate)
    {
        this.activityDate = activityDate;
    }

    public void setActivityDateAsStr(String aStringDate)
    {
        if (aStringDate == null || aStringDate.equals(""))
            activityDate = null;
        try
        {
            activityDate = DateUtil.stringToDate(aStringDate, "MM/dd/yyyy");
        }
        catch (Exception e)
        {
            activityDate = null;
        }
    }

    /**
     * @param activityDesc
     *            The activityDesc to set.
     */
    public void setActivityDesc(String activityDesc)
    {
        this.activityDesc = activityDesc;
    }

    /**
     * @param activityId
     *            The activityId to set.
     */
    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    /**
     * @param casefileId
     *            The casefileId to set.
     */
    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }

    /**
     * @param categoryDesc
     *            The categoryDesc to set.
     */
    public void setCategoryDesc(String categoryDesc)
    {
        this.categoryDesc = categoryDesc;
    }

    /**
     * @param categoryId
     *            The categoryId to set.
     */
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    /**
     * @param codeId
     *            The codeId to set.
     */
    public void setCodeId(String codeId)
    {
        this.codeId = codeId;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * @param string
     */
    public void setTitle(String aTitle)
    {
        this.title = aTitle;
    }

    /**
     * @param typeDesc
     *            The typeDesc to set.
     */
    public void setTypeDesc(String typeDesc)
    {
        this.typeDesc = typeDesc;
    }

    /**
     * @param typeId
     *            The typeId to set.
     */
    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}

	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}

	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}

	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
	/**
	 * @return the permissionId
	 */
	public String getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getTime()
	{
	   return time;
	}

	public void setTime(String time)
	{
	  this.time = time;
	}
	public String getActivityTime()
	{
	    return activityTime;
	}

	public void setActivityTime(String activityTime)
	{
	    this.activityTime = activityTime;
	}
	public String getDetentionId()
	{
	    return detentionId;
	}

	public void setDetentionId(String detentionId)
	{
	    this.detentionId = detentionId;
	}
	public String getUpdateComments()
	{
	    return updateComments;
	}

	public void setUpdateComments(String updateComments)
	{
	    this.updateComments = updateComments;
	}
	public String getDetentionTime()
	{
	    return detentionTime;
	}

	public void setDetentionTime(String detentionTime)
	{
	    this.detentionTime = detentionTime;
	}
	public String getActivityendTime()
	{
	    return activityendTime;
	}

	public void setActivityendTime(String activityendTime)
	{
	    this.activityendTime = activityendTime;
	}
	
	
	
	public String getLatitude()
	{
	    return latitude;
	}

	public void setLatitude(String latitude)
	{
	    this.latitude = latitude;
	}

	public String getLongitude()
	{
	    return longitude;
	}

	public void setLongitude(String longitude)
	{
	    this.longitude = longitude;
	}



	public static Comparator CaseReviewJournalSummaryActivityComparator = new Comparator() {
		public int compare(Object activity, Object otherActivity) {
			
		  int result = 0;
		  String activityDescription = ((ActivityResponseEvent)activity).getActivityDesc();
		  String otherActivityDescription = ((ActivityResponseEvent)otherActivity).getActivityDesc();
		  Date activityDate = ((ActivityResponseEvent)activity).getActivityDate();
		  Date otherActivityDate = ((ActivityResponseEvent)otherActivity).getActivityDate();
		  
		  if(activityDescription == null || activityDescription.equals("")){
			  result = -1;
		  } else if(otherActivityDescription == null || otherActivityDescription.equals("")){
			  result = 1;
		  }else{
			  result = activityDescription.compareTo(otherActivityDescription);
		  }
		  
		  if(result == 0){
			  if(activityDate == null)
			  {
				  result = -1;
			  }else if(otherActivityDate == null)
			  {
				  result = 1;
			  }
			  else 
			  {
				  result = otherActivityDate.compareTo(activityDate);
			  }
		  }
		  return result;
		}	
	};
	
}
