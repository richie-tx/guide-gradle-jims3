package pd.supervision.supervisionorder;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import messaging.supervisionorder.GetLatestSupervisionPeriodEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 44CFACF902E3
 */
public class SupervisionSplitOrder extends PersistentObject implements Comparable
{
	private Date supPeriodBeginDate;
	private Date supPeriodEndDate;
	private String agencyId;
    private String criminalCaseId;
    private String courtId;
    private Date caseBeginDate;
    private Date caseEndDate;
    private Date supOrderBeginDate;
    private Date supOrderEndDate;
    private String defendantId; 
    private String supOrderId;
    private String islimitedSupPeriod;
    private String versionNum;
    private Date orderSignedDate;
    private String versionTypeCd;
    private String signedByFName;
    private String signedByLName;
    private String printSupOrderId;
    private String supPrnTmplId;
    private String orderStatusCd;
    private String signedByTypeCd;
    private String isPrintSpanish;
    private String orderTitleCd;
    private Date orderFiledDate;
    private String isOrderinProgress;
    private String presentorFName;
    private String presentorLName;
    private Date activationDate;
    private String sugOrderId;
    private Date signedDate;
    private String modifyReason;
    private String summaryNotes;
    private Date orderSigByDefDate;
    private String isOrderSigByDef;
    private String withdrawReasonCd;
    private String currentCourtId;
    private String supPeriodId;
    private Date revisionDate;
    private Date inactiveDate;
    private String plea;
    
    
	/**
	 * Properties for previousSupervisionPeriod
	 * @referencedType pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private SupervisionSplitOrder previousSupervisionPeriod = null;
	private String previousSupervisionPeriodId;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		SupervisionSplitOrder sp = (SupervisionSplitOrder) arg0;
		Date curDate = new Date();
		if (supPeriodEndDate == null)
		{
			return sp.getSupPeriodEndDate().compareTo(curDate);
		}
		else if (sp.getSupPeriodEndDate() == null)
		{
			return curDate.compareTo(supPeriodEndDate);
		}
		else
		{
			return sp.getSupPeriodEndDate().compareTo(supPeriodEndDate);
		}
	}

	public static Comparator BeginDateComparator = new Comparator() {
		public int compare(Object supervisionPeriod, Object otherSupervisionPeriod) {
		  Date beginDate = ((SupervisionSplitOrder)supervisionPeriod).getCaseBeginDate();
		  Date otherBeginDate = ((SupervisionSplitOrder)otherSupervisionPeriod).getCaseBeginDate();
		  
		  return beginDate.compareTo(otherBeginDate);
		}	
	};

	public static Comparator SupervisionPeriodBeginDateComparator = new Comparator() {
		public int compare(Object supervisionPeriod, Object otherSupervisionPeriod) {
		  Date beginDate = ((SupervisionSplitOrder)supervisionPeriod).getSupPeriodBeginDate();
		  Date otherBeginDate = ((SupervisionSplitOrder)otherSupervisionPeriod).getSupPeriodBeginDate();
		  
		  if ((beginDate == null) && (otherBeginDate == null))
			  return 0;
		  else
			  if (beginDate == null)
				  return -1;
			  else
				  if (otherBeginDate == null)
					  return 1;				  
		  else
			  return beginDate.compareTo(otherBeginDate);
		}	
	};
	
	/**
	 * @return 
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionSplitOrder.class);
	}

	/**
	 * @param spn
	 * @return
	 */
	static public Iterator findAll(String spn){
		GetLatestSupervisionPeriodEvent getSupervisionPeriodEvent = new GetLatestSupervisionPeriodEvent();
		getSupervisionPeriodEvent.setDefendantId(spn);
		return findAll(getSupervisionPeriodEvent);
	}

