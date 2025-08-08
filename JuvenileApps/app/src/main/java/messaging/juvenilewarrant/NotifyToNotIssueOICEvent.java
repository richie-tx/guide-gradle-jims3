package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class NotifyToNotIssueOICEvent extends RequestEvent
{
	private String warrantNum;
	private String comments;
	private String status;
	private String warrantActivationStatus;
}
