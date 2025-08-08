/*
 * Created on Dec 5, 2005
 */
package messaging.supervisionorder.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import naming.PDCommonSupervisionConstants;

/**
 * @author dgibler
 *  
 */
public class CaseOrderResponseEvent extends ResponseEvent implements Comparable
{
    private String caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_NO_ORDERS;

	private Date caseFileDate;

	private String caseNum;

	private String cdi;

	private String comments;

	private String confinementLengthDays;

	private String confinementLengthMonths;

	private String confinementLengthYears;

	private String connectionId;

	private String courtCategory; //added by Kiran
	
	private String courtId;

	private String courtNum;
	
	private String currentCourtCategory;
	private String currentCourtId;
	private String currentCourtNum;

	private String deferredSupervisionLength;

	private String dispositionTypeId;//rry 10/23/08

	private double fineAmountTotal;

	private boolean isHistoricalOrder;

	private boolean isMigratedOrder;

	private boolean isSignedByDefendant;

	private String jailTime;

	private String judgeFirstName;

	private String judgeLastName;

	private String judgeName;

	private String juvenileCourtId;
	
    private String juvSupervisionLengthDays; // cws 10/15/09

    private String juvSupervisionLengthMonths; // cws 10/15/09
    
    private String juvSupervisionLengthYears; // cws 10/15/09

    private Date juvSupervisionOrderBeginDate; // cws 10/15/09

	private Collection likeConditionIds = new ArrayList();

	private boolean likeConditionInd;

	private boolean limitedSupervisionPeriod;
	
	private String modificationReason;

	private String name;

	private String offense;

    private String offenseId;
    
    private String offenseLevel;

    private int orderChainNum;

    private Date orderFileDate;
    
    private Date statusChangeDate; 

    private String orderId;

    private String orderPresentorBy;

    private String orderStatus;

    private String orderStatusId;

    private String orderTitle;

    private String orderTitleId;

    private String orderVersion;

    private String origJudgeFirstName;
    
    private String origJudgeLastName;
    
    private boolean outOfCountyCase=false;
    
    private String plea;
    
    private String presentedByFirstName;
    
    private String presentedByLastName;
    
    private String printedName;

	private String printedOffenseDesc;

    private Date probationEndDate;
    
    private Date probationStartDate;
    
    private Date signedByDefendantDate;

    private Date signedByOfficerDate;
    
    private Date signedByJudgeDate;

    private String specialCourtCd;

    private String spn;
    
    private String suggestedOrderId;

    private String summaryChanges;
    
    private String supervisionLengthDays;

    private String supervisionLengthMonths;
    
    private String supervisionLengthNum;

    private String supervisionLengthYears;

    private Date supervisionOrderBeginDate;

    private Date supervisionOrderEndDate;

    private Date supOrderRevisionDate;
    
    private Date updateDate;

    private int versionNum;

    private String versionType;

