package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * @author sthyagarajan
 * added for user-story #81390
 */
public class GetJJSCLDetentionByJuvNumRefNumEvent extends RequestEvent
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    public String juvenileNumber;
    public String referralNumber;

    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @param juvenileNumber
     *            the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    /**
     * @return the referralNumber
     */
    public String getReferralNumber()
    {
	return referralNumber;
    }

    /**
     * @param referralNumber
     *            the referralNumber to set
     */
    public void setReferralNumber(String referralNumber)
    {
	this.referralNumber = referralNumber;
    }

}
