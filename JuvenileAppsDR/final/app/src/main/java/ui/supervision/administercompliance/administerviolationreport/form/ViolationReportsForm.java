//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\form\\ViolationReportsForm.java

package ui.supervision.administercompliance.administerviolationreport.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.common.UIUtil;

/**
 * @author cshimek
 *  
 */
public class ViolationReportsForm extends ActionForm 
{

// begin common form variables
	private String action="";
	private String orderActivationDate;
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	private String userAgency;
// end common form variables	
	private String[] emptyArrayStr = {};
	private String addItemIndex;
	private String allowUpdate;	
	private String allowMaintain;
	private String cdi;
	private String caseNum;	
	private String caseNoteId;
	private String supervisionPeriodId;
	private String confirmationMessage;
	private String createdByName;
	private Date createDate;
	private String currentLogonId;
	private String los;	
	private Date managerApprovalDate;
	private String offense;
	private String officerName;
	private String orderId;
	private String orderStatus;
	private String programUnit;
	private boolean showAddFields;
	private boolean showAddress;
	private boolean showMotionAddFields;
	private boolean showVRAddFields;	
	private String statusDesc;
	private String statusId;
	private Date subMgrAppDate;
	private boolean cloPosition;
	private Date statusChangedDate;
	private String superviseeName;
	private String superviseeId;
	private String presentedById;
	private String presentedByFirstName;
	private String presentedByLastName;	
	private List presentedByList;
	private String presentedByName;
	private String whoSignedId;
	private String whoSignedFirstName;
	private String whoSignedLastName;
	private Collection whoSignedList;
	private String whoSignedName;
	private String fileDateStr;
	private List violationReportsDisplayList;
	private String violationReportId;
// these 9 variables are common variables used in element creation only 	
	private List create1ElementsList;
	private List create2ElementsList;
	private List create3ElementsList;
	private String create1Comments;
	private String create2Comments;
	private String create3Comments;
	// REASON FOR TRANSER variables	
	private String isExtended;
	private String reasonsForTransfer;
	private List reasonForTransferList;
	private String currentReasonForTransfer;
	private String currentReasonForTransferComments;
	private String previousReasonForTransferComments;
	private List currentReasonForTransferList;
	private String[] selectedReasonForTransferIds;
	// MENTAL HEALTH variables
	private String currentMentalHealthComments;
	private String currentMentalHealthDiagnosis;
	private String previousMentalHealthComments;
	private String previousMentalHealthDiagnosis;
// LAW VIOLATION variables	
	private String lvCaseNum;
	private String courtNum;
	private String offenseDateStr;
	private Date offenseDate;
	private String offenseLiteral;
	private String offenseLevelId;
	private String offenseDegreeId;
	private List currentLawViolationsList;
	private String currentLawViolationsComments;
	private String previousLawViolationsComments;
	private String[] selectedLawViolationsIds;
//	FEE HISTORY variables	
	private String payType;
	private String payTypeId;
	private List payTypes;
	private String amountOrdered;
	private String paidToDate;
	private String deliquentAmount;
	private String currentFeeHistoryComments;
	private String previousFeeHistoryComments;
	private List currentFeeHistoryList;
	private String[] selectedFeeHistoryIds;	
//	REPORTING HISTORY variables
	private Date occurrenceDate;
	private String occurrenceDateStr;
	private String occurrenceTime;
	private String AMPMId;
	private String[] selectedEventTypeIds;
	private String reportingHistoryDetails;
	private List eventTypes;
	private String currentReportingHistoryComments;
	private String previousReportingHistoryComments;
	private List currentReportingHistoryList;
	private String[] selectedReportingHistoryIds; 
	private Date lastContactDate;
	private String addressType;
	private String addressTypeId;
	private String addressName;
	private String addressNumber;
	private String addressCity;
	private String addressState;
	private String addressStateId;
	private String addressZipCode;
//	EMPLOYMENT HISTORY variables
	private String employerName;
	private String jobTitle;
	private String jobStatusId;
	private String jobStartDateStr;
	private String currentEmploymentHistoryComments;
	private String previousEmploymentHistoryComments;
	private List currentEmploymentHistoryList;
	private String[] selectedEmploymentHistoryIds;
	private String[] selectedStartDateStrs;
	private List employmentStatusList;
// 	 PREVIOUS COURT ACTIVITY - VIOLATION REPORT variables
	private String cursorPosition;
	private Date courtActivityVRDate;
	private String courtActivityVRDateStr;
	private String courtActivityVRSummaryOfCourtActions;
	private String currentCourtActivityVRComments;	
	private String previousCourtActivityVRComments;
	private List currentCourtActivityVRList;
	private String courtActivityVRActivityDesc;
	private String[] selectedCourtActivityVRIds;
//	 PREVIOUS COURT ACTIVITY - MOTIONS variables
	private Date motionDate;
	private String motionDateStr;
	private String motionSummaryOfCourtActions;	
	private String selectedMotionActivityId;
	private String selectedMotionDispositionId;	
	private String currentMotionsComments;
	private String previousMotionsComments;
	private List currentMotionsList;
	private List motionsActivities;
	private List motionsDispositions;	
	private String[] selectedMotionsIds;
//	 PREVIOUS COURT ACTIVITY - OTHER variables	
	private Date otherDate;
	private String otherDateStr;
	private String selectedOtherActivityId;
	private String currentOthersComments;
	private String previousOthersComments;
	private List currentOthersList;
	private List otherActivities;
	private String[] selectedOthersIds;	
	private String otherSummaryOfCourtActions;

	//	TREATMENT ISSUES variables	
//	public String selectedReferralTypeId;
	private String referralTypeDesc;
	public String programGroupId;
	public String programTypeId;
//	public String selectedAgencyId;
	private String serviceProviderName;
	private Date referralBeginDate;
	private String referralBeginDateStr;
	private Date referralExitDate;	
	private String referralExitDateStr;
	private String selectedDischargeReasonId;
	private String currentTreatmentIssuesComments;
	private String previousTreatmentIssuesComments;
//	public List referralTypes;
	private List dischargeReasons;
//	public List Agencies;
	private List currentTreatmentIssuesList;
	private String[] selectedTreatmentIssuesIds;	
// COMMUNITY SERVICE variables	
	private String hoursOrdered;
	private String hoursCompleted;
	private String currentHoursOrdered;
	private String currentHoursCompleted;
	private String currentCommunityServiceComments;
	private String previousCommunityServiceComments;
// POSITIVE URINALYSIS variables	
	private String totalSpecimensAnalyzed;
	private String currentTotalSpecimensAnalyzed;
	private Date testDate;
	private String testDateStr;
	private String substance;
//	public List substances;
	private String currentPositiveUrinalysisComments;
	private String previousPositiveUrinalysisComments;
	private List currentPositiveUrinalysisList;	
	private String[] selectedPositiveUrinalysisIds;	
// RECOMMENDATIONS variables	
	private List activeSuggestedCourtActions;
	private String recommendations;
	private String currentRecommendations;
	private String previousRecommendations;
	private List suggestedCourtActions;	
	private List currentSuggestedCourtActionsList;
	private String[] selectedSuggestedCourtActionIds;	
// COURT ACTIONS variables
	private String summaryOfCourtActions;
	private String previousSummaryOfCourtActions;
	private List courtActions;	
	private String[] selectedCourtActionsIds;	
	private String courtActionfiledDate;
	private List currentCourtActionsList;
// TASK varialbles
	private String taskCreatorId;
	private boolean taskflowInd;
	private String taskNextAction;
	private String taskPSCreatorId;
	private String taskSubject;
	private String taskText;
	private String taskTo;
	private String taskId;
	private String taskToStaffId;
	
    public void clear()
    {
    	this.orderActivationDate = "";
    	this.statusDesc = "";
    	this.statusId = "";
    	this.createdByName = "";
    	this.addItemIndex = "";
    	this.allowUpdate = "";
    	this.allowMaintain = "";
    	this.confirmationMessage = "";
    	this.currentLogonId = "";
    	this.showAddFields = false;
    	this.showAddress = false;
    	this.showMotionAddFields = false;
    	this.showVRAddFields = false;  
     	this.fileDateStr = "";
     	this.courtActionfiledDate = "";
     	this.offense = "";
     	this.officerName = "";
     	this.programUnit = "";
     	this.los = "";
     	this.managerApprovalDate = null;
     	this.subMgrAppDate = null;
    	this.presentedById = "";
    	this.presentedByFirstName = "";
    	this.presentedByLastName = "";
       	this.presentedByName = "";
    	this.whoSignedId = "";
    	this.whoSignedFirstName = "";
    	this.whoSignedLastName = "";
       	this.whoSignedName = "";    	
       	this.taskCreatorId = "";
       	this.taskflowInd = false;
       	this.taskId = "";
       	this.taskPSCreatorId ="";
    	this.reasonsForTransfer = "";
    	this.hoursOrdered = "";
    	this.hoursCompleted = "";
    	this.totalSpecimensAnalyzed = "";
    	this.recommendations = "";  
    	
    	this.offenseLiteral = "";
    	this.summaryOfCourtActions = "";
    	this.cursorPosition = "";
    	this.superviseeName = "";
    	this.userAgency = "";
    	this.supervisionPeriodId = "";
    	this.violationReportsDisplayList = new ArrayList();
    	this.presentedByList = new ArrayList();
    	this.whoSignedList = new ArrayList();
// create elements for New Violation Report drop down list and check box select    	
//    	this.eventTypes = new ArrayList();
    	this.reasonForTransferList = new ArrayList();
    	this.employmentStatusList = new ArrayList();
    	this.motionsActivities = new ArrayList();
    	this.motionsDispositions = new ArrayList();
    	this.otherActivities = new ArrayList();
//    	this.referralTypes = new ArrayList();
    	this.dischargeReasons =  new ArrayList();
    	this.suggestedCourtActions = new ArrayList();  
  
// grandular clear methods    	
       	this.clearCurrents();
       	this.clearSelecteds();
    	this.clearLawViolationsAdds();
		this.clearFeeHistoryAdds();
		this.clearReportingHistoryAdds();
		this.clearEmploymentHistoryAdds();	
		this.clearTreatmentIssuesAdds();
		this.clearPositiveUrinalysisAdds();
		this.clearViolationReportsAdds();
		this.clearMotionsAdds();
		this.clearOthersAdds();
    }

