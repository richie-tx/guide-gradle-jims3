package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportSubstanceAbuseInfoEvent extends RequestEvent 
{
    private String juvenileId;
    private String substanceAbuseId;
    
    
    public String getJuvenileId()
    {
        return juvenileId;
    }
    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }
    
    
    public String getSubstanceAbuseId()
    {
        return substanceAbuseId;
    }
    public void setSubstanceAbuseId(String substanceAbuseId)
    {
        this.substanceAbuseId = substanceAbuseId;
    }
    
    

}
