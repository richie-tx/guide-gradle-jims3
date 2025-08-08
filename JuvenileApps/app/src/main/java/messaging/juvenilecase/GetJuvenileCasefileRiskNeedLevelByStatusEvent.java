//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileRiskNeedLevelByStatusEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileRiskNeedLevelByStatusEvent extends RequestEvent 
{
   public String caseFileId;
   public String juvenileNum; 
   public String status;
   /**
    * @roseuid 4278C82F0396
    */
   public GetJuvenileCasefileRiskNeedLevelByStatusEvent() 
   {
    
   }
   
   /**
    * @param caseFileId
    * @roseuid 4278C7B902C4
    */
   public void setCaseFileId(String aCaseFileId) 
   {
    this.caseFileId = aCaseFileId;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B902C6
    */
   public String getCaseFileId() 
   {
    return this.caseFileId;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
