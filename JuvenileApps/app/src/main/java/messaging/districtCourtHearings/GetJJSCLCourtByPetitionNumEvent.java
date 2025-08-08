package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLCourtByPetitionNumEvent extends RequestEvent 
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String petitionNumber;
    public String referralNumber;
    public String juvenileNumber;

    /**
     * @return the petitionNumber
     */
    public String getPetitionNumber()
    {
	return petitionNumber;
    }

    /**
     * @param petitionNumber
     *            the petitionNumber to set
     */
    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber;
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

}
