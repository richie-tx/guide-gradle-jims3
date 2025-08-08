package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class SendJuvenileWarrantReleaseNotificationEvent extends RequestEvent
{
	private int juvenileLastName;
	private int juvenileFirstName;
	private String warrantNum;
}
