package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLDetentionByJuvNumEvent extends RequestEvent
{

    private String juvenileNumber;

    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }
}
