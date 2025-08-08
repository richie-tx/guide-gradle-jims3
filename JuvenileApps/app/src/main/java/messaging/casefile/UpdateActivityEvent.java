//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\CreateActivityEvent.java

package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateActivityEvent extends RequestEvent 
{
	private String activityId;
        private Date activityDate;
	private String comments;
	private String updateComments;
	private String supervisionNumber;
	private String activityCodeId;
	private String activityTypeId;
	private String activityCategoryId;
	private String activityTitle;
	private String detentionId;	
	private boolean fromAction = false;
	private boolean recordActivity = false;
	//US 105411
	private String time;
	
	private String activityTime;
  
/**
    * @roseuid 452132100150
    */
   public UpdateActivityEvent() 
   {
    
   }
	/**
	 * @return Returns the activityCategoryId.
	 */
	public String getActivityCategoryId() {
		return activityCategoryId;
	}
	/**
	 * @param activityCategoryId The activityCategoryId to set.
	 */
	public void setActivityCategoryId(String activityCategoryId) {
		this.activityCategoryId = activityCategoryId;
	}
	/**
	 * @return Returns the activityCodeId.
	 */
	public String getActivityCodeId() {
		return activityCodeId;
	}
	/**
	 * @param activityCodeId The activityCodeId to set.
	 */
	public void setActivityCodeId(String activityCodeId) {
		this.activityCodeId = activityCodeId;
	}
	/**
	 * @return Returns the activityDate.
	 */
	public Date getActivityDate() {
		return activityDate;
	}
	/**
	 * @param activityDate The activityDate to set.
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	/**
	 * @return Returns the activityTitle.
	 */
	public String getActivityTitle() {
		return activityTitle;
	}
	/**
	 * @param activityTitle The activityTitle to set.
	 */
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	/**
	 * @return Returns the activityTypeId.
	 */
	public String getActivityTypeId() {
		return activityTypeId;
	}
	/**
	 * @param activityTypeId The activityTypeId to set.
	 */
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the supervisionNumber.
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}
	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 */
	public void setSupervisionNumber(String supervisionNumber) {
		this.supervisionNumber = supervisionNumber;
	}
	/**
	 * @return Returns the fromAction.
	 */
	public boolean isFromAction() {
		return fromAction;
	}
	/**
	 * @param fromAction The fromAction to set.
	 */
	public void setFromAction(boolean fromAction) {
		this.fromAction = fromAction;
	}
	/**
	 * @return Returns the recordActivity.
	 */
	public boolean isRecordActivity() {
		return recordActivity;
	}
	/**
	 * @param recordActivity The recordActivity to set.
	 */
	public void setRecordActivity(boolean recordActivity) {
		this.recordActivity = recordActivity;
	}
	public String getDetentionId()
	{
	    return detentionId;
	}
	public void setDetentionId(String detentionId)
	{
	    this.detentionId = detentionId;
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
	public String getActivityId()
	{
	    return activityId;
	}
	public void setActivityId(String activityId)
	{
	    this.activityId = activityId;
	}
	public String getUpdateComments()
	{
	    return updateComments;
	}
	public void setUpdateComments(String updateComments)
	{
	    this.updateComments = updateComments;
	}
}
