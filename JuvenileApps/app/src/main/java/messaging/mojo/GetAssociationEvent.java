/*
 * Created on Feb 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.mojo;

import mojo.km.messaging.RequestEvent;


/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetAssociationEvent extends RequestEvent
{
	private String parentId;
	private String childId;
	
	public String getParentId()
	{
		return this.parentId;
	}
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	public String getChildId()
	{
		return this.childId;
	}
	public void setChildId(String childId) 
	{
		this.childId = childId;
	}
	
}
