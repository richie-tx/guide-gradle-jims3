//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\AcknowledgeDirectiveToApprehendEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class AcknowledgeDirectiveToApprehendEvent extends RequestEvent 
{
   public Date warrantAcknowledgeDate;
   public String warrantAcknowledgeStatus;
   public String warrantNum;
   
   /**
    * @roseuid 4187CDE301CF
    */
   public AcknowledgeDirectiveToApprehendEvent() 
   {
    
   }
   
   /**
    * @return Date
    * @roseuid 4187BDDD008A
    */
   public Date getWarrantAcknowledgeDate() 
   {
    return warrantAcknowledgeDate;
   }
   
   /**
    * @return String
    * @roseuid 4187BDDD007C
    */
   public String getWarrantAcknowledgeStatus() 
   {
    return warrantAcknowledgeStatus;
   }
   
   /**
    * @return String
    * @roseuid 4187BDDD0078
    */
   public String getWarrantNum() 
   {
    return warrantNum;
   }
   
   /**
    * @param warrantAcknowledgeDate
    * @roseuid 4187BDDD0088
    */
   public void setWarrantAcknowledgeDate(Date warrantAcknowledgeDate) 
   {
    	this.warrantAcknowledgeDate = warrantAcknowledgeDate;
   }
   
   /**
    * @param warrantAcknowledgeStatus
    * @roseuid 4187BDDD007A
    */
   public void setWarrantAcknowledgeStatus(String warrantAcknowledgeStatus) 
   {
    	this.warrantAcknowledgeStatus = warrantAcknowledgeStatus;
   }
   
   /**
    * @param warrantNum
    * @roseuid 4187BDDD0067
    */
   public void setWarrantNum(String warrantNum) 
   {
    	this.warrantNum = warrantNum;
   }
}
