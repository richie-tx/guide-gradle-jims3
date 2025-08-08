//Source file: C:\\Views\\MSA\\app\\src\\messaging\\user\\GetUserProfilesEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

public class GetSALAASAUsersEvent extends RequestEvent
{
	private String agencyId;
	private String departmentId;
	
	/**
	 * default constructor
	 * @roseuid 4107BEE2018A
	 */
	public GetSALAASAUsersEvent()
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
}
