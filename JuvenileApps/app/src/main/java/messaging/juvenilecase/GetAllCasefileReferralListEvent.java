package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetAllCasefileReferralListEvent extends RequestEvent
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
