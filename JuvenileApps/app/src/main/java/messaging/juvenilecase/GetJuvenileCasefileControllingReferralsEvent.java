//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetNewCaseFileAssignmentsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileControllingReferralsEvent extends RequestEvent
{
    private String juvenileNum;
    private String casefileControllingReferralId;

    /**
     * @roseuid 4277C5F20290
     */
    public GetJuvenileCasefileControllingReferralsEvent()
    {

    }

    /**
     * @param juvenileNum
     *            the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }

    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    public String getCasefileControllingReferralId()
    {
	return casefileControllingReferralId;
    }

    public void setCasefileControllingReferralId(String casefileControllingReferralId)
    {
	this.casefileControllingReferralId = casefileControllingReferralId;
    }

}
