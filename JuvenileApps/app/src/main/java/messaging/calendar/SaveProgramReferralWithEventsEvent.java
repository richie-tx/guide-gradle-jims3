package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class SaveProgramReferralWithEventsEvent extends RequestEvent
{
	private String referralId;
	private String programReferralId;
	private String casefileId;
	private String juvenileNum;
}
