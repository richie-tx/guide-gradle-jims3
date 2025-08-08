package messaging.security;

import mojo.km.messaging.RequestEvent;

public class UpdateManageUserEvent extends RequestEvent
{
	private String agencyId;
	private String userType;
	private String userID;
}
