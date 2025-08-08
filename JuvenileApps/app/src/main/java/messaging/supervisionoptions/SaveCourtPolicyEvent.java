//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveCourtPolicyEvent.java

package messaging.supervisionoptions;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.messaging.RequestEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class SaveCourtPolicyEvent extends RequestEvent 
{
	private String courtId;
	private String agencyId;
	private String groupId;
	private boolean isStandard;
	private boolean isDepartmentPolicy;
	private String description;
	private Date effectiveDate;
	private Date inactiveDate;
	private Collection eventTypes = new ArrayList();
	private int eventCountValue;
	private int eventPeriodValue;
	private String eventPeriodTypeId;
	private String notes;
	private String name;
	private Collection taskRecipients = new ArrayList();
	private String taskSubject;
	private int taskDueBy;
	private String emailNotificationTo;
	private boolean isNewCourtSelection;
	private Collection variableElements = new ArrayList();  /// court, value, fixed?
	private String courtPolicyId;

	
   /**
    * @roseuid 42F7C512001F
    */
   public SaveCourtPolicyEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}
	
	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * @return
	 */
	public Date getEffectiveDate()
	{
		return effectiveDate;
	}
	
	/**
	 * @return
	 */
	public boolean isStandard()
	{
		return isStandard;
	}
	
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}
	
	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}
	
	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}
	
	/**
	 * @param date
	 */
	public void setEffectiveDate(Date date)
	{
		effectiveDate = date;
	}
	
	/**
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		isStandard = b;
	}
	
	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}
	
	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}
	
	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}
	
	/**
	 * @param string
	 */
	public void setGroupId(String string)
	{
		groupId = string;
	}
	
	/**
	 * @return
	 */
	public Collection getEventTypes()
	{
		return eventTypes;
	}
	
	/**
	 * @param string
	 */
	public void addEventType( String eventTypeId )
	{
		eventTypes.add( eventTypeId );
	}
	
	/**
	 * @return
	 */
	public int getEventCountValue()
	{
		return eventCountValue;
	}
	
	/**
	 * @return
	 */
	public String getEventPeriodTypeId()
	{
		return eventPeriodTypeId;
	}
	
	/**
	 * @return
	 */
	public int getEventPeriodValue()
	{
		return eventPeriodValue;
	}
	
	/**
	 * @param string
	 */
	public void setEventCountValue(int value)
	{
		eventCountValue = value;
	}
	
	/**
	 * @param string
	 */
	public void setEventPeriodTypeId(String string)
	{
		eventPeriodTypeId = string;
	}
	
	/**
	 * @param string
	 */
	public void setEventPeriodValue(int value)
	{
		eventPeriodValue = value;
	}
	
	/**
	 * @return
	 */
	public int getTaskDueBy()
	{
		return taskDueBy;
	}
	
	/**
	 * @return
	 */
	public Collection getTaskRecipients()
	{
		return taskRecipients;
	}
	
	/**
	 * @return
	 */
	public String getTaskSubject()
	{
		return taskSubject;
	}
	
	/**
	 * @param i
	 */
	public void setTaskDueBy(int i)
	{
		taskDueBy = i;
	}
	
	/**
	 * @param collection
	 */
	public void addTaskRecipient( Object taskRecipient )
	{
		//TODO JBS - fix parameter
		taskRecipients.add( taskRecipient );
	}
	
	/**
	 * @param string
	 */
	public void setTaskSubject(String string)
	{
		taskSubject = string;
	}
	
	/**
	 * @return
	 */
	public String getEmailNotificationTo()
	{
		return emailNotificationTo;
	}
	
	/**
	 * @param string
	 */
	public void setEmailNotificationTo(String string)
	{
		emailNotificationTo = string;
	}
	
	/**
	 * @return
	 */
	public Collection getVariableElements()
	{
		return variableElements;
	}
	
	/**
	 * @param collection
	 */
	public void addVariableElement( VariableElementResponseEvent evt )
	{
		variableElements.add( evt );
	}

	/**
	 * @param collection
	 */
	public void setVariableElements( Collection collection )
	{
		variableElements = collection;
	}

	/**
	 * @return
	 */
	public boolean isNewCourtSelection()
	{
		return isNewCourtSelection;
	}

	/**
	 * @param b
	 */
	public void setNewCourtSelection(boolean b)
	{
		isNewCourtSelection = b;
	}

	

	/**
	 * @return
	 */
	public String getCourtPolicyId()
	{
		return courtPolicyId;
	}

	/**
	 * @param string
	 */
	public void setCourtPolicyId(String string)
	{
		courtPolicyId = string;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * @param date
	 */
	public void setInactiveDate(Date date)
	{
		inactiveDate = date;
	}

	/**
	 * @param b
	 */
	public void setDepartmentPolicy(boolean b)
	{
		isDepartmentPolicy = b;
	}

	/**
	 * @return
	 */
	public boolean isDepartmentPolicy()
	{
		return isDepartmentPolicy;
	}

}
