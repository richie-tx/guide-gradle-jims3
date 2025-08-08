//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\ActivateDirectiveToApprehendEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class ActivateDirectiveToApprehendEvent extends RequestEvent 
{
   public String status;
   public Date warrantActivationDate;
   public String warrantActivationStatus;
   public String warrantNum;
   public String warrantSignedStatus;
   
   /**
    * @roseuid 4187CDEE024C
    */
   public ActivateDirectiveToApprehendEvent() 
   {
    
   }
   
   /**
    * @return String
    * @roseuid 4187BDE700E9
    */
   public String getStatus() 
   {
    return status;
   }
   
   /**
    * @return Date
    * @roseuid 4187BDE700CA
    */
   public Date getWarrantActivationDate() 
   {
    return warrantActivationDate;
   }
   
   /**
    * @return String
    * @roseuid 4187BDE700D8
    */
   public String getWarrantActivationStatus() 
   {
    return warrantActivationStatus;
   }
   
   /**
    * @return String
    * @roseuid 4187BDE700C6
    */
   public String getWarrantNum() 
   {
    return warrantNum;
   }
   
   /**
    * @return String
    * @roseuid 4187BDE700E5
    */
   public String getWarrantSignedStatus() 
   {
    return warrantSignedStatus;
   }
   
   /**
    * @param status
    * @roseuid 4187BDE700E7
    */
   public void setStatus(String status) 
   {
    	this.status = status;
   }
   
   /**
    * @param warrantActivationDate
    * @roseuid 4187BDE700C8
    */
   public void setWarrantActivationDate(Date warrantActivationDate) 
   {
    	this.warrantActivationDate = warrantActivationDate;
   }
   
   /**
    * @param warrantActivationStatus
    * @roseuid 4187BDE700D6
    */
   public void setWarrantActivationStatus(String warrantActivationStatus) 
   {
    	this.warrantActivationStatus = warrantActivationStatus;
   }
   
   /**
    * @param warrantNum
    * @roseuid 4187BDE700B8
    */
   public void setWarrantNum(String warrantNum) 
   {
    	this.warrantNum = warrantNum;
   }
   
   /**
    * @param warrantSignedStatus
    * @roseuid 4187BDE700DA
    */
   public void setWarrantSignedStatus(String warrantSignedStatus) 
   {
    	this.warrantSignedStatus = warrantSignedStatus;
   }
}