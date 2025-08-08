//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\GetUserGroupsEvent.java

package messaging.inquiries;

import mojo.km.messaging.RequestEvent;

public class GetUserGroupSecurityInfoEvent extends RequestEvent
{
	private String userGroupId;

	/**
	 * @roseuid 429720C0012C
	 */
	public GetUserGroupSecurityInfoEvent()
	{

	}
	/**
	 * @return Returns the userGroupId.
	 */
	public String getUserGroupId() {
		return userGroupId;
	}
	/**
	 * @param userGroupId The userGroupId to set.
	 */
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
}