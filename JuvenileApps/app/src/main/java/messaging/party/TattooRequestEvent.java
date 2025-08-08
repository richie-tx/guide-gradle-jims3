/*
 * Created on Oct 13, 2004
 */
package messaging.party;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class TattooRequestEvent extends RequestEvent
{
	private String tattoo;
	/**
	 * @return
	 */
	public String getTattoo()
	{
		return tattoo;
	}

	/**
	 * @param string
	 */
	public void setTattoo(String string)
	{
		tattoo = string;
	}

}
