package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class GetOfficerProfileByLogonIdEvent extends RequestEvent
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * @roseuid 42E67C2501F4
     */
    public GetOfficerProfileByLogonIdEvent()
    {

    }

    private String userId;

    public String getUserId()
    {
	return userId;
    }

    public void setUserId(String userId)
    {
	this.userId = userId;
    }

}
