//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\RetrieveCasePlanDocumentDataEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class RetrieveCasePlanDocumentDataEvent extends RequestEvent 
{
   
	private String casefileID;
	private String juvenileNum;
	private boolean residential;
	private String caseplanID;
	
   /**
    * @roseuid 4533BD0E02E7
    */
   public RetrieveCasePlanDocumentDataEvent() 
   {
    
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
	 * @return Returns the residential.
	 */
	public boolean isResidential() {
		return residential;
	}
	/**
	 * @param residential The residential to set.
	 */
	public void setResidential(boolean residential) {
		this.residential = residential;
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
}
