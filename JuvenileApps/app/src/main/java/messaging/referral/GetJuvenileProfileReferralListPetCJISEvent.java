//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileProfileReferralListEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileProfileReferralListPetCJISEvent extends RequestEvent 
{
   public String juvenileNum;
   public String refNum;

/**
    * @roseuid 43300A720288
    */
   public GetJuvenileProfileReferralListPetCJISEvent() 
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
   public String getRefNum()
   {
       return refNum;
   }

   public void setRefNum(String refNum)
   {
       this.refNum = refNum;
   }
}
