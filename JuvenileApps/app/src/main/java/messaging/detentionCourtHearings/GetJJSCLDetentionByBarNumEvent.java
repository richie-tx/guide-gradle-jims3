package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * U.S #11645
 * 
 * @author sthyagarajan
 */
public class GetJJSCLDetentionByBarNumEvent extends RequestEvent
{

    private String barNumber;
  

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
