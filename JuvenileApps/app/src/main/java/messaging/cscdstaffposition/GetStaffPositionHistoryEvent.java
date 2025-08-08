//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\GetStaffPositionHistoryEvent.java

package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetStaffPositionHistoryEvent extends RequestEvent 
{
	private String agencyId;
    private	Date beginDate;
	private	Date endDate;
	private	boolean isRetired;
	private String positionStatusId;
	private	String reportType;
	private	String searchkeyId;  // this in general is the orgId, or positionId, or staff Id of the item being searched

   /**
    * @roseuid 460BDCCF00C7
    */
   public GetStaffPositionHistoryEvent() 
   {
    
   }
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
	/**
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @return Returns the isRetired.
	 */
	public boolean getIsRetired() {
		return isRetired;
	}
	/**
	 * @return Returns the positionStatusId.
	 */
	public String getPositionStatusId() {
		return positionStatusId;
	}
	/**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @return Returns the searchkeyId.
	 */
	public String getSearchkeyId() {
		return searchkeyId;
	}
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
	/**
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @param isRetired The isRetired to set.
	 */
	public void setIsRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}
	/**
	 * @param positionStatusId The positionStatusId to set.
	 */
	public void setPositionStatusId(String positionStatusId) {
		this.positionStatusId = positionStatusId;
	}
	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @param searchkeyId The searchkeyId to set.
	 */
	public void setSearchkeyId(String searchkeyId) {
		this.searchkeyId = searchkeyId;
	}
}
