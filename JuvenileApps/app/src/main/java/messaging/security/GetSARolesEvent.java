package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetSARolesEvent extends RequestEvent
{
	private String agencyName;
	private int roleName;
}
