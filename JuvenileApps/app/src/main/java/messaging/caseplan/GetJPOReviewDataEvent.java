//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\RetrieveCasePlanDocumentDataEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class GetJPOReviewDataEvent extends RequestEvent 
{
	private String casefileId;
	private String juvenileNum;
	
   /**
    * @roseuid 4533BD0E02E7
    */
   public GetJPOReviewDataEvent() 
   {
    
   }
   
	/**
	 * @return Returns the casefileID.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileID The casefileID to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
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
	
}
