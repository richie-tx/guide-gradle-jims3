package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * @author sthyagarajan
 */
public class GetJJSCLCourtByJuvNumEvent extends RequestEvent
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
