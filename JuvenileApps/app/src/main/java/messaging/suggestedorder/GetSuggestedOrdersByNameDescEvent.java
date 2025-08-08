/*
 * Created on Oct 17, 2005
 *
 */
package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;
/**
 * @author dgibler
 *
 */
public class GetSuggestedOrdersByNameDescEvent extends RequestEvent
{
	private String suggestedOrderDescription;
	private String suggestedOrderName;
	private String agencyId;
	/**
	 * @return
	 */
	public String getSuggestedOrderDescription()
	{
		return suggestedOrderDescription;
	}
	/**
	 * Access method for the suggestedOrderName property.
	 * 
	 * @return   the current value of the suggestedOrderName property
	 */
	public String getSuggestedOrderName()
	{
		return suggestedOrderName;
	}
	/**
	 * @param aSuggestedOrderDescription
	 * @roseuid 433AF0510364
	 */
	public void setSuggestedOrderDescription(String aSuggestedOrderDescription)
	{
		this.suggestedOrderDescription = aSuggestedOrderDescription;
	}
	/**
	 * @param suggestedOrderName
	 * @roseuid 433AF0510318
	 */
	public void setSuggestedOrderName(String theSuggestedOrderName)
	{
		this.suggestedOrderName = theSuggestedOrderName;
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

}
