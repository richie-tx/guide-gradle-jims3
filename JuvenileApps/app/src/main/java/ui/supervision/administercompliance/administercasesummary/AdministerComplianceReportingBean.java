/*
 * Created on Apr 29, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.administercompliance.administercasesummary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AdministerComplianceReportingBean
{
	private String addressZip = "";
	private String caMotionsComments = "";
	private String caOthersComments = "";
	private String cause = "";
	private List communityService = new ArrayList();
	private String communityServiceComments = "";
	private String court = "";
    
	private List courtActions = new ArrayList();
	private String courtCategory = "";            
	private Date createDate = new Date(); 
	private List currentMotions = new ArrayList();
	private List currentOthersList = new ArrayList();
	private String dateOfBirth = "";
	private String divisionName = "";
	private String dopDay = "";
    private List employmentHistory = new ArrayList();
    private String employmentHistoryComments = "";
	private List feeHistory = new ArrayList();
	private String feeHistoryComments = "";
	private String fileDateStr;
	private String hoursCompleted = "";
	private String hoursOrdered = "";
	private String judgeName = "";
	private String mentalHealthDiagnosis = "";
	private String mentalHealthComments = "";
	private List lawViolations = new ArrayList();
	private String lawViolationComments = "";
	private String levelOfSupervision = "";
	private String cloName = "";
	private String cloPOI = "";
	private String csoName = "";
	private String csoPOI = "";
	private String csoPhone = "";
	private String mgApproveName = "";
    private String mgApprovePOI  =	"";
    private String mgApprovePhone = "";
    private Date lastContactDate = new Date();
    private Date nextOfficeVisitDate = new Date();
    private Date nextOfficeVisitTime = new Date();
	private String offense = "";
	private String offenseLevel = "";
	private String partyName = "";
    private List posUrinalysis = new ArrayList(); 
    private String posUrinalysisComments = "";
    
    private List preCourtActivity = new ArrayList();
    private String preCourtActivityComments = "";
    private List reasonForTransfer = new ArrayList();
    private String isExtended;
    private String recommendations = "";
    private List reportingHistory = new ArrayList();
    private String reportingHistoryComments = "";
    private String sexCd 	= "";
    private String specimenTotal = "";
    private Date subMgrAppDate = new Date();
    private List sugCourtActions = new ArrayList();
    private String summaryOfCourtActions = "";
    private String superviseeId = "";
    private String supervisionLengthNum = "";
    private Date supervisionBeginDate = new Date();
    private Date supervisionEndDate = new Date();
    private String termHCJ = "";
    private String termSTJ = "";
    private List treatmentIssues = new ArrayList();
    private String treatmentIssuesComments = "";
    private String typeOfDisposition = "";
    private String createDateStr = "";
    private List motionList = new ArrayList();
    private String motionsComments = "";
    private List otherList = new ArrayList();
    private String otherComments = "";
    private String totalSpecimenAnalyzed = "";
    private String programUnit = "";
    private String superviseeAddress = "";
    private String supervisionType = "";
   
    
    

    
      
    public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	
	/**
	 * @return Returns the fileDateStr.
	 */
	public String getFileDateStr() {
		return fileDateStr;
	}
	
	/**
	 * @param fileDateStr The fileDateStr to set.
	 */
	public void setFileDateStr(String fileDateStr) {
		this.fileDateStr = fileDateStr;
	}

	public List getMotionList() {
		return motionList;
	}

	public void setMotionList(List motionList) {
		this.motionList = motionList;
	}

	public String getMotionsComments() {
		return motionsComments;
	}

	public void setMotionsComments(String motionsComments) {
		this.motionsComments = motionsComments;
	}

	public List getOtherList() {
		return otherList;
	}

	public void setOtherList(List otherList) {
		this.otherList = otherList;
	}

	public String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(String otherComments) {
		this.otherComments = otherComments;
	}

	public String getCaMotionsComments() {
		return caMotionsComments;
	}

	public void setCaMotionsComments(String caMotionsComments) {
		this.caMotionsComments = caMotionsComments;
	}

	public String getCaOthersComments() {
		return caOthersComments;
	}

	public void setCaOthersComments(String caOthersComments) {
		this.caOthersComments = caOthersComments;
	}

	/**
	 * @return
	 */
	public String getCause()
	{
		return cause;
	}
    
    public List getCommunityService() {
		return communityService;
	}
    
    /**
	 * @return
	 */
	public String getCourt()
	{
		return court;
	}
    
	/**
	 * @return the courtActions
	 */
	public List getCourtActions() {
		return courtActions;
	}

	/**
     * @return Returns the courtCategory.
     */
    public String getCourtCategory()
    {
        return courtCategory;
    }
    
    public Date getCreateDate() {
		return createDate;
	}
    
    
    public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	/**
	 * @return
	 */
	public String getDopDay()
	{
		return dopDay;
	}
    
 
	public List getEmploymentHistory() {
		return employmentHistory;
	}
	public List getFeeHistory() {
		return feeHistory;
	}

	/**
	 * @return
	 */
	public String getJudgeName()
	{
		return judgeName;
	}    
    public List getLawViolations() {
		return lawViolations;
	}
    
    /**
	 * @return the cloName
	 */
	public String getCloName() {
		return cloName;
	}

	/**
	 * @return the cloPOI
	 */
	public String getCloPOI() {
		return cloPOI;
	}

	/**
	 * @return the csoName
	 */
	public String getCsoName() {
		return csoName;
	}

	/**
	 * @return the csoPOI
	 */
	public String getCsoPOI() {
		return csoPOI;
	}
	
	/**
	 * @return the csoPhone
	 */
	public String getCsoPhone() {
		return csoPhone;
	}
	
	public String getMgApproveName() {
		return mgApproveName;
	}

	public String getMgApprovePOI() {
		return mgApprovePOI;
	}

	/**
	 * @return the mgApprovePhone
	 */
	public String getMgApprovePhone() {
		return mgApprovePhone;
	}

	/**
	 * @return
	 */
	public String getOffense()
	{
		return offense;
	}

   /**
	 * @return Returns the offenseLevel.
	 */
	public String getOffenseLevel() {
		return offenseLevel;
	}

	/**
     * @return Returns the partyName.
     */
    public String getPartyName()
    {
        return partyName;
    }

	public List getPosUrinalysis() {
		return posUrinalysis;
	}

	public List getPreCourtActivity() {
		return preCourtActivity;
	}

	public List getReasonForTransfer() {
		return reasonForTransfer;
	}
	public List getReportingHistory() {
		return reportingHistory;
	}

	public List getSugCourtActions() {
		return sugCourtActions;
	}

	/**
	 * @return the summaryOfCourtActions
	 */
	public String getSummaryOfCourtActions() {
		return summaryOfCourtActions;
	}

	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @return
	 */
	public String getSupervisionLengthNum()
	{
		return supervisionLengthNum;
	}

	/**
	 * @return
	 */
	public String getTermHCJ()
	{
		return termHCJ;
	}

	/**
	 * @return
	 */
	public String getTermSTJ()
	{
		return termSTJ;
	}

	public List getTreatmentIssues() {
		return treatmentIssues;
	}

	/**
	 * @param string
	 */
	public void setCause(String cause)
	{
		this.cause = cause;
	}

	public void setCommunityService(List communityService) {
		this.communityService = communityService;
	}

	/**
	 * @param string
	 */
	public void setCourt(String court)
	{
		this.court = court;
	}

	 /**
	 * @param courtActions the courtActions to set
	 */
	public void setCourtActions(List courtActions) {
		this.courtActions = courtActions;
	}

	/**
     * @param courtCategory The courtCategory to set.
     */
    public void setCourtCategory(String courtCategory)
    {
        this.courtCategory = courtCategory;
    }

	public void setCreateDate(Date currDate) {
		this.createDate = currDate;
	}
    /**
	 * @param string
	 */
	public void setDopDay(String dopDay)
	{
		this.dopDay = dopDay;
	}

	public void setEmploymentHistory(List employmentHistory) {
		this.employmentHistory = employmentHistory;
	}

	public void setFeeHistory(List feeHistory) {
		this.feeHistory = feeHistory;
	}

	/**
	 * @param string
	 */
	public void setJudgeName(String judgeName)
	{
		this.judgeName = judgeName;
	}

	public void setLawViolations(List lawViolations) {
		this.lawViolations = lawViolations;
	}
	
	/**
	 * @param cloName the cloName to set
	 */
	public void setCloName(String cloName) {
		this.cloName = cloName;
	}

	/**
	 * @param cloPOI the cloPOI to set
	 */
	public void setCloPOI(String cloPOI) {
		this.cloPOI = cloPOI;
	}

	/**
	 * @param csoName the csoName to set
	 */
	public void setCsoName(String csoName) {
		this.csoName = csoName;
	}

	/**
	 * @param csoPOI the csoPOI to set
	 */
	public void setCsoPOI(String csoPOI) {
		this.csoPOI = csoPOI;
	}

	/**
	 * @param csoPhone the csoPhone to set
	 */
	public void setCsoPhone(String csoPhone) {
		this.csoPhone = csoPhone;
	}
	
	public void setMgApproveName(String mgApproveName) {
		this.mgApproveName = mgApproveName;
	}

	public void setMgApprovePOI(String mgApprovePOI) {
		this.mgApprovePOI = mgApprovePOI;
	}

	/**
	 * @param mgApprovePhone the mgApprovePhone to set
	 */
	public void setMgApprovePhone(String mgApprovePhone) {
		this.mgApprovePhone = mgApprovePhone;
	}

	/**
	 * @param string
	 */
	public void setOffense(String offense)
	{
		this.offense = offense;
	}

	/**
	 * @param offenseLevel The offenseLevel to set.
	 */
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}

	/**
     * @param partyName The partyName to set.
     */
    public void setPartyName(String partyName)
    {
        this.partyName = partyName;
    }

	public void setPosUrinalysis(List posUrinalysis) {
		this.posUrinalysis = posUrinalysis;
	}

	public void setPreCourtActivity(List preCourtActivity) {
		this.preCourtActivity = preCourtActivity;
	}

	public void setReasonForTransfer(List reasonForTransfer) {
		this.reasonForTransfer = reasonForTransfer;
	}

	public void setReportingHistory(List reportingHistory) {
		this.reportingHistory = reportingHistory;
	}

	public void setSugCourtActions(List sugCourtActions) {
		this.sugCourtActions = sugCourtActions;
	}
	
	/**
	 * @param summaryOfCourtActions the summaryOfCourtActions to set
	 */
	public void setSummaryOfCourtActions(String summaryOfCourtActions) {
		this.summaryOfCourtActions = summaryOfCourtActions;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @param string
	 */
	public void setSupervisionLengthNum(String supervisionLengthNum)
	{
		this.supervisionLengthNum = supervisionLengthNum;
	}

	/**
	 * @param string
	 */
	public void setTermHCJ(String termHCJ)
	{
		this.termHCJ = termHCJ;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getLevelOfSupervision() {
		return levelOfSupervision;
	}

	public void setLevelOfSupervision(String levelOfSupervision) {
		this.levelOfSupervision = levelOfSupervision;
	}

	/**
	 * @return the lastContactDate
	 */
	public Date getLastContactDate() {
		return lastContactDate;
	}

	/**
	 * @param lastContactDate the lastContactDate to set
	 */
	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}

	public Date getNextOfficeVisitDate() {
		return nextOfficeVisitDate;
	}

	public void setNextOfficeVisitDate(Date nextOfficeVisitDate) {
		this.nextOfficeVisitDate = nextOfficeVisitDate;
	}

	public Date getNextOfficeVisitTime() {
		return nextOfficeVisitTime;
	}

	public void setNextOfficeVisitTime(Date nextOfficeVisitTime) {
		this.nextOfficeVisitTime = nextOfficeVisitTime;
	}

	public String getSexCd() {
		return sexCd;
	}

	public List getCurrentMotions() {
		return currentMotions;
	}

	public void setCurrentMotions(List currentMotions) {
		this.currentMotions = currentMotions;
	}

	public List getCurrentOthersList() {
		return currentOthersList;
	}

	public void setCurrentOthersList(List currentOthersList) {
		this.currentOthersList = currentOthersList;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getSpecimenTotal() {
		return specimenTotal;
	}

	public void setSpecimenTotal(String specimenTotal) {
		this.specimenTotal = specimenTotal;
	}

	/**
	 * @return the subMgrAppDate
	 */
	public Date getSubMgrAppDate() {
		return subMgrAppDate;
	}

	/**
	 * @param subMgrAppDate the subMgrAppDate to set
	 */
	public void setSubMgrAppDate(Date subMgrAppDate) {
		this.subMgrAppDate = subMgrAppDate;
	}

	public Date getSupervisionBeginDate() {
		return supervisionBeginDate;
	}

	public void setSupervisionBeginDate(Date supervisionBeginDate) {
		this.supervisionBeginDate = supervisionBeginDate;
	}

	/**
	 * @return the supervisionEndDate
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}

	/**
	 * @param supervisionEndDate the supervisionEndDate to set
	 */
	public void setSupervisionEndDate(Date supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}

	public String getTypeOfDisposition() {
		return typeOfDisposition;
	}

	public void setTypeOfDisposition(String typeOfDisposition) {
		this.typeOfDisposition = typeOfDisposition;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCommunityServiceComments() {
		return communityServiceComments;
	}

	public void setCommunityServiceComments(String communityServiceComments) {
		this.communityServiceComments = communityServiceComments;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public String getReportingHistoryComments() {
		return reportingHistoryComments;
	}

	public void setReportingHistoryComments(String reportingHistoryComments) {
		this.reportingHistoryComments = reportingHistoryComments;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * @param string
	 */
	public void setTermSTJ(String string)
	{
		this.termSTJ = string;
	}

	public void setTreatmentIssues(List treatmentIssues) {
		this.treatmentIssues = treatmentIssues;
	}
	
	/**
	 * @return the isExtended
	 */
	public String getIsExtended() {
		return isExtended;
	}

	/**
	 * @return the mentalHealthDiagnosis
	 */
	public String getMentalHealthDiagnosis() {
		return mentalHealthDiagnosis;
	}

	/**
	 * @return the mentalHealthComments
	 */
	public String getMentalHealthComments() {
		return mentalHealthComments;
	}

	public String getLawViolationComments() {
		return lawViolationComments;
	}

	public String getEmploymentHistoryComments() {
		return employmentHistoryComments;
	}

	public void setEmploymentHistoryComments(String employmentHistoryComments) {
		this.employmentHistoryComments = employmentHistoryComments;
	}

	public String getFeeHistoryComments() {
		return feeHistoryComments;
	}

	public void setFeeHistoryComments(String feeHistoryComments) {
		this.feeHistoryComments = feeHistoryComments;
	}

	public String getPosUrinalysisComments() {
		return posUrinalysisComments;
	}

	public void setPosUrinalysisComments(String posUrinalysisComments) {
		this.posUrinalysisComments = posUrinalysisComments;
	}

	public String getPreCourtActivityComments() {
		return preCourtActivityComments;
	}

	public void setPreCourtActivityComments(String preCourtActivityComments) {
		this.preCourtActivityComments = preCourtActivityComments;
	}

	public String getTreatmentIssuesComments() {
		return treatmentIssuesComments;
	}

	public String getHoursCompleted() {
		return hoursCompleted;
	}

	public void setHoursCompleted(String hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}

	public String getHoursOrdered() {
		return hoursOrdered;
	}

	public void setHoursOrdered(String hoursOrdered) {
		this.hoursOrdered = hoursOrdered;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setTreatmentIssuesComments(String treatmentIssuesComments) {
		this.treatmentIssuesComments = treatmentIssuesComments;
	}
	
	/**
	 * @param isExtended the isExtended to set
	 */
	public void setIsExtended(String isExtended) {
		this.isExtended = isExtended;
	}

	/**
	 * @param mentalHealthDiagnosis the mentalHealthDiagnosis to set
	 */
	public void setMentalHealthDiagnosis(String mentalHealthDiagnosis) {
		this.mentalHealthDiagnosis = mentalHealthDiagnosis;
	}

	/**
	 * @param mentalHealthComments the mentalHealthComments to set
	 */
	public void setMentalHealthComments(String mentalHealthComments) {
		this.mentalHealthComments = mentalHealthComments;
	}

	public void setLawViolationComments(String lawViolationComments) {
		this.lawViolationComments = lawViolationComments;
	}

	public String getTotalSpecimenAnalyzed() {
		return totalSpecimenAnalyzed;
	}

	public void setTotalSpecimenAnalyzed(String totalSpecimenAnalyzed) {
		this.totalSpecimenAnalyzed = totalSpecimenAnalyzed;
	}

	public String getProgramUnit() {
		return programUnit;
	}

	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}

	/**
	 * @return the superviseeAddress
	 */
	public String getSuperviseeAddress() {
		return superviseeAddress;
	}

	/**
	 * @param superviseeAddress the superviseeAddress to set
	 */
	public void setSuperviseeAddress(String superviseeAddress) {
		this.superviseeAddress = superviseeAddress;
	}

	public String getSupervisionType() {
		return supervisionType;
	}

	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}
	
}