    public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}

	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}

	public void clearLawViolationsAdds()
    {  
    	this.lvCaseNum = "";
    	this.courtNum = "";
    	this.offenseDateStr = "";
    	this.offenseLiteral = "";
    	this.offenseLevelId = "";
    	this.offenseDegreeId = "";
    }
    public void clearFeeHistoryAdds()
    {  
    	this.payType = "";
    	this.payTypeId = "";
    	this.amountOrdered = "";
    	this.paidToDate = "";
    	this.deliquentAmount = "";
    }  
    public void clearReportingHistoryAdds()
    {  
    	this.occurrenceDateStr = "";
    	this.occurrenceTime = "";
    	this.AMPMId = "";
    	this.selectedEventTypeIds = emptyArrayStr;
    	this.reportingHistoryDetails = "";
    }
    public void clearReportingHistoryAddressInfo()
    {  
    	this.addressType = "";
    	this.addressTypeId = "";
    	this.addressName = "";
    	this.addressNumber = "";
    	this.addressCity = "";
    	this.addressState = "";
    	this.addressStateId = "";
    	this.addressZipCode = "";
    }
    public void clearEmploymentHistoryAdds()
    {  
    	this.employerName = "";
    	this.jobTitle = "";;
    	this.jobStatusId = "";
    	this.jobStartDateStr = "";
    }  
    public void clearTreatmentIssuesAdds()
    {  
 //   	this.selectedReferralTypeId = "";
 //   	this.selectedAgencyId = "";
       	this.programGroupId = "";
       	this.programTypeId = "";
    	this.referralTypeDesc = "";
    	this.serviceProviderName = "";
    	this.referralBeginDateStr = "";
    	this.referralExitDateStr = "";
    	this.selectedDischargeReasonId = "";    	
    }
    public void clearPositiveUrinalysisAdds()
    {  
    	this.testDateStr = "";
    	this.substance = "";   	
    }    
    public void clearViolationReportsAdds()
    {  
    	this.courtActivityVRDateStr = "";
    	this.courtActivityVRSummaryOfCourtActions = "";
    	this.courtActivityVRActivityDesc = "";
    }  
    public void clearMotionsAdds()
    {  
    	this.motionDateStr= "";
    	this.motionSummaryOfCourtActions= "";	
    	this.selectedMotionActivityId= "";
    	this.selectedMotionDispositionId= "";	
    }
    public void clearOthersAdds()
    {  
    	this.otherDateStr= "";
    	this.selectedOtherActivityId= "";
    	this.otherSummaryOfCourtActions = "";
    }  
    public void clearCurrents()
    {
    	this.currentReasonForTransferList = new ArrayList();
    	this.currentReasonForTransfer = "";
    	this.isExtended = "";
    	this.currentReasonForTransferComments = "";
    	this.currentMentalHealthComments = "";
    	this.currentMentalHealthDiagnosis = "";
    	this.currentLawViolationsList = new ArrayList();
    	this.currentLawViolationsComments = "";
    	this.currentFeeHistoryList = new ArrayList();
    	this.currentFeeHistoryComments = "";
    	this.currentReportingHistoryList = new ArrayList();
    	this.currentReportingHistoryComments = "";
    	this.currentEmploymentHistoryList = new ArrayList();
    	this.currentEmploymentHistoryComments = "";
    	this.currentCourtActivityVRList = new ArrayList();
    	this.currentCourtActivityVRComments = "";
    	this.currentMotionsList = new ArrayList();
    	this.currentMotionsComments = "";
    	this.currentOthersList = new ArrayList();
    	this.currentOthersComments = "";
    	this.currentTreatmentIssuesList = new ArrayList();
    	this.currentTreatmentIssuesComments = "";
    	this.currentHoursOrdered = "";
    	this.currentHoursCompleted = "";
    	this.currentCommunityServiceComments = "";
    	this.currentTotalSpecimensAnalyzed = "";
    	this.currentPositiveUrinalysisList = new ArrayList();
    	this.currentPositiveUrinalysisComments = "";
    	this.currentRecommendations = "";
    	this.currentSuggestedCourtActionsList = new ArrayList();
    	this.currentCourtActionsList = new ArrayList();
    }
    /**
     *  Clears previous Comments
     */
    public void clearPreviousComments()
    {
    	this.previousReasonForTransferComments = "";
    	this.previousMentalHealthComments = "";
    	this.previousMentalHealthDiagnosis = "";
    	this.previousLawViolationsComments = "";
    	this.previousFeeHistoryComments = "";
    	this.previousReportingHistoryComments = "";
    	this.previousEmploymentHistoryComments = "";
    	this.previousCourtActivityVRComments = "";
     	this.previousMotionsComments = "";
     	this.previousOthersComments = "";
    	this.previousTreatmentIssuesComments = "";
    	this.previousCommunityServiceComments = "";
    	this.previousPositiveUrinalysisComments = "";
    	this.previousRecommendations = "";
    }
    
    public void clearTemps()
    {
    	this.create1Comments = "";
    	this.create2Comments = "";
    	this.create3Comments = "";
    	this.create1ElementsList = new ArrayList();
    	this.create2ElementsList = new ArrayList();
    	this.create3ElementsList = new ArrayList();
    }
    
    public void clearSelecteds()
    {
    	this.selectedCourtActionsIds = null;
    	this.selectedCourtActivityVRIds = null;
    	this.selectedDischargeReasonId = "";
    	this.selectedEmploymentHistoryIds = null;
    	this.selectedEventTypeIds = null;
    	this.selectedFeeHistoryIds = null;
    	this.selectedLawViolationsIds = null;
    	this.selectedMotionActivityId = "";
    	this.selectedMotionDispositionId = "";
    	this.selectedMotionsIds = null;
    	this.selectedOtherActivityId = "";
    	this.selectedOthersIds = null;
    	this.selectedPositiveUrinalysisIds = null;
    	this.selectedReasonForTransferIds = null;
//    	this.selectedReferralTypeId = "";
    	this.selectedReportingHistoryIds = null;
    	this.selectedStartDateStrs = null;
    	this.selectedSuggestedCourtActionIds = null;
    	this.selectedTreatmentIssuesIds = null;
    	this.selectedValue = "";
    }
/** BEGIN COMMON FORM GETTERS AND SETTERS */    
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	
	public String getAddressTypeId() {
		return addressTypeId;
	}

	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressStateId() {
		return addressStateId;
	}

	public void setAddressStateId(String addressStateId) {
		this.addressStateId = addressStateId;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

/** END COMMON FORM GETTERS AND SETTERS */	

	/**
	 * @return the activeSuggestedCourtActions
	 */
	public List getActiveSuggestedCourtActions() {
		return activeSuggestedCourtActions;
	}
	/**
	 * @param activeSuggestedCourtActions the activeSuggestedCourtActions to set
	 */
	public void setActiveSuggestedCourtActions(List activeSuggestedCourtActions) {
		this.activeSuggestedCourtActions = activeSuggestedCourtActions;
	}
	/**
	 * @return Returns the addItemIndex.
	 */
	public String getAddItemIndex() {
		return addItemIndex;
	}
	/**
	 * @param addItemIndex The addItemIndex to set.
	 */
	public void setAddItemIndex(String addItemIndex) {
		this.addItemIndex = addItemIndex;
	}
	/**
	 * @return Returns the addressCity.
	 */
	public String getAddressCity() {
		return addressCity;
	}
	/**
	 * @param addressCity The addressCity to set.
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	/**
	 * @return Returns the addressName.
	 */
	public String getAddressName() {
		return addressName;
	}
	/**
	 * @param addressName The addressName to set.
	 */
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	/**
	 * @return Returns the addressNumber.
	 */
	public String getAddressNumber() {
		return addressNumber;
	}
	/**
	 * @param addressNumber The addressNumber to set.
	 */
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	/**
	 * @return Returns the addressState.
	 */
	public String getAddressState() {
		return addressState;
	}
	/**
	 * @param addressState The addressState to set.
	 */
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	/**
	 * @return Returns the addressType.
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return Returns the addressZipCode.
	 */
	public String getAddressZipCode() {
		return addressZipCode;
	}
	/**
	 * @param addressZipCode The addressZipCode to set.
	 */
	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}
	/**
	 * @return Returns the agencies.
	 */
//	public List getAgencies() {
//		return Agencies;
//	}
	/**
	 * @param agencies The agencies to set.
	 */
