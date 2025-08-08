//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileProfileReferralDetailsEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileProfileReferralDetailsEvent extends RequestEvent 
{
   public String juvenileNum;
   /**
    * @roseuid 42A9A16B0396
    */
   public GetJuvenileProfileReferralDetailsEvent() 
   {
    
   }
   
 

/**
    * Access method for the juvenileNum property.
    * 
    * @return   the current value of the juvenileNum property
    */
   public String getJuvenileNum()
   {
      return juvenileNum;
   }
   
   /**
    * Sets the value of the juvenileNum property.
    * 
    * @param aJuvenileNum the new value of the juvenileNum property
    */
   public void setJuvenileNum(String aJuvenileNum)
   {
      juvenileNum = aJuvenileNum;
   }
   
   
}
