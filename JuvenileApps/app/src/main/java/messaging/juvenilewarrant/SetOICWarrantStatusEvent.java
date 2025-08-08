package messaging.juvenilewarrant;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class SetOICWarrantStatusEvent extends RequestEvent
{
	private String status;
	private String warrantSignatureStatus;
	private Date warrantActivationDate;
	private String warrantActivationStatus;
}