//	public void setAgencies(List agencies) {
//		Agencies = agencies;
//	}
	/**
	 * @return Returns the allowMaintain.
	 */
	public String getAllowMaintain() {
		return allowMaintain;
	}
	/**
	 * @param allowMaintain The allowMaintain to set.
	 */
	public void setAllowMaintain(String allowMaintain) {
		this.allowMaintain = allowMaintain;
	}
	/**
	 * @return Returns the allowUpdate.
	 */
	public String getAllowUpdate() {
		return allowUpdate;
	}
	/**
	 * @param allowUpdate The allowUpdate to set.
	 */
	public void setAllowUpdate(String allowUpdate) {
		this.allowUpdate = allowUpdate;
	}
	/**
	 * @return Returns the amountOrdered.
	 */
	public String getAmountOrdered() {
		return amountOrdered;
	}
	/**
	 * @param amountOrdered The amountOrdered to set.
	 */
	public void setAmountOrdered(String amountOrdered) {
		this.amountOrdered = amountOrdered;
	}
	/**
	 * @return Returns the aMPMId.
	 */
	public String getAMPMId() {
		return AMPMId;
	}
	/**
	 * @param id The aMPMId to set.
	 */
	public void setAMPMId(String id) {
		AMPMId = id;
	}
		
	/**
	 * @return Returns the caseNum.
	 */
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @param caseNum The caseNum to set.
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	/**
	 * @return the caseNoteId
	 */
	public String getCaseNoteId() {
		return caseNoteId;
	}
	/**
	 * @param caseNoteId the caseNoteId to set
	 */
	public void setCaseNoteId(String caseNoteId) {
		this.caseNoteId = caseNoteId;
	}
	/**
	 * @return Returns the cdi.
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi The cdi to set.
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	/**
	 * @return Returns the confirmationMessage.
	 */
	public String getConfirmationMessage() {
		return confirmationMessage;
	}
	/**
	 * @param confirmationMessage The confirmationMessage to set.
	 */
	public void setConfirmationMessage(String confirmationMessage) {
		this.confirmationMessage = confirmationMessage;
	}
	/**
	 * @return the courtActionfiledDate
	 */
	public String getCourtActionfiledDate() {
		return courtActionfiledDate;
	}
	/**
	 * @param courtActionfiledDate the courtActionfiledDate to set
	 */
	public void setCourtActionfiledDate(String courtActionfiledDate) {
		this.courtActionfiledDate = courtActionfiledDate;
	}
	/**
	 * @return Returns the courtActions.
	 */
	public List getCourtActions() {
		return courtActions;
	}
	/**
	 * @param courtActions The courtActions to set.
	 */
	public void setCourtActions(List courtActions) {
		this.courtActions = courtActions;
	}
	/**
	 * @return Returns the courtActivityVRActivityDesc.
	 */
	public String getCourtActivityVRActivityDesc() {
		return courtActivityVRActivityDesc;
	}
	/**
	 * @param courtActivityVRActivityDesc The courtActivityVRActivityDesc to set.
	 */
	public void setCourtActivityVRActivityDesc(String courtActivityVRActivityDesc) {
		this.courtActivityVRActivityDesc = courtActivityVRActivityDesc;
	}
	/**
	 * @return Returns the courtActivityVRDate.
	 */
	public Date getCourtActivityVRDate() {
		return courtActivityVRDate;
	}
	/**
	 * @param courtActivityVRDate The courtActivityVRDate to set.
	 */
	public void setCourtActivityVRDate(Date courtActivityVRDate) {
		this.courtActivityVRDate = courtActivityVRDate;
	}
	/**
	 * @return Returns the courtActivityVRDateStr.
	 */
	public String getCourtActivityVRDateStr() {
		return courtActivityVRDateStr;
	}
	/**
	 * @param courtActivityVRDateStr The courtActivityVRDateStr to set.
	 */
	public void setCourtActivityVRDateStr(String courtActivityVRDateStr) {
		this.courtActivityVRDateStr = courtActivityVRDateStr;
	}
	/**
	 * @return Returns the courtActivityVRSummaryOfCourtActions.
	 */
	public String getCourtActivityVRSummaryOfCourtActions() {
		return courtActivityVRSummaryOfCourtActions;
	}
	/**
	 * @param courtActivityVRSummaryOfCourtActions The courtActivityVRSummaryOfCourtActions to set.
	 */
	public void setCourtActivityVRSummaryOfCourtActions(String courtActivityVRSummaryOfCourtActions) {
		this.courtActivityVRSummaryOfCourtActions = courtActivityVRSummaryOfCourtActions;
	}
	/**
	 * @return Returns the courtNum.
	 */
	public String getCourtNum() {
		return courtNum;
	}
	/**
	 * @param courtNum The courtNum to set.
	 */
	public void setCourtNum(String courtNum) {
		this.courtNum = courtNum;
	}
	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return Returns the createdByName.
	 */
	public String getCreatedByName() {
		return createdByName;
	}
	/**
	 * @param createdByName The createdByName to set.
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	/**
	 * @return Returns the create1Comments.
	 */
	public String getCreate1Comments() {
		return create1Comments;
	}
	/**
	 * @param create1Comments The create1Comments to set.
	 */
	public void setCreate1Comments(String create1Comments) {
		this.create1Comments = create1Comments;
	}
	/**
	 * @return Returns the create1ElementsList.
	 */
	public List getCreate1ElementsList() {
		return create1ElementsList;
	}
	/**
	 * @param create1ElementsList The create1ElementsList to set.
	 */
	public void setCreate1ElementsList(List create1ElementsList) {
		this.create1ElementsList = create1ElementsList;
	}
	/**
	 * @return Returns the create2Comments.
	 */
	public String getCreate2Comments() {
		return create2Comments;
	}
	/**
	 * @param create2Comments The create2Comments to set.
	 */
	public void setCreate2Comments(String create2Comments) {
		this.create2Comments = create2Comments;
	}
	/**
	 * @return Returns the create2ElementsList.
	 */
	public List getCreate2ElementsList() {
		return create2ElementsList;
	}
	/**
	 * @param create2ElementsList The create2ElementsList to set.
	 */
	public void setCreate2ElementsList(List create2ElementsList) {
		this.create2ElementsList = create2ElementsList;
	}
	/**
	 * @return Returns the create3Comments.
	 */
	public String getCreate3Comments() {
		return create3Comments;
	}
	/**
	 * @param create3Comments The create3Comments to set.
	 */
	public void setCreate3Comments(String create3Comments) {
		this.create3Comments = create3Comments;
	}
	/**
	 * @return Returns the create3ElementsList.
	 */
	public List getCreate3ElementsList() {
		return create3ElementsList;
	}
	/**
	 * @param create3ElementsList The create3ElementsList to set.
	 */
	public void setCreate3ElementsList(List create3ElementsList) {
		this.create3ElementsList = create3ElementsList;
	}
	
	/**
	 * @return Returns the currentCommunityServiceComments.
	 */
	public String getCurrentCommunityServiceComments() {
		return currentCommunityServiceComments;
	}
	/**
	 * @param currentCommunityServiceComments The currentCommunityServiceComments to set.
	 */
	public void setCurrentCommunityServiceComments(String currentCommunityServiceComments) {
		this.currentCommunityServiceComments = currentCommunityServiceComments;
	}
	/**
	 * @return the currentCourtActionsList
	 */
	public List getCurrentCourtActionsList() {
		return currentCourtActionsList;
	}
	/**
	 * @param currentCourtActionsList the currentCourtActionsList to set
	 */
	public void setCurrentCourtActionsList(List currentCourtActionsList) {
		this.currentCourtActionsList = currentCourtActionsList;
	}
	/**
	 * @return Returns the currentCourtActivityVRComments.
	 */
	public String getCurrentCourtActivityVRComments() {
		return currentCourtActivityVRComments;
	}
	/**
	 * @param currentCourtActivityVRComments The currentCourtActivityVRComments to set.
	 */
	public void setCurrentCourtActivityVRComments(String currentCourtActivityVRComments) {
		this.currentCourtActivityVRComments = currentCourtActivityVRComments;
	}
	/**
	 * @return Returns the currentCourtActivityVRList.
	 */
	public List getCurrentCourtActivityVRList() {
		return currentCourtActivityVRList;
	}
	/**
	 * @param currentCourtActivityVRList The currentCourtActivityVRList to set.
	 */
	public void setCurrentCourtActivityVRList(List currentCourtActivityVRList) {
		this.currentCourtActivityVRList = currentCourtActivityVRList;
	}
	/**
	 * @return Returns the currentEmploymentHistoryComments.
	 */
	public String getCurrentEmploymentHistoryComments() {
		return currentEmploymentHistoryComments;
	}
	/**
	 * @param currentEmploymentHistoryComments The currentEmploymentHistoryComments to set.
	 */
	public void setCurrentEmploymentHistoryComments(String currentEmploymentHistoryComments) {
		this.currentEmploymentHistoryComments = currentEmploymentHistoryComments;
	}
	/**
	 * @return Returns the currentEmploymentHistoryList.
	 */
	public List getCurrentEmploymentHistoryList() {
		return currentEmploymentHistoryList;
	}
	/**
	 * @param currentEmploymentHistoryList The currentEmploymentHistoryList to set.
	 */
	public void setCurrentEmploymentHistoryList(List currentEmploymentHistoryList) {
		this.currentEmploymentHistoryList = currentEmploymentHistoryList;
	}
	/**
	 * @return Returns the currentFeeHistoryComments.
	 */
	public String getCurrentFeeHistoryComments() {
		return currentFeeHistoryComments;
	}
	/**
	 * @param currentFeeHistoryComments The currentFeeHistoryComments to set.
	 */
	public void setCurrentFeeHistoryComments(String currentFeeHistoryComments) {
		this.currentFeeHistoryComments = currentFeeHistoryComments;
	}
	/**
	 * @return Returns the currentFeeHistoryList.
	 */
	public List getCurrentFeeHistoryList() {
		return currentFeeHistoryList;
	}
	/**
	 * @param currentFeeHistoryList The currentFeeHistoryList to set.
	 */
	public void setCurrentFeeHistoryList(List currentFeeHistoryList) {
		this.currentFeeHistoryList = currentFeeHistoryList;
	}
	/**
	 * @return Returns the currentHoursCompleted.
	 */
	public String getCurrentHoursCompleted() {
		return currentHoursCompleted;
	}
	/**
	 * @param currentHoursCompleted The currentHoursCompleted to set.
	 */
	public void setCurrentHoursCompleted(String currentHoursCompleted) {
		this.currentHoursCompleted = currentHoursCompleted;
	}
	/**
	 * @return Returns the currentHoursOrdered.
	 */
	public String getCurrentHoursOrdered() {
		return currentHoursOrdered;
	}
	/**
	 * @param currentHoursOrdered The currentHoursOrdered to set.
	 */
	public void setCurrentHoursOrdered(String currentHoursOrdered) {
		this.currentHoursOrdered = currentHoursOrdered;
	}
	/**
	 * @return Returns the currentLawViolationsComments.
	 */
	public String getCurrentLawViolationsComments() {
		return currentLawViolationsComments;
	}
	/**
	 * @param currentLawViolationsComments The currentLawViolationsComments to set.
	 */
	public void setCurrentLawViolationsComments(String currentLawViolationsComments) {
		this.currentLawViolationsComments = currentLawViolationsComments;
	}
	/**
	 * @return Returns the currentLawViolationsList.
	 */
	public List getCurrentLawViolationsList() {
		return currentLawViolationsList;
	}
	/**
	 * @param currentLawViolationsList The currentLawViolationsList to set.
	 */
	public void setCurrentLawViolationsList(List currentLawViolationsList) {
		this.currentLawViolationsList = currentLawViolationsList;
	}
	/**
	 * @return the currentLogonId
	 */
	public String getCurrentLogonId() {
		return currentLogonId;
	}
	/**
	 * @param currentLogonId the currentLogonId to set
	 */
	public void setCurrentLogonId(String currentLogonId) {
		this.currentLogonId = currentLogonId;
	}
	/**
	 * @return Returns the currentMotionsComments.
	 */
	public String getCurrentMotionsComments() {
		return currentMotionsComments;
	}
	/**
	 * @param currentMotionsComments The currentMotionsComments to set.
	 */
	public void setCurrentMotionsComments(String currentMotionsComments) {
		this.currentMotionsComments = currentMotionsComments;
	}
	/**
	 * @return Returns the currentMotionsList.
	 */
	public List getCurrentMotionsList() {
		return currentMotionsList;
	}
	/**
	 * @param currentMotionsList The currentMotionsList to set.
	 */
	public void setCurrentMotionsList(List currentMotionsList) {
		this.currentMotionsList = currentMotionsList;
	}
	/**
	 * @return Returns the currentOthersComments.
	 */
	public String getCurrentOthersComments() {
		return currentOthersComments;
	}
	/**
	 * @param currentOthersComments The currentOthersComments to set.
	 */
	public void setCurrentOthersComments(String currentOthersComments) {
		this.currentOthersComments = currentOthersComments;
	}
	/**
	 * @return Returns the currentOthersList.
	 */
	public List getCurrentOthersList() {
		return currentOthersList;
	}
	/**
	 * @param currentOthersList The currentOthersList to set.
	 */
	public void setCurrentOthersList(List currentOthersList) {
		this.currentOthersList = currentOthersList;
	}
	/**
	 * @return Returns the currentPositiveUrinalysisComments.
	 */
	public String getCurrentPositiveUrinalysisComments() {
		return currentPositiveUrinalysisComments;
	}
	/**
	 * @param currentPositiveUrinalysisComments The currentPositiveUrinalysisComments to set.
	 */
	public void setCurrentPositiveUrinalysisComments(String currentPositiveUrinalysisComments) {
		this.currentPositiveUrinalysisComments = currentPositiveUrinalysisComments;
	}
	/**
	 * @return Returns the currentPositiveUrinalysisList.
	 */
	public List getCurrentPositiveUrinalysisList() {
		return currentPositiveUrinalysisList;
	}
	/**
	 * @param currentPositiveUrinalysisList The currentPositiveUrinalysisList to set.
	 */
	public void setCurrentPositiveUrinalysisList(List currentPositiveUrinalysisList) {
		this.currentPositiveUrinalysisList = currentPositiveUrinalysisList;
	}
	/**
	 * @return Returns the currentReasonForTransfer.
	 */
	public String getCurrentReasonForTransfer() {
		return currentReasonForTransfer;
	}
	/**
	 * @param currentReasonForTransfer The currentReasonForTransfer to set.
	 */
	public void setCurrentReasonForTransfer(String currentReasonForTransfer) {
		this.currentReasonForTransfer = currentReasonForTransfer;
	}
	/**
	 * @return Returns the currentReasonForTransferComments.
	 */
	public String getCurrentReasonForTransferComments() {
		return currentReasonForTransferComments;
	}
	/**
	 * @param currentReasonForTransferComments The currentReasonForTransferComments to set.
	 */
	public void setCurrentReasonForTransferComments(String currentReasonForTransferComments) {
		this.currentReasonForTransferComments = currentReasonForTransferComments;
	}
	/**
	 * @return Returns the currentReasonForTransferList.
	 */
	public List getCurrentReasonForTransferList() {
		return currentReasonForTransferList;
	}
	/**
	 * @param currentReasonForTransferList The currentReasonForTransferList to set.
	 */
	public void setCurrentReasonForTransferList(List currentReasonForTransferList) {
		this.currentReasonForTransferList = currentReasonForTransferList;
	}
	/**
	 * @return Returns the currentRecommendations.
	 */
	public String getCurrentRecommendations() {
		return currentRecommendations;
	}
	/**
	 * @param currentRecommendations The currentRecommendations to set.
	 */
	public void setCurrentRecommendations(String currentRecommendations) {
		this.currentRecommendations = currentRecommendations;
	}
	/**
	 * @return Returns the currentReportingHistoryComments.
	 */
	public String getCurrentReportingHistoryComments() {
		return currentReportingHistoryComments;
	}
	/**
	 * @param currentReportingHistoryComments The currentReportingHistoryComments to set.
	 */
	public void setCurrentReportingHistoryComments(String currentReportingHistoryComments) {
		this.currentReportingHistoryComments = currentReportingHistoryComments;
	}
	/**
	 * @return Returns the currentReportingHistoryList.
	 */
	public List getCurrentReportingHistoryList() {
		return currentReportingHistoryList;
	}
	/**
	 * @param currentReportingHistoryList The currentReportingHistoryList to set.
	 */
	public void setCurrentReportingHistoryList(List currentReportingHistoryList) {
		this.currentReportingHistoryList = currentReportingHistoryList;
	}
	/**
	 * @return Returns the currentSuggestedCourtActionsList.
	 */
	public List getCurrentSuggestedCourtActionsList() {
		return currentSuggestedCourtActionsList;
	}
	/**
	 * @param currentSuggestedCourtActionsList The currentSuggestedCourtActionsList to set.
	 */
	public void setCurrentSuggestedCourtActionsList(List currentSuggestedCourtActionsList) {
		this.currentSuggestedCourtActionsList = currentSuggestedCourtActionsList;
	}
	/**
	 * @return Returns the currentTotalSpecimensAnalyzed.
	 */
	public String getCurrentTotalSpecimensAnalyzed() {
		return currentTotalSpecimensAnalyzed;
	}
	/**
	 * @param currentTotalSpecimensAnalyzed The currentTotalSpecimensAnalyzed to set.
	 */
	public void setCurrentTotalSpecimensAnalyzed(String currentTotalSpecimensAnalyzed) {
		this.currentTotalSpecimensAnalyzed = currentTotalSpecimensAnalyzed;
	}
	/**
	 * @return Returns the currentTreatmentIssuesComments.
	 */
	public String getCurrentTreatmentIssuesComments() {
		return currentTreatmentIssuesComments;
	}
	/**
	 * @param currentTreatmentIssuesComments The currentTreatmentIssuesComments to set.
	 */
	public void setCurrentTreatmentIssuesComments(String currentTreatmentIssuesComments) {
		this.currentTreatmentIssuesComments = currentTreatmentIssuesComments;
	}
	/**
	 * @return Returns the currentTreatmentIssuesList.
	 */
	public List getCurrentTreatmentIssuesList() {
		return currentTreatmentIssuesList;
	}
	/**
	 * @param currentTreatmentIssuesList The currentTreatmentIssuesList to set.
	 */
	public void setCurrentTreatmentIssuesList(List currentTreatmentIssuesList) {
		this.currentTreatmentIssuesList = currentTreatmentIssuesList;
	}
	/**
	 * @return Returns the cursorPosition.
	 */
	public String getCursorPosition() {
		return cursorPosition;
	}
	/**
	 * @param cursorPosition The cursorPosition to set.
	 */
	public void setCursorPosition(String cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
	/**
	 * @return Returns the deliquentAmount.
	 */
	public String getDeliquentAmount() {
		return deliquentAmount;
	}
	/**
	 * @param deliquentAmount The deliquentAmount to set.
	 */
	public void setDeliquentAmount(String deliquentAmount) {
		this.deliquentAmount = deliquentAmount;
	}
	/**
	 * @return Returns the dischargeReasons.
	 */
	public List getDischargeReasons() {
		return dischargeReasons;
	}
	/**
	 * @param dischargeReasons The dischargeReasons to set.
	 */
	public void setDischargeReasons(List dischargeReasons) {
		this.dischargeReasons = dischargeReasons;
	}
	/**
	 * @return Returns the employerName.
	 */
	public String getEmployerName() {
		return employerName;
	}
	/**
	 * @param employerName The employerName to set.
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	/**
	 * @return Returns the employmentStatusList.
	 */
	public List getEmploymentStatusList() {
		return employmentStatusList;
	}
	
	public String getIsExtended() {
		return isExtended;
	}

	public void setIsExtended(String isExtended) {
		this.isExtended = isExtended;
	}

	/**
	 * @param employmentStatusList The employmentStatusList to set.
	 */
	public void setEmploymentStatusList(List employmentStatusList) {
		this.employmentStatusList = employmentStatusList;
	}
	/**
	 * @return Returns the eventTypes.
	 */
	public List getEventTypes() {
		return eventTypes;
	}
	/**
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(List eventTypes) {
		this.eventTypes = eventTypes;
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
	/**
	 * @return Returns the hoursCompleted.
	 */
	public String getHoursCompleted() {
		return hoursCompleted;
	}
	/**
	 * @param hoursCompleted The hoursCompleted to set.
	 */
	public void setHoursCompleted(String hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}
	/**
	 * @return Returns the hoursOrdered.
	 */
	public String getHoursOrdered() {
		return hoursOrdered;
	}
	/**
	 * @param hoursOrdered The hoursOrdered to set.
	 */
	public void setHoursOrdered(String hoursOrdered) {
		this.hoursOrdered = hoursOrdered;
	}
	/**
	 * @return Returns the jobStartDateStr.
	 */
	public String getJobStartDateStr() {
		return jobStartDateStr;
	}
	/**
	 * @param jobStartDateStr The jobStartDateStr to set.
	 */
	public void setJobStartDateStr(String jobStartDateStr) {
		this.jobStartDateStr = jobStartDateStr;
	}
	/**
	 * @return Returns the jobStatusId.
	 */
	public String getJobStatusId() {
		return jobStatusId;
	}
	/**
	 * @param jobStatusId The jobStatusId to set.
	 */
	public void setJobStatusId(String jobStatusId) {
		this.jobStatusId = jobStatusId;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return Returns the lastContactDate.
	 */
	public Date getLastContactDate() {
		return lastContactDate;
	}
	/**
	 * @param lastContactDate The lastContactDate to set.
	 */
	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	/**
	 * @return the los
	 */
	public String getLos() {
		return los;
	}
	/**
	 * @param los the los to set
	 */
	public void setLos(String los) {
		this.los = los;
	}
	/**
	 * @return the managerApprovalDate
	 */
	public Date getManagerApprovalDate() {
		return managerApprovalDate;
	}

	/**
	 * @param managerApprovalDate the managerApprovalDate to set
	 */
	public void setManagerApprovalDate(Date managerApprovalDate) {
		this.managerApprovalDate = managerApprovalDate;
	}
	/**
	 * @return Returns the motionDate.
	 */
	public Date getMotionDate() {
		return motionDate;
	}
	/**
	 * @param motionDate The motionDate to set.
	 */
	public void setMotionDate(Date motionDate) {
		this.motionDate = motionDate;
	}
	/**
	 * @return Returns the motionDateStr.
	 */
	public String getMotionDateStr() {
		return motionDateStr;
	}
	/**
	 * @param motionDateStr The motionDateStr to set.
	 */
	public void setMotionDateStr(String motionDateStr) {
		this.motionDateStr = motionDateStr;
	}
	/**
	 * @return Returns the motionsActivities.
	 */
	public List getMotionsActivities() {
		return motionsActivities;
	}
	/**
	 * @param motionsActivities The motionsActivities to set.
	 */
	public void setMotionsActivities(List motionsActivities) {
		this.motionsActivities = motionsActivities;
	}
	/**
	 * @return Returns the motionsDispositions.
	 */
	public List getMotionsDispositions() {
		return motionsDispositions;
	}
	/**
	 * @param motionsDispositions The motionsDispositions to set.
	 */
	public void setMotionsDispositions(List motionsDispositions) {
		this.motionsDispositions = motionsDispositions;
	}
	/**
	 * @return Returns the motionSummaryOfCourtActions.
	 */
	public String getMotionSummaryOfCourtActions() {
		return motionSummaryOfCourtActions;
	}
	/**
	 * @param motionSummaryOfCourtActions The motionSummaryOfCourtActions to set.
	 */
	public void setMotionSummaryOfCourtActions(String motionSummaryOfCourtActions) {
		this.motionSummaryOfCourtActions = motionSummaryOfCourtActions;
	}
	/**
	 * @return Returns the occurrenceDate.
	 */
	public Date getOccurrenceDate() {
		return occurrenceDate;
	}
	/**
	 * @param occurrenceDate The occurrenceDate to set.
	 */
	public void setOccurrenceDate(Date occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
	}
	/**
	 * @return Returns the occurrenceDateStr.
	 */
	public String getOccurrenceDateStr() {
		return occurrenceDateStr;
	}
	/**
	 * @param occurrenceDateStr The occurrenceDateStr to set.
	 */
	public void setOccurrenceDateStr(String occurrenceDateStr) {
		this.occurrenceDateStr = occurrenceDateStr;
	}
	/**
	 * @return Returns the occurrenceTime.
	 */
	public String getOccurrenceTime() {
		return occurrenceTime;
	}
	/**
	 * @param occurrenceTime The occurrenceTime to set.
	 */
	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}
	/**
	 * @return the offense
	 */
	public String getOffense() {
		return offense;
	}
	/**
	 * @param offense the offense to set
	 */
	public void setOffense(String offense) {
		this.offense = offense;
	}	
	/**
	 * @return Returns the offenseDate.
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}
	/**
	 * @param offenseDate The offenseDate to set.
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	/**
	 * @return Returns the offenseDateStr.
	 */
	public String getOffenseDateStr() {
		return offenseDateStr;
	}
	/**
	 * @param offenseDateStr The offenseDateStr to set.
	 */
	public void setOffenseDateStr(String offenseDateStr) {
		this.offenseDateStr = offenseDateStr;
	}
	/**
	 * @return Returns the offenseDegreeId.
	 */
	public String getOffenseDegreeId() {
		return offenseDegreeId;
	}
	/**
	 * @param offenseDegreeId The offenseDegreeId to set.
	 */
	public void setOffenseDegreeId(String offenseDegreeId) {
		this.offenseDegreeId = offenseDegreeId;
	}
	/**
	 * @return Returns the offenseLevelId.
	 */
	public String getOffenseLevelId() {
		return offenseLevelId;
	}
	/**
	 * @param offenseLevelId The offenseLevelId to set.
	 */
	public void setOffenseLevelId(String offenseLevelId) {
		this.offenseLevelId = offenseLevelId;
	}
	/**
	 * @return Returns the offenseLiteral.
	 */
	public String getOffenseLiteral() {
		return offenseLiteral;
	}
	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderStatus
	 */	
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus The orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @param offenseLiteral The offenseLiteral to set.
	 */
	public void setOffenseLiteral(String offenseLiteral) {
		this.offenseLiteral = offenseLiteral;
	}
	/**
	 * @return Returns the otherActivities.
	 */
	public List getOtherActivities() {
		return otherActivities;
	}
	/**
	 * @param otherActivities The otherActivities to set.
	 */
	public void setOtherActivities(List otherActivities) {
		this.otherActivities = otherActivities;
	}
	/**
	 * @return Returns the otherDate.
	 */
	public Date getOtherDate() {
		return otherDate;
	}
	/**
	 * @param otherDate The otherDate to set.
	 */
	public void setOtherDate(Date otherDate) {
		this.otherDate = otherDate;
	}
	/**
	 * @return Returns the otherDateStr.
	 */
	public String getOtherDateStr() {
		return otherDateStr;
	}
	/**
	 * @param otherDateStr The otherDateStr to set.
	 */
	public void setOtherDateStr(String otherDateStr) {
		this.otherDateStr = otherDateStr;
	}
	/**
	 * @return Returns the paidToDate.
	 */
	public String getPaidToDate() {
		return paidToDate;
	}
	/**
	 * @param paidToDate The paidToDate to set.
	 */
	public void setPaidToDate(String paidToDate) {
		this.paidToDate = paidToDate;
	}
	/**
	 * @return Returns the payType.
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * @param payType The payType to set.
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * @return Returns the payTypeId.
	 */
	public String getPayTypeId() {
		return payTypeId;
	}
	/**
	 * @param payTypeId The payTypeId to set.
	 */
	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
	}
	/**
	 * @return Returns the payTypes.
	 */
	public List getPayTypes() {
		return payTypes;
	}
	/**
	 * @param payTypes The payTypes to set.
	 */
	public void setPayTypes(List payTypes) {
		this.payTypes = payTypes;
	}
	/**
	 * @return Returns the presentedById.
	 */
	public String getPresentedById() {
		return presentedById;
	}
	/**
	 * @param presentedBy The presentedBy to set.
	 */
	public void setPresentedById(String presentedById) {
		this.presentedById = presentedById;
	}
	/**
	 * @return Returns the presentedByFirstName.
	 */
	public String getPresentedByFirstName() {
		return presentedByFirstName;
	}
	/**
	 * @param presentedByFirstName The presentedByFirstName to set.
	 */
	public void setPresentedByFirstName(String presentedByFirstName) {
		this.presentedByFirstName = presentedByFirstName;
	}
	/**
	 * @return Returns the presentedByLastName.
	 */
	public String getPresentedByLastName() {
		return presentedByLastName;
	}
	/**
	 * @param presentedByLastName The presentedByLastName to set.
	 */
	public void setPresentedByLastName(String presentedByLastName) {
		this.presentedByLastName = presentedByLastName;
	}
	/**
	 * @return the presentedByList
	 */
	public List getPresentedByList() {
		return presentedByList;
	}
	/**
	 * @param presentedByList the presentedByList to set
	 */
	public void setPresentedByList(List presentedByList) {
		this.presentedByList = presentedByList;
	}
	/**
	 * @return Returns the presentedByName.
	 */
	public String getPresentedByName() {
		return presentedByName;
	}
	/**
	 * @param presentedByName The presentedByName to set.
	 */
	public void setPresentedByName(String presentedByName) {
		this.presentedByName = presentedByName;
	}
	/**
	 * @return the programUnit
	 */
	public String getProgramUnit() {
		return programUnit;
	}
	/**
	 * @param programUnit the programUnit to set
	 */
	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}
	/**
	 * @return the reasonForTransferList
	 */
	public List getReasonForTransferList() {
		return reasonForTransferList;
	}
	/**
	 * @param reasonForTransferList the reasonForTransferList to set
	 */
	public void setReasonForTransferList(List reasonForTransferList) {
		this.reasonForTransferList = reasonForTransferList;
	}
	/**
	 * @return Returns the reasonsForTransfer.
	 */
	public String getReasonsForTransfer() {
		return reasonsForTransfer;
	}
	/**
	 * @param reasonsForTransfer The reasonsForTransfer to set.
	 */
	public void setReasonsForTransfer(String reasonsForTransfer) {
		this.reasonsForTransfer = reasonsForTransfer;
	}
	/**
	 * @return Returns the recommendations.
	 */
	public String getRecommendations() {
		return recommendations;
	}
	/**
	 * @param recommendations The recommendations to set.
	 */
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
	/**
	 * @return Returns the referralBeginDate.
	 */
	public Date getReferralBeginDate() {
		return referralBeginDate;
	}
	/**
	 * @param referralBeginDate The referralBeginDate to set.
	 */
	public void setReferralBeginDate(Date referralBeginDate) {
		this.referralBeginDate = referralBeginDate;
	}
	/**
	 * @return Returns the referralBeginDateStr.
	 */
	public String getReferralBeginDateStr() {
		return referralBeginDateStr;
	}
	/**
	 * @param referralBeginDateStr The referralBeginDateStr to set.
	 */
	public void setReferralBeginDateStr(String referralBeginDateStr) {
		this.referralBeginDateStr = referralBeginDateStr;
	}
	/**
	 * @return Returns the referralExitDate.
	 */
	public Date getReferralExitDate() {
		return referralExitDate;
	}
	/**
	 * @param referralExitDate The referralExitDate to set.
	 */
	public void setReferralExitDate(Date referralExitDate) {
		this.referralExitDate = referralExitDate;
	}
	/**
	 * @return Returns the referralExitDateStr.
	 */
	public String getReferralExitDateStr() {
		return referralExitDateStr;
	}
	/**
	 * @param referralExitDateStr The referralExitDateStr to set.
	 */
	public void setReferralExitDateStr(String referralExitDateStr) {
		this.referralExitDateStr = referralExitDateStr;
	}
	/**
	 * @return Returns the referralTypes.
	 */
