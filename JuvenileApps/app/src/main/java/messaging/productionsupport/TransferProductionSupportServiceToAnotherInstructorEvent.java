package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class TransferProductionSupportServiceToAnotherInstructorEvent extends RequestEvent 
{
    String serviceEventId;
    String instructorId;
    public String getServiceEventId()
    {
        return serviceEventId;
    }
    public void setServiceEventId(String serviceEventId)
    {
        this.serviceEventId = serviceEventId;
    }
    public String getInstructorId()
    {
        return instructorId;
    }
    public void setInstructorId(String instructorId)
    {
        this.instructorId = instructorId;
    }
    
    

}
