package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class GetOfficerProfileFromUserGroupEvent extends RequestEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @roseuid 42E67C2501F4
	 */
	public GetOfficerProfileFromUserGroupEvent() {

	}

	private String userGroupId;

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

}
