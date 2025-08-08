package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author anpillai
 *
 */
public class GetJJSCLCourtByGALSearchEvent extends RequestEvent
{
    String juvenileNumber;
    String barNumber;
    String courtDate;
    String todaysDate;  
    
    
    
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
    public String getTodaysDate()
    {
        return todaysDate;
    }
    public void setTodaysDate(String todaysDate)
    {
        this.todaysDate = todaysDate;
    }
}
