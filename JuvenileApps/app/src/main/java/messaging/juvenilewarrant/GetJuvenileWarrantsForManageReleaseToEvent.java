package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForManageReleaseToEvent extends RequestEvent
{
	private String releaseDecision;
	private int juvenileLastName;
	private int juvenileFirstName;
	private String warrantNum;
}
