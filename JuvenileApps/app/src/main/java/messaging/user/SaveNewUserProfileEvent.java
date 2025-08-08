package messaging.user;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class SaveNewUserProfileEvent extends RequestEvent
{
	private String ssn;
	private String departmentId;
	private Date dateOfBirth;
	private String middleName;
	private String lastName;
	private String firstName;
}
