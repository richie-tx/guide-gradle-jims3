/*
 * Created on Apr 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoleFeaturesResponseEvent extends ResponseEvent
{
	private String name;
	private String featureType;
	private String parentId;
	private String description;
	private java.util.Collection childFeatures;


	/**
	 * @return
	 */
	public java.util.Collection getChildFeatures()
	{
		return childFeatures;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return
	 */
	public String getFeatureType()
	{
		return featureType;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getParentId()
	{
		return parentId;
	}

	/**
	 * @param collection
	 */
	public void setChildFeatures(java.util.Collection collection)
	{
		childFeatures = collection;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @param string
	 */
	public void setFeatureType(String string)
	{
		featureType = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setParentId(String string)
	{
		parentId = string;
	}

}