	/**
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, SupervisionSplitOrder.class);
	}
	
    /**
     * @return
     * @param event
     * @roseuid 438F22CA0277
     * 
     * this will get all the supervision order for the given list of case numbers
     * and list of agencies.
     * create a map of agency to supervision orders.
     */
    static public Iterator getDetailsForCaseIds(IEvent event) {
        IHome home = new Home();
        return home.findAll(event, SupervisionSplitOrder.class);
    }
    
    
	/**
	 * @return Returns the supOrderBeginDate.
	 */
	public Date getSupOrderBeginDate() {
        fetch();
		return supOrderBeginDate;
	}

	/**
	 * @return Returns the supOrderEndDate.
	 */
	public Date getSupOrderEndDate() {
        fetch();        
		return supOrderEndDate;
	}

	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
        fetch();        
		return courtId;
	}
	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
        fetch();        
		return criminalCaseId;
	}

    /**
	 * @roseuid 44CFACF902F3
	 * @methodInvocation markModified
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * @roseuid 44D0C37A01B0
	 */
	public SupervisionSplitOrder()
	{
	}

	/**
	 * Access method for the supPeriodBeginDate property.
	 * @return the current value of the supPeriodBeginDate property
	 * @methodInvocation fetch
	 */
	public Date getSupPeriodBeginDate()
	{
		fetch();
		return supPeriodBeginDate;
	}

	/**
	 * Sets the value of the supPeriodBeginDate property.
	 * @param aSupervisionBeginDate the new value of the supPeriodBeginDate property
	 * @methodInvocation markModified
	 */
	public void setSupPeriodBeginDate(Date aSupervisionBeginDate)
	{
		if (this.supPeriodBeginDate == null || !this.supPeriodBeginDate.equals(aSupervisionBeginDate))
		{
			markModified();
		}
		supPeriodBeginDate = aSupervisionBeginDate;
	}

	/**
	 * Access method for the supPeriodEndDate property.
	 * @return the current value of the supPeriodEndDate property
	 * @methodInvocation fetch
	 */
	public Date getSupPeriodEndDate()
	{
		fetch();
		return supPeriodEndDate;
	}

	/**
	 * Sets the value of the supPeriodEndDate property.
	 * @param aSupervisionEndDate the new value of the supPeriodEndDate property
	 * @methodInvocation markModified
	 */
	public void setSupPeriodEndDate(Date aSupervisionEndDate)
	{
		if (this.supPeriodEndDate == null || !this.supPeriodEndDate.equals(aSupervisionEndDate))
		{
			markModified();
		}
		supPeriodEndDate = aSupervisionEndDate;
	}

	/**
	 * Access method for the agencyId property.
	 * @return the current value of the agencyId property
	 * @methodInvocation fetch
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * Sets the value of the agencyId property.
	 * @param aAgencyId the new value of the agencyId property
	 * @methodInvocation markModified
	 */
	public void setAgencyId(String aAgencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(aAgencyId))
		{
			markModified();
		}
		agencyId = aAgencyId;
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation markModified
	 */
	public void setPreviousSupervisionPeriodId(String aPreviousSupervisionPeriodId)
	{
		if (this.previousSupervisionPeriodId == null
				|| !this.previousSupervisionPeriodId.equals(aPreviousSupervisionPeriodId))
		{
			markModified();
		}
		previousSupervisionPeriod = null;
		this.previousSupervisionPeriodId = aPreviousSupervisionPeriodId;
	}

	/**
	 * @return Returns the previousSupervisionPeriod.
	 * @methodInvocation initPreviousSupervisionPeriod
	 */
	public SupervisionSplitOrder getPreviousSupervisionPeriod()
	{
		initPreviousSupervisionPeriod();
		return previousSupervisionPeriod;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation fetch
	 */
	public String getPreviousSupervisionPeriodId()
	{
		fetch();
		return previousSupervisionPeriodId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private void initPreviousSupervisionPeriod()
	{
		if (previousSupervisionPeriod == null)
		{
			previousSupervisionPeriod = (SupervisionSplitOrder) new mojo.km.persistence.Reference(
					previousSupervisionPeriodId, SupervisionSplitOrder.class).getObject();
		}
	}

	/**
	 * set the type reference for class member previousSupervisionPeriod
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPreviousSupervisionPeriodId
	 */
	public void setPreviousSupervisionPeriod(SupervisionSplitOrder aPreviousSupervisionPeriod)
	{
		if (this.previousSupervisionPeriod == null || !this.previousSupervisionPeriod.equals(aPreviousSupervisionPeriod))
		{
			markModified();
		}
		if (aPreviousSupervisionPeriod.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aPreviousSupervisionPeriod);
		}
		setPreviousSupervisionPeriodId("" + aPreviousSupervisionPeriod.getOID());
		this.previousSupervisionPeriod = (SupervisionSplitOrder) new mojo.km.persistence.Reference(
				aPreviousSupervisionPeriod).getObject();
	}
    
    
    
	/**
	 * @return Returns the activationDate.
	 */
	public Date getActivationDate() {
        fetch();
		return activationDate;
	}
	/**
	 * @param activationDate The activationDate to set.
	 */
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	/**
	 * @return Returns the currentCourtId.
	 */
	public String getCurrentCourtId() {
        fetch();
		return currentCourtId;
	}
	/**
	 * @param currentCourtId The currentCourtId to set.
	 */
	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
        fetch();
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the inactiveDate.
	 */
	public Date getInactiveDate() {
        fetch();
		return inactiveDate;
	}
	/**
	 * @param inactiveDate The inactiveDate to set.
	 */
	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	/**
	 * @return Returns the islimitedSupPeriod.
	 */
	public String getIslimitedSupPeriod() {
        fetch();
		return islimitedSupPeriod;
	}
	/**
	 * @param islimitedSupPeriod The islimitedSupPeriod to set.
	 */
	public void setIslimitedSupPeriod(String islimitedSupPeriod) {
		this.islimitedSupPeriod = islimitedSupPeriod;
	}
	/**
	 * @return Returns the isOrderinProgress.
	 */
	public String getIsOrderinProgress() {
        fetch();
		return isOrderinProgress;
	}
	/**
	 * @param isOrderinProgress The isOrderinProgress to set.
	 */
	public void setIsOrderinProgress(String isOrderinProgress) {
		this.isOrderinProgress = isOrderinProgress;
	}
	/**
	 * @return Returns the isOrderSigByDefDate.
	 */
	public String getIsOrderSigByDef() {
        fetch();
		return isOrderSigByDef;
	}
	/**
	 * @param isOrderSigByDefDate The isOrderSigByDefDate to set.
	 */
	public void setIsOrderSigByDefDate(String isOrderSigByDef) {
		this.isOrderSigByDef = isOrderSigByDef;
	}
	/**
	 * @return Returns the isPrintSpanish.
	 */
	public String getIsPrintSpanish() {
        fetch();
		return isPrintSpanish;
	}
	/**
	 * @param isPrintSpanish The isPrintSpanish to set.
	 */
	public void setIsPrintSpanish(String isPrintSpanish) {
		this.isPrintSpanish = isPrintSpanish;
	}
	/**
	 * @return Returns the modifyReason.
	 */
	public String getModifyReason() {
        fetch();
		return modifyReason;
	}
	/**
	 * @param modifyReason The modifyReason to set.
	 */
	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}
	/**
	 * @return Returns the orderFiledDate.
	 */
	public Date getOrderFiledDate() {
        fetch();
		return orderFiledDate;
	}
	/**
	 * @param orderFiledDate The orderFiledDate to set.
	 */
	public void setOrderFiledDate(Date orderFiledDate) {
		this.orderFiledDate = orderFiledDate;
	}
	/**
	 * @return Returns the orderSigByDefDate.
	 */
	public Date getOrderSigByDefDate() {
        fetch();
		return orderSigByDefDate;
	}
	/**
	 * @param orderSigByDefDate The orderSigByDefDate to set.
	 */
	public void setOrderSigByDefDate(Date orderSigByDefDate) {
		this.orderSigByDefDate = orderSigByDefDate;
	}
	/**
	 * @return Returns the orderSignedDate.
	 */
	public Date getOrderSignedDate() {
        fetch();
		return orderSignedDate;
	}
	/**
	 * @param orderSignedDate The orderSignedDate to set.
	 */
	public void setOrderSignedDate(Date orderSignedDate) {
		this.orderSignedDate = orderSignedDate;
	}
	/**
	 * @return Returns the orderStatusCd.
	 */
	public String getOrderStatusCd() {
        fetch();
		return orderStatusCd;
	}
	/**
	 * @param orderStatusCd The orderStatusCd to set.
	 */
	public void setOrderStatusCd(String orderStatusCd) {
		this.orderStatusCd = orderStatusCd;
	}
	/**
	 * @return Returns the orderTitleCd.
	 */
	public String getOrderTitleCd() {
        fetch();
		return orderTitleCd;
	}
	/**
	 * @param orderTitleCd The orderTitleCd to set.
	 */
	public void setOrderTitleCd(String orderTitleCd) {
		this.orderTitleCd = orderTitleCd;
	}
	/**
	 * @return Returns the plea.
	 */
	public String getPlea() {
        fetch();
		return plea;
	}
	/**
	 * @param plea The plea to set.
	 */
	public void setPlea(String plea) {
		this.plea = plea;
	}
	/**
	 * @return Returns the presentorFName.
	 */
	public String getPresentorFName() {
        fetch();
		return presentorFName;
	}
	/**
	 * @param presentorFName The presentorFName to set.
	 */
	public void setPresentorFName(String presentorFName) {
		this.presentorFName = presentorFName;
	}
	/**
	 * @return Returns the presentorLName.
	 */
	public String getPresentorLName() {
        fetch();
		return presentorLName;
	}
	/**
	 * @param presentorLName The presentorLName to set.
	 */
	public void setPresentorLName(String presentorLName) {
		this.presentorLName = presentorLName;
	}
	/**
	 * @return Returns the printSupOrderId.
	 */
	public String getPrintSupOrderId() {
        fetch();
		return printSupOrderId;
	}
	/**
	 * @param printSupOrderId The printSupOrderId to set.
	 */
	public void setPrintSupOrderId(String printSupOrderId) {
		this.printSupOrderId = printSupOrderId;
	}
	/**
	 * @return Returns the revisionDate.
	 */
	public Date getRevisionDate() {
        fetch();
		return revisionDate;
	}
	/**
	 * @param revisionDate The revisionDate to set.
	 */
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}
	/**
	 * @return Returns the signedByFName.
	 */
	public String getSignedByFName() {
        fetch();
		return signedByFName;
	}
	/**
	 * @param signedByFName The signedByFName to set.
	 */
	public void setSignedByFName(String signedByFName) {
		this.signedByFName = signedByFName;
	}
	/**
	 * @return Returns the signedByLName.
	 */
	public String getSignedByLName() {
        fetch();
		return signedByLName;
	}
	/**
	 * @param signedByLName The signedByLName to set.
	 */
	public void setSignedByLName(String signedByLName) {
		this.signedByLName = signedByLName;
	}
	/**
	 * @return Returns the signedByTypeCd.
	 */
	public String getSignedByTypeCd() {
        fetch();
		return signedByTypeCd;
	}
	/**
	 * @param signedByTypeCd The signedByTypeCd to set.
	 */
	public void setSignedByTypeCd(String signedByTypeCd) {
		this.signedByTypeCd = signedByTypeCd;
	}
	/**
	 * @return Returns the signedDate.
	 */
	public Date getSignedDate() {
        fetch();
		return signedDate;
	}
	/**
	 * @param signedDate The signedDate to set.
	 */
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	/**
	 * @return Returns the sugOrderId.
	 */
	public String getSugOrderId() {
        fetch();
		return sugOrderId;
	}
	/**
	 * @param sugOrderId The sugOrderId to set.
	 */
	public void setSugOrderId(String sugOrderId) {
		this.sugOrderId = sugOrderId;
	}
	/**
	 * @return Returns the summaryNotes.
	 */
	public String getSummaryNotes() {
        fetch();
		return summaryNotes;
	}
	/**
	 * @param summaryNotes The summaryNotes to set.
	 */
	public void setSummaryNotes(String summaryNotes) {
		this.summaryNotes = summaryNotes;
	}
	/**
	 * @return Returns the supOrderId.
	 */
	public String getSupOrderId() {
        fetch();
		return supOrderId;
	}
	/**
	 * @param supOrderId The supOrderId to set.
	 */
	public void setSupOrderId(String supOrderId) {
		this.supOrderId = supOrderId;
	}
	/**
	 * @return Returns the supPeriodId.
	 */
	public String getSupPeriodId() {
        fetch();
		return supPeriodId;
	}
	/**
	 * @param supPeriodId The supPeriodId to set.
	 */
	public void setSupPeriodId(String supPeriodId) {
		this.supPeriodId = supPeriodId;
	}
	/**
	 * @return Returns the supPrnTmplId.
	 */
	public String getSupPrnTmplId() {
        fetch();
		return supPrnTmplId;
	}
	/**
	 * @param supPrnTmplId The supPrnTmplId to set.
	 */
	public void setSupPrnTmplId(String supPrnTmplId) {
		this.supPrnTmplId = supPrnTmplId;
	}
	/**
	 * @return Returns the versionNum.
	 */
	public String getVersionNum() {
        fetch();
		return versionNum;
	}
	/**
	 * @param versionNum The versionNum to set.
	 */
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	/**
	 * @return Returns the versionTypeCd.
	 */
	public String getVersionTypeCd() {
        fetch();
		return versionTypeCd;
	}
	/**
	 * @param versionTypeCd The versionTypeCd to set.
	 */
	public void setVersionTypeCd(String versionTypeCd) {
		this.versionTypeCd = versionTypeCd;
	}
	/**
	 * @return Returns the withdrawReasonCd.
	 */
	public String getWithdrawReasonCd() {
        fetch();
		return withdrawReasonCd;
	}
	/**
	 * @param withdrawReasonCd The withdrawReasonCd to set.
	 */
	public void setWithdrawReasonCd(String withdrawReasonCd) {
		this.withdrawReasonCd = withdrawReasonCd;
	}
	/**
	 * @param supOrderBeginDate The supOrderBeginDate to set.
	 */
	public void setSupOrderBeginDate(Date casSupOrderBeginDate) {
		this.supOrderBeginDate = casSupOrderBeginDate;
	}
	/**
	 * @param supOrderEndDate The supOrderEndDate to set.
	 */
	public void setSupOrderEndDate(Date casSupOrderEndDate) {
		this.supOrderEndDate = casSupOrderEndDate;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return Returns the caseBeginDate.
	 */
	public Date getCaseBeginDate() {
        fetch();
		return caseBeginDate;
	}
	/**
	 * @param caseBeginDate The caseBeginDate to set.
	 */
	public void setCaseBeginDate(Date caseBeginDate) {
		this.caseBeginDate = caseBeginDate;
	}
	/**
	 * @return Returns the caseEndDate.
	 */
	public Date getCaseEndDate() {
        fetch();
		return caseEndDate;
	}
	/**
	 * @param caseEndDate The caseEndDate to set.
	 */
	public void setCaseEndDate(Date caseEndDate) {
		this.caseEndDate = caseEndDate;
	}
	/**
	 * @param isOrderSigByDef The isOrderSigByDef to set.
	 */
	public void setIsOrderSigByDef(String isOrderSigByDef) {
		this.isOrderSigByDef = isOrderSigByDef;
	}
}

