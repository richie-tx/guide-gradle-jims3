package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportJuvenilePurgeEvent extends RequestEvent 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileId;
    private String purgeComments;
    private String purgeBox;
    private String purgeSeries;    
    private String lastUpdateId;
    private String action;
    
    
    
    
    public String getPurgeComments()
    {
        return purgeComments;
    }
    public void setPurgeComments(String purgeComments)
    {
        this.purgeComments = purgeComments;
    }
    public String getPurgeBox()
    {
        return purgeBox;
    }
    public void setPurgeBox(String purgeBox)
    {
        this.purgeBox = purgeBox;
    }
    public String getPurgeSeries()
    {
        return purgeSeries;
    }
    public void setPurgeSeries(String purgeSeries)
    {
        this.purgeSeries = purgeSeries;
    }
    public String getJuvenileId()
    {
        return juvenileId;
    }
    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }
	
	public String getLastUpdateId()
    {
        return lastUpdateId;
    }
    public void setLastUpdateId(String lastUpdateId)
    {
        this.lastUpdateId = lastUpdateId;
    }
    public String getAction()
    {
        return action;
    }
    public void setAction(String action)
    {
        this.action = action;
    }


	/**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportJuvenilePurgeEvent() 
   {       
   
   }
   
}
