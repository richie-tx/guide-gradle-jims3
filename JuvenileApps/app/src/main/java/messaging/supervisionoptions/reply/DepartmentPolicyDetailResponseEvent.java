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
public class DepartmentPolicyDetailResponseEvent extends ResponseEvent
{
	private String name;
	private String group1Id;
	private String group1Name;
	private String group2Id;
	private String group2Name;
	private String group3Id;
	private String group3Name;
	private String agencyId;
	private String departmentId;
	private String description;
	private Date effectiveDate;
	private Date inactiveDate;
	private String notes;
	private String groupId;
	private Collection courtIds = new ArrayList();
	   
	/**
	 * @roseuid 42F7C5060196
	 */
	public DepartmentPolicyDetailResponseEvent() 
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
	   * @param string
	   */
	  public void setAgencyId(String string)
	  {
		  agencyId = string;
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
		 courtIds.add( courtId );
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

}
