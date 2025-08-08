//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\CreateSupervisionOrderEvent.java

package messaging.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import mojo.km.messaging.RequestEvent;

public class CreateSupervisionOrderEvent extends RequestEvent 
{
	public int getJuvSupervisionLengthDays() {
		return juvSupervisionLengthDays;
	}
	public void setJuvSupervisionLengthDays(int juvSupervisionLengthDays) {
		this.juvSupervisionLengthDays = juvSupervisionLengthDays;
	}
	public int getJuvSupervisionLengthMonths() {
		return juvSupervisionLengthMonths;
	}
	public void setJuvSupervisionLengthMonths(int juvSupervisionLengthMonths) {
		this.juvSupervisionLengthMonths = juvSupervisionLengthMonths;
	}
	public int getJuvSupervisionLengthYears() {
		return juvSupervisionLengthYears;
	}
	public void setJuvSupervisionLengthYears(int juvSupervisionLengthYears) {
		this.juvSupervisionLengthYears = juvSupervisionLengthYears;
	}
	public Date getJuvSupervisionBeginDate() {
		return juvSupervisionBeginDate;
	}
	public void setJuvSupervisionBeginDate(Date juvSupervisionBeginDate) {
		this.juvSupervisionBeginDate = juvSupervisionBeginDate;
	}

	private String agencyId;
	private Date caseSupervisionBeginDate;

	private Date caseSupervisionEndDate;
	private String currentCourtId;
	private String comments;

	private int confinementLengthDays;
	private int confinementLengthMonths;
	private int confinementLengthYears;
	private String criminalCaseId;
	private String defendantId;
	//The following will be populated only when the "Print Signature" button is pressed.
	private Date defendantSignatureDate;
	private boolean defendantSignatureInd;
	private Date judgeSignatureDate;
	private String dispositionTypeId;
	private double fineAmount;

	private Collection histOrderConditions;

	private String historicalOrderStatus;

	private boolean isHistoricalOrder;

	private boolean isMigratedOrder;

	private String juvCourtId;
	private int juvSupervisionLengthDays;
	private int juvSupervisionLengthMonths;
	private int juvSupervisionLengthYears;
	private Date juvSupervisionBeginDate;
	private Date limitedSupervisionBeginDate;
	private Date limitedSupervisionEndDate;
	private boolean limitedSupervisionPeriod;
	
	private String modificationReason; //Needed for Historical orders. You can create an Amended and Modified on create

	private int orderChainNum;

	private String orderCourtId;

	private Date orderSignatureDate;

	private String orderTitle;

	private String origJudgeFirstName;

	private String origJudgeLastName;

	private String parentSupervisionOrderId; // To be checked   

	private String plea;

