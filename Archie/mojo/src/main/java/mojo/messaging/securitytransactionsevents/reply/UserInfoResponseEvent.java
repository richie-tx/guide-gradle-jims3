/*
 * Created on Jul 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.messaging.securitytransactionsevents.reply;

import mojo.km.security.IUserInfo;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserInfoResponseEvent implements IUserInfo 
{
	private String oid;
	private String agencyId;
	private String agencyName;
	private String firstName;
	private String lastName;
	private String middleName;
	//private String password;
	private String userTypeId;
	private String JIMS2LogonId;
	//private String JIMS2Password;
	private String JIMSLogonId;
	private String departmentId;
	private String departmentName;
	private String orgCode;
	private String accountType;

	

	/**
	 * A simple bean representation of the User Persistant
	 * Object.  
	 * @param user
	 */
	public UserInfoResponseEvent() {
	}
	
	/**
	 * A simple bean representation of the User Persistant
	 * Object.  Takes a user object as a constructor.
	 * @param user
	 */
	public UserInfoResponseEvent(IUserInfo user) {
		this.oid = (String) user.getUserOID();
		this.firstName = user.getFirstName();
		this.middleName = user.getMiddleName();
		this.lastName = user.getLastName();
		// no longer in use. Migrated to SM. Refer US #87188.
		//this.password = user.getPassword();
		this.userTypeId = user.getUserTypeId();
		this.agencyId = user.getAgencyId();
		this.departmentId = user.getDepartmentId();
		this.JIMS2LogonId = user.getJIMS2LogonId();		
		this.JIMSLogonId = user.getJIMSLogonId();
		//added as part of 87188
		this.agencyName= user.getAgencyName();
		this.departmentName= user.getDepartmentName();
		this.orgCode=user.getOrgCode();
	}	

	/**
	 * @return oid
	 */
	public String setUserOID()
	{
		return this.oid;
	}

	/**
	 * @return oid
	 */
	public String getUserOID()
	{
		return this.oid;
	}
	
	/**
	 * @return agencyId
	 */
	public String getAgencyId()
	{
		return this.agencyId;
	}

	/**
	 * @return first name
	 */
	public String getFirstName()
	{
		return this.firstName;
	}

	/**
	 * @return last name
	 */
	public String getLastName()
	{
		return this.lastName;
	}

	/**
	 * @return middle name
	 */
	public String getMiddleName()
	{
		return this.middleName;
	}

	/**
	 * @return password
	 *//*
	public String getPassword()
	{
		return this.password;
	}*/

	/**
	 * @return user type id
	 */
	public String getUserTypeId()
	{
		return this.userTypeId;
	}

	/**
	 * @param aAgencyId
	 */
	public void setAgencyId(String aAgencyId)
	{
		this.agencyId = aAgencyId;
	}

	/**
	 * @param aFirstName
	 */
	public void setFirstName(String aFirstName)
	{
		this.firstName = aFirstName;
	}

	/**
	 * @param aLastName
	 */
	public void setLastName(String aLastName)
	{
		this.lastName = aLastName;
	}

	/**
	 * @param aMiddleName
	 */
	public void setMiddleName(String aMiddleName)
	{
		this.middleName = aMiddleName;
	}

	/**
	 * @param aPassword
	 *//*
	public void setPassword(String aPassword)
	{
		this.password = aPassword;
	}*/

	/**
	 * @param aUserTypeId
	 */
	public void setUserTypeId(String aUserTypeId)
	{
		this.userTypeId = aUserTypeId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
	 */
	public String getJIMS2LogonId()
	{
		return JIMS2LogonId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
	 */
	public void setJIMS2LogonId(String JIMS2LogonId)
	{
		this.JIMS2LogonId = JIMS2LogonId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMSLogonId()
	 */
	public String getJIMSLogonId()
	{
		return JIMSLogonId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
	 */
	public void setJIMSLogonId(String JIMSLogonId)
	{
		this.JIMSLogonId = JIMSLogonId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getDepartmentId()
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setDepartmentId(java.lang.String)
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;		
	}


	public String getDepartmentName()
	{
	    // TODO Auto-generated method stub
	    return departmentName;
	}

	
	public void setDepartmentName(String departmentName)
	{
	  this.departmentName= departmentName;
	    
	}

	
	public String getOrgCode()
	{
	    return orgCode;
	}

	
	public void setOrgCode(String orgCode)
	{
	    this.orgCode = orgCode;
	    
	}

	public String getAgencyName()
	{
	    return agencyName;
	}

	public void setAgencyName(String agencyName)
	{
	    this.agencyName= agencyName;
	    
	}

	
	public void setUserOID(String smUserId)
	{
	   this.oid =smUserId;	    
	}
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMS2Password()
	 */
	/*public String getJIMS2Password()
	{
		return JIMS2Password;
	}
*/
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
	 */
/*	public void setJIMS2Password(String jims2Password)
	{
		this.JIMS2Password = jims2Password;
	}*/
}