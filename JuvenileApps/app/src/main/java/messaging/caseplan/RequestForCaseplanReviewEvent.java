//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\GetCaseplanDetailsEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class RequestForCaseplanReviewEvent extends RequestEvent 
{
   private String casefileID;
   private String caseplanID;
   private String clmLogonID;
   private String comments;
   private String juvenileNum;
   
	public String getJuvenileNum() {
	return juvenileNum;
}
public void setJuvenileNum(String juvenileNum) {
	this.juvenileNum = juvenileNum;
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
	 * @return Returns the clmLogonID.
	 */
	public String getClmLogonID() {
		return clmLogonID;
	}
	/**
	 * @param clmLogonID The clmLogonID to set.
	 */
	public void setClmLogonID(String clmLogonID) {
		this.clmLogonID = clmLogonID;
	}
}