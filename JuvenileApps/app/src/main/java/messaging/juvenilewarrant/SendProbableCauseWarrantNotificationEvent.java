//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendProbableCauseWarrantNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendProbableCauseWarrantNotificationEvent  extends PersistentEvent 
{
   public String warrantNum;
   private String warrantTypeId;
   private String nameFirstMiddleLastSuffix;
   private String nameLastFirstMiddleSuffix;
   public String emailFrom;
   public String emailTo;
   public int notificationType;
   private String taskName;
   
   /**
    * @roseuid 41E6BF7F01E4
    */
   public SendProbableCauseWarrantNotificationEvent() 
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
      this.warrantNum = aWarrantNum;
   }
   
   /**
    *  Access method for the warrantTypeId property.
    * 
	* @return Returns the current value of the warrantTypeId.
	*/
	public String getWarrantTypeId()
	{
	    return warrantTypeId;
	}
	
	/**
	 *Sets the value of the warrantTypeId property.
	 *
	 *  @param awarrantTypeId The warrantTypeId to set.
	 */
	public void setWarrantTypeId(String aWarrantTypeId)
	{
	    this.warrantTypeId = aWarrantTypeId;
	}
	
   /**
    * Access method for the nameLastFirstMiddleSuffix property.
    * 
    * @return   the current value of the nameFirstMiddleLastSuffix property
    */
   public String getNameFirstMiddleLastSuffix()
   {
      return nameFirstMiddleLastSuffix;
   }
   
   /**
    * Sets the value of the fromatted nameFirstMiddleLastSuffix property.
    * 
    * @param aNameLastFirstMiddleSuffix the new value of the nameLastFirstMiddleSuffix property
    */
   public void setNameFirstMiddleLastSuffix(String aNameFirstMiddleLastSuffix)
   {
      this.nameFirstMiddleLastSuffix = aNameFirstMiddleLastSuffix;
   }
   
   /**
    * Access method for the nameLastFirstMiddleSuffix property.
    * 
    * @return   the current value of the nameLastFirstMiddleSuffix property
    */
   public String getNameLastFirstMiddleSuffix()
   {
      return nameLastFirstMiddleSuffix;
   }
   
   /**
    * Sets the value of the formatted nameLastFirstMiddleSuffix property.
    * 
    * @param aNameLastFirstMiddleSuffix the new value of the nameLastFirstMiddleSuffix property
    */
   public void setNameLastFirstMiddleSuffix(String aNameLastFirstMiddleSuffix)
   {
      this.nameLastFirstMiddleSuffix = aNameLastFirstMiddleSuffix;
   }
   
   /**
    * Access method for the emailFrom property.
    * 
    * @return   the current value of the emailFrom property
    */
   public String getEmailFrom()
   {
      return emailFrom;
   }
   
   /**
    * Sets the value of the emailFrom property.
    * 
    * @param aEmailFrom the new value of the emailFrom property
    */
   public void setEmailFrom(String aEmailFrom)
   {
      this.emailFrom = aEmailFrom;
   }
   
   /**
    * Access method for the emailTo property.
    * 
    * @return   the current value of the emailTo property
    */
   public String getEmailTo()
   {
      return emailTo;
   }
   
   /**
    * Sets the value of the emailTo property.
    * 
    * @param aEmailTo the new value of the emailTo property
    */
   public void setEmailTo(String aEmailTo)
   {
      this.emailTo = aEmailTo;
   }
   
   /**
    * Access method for the notificationType property.
    * 
    * @return   the current value of the notificationType property
    */
   public int getNotificationType() 
   {
      return notificationType;
   }
   
   /**
    * Sets the value of the notificationType property.
    * 
    * @param aNotificationType the new value of the notificationType property
    */
   public void setNotificationType(int aNotificationType) 
   {
      this.notificationType = aNotificationType;
   }
   
 
/**
 * @return
 */
public String getTaskName()
{
	return taskName;
}

/**
 * @param string
 */
public void setTaskName(String string)
{
	taskName = string;
}

}
