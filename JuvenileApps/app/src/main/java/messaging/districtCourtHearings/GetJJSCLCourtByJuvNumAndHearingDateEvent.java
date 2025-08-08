package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLCourtByJuvNumAndHearingDateEvent extends RequestEvent
{

    private String juvenileNumber;
    
    private String courtDate;
    
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
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    
}
