package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent extends RequestEvent
{
    String petitionNumber;
    String courtDate;
    
    
    public String getPetitionNumber()
    {
        return petitionNumber;
    }
    public void setPetitionNumber(String petitionNumber)
    {
        this.petitionNumber = petitionNumber;
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
