//Source file: C:\\views\\dev\\app\\src\\pd\\notification\\juvenilewarrant\\JuvenileNotificationController.java

package pd.notification.juvenilewarrant;

import java.util.Date;

/**
 * @stereotype control
 */
public class JuvenileNotificationController 
{
   
   /**
    * @roseuid 41EE6ACB034B
    */
   public JuvenileNotificationController() 
   {
    
   }
   
   /**
    * @stereotype design
    * @param phone
    * @param warrantAcknowledgeDate
    * @param officerPagerNum
    * @param officerCellPhone
    * @param notificationType
    * @param emailTo
    * @param emailFrom
    * @param officerLastName
    * @param officerFirstName
    * @param agencyId
    * @param fileStampDate
    * @param warrantNum
    * @param lastName
    * @param firstName
    * @roseuid 41E555C2005E
    */
   public void sendDirectiveToApprehendNotification(String phone, Date warrantAcknowledgeDate, String officerPagerNum, String officerCellPhone, int notificationType, String emailTo, String emailFrom, String officerLastName, String officerFirstName, String agencyId, Date fileStampDate, String warrantNum, String lastName, String firstName) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param notificationType
    * @param emailTo
    * @param emailFrom
    * @param warrantNum
    * @roseuid 41E555C200CF
    */
   public void sendOICNotification(int notificationType, String emailTo, String emailFrom, String warrantNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param notificationType
    * @param emailTo
    * @param emailFrom
    * @param lastName
    * @param firstName
    * @param warrantNum
    * @roseuid 41E555C401D4
    */
   public void sendProbableCauseWarrantNotification(int notificationType, String emailTo, String emailFrom, String lastName, String firstName, String warrantNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @roseuid 41E56D9801C9
    */
   public void sendJuvenileArrestWarrantNotification() 
   {
    
   }
   
   /**
    * @stereotype design
    * @param notificationType
    * @param emailTo
    * @param emailFrom
    * @param lastName
    * @param firstName
    * @param warrantNum
    * @roseuid 420BB1E3031C
    */
   public void sendProbableCauseServiceNotification(int notificationType, String emailTo, String emailFrom, String lastName, String firstName, String warrantNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param agencyName
    * @param officerId
    * @param logonId
    * @param warrantNum
    * @roseuid 420BB5860222
    */
   public void sendInvalidAddressNotification(String agencyName, String officerId, String logonId, String warrantNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param officerWorkPhone
    * @param juvenileLastName
    * @param juvenileFirstName
    * @param warrantNum
    * @roseuid 420BB5860246
    */
   public void sendReturnOfServiceNotification(String officerWorkPhone, int juvenileLastName, int juvenileFirstName, String warrantNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param arrestTimeStamp
    * @param officerWorkPhone
    * @param officerLastName
    * @param officerFirstName
    * @param juvenileLastName
    * @param juvenileFirstName
    * @param warrantNum
    * @param agencyName
    * @roseuid 420BB5870032
    */
   public void sendSuccessfulWarrantServiceNotification(int arrestTimeStamp, String officerWorkPhone, String officerLastName, String officerFirstName, int juvenileLastName, int juvenileFirstName, String warrantNum, String agencyName) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param notificationType
    * @roseuid 4210F86000ED
    */
   public void sendViolationOfProbationNotification(int notificationType) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param juvenileLastName
    * @param juvenileFirstName
    * @param warrantNum
    * @roseuid 421370560330
    */
   public void sendJuvenileWarrantReleaseNotification(int juvenileLastName, int juvenileFirstName, String warrantNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param serviceTimeStamp
    * @param juvenileLastName
    * @param juvenileFirstName
    * @param warrantNum
    * @roseuid 421395680261
    */
   public void processReturnOfServiceNotification(Date serviceTimeStamp, int juvenileLastName, int juvenileFirstName, String warrantNum) 
   {
    
   }
   
  /**
   * @stereotype design
   *
   */
   public void sendReleaseDecisionNotification(String warrantNum)
   {

   }
   
   /**
   * @stereotype design
   *
   */
   public void sendFailureToEnterReleaseDetailsNotification(String warrantNum, String emailTo, String emailFrom, String taskName)
   {
   }
}

