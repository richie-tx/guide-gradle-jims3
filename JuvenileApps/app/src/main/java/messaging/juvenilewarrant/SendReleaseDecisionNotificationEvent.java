//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendReleaseDecisionNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendReleaseDecisionNotificationEvent extends PersistentEvent 
{
   private String warrantNum;
   private String warrantTypeId;
   private String nameFirstMiddleLastSuffix;
   private String nameLastFirstMiddleSuffix;
   private String emailFrom;
   private String emailTo;
   private int notificationType;
   
   /**
    * @roseuid 422375500251
    */
   public SendReleaseDecisionNotificationEvent() 
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
	 * @return
	 */
	public String getEmailFrom()
	{
		return emailFrom;
	}
	
	/**
	 * @return
	 */
	public String getEmailTo()
	{
		return emailTo;
	}
	
	/**
	 * @param string
	 */
	public void setEmailFrom(String string)
	{
		emailFrom = string;
	}
	
	/**
	 * @param string
	 */
	public void setEmailTo(String string)
	{
		emailTo = string;
	}

/**
 * @return
 */
public int getNotificationType()
{
	return notificationType;
}

/**
 * @param i
 */
public void setNotificationType(int i)
{
	notificationType = i;
}

}
