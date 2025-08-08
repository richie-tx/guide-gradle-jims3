package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportDistrictCourtReferralEvent extends RequestEvent
{

    private String juvenileNum;
    private String OID;
    private String referralNum;  
    private String courtId;
    private String dispositionDate;
    private String courtResult;
    private String decision; 
    private String courtDisposition;
    private String lcDate;  
    private String lcUser;
    private String lcTime;   
    

    public UpdateProductionSupportDistrictCourtReferralEvent()
    {

    }

    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }

    public String getCourtResult()
    {
	return courtResult;
    }

    public void setCourtResult(String courtResult)
    {
	this.courtResult = courtResult;
    }

    public String getCourtDisposition()
    {
	return courtDisposition;
    }

    public void setCourtDisposition(String courtDisposition)
    {
	this.courtDisposition = courtDisposition;
    }

    public String getDispositionDate()
    {
	return dispositionDate;
    }

    public void setDispositionDate(String dispositionDate)
    {
	this.dispositionDate = dispositionDate;
    }

    public String getCourtId()
    {
	return courtId;
    }

    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }

    public String getLcDate()
    {
        return lcDate;
    }

    public void setLcDate(String lcDate)
    {
        this.lcDate = lcDate;
    }
    public String getLcUser()
    {
        return lcUser;
    }

    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }
    public String getDecision()
    {
        return decision;
    }

    public void setDecision(String decision)
    {
        this.decision = decision;
    }
    public String getReferralNum()
    {
        return referralNum;
    }

    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    public String getOID()
    {
        return OID;
    }

    public void setOID(String oID)
    {
        OID = oID;
    }
    public String getLcTime()
    {
        return lcTime;
    }

    public void setLcTime(String lcTime)
    {
        this.lcTime = lcTime;
    }

}
