package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLDetentionByBarNumAndJuvNumEvent  extends RequestEvent
{
    private String juvenileNumber;
    private String barNumber;


    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }

    /**
     * @param juvenileNumber the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }

    /**
     * @return the barNumber
     */
    public String getBarNumber()
    {
	return barNumber;
    }

    /**
     * @param barNumber
     *            the barNumber to set
     */
    public void setBarNumber(String barNumber)
    {
	this.barNumber = barNumber;
    }

}
