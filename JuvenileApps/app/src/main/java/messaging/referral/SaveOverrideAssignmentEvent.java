package messaging.referral;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveOverrideAssignmentEvent extends RequestEvent 
{
    private String referralId;
    private String juvenileNum;
    private String referralNum;
    private Date intakeDate;
    private String intakeDecisionId;
    private Date referralDate;
    private String assignmentType;
    private String jpoID;
    private String ctAssignJPOId; //cts assign jpo id   //89887
    private String probJPOId; //prob jpo id.   //89887
    private String casefileId;
  
    
    /**
     * @roseuid 42A9A16B0396
     */
    public SaveOverrideAssignmentEvent() 
    {
     
    }
    
    
    public String getReferralId()
    {
        return referralId;
    }


    public void setReferralId(String referralId)
    {
        this.referralId = referralId;
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
     * @return the intakeDate
     */
    public Date getIntakeDate()
    {
        return intakeDate;
    }

    /**
     * @param intakeDate the intakeDate to set
     */
    public void setIntakeDate(Date intakeDate)
    {
        this.intakeDate = intakeDate;
    }

    /**
     * @return the intakeDecisionId
     */
    public String getIntakeDecisionId()
    {
        return intakeDecisionId;
    }

    /**
     * @param intakeDecisionId the intakeDecisionId to set
     */
    public void setIntakeDecisionId(String intakeDecisionId)
    {
        this.intakeDecisionId = intakeDecisionId;
    }

    /**
     * @return the referralDate
     */
    public Date getReferralDate()
    {
	return referralDate;
    }

    /**
     * @param referralDate the referralDate to set
     */
    public void setReferralDate(Date referralDate)
    {
	this.referralDate = referralDate;
    }

    /**
     * @return the assignmentType
     */
    public String getAssignmentType()
    {
        return assignmentType;
    }

    /**
     * @param assignmentType the assignmentType to set
     */
    public void setAssignmentType(String assignmentType)
    {
        this.assignmentType = assignmentType;
    }

    /**
     * @return the jpoID
     */
    public String getJpoID()
    {
        return jpoID;
    }

    /**
     * @param jpoID the jpoID to set
     */
    public void setJpoID(String jpoID)
    {
        this.jpoID = jpoID;
    }

    /**
     * @return the casefileId
     */
    public String getCasefileId()
    {
	return casefileId;
    }

    /**
     * @param casefileId the casefileId to set
     */
    public void setCasefileId(String casefileId)
    {
	this.casefileId = casefileId;
    }

    /**
     * @return the ctAssignJPOId
     */
    public String getCtAssignJPOId()
    {
	return ctAssignJPOId;
    }

    /**
     * @param ctAssignJPOId the ctAssignJPOId to set
     */
    public void setCtAssignJPOId(String ctAssignJPOId)
    {
	this.ctAssignJPOId = ctAssignJPOId;
    }

    /**
     * @return the probJPOId
     */
    public String getProbJPOId()
    {
	return probJPOId;
    }

    /**
     * @param probJPOId the probJPOId to set
     */
    public void setProbJPOId(String probJPOId)
    {
	this.probJPOId = probJPOId;
    }

  
  
 }