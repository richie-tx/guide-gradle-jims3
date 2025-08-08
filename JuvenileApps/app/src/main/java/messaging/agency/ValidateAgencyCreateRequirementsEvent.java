/*
 * Created on July 05, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.agency;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValidateAgencyCreateRequirementsEvent extends RequestEvent 
{
	private String agencyId;
	private String agencyName;
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}
	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

}