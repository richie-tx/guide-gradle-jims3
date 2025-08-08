package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCurrentFacilityByJuvenileIdEvent extends RequestEvent
{
    private String juvenileNum;

    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    
    

}
