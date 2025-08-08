package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLDetentionByHearingDateEvent extends RequestEvent
{
    private String juvNumber;     
    private String courtDate;
    
    public String getJuvNumber()
    {
        return juvNumber;
    }

    public void setJuvNumber(String juvNumber)
    {
        this.juvNumber = juvNumber;
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
