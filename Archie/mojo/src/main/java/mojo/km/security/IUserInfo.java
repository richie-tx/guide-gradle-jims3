/*
 * Created on Aug 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security;

/**
 * Used by the SecurityManager to return an implementation of this interface
 * when asking for ther user.
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IUserInfo extends java.io.Serializable
{
	String getUserOID();
	void setUserOID(String smUserId);
	
	String getFirstName();
	void setFirstName(String firstName);
	
	String getLastName();
	void setLastName(String lastName);
	
	String getMiddleName();
	void setMiddleName(String middleName);
	
	String getJIMS2LogonId();
	void setJIMS2LogonId(String JIMS2LogonId);
	// no longer in use. Migrated to SM. Refer US #87188.
	/*
	String getJIMS2Password();
	void setJIMS2Password(String jims2Password);
*/
	String getJIMSLogonId();
	void setJIMSLogonId(String JIMSLogonId);

	String getDepartmentId();
	void setDepartmentId(String departmentId);

	String getDepartmentName();
	void setDepartmentName(String departmentName);
	
	String getOrgCode();
	void setOrgCode(String orgCode);
	
	// no longer in use. Migrated to SM. Refer US #87188.
	/*String getPassword();
	void setPassword(String password);*/
	
	String getUserTypeId();
	void setUserTypeId(String userTypeId);
	
	String getAgencyId();
	void setAgencyId(String agencyId);
	
	String getAgencyName();
	void setAgencyName(String agencyName);
	
	String getAccountType();
	void setAccountType(String accountType);

}
