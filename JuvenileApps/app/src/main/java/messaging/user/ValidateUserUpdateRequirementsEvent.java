package messaging.user;

import mojo.km.messaging.RequestEvent;

public class ValidateUserUpdateRequirementsEvent extends RequestEvent
{
	private String passwordMinDays;
	private String passwordMaxDays;
}
