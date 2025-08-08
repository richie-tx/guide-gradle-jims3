/*
 * Created on Mar 31, 2006
 *
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetDetailDictionaryReferenceVariablesEvent extends RequestEvent
{	
	private String agencyId;

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
