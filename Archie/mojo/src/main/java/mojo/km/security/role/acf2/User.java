package mojo.km.security.role.acf2;//no longer in use. Migrated to SM. Refer US #87188. references in the mapping file.
/*package mojo.km.security.role.acf2;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import mojo.km.naming.UserStatus;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.ObjectNotFoundException;

public class User extends Role
{
	*//**
	 * @param roleId
	 *//*
	static public Iterator findAll()
	{
		Iterator activities = null;
		return activities;
	}
	*//**
	 * @param aUserId
	 *//*
	static public User findUser(String aUserId)
	{
		User user = null;
		try
		{
			IHome home = new Home();
			user = (User) home.find(aUserId, User.class);
		}
		catch (ObjectNotFoundException e)
		{
		}
		return user;
	}
	private Date activationDate;
	*//**
	 * Properties for deactivator
	 *//*
	private String deactivatorId;
	private Date expirationDate;
	private Date lastLoginDate;
	private String logonId;
	private String password;
	private String requestorFirstName;
	private String requestorLastName;

	*//**
	 * Properties for parent
	 * @ associationType simple
	 * @ referencedType RoleGroup
	 *//* 
	private java.util.Collection parents = null;

	*//**
	 * Property for roles
	 * @associationType simple
	 * @referencedType Role
	 *//*
	private Collection roles;
	private Date suspendDate;

	*//**
	 * Properties for userProfile
	 *//*
	private String userProfileId;
	private String userStatus;
	*//**
	 * Default constructor
	 *//*
	public User()
	{
	}

	*//**
	 * Clears all Role from class relationship collection.
	 *//*
	public void clearRoles()
	{
		initRoles();
		roles.clear();
	}

	*//**
	 * Access method for the activationDate property.
	 * @return the current value of the activationDate property
	 *//*
	public Date getActivationDate()
	{
		fetch();
		return activationDate;
	}

	*//**
	 * Get the reference value to class :: pd.contact.UserProfile
	 *//*
	public String getDeactivatorId()
	{
		fetch();
		return deactivatorId;
	}

	*//**
	 * Access method for the expirationDate property.
	 * @return the current value of the expirationDate property
	 *//*
	public Date getExpirationDate()
	{
		fetch();
		return expirationDate;
	}

	*//**
	 * Access method for the lastLoginDate property.
	 * @return the current value of the lastLoginDate property
	 *//*
	public Date getLastLoginDate()
	{
		fetch();
		return lastLoginDate;
	}

	*//**
	 * Access method for the logonId property.
	 * @return the current value of the logonId property
	 *//*
	public java.lang.String getLogonId()
	{
		return "" + getOID();
	}
	*//**
	 * Access method for the password property.
	 * @return the current value of the password property
	 *//*
	public java.lang.String getPassword()
	{
		fetch();
		return password;
	}

	*//**
	 * returns a collection of Role
	 *//*
	public java.util.Collection getParents()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
//		initParents();
//		java.util.Iterator i = parents.iterator();
//		while (i.hasNext())
//		{
//			UserParentsUser actual = (UserParentsUser) i.next();
//			retVal.add(actual.getChild());
//		}
		return retVal;
	}

	*//**
	 * Access method for the requestorFirstName property.
	 * @return the current value of the requestorFirstName property
	 *//*
	public java.lang.String getRequestorFirstName()
	{
		fetch();
		return requestorFirstName;
	}

	*//**
	 * Access method for the requestorLastName property.
	 * @return the current value of the requestorLastName property
	 *//*
	public java.lang.String getRequestorLastName()
	{
		fetch();
		return requestorLastName;
	}

	*//**
	 * returns a collection of Role
	 *//*
	public java.util.Collection getRoles()
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
//		initRoles();
//		java.util.Iterator i = roles.iterator();
//		while (i.hasNext())
//		{
//			UserRolesRole actual = (UserRolesRole) i.next();
//			Role childRole = actual.getChild();
//			if (childRole != null)
//			{
//				retVal.add(childRole);
//			}
//		}
		return retVal;
	}

	*//**
	 * Access method for the suspendDate property.
	 * @return the current value of the suspendDate property
	 *//*
	public Date getSuspendDate()
	{
		fetch();
		return suspendDate;
	}

	*//**
	 * Get the reference value to class :: pd.contact.UserProfile
	 *//*
	public String getUserProfileId()
	{
		return userProfileId;
	}

	*//**
	 * Access method for the userStatus property.
	 * @return the current value of the userStatus property
	 *//*
	public java.lang.String getUserStatus()
	{
		fetch();
		return userStatus;
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
//						UserParentsUser.class,
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
//						UserRolesRole.class,
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
	 * insert a Role into class relationship collection.
	 *//*
//	public void insertParents(RoleGroup anObject)
//	{
//		initParents();
//		UserParentsUser actual = new UserParentsUser();
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
//	}

	*//**
	* insert a Role into class relationship collection.
	*//*
	public void insertRoles(Role anObject)
	{
//		initRoles();
//		UserRolesRole actual = new UserRolesRole();
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
	* Removes a Role from class relationship collection.
	*//*
	public void removeRoles(Role anObject)
	{
//		initRoles();
//		try
//		{
//			UserRolesRole actual =
//				(UserRolesRole) new mojo.km.persistence.Reference(
//						"" + this.getOID() + "-" + anObject.getOID(),
//						UserRolesRole.class).getObject();
//			roles.remove(actual);
//		}
//		catch (Throwable t)
//		{
//		}
	}

	*//**
	* Sets the value of the activationDate property.
	* @param aActivationDate the new value of the activationDate property
	*//*
	public void setActivationDate(Date aActivationDate)
	{
		if ((this.activationDate == null) || (!this.activationDate.equals(aActivationDate)))
		{
			markModified();
		}
		activationDate = aActivationDate;
	}

	*//**
	* Set the reference value to class :: pd.contact.UserProfile
	*//*
	public void setDeactivatorId(String deactivatorId)
	{
		if ((this.deactivatorId == null) || (!this.deactivatorId.equals(deactivatorId)))
		{
			markModified();
		}
		this.deactivatorId = deactivatorId;
	}

	*//**
	* Sets the value of the expirationDate property.
	* @param aExpirationDate the new value of the expirationDate property
	*//*
	public void setExpirationDate(Date aExpirationDate)
	{
		if ((this.expirationDate == null) || (!this.expirationDate.equals(aExpirationDate)))
		{
			markModified();
		}
		expirationDate = aExpirationDate;
	}

	*//**
	* Sets the value of the lastLoginDate property.
	* @param aLastLoginDate the new value of the lastLoginDate property
	*//*
	public void setLastLoginDate(Date aLastLoginDate)
	{
		if ((this.lastLoginDate == null) || (!this.lastLoginDate.equals(aLastLoginDate)))
		{
			markModified();
		}
		lastLoginDate = aLastLoginDate;
	}

	*//**
	* Sets the value of the logonId property.
	* @param aLogonId the new value of the logonId property
	*//*
	public void setLogonId(java.lang.String aLogonId)
	{
		if (this.logonId == null || !this.logonId.equals(aLogonId))
		{
			markModified();
		}
		logonId = aLogonId;
	}

	*//**
	* Sets the value of the password property.
	* @param aPassword the new value of the password property
	*//*
	public void setPassword(java.lang.String aPassword)
	{
		if ((this.password == null) || (!this.password.equals(aPassword)))
		{
			markModified();
		}
		password = aPassword;
	}

	*//**
	* Sets the value of the requestorFirstName property.
	* @param aRequestorFirstName the new value of the requestorFirstName property
	*//*
	public void setRequestorFirstName(java.lang.String aRequestorFirstName)
	{
		if ((this.requestorFirstName == null) || (!this.requestorFirstName.equals(aRequestorFirstName)))
		{
			markModified();
		}
		requestorFirstName = aRequestorFirstName;
	}

	*//**
	* Sets the value of the requestorLastName property.
	* @param aRequestorLastName the new value of the requestorLastName property
	*//*
	public void setRequestorLastName(java.lang.String aRequestorLastName)
	{
		if ((this.requestorLastName == null) || (!this.requestorLastName.equals(aRequestorLastName)))
		{
			markModified();
		}
		requestorLastName = aRequestorLastName;
	}

	*//**
	* Sets the value of the suspendDate property.
	* @param aSuspendDate the new value of the suspendDate property
	*//*
	public void setSuspendDate(Date aSuspendDate)
	{
		if ((this.suspendDate == null) || (!this.suspendDate.equals(aSuspendDate)))
		{
			markModified();
		}
		suspendDate = aSuspendDate;
	}

	*//**
	* Set the reference value to class :: pd.contact.UserProfile
	*//*
	public void setUserProfileId(String userProfileId)
	{
		this.userProfileId = userProfileId;
	}
	*//**
	* Sets the value of the userStatus property.
	* @param aUserStatus the new value of the userStatus property
	*//*
	public void setUserStatus(UserStatus aUserStatus)
	{
		if ((this.userStatus == null) || (!this.userStatus.equals(aUserStatus.getStatus())))
		{
			markModified();
		}
		userStatus = aUserStatus.getStatus();
	}
}
*/