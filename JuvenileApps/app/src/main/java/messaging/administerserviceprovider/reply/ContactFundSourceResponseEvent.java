package messaging.administerserviceprovider.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class ContactFundSourceResponseEvent extends ResponseEvent 
{
    Date entryDate;
    Date startDate;
    Date endDate;
    String sourceFund;
    String sourceFundDescription;
    String status;
    
    public Date getEntryDate()
    {
        return entryDate;
    }
    public void setEntryDate(Date entryDate)
    {
        this.entryDate = entryDate;
    }
    public Date getStartDate()
    {
        return startDate;
    }
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }
    public Date getEndDate()
    {
        return endDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    public String getSourceFund()
    {
        return sourceFund;
    }
    public void setSourceFund(String sourceFund)
    {
        this.sourceFund = sourceFund;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getSourceFundDescription()
    {
        return sourceFundDescription;
    }
    public void setSourceFundDescription(String sourceFundDescription)
    {
        this.sourceFundDescription = sourceFundDescription;
    }
    
    
    

}
