//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileProfileReferralListEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetReferralListwithDALognumEvent extends RequestEvent 
{
   public String dalogNum;

/**
    * @roseuid 43300A720288
    */
   public GetReferralListwithDALognumEvent() 
   {
    
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
