/*
 * Created on Dec 2, 2004
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class TattooRequestEvent  extends RequestEvent
{
	public String category;
	public String code;
	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}
	/**
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}
}
