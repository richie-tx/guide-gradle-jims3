//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\DeleteDepartmentEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class DeleteDepartmentEvent extends RequestEvent
{
	public String departmentId;

	/**
	 * @roseuid 4306348C0127
	 */
	public DeleteDepartmentEvent()
	{

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
	 * Sets the value of the departmentId property.
	 * 
	 * @param aDepartmentId the new value of the departmentId property
	 */
	public void setDepartmentId(String aDepartmentId)
	{
		departmentId = aDepartmentId;
	}
}