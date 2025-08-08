package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLCourtByBarNumEvent extends RequestEvent
{

    String barNumber;

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
