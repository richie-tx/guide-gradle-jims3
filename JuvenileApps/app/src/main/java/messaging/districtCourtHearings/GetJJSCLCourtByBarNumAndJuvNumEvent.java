package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLCourtByBarNumAndJuvNumEvent extends RequestEvent
{
    String juvenileNumber;
    String barNumber;
    

    
    /**
     * @return the juvenileNum
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @param juvenileNum
     *            the juvenileNum to set
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
     * @param barNumber the barNumber to set
     */
    public void setBarNumber(String barNumber)
    {
        this.barNumber = barNumber;
    }

}
