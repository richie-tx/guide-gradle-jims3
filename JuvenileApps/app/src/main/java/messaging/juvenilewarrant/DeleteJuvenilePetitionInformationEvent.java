package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class DeleteJuvenilePetitionInformationEvent extends RequestEvent
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String juvenileNum;
    String referralNum;
    String chainNum;
    String seqNum;
    String petitionNum;
    
    
    
    /**
     * @return the petitionNum
     */
    public String getPetitionNum()
    {
        return petitionNum;
    }
    /**
     * @param petitionNum the petitionNum to set
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }
    /**
     * @return the seqNum
     */
    public String getSeqNum()
    {
        return seqNum;
    }
    /**
     * @param seqNum the seqNum to set
     */
    public void setSeqNum(String seqNum)
    {
        this.seqNum = seqNum;
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
     * @return the chainNum
     */
    public String getChainNum()
    {
        return chainNum;
    }
    /**
     * @param chainNum the chainNum to set
     */
    public void setChainNum(String chainNum)
    {
        this.chainNum = chainNum;
    }
}
