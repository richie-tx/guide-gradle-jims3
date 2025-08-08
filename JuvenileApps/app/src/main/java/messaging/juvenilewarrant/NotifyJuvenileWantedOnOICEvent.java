package messaging.juvenilewarrant;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class NotifyJuvenileWantedOnOICEvent extends RequestEvent
{
	private String warrantNum;
	private Date warrantActivationDate;
	private String petitionNum;
	private String juvenileNum;
	private String middleName;
	private String lastName;
	private String firstName;
	private String courtId;
}
