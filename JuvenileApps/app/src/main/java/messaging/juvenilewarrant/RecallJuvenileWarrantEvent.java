//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\RecallJuvenileWarrantEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class RecallJuvenileWarrantEvent extends RequestEvent 
{
   public Date recallDate;
   public String recallLogonId;
   public String recallReason;
   public Date warrantActivationDate;
   public String warrantActivationStatus;
   public String warrantNum;
   public int warrantStatus;
   
   /**
    * @roseuid 41F7C2B10297
    */
   public RecallJuvenileWarrantEvent() 
   {
    
   }
   
   /**
    * Access method for the recallDate property.
    * 
    * @return   the current value of the recallDate property
    */
   public Date getRecallDate() 
   {
      return recallDate;
   }
   
   /**
    * Access method for the recallLogonId property.
    * 
    * @return   the current value of the recallLogonId property
    */
   public String getRecallLogonId()
   {
      return recallLogonId;
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
    * Access method for the warrantActivationDate property.
    * 
    * @return   the current value of the warrantActivationDate property
    */
   public Date getWarrantActivationDate() 
   {
      return warrantActivationDate;
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
    * Access method for the warrantNum property.
    * 
    * @return   the current value of the warrantNum property
    */
   public String getWarrantNum()
   {
      return warrantNum;
   }
   
   /**
    * Access method for the warrantStatus property.
    * 
    * @return   the current value of the warrantStatus property
    */
   public int getWarrantStatus() 
   {
      return warrantStatus;
   }
   
   /**
    * Sets the value of the recallDate property.
    * 
    * @param aRecallDate the new value of the recallDate property
    */
   public void setRecallDate(Date aRecallDate) 
   {
      recallDate = aRecallDate;
   }
   
   /**
    * Sets the value of the recallLogonId property.
    * 
    * @param aRecallLogonId the new value of the recallLogonId property
    */
   public void setRecallLogonId(String aRecallLogonId)
   {
      recallLogonId = aRecallLogonId;
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
    * Sets the value of the warrantActivationDate property.
    * 
    * @param aWarrantActivationDate the new value of the warrantActivationDate property
    */
   public void setWarrantActivationDate(Date aWarrantActivationDate) 
   {
      warrantActivationDate = aWarrantActivationDate;
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
    * Sets the value of the warrantStatus property.
    * 
    * @param aWarrantStatus the new value of the warrantStatus property
    */
   public void setWarrantStatus(int aWarrantStatus) 
   {
      warrantStatus = aWarrantStatus;
   }
}