//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetCourtPoliciesEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class GetCourtPoliciesEvent extends RequestEvent
{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private String agencyId;
	private Date effectiveDate;
	private boolean standard;
	private boolean isStandardSelected;
	private String name;
	private String jurisdiction;
	private String group1;
	private String group2;
	private String group3;
	private Date inactiveDate;
	private String status;
	private Collection courts = new ArrayList();
	private String description;
	private boolean searchForAssociation;

	/**
	 * @roseuid 42F7C50301D4
	 */
	public GetCourtPoliciesEvent()
	{

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
		standard = b;
	}

	public boolean isStandard()
	{
		return standard;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @return
	 */
	public Collection getCourts()
	{
		return courts;
	}

	/**
	 * @return
	 */
	public String getGroup1()
	{
		return group1;
	}

	/**
	 * @return
	 */
	public String getGroup2()
	{
		return group2;
	}

	/**
	 * @return
	 */
	public String getGroup3()
	{
		return group3;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * @return
	 */
	public String getJurisdiction()
	{
		return jurisdiction;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param collection
	 */
	public void setCourts(Collection collection)
	{
		courts = collection;
	}

	/**
	 * @param string
	 */
	public void setGroup1(String string)
	{
		group1 = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2(String string)
	{
		group2 = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3(String string)
	{
		group3 = string;
	}

	/**
	 * @param date
	 */
	public void setInactiveDate(Date date)
	{
		inactiveDate = date;
	}

	/**
	 * @param string
	 */
	public void setJurisdiction(String string)
	{
		jurisdiction = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
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
	public boolean isStandardSelected()
	{
		return isStandardSelected;
	}

	/**
	 * @param b
	 */
	public void setStandardSelected(boolean b)
	{
		isStandardSelected = b;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}


	/**
	 * @return
	 */
	public boolean isSearchForAssociation()
	{
		return searchForAssociation;
	}

	/**
	 * @param b
	 */
	public void setSearchForAssociation(boolean b)
	{
		searchForAssociation = b;
	}

}
