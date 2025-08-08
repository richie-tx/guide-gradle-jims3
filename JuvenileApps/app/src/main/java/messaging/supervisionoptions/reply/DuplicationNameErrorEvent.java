/*
 * Created on Sep 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionoptions.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DuplicationNameErrorEvent extends ResponseEvent
{
	private String name;

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

}
