package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLCourtByBarNumAndHearingDateEvent extends RequestEvent
{

    private String barNumber;
    private String courtDate;
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
    
}
