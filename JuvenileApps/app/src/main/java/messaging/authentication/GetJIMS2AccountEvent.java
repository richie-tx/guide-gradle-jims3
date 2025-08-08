//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\GetJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class GetJIMS2AccountEvent extends RequestEvent
{
	private String logonId; //U.S #79250
	private String JIMS2LogonId;// retain for service provider
	private String lastName;
	private String firstName;
	private String departmentId;	
	private String jimsAccountId;
	
	/**
	 * @roseuid 4399CD3A02A3
	 */
	public GetJIMS2AccountEvent()
	{

	}

	/**
	 * Access method for the JIMS2LogonId property.
	 * 
	 * @return   the current value of the JIMS2LogonId property
	 */
	public String getJIMS2LogonId()
	{
		return JIMS2LogonId;
	}

	/**
	 * Sets the value of the JIMS2LogonId property.
	 * 
	 * @param aJIMS2LogonId the new value of the JIMS2LogonId property
	 */
	public void setJIMS2LogonId(String aJIMS2LogonId)
	{
		JIMS2LogonId = aJIMS2LogonId;
	}

	public String getLogonId()
	{
	    return logonId;
	}

	public void setLogonId(String logonId)
	{
	    this.logonId = logonId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
	    return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
	    this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
	    return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
	    this.firstName = firstName;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId()
	{
	    return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId)
	{
	    this.departmentId = departmentId;
	}

	/**
	 * @return the jimsAccountId
	 */
	public String getJimsAccountId()
	{
	    return jimsAccountId;
	}

	/**
	 * @param jimsAccountId the jimsAccountId to set
	 */
	public void setJimsAccountId(String jimsAccountId)
	{
	    this.jimsAccountId = jimsAccountId;
	}
	
}
