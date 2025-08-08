package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportActivityRecordEvent extends RequestEvent 
{
   
    	private String activityId;
	private String casefileId;
	private String activityCd;
	private String comments;	
	private String updateComments;
	private Date activityDate;
	private String detentionId;
	private String detentionTime;
	private String activityTime;
	private String activityendTime;
/**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportActivityRecordEvent() 
   {
    
   }

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public String getActivityId()
	{
	    return activityId;
	}

	public void setActivityId(String activityId)
	{
	    this.activityId = activityId;
	}
	public String getActivityCd()
	{
	    return activityCd;
	}

	public void setActivityCd(String activityCd)
	{
	    this.activityCd = activityCd;
	}
	public String getComments()
	{
	    return comments;
	}

	public void setComments(String comments)
	{
	    this.comments = comments;
	}
	public String getUpdateComments()
	{
	    return updateComments;
	}

	public void setUpdateComments(String updateComments)
	{
	    this.updateComments = updateComments;
	}
	
	public String getDetentionId()
	{
	    return detentionId;
	}

	public void setDetentionId(String detentionId)
	{
	    this.detentionId = detentionId;
	}
	public String getDetentionTime()
	{
	    return detentionTime;
	}

	public void setDetentionTime(String detentionTime)
	{
	    this.detentionTime = detentionTime;
	}
	public String getActivityTime()
	{
	    return activityTime;
	}

	public void setActivityTime(String activityTime)
	{
	    this.activityTime = activityTime;
	}
	public String getActivityendTime()
	{
	    return activityendTime;
	}

	public void setActivityendTime(String activityendTime)
	{
	    this.activityendTime = activityendTime;
	}
	public Date getActivityDate()
	{
	    return activityDate;
	}

	public void setActivityDate(Date activityDate)
	{
	    this.activityDate = activityDate;
	}
	
}