	private String presentorFirstName;
	private String presentorLastName;
	private String printedName;
	private String printedOffenseDesc;
	private String signorFirstName;
	private String signorLastName;
	private String specialCourtCd;
	private String status;
	private String suggestedOrderId;
	private String summaryOfChanges;  //needed when creating an amended or modified from migrated order
	private String supervisionId;
	private int supervisionLengthDays;
	private int supervisionLengthMonths;
	private int supervisionLengthYears;
	private String supervisionOrderId;
	private Map variableElementReferenceMap;
	private int versionNum; //for historical orders only
	private String versionType;
	/**
	  * @roseuid 43BECFBE0131
	*/
	public CreateSupervisionOrderEvent() 
	{
	    
	}
	/**
	 * @param collection
	 */
	public void addHistOrderCondition(SupervisionOrderConditionEvent conditionEvent) {
		if (histOrderConditions == null){
		    histOrderConditions = new ArrayList();
		}
		histOrderConditions.add(conditionEvent);
	}
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	/**
     * @return Returns the caseSupervisionBeginDate.
     */
    public Date getCaseSupervisionBeginDate() {
        return caseSupervisionBeginDate;
    }
	/**
     * @return Returns the caseSupervisionEndDate.
     */
    public Date getCaseSupervisionEndDate() {
        return caseSupervisionEndDate;
    }
	public String getComments() {
		return comments;
	}
	/**
	 * @return
	 */
	public Collection getConditions() {
		return histOrderConditions;
	}
	/**
     * @return Returns the confinementLengthDays.
     */
    public int getConfinementLengthDays() {
        return confinementLengthDays;
    }
	/**
     * @return Returns the confinementLengthMonths.
     */
    public int getConfinementLengthMonths() {
        return confinementLengthMonths;
    }
	/**
     * @return Returns the confinementLengthYears.
     */
    public int getConfinementLengthYears() {
        return confinementLengthYears;
    }
	/**
	 * @return
	 */
	public String getCriminalCaseId()
	{
		return criminalCaseId;
	}
	/**
	 * @return
	 */
	public String getDefendantId()
	{
		return defendantId;
	}	
	public Date getDefendantSignatureDate() {
		return defendantSignatureDate;
	}
	public void setJudgeSignatureDate(Date judgeSignatureDate) {
		this.judgeSignatureDate = judgeSignatureDate;
	}
	public Date getJudgeSignatureDate() {
		return judgeSignatureDate;
	}
	/**
     * @return Returns the dispositionTypeId.
     */
    public String getDispositionTypeId() {
        return dispositionTypeId;
    }
	/**
     * @return Returns the fineAmount.
     */
    public double getFineAmount() {
        return fineAmount;
    }
	/**
     * @return Returns the histOrderConditions.
     */
    public Collection getHistOrderConditions() {
        return histOrderConditions;
    }
	/**
     * @return Returns the historicalOrderStatus.
     */
    public String getHistoricalOrderStatus() {
        return historicalOrderStatus;
    }
	/**
     * @return Returns the isHistoricalOrder.
     */
    public boolean getIsHistoricalOrder() {
        return isHistoricalOrder;
    }
	/**
	 * @return the juvCourtId
	 */
	public String getJuvCourtId() {
		return juvCourtId;
	}
	/**
	 * @return
	 */
	public Date getLimitedSupervisionBeginDate()
	{
		return limitedSupervisionBeginDate;
	}
	/**
	 * @return
	 */
	public Date getLimitedSupervisionEndDate()
	{
		return limitedSupervisionEndDate;
	}    
	/**
	 * @return
	 */
	public boolean getLimitedSupervisionPeriod()
	{
		return limitedSupervisionPeriod;
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
	public String getOrderCourtId()
	{
		return orderCourtId;
	}
	public Date getOrderSignatureDate() {
		return orderSignatureDate;
	}
	/**
	 * @return
	 */
	public String getOrderTitle()
	{
		return orderTitle;
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
	public String getParentSupervisionOrderId()
	{
		return parentSupervisionOrderId;
	}

	/**
	 * @return Returns the plea.
	 */
	public String getPlea() {
		return plea;
	}
    public String getPresentorFirstName() {
		return presentorFirstName;
	}
    public String getPresentorLastName() {
		return presentorLastName;
	}
    public String getPrintedName() {
		return printedName;
	}
    public String getPrintedOffenseDesc() {
		return printedOffenseDesc;
	}
    public String getSignorFirstName() {
		return signorFirstName;
	}
    public String getSignorLastName() {
		return signorLastName;
	}

	public String getSpecialCourtCd() {
		return specialCourtCd;
	}
    /**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}
    /**
	 * @return
	 */
	public String getSuggestedOrderId()
	{
		return suggestedOrderId;
	}
	
    public String getSummaryOfChanges() {
		return summaryOfChanges;
	}

	/**
	 * @return
	 */
	public String getSupervisionId()
	{
		return supervisionId;
	}

	/**
     * @return Returns the supervisionLengthDays.
     */
    public int getSupervisionLengthDays() {
        return supervisionLengthDays;
    }

	/**
     * @return Returns the supervisionLengthMonths.
     */
    public int getSupervisionLengthMonths() {
        return supervisionLengthMonths;
    }
    /**
     * @return Returns the supervisionLengthYears.
     */
    public int getSupervisionLengthYears() {
        return supervisionLengthYears;
    }

	/**
	 * @return Returns the supervisionOrderId.
	 */
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}

	/**
	 * @return
	 */
	public Map getVariableElementReferenceMap()
	{
		return variableElementReferenceMap;
	}

	/**
     * @return Returns the versionNum.
     */
    public int getVersionNum() {
        return versionNum;
    }
	/**
	 * @return
	 */
	public String getVersionType()
	{
		return versionType;
	}

	public boolean isDefendantSignatureInd() {
		return defendantSignatureInd;
	}

	public boolean isMigratedOrder() {
		return isMigratedOrder;
	}
    /**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}
    /**
     * @param caseSupervisionBeginDate The caseSupervisionBeginDate to set.
     */
    public void setCaseSupervisionBeginDate(Date caseSupervisionBeginDate) {
        this.caseSupervisionBeginDate = caseSupervisionBeginDate;
    }
    /**
     * @param caseSupervisionEndDate The caseSupervisionEndDate to set.
     */
    public void setCaseSupervisionEndDate(Date caseSupervisionEndDate) {
        this.caseSupervisionEndDate = caseSupervisionEndDate;
    }
    public void setComments(String comments) {
		this.comments = comments;
	}

	/**
     * @param confinementLengthDays The confinementLengthDays to set.
     */
    public void setConfinementLengthDays(int confinementLengthDays) {
        this.confinementLengthDays = confinementLengthDays;
    }
    /**
     * @param confinementLengthMonths The confinementLengthMonths to set.
     */
    public void setConfinementLengthMonths(int confinementLengthMonths) {
        this.confinementLengthMonths = confinementLengthMonths;
    }
    /**
     * @param confinementLengthYears The confinementLengthYears to set.
     */
    public void setConfinementLengthYears(int confinementLengthYears) {
        this.confinementLengthYears = confinementLengthYears;
    }

	/**
	 * @param string
	 */
	public void setCriminalCaseId(String string)
	{
		criminalCaseId = string;
	}

	/**
	 * @param string
	 */
	public void setDefendantId(String string)
	{
		defendantId = string;
	}
    public void setDefendantSignatureDate(Date defendantSignatureDate) {
		this.defendantSignatureDate = defendantSignatureDate;
	}
    public void setDefendantSignatureInd(boolean defendantSignatureInd) {
		this.defendantSignatureInd = defendantSignatureInd;
	}
    /**
     * @param dispositionTypeId The dispositionTypeId to set.
     */
    public void setDispositionTypeId(String dispositionTypeId) {
        this.dispositionTypeId = dispositionTypeId;
    }
    /**
     * @param fineAmount The fineAmount to set.
     */
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
    /**
     * @param histOrderConditions The histOrderConditions to set.
     */
    public void setHistOrderConditions(Collection histOrderConditions) {
        this.histOrderConditions = histOrderConditions;
    }
    /**
     * @param isHistoricalOrder The isHistoricalOrder to set.
     */
    public void setHistoricalOrder(boolean isHistoricalOrder) {
        this.isHistoricalOrder = isHistoricalOrder;
    }

	/**
     * @param historicalOrderStatus The historicalOrderStatus to set.
     */
    public void setHistoricalOrderStatus(String historicalOrderStatus) {
        this.historicalOrderStatus = historicalOrderStatus;
    }
    /**
	 * @param juvCourtId the juvCourtId to set
	 */
	public void setJuvCourtId(String juvCourtId) {
		this.juvCourtId = juvCourtId;
	}
	/**
	 * @param date
	 */
	public void setLimitedSupervisionBeginDate(Date date)
	{
		limitedSupervisionBeginDate = date;
	}
    /**
	 * @param date
	 */
	public void setLimitedSupervisionEndDate(Date date)
	{
		limitedSupervisionEndDate = date;
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
	 * 
	 * @return
	 */
	public String getModificationReason() {
		return modificationReason;
	}
	
	/**
	 * Needed for Historical orders
	 * @param modificationReason
	 */
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}

	/**
	 * @param orderChainNum The orderChainNum to set.
	 */
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}
    /**
	 * @param string
	 */
	public void setOrderCourtId(String string)
	{
		orderCourtId = string;
	}

