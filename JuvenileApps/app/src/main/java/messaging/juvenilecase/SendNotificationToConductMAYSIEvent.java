//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SendNotificationToConductMAYSIEvent.java

package messaging.juvenilecase;

import java.util.Date;

import mojo.km.messaging.PersistentEvent;

public class SendNotificationToConductMAYSIEvent extends PersistentEvent 
{
   private Date assignmentAddDate;
   private String referralNumber;
   private String emailFrom;
   private String emailTo;
   
   /**
    * @roseuid 427FAB39029F
    */
   public SendNotificationToConductMAYSIEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public Date getAssignmentAddDate()
	{
		return assignmentAddDate;
	}
	
	/**
	 * @return
	 */
	public String getReferralNumber()
	{
		return referralNumber;
	}
	
	/**
	 * @param date
	 */
	public void setAssignmentAddDate(Date date)
	{
		assignmentAddDate = date;
	}
	
	/**
	 * @param string
	 */
	public void setReferralNumber(String string)
	{
		referralNumber = string;
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

}
