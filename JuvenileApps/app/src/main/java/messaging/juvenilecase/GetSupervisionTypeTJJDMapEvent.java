package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetSupervisionTypeTJJDMapEvent extends RequestEvent
{
    String supervisionTypeId;

    public String getSupervisionTypeId()
    {
        return supervisionTypeId;
    }

    public void setSupervisionTypeId(String supervisionTypeId)
    {
        this.supervisionTypeId = supervisionTypeId;
    }
    
    

}
