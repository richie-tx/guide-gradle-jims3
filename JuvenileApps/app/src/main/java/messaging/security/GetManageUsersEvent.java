package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetManageUsersEvent extends RequestEvent
{
	private String loginId;
	private String lastName;
	private String firstName;
	private String agencyType;
	private String agencyId;
	private String agencyName;
}
