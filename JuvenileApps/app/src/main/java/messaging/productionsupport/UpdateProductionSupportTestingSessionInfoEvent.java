package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportTestingSessionInfoEvent extends RequestEvent 
{ 
    private String testingSessionId;
    private String testingSessionDate;
    
    
    public String getTestingSessionId()
    {
        return testingSessionId;
    }
    public void setTestingSessionId(String testingSessionId)
    {
        this.testingSessionId = testingSessionId;
    }
    public String getTestingSessionDate()
    {
        return testingSessionDate;
    }
    public void setTestingSessionDate(String testingSessionDate)
    {
        this.testingSessionDate = testingSessionDate;
    }
    
    

}
