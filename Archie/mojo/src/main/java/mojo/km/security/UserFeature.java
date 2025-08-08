// no longer in use. Migrated to SM. Refer US #87188. references in the mapping file commented.
package mojo.km.security;

import java.util.Iterator;
import java.util.List;

import mojo.km.persistence.Home;
import mojo.km.utilities.CollectionUtil;
import mojo.messaging.securitytransactionsevents.LoginEvent;

public class UserFeature extends Feature
{
   /* private String featureId;

    private String roleId;

    public static List findAll(LoginEvent anEvent)
    {
        Iterator i = new Home().findAll(anEvent, UserFeature.class);
        List userFeatures = CollectionUtil.iteratorToList(i);
        return userFeatures;
    }

    *//**
     * @return Returns the roleId.
     *//*
    public String getRoleId()
    {
        return roleId;
    }

    *//**
     * @param roleId
     *            The roleId to set.
     *//*
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    *//**
     * @return Returns the featureId.
     *//*
    public String getFeatureId()
    {
        return featureId;
    }

    *//**
     * @param featureId
     *            The featureId to set.
     *//*
    public void setFeatureId(String featureId)
    {
        this.featureId = featureId;
    }
    
    public FeatureBean valueObject()
	{
	    FeatureBean fb = new FeatureBean();
        fb.setName(this.name);
        fb.setFeatureCategory(this.featureCategory);
        fb.setFeatureType(this.featureType);
        fb.setParentId(this.parentId);
        fb.setOID(this.featureId);
        fb.setDescription(this.description);
        return fb;
	}*/
}
