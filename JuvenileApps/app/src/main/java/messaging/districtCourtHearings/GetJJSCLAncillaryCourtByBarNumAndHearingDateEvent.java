package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class GetJJSCLAncillaryCourtByBarNumAndHearingDateEvent extends RequestEvent
{

    private static final long serialVersionUID = 1L;

    String barNumber;
    String courtDate;

   

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

  

    /**
     * @return the courtDate
     */
    public String getCourtDate()
    {
        return courtDate;
    }

    /**
     * @param courtDate the courtDate to set
     */
    public void setCourtDate(String courtDate)
    {
        this.courtDate = courtDate;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }
}
