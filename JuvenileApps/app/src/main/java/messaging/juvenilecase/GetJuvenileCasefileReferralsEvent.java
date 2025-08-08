//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileReferralsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileReferralsEvent extends RequestEvent 
{
   public String supervisionNum;
   public String juvenileNum;
   
   /**
    * @roseuid 4278C82F0396
    */
   public GetJuvenileCasefileReferralsEvent() 
   {
    
   }
   
   /**
    * @param supervisionNum
    * @roseuid 4278C7B902C4
    */
   public void setSupervisionNum(String aSupervisionNum) 
   {
    this.supervisionNum = aSupervisionNum;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B902C6
    */
   public String getSupervisionNum() 
   {
    return this.supervisionNum;
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
