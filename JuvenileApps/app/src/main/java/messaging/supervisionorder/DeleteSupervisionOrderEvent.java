/*
 * Created on Mar 13, 2006
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class DeleteSupervisionOrderEvent extends RequestEvent
{
	private String supervisionOrderId;
	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @param aSupervisionOrderId
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		this.supervisionOrderId = aSupervisionOrderId;
	}

}
