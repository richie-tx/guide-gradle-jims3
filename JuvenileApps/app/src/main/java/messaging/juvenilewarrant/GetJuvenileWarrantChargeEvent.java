package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantChargeEvent extends RequestEvent
{

    private String warrantNum;
    
    /**
     * @roseuid 420A63F103C8
     */
    public GetJuvenileWarrantChargeEvent() 
    {
     
    }

    public String getWarrantNum()
    {
        return warrantNum;
    }

    public void setWarrantNum(String warrantNum)
    {
        this.warrantNum = warrantNum;
    }
    
    
}
