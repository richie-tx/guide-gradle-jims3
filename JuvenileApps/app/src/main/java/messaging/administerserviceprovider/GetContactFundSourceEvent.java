package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetContactFundSourceEvent extends RequestEvent
{
    private String profileId;
    

    public String getProfileId()
    {
	return profileId;
    }

    public void setProfileId(String profileId)
    {
	this.profileId = profileId;
    }
}
