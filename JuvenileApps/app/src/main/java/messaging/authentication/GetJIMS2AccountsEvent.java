//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\GetJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class GetJIMS2AccountsEvent extends RequestEvent
{
	private String jims2LogonId;
	private String lastName;
	private String firstName;
	private String departmentId;	
	private String jimsLogonId;
	private String jimsAccountId;

	/**
	 * @roseuid 4399CD3A02A3
	 */
	public GetJIMS2AccountsEvent()
	{

	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return (firstName==null?"":firstName);
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
		return (lastName==null?"":lastName);
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the departmentId.
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId The departmentId to set.
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return Returns the jims2LogonId.
	 */
	public String getJims2LogonId() {
		return (jims2LogonId==null?"":jims2LogonId);
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
	/**
	 * @return Returns the jimsAccountId.
	 */
	public String getJimsAccountId() {
		return jimsAccountId;
	}
	/**
	 * @param jimsAccountId The jimsAccountId to set.
	 */
	public void setJimsAccountId(String jimsAccountId) {
		this.jimsAccountId = jimsAccountId;
	}
}
