//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\InactivateJuvenileWarrantEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class InactivateJuvenileWarrantEvent extends RequestEvent 
{
   public String recallReason;
   public String warrantNum;
   
   /**
    * @roseuid 41F7C2AB01DC
    */
   public InactivateJuvenileWarrantEvent() 
   {
    
   }
   
   /**
    * Access method for the recallReason property.
    * 
    * @return   the current value of the recallReason property
    */
   public String getRecallReason()
   {
      return recallReason;
   }
   
   /**
    * Access method for the warrantNum property.
    * 
    * @return   the current value of the warrantNum property
    */
   public String getWarrantNum()
   {
      return warrantNum;
   }
   
   /**
    * Sets the value of the recallReason property.
    * 
    * @param aRecallReason the new value of the recallReason property
    */
   public void setRecallReason(String aRecallReason)
   {
      recallReason = aRecallReason;
   }

   /**
    * Sets the value of the warrantNum property.
    * 
    * @param aWarrantNum the new value of the warrantNum property
    */
   public void setWarrantNum(String aWarrantNum)
   {
      warrantNum = aWarrantNum;
   }
}