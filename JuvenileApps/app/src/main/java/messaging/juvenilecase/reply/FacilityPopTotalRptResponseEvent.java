package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

public class FacilityPopTotalRptResponseEvent extends ResponseEvent 
{
    String risk;
    String need;
    
    public String getRisk()
    {
        return risk;
    }
    public void setRisk(String risk)
    {
        this.risk = risk;
    }
    public String getNeed()
    {
        return need;
    }
    public void setNeed(String need)
    {
        this.need = need;
    }
    
    
    
}
