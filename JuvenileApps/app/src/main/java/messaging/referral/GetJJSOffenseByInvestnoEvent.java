//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileOffensesEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJJSOffenseByInvestnoEvent extends RequestEvent 
{
   public String inquiryNum;
   
   /**
    * @roseuid 42A9A16B0396
    */
   public GetJJSOffenseByInvestnoEvent() 
   {
    
   }
   
   /**
    * Access method for the juvenileNum property.
    * 
    * @return   the current value of the juvenileNum property
    */
   public String getInquiryNum()
   {
      return inquiryNum;
   }
   
   /**
    * Sets the value of the juvenileNum property.
    * 
    * @param aJuvenileNum the new value of the juvenileNum property
    */
   public void setInquiryNum(String aJuvenileNum)
   {
      inquiryNum = aJuvenileNum;
   }
   
}
