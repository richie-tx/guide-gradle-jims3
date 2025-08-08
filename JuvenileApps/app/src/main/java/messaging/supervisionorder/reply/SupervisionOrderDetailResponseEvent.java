/*
 * Created on Feb 23, 2006
 *
 */
package messaging.supervisionorder.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import naming.UIConstants;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *  
 */
public class SupervisionOrderDetailResponseEvent extends ResponseEvent
		implements Comparable {
	private Date activationDate;
	private String agencyId;

	private Date caseFileDate;
	private String caseNum;

	private Date caseSupervisionBeginDate=null; //dag 01/19/07
	private Date caseSupervisionEndDate=null;//dag 01/19/07
	private String cdi;

	private String comments;
	private Collection conditions = new ArrayList();

	private String confinementLengthDays="";//dag 01/19/07

	private String confinementLengthMonths="";//dag 01/19/07

	private String confinementLengthYears="";//dag 01/19/07

	private String orderCourtCategory; //added by Kiran

	private String orderCourtId;
	private String orderCourtNum;
	private String currentCourtCategory;
	private String currentCourtId;
	private String currentCourtNum;

	private String criminalCaseid;

	private String defendantId;
	
	private Name defendantName;
	
	private String defendantSignature; //cws 3/19/10
	 
	private String deferredSupervisionLength;
	
	public String dispositionType="";

	private String dispositionTypeId="";//dag 01/19/07

	private String fineAmountTotal;

	private boolean isHistoricalOrder=false;

	private boolean isMigrated;

	private String jailTime;

	private String judgeId;

	private String juvenileCourtId;
	
	private String juvSupervisionLengthDays=""; //cws 10/15/09
	
	private String juvSupervisionLengthMonths=""; //cws 10/15/09

	private String juvSupervisionLengthYears=""; //cws 10/15/09
	
	private String juvSupervisionLengthString; //rck 10/29/09

	private Date juvSupervisionOrderBeginDate; //cws 10/15/09

	private boolean limitedSupervisionPeriod;

	private String magistrateId;

	private String modificationReason;

	private String offense;

	private String offenseId;

	private String offenseLevel;
	
	private int orderChainNum;

	private Date orderFileDate;

	private String orderId;

	private boolean orderInProgress;

	private String orderPresentorId;

	private Name orderPresentorName;
	
	private Date orderSignedDate;

	private String orderStatusId;

	private String orderTitle;

	private String orderTitleId;

	private String orderVersion;

	private Name origJudgeName;

	private boolean outOfCountyCase=false;

	private String parentSupervisionOrderId;

	private String plea;

	private String printedName;

	private String printedOffenseDesc;
	
	private boolean printSpanish;

	private String printTemplateId;

	private String probationEndDate;

	private String probationStartDate;

	private boolean signedByDefendant=false;

	private Date signedByDefendantDate;
	
	private Date signedByJudgeDate;

	private Name signedByName;

	private String signedByTypeId;

	private String specialCourtCd;
	
	private String specialCourtDesc; //cws 3/19/10
	
	private String suggestedOrderId;
	
	private String summaryOfChanges;

	private String supervisionLengthDays="";//dag 01/19/07

	private String supervisionLengthMonths="";//dag 01/19/07

	private String supervisionLengthNum;

	private String supervisionLengthYears="";//dag 01/19/07

	private Date supervisionOrderBeginDate;

	private Date supervisionOrderEndDate;

	private String supervisionPeriodId;

	private Date supOrderRevisionDate;

	private Date updateDate;
	private Date statusChangeDate; 

	private int versionNum;
	private String versionType;

	private String versionTypeId;
	
	private String whoSignedOrder; //cws 3/19/10
	
	public int compareTo(Object obj) throws ClassCastException {
		SupervisionOrderDetailResponseEvent evt = (SupervisionOrderDetailResponseEvent) obj;
		Date curDate = new Date();
		if (activationDate == null && evt.getActivationDate() == null) {
			return 0;
		} else if (activationDate == null) {
			return evt.getActivationDate().compareTo(curDate);
		} else if (evt.getActivationDate() == null) {
			return curDate.compareTo(activationDate);
		} else {
			return evt.getActivationDate().compareTo(activationDate);
		}
	}
    /**
	 * @return
	 */
	public Date getActivationDate() {
		return activationDate;
	}
    /**
	 * @return
	 */
	public String getAgencyId() {
		return agencyId;
	}
    /**
	 * @return Returns the caseFileDate.
	 */
	public Date getCaseFileDate() {
		return caseFileDate;
	}
    /**
	 * @return
	 */
	public String getCaseNum() {
		// TODO check this logic
		if (this.criminalCaseid != null && !this.criminalCaseid.equals("")) {
			caseNum = criminalCaseid.substring(3);
		}
		return caseNum;
	}
    /**
	 * @return Returns the caseSupervisionBeginDate.
	 */
	public Date getCaseSupervisionBeginDate() {
		return caseSupervisionBeginDate;
	}
    /**
     * @return
     */
    public String getCaseSupervisionBeginDateAsString() {
        if (caseSupervisionBeginDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(caseSupervisionBeginDate, UIConstants.DATE_FMT_1);
        }
    }
    /**
	 * @return Returns the caseSupervisionEndDate.
	 */
	public Date getCaseSupervisionEndDate() {
		return caseSupervisionEndDate;
	}
    /**
     * @return
     */
    public String getCaseSupervisionEndDateAsString() {
        if (caseSupervisionEndDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(caseSupervisionEndDate, UIConstants.DATE_FMT_1);
        }
    }
    /**
	 * @return
	 */
	public String getCdi() {
		return cdi;
	}

    public String getComments() {
		return comments;
	}
    
	/**
	 * @return
	 */
	public Collection getConditions() {
		Collections.sort((List) conditions);
		return conditions;
	}
	/**
	 * @return Returns the confinementLengthDays.
	 */
	public String getConfinementLengthDays() {
		return confinementLengthDays;
	}
    
    /**
	 * @return Returns the confinementLengthMonths.
	 */
	public String getConfinementLengthMonths() {
		return confinementLengthMonths;
	}

    /**
	 * @return Returns the confinementLengthYears.
	 */
	public String getConfinementLengthYears() {
		return confinementLengthYears;
	}
    
	/**
	 * @return Returns the courtCategory.
	 */
	public String getOrderCourtCategory() {
		return orderCourtCategory;
	}

	/**
	 * @return
	 */
	public String getOrderCourtId() {
		return orderCourtId;
	}

	/**
	 * @return Returns the courtNum.
	 */
	public String getOrderCourtNum() {
		return orderCourtNum;
	}

	/**
	 * @return
	 */
	public String getCriminalCaseid() {
		return criminalCaseid;
	}

	/**
	 * @return
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @return
	 */
	public Name getDefendantName() {
		return defendantName;
	}

	/**
	 * @return the defendantSignature
	 */
	public String getDefendantSignature() {
		return defendantSignature;
	}
	/**
	 * @return Returns the deferredSupervisionLength.
	 */
	public String getDeferredSupervisionLength() {
		return deferredSupervisionLength;
	}

	/**
	 * @return Returns the dispositionType.
	 */
	public String getDispositionType() {
		return dispositionType;
	}

	/**
	 * @return Returns the dispositionTypeId.
	 */
	public String getDispositionTypeId() {
		if(dispositionTypeId==null)
			return "";
		return dispositionTypeId;
	}

	/**
	 * @return Returns the fineAmountTotal.
	 */
	public String getFineAmountTotal() {
		return fineAmountTotal;
	}

	/**
	 * @return Returns the jailTime.
	 */
	public String getJailTime() {
		return jailTime;
	}

	/**
	 * @return
	 */
	public String getJudgeId() {
		return judgeId;
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
	 * @return the juvSupervisionLengthString
	 */
	public String getJuvSupervisionLengthString() {
		return juvSupervisionLengthString;
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
	public String getMagistrateId() {
		return magistrateId;
	}

	/**
	 * @return
	 */
	public String getModificationReason() {
		return modificationReason;
	}

	/**
	 * @return Returns the offense.
	 */
	public String getOffense() {
		return offense;
	}

	/**
	 * @return Returns the offenseId.
	 */
	public String getOffenseId() {
		return offenseId;
	}

	/**
	 * @return Returns the offenseLevel.
	 */
	public String getOffenseLevel() {
		return offenseLevel;
	}

	/**
	 * @return Returns the orderChainNum.
	 */
	public int getOrderChainNum() {
		return orderChainNum;
	}

	/**
	 * /**
	 * 
	 * @return
	 */
	public Date getOrderFileDate() {
		return orderFileDate;
	}

	/**
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @return
	 */
	public String getOrderPresentorId() {
		return orderPresentorId;
	}

	/**
	 * @return
	 */
	public Name getOrderPresentorName() {
		return orderPresentorName;
	}

	/**
	 * @return
	 */
	public Date getOrderSignedDate() {
		return orderSignedDate;
	}

	/**
	 * @return
	 */
	public String getOrderStatusId() {
		return orderStatusId;
	}

	/**
	 * @return
	 */
	public String getOrderTitle() {
		return orderTitle;
	}

	/**
	 * @return
	 */
	public String getOrderTitleId() {
		return orderTitleId;
	}

	/**
	 * @return
	 */
	public String getOrderVersion() {
		return orderVersion;
	}

	public Name getOrigJudgeName() {
		return origJudgeName;
	}

	/**
	 * @return
	 */
	public String getParentSupervisionOrderId() {
		return parentSupervisionOrderId;
	}

	/**
	 * @return Returns the plea.
	 */
	public String getPlea() {
		return plea;
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
	public String getPrintTemplateId() {
		return printTemplateId;
	}

	/**
	 * @return
	 */
	public String getProbationEndDate() {
		return probationEndDate;
	}

	/**
	 * @return
	 */
	public String getProbationStartDate() {
		return probationStartDate;
	}

	/**
	 * @return Returns the signedByDefendantDate.
	 */
	public Date getSignedByDefendantDate() {
		return signedByDefendantDate;
	}

	/**
	 * @return
	 */
	public Name getSignedByName() {
		return signedByName;
	}

	/**
	 * @return
	 */
	public String getSignedByTypeId() {
		return signedByTypeId;
	}

	public String getSpecialCourtCd() {
		return specialCourtCd;
	}
	/**
	 * @return Returns the specialCourtDesc.
	 */
	public String getSpecialCourtDesc() {
		return specialCourtDesc;
	}
	/**
	 * @return
	 */
	public String getSuggestedOrderId() {
		return suggestedOrderId;
	}

	/**
	 * @return Returns the summaryOfChanges.
	 */
	public String getSummaryOfChanges() {
		return summaryOfChanges;
	}

	/**
	 * @return
	 */
	public String getSupervisionId() {
		return orderId;
	}

	/**
	 * @return Returns the supervisionLengthDays.
	 */
	public String getSupervisionLengthDays() {
		return supervisionLengthDays;
	}

	/**
	 * @return Returns the supervisionLengthMonths.
	 */
	public String getSupervisionLengthMonths() {
		return supervisionLengthMonths;
	}

	/**
	 * @return Returns the supervisionLengthNum.
	 */
	public String getSupervisionLengthNum() {
		return supervisionLengthNum;
	}

	/**
	 * @return Returns the supervisionLengthYears.
	 */
	public String getSupervisionLengthYears() {
		return supervisionLengthYears;
	}

	/**
	 * @return
	 */
	public Date getSupervisionOrderBeginDate() {
		return supervisionOrderBeginDate;
	}

	/**
	 * @return
	 */
	public Date getSupervisionOrderEndDate() {
		return supervisionOrderEndDate;
	}

	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}

	/**
	 * @return Returns the supOrderRevisionDate.
	 */
	public Date getSupOrderRevisionDate() {
		return supOrderRevisionDate;
	}

	/**
	 * @return
	 */
	public Date getUpdateDate() {
		return updateDate;
	}


	/**
	 * @return
	 */
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}

	/**
	 * @return
	 */
	public int getVersionNum() {
		return versionNum;
	}

	/**
	 * @return
	 */
	public String getVersionType() {
		return versionType;
	}

	/**
	 * @return
	 */
	public String getVersionTypeId() {
		return versionTypeId;
	}

	/**
	 * @return the whoSignedOrder
	 */
	public String getWhoSignedOrder() {
		return whoSignedOrder;
	}
	/**
	 * @param aCollection
	 */
	public void insertCondition(SupOrderConditionResponseEvent socre) {
		conditions.add(socre);
	}

	/**
	 * @return Returns the isHistoricalOrder.
	 */
	public boolean isHistoricalOrder() {
		return isHistoricalOrder;
	}

	/**
	 * @return
	 */
	public boolean isLimitedSupervisionPeriod() {
		return limitedSupervisionPeriod;
	}

	public boolean isMigrated() {
		return isMigrated;
	}

	/**
	 * @return
	 */
	public boolean isOrderInProgress() {
		return orderInProgress;
	}

	/**
	 * @return Returns the outOfCountyCase.
	 */
	public boolean isOutOfCountyCase() {
		return outOfCountyCase;
	}

	/**
	 * @return
	 */
	public boolean isPrintSpanish() {
		return printSpanish;
	}

	/**
	 * @return Returns the signedByDefendant.
	 */
	public boolean isSignedByDefendant() {
		return signedByDefendant;
	}

	/**
	 * @param date
	 */
	public void setActivationDate(Date date) {
		activationDate = date;
	}

	/**
	 * @param aString
	 */
	public void setAgencyId(String aString) {
		agencyId = aString;
	}

	/**
	 * @param caseFileDate
	 *            The caseFileDate to set.
	 */
	public void setCaseFileDate(Date caseFileDate) {
		this.caseFileDate = caseFileDate;
	}

	/**
	 * @param aString
	 */
	public void setCaseNum(String aString) {
		caseNum = aString;
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

	/**
	 * @param string
	 */
	public void setCdi(String string) {
		cdi = string;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @param aCollection
	 */
	public void setConditions(Collection aCollection) {
		conditions = aCollection;
	}

	/**
	 * @param confinementLengthDays The confinementLengthDays to set.
	 */
	public void setConfinementLengthDays(String confinementLengthDays) {
		this.confinementLengthDays = confinementLengthDays;
	}

	/**
	 * @param confinementLengthMonths The confinementLengthMonths to set.
	 */
	public void setConfinementLengthMonths(String confinementLengthMonths) {
		this.confinementLengthMonths = confinementLengthMonths;
	}

	/**
	 * @param confinementLengthYears The confinementLengthYears to set.
	 */
	public void setConfinementLengthYears(String confinementLengthYears) {
		this.confinementLengthYears = confinementLengthYears;
	}

	/**
	 * @param courtCategory
	 *            The courtCategory to set.
	 */
	public void setOrderCourtCategory(String courtCategory) {
		this.orderCourtCategory = courtCategory;
	}

	/**
	 * @param aString
	 */
	public void setOrderCourtId(String aString) {
		orderCourtId = aString;
	}

	/**
	 * @param courtNum
	 *            The courtNum to set.
	 */
	public void setOrderCourtNum(String courtNum) {
		this.orderCourtNum = courtNum;
	}

	/**
	 * @param string
	 */
	public void setCriminalCaseid(String string) {
		criminalCaseid = string;
	}

	/**
	 * @param aString
	 */
	public void setDefendantId(String aString) {
		defendantId = aString;
	}

	/**
	 * @param name
	 */
	public void setDefendantName(Name name) {
		defendantName = name;
	}

	/**
	 * @param defendantSignature the defendantSignature to set
	 */
	public void setDefendantSignature(String defendantSignature) {
		this.defendantSignature = defendantSignature;
	}
	/**
	 * @param deferredSupervisionLength
	 *            The deferredSupervisionLength to set.
	 */
	public void setDeferredSupervisionLength(String deferredSupervisionLength) {
		this.deferredSupervisionLength = deferredSupervisionLength;
	}

	/**
	 * @param dispositionType The dispositionType to set.
	 */
	public void setDispositionType(String dispositionType) {
		this.dispositionType = dispositionType;
	}

	/**
	 * @param dispositionTypeId The dispositionTypeId to set.
	 */
	public void setDispositionTypeId(String dispositionTypeId) {
		this.dispositionTypeId = dispositionTypeId;
	}

	/**
	 * @param fineAmountTotal
	 *            The fineAmountTotal to set.
	 */
	public void setFineAmountTotal(String fineAmountTotal) {
		this.fineAmountTotal = fineAmountTotal;
	}

	/**
	 * @param isHistoricalOrder The isHistoricalOrder to set.
	 */
	public void setHistoricalOrder(boolean isHistoricalOrder) {
		this.isHistoricalOrder = isHistoricalOrder;
	}

	/**
	 * @param jailTime
	 *            The jailTime to set.
	 */
	public void setJailTime(String jailTime) {
		this.jailTime = jailTime;
	}

	/**
	 * @param string
	 */
	public void setJudgeId(String string) {
		judgeId = string;
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
	 * @param juvSupervisionLengthString the juvSupervisionLengthString to set
	 */
	public void setJuvSupervisionLengthString(String juvSupervisionLengthString) {
		this.juvSupervisionLengthString = juvSupervisionLengthString;
	}
	
	/**
	 * @param juvSupervisionOrderBeginDate the juvSupervisionOrderBeginDate to set
	 */
	public void setJuvSupervisionOrderBeginDate(Date juvSupervisionOrderBeginDate) {
		this.juvSupervisionOrderBeginDate = juvSupervisionOrderBeginDate;
	}
	
	/**
	 * @param b
	 */
	public void setLimitedSupervisionPeriod(boolean b) {
		limitedSupervisionPeriod = b;
	}

	/**
	 * @param aString
	 */
	public void setMagistrateId(String aString) {
		magistrateId = aString;
	}

	public void setMigrated(boolean isMigrated) {
		this.isMigrated = isMigrated;
	}

	/**
	 * @param string
	 */
	public void setModificationReason(String string) {
		modificationReason = string;
	}

	/**
	 * @param offense
	 *            The offense to set.
	 */
	public void setOffense(String offense) {
		this.offense = offense;
	}

	/**
	 * @param offenseId
	 *            The offenseId to set.
	 */
	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}

	/**
	 * @param offenseLevel
	 *            The offenseLevel to set.
	 */
	public void setOffenseLevel(String offenseLevel) {
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
	public void setOrderFileDate(Date aDate) {
		orderFileDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setOrderId(String string) {
		orderId = string;
	}

	/**
	 * @param b
	 */
	public void setOrderInProgress(boolean b) {
		orderInProgress = b;
	}

	/**
	 * @param aString
	 */
	public void setOrderPresentorId(String aString) {
		orderPresentorId = aString;
	}

	/**
	 * @param name
	 */
	public void setOrderPresentorName(Name name) {
		orderPresentorName = name;
	}

	/**
	 * @param aDate
	 */
	public void setOrderSignedDate(Date aDate) {
		orderSignedDate = aDate;
	}

	/**
	 * @param aString
	 */
	public void setOrderStatusId(String aString) {
		orderStatusId = aString;
	}

	/**
	 * @param string
	 */
	public void setOrderTitle(String string) {
		orderTitle = string;
	}

	/**
	 * @param aString
	 */
	public void setOrderTitleId(String aString) {
		orderTitleId = aString;
	}

	/**
	 * @param string
	 */
	public void setOrderVersion(String string) {
		orderVersion = string;
	}
	public void setOrigJudgeName(Name origJudgeName) {
		this.origJudgeName = origJudgeName;
	}
	/**
	 * @param outOfCountyCase The outOfCountyCase to set.
	 */
	public void setOutOfCountyCase(boolean outOfCountyCase) {
		this.outOfCountyCase = outOfCountyCase;
	}
	/**
	 * @param aString
	 */
	public void setParentSupervisionOrderId(String aString) {
		parentSupervisionOrderId = aString;
	}
	/**
	 * @param plea
	 *            The plea to set.
	 */
	public void setPlea(String plea) {
		this.plea = plea;
	}
	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}
	public void setPrintedOffenseDesc(String printedOffenseDesc) {
		this.printedOffenseDesc = printedOffenseDesc;
	}
	/**
	 * @param b
	 */
	public void setPrintSpanish(boolean b) {
		printSpanish = b;
	}
	/**
	 * @param aString
	 */
	public void setPrintTemplateId(String aString) {
		printTemplateId = aString;
	}
	/**
	 * @param string
	 */
	public void setProbationEndDate(String string) {
		probationEndDate = string;
	}
	/**
	 * @param string
	 */
	public void setProbationStartDate(String string) {
		probationStartDate = string;
	}
	/**
	 * @param signedByDefendant The signedByDefendant to set.
	 */
	public void setSignedByDefendant(boolean signedByDefendant) {
		this.signedByDefendant = signedByDefendant;
	}
	/**
	 * @param signedByDefendantDate
	 *            The signedByDefendantDate to set.
	 */
	public void setSignedByDefendantDate(Date signedByDefendantDate) {
		this.signedByDefendantDate = signedByDefendantDate;
	}
	/**
	 * @param name
	 */
	public void setSignedByName(Name name) {
		signedByName = name;
	}
	/**
	 * @param aString
	 */
	public void setSignedByTypeId(String aString) {
		signedByTypeId = aString;
	}
	public void setSpecialCourtCd(String specialCourtCd) {
		this.specialCourtCd = specialCourtCd;
	}
	/**
	 * @param specialCourtDesc the specialCourtDesc to set
	 */
	public void setSpecialCourtDesc(String specialCourtDesc) {
		this.specialCourtDesc = specialCourtDesc;
	}
	/**
	 * @param aString
	 */
	public void setSuggestedOrderId(String aString) {
		suggestedOrderId = aString;
	}
	/**
	 * @param summaryOfChanges
	 *            The summaryOfChanges to set.
	 */
	public void setSummaryOfChanges(String summaryOfChanges) {
		this.summaryOfChanges = summaryOfChanges;
	}
	/**
	 * @param aString
	 */
	public void setSupervisionId(String aString) {
		orderId = aString;
	}
	/**
	 * @param supervisionLengthDays The supervisionLengthDays to set.
	 */
	public void setSupervisionLengthDays(String supervisionLengthDays) {
		this.supervisionLengthDays = supervisionLengthDays;
	}
	/**
	 * @param supervisionLengthMonths The supervisionLengthMonths to set.
	 */
	public void setSupervisionLengthMonths(String supervisionLengthMonths) {
		this.supervisionLengthMonths = supervisionLengthMonths;
	}
	/**
	 * @param supervisionLengthNum
	 *            The supervisionLengthNum to set.
	 */
	public void setSupervisionLengthNum(String supervisionLengthNum) {
		this.supervisionLengthNum = supervisionLengthNum;
	}
	/**
	 * @param supervisionLengthYears The supervisionLengthYears to set.
	 */
	public void setSupervisionLengthYears(String supervisionLengthYears) {
		this.supervisionLengthYears = supervisionLengthYears;
	}
	/**
	 * @param aDate
	 */
	public void setSupervisionOrderBeginDate(Date aDate) {
		supervisionOrderBeginDate = aDate;
	}
	/**
	 * @param aDate
	 */
	public void setSupervisionOrderEndDate(Date aDate) {
		supervisionOrderEndDate = aDate;
	}
	/**
	 * @param supervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
	/**
	 * @param supOrderRevisionDate
	 *            The supOrderRevisionDate to set.
	 */
	public void setSupOrderRevisionDate(Date supOrderRevisionDate) {
		this.supOrderRevisionDate = supOrderRevisionDate;
	}
	/**
	 * @param aDate
	 */
	public void setUpdateDate(Date aDate) {
		updateDate = aDate;
	}
	
	/**
	 * @param aDate
	 */
	public void setStatusChangeDate(Date aDate) {
		statusChangeDate = aDate;
	}
	
	/**
	 * @param i
	 */
	public void setVersionNum(int i) {
		versionNum = i;
	}

	/**
	 * @param string
	 */
	public void setVersionType(String string) {
		versionType = string;
	}

	/**
	 * @param aString
	 */
	public void setVersionTypeId(String aString) {
		versionTypeId = aString;
	}
	
	/**
	 * @param whoSignedOrder the whoSignedOrder to set
	 */
	public void setWhoSignedOrder(String whoSignedOrder) {
		this.whoSignedOrder = whoSignedOrder;
	}

	public static Comparator OrderIdComparator = new Comparator() {
		public int compare(Object orderVersion, Object previousOrderVersion) {
		  int orderId = Integer.parseInt(((SupervisionOrderDetailResponseEvent)orderVersion).getOrderId());
		  int previousOrderId = Integer.parseInt(((SupervisionOrderDetailResponseEvent)previousOrderVersion).getOrderId());
		  
		  Integer orderIdInt = new Integer(orderId);
		  Integer previousOrderIdInt = new Integer(previousOrderId);
		  return orderIdInt.compareTo(previousOrderIdInt);
		}	
	};
	
	/**
	 * sort based on the order status
	 */
	public static Comparator OrderStatusComparator = new Comparator() {
		public int compare(Object orderVersion, Object previousOrderVersion){
			String status = ((SupervisionOrderDetailResponseEvent)orderVersion).getOrderStatusId();
			String previousStatus = ((SupervisionOrderDetailResponseEvent)previousOrderVersion).getOrderStatusId();
			return status.compareTo(previousStatus);
		}
	};
	
	/**
	 * sort based on the order status and then the casesupervisionstartdate for superviseeheader dna calculation for probation start date
	 */
	public static Comparator OrderStatusProbationStartDateComparator = new Comparator() {
		public int compare(Object orderVersion, Object aternativeOrderVersion){
			int compareResult = 0;
			SupervisionOrderDetailResponseEvent orderDetaileEvent1 = ((SupervisionOrderDetailResponseEvent)orderVersion);
			SupervisionOrderDetailResponseEvent orderDetaileEvent2 = ((SupervisionOrderDetailResponseEvent)aternativeOrderVersion);
			if(orderDetaileEvent1 == null || orderDetaileEvent2 == null){
				return compareResult;
			}
			Date probationDate1 = orderDetaileEvent1.getCaseSupervisionBeginDate();
			Date probationDate2 = orderDetaileEvent2.getCaseSupervisionBeginDate();
			String orderStatus1 = orderDetaileEvent1.getOrderStatusId();
			String orderStatus2 = orderDetaileEvent2.getOrderStatusId();
			
			if(orderStatus1 != null && orderStatus2 != null){
				compareResult = orderStatus1.compareTo(orderStatus2);
			}			
			if(compareResult == 0 && probationDate1 != null && probationDate2 != null){
				compareResult = probationDate1.compareTo(probationDate2);
			}
			return compareResult;
		}
	};

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
	public String getCurrentCourtCategory() {
		return currentCourtCategory;
	}
	public void setCurrentCourtCategory(String currentCourtCategory) {
		this.currentCourtCategory = currentCourtCategory;
	}
	public void setSignedByJudgeDate(Date signedByJudgeDate) {
		this.signedByJudgeDate = signedByJudgeDate;
	}
	public Date getSignedByJudgeDate() {
		return signedByJudgeDate;
	}			
}