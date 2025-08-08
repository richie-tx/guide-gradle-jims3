package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportTestingSessionInfoEvent extends RequestEvent 
{
    public String juvenileId;
    public String testingSessionId;
    
    
    public String getJuvenileId()
    {
        return juvenileId;
    }
    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }
    public String getTestingSessionId()
    {
        return testingSessionId;
    }
    public void setTestingSessionId(String testingSessionId)
    {
        this.testingSessionId = testingSessionId;
    }
    
    

}
