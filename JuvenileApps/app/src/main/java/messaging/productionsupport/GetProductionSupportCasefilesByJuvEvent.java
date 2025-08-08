package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportCasefilesByJuvEvent extends RequestEvent
{
    String juvenileNumber;

    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }

    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }

}
