package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class SendProbableCauseServiceNotificationEvent extends RequestEvent
{
	private int notificationType;
	private String emailTo;
	private String emailFrom;
	private String lastName;
	private String firstName;
	private String warrantNum;
}
