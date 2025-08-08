package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportJuvenileSealEvent extends RequestEvent 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileId;
    private String sealComments;
    private String sealedDate;
    private String lastUpdateId;
    private String action;
    
    
   
	public String getJuvenileId()
    {
        return juvenileId;
    }



    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }



	public String getSealComments()
    {
        return sealComments;
    }



    public void setSealComments(String sealComments)
    {
        this.sealComments = sealComments;
    }



    public String getSealedDate()
    {
        return sealedDate;
    }



    public void setSealedDate(String sealedDate)
    {
        this.sealedDate = sealedDate;
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
   public UpdateProductionSupportJuvenileSealEvent() 
   {       
   
   }



   
}
