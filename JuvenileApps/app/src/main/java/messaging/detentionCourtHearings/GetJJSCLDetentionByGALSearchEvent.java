package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLDetentionByGALSearchEvent extends RequestEvent
{

    private String galBarNumber;
    
    private String juvenileNumber;
    private String courtDate;
    private String todaysDate;
    
    /**
     * @return the barNumber
     */
    public String getGalBarNumber()
    {
        return galBarNumber;
    }
    public void setGalBarNumber(String galBarNumber)
    {
        this.galBarNumber = galBarNumber;
    }
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
    public String getTodaysDate()
    {
        return todaysDate;
    }
    public void setTodaysDate(String todaysDate)
    {
        this.todaysDate = todaysDate;
    }
    
}
