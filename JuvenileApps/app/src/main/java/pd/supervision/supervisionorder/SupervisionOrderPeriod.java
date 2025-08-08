/**
 * 
 */
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
 * @author cc_cwalters
 *
 */
public class SupervisionOrderPeriod extends PersistentObject implements
		Comparable {

	private String versionTypeId;
	private String orderCourtId;
	private String versionNum;
	private String defendantId;
	private String agencyCode;
	private Date   limitedSupervisionEndDate;
	private String criminalCaseId;
	private String orderStatusId;
	private String signedByTypeId;
	private Date   limitedSupervisionBeginDate;
	private String parentSupervisionOrderId;
	private String currentCourtId;
	private Date revisionDate;
	private Date inactivationDate;
	private String supervisionRedirectId;
	private String supervisionPeriodId;
	private String supervisionOrderId;
	private String signedByFirstName;
	private String signedByLastName;
	private String supervisionParentTemplateId;
	private boolean isPrintSpanish;
	private String orderTitleId;
	private Date orderFiledDate;
	private boolean isOrderInProgress;
	private String presentorFirstName;
	private String presentorLastName;
	private Date activationDate;
	private String suggestedOrderId;
	private Date signedDate;
	private String modifyReason;
	private String summaryNotes;
	private Date orderSignedByDefendantDate;
	private boolean isOrderSignedByDefendant;
	private String withdrawReasonId;
	private String plea;
	private float fineAmount;
	private int confinementYears;
	private int confinementMonths;
	private int confinementDays;
	private int supervisionYears;
	private int supervisionMonths;
	private int supervisionDays;
	private String offenseId;
	private String dispositionId;
	private Date caseSupervisionBeginDate;
	private Date caseSupervisionEndDate;
	private Date supervisionPeriodBeginDate;
	private Date supervisionPeriodEndDate;
	private String agencyId;
	private String previousSupervisionPeriod;
	private String originalSupervisionPeriod;

	
	public static Comparator getSupervisionPeriodBeginDateComparator() {
		return SupervisionPeriodBeginDateComparator;
	}

	public static void setSupervisionPeriodBeginDateComparator(
			Comparator supervisionPeriodBeginDateComparator) {
		SupervisionPeriodBeginDateComparator = supervisionPeriodBeginDateComparator;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public Date getCaseSupervisionBeginDate() {
		return caseSupervisionBeginDate;
	}

	public void setCaseSupervisionBeginDate(Date caseSupervisionBeginDate) {
		this.caseSupervisionBeginDate = caseSupervisionBeginDate;
	}

	public Date getCaseSupervisionEndDate() {
		return caseSupervisionEndDate;
	}

	public void setCaseSupervisionEndDate(Date caseSupervisionEndDate) {
		this.caseSupervisionEndDate = caseSupervisionEndDate;
	}

	public int getConfinementDays() {
		return confinementDays;
	}

	public void setConfinementDays(int confinementDays) {
		this.confinementDays = confinementDays;
	}

	public int getConfinementMonths() {
		return confinementMonths;
	}

	public void setConfinementMonths(int confinementMonths) {
		this.confinementMonths = confinementMonths;
	}

	public int getConfinementYears() {
		return confinementYears;
	}

	public void setConfinementYears(int confinementYears) {
		this.confinementYears = confinementYears;
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	public String getCurrentCourtId() {
		return currentCourtId;
	}

	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	public String getDispositionId() {
		return dispositionId;
	}

	public void setDispositionId(String dispositionId) {
		this.dispositionId = dispositionId;
	}

	public float getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(float fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Date getInactivationDate() {
		return inactivationDate;
	}

	public void setInactivationDate(Date inactivationDate) {
		this.inactivationDate = inactivationDate;
	}

	public boolean isOrderInProgress() {
		return isOrderInProgress;
	}

	public void setOrderInProgress(boolean isOrderInProgress) {
		this.isOrderInProgress = isOrderInProgress;
	}

	public boolean isOrderSignedByDefendant() {
		return isOrderSignedByDefendant;
	}

	public void setOrderSignedByDefendant(boolean isOrderSignedByDefendant) {
		this.isOrderSignedByDefendant = isOrderSignedByDefendant;
	}

	public boolean isPrintSpanish() {
		return isPrintSpanish;
	}

	public void setPrintSpanish(boolean isPrintSpanish) {
		this.isPrintSpanish = isPrintSpanish;
	}

	public Date getLimitedSupervisionBeginDate() {
		return limitedSupervisionBeginDate;
	}

	public void setLimitedSupervisionBeginDate(Date limitedSupervisionBeginDate) {
		this.limitedSupervisionBeginDate = limitedSupervisionBeginDate;
	}

	public Date getLimitedSupervisionEndDate() {
		return limitedSupervisionEndDate;
	}

	public void setLimitedSupervisionEndDate(Date limitedSupervisionEndDate) {
		this.limitedSupervisionEndDate = limitedSupervisionEndDate;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getOffenseId() {
		return offenseId;
	}

	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}

	public String getOrderCourtId() {
		return orderCourtId;
	}

	public void setOrderCourtId(String orderCourtId) {
		this.orderCourtId = orderCourtId;
	}

	public Date getOrderFiledDate() {
		return orderFiledDate;
	}

	public void setOrderFiledDate(Date orderFiledDate) {
		this.orderFiledDate = orderFiledDate;
	}

	public Date getOrderSignedByDefendantDate() {
		return orderSignedByDefendantDate;
	}

	public void setOrderSignedByDefendantDate(Date orderSignedByDefendantDate) {
		this.orderSignedByDefendantDate = orderSignedByDefendantDate;
	}

	public String getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(String orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderTitleId() {
		return orderTitleId;
	}

	public void setOrderTitleId(String orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public String getParentSupervisionOrderId() {
		return parentSupervisionOrderId;
	}

	public void setParentSupervisionOrderId(String parentSupervisionOrderId) {
		this.parentSupervisionOrderId = parentSupervisionOrderId;
	}

	public String getPlea() {
		return plea;
	}

	public void setPlea(String plea) {
		this.plea = plea;
	}

	public String getPresentorFirstName() {
		return presentorFirstName;
	}

	public void setPresentorFirstName(String presentorFirstName) {
		this.presentorFirstName = presentorFirstName;
	}

	public String getPresentorLastName() {
		return presentorLastName;
	}

	public void setPresentorLastName(String presentorLastName) {
		this.presentorLastName = presentorLastName;
	}

	public String getPreviousSupervisionPeriod() {
		return previousSupervisionPeriod;
	}

	public void setPreviousSupervisionPeriod(String previousSupervisionPeriod) {
		this.previousSupervisionPeriod = previousSupervisionPeriod;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getSignedByFirstName() {
		return signedByFirstName;
	}

	public void setSignedByFirstName(String signedByFirstName) {
		this.signedByFirstName = signedByFirstName;
	}

	public String getSignedByLastName() {
		return signedByLastName;
	}

	public void setSignedByLastName(String signedByLastName) {
		this.signedByLastName = signedByLastName;
	}

	public String getSignedByTypeId() {
		return signedByTypeId;
	}

	public void setSignedByTypeId(String signedByTypeId) {
		this.signedByTypeId = signedByTypeId;
	}

	public Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	public String getSuggestedOrderId() {
		return suggestedOrderId;
	}

	public void setSuggestedOrderId(String suggestedOrderId) {
		this.suggestedOrderId = suggestedOrderId;
	}

	public String getSummaryNotes() {
		return summaryNotes;
	}

	public void setSummaryNotes(String summaryNotes) {
		this.summaryNotes = summaryNotes;
	}

	public Date getSupervisionPeriodBeginDate() {
		return supervisionPeriodBeginDate;
	}

	public void setSupervisionPeriodBeginDate(Date supervisionPeriodBeginDate) {
		this.supervisionPeriodBeginDate = supervisionPeriodBeginDate;
	}

	public int getSupervisionDays() {
		return supervisionDays;
	}

	public void setSupervisionDays(int supervisionDays) {
		this.supervisionDays = supervisionDays;
	}

	public Date getSupervisionPeriodEndDate() {
		return supervisionPeriodEndDate;
	}

	public void setSupervisionPeriodEndDate(Date supervisionPeriodEndDate) {
		this.supervisionPeriodEndDate = supervisionPeriodEndDate;
	}

	public int getSupervisionMonths() {
		return supervisionMonths;
	}

	public void setSupervisionMonths(int supervisionMonths) {
		this.supervisionMonths = supervisionMonths;
	}

	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}

	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}

	public String getSupervisionParentTemplateId() {
		return supervisionParentTemplateId;
	}

	public void setSupervisionParentTemplateId(String supervisionParentTemplateId) {
		this.supervisionParentTemplateId = supervisionParentTemplateId;
	}

	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}

	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}

	public String getSupervisionRedirectId() {
		return supervisionRedirectId;
	}

	public void setSupervisionRedirectId(String supervisionRedirectId) {
		this.supervisionRedirectId = supervisionRedirectId;
	}

	public int getSupervisionYears() {
		return supervisionYears;
	}

	public void setSupervisionYears(int supervisionYears) {
		this.supervisionYears = supervisionYears;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersionTypeId() {
		return versionTypeId;
	}

	public void setVersionTypeId(String versionTypeId) {
		this.versionTypeId = versionTypeId;
	}

	public String getWithdrawReasonId() {
		return withdrawReasonId;
	}

	public void setWithdrawReasonId(String withdrawReasonId) {
		this.withdrawReasonId = withdrawReasonId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Comparator SupervisionPeriodBeginDateComparator = new Comparator() 
	{
		public int compare(Object supervisionPeriod, Object otherSupervisionPeriod) 
		{
		  Date beginDate = ((SupervisionOrderPeriod)supervisionPeriod).getSupervisionPeriodBeginDate();
		  Date otherBeginDate = ((SupervisionOrderPeriod)otherSupervisionPeriod).getSupervisionPeriodBeginDate();
		  
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

	public static Comparator OrderActivationDateComparator = new Comparator() 
	{
		public int compare(Object orderActivationDate, Object otherOrderActivationDate) 
		{
		  Date beginDate = ((SupervisionOrderPeriod)orderActivationDate).getActivationDate();
		  Date otherBeginDate = ((SupervisionOrderPeriod)otherOrderActivationDate).getActivationDate();
		  
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
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, SupervisionOrderPeriod.class);
	}

	public String getOriginalSupervisionPeriod() {
		return originalSupervisionPeriod;
	}

	public void setOriginalSupervisionPeriod(String originalSupervisionPeriod) {
		this.originalSupervisionPeriod = originalSupervisionPeriod;
	}
	
}
