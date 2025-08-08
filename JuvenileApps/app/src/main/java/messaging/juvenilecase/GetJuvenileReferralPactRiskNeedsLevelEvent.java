//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileRiskNeedLevelByStatusEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileReferralPactRiskNeedsLevelEvent extends RequestEvent
{
    private String juvenileNum;
    public String getReferralNumber()
    {
        return referralNumber;
    }

    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }

    private String referralNumber;

    /**
     * @roseuid 4278C82F0396
     */
    public GetJuvenileReferralPactRiskNeedsLevelEvent()
    {

    }

    /**
     * @return Returns the juvenileNum.
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            The juvenileNum to set.
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }
}
