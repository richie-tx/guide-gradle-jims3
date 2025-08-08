package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import pd.codetable.Code;

public class UpdateJJSCLDetentionCourtEvent extends RequestEvent
{
   
    private static final long serialVersionUID = 1L;

    String docketEventId;  

    private String juvenileNumber;
    private String barNumber;
    private String attorneyConnection;
    private String attorneyName;
    private String hearingDate;
    private String hearingResult;
    private String hearingType;    
    private String referralNumber;
    private String sequenceNumber;
    private String detentionId;
    private String petitionNumber;

    private String courtId;
    
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
   
    public String getReferralNumber()
    {
        return referralNumber;
    }
    
    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }    
    
    
    public String getAttorneyName()
    {
        return attorneyName;
    }
   
    public void setAttorneyName(String attorneyName)
    {
        this.attorneyName = attorneyName;
    }
    
    public String getAttorneyConnection()
    {
        return attorneyConnection;
    }
    
    public void setAttorneyConnection(String attorneyConnection)
    {
        this.attorneyConnection = attorneyConnection;
    }
    
    public String getBarNumber()
    {
        return barNumber;
    }
    
    public void setBarNumber(String barNumber)
    {
        this.barNumber = barNumber;
    }
    
    public String getHearingResult()
    {
        return hearingResult;
    }
    
    public void setHearingResult(String hearingResult)
    {
        this.hearingResult = hearingResult;
    }
    
    public String getHearingType()
    {
        return hearingType;
    }
   
    public void setHearingType(String hearingType)
    {
        this.hearingType = hearingType;
    }
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    public String getDocketEventId()
    {
        return docketEventId;
    }

    public void setDocketEventId(String docketEventId)
    {
        this.docketEventId = docketEventId;
    }
    public String getHearingDate()
    {
        return hearingDate;
    }

    public void setHearingDate(String hearingDate)
    {
        this.hearingDate = hearingDate;
    }
    
    public String getSequenceNumber()
    {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
    }
    
    public String getDetentionId()
    {
        return this.detentionId;
    }

    public void setDetentionId(String detentionId)
    {
        this.detentionId = detentionId;
    }

    public String getPetitionNumber()
    {
        return petitionNumber;
    }

    public void setPetitionNumber(String petitionNumber)
    {
        this.petitionNumber = petitionNumber;
    }

    public String getCourtId()
    {
		// TODO Auto-generated method stub
	return courtId;
    }
    
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }
    
    
}