    private String versionTypeId;
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object arg0)
    {
        CaseOrderResponseEvent cre = (CaseOrderResponseEvent) arg0;
        int comparisonResult = 0;
        String newString = "";
        if (this.getCaseNum() == null)
        {
            this.setCaseNum(newString);
        }
        if (this.getCdi() == null)
        {
            this.setCdi(newString);
        }
        if (this.getName() == null)
        {
            this.setName(newString);
        }
        if (cre.getCaseNum() == null)
        {
            cre.setCaseNum(newString);
        }
        if (cre.getCdi() == null)
        {
            cre.setCdi(newString);
        }
        if (cre.getName() == null)
        {
            cre.setName(newString);
        }

        if (this.getName().compareTo(cre.getName()) == 0)
        {
            if (this.getCdi().compareTo(cre.getCdi()) == 0)
            {
                if (this.getCaseNum().compareTo(cre.getCaseNum()) == 0)
                {
                    comparisonResult = this.getOrderStatusId().compareTo(cre.getOrderStatusId());
                }
                else
                {
                    comparisonResult = this.getCaseNum().compareTo(cre.getCaseNum());
                }
            }
            else
            {
                comparisonResult = this.getCdi().compareTo(cre.getCdi());
            }
        }
        else
        {
            comparisonResult = this.getName().compareTo(cre.getName());
        }
        return comparisonResult;
    }
    
    /**
     * @return
     */
    public String getCaseActivityRuleStatus()
    {
        return caseActivityRuleStatus;
    }

    /**
     * @return
     */
    public Date getCaseFileDate()
    {
        return caseFileDate;
    }

    /**
     * @return
     */
    public String getCaseNum()
    {
        return caseNum;
    }

    /**
     * @return
     */
    public String getCdi()
    {
        return cdi;
    }

    public String getComments() {
		return comments;
	}   

    /**
     * @return Returns the confinementLengthDays.
     */
    public String getConfinementLengthDays()
    {
        return confinementLengthDays;
    }
    
    /**
     * @return Returns the confinementLengthMonths.
     */
    public String getConfinementLengthMonths()
    {
        return confinementLengthMonths;
    }

    /**
     * @return Returns the confinementLengthYears.
     */
    public String getConfinementLengthYears()
    {
        return confinementLengthYears;
    }

    /**
     * @return
     */
    public String getConnectionId()
    {
        return connectionId;
    }

    /**
     * @return
     */
    public String getCourtCategory()
    {
        return courtCategory;
    }

    /**
     * @return
     */
    public String getCourtId()
    {
        return courtId;
    }

    /**
     * @return
     */
    public String getCourtNum()
    {
        return courtNum;
    }

    /**
     * @return
     */
    public String getDeferredSupervisionLength()
    {
        return deferredSupervisionLength;
    }

    public String getDispositionTypeId() {
		return dispositionTypeId;
	}

    /**
     * @return
     */
    public double getFineAmountTotal()
    {
        return fineAmountTotal;
    }

    /**
     * @return Returns the isHistoricalOrder.
     */
    public boolean getIsHistoricalOrder() {
        return isHistoricalOrder;
    }

    /**
     * @return Returns the isMigratedOrder.
     */
    public boolean getIsMigratedOrder() {
        return isMigratedOrder;
    }   
    /**
     * @return
     */
    public String getJailTime()
    {
        return jailTime;
    }

    public String getJudgeFirstName() {
		return judgeFirstName;
	}

    public String getJudgeLastName() {
		return judgeLastName;
	}

    /**
     * @return
     */
    public String getJudgeName()
    {
        return judgeName;
    }

    public String getJuvenileCourtId() {
		return juvenileCourtId;
	}

    /**
	 * @return the juvSupervisionLengthDays
	 */
	public String getJuvSupervisionLengthDays() {
		return juvSupervisionLengthDays;
	}

	/**
	 * @return the juvSupervisionLengthMonths
	 */
	public String getJuvSupervisionLengthMonths() {
		return juvSupervisionLengthMonths;
	}

	/**
	 * @return the juvSupervisionLengthYears
	 */
	public String getJuvSupervisionLengthYears() {
		return juvSupervisionLengthYears;
	}

	/**
	 * @return the juvSupervisionOrderBeginDate
	 */
	public Date getJuvSupervisionOrderBeginDate() {
		return juvSupervisionOrderBeginDate;
	}

	/**
     * @return
     */
    public Collection getLikeConditionIds()
    {
        return likeConditionIds;
    }
    
    /**
     * @return
     */
    public boolean getLikeConditionInd()
    {
        return likeConditionInd;
    }

    /**
     * @return
     */
    public String getModificationReason()
    {
        return modificationReason;
    }

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return
     */
    public String getOffense()
    {
        return offense;
    }

    /**
     * @return
     */
    public String getOffenseId()
    {
        return offenseId;
    }

    /**
     * @return Returns the offenseLevel.
     */
    public String getOffenseLevel()
    {
        return offenseLevel;
    }

    /**
	 * @return Returns the orderChainNum.
	 */
	public int getOrderChainNum() {
		return orderChainNum;
	}

    /**
     * @return
     */
    public Date getOrderFileDate()
    {
        return orderFileDate;
    }
    
    /**
     * @return
     */
    public Date getStatusChangeDate()
    {
        return statusChangeDate;
    }


    /**
     * @return
     */
    public String getOrderId()
    {
        return orderId;
    }

    /**
     * @return Returns the orderPresentorBy.
     */
    public String getOrderPresentorBy()
    {
        return orderPresentorBy;
    }

    /**
     * @return
     */
    public String getOrderStatus()
    {
        return orderStatus;
    }

    /**
     * @return
     */
    public String getOrderStatusId()
    {
        return orderStatusId;
    }

    /**
     * @return
     */
    public String getOrderTitle()
    {
        return orderTitle;
    }

    /**
     * @return
     */
    public String getOrderTitleId()
    {
        return orderTitleId;
    }

    /**
     * @return
     */
    public String getOrderVersion()
    {
        return orderVersion;
    }

    public String getOrigJudgeFirstName() {
		return origJudgeFirstName;
	}
    
    public String getOrigJudgeLastName() {
		return origJudgeLastName;
	}

	/**
     * @return
     */
    public String getPlea()
    {
        return plea;
    }

    public String getPresentedByFirstName() {
		return presentedByFirstName;
	}
    public String getPresentedByLastName() {
		return presentedByLastName;
	}

    /**
     * Builds primary key.
     * 
     * @return
     */
    public String getPrimaryKey()
    {
        StringBuffer primaryKey = new StringBuffer(this.getCdi());
        primaryKey.append(this.getCaseNum());
        if (this.getOrderId() != null)
        {
            primaryKey.append(this.getOrderId());
        }
        return primaryKey.toString();
    }

    public String getPrintedName() {
		return printedName;
	}

    public String getPrintedOffenseDesc() {
		return printedOffenseDesc;
	}

    /**
     * @return
     */
    public Date getProbationEndDate()
    {
        return probationEndDate;
    }

    /**
     * @return
     */
    public Date getProbationStartDate()
    {
        return probationStartDate;
    }

    /**
     * @return Returns the signedByDefendantDate.
     */
    public Date getSignedByDefendantDate()
    {
        return signedByDefendantDate;
    }

    /**
     * @return Returns the signedByJudgeDate.
     */
    public Date getSignedByOfficerDate()
    {
        return signedByOfficerDate;
    }

    public String getSpecialCourtCd() {
		return specialCourtCd;
	}

    /**
     * @return
     */
    public String getSpn()
    {
        return spn;
    }

    /**
     * @return
     */
    public String getSuggestedOrderId()
    {
        return suggestedOrderId;
    }

    /**
     * @return
     */
    public String getSummaryChanges()
    {
        return summaryChanges;
    }

    /**
     * @return Returns the supervisionLenghDays.
     */
    public String getSupervisionLengthDays()
    {
        return supervisionLengthDays;
    }

    /**
     * @return Returns the supervisionLenghMonths.
     */
    public String getSupervisionLengthMonths()
    {
        return supervisionLengthMonths;
    }

    /**
     * @return
     */
    public String getSupervisionLengthNum()
    {
        return supervisionLengthNum;
    }

    /**
     * @return Returns the supervisionLenghYears.
     */
    public String getSupervisionLengthYears()
    {
        return supervisionLengthYears;
    }

    /**
     * @return
     */
    public Date getSupervisionOrderBeginDate()
    {
        return supervisionOrderBeginDate;
    }

    /**
     * @return
     */
    public Date getSupervisionOrderEndDate()
    {
        return supervisionOrderEndDate;
    }

    /**
     * @return Returns the supOrderRevisionDate.
     */
    public Date getSupOrderRevisionDate()
    {
        return supOrderRevisionDate;
    }

    /**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
     * @return
     */
    public int getVersionNum()
    {
        return versionNum;
    }

    /**
     * @return
     */
    public String getVersionType()
    {
        return versionType;
    }

    /**
     * @return
     */
    public String getVersionTypeId()
    {
        return versionTypeId;
    }

    /**
     * @param collection
     */
    public void insertLikeConditionId(String conditionId)
    {
        likeConditionIds.add(conditionId);
    }

    /**
     * @return
     */
    public boolean isLimitedSupervisionPeriod()
    {
        return limitedSupervisionPeriod;
    }

    public boolean isMigratedOrder() {
		return isMigratedOrder;
	}

    /**
	 * @return Returns the outOfCountyCase.
	 */
	public boolean isOutOfCountyCase() {
		return outOfCountyCase;
	}

    public boolean isSignedByDefendant() {
		return isSignedByDefendant;
	}

    /**
     * @param string
     */
    public void setCaseActivityRuleStatus(String string)
    {
        caseActivityRuleStatus = string;
    }

    /**
     * @param aDate
     */
    public void setCaseFileDate(Date aDate)
    {
        caseFileDate = aDate;
    }

    /**
     * @param aCaseNum
     */
    public void setCaseNum(String aCaseNum)
    {
        caseNum = aCaseNum;
    }

    /**
     * @param aCdi
     */
    public void setCdi(String aCdi)
    {
        cdi = aCdi;
    }

    public void setComments(String comments) {
		this.comments = comments;
	}

    /**
     * @param confinementLengthDays
     *            The confinementLengthDays to set.
     */
    public void setConfinementLengthDays(String confinementLengthDays)
    {
        this.confinementLengthDays = confinementLengthDays;
    }

    /**
     * @param confinementLengthMonths
     *            The confinementLengthMonths to set.
     */
    public void setConfinementLengthMonths(String confinementLengthMonths)
    {
        this.confinementLengthMonths = confinementLengthMonths;
    }

    /**
     * @param confinementLengthYears
     *            The confinementLengthYears to set.
     */
    public void setConfinementLengthYears(String confinementLengthYears)
    {
        this.confinementLengthYears = confinementLengthYears;
    }

    /**
     * @param aConnectionId
     */
    public void setConnectionId(String aConnectionId)
    {
        connectionId = aConnectionId;
    }

    /**
     * @param string
     */
    public void setCourtCategory(String string)
    {
        courtCategory = string;
    }

    /**
     * @param aCourtId
     */
    public void setCourtId(String aCourtId)
    {
        courtId = aCourtId;
    }

    /**
     * @param aCourtNum
     */
    public void setCourtNum(String aCourtNum)
    {
        courtNum = aCourtNum;
    }

    /**
     * @param string
     */
    public void setDeferredSupervisionLength(String string)
    {
        deferredSupervisionLength = string;
    }

    public void setDispositionTypeId(String dispositionTypeId) {
		this.dispositionTypeId = dispositionTypeId;
	}

    /**
     * @param string
     */
    public void setFineAmountTotal(double aDouble)
    {
        fineAmountTotal = aDouble;
    }

    /**
     * @param isHistoricalOrder The isHistoricalOrder to set.
     */
    public void setIsHistoricalOrder(boolean isHistoricalOrder) {
        this.isHistoricalOrder = isHistoricalOrder;
    }

    /**
     * @param string
     */
    public void setJailTime(String string)
    {
        jailTime = string;
    }

    public void setJudgeFirstName(String judgeFirstName) {
		this.judgeFirstName = judgeFirstName;
	}

    public void setJudgeLastName(String judgeLastName) {
		this.judgeLastName = judgeLastName;
	}

    /**
     * @param string
     */
    public void setJudgeName(String string)
    {
        judgeName = string;
    }

    public void setJuvenileCourtId(String juvenileCourtId) {
		this.juvenileCourtId = juvenileCourtId;
	}

    /**
	 * @param juvSupervisionLengthDays the juvSupervisionLengthDays to set
	 */
	public void setJuvSupervisionLengthDays(String juvSupervisionLengthDays) {
		this.juvSupervisionLengthDays = juvSupervisionLengthDays;
	}

	/**
	 * @param juvSupervisionLengthMonths the juvSupervisionLengthMonths to set
	 */
	public void setJuvSupervisionLengthMonths(String juvSupervisionLengthMonths) {
		this.juvSupervisionLengthMonths = juvSupervisionLengthMonths;
	}

	/**
	 * @param juvSupervisionLengthYears the juvSupervisionLengthYears to set
	 */
	public void setJuvSupervisionLengthYears(String juvSupervisionLengthYears) {
		this.juvSupervisionLengthYears = juvSupervisionLengthYears;
	}

	/**
	 * @param juvSupervisionOrderBeginDate the juvSupervisionOrderBeginDate to set
	 */
	public void setJuvSupervisionOrderBeginDate(Date juvSupervisionOrderBeginDate) {
		this.juvSupervisionOrderBeginDate = juvSupervisionOrderBeginDate;
	}

	/**
     * @param collection
     */
    public void setLikeConditionIds(Collection collection)
    {
        likeConditionIds = collection;
    }

    /**
     * @param aLikeConditionInd
     */
    public void setLikeConditionInd(boolean aLikeConditionInd)
    {
        likeConditionInd = aLikeConditionInd;
    }

    /**
     * @param b
     */
    public void setLimitedSupervisionPeriod(boolean b)
    {
        limitedSupervisionPeriod = b;
    }
    public void setMigratedOrder(boolean isMigratedOrder) {
		this.isMigratedOrder = isMigratedOrder;
	}

    /**
     * @param string
     */
    public void setModificationReason(String string)
    {
        modificationReason = string;
    }

    /**
     * @param aName
     */
    public void setName(String aName)
    {
        name = aName;
    }

    /**
     * @param anOffense
     */
    public void setOffense(String anOffense)
    {
        offense = anOffense;
    }

    /**
     * @param anOffenseId
     */
    public void setOffenseId(String anOffenseId)
    {
        offenseId = anOffenseId;
    }

    /**
     * @param offenseLevel
     *            The offenseLevel to set.
     */
    public void setOffenseLevel(String offenseLevel)
    {
        this.offenseLevel = offenseLevel;
    }

    /**
	 * @param orderChainNum The orderChainNum to set.
	 */
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}

    /**
     * @param aDate
     */
    public void setOrderFileDate(Date aDate)
    {
        orderFileDate = aDate;
    }
    
    /**
     * @param aDate
     */
    public void setStatusChangeDate(Date aDate)
    {
        statusChangeDate = aDate;
    }

    /**
     * @param anOrderId
     */
    public void setOrderId(String anOrderId)
    {
        orderId = anOrderId;
    }

    /**
     * @param orderPresentorBy
     *            The orderPresentorBy to set.
     */
    public void setOrderPresentorBy(String orderPresentorId)
    {
        this.orderPresentorBy = orderPresentorId;
    }

    /**
     * @param theOrderStatus
     */
    public void setOrderStatus(String theOrderStatus)
    {
        orderStatus = theOrderStatus;
    }

    /**
     * @param aStatusId
     */
    public void setOrderStatusId(String aStatusId)
    {
        orderStatusId = aStatusId;
    }

    /**
     * @param string
     */
    public void setOrderTitle(String string)
    {
        orderTitle = string;
    }

    /**
     * @param string
     */
    public void setOrderTitleId(String string)
    {
        orderTitleId = string;
    }

    /**
     * @param aVersion
     */
    public void setOrderVersion(String aVersion)
    {
        orderVersion = aVersion;
    }

    public void setOrigJudgeFirstName(String origJudgeFirstName) {
		this.origJudgeFirstName = origJudgeFirstName;
	}

    public void setOrigJudgeLastName(String origJudgeLastName) {
		this.origJudgeLastName = origJudgeLastName;
	}

    /**
	 * @param outOfCountyCase The outOfCountyCase to set.
	 */
	public void setOutOfCountyCase(boolean outOfCountyCase) {
		this.outOfCountyCase = outOfCountyCase;
	}

    /**
     * @param string
     */
    public void setPlea(String string)
    {
        plea = string;
    }

    public void setPresentedByFirstName(String presentedByFirstName) {
		this.presentedByFirstName = presentedByFirstName;
	}

    public void setPresentedByLastName(String presentedByLastName) {
		this.presentedByLastName = presentedByLastName;
	}

    public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

    public void setPrintedOffenseDesc(String printedOffenseDesc) {
		this.printedOffenseDesc = printedOffenseDesc;
	}

    /**
     * @param string
     */
    public void setProbationEndDate(Date aDate)
    {
        probationEndDate = aDate;
    }

    /**
     * @param string
     */
    public void setProbationStartDate(Date aDate)
    {
        probationStartDate = aDate;
    }

    public void setSignedByDefendant(boolean isSignedByDefendant) {
		this.isSignedByDefendant = isSignedByDefendant;
	}

    /**
     * @param signedByDefendantDate
     *            The signedByDefendantDate to set.
     */
    public void setSignedByDefendantDate(Date signedByDefendantDate)
    {
        this.signedByDefendantDate = signedByDefendantDate;
    }

    public void setSignedByJudgeDate(Date signedByJudgeDate) {
		this.signedByJudgeDate = signedByJudgeDate;
	}

	public Date getSignedByJudgeDate() {
		return signedByJudgeDate;
	}

	/**
     * @param signedByJudgeDate
     *            The signedByJudgeDate to set.
     */
    public void setSignedByOfficerDate(Date signedByOfficerDate)
    {
        this.signedByOfficerDate = signedByOfficerDate;
    }

    public void setSpecialCourtCd(String specialCourtCd) {
		this.specialCourtCd = specialCourtCd;
	}

    /**
     * @param string
     */
    public void setSpn(String string)
    {
        spn = string;
    }

    /**
     * @param string
     */
    public void setSuggestedOrderId(String string)
    {
        suggestedOrderId = string;
    }

    /**
     * @param string
     */
    public void setSummaryChanges(String string)
    {
        summaryChanges = string;
    }

    /**
     * @param supervisionLengthDays
     *            The supervisionLengthDays to set.
     */
    public void setSupervisionLengthDays(String supervisionLengthDays)
    {
        this.supervisionLengthDays = supervisionLengthDays;
    }

    /**
     * @param supervisionLengthMonths
     *            The supervisionLengthMonths to set.
     */
    public void setSupervisionLengthMonths(String supervisionLengthMonths)
    {
        this.supervisionLengthMonths = supervisionLengthMonths;
    }

    /**
     * @param string
     */
    public void setSupervisionLengthNum(String string)
    {
        supervisionLengthNum = string;
    }

    /**
     * @param supervisionLengthYears
     *            The supervisionLengthYears to set.
     */
    public void setSupervisionLengthYears(String supervisionLengthYears)
    {
        this.supervisionLengthYears = supervisionLengthYears;
    }

	
	/**
     * @param date
     */
    public void setSupervisionOrderBeginDate(Date date)
    {
        supervisionOrderBeginDate = date;
    }
	/**
     * @param date
     */
    public void setSupervisionOrderEndDate(Date date)
    {
        supervisionOrderEndDate = date;
    }
	/**
     * @param supOrderRevisionDate
     *            The supOrderRevisionDate to set.
     */
    public void setSupOrderRevisionDate(Date supOrderRevisionDate)
    {
        this.supOrderRevisionDate = supOrderRevisionDate;
    }

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
	/**
     * @param aVersionNum
     */
    public void setVersionNum(int aVersionNum)
    {
        versionNum = aVersionNum;
    }

	/**
     * @param string
     */
    public void setVersionType(String string)
    {
        versionType = string;
    }

	/**
     * @param aVersionType
     */
    public void setVersionTypeId(String aVersionType)
    {
        versionTypeId = aVersionType;
    }

	public String getCurrentCourtCategory() {
		return currentCourtCategory;
	}

	public void setCurrentCourtCategory(String currentCourtCategory) {
		this.currentCourtCategory = currentCourtCategory;
	}

	public String getCurrentCourtId() {
		return currentCourtId;
	}

	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}

	public String getCurrentCourtNum() {
		return currentCourtNum;
	}

	public void setCurrentCourtNum(String currentCourtNum) {
		this.currentCourtNum = currentCourtNum;
	}
}
