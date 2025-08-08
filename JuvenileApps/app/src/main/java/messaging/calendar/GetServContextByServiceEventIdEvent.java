package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetServContextByServiceEventIdEvent extends RequestEvent
{
    String serviceEventId;
    
    public GetServContextByServiceEventIdEvent(){}
    
    public String getServiceEventId()
    {
        return serviceEventId;
    }
    public void setServiceEventId(String serviceEventId)
    {
        this.serviceEventId = serviceEventId;
    }
    
    
    

}