//	public List getReferralTypes() {
//		return referralTypes;
//	}
//	/**
//	 * @param referralTypes The referralTypes to set.
//	 */
//	public void setReferralTypes(List referralTypes) {
//		this.referralTypes = referralTypes;
//	}
	/**
	 * @return Returns the referralTypeDesc.
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	/**
	 * @param referralType The referralType to set.
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
	}
	/**
	 * @return Returns the reportingHistoryDetails.
	 */
	public String getReportingHistoryDetails() {
		return reportingHistoryDetails;
	}
	/**
	 * @param reportingHistoryDetails The reportingHistoryDetails to set.
	 */
	public void setReportingHistoryDetails(String reportingHistoryDetails) {
		this.reportingHistoryDetails = reportingHistoryDetails;
	}
	/**
	 * @return Returns the selectedAgencyId.
	 */
//	public String getSelectedAgencyId() {
//		return selectedAgencyId;
//	}
	/**
	 * @param selectedAgencyId The selectedAgencyId to set.
	 */
//	public void setSelectedAgencyId(String selectedAgencyId) {
//		this.selectedAgencyId = selectedAgencyId;
//	}
	/**
	 * @return Returns the selectedCourtActionsIds.
	 */
	public String[] getSelectedCourtActionsIds() {
		return selectedCourtActionsIds;
	}
	/**
	 * @param selectedCourtActionsIds The selectedCourtActionsIds to set.
	 */
	public void setSelectedCourtActionsIds(String[] selectedCourtActionsIds) {
		this.selectedCourtActionsIds = selectedCourtActionsIds;
	}
	/**
	 * @return Returns the selectedCourtActivityVRIds.
	 */
	public String[] getSelectedCourtActivityVRIds() {
		return selectedCourtActivityVRIds;
	}
	/**
	 * @param selectedCourtActivityVRIds The selectedCourtActivityVRIds to set.
	 */
	public void setSelectedCourtActivityVRIds(String[] selectedCourtActivityVRIds) {
		this.selectedCourtActivityVRIds = selectedCourtActivityVRIds;
	}
	/**
	 * @return Returns the selectedDischargeReasonId.
	 */
	public String getSelectedDischargeReasonId() {
		return selectedDischargeReasonId;
	}
	/**
	 * @param selectedDischargeReasonId The selectedDischargeReasonId to set.
	 */
	public void setSelectedDischargeReasonId(String selectedDischargeReasonId) {
		this.selectedDischargeReasonId = selectedDischargeReasonId;
	}
	/**
	 * @return Returns the selectedEmploymentHistoryIds.
	 */
	public String[] getSelectedEmploymentHistoryIds() {
		return selectedEmploymentHistoryIds;
	}
	/**
	 * @param selectedEmploymentHistoryIds The selectedEmploymentHistoryIds to set.
	 */
	public void setSelectedEmploymentHistoryIds(String[] selectedEmploymentHistoryIds) {
		this.selectedEmploymentHistoryIds = selectedEmploymentHistoryIds;
	}
	/**
	 * @return Returns the selectedEventTypeIds.
	 */
	public String[] getSelectedEventTypeIds() {
		return selectedEventTypeIds;
	}
	/**
	 * @param selectedEventTypeIds The selectedEventTypeIds to set.
	 */
	public void setSelectedEventTypeIds(String[] selectedEventTypeIds) {
		this.selectedEventTypeIds = selectedEventTypeIds;
	}
	/**
	 * @return Returns the selectedFeeHistoryIds.
	 */
	public String[] getSelectedFeeHistoryIds() {
		return selectedFeeHistoryIds;
	}
	/**
	 * @param selectedFeeHistoryIds The selectedFeeHistoryIds to set.
	 */
	public void setSelectedFeeHistoryIds(String[] selectedFeeHistoryIds) {
		this.selectedFeeHistoryIds = selectedFeeHistoryIds;
	}
	/**
	 * @return Returns the selectedLawViolationsIds.
	 */
	public String[] getSelectedLawViolationsIds() {
		return selectedLawViolationsIds;
	}
	/**
	 * @param selectedLawViolationsIds The selectedLawViolationsIds to set.
	 */
	public void setSelectedLawViolationsIds(String[] selectedLawViolationsIds) {
		this.selectedLawViolationsIds = selectedLawViolationsIds;
	}
	/**
	 * @return Returns the selectedMotionActivityId.
	 */
	public String getSelectedMotionActivityId() {
		return selectedMotionActivityId;
	}
	/**
	 * @param selectedMotionActivityId The selectedMotionActivityId to set.
	 */
	public void setSelectedMotionActivityId(String selectedMotionActivityId) {
		this.selectedMotionActivityId = selectedMotionActivityId;
	}
	/**
	 * @return Returns the selectedMotionDispositionId.
	 */
	public String getSelectedMotionDispositionId() {
		return selectedMotionDispositionId;
	}
	/**
	 * @param selectedMotionDispositionId The selectedMotionDispositionId to set.
	 */
	public void setSelectedMotionDispositionId(String selectedMotionDispositionId) {
		this.selectedMotionDispositionId = selectedMotionDispositionId;
	}
	/**
	 * @return Returns the selectedMotionsIds.
	 */
	public String[] getSelectedMotionsIds() {
		return selectedMotionsIds;
	}
	/**
	 * @param selectedMotionsIds The selectedMotionsIds to set.
	 */
	public void setSelectedMotionsIds(String[] selectedMotionsIds) {
		this.selectedMotionsIds = selectedMotionsIds;
	}
	/**
	 * @return Returns the selectedOtherActivityId.
	 */
	public String getSelectedOtherActivityId() {
		return selectedOtherActivityId;
	}
	/**
	 * @param selectedOtherActivityId The selectedOtherActivityId to set.
	 */
	public void setSelectedOtherActivityId(String selectedOtherActivityId) {
		this.selectedOtherActivityId = selectedOtherActivityId;
	}
	/**
	 * @return Returns the selectedOthersIds.
	 */
	public String[] getSelectedOthersIds() {
		return selectedOthersIds;
	}
	/**
	 * @param selectedOthersIds The selectedOthersIds to set.
	 */
	public void setSelectedOthersIds(String[] selectedOthersIds) {
		this.selectedOthersIds = selectedOthersIds;
	}
	/**
	 * @return Returns the selectedPositiveUrinalysisIds.
	 */
	public String[] getSelectedPositiveUrinalysisIds() {
		return selectedPositiveUrinalysisIds;
	}
	/**
	 * @param selectedPositiveUrinalysisIds The selectedPositiveUrinalysisIds to set.
	 */
	public void setSelectedPositiveUrinalysisIds(String[] selectedPositiveUrinalysisIds) {
		this.selectedPositiveUrinalysisIds = selectedPositiveUrinalysisIds;
	}
	/**
	 * @return the selectedReasonForTransferIds
	 */
	public String[] getSelectedReasonForTransferIds() {
		return selectedReasonForTransferIds;
	}
	/**
	 * @param selectedReasonForTransferIds the selectedReasonForTransferIds to set
	 */
	public void setSelectedReasonForTransferIds(
			String[] selectedReasonForTransferIds) {
		this.selectedReasonForTransferIds = selectedReasonForTransferIds;
	}
	/**
	 * @return Returns the selectedReferralTypeId.
	 */
