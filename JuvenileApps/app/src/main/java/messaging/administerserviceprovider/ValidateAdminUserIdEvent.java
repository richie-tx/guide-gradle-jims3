package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class ValidateAdminUserIdEvent extends RequestEvent 
{
    private String adminUserId;

    public String getAdminUserId()
    {
	return adminUserId;
    }

    public void setAdminUserId(String adminUserId)
    {
	this.adminUserId = adminUserId;
    }
    
}
