//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\ActivateJuvenileArrestWarrantEvent.java

package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class ActivateJuvenileArrestWarrantEvent extends RequestEvent 
{
   public String warrantNum;
   public Date warrantActivationDate;
   public String warrantActivationStatus;
   public String status;
   public String warrantSignedStatus;
   
   /**
    * @roseuid 41E6953A0055
    */
   public ActivateJuvenileArrestWarrantEvent() 
   {
    
   }
   
   /**
    * @param warrantNum
    * @roseuid 41E692CB0381
    */
   public void setWarrantNum(String warrantNum) 
   {
    this.warrantNum = warrantNum;
   }
   
   /**
    * @return String
    * @roseuid 41E692CB0383
    */
   public String getWarrantNum() 
   {
    return warrantNum;
   }
   
   /**
    * @param warrantActivationDate
    * @roseuid 41E692CB0385
    */
   public void setWarrantActivationDate(Date warrantActivationDate) 
   {
    this.warrantActivationDate = warrantActivationDate;
   }
   
   /**
    * @return Date
    * @roseuid 41E692CB0387
    */
   public Date getWarrantActivationDate() 
   {
    return warrantActivationDate;
   }
   
   /**
    * @param warrantActivationStatus
    * @roseuid 41E692CB0391
    */
   public void setWarrantActivationStatus(String warrantActivationStatus) 
   {
    this.warrantActivationStatus = warrantActivationStatus;
   }
   
   /**
    * @return String
    * @roseuid 41E692CB0393
    */
   public String getWarrantActivationStatus() 
   {
    return warrantActivationStatus;
   }
   
   /**
    * @param status
    * @roseuid 41E692CB0395
    */
   public void setStatus(String status) 
   {
    this.status = status;
   }
   
   /**
    * @return String
    * @roseuid 41E692CB0397
    */
   public String getStatus() 
   {
    return status;
   }
   
   /**
    * @param warrantSignedStatus
    * @roseuid 41E692CB03A0
    */
   public void setWarrantSignedStatus(String warrantSignedStatus) 
   {
    this.warrantSignedStatus = warrantSignedStatus;
   }
   
   /**
    * @return String
    * @roseuid 41E692CB03A2
    */
   public String getWarrantSignedStatus() 
   {
    return warrantSignedStatus;
   }
}
