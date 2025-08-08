package messaging.juvenilewarrant;

import java.util.Date;

import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.JuvenileOffenseCode;
import mojo.km.messaging.RequestEvent;

public class UpdateJuvenilePetitionInformationEvent extends RequestEvent
{
    
    private String juvenileNum;
    //private String referralNum;    
    private String petitionNum; // petitionNum    
    private String DPSCode;  
    
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    /*public String getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }*/
    public String getDPSCode()
    {
        return DPSCode;
    }
    public void setDPSCode(String dPSCode)
    {
        DPSCode = dPSCode;
    }
    public String getPetitionNum()
    {
        return petitionNum;
    }
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }
}
