/*
 * Created on Oct 13, 2004
 */
package messaging.party;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class ScarMarkRequestEvent extends RequestEvent
{
	private String scarMark;
	/**
	 * @return
	 */
	public String getScarMark()
	{
		return scarMark;
	}

	/**
	 * @param string
	 */
	public void setScarMark(String string)
	{
		scarMark = string;
	}

}
