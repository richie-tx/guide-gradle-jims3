package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportFacilityDetentionEvent  extends RequestEvent 
{
    String detentionId;
    String lastSequenceNumber;
    String newLastSequenceNumber;
    
    
    public String getNewLastSequenceNumber()
    {
        return newLastSequenceNumber;
    }

    public void setNewLastSequenceNumber(String newLastSequenceNumber)
    {
        this.newLastSequenceNumber = newLastSequenceNumber;
    }

    public String getLastSequenceNumber()
    {
        return lastSequenceNumber;
    }

    public void setLastSequenceNumber(String lastSequenceNumber)
    {
        this.lastSequenceNumber = lastSequenceNumber;
    }

    public String getDetentionId()
    {
        return detentionId;
    }

    public void setDetentionId(String detentionId)
    {
        this.detentionId = detentionId;
    }

}
