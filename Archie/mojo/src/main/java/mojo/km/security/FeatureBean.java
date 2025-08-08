/*
 * Created on Jul 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FeatureBean implements java.io.Serializable
{
	private String oid;
	private String featureCategory;
	private String name;
	private String featureType;
	private String parentId;
	private String description;
	
	public boolean equals(Object obj)
	{
	    boolean result = true;
	    
	    if(obj instanceof FeatureBean == false)
	    {
	        return false;
	    }	    	   
	    
	    FeatureBean feature = (FeatureBean) obj;
	    
	    if(this.oid.equals(feature.getOID()) == false)
	    {
	        result = false;
	    }
	    else if(this.featureCategory.equals(feature.getFeatureCategory()) == false)
	    {
	        result = false;
	    }
	    else if(this.name.equals(feature.getName()) == false)
	    {
	        result = false;
	    }
	    else if(this.featureType.equals(feature.getFeatureType()) == false)
	    {
	        result = false;
	    }
	    else if(this.parentId.equals(feature.getParentId()) == false)
	    {
	        result = false;
	    }
	    else if(this.description.equals(feature.getDescription()) == false)
	    {
	        result = false;
	    }
	    
	    return result;
	}
	
	/**
	 * 
	 * @return oid
	 */
	public String getOID() 
	{
		return oid;
	}
	
	/**
	 * 
	 * @param aOid
	 */
	public void setOID(String aOid) 
	{
		oid = aOid;
	}
	
	/**
	 * 
	 * @return description
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * 
	 * @param aDescription
	 */
	public void setDescription(String aDescription) 
	{
		description = aDescription;	
	}
	/** 
	 * @return featureCategory
	 */
	public String getFeatureCategory()
	{
		return featureCategory;
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
	 * @param string
	 */
	public void setFeatureCategory(String aFeatureCategory)
	{
		featureCategory = aFeatureCategory;
	}

	/**
	 * @param string
	 */
	public void setFeatureType(String aFeatureType)
	{
		featureType = aFeatureType;
	}

	/**
	 * @param string
	 */
	public void setName(String aName)
	{
		name = aName;
	}

	/**
	 * @param string
	 */
	public void setParentId(String aParentId)
	{
		parentId = aParentId;
	}

}
