// no longer in use. Migrated to SM. Refer US #87188. references in the mapping file commented.
package mojo.km.security;

import mojo.km.persistence.PersistentObject;

/**
* @roseuid 422C77F40098
*/
public class UserGroup extends PersistentObject
{
    //87191
	/*private String groupType;
	private String name;
	private String statusId;
	private String category;

	*//**
	* Properties for roles
	* @associationType simple
	* @referencedType mojo.km.security.Role
	*//*
	private java.util.Collection roles = null;
	private String description;
	*//**
	* Properties for users
	* @associationType simple
	* @referencedType mojo.km.security.User
	*//*
	private java.util.Collection users = null;
	*//**
	* Properties for constraints
	* @associationType simple
	* @referencedType mojo.km.security.Constraint
	*//*
	private java.util.Collection constraints = null;
	*//**
	* @roseuid 422C77F40098
	*//*
	public UserGroup()
	{
	}
	*//**
	* Access method for the category property.
	* @return the current value of the category property
	*//*
	public java.lang.String getCategory()
	{
		fetch();
		return category;
	}

	*//**
	* Access method for the groupType property.
	* @return the current value of the groupType property
	*//*
	public java.lang.String getGroupType()
	{
		fetch();
		return groupType;
	}

	*//**
	* Sets the value of the category property.
	* @param aCategory the new value of the category property
	*//*
	public void setCategory(java.lang.String aCategory)
	{
		if (this.category == null || !this.category.equals(aCategory))
		{
			markModified();
		}
		category = aCategory;
	}
	
	*//**
	* Sets the value of the groupType property.
	* @param aGroupType the new value of the groupType property
	*//*
	public void setGroupType(java.lang.String aGroupType)
	{
		if (this.groupType == null || !this.groupType.equals(aGroupType))
		{
			markModified();
		}
		groupType = aGroupType;
	}
	*//**
	* Access method for the statusId property.
	* @return the current value of the statusId property
	*//*
	public java.lang.String getStatusId()
	{
		fetch();
		return statusId;
	}
	*//**
	* Sets the value of the statusId property.
	* @param aStatusId the new value of the statusId property
	*//*
	public void setStatusId(java.lang.String aStatusId)
	{
		if (this.statusId == null || !this.statusId.equals(aStatusId))
		{
			markModified();
		}
		statusId = aStatusId;
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
	* Sets the value of the name property.
	* @param aName the new value of the name property
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
	* Access method for the description property.
	* @return the current value of the description property
	*//*
	public java.lang.String getDescription()
	{
		fetch();
		return description;
	}
	*//**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
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
	* Initialize class relationship implementation for mojo.km.security.Role
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
				roles =
					new mojo.km.persistence.ArrayList(
						mojo.km.security.UserGroupRolesRole.class,
						"parentId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				roles = new java.util.ArrayList();
			}
		}
	}

	*//**
	* returns a collection of mojo.km.security.Role
	*//*
	public java.util.Collection getRoles()
	{
		initRoles();
		java.util.ArrayList retVal = new java.util.ArrayList();
		java.util.Iterator i = roles.iterator();
		while (i.hasNext())
		{
			mojo.km.security.UserGroupRolesRole actual = (mojo.km.security.UserGroupRolesRole) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	*//**
	* insert a mojo.km.security.Role into class relationship collection.
	*//*
	public void insertRoles(mojo.km.security.Role anObject)
	{
		initRoles();
		mojo.km.security.UserGroupRolesRole actual = new mojo.km.security.UserGroupRolesRole();
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
	*//**
	* Removes a mojo.km.security.Role from class relationship collection.
	*//*
	public void removeRoles(mojo.km.security.Role anObject)
	{
		initRoles();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			mojo.km.security.UserGroupRolesRole actual =
				(mojo.km.security.UserGroupRolesRole) new mojo
					.km
					.persistence
					.Reference(assocEvent, mojo.km.security.UserGroupRolesRole.class)
					.getObject();
			roles.remove(actual);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
	*//**
	* Clears all mojo.km.security.Role from class relationship collection.
	*//*
	public void clearRoles()
	{
		initRoles();
		roles.clear();
	}
	*//**
	* Initialize class relationship implementation for mojo.km.security.User
	*//*
	private void initUsers()
	{
		if (users == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				users =
					new mojo.km.persistence.ArrayList(
						mojo.km.security.UserGroupUsersUser.class,
						"parentId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				users = new java.util.ArrayList();
			}
		}
	}
	*//**
	* returns a collection of mojo.km.security.User
	*//*
	public java.util.Collection getUsers()
	{
		initUsers();
		java.util.ArrayList retVal = new java.util.ArrayList();
		java.util.Iterator i = users.iterator();
		while (i.hasNext())
		{
			mojo.km.security.UserGroupUsersUser actual = (mojo.km.security.UserGroupUsersUser) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	*//**
	* insert a mojo.km.security.User into class relationship collection.
	*//*
	public void insertUsers(mojo.km.security.User anObject)
	{
		initUsers();
		mojo.km.security.UserGroupUsersUser actual = new mojo.km.security.UserGroupUsersUser();
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
		users.add(actual);
	}
	*//**
	* Removes a mojo.km.security.User from class relationship collection.
	*//*
	public void removeUsers(mojo.km.security.User anObject)
	{
		initUsers();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			mojo.km.security.UserGroupUsersUser actual =
				(mojo.km.security.UserGroupUsersUser) new mojo
					.km
					.persistence
					.Reference(assocEvent, mojo.km.security.UserGroupUsersUser.class)
					.getObject();
			users.remove(actual);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
	*//**
	* Clears all mojo.km.security.User from class relationship collection.
	*//*
	public void clearUsers()
	{
		initUsers();
		users.clear();
	}
	*//**
	* returns a collection of mojo.km.security.Constraint
	*//*
	public java.util.Collection getConstraints()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
		if (constraints == null)
		{
			constraints = Constraint.findByConstrainsId(this.getOID().toString(), "USERGROUP");
		}
		java.util.Iterator i = constraints.iterator();
		while (i.hasNext())
		{
			Constraint actual = (Constraint) i.next();
			retVal.add(actual);
		}
		return retVal;
	}

	public void addConstraint(Object constrainerObjectOID, Class constrainerObjectType)
	{
		String constrainerType =
			mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
		if (constrainerType == null)
		{
			throw new RuntimeException(
				"No ConstraintMapping exists for the class of type "
					+ constrainerObjectType
					+ ". This needs to exist in the Security section of your mojo configuration");
		}
		Constraint newConstraint = new Constraint();
		newConstraint.setConstrainerId(constrainerObjectOID.toString());
		newConstraint.setConstrainerType(constrainerType);
		newConstraint.setConstrainsId(this.getOID().toString());
		newConstraint.setConstrainsType("USERGROUP");
	}

	public void removeConstraint(Object constrainerObjectOID, Class constrainerObjectType)
	{
		String constrainerType =
			mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
		if (constrainerType == null)
		{
			throw new RuntimeException(
				"No ConstraintMapping exists for the class of type "
					+ constrainerObjectType
					+ ". This needs to exist in the Security section of your mojo configuration");
		}
		Constraint constraint =
			Constraint.findByConstrainsIdAndConstrainerId(
				this.getOID().toString(),
				"USERGROUP",
				constrainerObjectOID.toString(),
				constrainerType);
		if (constraint != null)
		{
			constraint.delete();
		}
	}

	*//**
	 * @param event
	 * @return
	 *//*
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, UserGroup.class);
		return iter;
	}

	*//**
	* @return mojo.km.security.UserGroup
	* @param userGroupId
	* @roseuid 4236ED9502A8
	*//*
	static public UserGroup find(String userGroupId)
	{
		UserGroup userGroup = (UserGroup) new Home().find(userGroupId, UserGroup.class);
		return userGroup;
	}
	public static MetaDataResponseEvent findMeta(IEvent iEvent ) {
		  IHome home = new Home();
		  MetaDataResponseEvent iter = home.findMeta(iEvent, UserGroup.class);
		  return iter;
		 }*/
}