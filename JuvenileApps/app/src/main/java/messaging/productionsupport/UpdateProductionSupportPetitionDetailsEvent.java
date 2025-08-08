package messaging.productionsupport;

import java.sql.Time;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportPetitionDetailsEvent extends RequestEvent
{
    public UpdateProductionSupportPetitionDetailsEvent(){}
    
    public String getJuvenileNum()
    {
	return juvenileNum;
    }
    
    public void setJuvenileNum(String juvenileNumber)
    {
	this.juvenileNum = juvenileNumber;
    }


    public String getReferralNum()
    {
	return referralNum;
    }

    public void setReferralNum(String referralNumber)
    {
	this.referralNum = referralNumber;
    }
    
    
    public String getLcUser()
    {
        return lcUser;
    }

    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }

    
    public Date getLcDate()
    {
        return lcDate;
    }

    public void setLcDate(Date lcDate)
    {
        this.lcDate = lcDate;
    }
    
    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public String getOffenseCodeId()
    {
        return offenseCodeId;
    }

    public void setOffenseCodeId(String offenseCodeId)
    {
        this.offenseCodeId = offenseCodeId;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    public Date getPetitionDate()
    {
        return petitionDate;
    }

    public void setPetitionDate(Date petitionDate)
    {
        this.petitionDate = petitionDate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAmend()
    {
        return amend;
    }

    public void setAmend(String amend)
    {
        this.amend = amend;
    }
    
    public String getPetitionNum()
    {
        return petitionNum;
    }

    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    public String getSequenceNum()
    {
        return sequenceNum;
    }

    public void setSequenceNum(String sequenceNum)
    {
        this.sequenceNum = sequenceNum;
    }
    
    public Date getLcTime()
    {
	return lcTime;
    }

    public void setLcTime(Date lcTime)
    {
	this.lcTime = lcTime;
    }


    public String getOID()
    {
	return OID;
    }

    public void setOID(String oID)
    {
	OID = oID;
    }

    public String getSequenceNumber()
    {
	return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber)
    {
	this.sequenceNumber = sequenceNumber;
    }





    public Date getTerminationDate()
    {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate)
    {
        this.terminationDate = terminationDate;
    }
    
    public Date getTerminationCreateDate()
    {
        return this.terminationCreateDate;
    }

    public void setTerminationCreateDate(Date terminationCreateDate)
    {
        this.terminationCreateDate = terminationCreateDate;
    }
    
    public String getCJISNum()
    {
        return CJISNum;
    }

    public void setCJISNum(String cJISNum)
    {
        CJISNum = cJISNum;
    }
    public String getDPSCode()
    {
        return DPSCode;
    }

    public void setDPSCode(String dPSCode)
    {
        DPSCode = dPSCode;
    }




    private String juvenileNum;
    private String referralNum;
    private String lcUser;
    private Date lcDate;
    private Date lcTime;
    private String severity;
    private String offenseCodeId;
    private String type;
    private Date petitionDate;
    private String status;
    private String amend;
    private String petitionNum;
    private String sequenceNum;
    private String sequenceNumber;
    private String OID;
    private Date terminationDate;
    private Date terminationCreateDate;
    private String CJISNum;
    private String DPSCode;
  
    
    
    
    
    
}