//	public String getSelectedReferralTypeId() {
//		return selectedReferralTypeId;
//	}
	/**
	 * @param selectedReferralTypeId The selectedReferralTypeId to set.
	 */
//	public void setSelectedReferralTypeId(String selectedReferralTypeId) {
//		this.selectedReferralTypeId = selectedReferralTypeId;
//	}
	/**
	 * @return Returns the selectedReportingHistoryIds.
	 */
	public String[] getSelectedReportingHistoryIds() {
		return selectedReportingHistoryIds;
	}
	public String getCurrentMentalHealthComments() {
		return currentMentalHealthComments;
	}

	public void setCurrentMentalHealthComments(String currentMentalHealthComments) {
		this.currentMentalHealthComments = currentMentalHealthComments;
	}

	public String getCurrentMentalHealthDiagnosis() {
		return currentMentalHealthDiagnosis;
	}

	public void setCurrentMentalHealthDiagnosis(String currentMentalHealthDiagnosis) {
		this.currentMentalHealthDiagnosis = currentMentalHealthDiagnosis;
	}

	public String getPreviousMentalHealthComments() {
		return previousMentalHealthComments;
	}

	public void setPreviousMentalHealthComments(String previousMentalHealthComments) {
		this.previousMentalHealthComments = previousMentalHealthComments;
	}

	public String getPreviousMentalHealthDiagnosis() {
		return previousMentalHealthDiagnosis;
	}

	public void setPreviousMentalHealthDiagnosis(
			String previousMentalHealthDiagnosis) {
		this.previousMentalHealthDiagnosis = previousMentalHealthDiagnosis;
	}

	/**
	 * @param selectedReportingHistoryIds The selectedReportingHistoryIds to set.
	 */
	public void setSelectedReportingHistoryIds(String[] selectedReportingHistoryIds) {
		this.selectedReportingHistoryIds = selectedReportingHistoryIds;
	}
	/**
	 * @return the selectedStartDateStrs
	 */
	public String[] getSelectedStartDateStrs() {
		return selectedStartDateStrs;
	}
	/**
	 * @param selectedStartDateStrs the selectedStartDateStrs to set
	 */
	public void setSelectedStartDateStrs(String[] selectedStartDateStrs) {
		this.selectedStartDateStrs = selectedStartDateStrs;
	}
	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the substance.
	 */
	public String getSubstance() {
		return substance;
	}
	/**
	 * @param substance The substance to set.
	 */
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	/**
	 * @return Returns the selectedSuggestedCourtActionIds.
	 */
	public String[] getSelectedSuggestedCourtActionIds() {
		return selectedSuggestedCourtActionIds;
	}
	/**
	 * @param selectedSuggestedCourtActionIds The selectedSuggestedCourtActionIds to set.
	 */
	public void setSelectedSuggestedCourtActionIds(String[] selectedSuggestedCourtActionIds) {
		this.selectedSuggestedCourtActionIds = selectedSuggestedCourtActionIds;
	}
	/**
	 * @return Returns the selectedTreatmentIssuesIds.
	 */
	public String[] getSelectedTreatmentIssuesIds() {
		return selectedTreatmentIssuesIds;
	}
	/**
	 * @param selectedTreatmentIssuesIds The selectedTreatmentIssuesIds to set.
	 */
	public void setSelectedTreatmentIssuesIds(String[] selectedTreatmentIssuesIds) {
		this.selectedTreatmentIssuesIds = selectedTreatmentIssuesIds;
	}
	/**
	 * @return Returns the showAddFields.
	 */
	public boolean isShowAddFields() {
		return showAddFields;
	}
	/**
	 * @return Returns the showAddFields.
	 */
	public void setShowAddFields(boolean showAddFields) {
		this.showAddFields = showAddFields;
	}

	/**
	 * @return Returns the showAddress.
	 */
	public boolean isShowAddress() {
		return showAddress;
	}
	/**
	 * @param showAddress The showAddress to set.
	 */
	public void setShowAddress(boolean showAddress) {
		this.showAddress = showAddress;
	}
	/**
	 * @return Returns the showMotionAddFields.
	 */
	public boolean isShowMotionAddFields() {
		return showMotionAddFields;
	}
	/**
	 * @return Returns the showMotionAddFields.
	 */
	public void setShowMotionAddFields(boolean showMotionAddFields) {
		this.showMotionAddFields = showMotionAddFields;
	}
	/**
	 * @return Returns the showVRAddFields.
	 */
	public boolean isShowVRAddFields() {
		return showVRAddFields;
	}
	/**
	 * @return Returns the showVRAddFields.
	 */
	public void setShowVRAddFields(boolean showVRAddFields) {
		this.showVRAddFields = showVRAddFields;
	}
	/**
	 * @return Returns the statusChangedDate.
	 */
	public Date getStatusChangedDate() {
		return statusChangedDate;
	}
	/**
	 * @param statusChangedDate The statusChangedDate to set.
	 */
	public void setStatusChangedDate(Date statusChangedDate) {
		this.statusChangedDate = statusChangedDate;
	}
	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
	/**
	 * @return Returns the substances.
	 */
