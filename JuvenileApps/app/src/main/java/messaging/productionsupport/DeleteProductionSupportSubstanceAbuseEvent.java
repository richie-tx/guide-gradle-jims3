package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportSubstanceAbuseEvent extends RequestEvent 
{
    String substanceAbuseId;

    public String getSubstanceAbuseId()
    {
        return substanceAbuseId;
    }

    public void setSubstanceAbuseId(String substanceAbuseId)
    {
        this.substanceAbuseId = substanceAbuseId;
    }
    
}
