//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\ActivateViolationOfProbationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class ActivateViolationOfProbationEvent extends RequestEvent 
{
   public String warrantNum;
   public String unSendNotSignedReason;
   public String warrantActivationStatus;
   
   /**
    * @roseuid 4210FFBB006A
    */
   public ActivateViolationOfProbationEvent() 
   {
    
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
    * Sets the value of the warrantNum property.
    * 
    * @param aWarrantNum the new value of the warrantNum property
    */
   public void setWarrantNum(String aWarrantNum)
   {
      warrantNum = aWarrantNum;
   }
   
   /**
    * Access method for the unSendNotSignedReason property.
    * 
    * @return   the current value of the unSendNotSignedReason property
    */
   public String getUnSendNotSignedReason()
   {
      return unSendNotSignedReason;
   }
   
   /**
    * Sets the value of the unSendNotSignedReason property.
    * 
    * @param aUnSendNotSignedReason the new value of the unSendNotSignedReason property
    */
   public void setUnSendNotSignedReason(String aUnSendNotSignedReason)
   {
      unSendNotSignedReason = aUnSendNotSignedReason;
   }
   
   /**
    * Access method for the warrantActivationStatus property.
    * 
    * @return   the current value of the warrantActivationStatus property
    */
   public String getWarrantActivationStatus()
   {
      return warrantActivationStatus;
   }
   
   /**
    * Sets the value of the warrantActivationStatus property.
    * 
    * @param aWarrantActivationStatus the new value of the warrantActivationStatus property
    */
   public void setWarrantActivationStatus(String aWarrantActivationStatus)
   {
      warrantActivationStatus = aWarrantActivationStatus;
   }
   
}
