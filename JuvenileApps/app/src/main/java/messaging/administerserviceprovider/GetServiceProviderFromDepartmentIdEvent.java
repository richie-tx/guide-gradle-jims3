//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServiceLocationsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderFromDepartmentIdEvent extends RequestEvent
{
	public String departmentId;

	/**
	 * @roseuid 44805C9400DF
	 */
	public GetServiceProviderFromDepartmentIdEvent()
	{

	}


	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

}