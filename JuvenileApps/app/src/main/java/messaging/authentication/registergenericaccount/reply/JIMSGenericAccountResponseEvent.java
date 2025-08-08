/*
 * Created on Nov 21, 2006
 */
package messaging.authentication.registergenericaccount.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class JIMSGenericAccountResponseEvent extends ResponseEvent implements Comparable
{
	private String logonId;
	private String password;
	private String departmentId;
	private String statusId;
	private String statusDesc;
	private String genericAccountId;
	private String canInactivate = "N";
	
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
	 * @return Returns the genericAccountId.
	 */
	public String getGenericAccountId() {
		return genericAccountId;
	}
	/**
	 * @param genericAccountId The genericAccountId to set.
	 */
	public void setGenericAccountId(String genericAccountId) {
		this.genericAccountId = genericAccountId;
	}

	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the statusDesc.
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc The statusDesc to set.
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	/**
	 * @return Returns the canInactivate.
	 */
	public String getCanInactivate() {
		return canInactivate;
	}
	/**
	 * @param canInactivate The canInactivate to set.
	 */
	public void setCanInactivate(String canInactivate) {
		this.canInactivate = canInactivate;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		JIMSGenericAccountResponseEvent c = (JIMSGenericAccountResponseEvent)o;
		if (c.getLogonId() == null){
			return -1;
		}		
		if (this.getLogonId() == null){
			return 1;
		}
		return this.getLogonId().compareToIgnoreCase(c.getLogonId());
	}	
}