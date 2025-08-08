//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionoptions\\SearchSupervisionConditionsDetailsEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

public class SearchSupervisionConditionsDetailsEvent extends mojo.km.messaging.RequestEvent
{
	private String agencyId;
	private boolean standard;
	private String group1;
	private String group2;
	private String group3;
	private String supervisionTypeId;
	private Collection courts = new ArrayList();
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
	public boolean isStandard()
	{
		return standard;
	}

	/**
	 * @return
	 */
	public String getSupervisionTypeId()
	{
		return supervisionTypeId;
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
	public void addCourt( String courtId )
	{
		if(courts.contains(courtId) == false)
			courts.add( courtId );
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
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		standard = b;
	}

	/**
	 * @param string
	 */
	public void setSupervisionTypeId(String string)
	{
		supervisionTypeId = string;
	}

}
