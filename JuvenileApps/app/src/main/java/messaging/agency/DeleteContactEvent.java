//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\DeleteContactEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class DeleteContactEvent extends RequestEvent
{
	public String contactId;
	public String departmentId;

	/**
	 * @roseuid 4306378500CE
	 */
	public DeleteContactEvent()
	{

	}

	/**
	 * Access method for the contactId property.
	 * 
	 * @return   the current value of the contactId property
	 */
	public String getContactId()
	{
		return contactId;
	}

	/**
	 * Access method for the departmentId property.
	 * 
	 * @return   the current value of the departmentId property
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * Sets the value of the contactId property.
	 * 
	 * @param aContactId the new value of the contactId property
	 */
	public void setContactId(String aContactId)
	{
		contactId = aContactId;
	}

	/**
	 * Sets the value of the departmentId property.
	 * 
	 * @param aDepartmentId the new value of the departmentId property
	 */
	public void setDepartmentId(String aDepartmentId)
	{
		departmentId = aDepartmentId;
	}
}
