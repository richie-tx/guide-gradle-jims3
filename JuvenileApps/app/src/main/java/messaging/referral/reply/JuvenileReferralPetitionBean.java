package messaging.referral.reply;

import java.util.Date;

public class JuvenileReferralPetitionBean implements Comparable
{
    private String juvenileNum;
    private String petitionNum;
    private String referralNum;
    private String petitionStatus;
    private String severityLevel;
    private Date   closedDate;
    private String petitionOid;
    private String sequenceNum;
    
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getPetitionNum()
    {
        return petitionNum;
    }
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }
    public String getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    public String getPetitionStatus()
    {
        return petitionStatus;
    }
    public void setPetitionStatus(String petitionStatus)
    {
        this.petitionStatus = petitionStatus;
    }
    public Date getClosedDate()
    {
        return closedDate;
    }
    public void setClosedDate(Date closedDate)
    {
        this.closedDate = closedDate;
    }
    
    public String getSeverityLevel()
    {
        return severityLevel;
    }
    public void setSeverityLevel(String severityLevel)
    {
        this.severityLevel = severityLevel;
    }
    
    public String getPetitionOid()
    {
        return petitionOid;
    }
    public void setPetitionOid(String petitionOid)
    {
        this.petitionOid = petitionOid;
    }
    public String getSequenceNum()
    {
        return sequenceNum;
    }
    public void setSequenceNum(String sequenceNum)
    {
        this.sequenceNum = sequenceNum;
    }
    public int compare(JuvenileReferralPetitionBean o1, JuvenileReferralPetitionBean o2)
	{
	    // TODO Auto-generated method stub
	    return 0;
	}
    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }
        
}
