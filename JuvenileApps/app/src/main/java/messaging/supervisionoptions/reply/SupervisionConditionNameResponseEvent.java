/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class SupervisionConditionNameResponseEvent extends ResponseEvent
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
	 * 
	 */
	public void setName( String aName )
	{
		name = aName;
	}

}
