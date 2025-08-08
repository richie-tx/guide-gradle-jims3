package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportCalendarServiceByInstructorEvent extends RequestEvent
{
    private String instructorId;
    private String fromStartDate;
    private String toStartDate;

    public String getInstructorId()
    {
        return instructorId;
    }

    public void setInstructorId(String instructorId)
    {
        this.instructorId = instructorId;
    }

    public String getFromStartDate()
    {
        return fromStartDate;
    }

    public void setFromStartDate(String fromStartDate)
    {
        this.fromStartDate = fromStartDate;
    }

    public String getToStartDate()
    {
        return toStartDate;
    }

    public void setToStartDate(String toStartDate)
    {
        this.toStartDate = toStartDate;
    }
    
    
    

}
