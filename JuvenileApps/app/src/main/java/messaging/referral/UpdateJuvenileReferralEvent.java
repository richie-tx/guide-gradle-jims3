package messaging.referral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileReferralEvent extends RequestEvent 
{
    private String juvenileNum;
    private String referralNum;
    private String referralSource;
    private Date intakeDate;
    private String intakeDecisionId;
    private Date referralDate;
    private String casefileGenerate;
    private Collection offenses = new ArrayList();
    private String offenseTotal;
    private String keyMapLocation;
    private String investigationNum;
    private String formalReferralType;
    private Date TJJDReferralDate;
    private String referralTypeInd;
    private String assignmentType;
    private String jpoID;
    private String ctAssignJPOId; //cts assign jpo id   //89887
    private String probJPOId; //prob jpo id.   //89887
    private String supervisionCat;
    private String supervisionType;
    private Date assignmentDate;
    private String subsequentCasefileId;
    private String actionFlag;
    private String updateAsmntTypeFlag;
    private Date probationStartDate;
    private Date probationEndDate;
    private Date closedDate;
    private String refStatus;
    private String casefileId;
    private String tjpcDisp;
    
    /**
     * @roseuid 42A9A16B0396
     */
    public UpdateJuvenileReferralEvent() 
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
     * @return the referralSource
     */
    public String getReferralSource()
    {
        return referralSource;
    }

    /**
     * @param referralSource the referralSource to set
     */
    public void setReferralSource(String referralSource)
    {
        this.referralSource = referralSource;
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
     * @return the offenses
     */
    public Collection getOffenses()
    {
        return offenses;
    }

    /**
     * @param offenses the offenses to set
     */
    public void setOffenses(Collection offenses)
    {
        this.offenses = offenses;
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
     * @return the casefileGenerate
     */
    public String getCasefileGenerate()
    {
	return casefileGenerate;
    }

    /**
     * @param casefileGenerate the casefileGenerate to set
     */
    public void setCasefileGenerate(String casefileGenerate)
    {
	this.casefileGenerate = casefileGenerate;
    }

    /**
     * @return the offenseTotal
     */
    public String getOffenseTotal()
    {
	return offenseTotal;
    }

    /**
     * @param offenseTotal the offenseTotal to set
     */
    public void setOffenseTotal(String offenseTotal)
    {
	this.offenseTotal = offenseTotal;
    }

    /**
     * @return the keyMapLocation
     */
    public String getKeyMapLocation()
    {
        return keyMapLocation;
    }

    /**
     * @param keyMapLocation the keyMapLocation to set
     */
    public void setKeyMapLocation(String keyMapLocation)
    {
        this.keyMapLocation = keyMapLocation;
    }

    /**
     * @return the investigationNum
     */
    public String getInvestigationNum()
    {
        return investigationNum;
    }

    /**
     * @param investigationNum the investigationNum to set
     */
    public void setInvestigationNum(String investigationNum)
    {
        this.investigationNum = investigationNum;
    }

    /**
     * @return the formalReferralType
     */
    public String getFormalReferralType()
    {
        return formalReferralType;
    }

    /**
     * @param formalReferralType the formalReferralType to set
     */
    public void setFormalReferralType(String formalReferralType)
    {
        this.formalReferralType = formalReferralType;
    }

    /**
     * @return the tJJDReferralDate
     */
    public Date getTJJDReferralDate()
    {
        return TJJDReferralDate;
    }

    /**
     * @param tJJDReferralDate the tJJDReferralDate to set
     */
    public void setTJJDReferralDate(Date tJJDReferralDate)
    {
        TJJDReferralDate = tJJDReferralDate;
    }

    /**
     * @return the referralTypeInd
     */
    public String getReferralTypeInd()
    {
	return referralTypeInd;
    }

    /**
     * @param referralTypeInd the referralTypeInd to set
     */
    public void setReferralTypeInd(String referralTypeInd)
    {
	this.referralTypeInd = referralTypeInd;
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
     * @return the supervisionCat
     */
    public String getSupervisionCat()
    {
        return supervisionCat;
    }

    /**
     * @param supervisionCat the supervisionCat to set
     */
    public void setSupervisionCat(String supervisionCat)
    {
        this.supervisionCat = supervisionCat;
    }

    /**
     * @return the supervisionType
     */
    public String getSupervisionType()
    {
        return supervisionType;
    }

    /**
     * @param supervisionType the supervisionType to set
     */
    public void setSupervisionType(String supervisionType)
    {
        this.supervisionType = supervisionType;
    }

    /**
     * @return the assignmentDate
     */
    public Date getAssignmentDate()
    {
        return assignmentDate;
    }

    /**
     * @param assignmentDate the assignmentDate to set
     */
    public void setAssignmentDate(Date assignmentDate)
    {
        this.assignmentDate = assignmentDate;
    }

    /**
     * @return the subsequentCasefileId
     */
    public String getSubsequentCasefileId()
    {
	return subsequentCasefileId;
    }

    /**
     * @param subsequentCasefileId the subsequentCasefileId to set
     */
    public void setSubsequentCasefileId(String subsequentCasefileId)
    {
	this.subsequentCasefileId = subsequentCasefileId;
    }

    public String getActionFlag()
    {
	return actionFlag;
    }

    public void setActionFlag(String actionFlag)
    {
	this.actionFlag = actionFlag;
    }

    /**
     * @return the updateAsmntTypeFlag
     */
    public String getUpdateAsmntTypeFlag()
    {
	return updateAsmntTypeFlag;
    }

    /**
     * @param updateAsmntTypeFlag the updateAsmntTypeFlag to set
     */
    public void setUpdateAsmntTypeFlag(String updateAsmntTypeFlag)
    {
	this.updateAsmntTypeFlag = updateAsmntTypeFlag;
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

  
    /**
     * @return the refStatus
     */
    public String getRefStatus()
    {
	return refStatus;
    }

    /**
     * @param refStatus the refStatus to set
     */
    public void setRefStatus(String refStatus)
    {
	this.refStatus = refStatus;
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

    public String getTJPCDisp()
    {
        return this.tjpcDisp;
    }

    public void setTJPCDisp(String tjpcDisp)
    {
        this.tjpcDisp = tjpcDisp;
    }
  
 }