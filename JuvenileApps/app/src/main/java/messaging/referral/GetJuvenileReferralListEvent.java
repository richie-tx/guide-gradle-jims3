//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileProfileReferralListEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileReferralListEvent extends RequestEvent 
{
   public String juvenileNum;
   /**
    * @roseuid 43300A720288
    */
   public GetJuvenileReferralListEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 432836080293
    */
   public void setJuvenileNum(String juvenileNum) 
   {
   	this.juvenileNum = juvenileNum;
   
    
   }
   
   /**
    * @return String
    * @roseuid 432836080295
    */
   public String getJuvenileNum() 
   {
    return this.juvenileNum;
   }
   
}
