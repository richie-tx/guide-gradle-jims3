/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class CourtPolicyDetailResponseEvent extends ResponseEvent
{
	private String agencyId;
	private String policyId;
	private String name;
	private String group1Id;
	private String group1Name;
	private String group2Id;
	private String group2Name;
	private String group3Id;
	private String group3Name;
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
	private Collection taskRecipients = new ArrayList();
	private String taskSubject;
	private int taskDueBy;
	private String emailNotificationTo;
	private boolean isNewCourtSelection;
	private Collection variableElements = new ArrayList();  /// court, value, fixed?

	
   /**
	* @roseuid 42F7C512001F
	*/
   public CourtPolicyDetailResponseEvent() 
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
	public void setNotes(String string)
	{
		notes = string;
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
	 * @return
	 */
	public void setVariableElements(Collection veres)
	{
		this.variableElements.addAll(veres);
	}
	/**
	 * @param collection
	 */
	public void addVariableElements( VariableElementResponseEvent evt )
	{
		variableElements.add( evt );
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
	 * 
	 */
	public void setName( String aName )
	{
		name = aName;
	}

	/**
	 * @return
	 */
	public String getGroup1Id()
	{
		return group1Id;
	}

	/**
	 * @return
	 */
	public String getGroup2Id()
	{
		return group2Id;
	}

	/**
	 * @return
	 */
	public String getGroup3Id()
	{
		return group3Id;
	}

	/**
	 * @param string
	 */
	public void setGroup1Id(String string)
	{
		group1Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2Id(String string)
	{
		group2Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string)
	{
		group3Id = string;
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
	 * @return
	 */
	public String getGroup1Name()
	{
		return group1Name;
	}

	/**
	 * @return
	 */
	public String getGroup2Name()
	{
		return group2Name;
	}

	/**
	 * @return
	 */
	public String getGroup3Name()
	{
		return group3Name;
	}

	/**
	 * @param string
	 */
	public void setGroup1Name(String string)
	{
		group1Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2Name(String string)
	{
		group2Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Name(String string)
	{
		group3Name = string;
	}

	/**
	 * @return
	 */
	public String getPolicyId()
	{
		return policyId;
	}

	/**
	 * @param string
	 */
	public void setPolicyId(String string)
	{
		policyId = string;
	}

	/**
	 * @return
	 */
	public boolean isDepartmentPolicy()
	{
		return isDepartmentPolicy;
	}

	/**
	 * @param b
	 */
	public void setDepartmentPolicy(boolean b)
	{
		isDepartmentPolicy = b;
	}

}
