package messaging.referral;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileReferralProbDatesEvent extends RequestEvent 
{
    private String juvenileNum;
    private String referralNum;    
    private Date probationStartDate;
    private Date probationEndDate;
    private Date closedDate;
    private String casefileId;
        /**
     * @roseuid 42A9A16B0396
     */
    public UpdateJuvenileReferralProbDatesEvent() 
    {
     
    }
    
    /**
     * Access method for the juvenileNum property.
     * 
     * @return   the current value of the juvenileNum property
     */
    public String getJuvenileNum()
    {
       return juvenileNum;
    }
    
    /**
     * Sets the value of the juvenileNum property.
     * 
     * @param aJuvenileNum the new value of the juvenileNum property
     */
    public void setJuvenileNum(String aJuvenileNum)
    {
       juvenileNum = aJuvenileNum;
    }
    
    /**
     * Access method for the referralNum property.
     * 
     * @return   the current value of the referralNum property
     */
    public String getReferralNum()
    {
       return referralNum;
    }
    
    /**
     * Sets the value of the referralNum property.
     * 
     * @param aReferralNum the new value of the referralNum property
     */
    public void setReferralNum(String aReferralNum)
    {
       referralNum = aReferralNum;
    }

    /**
     * @return the probationStartDate
     */
    public Date getProbationStartDate()
    {
	return probationStartDate;
    }

    /**
     * @param probationStartDate the probationStartDate to set
     */
    public void setProbationStartDate(Date probationStartDate)
    {
	this.probationStartDate = probationStartDate;
    }

    /**
     * @return the probationEndDate
     */
    public Date getProbationEndDate()
    {
	return probationEndDate;
    }

    /**
     * @param probationEndDate the probationEndDate to set
     */
    public void setProbationEndDate(Date probationEndDate)
    {
	this.probationEndDate = probationEndDate;
    }

    /**
     * @return the closedDate
     */
    public Date getClosedDate()
    {
	return closedDate;
    }

    /**
     * @param closedDate the closedDate to set
     */
    public void setClosedDate(Date closedDate)
    {
	this.closedDate = closedDate;
    }

    public String getCasefileId()
    {
        return casefileId;
    }

    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }
    
 }