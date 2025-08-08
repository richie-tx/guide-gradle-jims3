package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLCourtByJuvNumResetDateChainNumEvent extends RequestEvent
{

    private String juvenileNumber;    
    private String resetDate;
    private String chainNumber;
    private String seqNumber;  
      
    
    
    public String getResetDate()
    {
        return resetDate;
    }
    public void setResetDate(String resetDate)
    {
        this.resetDate = resetDate;
    }
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    public String getChainNumber()
    {
        return chainNumber;
    }
    public void setChainNumber(String chainNumber)
    {
        this.chainNumber = chainNumber;
    }
    public String getSeqNumber()
    {
        return seqNumber;
    }
    public void setSeqNumber(String seqNumber)
    {
        this.seqNumber = seqNumber;
    }
    
}
