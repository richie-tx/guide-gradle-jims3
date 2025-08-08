/*
 * Created on Nov 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetOffenseCodeEvent extends RequestEvent
{
	private String offenseCode;
	/**
	 * @return
	 */
	public String getOffenseCode()
	{
		return offenseCode;
	}

	/**
	 * @param theOffenseCode
	 */
	public void setOffenseCode(String theOffenseCode)
	{
		offenseCode = theOffenseCode;
	}

}
