package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent extends RequestEvent
{
    
    String petitioNumber;
    String courtDate;
    
    
    public String getPetitioNumber()
    {
        return petitioNumber;
    }
    public void setPetitioNumber(String petitioNumber)
    {
        this.petitioNumber = petitioNumber;
    }
    public String getCourtDate()
    {
        return courtDate;
    }
    public void setCourtDate(String courtDate)
    {
        this.courtDate = courtDate;
    }
    

}
