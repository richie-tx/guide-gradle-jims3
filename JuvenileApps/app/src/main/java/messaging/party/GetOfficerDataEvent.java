package messaging.party;

import mojo.km.messaging.RequestEvent;

public class GetOfficerDataEvent extends RequestEvent
{
	private String payrollNum;
	private String badgeNum;
	private String agencyId;
}
