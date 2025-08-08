package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLDetentionByBarNumAndHearingDateEvent extends RequestEvent
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
     * @param barNumber
     *            the barNumber to set
     */
    public void setBarNumber(String barNumber)
    {
	this.barNumber = barNumber;
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
