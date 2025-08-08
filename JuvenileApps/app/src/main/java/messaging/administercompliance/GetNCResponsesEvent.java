//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCResponsesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCResponsesEvent extends RequestEvent 
{
   private String sprOrderId;
   private String reportType;
   private String criminalcaseId;

	public String getCriminalcaseId() {
	return criminalcaseId;
}
public void setCriminalcaseId(String criminalcaseId) {
	this.criminalcaseId = criminalcaseId;
}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
/**
    * @roseuid 47D9BBF50048
    */
   public GetNCResponsesEvent() 
   {
    
   }
	/**
	 * @return Returns the sprOrderId.
	 */
	public String getSprOrderId() {
		return sprOrderId;
	}
	/**
	 * @param sprOrderId The sprOrderId to set.
	 */
	public void setSprOrderId(String sprOrderId) {
		this.sprOrderId = sprOrderId;
	}
}
