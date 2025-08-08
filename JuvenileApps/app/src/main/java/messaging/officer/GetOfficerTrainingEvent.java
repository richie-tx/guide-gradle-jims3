package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class GetOfficerTrainingEvent extends RequestEvent
{

    private String officerProfileId;

    public String getOfficerProfileId()
    {
        return officerProfileId;
    }

    public void setOfficerProfileId(String officerProfileId)
    {
        this.officerProfileId = officerProfileId;
    }
    
    
}
