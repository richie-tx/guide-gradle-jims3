//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\GetActivitiesEvent.java

package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetActivitiesEvent extends RequestEvent 
{
   
	private String casefileID;
	private String juvenileNum;
	private String categoryId;
	private String activityTypeId;
	private String activityCodeId;
	private Date startDate;
	private Date endDate;
	private String activityTime;
	
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
    * @roseuid 4521321B00AC
    */
   public GetActivitiesEvent() 
   {
    
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
	 * @return Returns the casefileID.
	 */
	public String getCasefileID() {
		return casefileID;
	}
	/**
	 * @param casefileID The casefileID to set.
	 */
	public void setCasefileID(String casefileID) {
		this.casefileID = casefileID;
	}
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getActivityTime()
	{
	    return activityTime;
	}
	public void setActivityTime(String activityTime)
	{
	    this.activityTime = activityTime;
	}
}
