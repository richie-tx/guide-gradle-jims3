// no longer in use. Migrated to SM. Refer US #87188. references in the mapping file commented.
package mojo.km.security;

//import messaging.security.GetRolesByConstarintsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.*;

import java.util.Iterator;
import java.util.Collection;

/**
* @roseuid 422C77F30367
*/
public class Role extends PersistentObject {
	/**
	* Properties for userGroups
	* @associationType simple
	* @referencedType mojo.km.security.UserGroup
	*//*
	private java.util.Collection userGroups = null;
	private String roleType;
	private String name;
   
	*//**
	* Properties for features
	* @associationType simple
	* @referencedType mojo.km.security.Feature
	*//*
	private java.util.Collection features = null;
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
	
	private String creatorUserId = null;
	*//**   
	* @roseuid 422C77F30367
	*//*
	public Role() {
	}
	*//**
	* Access method for the roleType property.
	* @return the current value of the roleType property
	*//*
	public java.lang.String getRoleType() {
		fetch();
		return roleType;
	}
	*//**
	* Sets the value of the roleType property.
	* @param aRoleType the new value of the roleType property
	*//*
	public void setRoleType(java.lang.String aRoleType) {
		if (this.roleType == null || !this.roleType.equals(aRoleType)) {
			markModified();
		}
		roleType = aRoleType;
	}
	
	*//**
	* Sets the value of the creatorUserId property.
	* @param aCreatorUserId the new value of the creatorUserId property
	*//*
	public void setCreatorUserId(java.lang.String aCreatorUserId) {
		if (this.creatorUserId == null || !this.creatorUserId.equals(aCreatorUserId)) {
			markModified();
		}
		creatorUserId = aCreatorUserId;
	}
	
	
	*//**
	* Access method for the name property.
	* @return the current value of the name property
	*//*
	public java.lang.String getName() {
		fetch();
		return name;
	}
	*//**
	* Sets the value of the name property.
	* @param aName the new value of the name property
	*//*
	public void setName(java.lang.String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}
	*//**
	* Access method for the description property.
	* @return the current value of the description property
	*//*
	public java.lang.String getDescription() {
		fetch();
		return description;
	}
	*//**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*//*
	public void setDescription(java.lang.String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}
	*//**
	* Initialize class relationship implementation for mojo.km.security.User
	* @roseuid 4231D3370096
	*//*
	private void initUsers() {
		if (users == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				users = new mojo.km.persistence.ArrayList(mojo.km.security.RoleUsersUser.class, "parentId", "" + getOID());
			} catch (Throwable t) {
				users = new java.util.ArrayList();
			}
		}
	}
	*//**
	* Clears all mojo.km.security.User from class relationship collection.
	* @roseuid 4231D33701F4
	*//*
	public void clearUsers() {
		initUsers();
		users.clear();
	}
	*//**
	* Initialize class relationship implementation for mojo.km.security.Feature
	* @roseuid 4231D337021C
	*//*
	private void initFeatures() {
		if (features == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				features = new mojo.km.persistence.ArrayList(mojo.km.security.RoleFeaturesFeature.class, "parentId", "" + getOID());
			} catch (Throwable t) {
				features = new java.util.ArrayList();
			}
		}
	}
	*//**
	* Clears all mojo.km.security.Feature from class relationship collection.
	* @roseuid 4231D33703C1
	*//*
	public void clearFeatures() {
		initFeatures();
		features.clear();
	}
	*//**
	* Initialize class relationship implementation for mojo.km.security.UserGroup
	* @roseuid 4231D338017E
	*//*
	private void initUserGroups() {
		if (userGroups == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				userGroups = new mojo.km.persistence.ArrayList(mojo.km.security.RoleUserGroupsUserGroup.class, "parentId", "" + getOID());
			} catch (Throwable t) {
				userGroups = new java.util.ArrayList();
			}
		}
	}
	*//**
	* Clears all mojo.km.security.UserGroup from class relationship collection.
	* @roseuid 4231D33802A0
	*//*
	public void clearUserGroups() {
		initUserGroups();
		userGroups.clear();
	}
	*//**
	* @return mojo.km.security.Role
	* @param roleId
	* @roseuid 4236ED9502A8
	*//*
	static public Role find(String roleId) {
		return (Role) new Home().find(roleId, Role.class);
	}
	*//**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*//*
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName,attrValue,Role.class);
	}
	
	*//**
		* @return boolean
		* @param attrName
		* @param attrValue
		* @param agencyId
		* @roseuid 4236ED950316
		*//*
	
		public static boolean isRoleExistsWithinAgency(String attrName, String attrValue, String agencyId) {
		    Iterator it = findAll(attrName,attrValue);
			while(it.hasNext()){
				Role role = (Role) it.next();
				Collection collection = role.getConstraints();
				Iterator itCollection = collection.iterator();
				while(itCollection.hasNext()){
					Constraint con = (Constraint) itCollection.next();
					String type = con.getConstrainerType();
					if(con.getConstrainerId().equalsIgnoreCase(agencyId) && (type.equals("AGENCY"))){
					   return true;  
					}		
				}
			}
			return false;
		}
	
	*//**
		* @return java.util.Iterator
		* @roseuid 4236ED950316
		*//*
		public Object find() {
			IHome home = new Home();
			return home.find(this.getOID(),Role.class);
		}

	static public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(Role.class);
	}

	*//**
	* returns a collection of mojo.km.security.User
	* @return java.util.Collection
	* @roseuid 4236ED960278
	*//*
	public java.util.Collection getUsers() {
		initUsers();
		java.util.ArrayList retVal = new java.util.ArrayList();
		java.util.Iterator i = users.iterator();
		while (i.hasNext()) {
			mojo.km.security.RoleUsersUser actual = (mojo.km.security.RoleUsersUser) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	
	public String getCreatorUserId()
	{
		fetch();
		return creatorUserId;
		
	}
	*//**
	* insert a mojo.km.security.User into class relationship collection.
	* @param anObject
	* @roseuid 4236ED9602AA
	*//*
	public void insertUsers(mojo.km.security.User anObject) {
		initUsers();
		mojo.km.security.RoleUsersUser actual = new mojo.km.security.RoleUsersUser();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		users.add(actual);
	}
	*//**
	* Removes a mojo.km.security.User from class relationship collection.
	* @param anObject
	* @roseuid 4236ED960322
	*//*
	public void removeUsers(mojo.km.security.User anObject) {
		initUsers();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			mojo.km.security.RoleUsersUser actual =
				(mojo.km.security.RoleUsersUser) new mojo.km.persistence.Reference(assocEvent, mojo.km.security.RoleUsersUser.class).getObject();
			users.remove(actual);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	*//**
	* returns a collection of mojo.km.security.Feature
	* @return java.util.Collection
	* @roseuid 4236ED9603A4
	*//*
	public java.util.Collection getFeatures() {
		initFeatures();
		java.util.ArrayList retVal = new java.util.ArrayList();
		java.util.Iterator i = features.iterator();
		while (i.hasNext()) {
			mojo.km.security.RoleFeaturesFeature actual = (mojo.km.security.RoleFeaturesFeature) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	
	*//**
	* insert a mojo.km.security.Feature into class relationship collection.
	* @param anObject
	* @roseuid 4236ED9603D6
	*//*
	public void insertFeatures(mojo.km.security.Feature anObject) {
		initFeatures();
		mojo.km.security.RoleFeaturesFeature actual = new mojo.km.security.RoleFeaturesFeature();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		features.add(actual);
	}
	*//**
	* Removes a mojo.km.security.Feature from class relationship collection.
	* @param anObject
	* @roseuid 4236ED97007A
	*//*
	public void removeFeatures(mojo.km.security.Feature anObject) {
		initFeatures();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			mojo.km.security.RoleFeaturesFeature actual =
				(mojo.km.security.RoleFeaturesFeature) new mojo.km.persistence.Reference(assocEvent, mojo.km.security.RoleFeaturesFeature.class).getObject();
			features.remove(actual);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	*//**
	* returns a collection of mojo.km.security.Constraint
	* @return java.util.Collection
	* @roseuid 4236ED970107
	*//*
	public java.util.Collection getConstraints() {
		java.util.ArrayList retVal = new java.util.ArrayList();
		if (constraints == null) {
			constraints = Constraint.findByConstrainsId(this.getOID().toString(), "ROLE");
		}
		java.util.Iterator i = constraints.iterator();
		while (i.hasNext()) {
			Constraint actual = (Constraint) i.next();
			retVal.add(actual);
		}
		return retVal;
	}
	
	*//**
		* returns a collection of mojo.km.security.Constraint
		*//*
	public java.util.Collection getConstraintsByConstrainerType(Class constrainerObjectType) {
		String constrainerType = mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
		if (constrainerType == null) {
			throw new RuntimeException("No ConstraintMapping exists for the class of type " + constrainerObjectType + ". This needs to exist in the Security section of your mojo configuration");
		}
		java.util.ArrayList retVal = new java.util.ArrayList();
		if (constraints == null) {
			constraints = Constraint.findByConstrainsId(this.getOID().toString(), "ROLE");
		}
		java.util.Iterator i = constraints.iterator();
		while (i.hasNext()) {
			Constraint actual = (Constraint) i.next();
			if (actual.getConstrainerType().equalsIgnoreCase(constrainerType)) {
				retVal.add(actual);
			}
		}
		return retVal;
	}	
	
	public void addConstraint(Object constrainerObjectOID, Class constrainerObjectType) {
		String constrainerType = mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
		if (constrainerType == null) {
			throw new RuntimeException("No ConstraintMapping exists for the class of type " + constrainerObjectType + ". This needs to exist in the Security section of your mojo configuration");
		}
		Constraint newConstraint = new Constraint();
		newConstraint.setConstrainerId(constrainerObjectOID.toString());
		newConstraint.setConstrainerType(constrainerType);
		newConstraint.setConstrainsId(this.getOID().toString());
		newConstraint.setConstrainsType("ROLE");
	}
	
	public void removeConstraint(Object constrainerObjectOID, Class constrainerObjectType) {
		String constrainerType = mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
		if (constrainerType == null) {
			throw new RuntimeException("No ConstraintMapping exists for the class of type " + constrainerObjectType + ". This needs to exist in the Security section of your mojo configuration");
		}
		Constraint constraint = Constraint.findByConstrainsIdAndConstrainerId(this.getOID().toString(), "ROLE", constrainerObjectOID.toString(), constrainerType);
		if (constraint != null) {
			constraint.delete();
		}
	}
	
	*//**
	* returns a collection of mojo.km.security.UserGroup
	* @return java.util.Collection
	* @roseuid 4236ED97025B
	*//*
	public java.util.Collection getUserGroups() {
		initUserGroups();
		java.util.ArrayList retVal = new java.util.ArrayList();
		java.util.Iterator i = userGroups.iterator();
		while (i.hasNext()) {
			mojo.km.security.RoleUserGroupsUserGroup actual = (mojo.km.security.RoleUserGroupsUserGroup) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	*//**
	* insert a mojo.km.security.UserGroup into class relationship collection.
	* @param anObject
	* @roseuid 4236ED97028D
	*//*
	public void insertUserGroups(mojo.km.security.UserGroup anObject) {
		initUserGroups();
		mojo.km.security.RoleUserGroupsUserGroup actual = new mojo.km.security.RoleUserGroupsUserGroup();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		userGroups.add(actual);
	}
	*//**
	* Removes a mojo.km.security.UserGroup from class relationship collection.
	* @param anObject
	* @roseuid 4236ED970323
	*//*
	public void removeUserGroups(mojo.km.security.UserGroup anObject) {
		initUserGroups();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			mojo.km.security.RoleUserGroupsUserGroup actual =
				(mojo.km.security.RoleUserGroupsUserGroup) new mojo.km.persistence.Reference(assocEvent, mojo.km.security.RoleUserGroupsUserGroup.class).getObject();
			userGroups.remove(actual);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	*//**
	 * @param event
	 * @return
	 *//*
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, Role.class);
		return iter;
	}
	*//**
	* Access method for the roleId property.
	* @return the current value of the roleId property
	*//*
	public java.lang.String getRoleId()
	{
		return "" + getOID();
	}
	public static MetaDataResponseEvent findMeta(IEvent iEvent ) {
		  IHome home = new Home();
		  MetaDataResponseEvent iter = home.findMeta(iEvent, Role.class);
		  return iter;
		 }*/
}
