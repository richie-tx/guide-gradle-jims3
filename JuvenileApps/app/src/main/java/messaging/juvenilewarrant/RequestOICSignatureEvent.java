package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class RequestOICSignatureEvent extends RequestEvent
{
	private String judgeEmailAddres;
	private String districtClerkEmailAddress;
	private String warrantNum;
	private String middleName;
	private String lastName;
	private String firstName;
}
