//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SendUpdateJuvenileCasefileNotificationEvent.java

package messaging.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import mojo.km.messaging.PersistentEvent;

public class SendUpdateJuvenileCasefileNotificationEvent extends PersistentEvent 
{
   private String juvenileNumber;
   private String juvenileFirstName;
   private String juvenileLastName;
   private Collection assignments = new ArrayList();
   private Date assignmentDate;
   private String emailFrom;
   private String emailTo;
   
   /**
    * @roseuid 427FAB3B00CB
    */
   public SendUpdateJuvenileCasefileNotificationEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public Date getAssignmentDate()
	{
		return assignmentDate;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}
	
	/**
	 * @param date
	 */
	public void setAssignmentDate(Date date)
	{
		assignmentDate = date;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileFirstName(String string)
	{
		juvenileFirstName = string;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileLastName(String string)
	{
		juvenileLastName = string;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}
	
	/**
	 * @return
	 */
	public Collection getAssignments()
	{
		return assignments;
	}
	
	/**
	 * @param collection
	 */
	public void setAssignments(Collection collection)
	{
		assignments = collection;
	}

	/**
	 * @param collection
	 */
	public void insertAssignment(JuvenileCasefileReferralsResponseEvent referralResponseEvent)
	{
		assignments.add(referralResponseEvent);
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
