//Source file: C:\\views\\dev\\app\\src\\messaging\\user\\GetUserProfileEvent.java

package messaging.inquiries;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetUserSecurityInfoEvent extends RequestEvent
{
	private String logonId;
	private String employeeId;

	/**
	 * @roseuid 42E67E51014B
	 */
	public GetUserSecurityInfoEvent()
	{

	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}
	/**
	 * @return Returns the employeeId.
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId The employeeId to set.
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}