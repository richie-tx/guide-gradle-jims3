//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\SaveCLMReviewEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class SaveCLMReviewEvent extends RequestEvent 
{
	private boolean accept;
	private String activityComments;
	private String caseplanID;
   
   /**
    * @roseuid 4533BD1801B6
    */
   public SaveCLMReviewEvent() 
   {
    
   }
	/**
	 * @return Returns the accept.
	 */
	public boolean isAccept() {
		return accept;
	}
	/**
	 * @param accept The accept to set.
	 */
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	/**
	 * @return Returns the caseplanID.
	 */
	public String getCaseplanID() {
		return caseplanID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setCaseplanID(String caseplanID) {
		this.caseplanID = caseplanID;
	}
	/**
	 * @return Returns the activityComments.
	 */
	public String getActivityComments() {
		return activityComments;
	}
	/**
	 * @param activityComments The activityComments to set.
	 */
	public void setActivityComments(String activityComments) {
		this.activityComments = activityComments;
	}
}
