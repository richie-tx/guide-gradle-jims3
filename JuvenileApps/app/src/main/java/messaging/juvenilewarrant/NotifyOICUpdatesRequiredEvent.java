package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class NotifyOICUpdatesRequiredEvent extends RequestEvent
{
	private String warrantNum;
	private String comments;
	private String warrantActivationStatus;
	private String status;
}
