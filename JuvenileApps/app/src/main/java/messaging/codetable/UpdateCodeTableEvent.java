package messaging.codetable;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class UpdateCodeTableEvent extends RequestEvent
{
	private String description;
	private Date activationDate;
	private String code;
}
