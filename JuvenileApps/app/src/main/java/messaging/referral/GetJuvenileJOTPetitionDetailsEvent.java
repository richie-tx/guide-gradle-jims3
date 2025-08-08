//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileReferralDetailsEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileJOTPetitionDetailsEvent extends RequestEvent 
{
   public String dalogNum;   
   public String referralNum;
   
   /**
    * @roseuid 42A9A16F0355
    */
   public GetJuvenileJOTPetitionDetailsEvent() 
   {
    
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
   public String getDalogNum()
   {
       return dalogNum;
   }


   public void setDalogNum(String dalogNum)
   {
       this.dalogNum = dalogNum;
   }

}
