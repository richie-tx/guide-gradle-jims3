package mojo.km.security.role.acf2;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*package mojo.km.security.role.acf2;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.persistence.PersistentObject;
//import pd.appshell.Feature;

public class Permission extends PersistentObject
{
	private String description;
	*//**
	 * Properties for features
	 * @associationType simple
	 * @referencedType pd.appshell.Feature
	 *//*
	private java.util.Collection features;
	private String name;
	*//**
	 * Properties for parent
	 *//*
	private Permission parent = null;
	private String parentId;
	private String roleId;
	private String permissionId;

	*//**
	 * @param permissionId
	 *//*
	public static Permission find(String permissionId)
	{
		Permission permission = null;
		try
		{
			IHome home = new Home();
			permission =
				(Permission) home.find(permissionId, Permission.class);
		}
		catch (ObjectNotFoundException e)
		{
		}
		return permission;
	}
	
	*//**
	 *//*
	static public Iterator findAll()
	{
		Iterator permissions = null;
		return permissions;
	}

	*//**
	 *//*
	public Permission()
	{
	}

	*//**
	 * Clears all Features from class relationship collection.
	 *//*
	public void clearFeatures()
	{
		initFeatures();
		//features.clear();
	}

	*//**
	 * Access method for the description property.
	 * @return the current value of the description property
	 *//*
	public java.lang.String getDescription()
	{
		fetch();
		return description;
	}
	
	*//**
	 * returns a FeatureList
	 *//*
	public java.util.Collection getFeatures()
	{
//		initFeatures();
//		java.util.Iterator i = features.iterator();
//		while (i.hasNext())
//		{
//			PermissionFeatures actual = (PermissionFeatures) i.next();
//			retVal.add(actual.getChild());
//		}
		return features;
	}

	*//**
	 * Access method for the name property.
	 * @return the current value of the name property
	 *//*
	public java.lang.String getName()
	{
		fetch();
		return name;
	}
	
	*//**
	* Gets referenced type Permission
	*//*
	public Permission getParent()
	{
		fetch();
		initParent();
		return parent;
	}
	
	*//**
	* Get the reference value to class :: Permission
	*//*
	public String getParentId()
	{
		fetch();
		return parentId;
	}
	
	*//**
	* Get the reference value to class :: Role
	*//*
	public String getRoleId()
	{
		fetch();
		return roleId;
	}
	
	*//**
	* Access method for the systemActivityId property.
	* @return the current value of the systemActivityId property
	*//*
	public java.lang.String getPermissionId()
	{
		return "" + getOID();
	}
	
	*//**
	* Initialize class relationship implementation for Permission
	*//*
	private void initPermissions()
	{
//		if (permissions == null)
//		{
//			if (this.getOID() == null)
//			{
//				new mojo.km.persistence.Home().bind(this);
//			}
//			try
//			{
//				permissions =
//					new mojo.km.persistence.ArrayList(
//						PermissionPermissionsPermission.class,
//						"parentId", "" + getOID());
//			}
//			catch (Throwable t)
//			{
//				permissions = new java.util.ArrayList();
//			}
//		}
	}
	
	*//**
	* Initialize class relationship implementation for Permission
	*//*
	private void initFeatures()
	{
//		if (features == null)
//		{
//			if (this.getOID() == null)
//			{
//				new mojo.km.persistence.Home().bind(this);
//			}
//			try
//			{
//				features =
//					new mojo.km.persistence.ArrayList(PermissionFeaturesPermission.class,
//						"parentId", "" + getOID());
//			}
//			catch (Throwable t)
//			{
//				features = new java.util.ArrayList();
//			}
//		}
	}
	
	*//**
	* Initialize class relationship to class Permission
	*//*
	private void initParent()
	{
		if (parent == null)
		{
			try
			{
				parent = (Permission) new mojo.km.persistence.Reference(
							parentId, Permission.class).getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	
	*//**
	* insert a Permission into class relationship collection.
	*//*
	public void insertPermission(Permission anObject)
	{
//		initPermissions();
//		PermissionActivitiesPermission actual =
//			new PermissionActivitiesPermission();
//		if (this.getOID() == null)
//		{
//			new Home().bind(this);
//		}
//		if (anObject.getOID() == null)
//		{
//			new Home().bind(anObject);
//		}
//		actual.setOID("" + this.getOID() + "-" + anObject.getOID());
//		actual.setChild(anObject);
//		actual.setParent(this);
//		permissions.add(actual);
	}
	
	*//**
	* insert a Permission into class relationship collection.
	*//*
//	public void insertFeature(Feature anObject)
//	{
//		// This anObject Permission is a feature
//		anObject.setTreeFeature(true);
//		
//		initFeatures();
//		PermissionFeatures actual = new PermissionFeatures();
//		if (this.getOID() == null)
//		{
//			new Home().bind(this);
//		}
//		if (anObject.getOID() == null)
//		{
//			new Home().bind(anObject);
//		}
//		actual.setOID("" + this.getOID() + "-" + anObject.getOID());
//		actual.setChild(anObject);
//		actual.setParent(this);
//		features.add(actual);
//	}
	
	*//**
	* Removes a Permission from class relationship collection.
	*//*
	public void removePermission(Permission anObject)
	{
//		initActivities();
//		try
//		{
//			PermissionActivitiesPermission actual =
//				(PermissionActivitiesPermission) new mojo
//					.km
//					.persistence
//					.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						PermissionActivitiesPermission.class)
//					.getObject();
//			activities.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
	}
	
	*//**
	* Removes a Feature from class relationship collection.
	*//*
//	public void removeFeature(Feature anObject)
//	{
//		initFeatures();
//		try
//		{
//			PermissionFeatures actual =
//				(PermissionFeatures) new mojo.km.persistence.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						PermissionFeatures.class).getObject();
//			features.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
//	}
	*//**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*//*
	public void setDescription(java.lang.String aDescription)
	{
		if (description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}
	*//**
	* Sets the value of the name property.
	* @param aName the new value of the name property
	*//*
	public void setName(java.lang.String aName)
	{
		if (name == null || !this.name.equals(aName))
		{
			markModified();
		}
		name = aName;
	}
	*//**
	* set the type reference for class member parentActivity
	*//*
	public void setParent(Permission parent)
	{
		if (this.parent == null || !this.parent.equals(parent))
		{
			markModified();
		}
		if (parent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent =
			(Permission) new mojo.km.persistence.Reference((PersistentObject) parent)
				.getObject();
	}
	*//**
	* Set the reference value to class :: Permission
	*//*
	public void setParentId(String parentId)
	{
		if (this.parentId == null
			|| !this.parentId.equals(parentId))
		{
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	*//**
	* Set the reference value to class :: Role
	*//*
	public void setRoleId(String roleId)
	{
		if (this.roleId == null || !this.roleId.equals(roleId))
		{
			markModified();
		}
		this.roleId = roleId;
	}
	*//**
	* Sets the value of the systemActivityId property.
	* @param aPermissionId the new value of the systemActivityId property
	*//*
	public void setPermissionId(String aPermissionId)
	{
		setOID(aPermissionId);
	}
}
*/