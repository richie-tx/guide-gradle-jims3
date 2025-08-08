package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportDrugTestingInfoEvent extends RequestEvent 
{
    String drugTestingId;

    public String getDrugTestingId()
    {
        return drugTestingId;
    }

    public void setDrugTestingId(String drugTestingId)
    {
        this.drugTestingId = drugTestingId;
    }
    
}
