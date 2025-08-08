package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class ListProductionSupportJuvenileReferralsEvent extends RequestEvent
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileId;

    public String getJuvenileId()
    {
        return juvenileId;
    }

    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }
    
    

}
