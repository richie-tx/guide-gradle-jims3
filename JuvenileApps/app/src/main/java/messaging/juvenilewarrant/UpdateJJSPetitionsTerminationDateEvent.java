package messaging.juvenilewarrant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mojo.km.messaging.RequestEvent;

public class UpdateJJSPetitionsTerminationDateEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileNum;
    private String referralNum;
    private Date terminationDate;    
    private Map reffDps = new HashMap();

      

    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }

    public String getReferralNum()
    {
        return referralNum;
    }

    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }

    public Date getTerminationDate()
    {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate)
    {
        this.terminationDate = terminationDate;
    }
    public Map getReffDps()
    {
        return reffDps;
    }

    public void setReffDps(Map reffDps)
    {
        this.reffDps = reffDps;
    }
}
