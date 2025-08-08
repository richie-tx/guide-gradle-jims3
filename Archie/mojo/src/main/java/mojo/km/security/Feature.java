// no longer in use. Migrated to SM. Refer US #87188. references in the mapping file commented.
package mojo.km.security;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.*;

import java.util.Iterator;
import java.util.Collection;

/**
 * @roseuid 422C77F300BE
 */
public class Feature extends PersistentObject
{
//89191
   /* *//**
     * @return mojo.km.security.Feature
     * @param featureId
     * @roseuid 4236ED94015C
     *//*
    static public Feature find(String featureId)
    {
        return (Feature) new Home().find(featureId, Feature.class);
    }

    *//**
     * Implement hashCode locally until it is put in place in PersistentObject
     *//*
    
     * public int hashCode() { return ("" + getOID() + getClass().getName()).hashCode(); }
     
    static public Iterator findAll()
    {
        IHome home = new Home();
        Iterator iter = home.findAll(Feature.class);
        return iter;
    }

    *//**
     * @return java.util.Iterator
     *//*
    static public Iterator findAll(IEvent event)
    {
        return new Home().findAll(event, Feature.class);
    }

    *//**
     * @return java.util.Iterator
     * @param attrName
     * @param attrValue
     * @roseuid 4236ED9401A2
     *//*
    static public Iterator findAll(String attrName, String attrValue)
    {
        return null;
    }

    public static MetaDataResponseEvent findMeta(IEvent iEvent)
    {
        IHome home = new Home();
        MetaDataResponseEvent iter = home.findMeta(iEvent, Feature.class);
        return iter;
    }

    *//**
     * Properties for roles
     * 
     * @associationType simple
     * @referencedType mojo.km.security.Role
     *//*
    private java.util.Collection childFeatures = null;

    protected String description;

    protected String featureCategory;

    protected String featureType;

    protected String name;

    protected String parentId;

    *//**
     * Properties for roles
     * 
     * @associationType simple
     * @referencedType mojo.km.security.Role
     *//*
    private java.util.Collection roles = null;

    *//**
     * @roseuid 422C77F300BE
     *//*
    public Feature()
    {
    }

    public void clearChildFeatures()
    {
        initChildFeatures();
        childFeatures.clear();
    }

    *//**
     * Clears all mojo.km.security.Role from class relationship collection.
     * 
     * @roseuid 4231D33502EC
     *//*
    public void clearRoles()
    {
        initRoles();
        roles.clear();
    }

    public java.util.Collection getChildFeatures()
    {
        initChildFeatures();
        java.util.ArrayList retVal = new java.util.ArrayList();
        java.util.Iterator i = childFeatures.iterator();
        while (i.hasNext())
        {
            mojo.km.security.Feature actual = (mojo.km.security.Feature) i.next();
            retVal.add(actual);
        }
        return retVal;
    }

    *//**
     * Access method for the description property.
     * 
     * @return the current value of the description property
     *//*
    public java.lang.String getDescription()
    {
        fetch();
        return description;
    }

    *//**
     * Access method for the featureCategory property.
     * 
     * @return the current value of the featureCategory property
     *//*
    public java.lang.String getFeatureCategory()
    {
        fetch();
        return featureCategory;
    }

    *//**
     * Access method for the featureType property.
     * 
     * @return the current value of the featureType property
     *//*
    public java.lang.String getFeatureType()
    {
        fetch();
        return featureType;
    }

    *//**
     * Access method for the name property.
     * 
     * @return the current value of the name property
     *//*
    public java.lang.String getName()
    {
        fetch();
        return name;
    }

    *//**
     * Access method for the parentId property.
     * 
     * @return the current value of the parentId property
     *//*
    public java.lang.String getParentId()
    {
        fetch();
        return parentId;
    }

    *//**
     * returns a collection of mojo.km.security.Role
     * 
     * @return java.util.Collection
     * @roseuid 4236ED950077
     *//*
    public java.util.Collection getRoles()
    {
        initRoles();
        java.util.ArrayList retVal = new java.util.ArrayList();
        java.util.Iterator i = roles.iterator();
        while (i.hasNext())
        {
            mojo.km.security.FeatureRolesRole actual = (mojo.km.security.FeatureRolesRole) i.next();
            retVal.add(actual.getChild());
        }
        return retVal;
    }

    private void initChildFeatures()
    {
        if (childFeatures == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }
            try
            {
                childFeatures = new mojo.km.persistence.ArrayList(mojo.km.security.Feature.class, "parentId", "" + getOID());
            }
            catch (Throwable t)
            {
                childFeatures = new java.util.ArrayList();
            }
        }
    }

    *//**
     * Initialize class relationship implementation for mojo.km.security.Role
     * 
     * @roseuid 4231D33501F1
     *//*
    private void initRoles()
    {
        if (roles == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }
            try
            {
                roles = new mojo.km.persistence.ArrayList(mojo.km.security.FeatureRolesRole.class, "parentId", "" + getOID());
            }
            catch (Throwable t)
            {
                roles = new java.util.ArrayList();
            }
        }
    }

    *//**
     * insert a mojo.km.security.Role into class relationship collection.
     * 
     * @param anObject
     * @roseuid 4236ED95009F
     *//*
    public void insertRoles(mojo.km.security.Role anObject)
    {
        initRoles();
        mojo.km.security.FeatureRolesRole actual = new mojo.km.security.FeatureRolesRole();
        if (this.getOID() == null)
        {
            new Home().bind(this);
        }
        if (anObject.getOID() == null)
        {
            new Home().bind(anObject);
        }
        actual.setChild(anObject);
        actual.setParent(this);
        roles.add(actual);
    }

    public boolean isLeafFeature()
    {
        Collection childFeatures = this.getChildFeatures();
        return (childFeatures == null || childFeatures.size() == 0);
    }

    *//**
     * Removes a mojo.km.security.Role from class relationship collection.
     * 
     * @param anObject
     * @roseuid 4236ED950104
     *//*
    public void removeRoles(mojo.km.security.Role anObject)
    {
        initRoles();
        try
        {
            mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
            assocEvent.setChildId((String) anObject.getOID());
            assocEvent.setParentId((String) this.getOID());
            mojo.km.security.FeatureRolesRole actual = (mojo.km.security.FeatureRolesRole) new mojo.km.persistence.Reference(
                    assocEvent, mojo.km.security.FeatureRolesRole.class).getObject();
            roles.remove(actual);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    *//**
     * Sets the value of the description property.
     * 
     * @param aDescription
     *            the new value of the description property
     *//*
    public void setDescription(java.lang.String aDescription)
    {
        if (this.description == null || !this.description.equals(aDescription))
        {
            markModified();
        }
        description = aDescription;
    }

    *//**
     * Sets the value of the featureCategory property.
     * 
     * @param aFeatureCategory
     *            the new value of the featureCategory property
     *//*
    public void setFeatureCategory(java.lang.String aFeatureCategory)
    {
        if (this.featureCategory == null || !this.featureCategory.equals(aFeatureCategory))
        {
            markModified();
        }
        featureCategory = aFeatureCategory;
    }

    *//**
     * Sets the value of the featureType property.
     * 
     * @param aFeatureType
     *            the new value of the featureType property
     *//*
    public void setFeatureType(java.lang.String aFeatureType)
    {
        if (this.featureType == null || !this.featureType.equals(aFeatureType))
        {
            markModified();
        }
        featureType = aFeatureType;
    }

    *//**
     * Sets the value of the name property.
     * 
     * @param aName
     *            the new value of the name property
     *//*
    public void setName(java.lang.String aName)
    {
        if (this.name == null || !this.name.equals(aName))
        {
            markModified();
        }
        name = aName;
    }

    *//**
     * Sets the value of the parentId property.
     * 
     * @param aParentId
     *            the new value of the parentId property
     *//*
    public void setParentId(java.lang.String aParentId)
    {
        if (this.parentId == null || !this.parentId.equals(aParentId))
        {
            markModified();
        }
        parentId = aParentId;
    }

    public String toString()
    {
        return this.getName();// + ", is leaf = " + this.isLeafFeature();
    }

    public FeatureBean valueObject()
    {
        FeatureBean fb = new FeatureBean();
        fb.setName(this.name);
        fb.setFeatureCategory(this.featureCategory);
        fb.setFeatureType(this.featureType);
        fb.setParentId(this.parentId);
        fb.setOID((String) this.getOID());
        fb.setDescription(this.description);
        return fb;
    }
*/
}
