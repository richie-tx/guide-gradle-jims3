/*
 * Created on Jun 22, 2006
 *
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetByCourtEvent extends RequestEvent
{
	private String courtId;
	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

}
