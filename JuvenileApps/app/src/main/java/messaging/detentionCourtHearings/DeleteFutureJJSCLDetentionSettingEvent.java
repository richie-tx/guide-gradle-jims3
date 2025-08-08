package messaging.detentionCourtHearings;

import java.sql.Date;

import mojo.km.messaging.RequestEvent;

public class DeleteFutureJJSCLDetentionSettingEvent extends RequestEvent
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String docketEventId;
    private String chainNum;
    private String seqNumber;
    private String resetToDate;
    private String courtDate;
    
    
    public String getDocketEventId()
    {
        return docketEventId;
    }
    public void setDocketEventId(String docketEventId)
    {
        this.docketEventId = docketEventId;
    }
    public String getChainNum()
    {
        return chainNum;
    }
    public void setChainNum(String chainNum)
    {
        this.chainNum = chainNum;
    }
    public String getResetToDate()
    {
        return resetToDate;
    }
    public void setResetToDate(String resetToDate)
    {
        this.resetToDate = resetToDate;
    }
    public String getSeqNumber()
    {
        return seqNumber;
    }
    public void setSeqNumber(String seqNumber)
    {
        this.seqNumber = seqNumber;
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
