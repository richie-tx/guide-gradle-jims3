package messaging.productionsupport;

import messaging.productionsupport.reply.ProductionSupportSubstanceAbuseInfoResponseEvent;
import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportSubstanceAbuseInfoEvent extends RequestEvent 
{
    private ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuse;
    private boolean highestOID;

    public ProductionSupportSubstanceAbuseInfoResponseEvent getSubstanceAbuse()
    {
        return substanceAbuse;
    }

    public void setSubstanceAbuse(ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuse)
    {
        this.substanceAbuse = substanceAbuse;
    }

    public boolean getHighestOID()
    {
        return highestOID;
    }

    public void setHighestOID(boolean highestOID)
    {
        this.highestOID = highestOID;
    }
    
    
    
    
    
    
    

}
