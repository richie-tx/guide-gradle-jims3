/*
 * Created on Feb 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.persistence;

import mojo.km.messaging.RequestEvent;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetAssociationEvent extends RequestEvent
{
	private String parentId;
	private String childId;

	public GetAssociationEvent() 
	{
	}

	/**
	 * @return
	 */
	public String getChildId()
	{
		return childId;
	}

	/**
	 * @return
	 */
	public String getParentId()
	{
		return parentId;
	}

	/**
	 * @param string
	 */
	public void setChildId(String string)
	{
		childId = string;
	}

	/**
	 * @param string
	 */
	public void setParentId(String string)
	{
		parentId = string;
	}

}
