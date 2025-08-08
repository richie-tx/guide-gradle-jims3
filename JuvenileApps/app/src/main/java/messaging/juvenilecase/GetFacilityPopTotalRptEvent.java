package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetFacilityPopTotalRptEvent extends RequestEvent
{
    public GetFacilityPopTotalRptEvent(){}
    
    public String facilityCode;

    public String getFacilityCode()
    {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode)
    {
        this.facilityCode = facilityCode;
    }

}
