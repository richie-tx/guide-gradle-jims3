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
 */
public class ValidateAgencyDeleteRequirementsEvent extends RequestEvent 
{
	private String agencyId;

	/**
	 * @return agencyId
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