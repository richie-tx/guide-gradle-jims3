package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJJSDetentionEventByDateEvent extends RequestEvent
{
	private String juvenileNum;
	private String startDate;
	private String endDate;
}
