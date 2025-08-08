/*
 * Created on Apr 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoleResponseEvent extends ResponseEvent implements Comparable
{
	//	public String action;
	private String agencyId;
	private String agncyType;
	private String agencyName;
	private Date createDate;
	//	private String createTime;
	private String creatorId;
//	private String roleCreatorFirstName;
//	private String roleCreatorLastName;
	private String roleDescription;
	private String roleId;
	private String roleName;
	private String roleType;
	private String roleTypeId;
	private Collection agencies;
	private Collection features = new ArrayList();
	private Collection userGroups = new ArrayList();
	private Collection users = new ArrayList();
	private boolean agencyUpdatable = false;
	//	public Collection associatedRoleGroups;
	//	public Collection availableRoleGroups;
	
	//	public Collection availableRoles;
	//	public Collection roleGroups;
	//	public Collection divisions;
	//	public Collection associatedSystemActivities;
	//	public Collection systemActivities;
	//	public Collection users;

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	
	/**
	 * @return
	 */
	public String getRoleDescription()
	{
		return roleDescription;
	}

	/**
	 * @return
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @return
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @return
	 */
	public String getRoleType()
	{
		return roleType;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		createDate = date;
	}



	/**
	 * @param string
	 */
	public void setRoleDescription(String string)
	{
		roleDescription = string;
	}

	/**
	 * @param string
	 */
	public void setRoleId(String string)
	{
		roleId = string;
	}

	/**
	 * @param string
	 */
	public void setRoleName(String string)
	{
		roleName = string;
	}

	/**
	 * @param string
	 */
	public void setRoleType(String string)
	{
		roleType = string;
	}

	/**
	 * @return
	 */
	public String getAgncyType()
	{
		return agncyType;
	}

	/**
	 * @param string
	 */
	public void setAgncyType(String string)
	{
		agncyType = string;
	}

	/**
	 * @return
	 */
	public String getCreatorId()
	{
		return creatorId;
	}

	/**
	 * @param string
	 */
	public void setCreatorId(String string)
	{
		creatorId = string;
	}


	/**
	 * @return
	 */
	public Collection getFeatures()
	{
		return features;
	}


	/**
	 * @param collection
	 */
	public void setFeatures(Collection collection)
	{
		features = collection;
	}

	/**
	 * @return
	 */
	public Collection getAgencies()
	{
		return agencies;
	}

	/**
	 * @param collection
	 */
	public void setAgencies(Collection collection)
	{
		agencies = collection;
	}
	/**
	 * @return
	 */
	public boolean isAgencyUpdatable()
	{
		return agencyUpdatable;
	}

	/**
	 * @param b
	 */
	public void setAgencyUpdatable(boolean b)
	{
		agencyUpdatable = b;
	}
	
	/**
	 * @param feature
	 */
	public void addFeature(FeaturesResponseEvent feature)
	{
		this.features.add(feature);
	}

	/**
	 * @return Returns the userGroups.
	 */
	public Collection getUserGroups() {
		return userGroups;
	}
	/**
	 * @param userGroup The userGroup to add.
	 */
	public void addUserGroup(UserGroupResponseEvent userGroup) {
		this.userGroups.add(userGroup);
	}
	/**
	 * @return Returns the users.
	 */
	public Collection getUsers() {
		return users;
	}
	/**
	 * @param user The user to add.
	 */
	public void addUser(SecurityUserResponseEvent user) {
		this.users.add(user);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		RoleResponseEvent c = (RoleResponseEvent)o;
		if (c.getRoleName() == null){
			return -1;
		}		
		if (this.getRoleName() == null){
			return 1;
		}
		return this.getRoleName().compareToIgnoreCase(c.getRoleName());
	}	
}
