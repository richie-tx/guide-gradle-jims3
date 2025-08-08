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
public class CacheGetEvent extends ResponseEvent
{

	private String Id = null;
	private String typeName = null;
	/**
	 * 
	 */
	public CacheGetEvent()
	{
		//super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public String getId()
	{
		return Id;
	}

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		Id = string;
	}

	/**
	 * @return
	 */
	public String getTypeName()
	{
		return typeName;
	}
	

	/**
	 * @param string
	 */
	public void setTypeName(String string)
	{
		typeName = string;
	}

}
