//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveDepartmentPolicyEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class SaveDepartmentPolicyEvent extends RequestEvent 
{
	private String agencyId;
	private String departmentId;
	private String description;
	private Date effectiveDate;
	private Date inactiveDate;
	private String name;
	private String notes;
	private String groupId;
	private Collection courtIds = new ArrayList();
   
   /**
    * @roseuid 42F7C513037A
    */
   public SaveDepartmentPolicyEvent() 
   {
    
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
	public Collection getCourtIds()
	{
		return courtIds;
	}
	
	/**
	 * @param collection
	 */
	public void addCourtId(String courtId)
	{
		courtIds.add(courtId);
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
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
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

}
