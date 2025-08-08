package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetPetitionBySequenceEvent extends RequestEvent
{

    public String sequenceNum;
    public String referralNum;
    public String juvenileNum;
    
    /**
     * @return the sequenceNum
     */
    public String getSequenceNum()
    {
        return sequenceNum;
    }
    /**
     * @param sequenceNum the sequenceNum to set
     */
    public void setSequenceNum(String sequenceNum)
    {
        this.sequenceNum = sequenceNum;
    }
    /**
     * @return the referralNum
     */
    public String getReferralNum()
    {
        return referralNum;
    }
    /**
     * @param referralNum the referralNum to set
     */
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    /**
     * @param juvenileNum the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
}
