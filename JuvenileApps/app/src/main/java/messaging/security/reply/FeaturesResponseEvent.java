/*
 * Created on Apr 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import java.util.SortedMap;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FeaturesResponseEvent extends ResponseEvent implements Comparable
{
	private String featureName;
	private String featureType;
	private String parentId;
	private String description;
	private String featureCategory;
	private String featureCategoryId;
	private String featureId;
	
	private java.util.Collection childFeatures;
	private SortedMap childFeatureMap;


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
	public void setParentId(String string)
	{
		parentId = string;
	}

	/**
	 * @return
	 */
	public String getFeatureCategory()
	{
		return this.featureCategory;
	}

	/**
	 * @param string
	 */
	public void setFeatureCategory(String string)
	{
		this.featureCategory = string;
	}

	/**
	 * @return
	 */
	public String getFeatureId()
	{
		return featureId;
	}

	/**
	 * @param string
	 */
	public void setFeatureId(String string)
	{
		featureId = string;
	}

	/**
	 * @return
	 */
	public String getFeatureName()
	{
		return featureName;
	}

	/**
	 * @param string
	 */
	public void setFeatureName(String string)
	{
		featureName = string;
	}

	/**
	 * @return
	 */
	public SortedMap getChildFeatureMap()
	{
		return childFeatureMap;
	}

	/**
	 * @param map
	 */
	public void setChildFeatureMap(SortedMap map)
	{
		childFeatureMap = map;
	}
	
	/**
	 * @return Returns the featureCategoryId.
	 */
	public String getFeatureCategoryId() {
		return featureCategoryId;
	}
	/**
	 * @param featureCategoryId The featureCategoryId to set.
	 */
	public void setFeatureCategoryId(String featureCategoryId) {
		this.featureCategoryId = featureCategoryId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		FeaturesResponseEvent c = (FeaturesResponseEvent)o;
		if (c.getFeatureName() == null){
			return -1;
		}		
		if (this.getFeatureName() == null){
			return 1;
		}
		return this.getFeatureName().compareToIgnoreCase(c.getFeatureName());
	}
}
