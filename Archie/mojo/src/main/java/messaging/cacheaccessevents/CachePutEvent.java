/*
 * Created on Oct 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.cacheaccessevents;

import mojo.km.messaging.ResponseEvent;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CachePutEvent extends ResponseEvent
{
	private String Id = null;
	private Object cachedObject = null;

	/**
	 * 
	 */
	public CachePutEvent()
	{
		//super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public Object getCachedObject()
	{
		return cachedObject;
	}

	/**
	 * @return
	 */
	public String getId()
	{
		return Id;
	}

	/**
	 * @param object
	 */
	public void setCachedObject(Object object)
	{
		cachedObject = object;
	}

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		Id = string;
	}

}
