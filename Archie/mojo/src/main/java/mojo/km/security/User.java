// no longer in use. Migrated to SM. Refer US #87188.  references in the mapping file commented.
package mojo.km.security;

//import messaging.security.GetRoleCreatorUserEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

/** @stereotype entity
 * @roseuid 422C77F4003E */
public class User /*extends PersistentObject implements IUserInfo*/
{
   /* private String agencyId;
    *//** Properties for constraints
     * 
     * @associationType simple
     * @referencedType mojo.km.security.Constraint *//*
    private java.util.Collection constraints = null;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
    //   private PrimaryDeptContact primaryContact = null; //not needed.86318
    *//** @associationType simple
     * @referencedType mojo.km.security.Role *//*
    private java.util.Collection roles = null;
    *//** @associationType simple
     * @referencedType mojo.km.security.UserGroup *//*
    private java.util.Collection userGroups = null;
    private String userTypeId;
    private String JIMSLogonId;
    private String JIMS2LogonId;
    private String JIMS2Password;
    private String departmentId;

    *//** @roseuid 422C77F4003E *//*
    public User()
    {
    }

    public void addConstraint(Object constrainerObjectOID, Class constrainerObjectType)
    {
	String constrainerType = mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
	if (constrainerType == null)
	{
	    throw new RuntimeException("No ConstraintMapping exists for the class of type " + constrainerObjectType + ". This needs to exist in the Security section of your mojo configuration");
	}
	Constraint newConstraint = new Constraint();
	newConstraint.setConstrainerId(constrainerObjectOID.toString());
	newConstraint.setConstrainerType(constrainerType);
	newConstraint.setConstrainsId(this.getOID().toString());
	newConstraint.setConstrainsType("USER");
    }

    *//** Clears all mojo.km.security.Role from class relationship collection.
     * 
     * @roseuid 4231D33A00EB *//*
    public void clearRoles()
    {
	initRoles();
	roles.clear();
    }

    *//** Clears all mojo.km.security.UserGroup from class relationship collection.
     * 
     * @roseuid 4231D3390324 *//*
    public void clearUserGroups()
    {
	initUserGroups();
	userGroups.clear();
    }

    public String getAgencyId()
    {
	fetch();
	return agencyId;
    }

    *//** returns a collection of mojo.km.security.Constraint *//*
    public java.util.Collection getConstraints()
    {
	java.util.ArrayList retVal = new java.util.ArrayList();
	//	if (constraints == null)
	//	{
	constraints = Constraint.findByConstrainsId(this.getOID().toString(), "USER");
	//	}
	java.util.Iterator i = constraints.iterator();
	while (i.hasNext())
	{
	    Constraint actual = (Constraint) i.next();
	    retVal.add(actual);
	}
	return retVal;
    }

    *//** returns a collection of mojo.km.security.Constraint *//*
    public java.util.Collection getConstraintsByConstrainerType(Class constrainerObjectType)
    {
	String constrainerType = mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
	if (constrainerType == null)
	{
	    throw new RuntimeException("No ConstraintMapping exists for the class of type " + constrainerObjectType + ". This needs to exist in the Security section of your mojo configuration");
	}
	java.util.ArrayList retVal = new java.util.ArrayList();
	Collection constraints = getConstraints();
	java.util.Iterator i = constraints.iterator();
	while (i.hasNext())
	{
	    Constraint constraint = (Constraint) i.next();
	    if (constraint.getConstrainerType().equalsIgnoreCase(constrainerType))
	    {
		retVal.add(constraint);
	    }
	}
	return retVal;
    }

    *//** @return *//*
    public String getFirstName()
    {
	fetch();
	return firstName;
    }

    *//** @return *//*
    public String getLastName()
    {
	fetch();
	return lastName;
    }

    *//** @return *//*
    public String getMiddleName()
    {
	fetch();
	return middleName;
    }

    *//** @return *//*
    public String getPassword()
    {
	fetch();
	return password;
    }

    *//** returns a collection of mojo.km.security.Role
     * 
     * @return java.util.Collection
     * @roseuid 4236ED9901FA *//*
    public java.util.Collection getRoles()
    {
	initRoles();
	java.util.ArrayList retVal = new java.util.ArrayList();
	java.util.Iterator i = roles.iterator();
	while (i.hasNext())
	{
	    mojo.km.security.UserRolesRole actual = (mojo.km.security.UserRolesRole) i.next();
	    retVal.add(actual.getChild());
	}
	return retVal;
    }

    *//** returns a collection of mojo.km.security.UserGroup
     * 
     * @return java.util.Collection
     * @roseuid 4236ED9900E1 *//*
    public java.util.Collection getUserGroups()
    {
	initUserGroups();
	java.util.ArrayList retVal = new java.util.ArrayList();
	java.util.Iterator i = userGroups.iterator();
	while (i.hasNext())
	{
	    mojo.km.security.UserUserGroupsUserGroup actual = (mojo.km.security.UserUserGroupsUserGroup) i.next();
	    retVal.add(actual.getChild());
	}
	return retVal;
    }

    *//** Access method for the userTypeId property.
     * 
     * @return the current value of the userTypeId property *//*
    public java.lang.String getUserTypeId()
    {
	fetch();
	return userTypeId;
    }

    *//** Initialize class relationship implementation for mojo.km.security.Role
     * 
     * @roseuid 4231D339034C *//*
    private void initRoles()
    {
	//	if (roles == null)
	//	{
	if (this.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(this);
	}
	try
	{
	    roles = new mojo.km.persistence.ArrayList(mojo.km.security.UserRolesRole.class, "parentId", "" + getOID());
	}
	catch (Throwable t)
	{
	    roles = new java.util.ArrayList();
	}
	//	}
    }

    *//** Initialize class relationship implementation for mojo.km.security.UserGroup
     * 
     * @roseuid 4231D33901F7 *//*
    private void initUserGroups()
    {
	//	if (userGroups == null)
	//	{
	if (this.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(this);
	}
	try
	{
	    userGroups = new mojo.km.persistence.ArrayList(mojo.km.security.UserUserGroupsUserGroup.class, "parentId", "" + getOID());
	}
	catch (Throwable t)
	{
	    userGroups = new java.util.ArrayList();
	}
	//}
    }

    *//** insert a mojo.km.security.Role into class relationship collection.
     * 
     * @param anObject
     * @roseuid 4236ED99022C *//*
    public void insertRoles(mojo.km.security.Role anObject)
    {
	initRoles();
	mojo.km.security.UserRolesRole actual = new mojo.km.security.UserRolesRole();
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

    *//** insert a mojo.km.security.UserGroup into class relationship collection.
     * 
     * @param anObject
     * @roseuid 4236ED990109 *//*
    public void insertUserGroups(mojo.km.security.UserGroup anObject)
    {
	initUserGroups();
	mojo.km.security.UserUserGroupsUserGroup actual = new mojo.km.security.UserUserGroupsUserGroup();
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
	userGroups.add(actual);
    }

    public void removeConstraint(Object constrainerObjectOID, Class constrainerObjectType)
    {
	String constrainerType = mojo.km.config.SecurityProperties.getInstance().getConstraintType(constrainerObjectType);
	if (constrainerType == null)
	{
	    throw new RuntimeException("No ConstraintMapping exists for the class of type " + constrainerObjectType + ". This needs to exist in the Security section of your mojo configuration");
	}
	Constraint constraint = Constraint.findByConstrainsIdAndConstrainerId(this.getOID().toString(), "USER", constrainerObjectOID.toString(), constrainerType);
	if (constraint != null)
	{
	    constraint.delete();
	}
    }

    *//** Removes a mojo.km.security.Role from class relationship collection.
     * 
     * @param anObject
     * @roseuid 4236ED9902B8 *//*
    public void removeRoles(mojo.km.security.Role anObject)
    {
	initRoles();
	try
	{
	    mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
	    assocEvent.setChildId((String) anObject.getOID());
	    assocEvent.setParentId((String) this.getOID());
	    mojo.km.security.UserRolesRole actual = (mojo.km.security.UserRolesRole) new mojo.km.persistence.Reference(assocEvent, mojo.km.security.UserRolesRole.class).getObject();
	    roles.remove(actual);
	}
	catch (Throwable t)
	{
	    t.printStackTrace();
	}
    }

    *//** Removes a mojo.km.security.UserGroup from class relationship collection.
     * 
     * @param anObject
     * @roseuid 4236ED990182 *//*
    public void removeUserGroups(mojo.km.security.UserGroup anObject)
    {
	initUserGroups();
	try
	{
	    mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
	    assocEvent.setChildId((String) anObject.getOID());
	    assocEvent.setParentId((String) this.getOID());
	    mojo.km.security.UserUserGroupsUserGroup actual = (mojo.km.security.UserUserGroupsUserGroup) new mojo.km.persistence.Reference(assocEvent, mojo.km.security.UserUserGroupsUserGroup.class)
		    .getObject();
	    userGroups.remove(actual);
	}
	catch (Throwable t)
	{
	    t.printStackTrace();
	}
    }

    *//** Sets the value of the agencyId property.
     * 
     * @param aAgencyId
     *            the new value of the agencyId property *//*
    public void setAgencyId(String aAgencyId)
    {
	if (this.agencyId == null || !this.agencyId.equals(aAgencyId))
	{
	    markModified();
	}
	this.agencyId = aAgencyId;
    }

    *//** Sets the value of the firstName property.
     * 
     * @param aFirstName
     *            the new value of the firstName property *//*
    public void setFirstName(String aFirstName)
    {
	if (this.firstName == null || !this.firstName.equals(aFirstName))
	{
	    markModified();
	}
	this.firstName = aFirstName;
    }

    *//** Sets the value of the lastName property.
     * 
     * @param aLastName
     *            the new value of the lastName property *//*
    public void setLastName(String aLastName)
    {
	if (this.lastName == null || !this.lastName.equals(aLastName))
	{
	    markModified();
	}
	this.lastName = aLastName;
    }

    *//** Sets the value of the middleName property.
     * 
     * @param aMiddleName
     *            the new value of the middleName property *//*
    public void setMiddleName(String aMiddleName)
    {
	if (this.middleName == null || !this.middleName.equals(aMiddleName))
	{
	    markModified();
	}
	this.middleName = aMiddleName;
    }

    *//** @param password *//*
    public void setPassword(String aPassword)
    {
	if (this.password == null || !this.password.equals(aPassword))
	{
	    markModified();
	}
	this.password = aPassword;
    }

    *//** Sets the value of the userTypeId property.
     * 
     * @param aUserType
     *            the new value of the userTypeId property *//*
    public void setUserTypeId(java.lang.String aUserType)
    {
	if (this.userTypeId == null || !this.userTypeId.equals(aUserType))
	{
	    markModified();
	}
	userTypeId = aUserType;
    }

    *//** @return mojo.km.security.User
     * @param userId
     * @roseuid 4236ED980299 *//*
    static public User find(String userId)
    {
	return (User) new Home().find(userId, User.class);
    }

    *//** @param IEvent
     * @return *//*
    public static Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator iter = home.findAll(event, Role.class);
	return iter;

    }

    *//** @return java.util.Iterator
     * @param attrName
     * @param attrValue
     * @roseuid 4236ED9802FD *//*
    static public Iterator findAll(String attrName, String attrValue)
    {
	return new Home().findAll(attrName, attrValue, User.class);
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getUserOID()
     
    public String getUserOID()
    {
	return JIMS2LogonId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
     
    public String getJIMS2LogonId()
    {
	return JIMS2LogonId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
     
    public void setJIMS2LogonId(String JIMS2LogonId)
    {
	this.JIMS2LogonId = JIMS2LogonId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getJIMSLogonId()
     
    public String getJIMSLogonId()
    {
	return JIMSLogonId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
     
    public void setJIMSLogonId(String JIMSLogonId)
    {
	this.JIMSLogonId = JIMSLogonId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getDepartmentId()
     
    public String getDepartmentId()
    {
	return departmentId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setDepartmentId(java.lang.String)
     
    public void setDepartmentId(String departmentId)
    {
	this.departmentId = departmentId;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getJIMS2Password()
     
    public String getJIMS2Password()
    {
	return JIMS2Password;
    }

     (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
     
    public void setJIMS2Password(String jims2Password)
    {
	this.JIMS2Password = jims2Password;
    }

      *//** @return *//* //86318
    
    public PrimaryDeptContact getPrimaryContact(String deptId)
    {
    return (PrimaryDeptContact) new Home().find(deptId, PrimaryDeptContact.class);
    }
    
    public HashMap getFeatures()
    {

	Collection roles = this.getRoles();
	Collection uGroups = this.getUserGroups();
	Collection uGRoles = null;
	HashMap features = new HashMap();

	// get features by User Group
	if (uGroups != null)
	{
	    for (Iterator iter = uGroups.iterator(); iter.hasNext();)
	    {
		UserGroup uGrp = (UserGroup) iter.next();
		uGRoles = uGrp.getRoles();
		if (uGRoles != null)
		{
		    for (Iterator iterator = uGRoles.iterator(); iterator.hasNext();)
		    {
			Role ugr = (Role) iterator.next();

			Collection uGFeatures = ugr.getFeatures();
			if (uGFeatures != null)
			{
			    for (Iterator it = uGFeatures.iterator(); it.hasNext();)
			    {
				Feature feat = (Feature) it.next();

				features.put(feat.getOID(), feat);
			    }
			}

		    }
		}
	    }
	}
	//		get features by Role
	if (roles != null)
	{
	    for (Iterator iter = roles.iterator(); iter.hasNext();)
	    {
		Role ugr = (Role) iter.next();
		Collection uGFeatures = ugr.getFeatures();
		if (uGFeatures != null)
		{
		    for (Iterator it = uGFeatures.iterator(); it.hasNext();)
		    {
			Feature feat = (Feature) it.next();
			features.put(feat.getOID(), feat);
		    }
		}
	    }
	}
	if ((userTypeId != null) && (userTypeId.equals("MA")))
	{
	    Iterator feats = Feature.findAll();
	    while (feats.hasNext())
	    {
		Feature feat = (Feature) feats.next();
		features.put(feat.getOID(), feat);
	    }

	}
	//					&& (!(userType.equals("SA")))
	//
	//					&& (!(userType.equals("ASA"))))
	return features;
    }

    @Override
    public String getDepartmentName()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setDepartmentName(String departmentName)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public String getAgencyName()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setAgencyName(String agencyName)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public String getOrgCode()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setOrgCode(String orgCode)
    {
	// TODO Auto-generated method stub
	
    }*/
}
