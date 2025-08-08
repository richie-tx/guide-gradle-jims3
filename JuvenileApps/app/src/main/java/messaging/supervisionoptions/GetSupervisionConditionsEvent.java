//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetSupervisionConditionsEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class GetSupervisionConditionsEvent extends RequestEvent
{
	private String agencyId;
	private Collection courts = new ArrayList();
	private String description;
	private Date effectiveDate;
	private String group1;
	private String group2;
	private String group3;
	private Date inactiveDate;
	private boolean isSpecialCondition;
	private boolean isStandardSelected;
	private String jurisdiction;
	private boolean limitSearchResults;
	private String name;
	private boolean searchForAssociation;
	private boolean standard;
	private String status;
	private String supervisionTypeCd;
	private String unformattedDesc;

	/**
	 * @roseuid 42F7C50A01A5
	 */
	public GetSupervisionConditionsEvent()
	{

	}

	/**
	 * @param collection
	 */
	public void addCourt( String courtId )
	{
		if(courts.contains(courtId) == false)
			courts.add( courtId );
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
	public Collection getCourts()
	{
		return courts;
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
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	public String getSupervisionTypeCd() {
		return supervisionTypeCd;
	}

	/**
	 * @return Returns the unformattedDesc.
	 */
	public String getUnformattedDesc() {
		return unformattedDesc;
	}

	public boolean isLimitSearchResults() {
		return limitSearchResults;
	}

	/**
	 * @return
	 */
	public boolean isSearchForAssociation()
	{
		return searchForAssociation;
	}

	/**
	 * @return
	 */
	public boolean isSpecialCondition()
	{
		return isSpecialCondition;
	}

	/**
	 * @return
	 */
	public boolean isStandard()
	{
		return standard;
	}

	/**
	 * @return
	 */
	public boolean isStandardSelected()
	{
		return isStandardSelected;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
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

	public void setLimitSearchResults(boolean limitSearchResults) {
		this.limitSearchResults = limitSearchResults;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param b
	 */
	public void setSearchForAssociation(boolean b)
	{
		searchForAssociation = b;
	}

	/**
	 * @param b
	 */
	public void setSpecialCondition(boolean b)
	{
		isSpecialCondition = b;
	}

	/**
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		standard = b;
	}

	/**
	 * @param b
	 */
	public void setStandardSelected(boolean b)
	{
		isStandardSelected = b;
	}
	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

	public void setSupervisionTypeCd(String supervisionTypeCd) {
		this.supervisionTypeCd = supervisionTypeCd;
	}

	/**
	 * @param unformattedDesc The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String unformattedDesc) {
		this.unformattedDesc = unformattedDesc;
	}

}
