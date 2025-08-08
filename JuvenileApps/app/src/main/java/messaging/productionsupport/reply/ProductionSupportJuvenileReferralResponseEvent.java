package messaging.productionsupport.reply;

import mojo.km.messaging.ResponseEvent;

public class ProductionSupportJuvenileReferralResponseEvent extends ResponseEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileNum;
    private String referralNum;
    private String referralDate;
    private String referralOID;
    private String referralSource;
    private String referralOfficer;
    private String intakeDecision;
    private String closeDate;
    private String courtResult;
    private String courtDisposition;
    private String dispositionDate;
    private String courtId;
    private String piaStatus;
    private String violationProbation;
    private String daLogNum;
    private String transNum;
    private String offenseCode;
    private String lcuser;
    private String lcdate;
    private String intakeDate;
    private String rectype;
    private String referralTypeInd;
    private String ctAssignJPOId;
    private String probationStartDate;
    private String probationEndDate;
    private String pdaDate;
  /// task 172857
    private String TJJDreferralDate;
    private String countyREFD;   
    
    
    
    private String onCampOffense;
    private String onCampDistrict;
    private String onCampSchool;
    
    private String lcTime;  //Bug #93012
    private String jpoId;
    private String offenseTotal;
    private String probationJpoId;
    private String decisionType;
    /**
     * 
     * @return
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    
    public String getReferralOID()
    {
        return referralOID;
    }
    public void setReferralOID(String referralOID)
    {
        this.referralOID = referralOID;
    }
    public String getReferralDate()
    {
        return referralDate;
    }
    public void setReferralDate(String referralDate)
    {
        this.referralDate = referralDate;
    }
    public String getReferralSource()
    {
        return referralSource;
    }
    public void setReferralSource(String referralSource)
    {
        this.referralSource = referralSource;
    }
    public String getReferralOfficer()
    {
        return referralOfficer;
    }
    public void setReferralOfficer(String referralOfficer)
    {
        this.referralOfficer = referralOfficer;
    }
    public String getIntakeDecision()
    {
        return intakeDecision;
    }
    public void setIntakeDecision(String intakeDecision)
    {
        this.intakeDecision = intakeDecision;
    }
    public String getCloseDate()
    {
        return closeDate;
    }
    public void setCloseDate(String closeDate)
    {
        this.closeDate = closeDate;
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
    public String getPiaStatus()
    {
        return piaStatus;
    }
    public void setPiaStatus(String piaStatus)
    {
        this.piaStatus = piaStatus;
    }
    public String getViolationProbation()
    {
        return violationProbation;
    }
    public void setViolationProbation(String violationProbation)
    {
        this.violationProbation = violationProbation;
    }
    public String getDaLogNum()
    {
        return daLogNum;
    }
    public void setDaLogNum(String daLogNum)
    {
        this.daLogNum = daLogNum;
    }
    public String getTransNum()
    {
        return transNum;
    }
    public void setTransNum(String transNum)
    {
        this.transNum = transNum;
    }
    public String getOffenseCode()
    {
        return offenseCode;
    }
    public void setOffenseCode(String offenseCode)
    {
        this.offenseCode = offenseCode;
    }
    public String getLcuser()
    {
        return lcuser;
    }
    public void setLcuser(String lcuser)
    {
        this.lcuser = lcuser;
    }
    public String getLcdate()
    {
        return lcdate;
    }
    public void setLcdate(String lcdate)
    {
        this.lcdate = lcdate;
    }
    public String getIntakeDate()
    {
        return intakeDate;
    }
    public void setIntakeDate(String intakeDate)
    {
        this.intakeDate = intakeDate;
    }
    public String getRectype()
    {
        return rectype;
    }
    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }
    /**
     * @return the lcTime
     */
    public String getLcTime()
    {
	return lcTime;
    }
    /**
     * @param lcTime the lcTime to set
     */
    public void setLcTime(String lcTime)
    {
	this.lcTime = lcTime;
    }
    public String getReferralTypeInd()
    {
	return referralTypeInd;
    }
    public void setReferralTypeInd(String referralTypeInd)
    {
	this.referralTypeInd = referralTypeInd;
    }
    public String getCtAssignJPOId()
    {
	return ctAssignJPOId;
    }
    public void setCtAssignJPOId(String ctAssignJPOId)
    {
	this.ctAssignJPOId = ctAssignJPOId;
    }
    public String getProbationStartDate()
    {
	return probationStartDate;
    }
    public void setProbationStartDate(String probationStartDate)
    {
	this.probationStartDate = probationStartDate;
    }
    public String getProbationEndDate()
    {
	return probationEndDate;
    }
    public void setProbationEndDate(String probationEndDate)
    {
	this.probationEndDate = probationEndDate;
    }
    public String getPdaDate()
    {
	return pdaDate;
    }
    public void setPdaDate(String pdaDate)
    {
	this.pdaDate = pdaDate;
    }
    
    public String getOnCampOffense()
	{
	    return onCampOffense;
	}

	public void setOnCampOffense(String onCampOffense)
	{
	    this.onCampOffense = onCampOffense;
	}

	public String getOnCampDistrict()
	{
	    return onCampDistrict;
	}

	public void setOnCampDistrict(String onCampDistrict)
	{
	    this.onCampDistrict = onCampDistrict;
	}

	public String getOnCampSchool()
	{
	    return onCampSchool;
	}

	public void setOnCampSchool(String onCampSchool)
	{
	    this.onCampSchool = onCampSchool;
	}

    public String getCountyREFD()
    {
        return countyREFD;
    }
    public void setCountyREFD(String countyREFD)
    {
        this.countyREFD = countyREFD;
    }
    public String getTJJDreferralDate()
    {
        return TJJDreferralDate;
    }
    public void setTJJDreferralDate(String tJJDreferralDate)
    {
        TJJDreferralDate = tJJDreferralDate;
    }
    public String getJpoId()
    {
        return jpoId;
    }
    public void setJpoId(String jpoId)
    {
        this.jpoId = jpoId;
    }
    public String getOffenseTotal()
    {
        return offenseTotal;
    }
    public void setOffenseTotal(String offenseTotal)
    {
        this.offenseTotal = offenseTotal;
    }
    public String getProbationJpoId()
    {
        return probationJpoId;
    }
    public void setProbationJpoId(String probationJpoId)
    {
        this.probationJpoId = probationJpoId;
    }
    public String getDecisionType()
    {
        return decisionType;
    }
    public void setDecisionType(String decisionType)
    {
        this.decisionType = decisionType;
    }
    
    
}
