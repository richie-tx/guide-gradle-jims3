//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileOffensesEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJJSReferralEvent extends RequestEvent 
{
   public String juvenileNum;
   public String referralNum;
   
   /**
    * @roseuid 42A9A16B0396
    */
   public GetJJSReferralEvent() 
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
   
   /**
    * Access method for the referralNum property.
    * 
    * @return   the current value of the referralNum property
    */
   public String getReferralNum()
   {
      return referralNum;
   }
   
   /**
    * Sets the value of the referralNum property.
    * 
    * @param aReferralNum the new value of the referralNum property
    */
   public void setReferralNum(String aReferralNum)
   {
      referralNum = aReferralNum;
   }
   
   
}
