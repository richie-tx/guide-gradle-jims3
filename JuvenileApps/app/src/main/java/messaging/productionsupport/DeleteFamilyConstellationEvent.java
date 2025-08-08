package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteFamilyConstellationEvent  extends RequestEvent 
{
    public String constellationId;

    public String getConstellationId()
    {
        return constellationId;
    }

    public void setConstellationId(String constellationId)
    {
        this.constellationId = constellationId;
    }

}