//	public List getSubstances() {
//		return substances;
//	}
	/**
	 * @param substances The substances to set.
	 */
//	public void setSubstances(List substances) {
//		this.substances = substances;
//	}
	/**
	 * @return Returns the suggestedCourtActions.
	 */
	public List getSuggestedCourtActions() {
		return suggestedCourtActions;
	}
	/**
	 * @param suggestedCourtActions The suggestedCourtActions to set.
	 */
	public void setSuggestedCourtActions(List suggestedCourtActions) {
		this.suggestedCourtActions = suggestedCourtActions;
	}
	/**
	 * @return Returns the summaryOfCourtActions.
	 */
	public String getSummaryOfCourtActions() {
		return summaryOfCourtActions;
	}
	/**
	 * @param summaryOfCourtActions The summaryOfCourtActions to set.
	 */
	public void setSummaryOfCourtActions(String summaryOfCourtActions) {
		this.summaryOfCourtActions = summaryOfCourtActions;
	}
	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return the taskCreatorId
	 */
	public String getTaskCreatorId() {
		return taskCreatorId;
	}
	/**
	 * @param taskCreatorId the taskCreatorId to set
	 */
	public void setTaskCreatorId(String taskCreatorId) {
		this.taskCreatorId = taskCreatorId;
	}
	/**
	 * @return the taskflowInd
	 */
	public boolean isTaskflowInd() {
		return taskflowInd;
	}
	/**
	 * @param taskflowInd the taskflowInd to set
	 */
	public void setTaskflowInd(boolean taskflowInd) {
		this.taskflowInd = taskflowInd;
	}
	/**
	 * @return the taskNextAction
	 */
	public String getTaskNextAction() {
		return taskNextAction;
	}
	/**
	 * @param taskNextAction the taskNextAction to set
	 */
	public void setTaskNextAction(String taskNextAction) {
		this.taskNextAction = taskNextAction;
	}
	/**
	 * @return the taskPSCreatorId
	 */
	public String getTaskPSCreatorId() {
		return taskPSCreatorId;
	}
	/**
	 * @param taskPSCreatorId the taskPSCreatorId to set
	 */
	public void setTaskPSCreatorId(String taskPSCreatorId) {
		this.taskPSCreatorId = taskPSCreatorId;
	}
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the taskSubject
	 */
	public String getTaskSubject() {
		return taskSubject;
	}
	/**
	 * @param taskSubject the taskSubject to set
	 */
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
	/**
	 * @return the taskText
	 */
	public String getTaskText() {
		return taskText;
	}
	/**
	 * @param taskText the taskText to set
	 */
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	/**
	 * @return the taskTo
	 */
	public String getTaskTo() {
		return taskTo;
	}
	/**
	 * @param taskTo the taskTo to set
	 */
	public void setTaskTo(String taskTo) {
		this.taskTo = taskTo;
	}
	/**
	 * @return Returns the testDate.
	 */
	public Date getTestDate() {
		return testDate;
	}
	/**
	 * @param testDate The testDate to set.
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	/**
	 * @return Returns the testDateStr.
	 */
	public String getTestDateStr() {
		return testDateStr;
	}
	/**
	 * @param testDateStr The testDateStr to set.
	 */
	public void setTestDateStr(String testDateStr) {
		this.testDateStr = testDateStr;
	}
	/**
	 * @return Returns the totalSpecimensAnalyzed.
	 */
	public String getTotalSpecimensAnalyzed() {
		return totalSpecimensAnalyzed;
	}
	/**
	 * @param totalSpecimensAnalyzed The totalSpecimensAnalyzed to set.
	 */
	public void setTotalSpecimensAnalyzed(String totalSpecimensAnalyzed) {
		this.totalSpecimensAnalyzed = totalSpecimensAnalyzed;
	}
	/**
	 * @return Returns the violationReportsDisplayList.
	 */
	public List getViolationReportsDisplayList() {
		return violationReportsDisplayList;
	}
	/**
	 * @param violationReportsDisplayList The violationReportsDisplayList to set.
	 */
	public void setViolationReportsDisplayList(List violationReportsDisplayList) {
		this.violationReportsDisplayList = violationReportsDisplayList;
	}
	/**
	 * @return Returns the violationReportId.
	 */
	public String getViolationReportId() {
		return violationReportId;
	}
	/**
	 * @param violationReportId The violationReportId to set.
	 */
	public void setViolationReportId(String violationReportId) {
		this.violationReportId = violationReportId;
	}
	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	/**
	 * @return Returns the whoSignedId.
	 */
	public String getWhoSignedId() {
		return whoSignedId;
	}
	/**
	 * @param whoSignedId The whoSignedId to set.
	 */
	public void setWhoSignedId(String whoSignedId) {
		this.whoSignedId = whoSignedId;
	}
	/**
	 * @return Returns the whoSignedFirstName.
	 */
	public String getWhoSignedFirstName() {
		return whoSignedFirstName;
	}
	/**
	 * @param whoSignedFirstName The whoSignedFirstName to set.
	 */
	public void setWhoSignedFirstName(String whoSignedFirstName) {
		this.whoSignedFirstName = whoSignedFirstName;
	}
	/**
	 * @return Returns the whoSignedLastName.
	 */
	public String getWhoSignedLastName() {
		return whoSignedLastName;
	}
	/**
	 * @param whoSignedLastName The whoSignedLastName to set.
	 */
	public void setWhoSignedLastName(String whoSignedLastName) {
		this.whoSignedLastName = whoSignedLastName;
	}
	/**
	 * @return the whoSignedList
	 */
	public Collection getWhoSignedList() {
		return whoSignedList;
	}
	/**
	 * @param whoSignedList the whoSignedList to set
	 */
	public void setWhoSignedList(Collection whoSignedList) {
		this.whoSignedList = whoSignedList;
	}
	/**
	 * @return Returns the whoSignedName.
	 */
	public String getWhoSignedName() {
		return whoSignedName;
	}
	/**
	 * @param whoSignedName The whoSignedName to set.
	 */
	public void setWhoSignedName(String whoSignedName) {
		this.whoSignedName = whoSignedName;
	}

	/**
	 * @return the orderActivationDate
	 */
	public String getOrderActivationDate() {
		return orderActivationDate;
	}

	/**
	 * @param orderActivationDate the orderActivationDate to set
	 */
	public void setOrderActivationDate(String orderActivationDate) {
		this.orderActivationDate = orderActivationDate;
	}
	
	public String getOtherSummaryOfCourtActions() {
		return otherSummaryOfCourtActions;
	}

	public void setOtherSummaryOfCourtActions(String otherSummaryOfCourtActions) {
		this.otherSummaryOfCourtActions = otherSummaryOfCourtActions;
	}
	
	public String getLvCaseNum() {
		return lvCaseNum;
	}

	public void setLvCaseNum(String lvCaseNum) {
		this.lvCaseNum = lvCaseNum;
	}

	public String getTaskToStaffId() {
		return taskToStaffId;
	}

	public void setTaskToStaffId(String taskToStaffId) {
		this.taskToStaffId = taskToStaffId;
	}

	public boolean isCloPosition() {
		return cloPosition;
	}

	public void setCloPosition(boolean cloPosition) {
		this.cloPosition = cloPosition;
	}

	public String getPreviousReasonForTransferComments() {
		return previousReasonForTransferComments;
	}

	public void setPreviousReasonForTransferComments(
			String previousReasonForTransferComments) {
		this.previousReasonForTransferComments = previousReasonForTransferComments;
	}

	public String getPreviousLawViolationsComments() {
		return previousLawViolationsComments;
	}

	public void setPreviousLawViolationsComments(
			String previousLawViolationsComments) {
		this.previousLawViolationsComments = previousLawViolationsComments;
	}

	public String getPreviousFeeHistoryComments() {
		return previousFeeHistoryComments;
	}

	public void setPreviousFeeHistoryComments(String previousFeeHistoryComments) {
		this.previousFeeHistoryComments = previousFeeHistoryComments;
	}

	public String getPreviousReportingHistoryComments() {
		return previousReportingHistoryComments;
	}

	public void setPreviousReportingHistoryComments(
			String previousReportingHistoryComments) {
		this.previousReportingHistoryComments = previousReportingHistoryComments;
	}

	public String getPreviousEmploymentHistoryComments() {
		return previousEmploymentHistoryComments;
	}

	public void setPreviousEmploymentHistoryComments(
			String previousEmploymentHistoryComments) {
		this.previousEmploymentHistoryComments = previousEmploymentHistoryComments;
	}

	public String getPreviousCourtActivityVRComments() {
		return previousCourtActivityVRComments;
	}

	public void setPreviousCourtActivityVRComments(
			String previousCourtActivityVRComments) {
		this.previousCourtActivityVRComments = previousCourtActivityVRComments;
	}

	public String getPreviousMotionsComments() {
		return previousMotionsComments;
	}

	public void setPreviousMotionsComments(String previousMotionsComments) {
		this.previousMotionsComments = previousMotionsComments;
	}

	public String getPreviousOthersComments() {
		return previousOthersComments;
	}

	public void setPreviousOthersComments(String previousOthersComments) {
		this.previousOthersComments = previousOthersComments;
	}

	public String getPreviousTreatmentIssuesComments() {
		return previousTreatmentIssuesComments;
	}

	public void setPreviousTreatmentIssuesComments(
			String previousTreatmentIssuesComments) {
		this.previousTreatmentIssuesComments = previousTreatmentIssuesComments;
	}

	public String getPreviousCommunityServiceComments() {
		return previousCommunityServiceComments;
	}

	public void setPreviousCommunityServiceComments(
			String previousCommunityServiceComments) {
		this.previousCommunityServiceComments = previousCommunityServiceComments;
	}

	public String getPreviousPositiveUrinalysisComments() {
		return previousPositiveUrinalysisComments;
	}

	public void setPreviousPositiveUrinalysisComments(
			String previousPositiveUrinalysisComments) {
		this.previousPositiveUrinalysisComments = previousPositiveUrinalysisComments;
	}

	public String getPreviousRecommendations() {
		return previousRecommendations;
	}

	public void setPreviousRecommendations(String previousRecommendations) {
		this.previousRecommendations = previousRecommendations;
	}

	public String getPreviousSummaryOfCourtActions() {
		return previousSummaryOfCourtActions;
	}

	public void setPreviousSummaryOfCourtActions(
			String previousSummaryOfCourtActions) {
		this.previousSummaryOfCourtActions = previousSummaryOfCourtActions;
	}

	/**
	 * @return the programGroupId
	 */
	public String getProgramGroupId() {
		return programGroupId;
	}

	/**
	 * @param programGroupId the programGroupId to set
	 */
	public void setProgramGroupId(String programGroupId) {
		this.programGroupId = programGroupId;
	}

	/**
	 * @return the programTypeId
	 */
	public String getProgramTypeId() {
		return programTypeId;
	}

	/**
	 * @param programTypeId the programTypeId to set
	 */
	public void setProgramTypeId(String programTypeId) {
		this.programTypeId = programTypeId;
	}

	/**
	 * @param userAgency the userAgency to set
	 */
	
	public void setUserAgency(String userAgency) {
		this.userAgency = userAgency;
	}
	/**

	/**
	 * @return the userAgency
	 */
	public String getUserAgency() {
		return UIUtil.getCurrentUserAgencyID();
	}
	
}