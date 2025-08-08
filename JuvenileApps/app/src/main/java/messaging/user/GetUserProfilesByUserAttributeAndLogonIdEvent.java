//Source file: C:\\views\\dev\\app\\src\\messaging\\user\\GetUserProfileEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetUserProfilesByUserAttributeAndLogonIdEvent extends RequestEvent
{
	private String lastName;
	private String firstName;	
	private String agencyName;		
	private String agencyId;
	private String deptName;		
	private String deptId;
	private String jimsLogonId;
	private String jims2LogonId;

	/**
	 * @roseuid 42E67E51014B
	 */
	public GetUserProfilesByUserAttributeAndLogonIdEvent()
	{

	}


	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the agencyName.
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName The agencyName to set.
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return Returns the deptId.
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId The deptId to set.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return Returns the deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName The deptName to set.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the jims2LogonId.
	 */
	public String getJims2LogonId() {
		return jims2LogonId;
	}
	/**
	 * @param jims2LogonId The jims2LogonId to set.
	 */
	public void setJims2LogonId(String jims2LogonId) {
		this.jims2LogonId = jims2LogonId;
	}
	/**
	 * @return Returns the jimsLogonId.
	 */
	public String getJimsLogonId() {
		return jimsLogonId;
	}
	/**
	 * @param jimsLogonId The jimsLogonId to set.
	 */
	public void setJimsLogonId(String jimsLogonId) {
		this.jimsLogonId = jimsLogonId;
	}
}