//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveSupervisionConditionEvent.java

package messaging.supervisionoptions;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.messaging.RequestEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class SaveSupervisionConditionEvent extends RequestEvent 
{
	private String conditionId;
	private String agencyId;
	private String description;
	private String unformattedDesc;
    private String spanishDescription;
	private Date effectiveDate;
	private Date inactiveDate;
	private boolean isStandard;
	private String name;
	private String notes;
	private String groupId;
	private String severityLevelId;
	private Collection documents = new ArrayList(); // Strings of Code id's for documents
	private String jurisdictionId;
	private Collection eventTypes = new ArrayList(); // Strings of eventTypeId
	private int eventCountValue;
	private int eventPeriodValue;
	private String eventPeriodTypeId;
	private Collection taskRecipients = new ArrayList();
	private String taskSubject;
	private int taskDueBy;
	private String emailNotificationTo;
	private boolean isNewCourtSelection;
	private Collection variableElements = new ArrayList();  /// court, value, fixed?
	private Collection supervisionTypes = new ArrayList(); // Strings of Code id's for supervison types
	private Collection policyIds = new ArrayList(); 

   
   /**
    * @roseuid 42F7C514034B
    */
   public SaveSupervisionConditionEvent() 
   {
    
   }
   
   /**
	* @return
	*/
   public String getConditionId()
   {
	   return conditionId;
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
    public String getSpanishDescription()
    {
        return spanishDescription;
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
	public void setConditionId(String string)
	{
		conditionId = string;
	}
	
	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}
	
    /**
      * @param string
      */
     public void setSpanishDescription(String string)
     {
         spanishDescription = string;
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
	public Collection getDocuments()
	{
		return documents;
	}
	
	/**
	 * @return
	 */
	public String getEmailNotificationTo()
	{
		return emailNotificationTo;
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
	 * @return
	 */
	public Collection getEventTypes()
	{
		return eventTypes;
	}
	
	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}
	
	/**
	 * @return
	 */
	public String getJurisdictionId()
	{
		return jurisdictionId;
	}
	
	/**
	 * @return
	 */
	public String getSeverityLevelId()
	{
		return severityLevelId;
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
	 * @return
	 */
	public Collection getVariableElements()
	{
		return variableElements;
	}
	
	/**
	 * @param collection
	 */
	public void addDocument( String docId )
	{
		documents.add( docId );
	}
	
	/**
	 * @param string
	 */
	public void setEmailNotificationTo(String string)
	{
		emailNotificationTo = string;
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
	 * @param string
	 */
	public void addEventType( String eventTypeId )
	{
		eventTypes.add( eventTypeId );
	}

	/**
	 * @param string
	 */
	public void setEventTypes( Collection eventTypes )
	{
		this.eventTypes = eventTypes;
	}

	
	/**
	 * @param string
	 */
	public void setGroupId(String string)
	{
		groupId = string;
	}
	
	/**
	 * @param string
	 */
	public void setJurisdictionId(String string)
	{
		jurisdictionId = string;
	}
	
	/**
	 * @param string
	 */
	public void setSeverityLevelId(String string)
	{
		severityLevelId = string;
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
	public Collection getSupervisionTypes()
	{
		return supervisionTypes;
	}

	/**
	 * @param collection
	 */
	public void setSupervisionTypes(Collection collection)
	{
		supervisionTypes = collection;
	}

	/**
	 * @param collection
	 */
	public void addSupervisionType(String supervisionTypeId)
	{
		supervisionTypes.add(supervisionTypeId);
	}
	/**
	 * @return
	 */
	public Collection getPolicyIds()
	{
		return policyIds;
	}

	/**
	 * @param collection
	 */
	public void setPolicyIds(Collection collection)
	{
		policyIds = collection;
	}

	/**
	 * @return Returns the unformattedDesc.
	 */
	public String getUnformattedDesc() {
		return unformattedDesc;
	}
	/**
	 * @param unformattedDesc The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String unformattedDesc) {
		this.unformattedDesc = unformattedDesc;
	}
}
