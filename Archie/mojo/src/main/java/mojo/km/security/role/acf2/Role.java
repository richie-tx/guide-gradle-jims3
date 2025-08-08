package mojo.km.security.role.acf2;//no longer in use. Migrated to SM. Refer US #87188. references in the mapping file.

/*package mojo.km.security.role.acf2;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.role.IUser;

*//**
 * @author jmcnabb
 *
 *//*
public class Role extends PersistentObject
{
	*//**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4111351C01A3
	*//*
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, Role.class);
	}
	*//**
	* @return Role
	* @param roleId
	* @roseuid 4111351C0198
	*//*
	static public Role findRole(String roleId)
	{
		Role role = null;
		try
		{
			IHome home = new Home();
			role = (Role) home.find(roleId, Role.class);
		}
		catch (ObjectNotFoundException e)
		{
		}
		return role;
	}
	*//**
	* Property for activities
	* @associationType simple
	* @referencedType SystemActivity
	*//*
	private java.util.Collection activities;
	*//**
	* Properties for agency
	*//*
	private String agencyId;
	private Timestamp creationDate;
	*//**
	* Properties for creator
	*//*
	private String creatorId;
	private String description;
	*//**
	* Properties for division
	*//*
	private String divisionId;
	
	*//**
	* Properties for parent
	* @associationType simple
	* @referencedType Role
	*//*
	private java.util.Collection parents = null;
	private String roleId;
	private String roleName;
	*//**
	* Property for roles
	* @associationType simple
	* @referencedType Role
	*//*
	private Collection roles;
	private String type;
	*//**
	* Property for activities
	* @associationType simple
	* @referencedType User
	*//*
	private java.util.Collection users;
	*//**
	* @roseuid 411137DE005D
	*//*
	public Role()
	{
	}
	*//**
	* Clears all SystemActivity from class relationship
	* collection.
	* @roseuid 4111393F0227
	*//*
	public void clearActivities()
	{
		initActivities();
		activities.clear();
	}
	*//**
	* Clears all Role from class relationship collection.
	*//*
	public void clearParents()
	{
		initParents();
		parents.clear();
	}
	*//**
	* Clears all Role from class relationship collection.
	* @roseuid 4123D6F3010F
	*//*
	public void clearRoles()
	{
		initRoles();
		roles.clear();
	}
	*//**
	* Clears all User from class relationship collection.
	* @roseuid 4111393F023B
	*//*
	public void clearUsers()
	{
		initUsers();
		users.clear();
	}
	*//**
	* @param roleId
	* @roseuid 412A29BA0232
	*//*
	public void find(String roleId)
	{
		fetch();
	}
	*//**
	* returns a collection of SystemActivity
	* @return java.util.Collection
	* @roseuid 4111393F0281
	*//*
	public Collection getPermissions()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
//		initActivities();
//		java.util.Iterator i = activities.iterator();
//		while (i.hasNext())
//		{
//			RoleActivitiesSystemActivity actual =
//				(RoleActivitiesSystemActivity) i
//					.next();
//			retVal.add(actual.getChild());
//		}
		return retVal;
	}

	*//**
	* Get the reference value to class :: pd.contact.Agency
	*//*
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	*//**
	* Access method for the creationDate property.
	* @return the current value of the creationDate property
	*//*
	public Date getCreationDate()
	{
		fetch();
		return creationDate;
	}

	*//**
	* Access method for the creatorId property.
	* @return the current value of the creatorId property
	*//*
	public java.lang.String getCreatorId()
	{
		fetch();
		return creatorId;
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
	* Get the reference value to class :: pd.contact.Division
	* @return java.lang.String
	* @roseuid 4111393F02F9
	*//*
	public String getDivisionId()
	{
		fetch();
		return divisionId;
	}
	
   *//**
	* returns a collection of Role
	*//*
	public java.util.Collection getParents()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
		initParents();
//		java.util.Iterator i = parents.iterator();
//		while (i.hasNext())
//		{
//			RoleParentsRole actual =
//				(RoleParentsRole) i.next();
//			retVal.add(actual.getChild());
//		}
		return retVal;
	}
	
	*//**
	* Access method for the roleId property.
	* @return the current value of the roleId property
	*//*
	public java.lang.String getRoleId()
	{
		return "" + getOID();
	}
	*//**
	* Access method for the roleName property.
	* @return the current value of the roleName property
	*//*
	public java.lang.String getRoleName()
	{
		fetch();
		return roleName;
	}

	*//**
	* returns a collection of Role
	* @return java.util.Collection
	* @roseuid 4126779D0163
	*//*
	public java.util.Collection getRoles()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
		initRoles();
//		java.util.Iterator i = roles.iterator();
//		while (i.hasNext())
//		{
//			RoleRolesRole actual = (RoleRolesRole) i.next();
//			retVal.add(actual.getChild());
//		}
		return retVal;
	}

	*//**
	* Access method for the type property.
	* @return the current value of the type property
	*//*
	public java.lang.String getType()
	{
		fetch();
		return type;
	}
	*//**
	* returns a collection of User
	* @return java.util.Collection
	* @roseuid 4111393F0368
	*//*
	public java.util.Collection getUsers()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
		initUsers();
//		java.util.Iterator i = users.iterator();
//		while (i.hasNext())
//		{
//			RoleUsersUser actual = (RoleUsersUser) i.next();
//			retVal.add(actual.getChild());
//		}
		return retVal;
	}

	*//**
	* Initialize class relationship implementation for
	* SystemActivity
	* @roseuid 4111393F0386
	*//*
	private void initActivities()
	{
//		if (activities == null)
//		{
//			if (this.getOID() == null)
//			{
//				new mojo.km.persistence.Home().bind(this);
//			}
//			try
//			{
//				activities =
//					new mojo.km.persistence.ArrayList(RoleActivitiesPermission.class,
//						"parentId",
//						"" + getOID());
//			}
//			catch (Throwable t)
//			{
//				activities = new java.util.ArrayList();
//			}
//		}
	}

	*//**
	* Initialize class relationship implementation for Role
	*//*
	protected void initParents()
	{
//		if (parents == null)
//		{
//			if (this.getOID() == null)
//			{
//				new mojo.km.persistence.Home().bind(this);
//			}
//			try
//			{
//				parents =
//					new mojo.km.persistence.ArrayList(
//						RoleParentsRole.class,
//						"parentId",
//						"" + getOID());
//			}
//			catch (Throwable t)
//			{
//				parents = new java.util.ArrayList();
//			}
//		}
	}
	*//**
	* Initialize class relationship implementation for Role
	* @roseuid 4123D6F30001
	*//*
	private void initRoles()
	{
//		if (roles == null)
//		{
//			if (this.getOID() == null)
//			{
//				new mojo.km.persistence.Home().bind(this);
//			}
//			try
//			{
//				roles =
//					new mojo.km.persistence.ArrayList(
//						RoleRolesRole.class,
//						"parentId",
//						"" + getOID());
//			}
//			catch (Throwable t)
//			{
//				roles = new java.util.ArrayList();
//			}
//		}
	}

	*//**
	* Initialize class relationship implementation for User
	* @roseuid 4111393F03D6
	*//*
	private void initUsers()
	{
//		if (users == null)
//		{
//			if (this.getOID() == null)
//			{
//				new mojo.km.persistence.Home().bind(this);
//			}
//			try
//			{
//				users =
//					new mojo.km.persistence.ArrayList(
//						RoleUsersUser.class,
//						"parentId",
//						"" + getOID());
//			}
//			catch (Throwable t)
//			{
//				users = new java.util.ArrayList();
//			}
//		}
	}
	*//**
	* insert a SystemActivity into class relationship
	* collection.
	* @param anObject
	* @roseuid 411139400002
	*//*
//	public void insertActivities(SystemActivity anObject)
//	{
//		initActivities();
//		RoleActivitiesSystemActivity actual =
//			new RoleActivitiesSystemActivity();
//		if (this.getOID() == null)
//		{
//			new Home().bind(this);
//		}
//		if (anObject.getOID() == null)
//		{
//			new Home().bind(anObject);
//		}
//		actual.setOID("" + this.getOID() + "0" + anObject.getOID());
//		actual.setChild(anObject);
//		actual.setParent(this);
//		activities.add(actual);
//	}

	*//**
	* insert a Role into class relationship collection.
	*//*
	public void insertParents(Role anObject)
	{
//		initParents();
//		RoleParentsRole actual =
//			new RoleParentsRole();
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
//		parents.add(actual);
	}
	*//**
	* insert a Role into class relationship collection.
	* @param anObject
	* @roseuid 4126779D03C6
	*//*
	public void insertRoles(Role anObject)
	{
		initRoles();
//		RoleRolesRole actual =
//			new RoleRolesRole();
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
//		roles.add(actual);
	}
	*//**
	* insert a User into class relationship collection.
	* @param anObject
	* @roseuid 41113940003E
	*//*
	public void insertUsers(User anObject)
	{
//		initUsers();
//		RoleUsersUser actual =
//			new RoleUsersUser();
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
//		users.add(actual);
	}
	*//**
	* Removes a SystemActivity from class relationship
	* collection.
	* @param anObject
	* @roseuid 41113940007A
	*//*
//	public void removeActivities(
//		SystemActivity anObject)
//	{
//		initActivities();
//		try
//		{
//			RoleActivitiesSystemActivity actual =
//				(RoleActivitiesSystemActivity) new mojo.km.persistence.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						RoleActivitiesSystemActivity.class).getObject();
//			activities.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
//	}

	*//**
	* Removes a Role from class relationship collection.
	*//*
	public void removeParents(Role anObject)
	{
//		initParents();
//		try
//		{
//			RoleParentsRole actual =
//				(RoleParentsRole) new mojo.km.persistence.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						RoleParentsRole.class).getObject();
//			parents.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
	}
	
	*//**
	* Removes a Role from class relationship collection.
	* @param anObject
	* @roseuid 4126779F0148
	*//*
	public void removeRoles(Role anObject)
	{
//		initRoles();
//		try
//		{
//			RoleRolesRole actual =
//				(RoleRolesRole) new mojo.km.persistence.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						RoleRolesRole.class).getObject();
//			roles.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
	}
	
	*//**
	* Removes a User from class relationship collection.
	* @param anObject
	* @roseuid 4111394000B6
	*//*
	public void removeUsers(User anObject)
	{
//		initUsers();
//		try
//		{
//			RoleUsersUser actual =
//				(RoleUsersUser) new mojo.km.persistence.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						RoleUsersUser.class).getObject();
//			users.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
	}

	*//**
	* Set the reference value to class :: pd.contact.Agency
	*//*
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			markModified();
		}

		this.agencyId = agencyId;
	}
	*//**
	* Sets the value of the creationDate property.
	* @param aCreationDate the new value of the creationDate property
	*//*
	public void setCreationDate(Timestamp aCreationDate)
	{
		if (creationDate == null || !this.creationDate.equals(aCreationDate))
		{
			markModified();
		}
		creationDate = aCreationDate;
	}

	*//**
	* Set the reference value to class :: pd.contact.UserProfile
	*//*
	public void setCreatorId(String creatorId)
	{
		if (this.creatorId == null || !this.creatorId.equals(creatorId))
		{
			markModified();
		}
		this.creatorId = creatorId;
	}
	
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
	* Set the reference value to class :: pd.contact.Division
	* @param divisionId
	* @roseuid 4111394001E3
	*//*
	public void setDivisionId(String divisionId)
	{
		if (this.divisionId == null || !this.divisionId.equals(divisionId))
		{
			markModified();
		}
		this.divisionId = divisionId;
	}

	*//**
	* Sets the value of the roleId property.
	* @param aRoleId the new value of the roleId property
	*//*
	public void setRoleId(java.lang.String aRoleId)
	{
		setOID(aRoleId);
	}

	*//**
	* Sets the value of the roleName property.
	* @param aRoleName the new value of the roleName property
	*//*
	public void setRoleName(java.lang.String aRoleName)
	{
		if (this.roleName == null || !this.roleName.equals(aRoleName))
		{
			markModified();
		}
		roleName = aRoleName;
	}

	*//**
	* Sets the value of the type property.
	* @param aType the new value of the type property
	*//*
	public void setType(java.lang.String aType)
	{
		if (this.type == null || !this.type.equals(aType))
		{
			markModified();
		}
		type = aType;
	}
}
*/