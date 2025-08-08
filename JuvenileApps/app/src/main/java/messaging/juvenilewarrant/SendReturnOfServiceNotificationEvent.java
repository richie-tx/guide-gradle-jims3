package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class SendReturnOfServiceNotificationEvent extends RequestEvent
{
	private String officerWorkPhone;
	private int juvenileLastName;
	private int juvenileFirstName;
	private String warrantNum;
}