	public void setOrderSignatureDate(Date orderSignatureDate) {
		this.orderSignatureDate = orderSignatureDate;
	}

	/**
	 * @param string
	 */
	public void setOrderTitle(String string)
	{
		orderTitle = string;
	}

	public void setOrigJudgeFirstName(String origJudgeFirstName) {
		this.origJudgeFirstName = origJudgeFirstName;
	}
	public void setOrigJudgeLastName(String origJudgeLastName) {
		this.origJudgeLastName = origJudgeLastName;
	}

	/**
	 * @param string
	 */
	public void setParentSupervisionOrderId(String string)
	{
		parentSupervisionOrderId = string;
	}

	/**
	 * @param plea The plea to set.
	 */
	public void setPlea(String plea) {
		this.plea = plea;
	}
    public void setPresentorFirstName(String presentorFirstName) {
		this.presentorFirstName = presentorFirstName;
	}
    public void setPresentorLastName(String presentorLastName) {
		this.presentorLastName = presentorLastName;
	}
    public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}
    public void setPrintedOffenseDesc(String printedOffenseDesc) {
		this.printedOffenseDesc = printedOffenseDesc;
	}
	public void setSignorFirstName(String signorFirstName) {
		this.signorFirstName = signorFirstName;
	}
    public void setSignorLastName(String signorLastName) {
		this.signorLastName = signorLastName;
	}
    public void setSpecialCourtCd(String specialCourtCd) {
		this.specialCourtCd = specialCourtCd;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}
	/**
	 * @param string
	 */
	public void setSuggestedOrderId(String string)
	{
		suggestedOrderId = string;
	}
	public void setSummaryOfChanges(String summaryOfChanges) {
		this.summaryOfChanges = summaryOfChanges;
	}

    /**
	 * @param string
	 */
	public void setSupervisionId(String string)
	{
		supervisionId = string;
	}
    /**
     * @param supervisionLengthDays The supervisionLengthDays to set.
     */
    public void setSupervisionLengthDays(int supervisionLengthDays) {
        this.supervisionLengthDays = supervisionLengthDays;
    }
    /**
     * @param supervisionLengthMonths The supervisionLengthMonths to set.
     */
    public void setSupervisionLengthMonths(int supervisionLengthMonths) {
        this.supervisionLengthMonths = supervisionLengthMonths;
    }
    /**
     * @param supervisionLengthYears The supervisionLengthYears to set.
     */
    public void setSupervisionLengthYears(int supervisionLengthYears) {
        this.supervisionLengthYears = supervisionLengthYears;
    }
	/**
	 * @param supervisionOrderId The supervisionOrderId to set.
	 */
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
	/**
	 * @param map
	 */
	public void setVariableElementReferenceMap(Map map)
	{
		variableElementReferenceMap = map;
	}

	/**
     * @param versionNum The versionNum to set.
     */
    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

	/**
	 * @param string
	 */
	public void setVersionType(String string)
	{
		versionType = string;
	}
	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}
	public String getCurrentCourtId() {
		return currentCourtId;
	}
}
