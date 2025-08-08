/*
 * Created on Jul 21, 2011
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 * 
 */
public class JuvenileNonComplianceForm extends ActionForm
{
	private String action;
	private List actionTakenCodes;
	private String actionTakenDesc;
	private String actionTakenId;
	private String caseAssignmentDateStr;
	private String caseStatusId;
	private Date completionDate;
	private String completionDateStr;
	private String confirmationMsg = "";
	private String currentDateStr;
	private List documents;
	private List existingNoticesList;
	private List emptyList = new ArrayList();
	private String juvenileFullName;
	private String juvenileCompletedStatus;
	private String juvenileCompletedStatusLit;
	private String juvenileCompletedComments;
	private String juvenileNum;
	private String juvenileSignedStatus;
	private Date nonComplianceDate;
	private String nonComplianceDateStr;
	private Date nonComplianceEntryDate;
	private String nonComplianceEntryDateStr;
	private String noiticeGeneratedInd;
	private String originalActionTakenId;
	private String originalOtherActionTakenComment;
	private String otherActionTakenComment;
	private String parentalNotified;
	private String probationViolationLevelDesc;
	private Date sanctionAssignedDate;
	private String sanctionAssignedDateStr;	
	private Date sanctionCompleteByDate;
	private String sanctionCompleteByDateStr;
	private String sanctionLevelDesc;
	private List sanctionLevelTypes;
	private String selectedActivityId;
	private String selectedActivityCategoryId;
	private String selectedActivityTypeId;
	private String selectedNoticeId;
	private String selectedCaseStatusId;
	private String[] selectedTechnicalIds; 
	private String[] selectedMinorTechnicalIds;
	private String[] selectedModSevereTechnicalIds;
	private String selectedSpecifiedViolationId;
	private List selectedProbationViolationList;
	private Date signatureSignedDate;
	private String signatureSignedDateStr;
	private String signatureStatus;
	private String signatureStatusLiteral;
	private String supervisionNum;
	private List specifiedViolationsList;
	private String updateFlow;
	
	// GRID lists and selections
	private List probationViolationList;
	private List minorProbationViolationList;
	private List minorSanctionLevelMinList;
	private List minorSanctionLevelMedList;
	private List minorSanctionLevelMaxList;
	private List minorSanctionLevelIntesiveList;
	
	private List modSevereProbationViolationList;
	private List modSevereSanctionLevelMinList;
	private List modSevereSanctionLevelMedList;
	private List modSevereSanctionLevelMaxList;
	private List modSevereSanctionLevelIntesiveList;
	
	private String[] minorMinComments;
	private String[] minorMedComments ;
	private String[] minorMaxComments ;
	private String[] minorIntComments ;
	private String[] modSevereMinComments ;
	private String[] modSevereMedComments ;
	private String[] modSevereMaxComments ;
	private String[] modSevereIntComments ;
	
	private String minorMinOtherComments;
	private String minorMedOtherComments ;
	private String minorMaxOtherComments ;
	private String minorIntOtherComments ;
	private String modSevereMinOtherComments ;
	private String modSevereMedOtherComments ;
	private String modSevereMaxOtherComments ;
	private String modSevereIntOtherComments ;
	
	private String minorMinOtherDescription;
	private String minorMedOtherDescription ;
	private String minorMaxOtherDescription ;
	private String minorIntOtherDescription ;
	private String modSevereMinOtherDescription ;
	private String modSevereMedOtherDescription ;
	private String modSevereMaxOtherDescription ;
	private String modSevereIntOtherDescription ;
	
	private String sanctionLevelId;
	private String violationLevelId;
	private String[] selectedSanctionIds;
	private String[] selectedMinorSanctionIds;
	private String[] selectedModSevereSanctionIds;
	private List selectedSanctionsList;

	/*
	 * 
	 */
	public void clear()
	{
		this.selectedActivityId = "";
		this.selectedActivityCategoryId = "";
		this.selectedActivityTypeId = "";
		this.selectedNoticeId = "";
		this.selectedCaseStatusId = "";
		this.confirmationMsg = "";
		this.selectedSpecifiedViolationId = "";
		this.violationLevelId = "";
		this.selectedTechnicalIds = new String[0];
		this.selectedMinorTechnicalIds = new String[0];
		this.selectedModSevereTechnicalIds = new String[0];
		this.selectedSanctionIds = new String[0];
		this.selectedMinorSanctionIds = new String[0];
		this.selectedModSevereSanctionIds = new String[0];
		
		this.minorProbationViolationList= emptyList;
		this.minorSanctionLevelMinList= emptyList;
		this.minorSanctionLevelMedList= emptyList;
		this.minorSanctionLevelMaxList= emptyList;
		this.minorSanctionLevelIntesiveList= emptyList;
		
		this.modSevereProbationViolationList= emptyList;
		this.modSevereSanctionLevelMinList= emptyList;
		this.modSevereSanctionLevelMedList= emptyList;
		this.modSevereSanctionLevelMaxList= emptyList;
		this.modSevereSanctionLevelIntesiveList= emptyList;
	}

	public void createClear()
	{
		this.sanctionAssignedDateStr = ""; 
		this.sanctionCompleteByDateStr = "";
		this.nonComplianceDateStr = "";
		this.parentalNotified = "";
		this.violationLevelId = "";
		this.sanctionLevelDesc = "" ;
		this.sanctionLevelId = "";
		this.minorMinOtherComments = "" ;
		this.minorMedOtherComments = "" ;
		this.minorMaxOtherComments = "" ;
		this.minorIntOtherComments = "" ;
		this.modSevereMinOtherComments = "" ;
		this.modSevereMedOtherComments = "" ;
		this.modSevereMaxOtherComments = "" ;
		this.modSevereIntOtherComments = "" ;
		
		this.minorMinOtherDescription = "";
		this.minorMedOtherDescription = "" ;
		this.minorMaxOtherDescription = "" ;
		this.minorIntOtherDescription = "" ;
		this.modSevereMinOtherDescription = "" ;
		this.modSevereMedOtherDescription = "" ;
		this.modSevereMaxOtherDescription = "" ;
		this.modSevereIntOtherDescription = "" ;
	}	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the actionTakenCodes
	 */
	public List getActionTakenCodes() {
		return actionTakenCodes;
	}

	/**
	 * @param actionTakenCodes the actionTakenCodes to set
	 */
	public void setActionTakenCodes(List actionTakenCodes) {
		this.actionTakenCodes = actionTakenCodes;
	}

	/**
	 * @return the actionTakenDesc
	 */
	public String getActionTakenDesc() {
		return actionTakenDesc;
	}

	/**
	 * @param actionTakenDesc the actionTakenDesc to set
	 */
	public void setActionTakenDesc(String actionTakenDesc) {
		this.actionTakenDesc = actionTakenDesc;
	}

	/**
	 * @return the actionTakenId
	 */
	public String getActionTakenId() {
		return actionTakenId;
	}

	/**
	 * @param actionTakenId the actionTakenId to set
	 */
	public void setActionTakenId(String actionTakenId) {
		this.actionTakenId = actionTakenId;
	}

	/**
	 * @return the caseAssignmentDateStr
	 */
	public String getCaseAssignmentDateStr() {
		return caseAssignmentDateStr;
	}

	/**
	 * @param caseAssignmentDateStr the caseAssignmentDateStr to set
	 */
	public void setCaseAssignmentDateStr(String caseAssignmentDateStr) {
		this.caseAssignmentDateStr = caseAssignmentDateStr;
	}

	/**
	 * @return the caseStatusId
	 */
	public String getCaseStatusId() {
		return caseStatusId;
	}

	/**
	 * @param caseStatusId the caseStatusId to set
	 */
	public void setCaseStatusId(String caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	/**
	 * @return the confirmationMsg
	 */
	public String getConfirmationMsg() {
		return confirmationMsg;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * @return the completionDateStr
	 */
	public String getCompletionDateStr() {
		return completionDateStr;
	}

	/**
	 * @param completionDateStr the completionDateStr to set
	 */
	public void setCompletionDateStr(String completionDateStr) {
		this.completionDateStr = completionDateStr;
	}

	/**
	 * @param confirmationMsg the confirmationMsg to set
	 */
	public void setConfirmationMsg(String confirmationMsg) {
		this.confirmationMsg = confirmationMsg;
	}

	/**
	 * @return the currentDateStr
	 */
	public String getCurrentDateStr() {
		return currentDateStr;
	}

	/**
	 * @param currentDateStr the currentDateStr to set
	 */
	public void setCurrentDateStr(String currentDateStr) {
		this.currentDateStr = currentDateStr;
	}

	/**
	 * @return the documents
	 */
	public List getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List documents) {
		this.documents = documents;
	}

	/**
	 * @return the existingNoticesList
	 */
	public List getExistingNoticesList() {
		return existingNoticesList;
	}

	/**
	 * @param existingNoticesList the existingNoticesList to set
	 */
	public void setExistingNoticesList(List existingNoticesList) {
		this.existingNoticesList = existingNoticesList;
	}

	/**
	 * @return the juvenileFullName
	 */
	public String getJuvenileFullName() {
		return juvenileFullName;
	}

	/**
	 * @param juvenileFullName the juvenileFullName to set
	 */
	public void setJuvenileFullName(String juvenileFullName) {
		this.juvenileFullName = juvenileFullName;
	}

	/**
	 * @return the juvenileCompletedStatus
	 */
	public String getJuvenileCompletedStatus() {
		return juvenileCompletedStatus;
	}

	/**
	 * @param juvenileCompletedStatus the juvenileCompletedStatus to set
	 */
	public void setJuvenileCompletedStatus(String juvenileCompletedStatus) {
		this.juvenileCompletedStatus = juvenileCompletedStatus;
	}

	/**
	 * @return the juvenileCompletedStatusLit
	 */
	public String getJuvenileCompletedStatusLit() {
		return juvenileCompletedStatusLit;
	}

	/**
	 * @param juvenileCompletedStatusLit the juvenileCompletedStatusLit to set
	 */
	public void setJuvenileCompletedStatusLit(String juvenileCompletedStatusLit) {
		this.juvenileCompletedStatusLit = juvenileCompletedStatusLit;
	}

	/**
	 * @return the juvenileCompletedComments
	 */
	public String getJuvenileCompletedComments() {
		return juvenileCompletedComments;
	}

	/**
	 * @param juvenileCompletedComments the juvenileCompletedComments to set
	 */
	public void setJuvenileCompletedComments(String juvenileCompletedComments) {
		this.juvenileCompletedComments = juvenileCompletedComments;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the juvenileSignedStatus
	 */
	public String getJuvenileSignedStatus() {
		return juvenileSignedStatus;
	}

	/**
	 * @param juvenileSignedStatus the juvenileSignedStatus to set
	 */
	public void setJuvenileSignedStatus(String juvenileSignedStatus) {
		this.juvenileSignedStatus = juvenileSignedStatus;
	}

	/**
	 * @return the nonComplianceDate
	 */
	public Date getNonComplianceDate() {
		return nonComplianceDate;
	}

	/**
	 * @param nonComplianceDate the nonComplianceDate to set
	 */
	public void setNonComplianceDate(Date nonComplianceDate) {
		this.nonComplianceDate = nonComplianceDate;
	}

	/**
	 * @return the nonComplianceDateStr
	 */
	public String getNonComplianceDateStr() {
		return nonComplianceDateStr;
	}

	/**
	 * @param nonComplianceDateStr the nonComplianceDateStr to set
	 */
	public void setNonComplianceDateStr(String nonComplianceDateStr) {
		this.nonComplianceDateStr = nonComplianceDateStr;
	}

	/**
	 * @return the nonComplianceEntryDate
	 */
	public Date getNonComplianceEntryDate() {
		return nonComplianceEntryDate;
	}

	/**
	 * @param nonComplianceEntryDate the nonComplianceEntryDate to set
	 */
	public void setNonComplianceEntryDate(Date nonComplianceEntryDate) {
		this.nonComplianceEntryDate = nonComplianceEntryDate;
	}

	/**
	 * @return the nonComplianceEntryDateStr
	 */
	public String getNonComplianceEntryDateStr() {
		return nonComplianceEntryDateStr;
	}

	/**
	 * @param nonComplianceEntryDateStr the nonComplianceEntryDateStr to set
	 */
	public void setNonComplianceEntryDateStr(String nonComplianceEntryDateStr) {
		this.nonComplianceEntryDateStr = nonComplianceEntryDateStr;
	}

	/**
	 * @return the noiticeGeneratedInd
	 */
	public String getNoiticeGeneratedInd() {
		return noiticeGeneratedInd;
	}

	/**
	 * @param noiticeGeneratedInd the noiticeGeneratedInd to set
	 */
	public void setNoiticeGeneratedInd(String noiticeGeneratedInd) {
		this.noiticeGeneratedInd = noiticeGeneratedInd;
	}

	/**
	 * @return the originalActionTakenId
	 */
	public String getOriginalActionTakenId() {
		return originalActionTakenId;
	}

	/**
	 * @param originalActionTakenId the originalActionTakenId to set
	 */
	public void setOriginalActionTakenId(String originalActionTakenId) {
		this.originalActionTakenId = originalActionTakenId;
	}

	/**
	 * @return the originalOtherActionTakenComment
	 */
	public String getOriginalOtherActionTakenComment() {
		return originalOtherActionTakenComment;
	}

	/**
	 * @param originalOtherActionTakenComment the originalOtherActionTakenComment to set
	 */
	public void setOriginalOtherActionTakenComment(
			String originalOtherActionTakenComment) {
		this.originalOtherActionTakenComment = originalOtherActionTakenComment;
	}

	/**
	 * @return the otherActionTakenComment
	 */
	public String getOtherActionTakenComment() {
		return otherActionTakenComment;
	}

	/**
	 * @param otherActionTakenComment the otherActionTakenComment to set
	 */
	public void setOtherActionTakenComment(String otherActionTakenComment) {
		this.otherActionTakenComment = otherActionTakenComment;
	}

	/**
	 * @return the parentalNotified
	 */
	public String getParentalNotified() {
		return parentalNotified;
	}

	/**
	 * @param parentalNotified the parentalNotified to set
	 */
	public void setParentalNotified(String parentalNotified) {
		this.parentalNotified = parentalNotified;
	}

	/**
	 * @return the probationViolationLevelDesc
	 */
	public String getProbationViolationLevelDesc() {
		return probationViolationLevelDesc;
	}

	/**
	 * @param probationViolationLevelDesc the probationViolationLevelDesc to set
	 */
	public void setProbationViolationLevelDesc(String probationViolationLevelDesc) {
		this.probationViolationLevelDesc = probationViolationLevelDesc;
	}

	/**
	 * @return the sanctionAssignedDate
	 */
	public Date getSanctionAssignedDate() {
		return sanctionAssignedDate;
	}

	/**
	 * @param sanctionAssignedDate the sanctionAssignedDate to set
	 */
	public void setSanctionAssignedDate(Date sanctionAssignedDate) {
		this.sanctionAssignedDate = sanctionAssignedDate;
	}

	/**
	 * @return the sanctionAssignedDateStr
	 */
	public String getSanctionAssignedDateStr() {
		return sanctionAssignedDateStr;
	}

	/**
	 * @param sanctionAssignedDateStr the sanctionAssignedDateStr to set
	 */
	public void setSanctionAssignedDateStr(String sanctionAssignedDateStr) {
		this.sanctionAssignedDateStr = sanctionAssignedDateStr;
	}

	/**
	 * @return the sanctionCompleteByDate
	 */
	public Date getSanctionCompleteByDate() {
		return sanctionCompleteByDate;
	}

	/**
	 * @param sanctionCompleteByDate the sanctionCompleteByDate to set
	 */
	public void setSanctionCompleteByDate(Date sanctionCompleteByDate) {
		this.sanctionCompleteByDate = sanctionCompleteByDate;
	}

	/**
	 * @return the sanctionCompleteByDateStr
	 */
	public String getSanctionCompleteByDateStr() {
		return sanctionCompleteByDateStr;
	}

	/**
	 * @param sanctionCompleteByDateStr the sanctionCompleteByDateStr to set
	 */
	public void setSanctionCompleteByDateStr(String sanctionCompleteByDateStr) {
		this.sanctionCompleteByDateStr = sanctionCompleteByDateStr;
	}

	/**
	 * @return the sanctionLevelDesc
	 */
	public String getSanctionLevelDesc() {
		return sanctionLevelDesc;
	}

	/**
	 * @param sanctionLevelDesc the sanctionLevelDesc to set
	 */
	public void setSanctionLevelDesc(String sanctionLevelDesc) {
		this.sanctionLevelDesc = sanctionLevelDesc;
	}

	/**
	 * @return the sanctionLevelTypes
	 */
	public List getSanctionLevelTypes() {
		return sanctionLevelTypes;
	}

	/**
	 * @param sanctionLevelTypes the sanctionLevelTypes to set
	 */
	public void setSanctionLevelTypes(List sanctionLevelTypes) {
		this.sanctionLevelTypes = sanctionLevelTypes;
	}

	/**
	 * @return the selectedActivityId
	 */
	public String getSelectedActivityId() {
		return selectedActivityId;
	}

	/**
	 * @param selectedActivityId the selectedActivityId to set
	 */
	public void setSelectedActivityId(String selectedActivityId) {
		this.selectedActivityId = selectedActivityId;
	}

	/**
	 * @return the selectedActivityCategoryId
	 */
	public String getSelectedActivityCategoryId() {
		return selectedActivityCategoryId;
	}

	/**
	 * @param selectedActivityCategoryId the selectedActivityCategoryId to set
	 */
	public void setSelectedActivityCategoryId(String selectedActivityCategoryId) {
		this.selectedActivityCategoryId = selectedActivityCategoryId;
	}

	/**
	 * @return the selectedActivityTypeId
	 */
	public String getSelectedActivityTypeId() {
		return selectedActivityTypeId;
	}

	/**
	 * @param selectedActivityTypeId the selectedActivityTypeId to set
	 */
	public void setSelectedActivityTypeId(String selectedActivityTypeId) {
		this.selectedActivityTypeId = selectedActivityTypeId;
	}

	/**
	 * @return the selectedNoticeId
	 */
	public String getSelectedNoticeId() {
		return selectedNoticeId;
	}

	/**
	 * @param selectedNoticeId the selectedNoticeId to set
	 */
	public void setSelectedNoticeId(String selectedNoticeId) {
		this.selectedNoticeId = selectedNoticeId;
	}

	/**
	 * @return the selectedCaseStatusId
	 */
	public String getSelectedCaseStatusId() {
		return selectedCaseStatusId;
	}

	/**
	 * @param selectedCaseStatusId the selectedCaseStatusId to set
	 */
	public void setSelectedCaseStatusId(String selectedCaseStatusId) {
		this.selectedCaseStatusId = selectedCaseStatusId;
	}

	/**
	 * @return the selectedTechnicalIds
	 */
	public String[] getSelectedTechnicalIds() {
		return selectedTechnicalIds;
	}

	/**
	 * @param selectedTechnicalIds the selectedTechnicalIds to set
	 */
	public void setSelectedTechnicalIds(String[] selectedTechnicalIds) {
		this.selectedTechnicalIds = selectedTechnicalIds;
	}

	/**
	 * @return the selectedMinorTechnicalIds
	 */
	public String[] getSelectedMinorTechnicalIds() {
		return selectedMinorTechnicalIds;
	}

	/**
	 * @param selectedMinorTechnicalIds the selectedMinorTechnicalIds to set
	 */
	public void setSelectedMinorTechnicalIds(String[] selectedMinorTechnicalIds) {
		this.selectedMinorTechnicalIds = selectedMinorTechnicalIds;
	}

	/**
	 * @return the selectedModSevereTechnicalIds
	 */
	public String[] getSelectedModSevereTechnicalIds() {
		return selectedModSevereTechnicalIds;
	}

	/**
	 * @param selectedModSevereTechnicalIds the selectedModSevereTechnicalIds to set
	 */
	public void setSelectedModSevereTechnicalIds(
			String[] selectedModSevereTechnicalIds) {
		this.selectedModSevereTechnicalIds = selectedModSevereTechnicalIds;
	}

	/**
	 * @return the selectedSpecifiedViolationId
	 */
	public String getSelectedSpecifiedViolationId() {
		return selectedSpecifiedViolationId;
	}

	/**
	 * @param selectedSpecifiedViolationId the selectedSpecifiedViolationId to set
	 */
	public void setSelectedSpecifiedViolationId(String selectedSpecifiedViolationId) {
		this.selectedSpecifiedViolationId = selectedSpecifiedViolationId;
	}

	/**
	 * @return the selectedProbationViolationList
	 */
	public List getSelectedProbationViolationList() {
		return selectedProbationViolationList;
	}

	/**
	 * @param selectedProbationViolationList the selectedProbationViolationList to set
	 */
	public void setSelectedProbationViolationList(
			List selectedProbationViolationList) {
		this.selectedProbationViolationList = selectedProbationViolationList;
	}

	/**
	 * @return the signatureSignedDate
	 */
	public Date getSignatureSignedDate() {
		return signatureSignedDate;
	}

	/**
	 * @param signatureSignedDate the signatureSignedDate to set
	 */
	public void setSignatureSignedDate(Date signatureSignedDate) {
		this.signatureSignedDate = signatureSignedDate;
	}

	/**
	 * @return the signatureSignedDateStr
	 */
	public String getSignatureSignedDateStr() {
		return signatureSignedDateStr;
	}

	/**
	 * @param signatureSignedDateStr the signatureSignedDateStr to set
	 */
	public void setSignatureSignedDateStr(String signatureSignedDateStr) {
		this.signatureSignedDateStr = signatureSignedDateStr;
	}

	/**
	 * @return the signatureStatus
	 */
	public String getSignatureStatus() {
		return signatureStatus;
	}

	/**
	 * @param signatureStatus the signatureStatus to set
	 */
	public void setSignatureStatus(String signatureStatus) {
		this.signatureStatus = signatureStatus;
	}

	/**
	 * @return the signatureStatusLiteral
	 */
	public String getSignatureStatusLiteral() {
		return signatureStatusLiteral;
	}

	/**
	 * @param signatureStatusLiteral the signatureStatusLiteral to set
	 */
	public void setSignatureStatusLiteral(String signatureStatusLiteral) {
		this.signatureStatusLiteral = signatureStatusLiteral;
	}

	/**
	 * @return the supervisionNum
	 */
	public String getSupervisionNum() {
		return supervisionNum;
	}

	/**
	 * @param supervisionNum the supervisionNum to set
	 */
	public void setSupervisionNum(String supervisionNum) {
		this.supervisionNum = supervisionNum;
	}

	/**
	 * @return the specifiedViolationsList
	 */
	public List getSpecifiedViolationsList() {
		return specifiedViolationsList;
	}

	/**
	 * @param specifiedViolationsList the specifiedViolationsList to set
	 */
	public void setSpecifiedViolationsList(List specifiedViolationsList) {
		this.specifiedViolationsList = specifiedViolationsList;
	}


	/**
	 * @return the minorProbationViolationList
	 */
	public List getMinorProbationViolationList() {
		return minorProbationViolationList;
	}

	/**
	 * @param minorProbationViolationList the minorProbationViolationList to set
	 */
	public void setMinorProbationViolationList(List minorProbationViolationList) {
		this.minorProbationViolationList = minorProbationViolationList;
	}

	/**
	 * @return the minorSanctionLevelMinList
	 */
	public List getMinorSanctionLevelMinList() {
		return minorSanctionLevelMinList;
	}

	/**
	 * @param minorSanctionLevelMinList the minorSanctionLevelMinList to set
	 */
	public void setMinorSanctionLevelMinList(List minorSanctionLevelMinList) {
		this.minorSanctionLevelMinList = minorSanctionLevelMinList;
	}

	/**
	 * @return the minorSanctionLevelMedList
	 */
	public  List getMinorSanctionLevelMedList() {
		return minorSanctionLevelMedList;
	}

	/**
	 * @param minorSanctionLevelMedList the minorSanctionLevelMedList to set
	 */
	public void setMinorSanctionLevelMedList(List minorSanctionLevelMedList) {
		this.minorSanctionLevelMedList = minorSanctionLevelMedList;
	}

	/**
	 * @return the minorSanctionLevelMaxList
	 */
	public  List getMinorSanctionLevelMaxList() {
		return minorSanctionLevelMaxList;
	}

	/**
	 * @param minorSanctionLevelMaxList the minorSanctionLevelMaxList to set
	 */
	public void setMinorSanctionLevelMaxList(List minorSanctionLevelMaxList) {
		this.minorSanctionLevelMaxList = minorSanctionLevelMaxList;
	}

	/**
	 * @return the minorSanctionLevelIntesiveList
	 */
	public  List getMinorSanctionLevelIntesiveList() {
		return minorSanctionLevelIntesiveList;
	}

	/**
	 * @param minorSanctionLevelIntesiveList the minorSanctionLevelIntesiveList to set
	 */
	public void setMinorSanctionLevelIntesiveList(
			List minorSanctionLevelIntesiveList) {
		this.minorSanctionLevelIntesiveList = minorSanctionLevelIntesiveList;
	}

	/**
	 * @return the modSevereProbationViolationList
	 */
	public List getModSevereProbationViolationList() {
		return modSevereProbationViolationList;
	}

	/**
	 * @param modSevereProbationViolationList the modSevereProbationViolationList to set
	 */
	public void setModSevereProbationViolationList(
			List modSevereProbationViolationList) {
		this.modSevereProbationViolationList = modSevereProbationViolationList;
	}

	/**
	 * @return the modSevereSanctionLevelMinList
	 */
	public List getModSevereSanctionLevelMinList() {
		return modSevereSanctionLevelMinList;
	}

	/**
	 * @param modSevereSanctionLevelMinList the modSevereSanctionLevelMinList to set
	 */
	public void setModSevereSanctionLevelMinList(List modSevereSanctionLevelMinList) {
		this.modSevereSanctionLevelMinList = modSevereSanctionLevelMinList;
	}

	/**
	 * @return the modSevereSanctionLevelMedList
	 */
	public List getModSevereSanctionLevelMedList() {
		return modSevereSanctionLevelMedList;
	}

	/**
	 * @param modSevereSanctionLevelMedList the modSevereSanctionLevelMedList to set
	 */
	public void setModSevereSanctionLevelMedList(List modSevereSanctionLevelMedList) {
		this.modSevereSanctionLevelMedList = modSevereSanctionLevelMedList;
	}

	/**
	 * @return the modSevereSanctionLevelMaxList
	 */
	public List getModSevereSanctionLevelMaxList() {
		return modSevereSanctionLevelMaxList;
	}

	/**
	 * @param modSevereSanctionLevelMaxList the modSevereSanctionLevelMaxList to set
	 */
	public void setModSevereSanctionLevelMaxList(List modSevereSanctionLevelMaxList) {
		this.modSevereSanctionLevelMaxList = modSevereSanctionLevelMaxList;
	}

	/**
	 * @return the modSevereSanctionLevelIntesiveList
	 */
	public List getModSevereSanctionLevelIntesiveList() {
		return modSevereSanctionLevelIntesiveList;
	}

	/**
	 * @param modSevereSanctionLevelIntesiveList the modSevereSanctionLevelIntesiveList to set
	 */
	public void setModSevereSanctionLevelIntesiveList(
			List modSevereSanctionLevelIntesiveList) {
		this.modSevereSanctionLevelIntesiveList = modSevereSanctionLevelIntesiveList;
	}

	/**
	 * @return the sanctionLevelId
	 */
	public String getSanctionLevelId() {
		return sanctionLevelId;
	}

	/**
	 * @param sanctionLevelId the sanctionLevelId to set
	 */
	public void setSanctionLevelId(String sanctionLevelId) {
		this.sanctionLevelId = sanctionLevelId;
	}

	/**
	 * @return the violationLevelId
	 */
	public String getViolationLevelId() {
		return violationLevelId;
	}

	/**
	 * @param violationLevelId the violationLevelId to set
	 */
	public void setViolationLevelId(String violationLevelId) {
		this.violationLevelId = violationLevelId;
	}

	/**
	 * @return the selectedSanctionIds
	 */
	public String[] getSelectedSanctionIds() {
		return selectedSanctionIds;
	}

	/**
	 * @param selectedSanctionIds the selectedSanctionIds to set
	 */
	public void setSelectedSanctionIds(String[] selectedSanctionIds) {
		this.selectedSanctionIds = selectedSanctionIds;
	}

	/**
	 * @return the selectedMinorSanctionIds
	 */
	public String[] getSelectedMinorSanctionIds() {
		return selectedMinorSanctionIds;
	}

	/**
	 * @param selectedMinorSanctionIds the selectedMinorSanctionIds to set
	 */
	public void setSelectedMinorSanctionIds(String[] selectedMinorSanctionIds) {
		this.selectedMinorSanctionIds = selectedMinorSanctionIds;
	}

	/**
	 * @return the selectedModSevereSanctionIds
	 */
	public String[] getSelectedModSevereSanctionIds() {
		return selectedModSevereSanctionIds;
	}

	/**
	 * @param selectedModSevereSanctionIds the selectedModSevereSanctionIds to set
	 */
	public void setSelectedModSevereSanctionIds(
			String[] selectedModSevereSanctionIds) {
		this.selectedModSevereSanctionIds = selectedModSevereSanctionIds;
	}

	/**
	 * @return the selectedSanctionsList
	 */
	public List getSelectedSanctionsList() {
		return selectedSanctionsList;
	}

	/**
	 * @param selectedSanctionsList the selectedSanctionsList to set
	 */
	public void setSelectedSanctionsList(List selectedSanctionsList) {
		this.selectedSanctionsList = selectedSanctionsList;
	}

	/**
	 * @return the probationViolationList
	 */
	public List getProbationViolationList() {
		return probationViolationList;
	}

	/**
	 * @param probationViolationList the probationViolationList to set
	 */
	public void setProbationViolationList(List probationViolationList) {
		this.probationViolationList = probationViolationList;
	}

	/**
	 * @return the minorMinComments
	 */
	public String[] getMinorMinComments() {
		return minorMinComments;
	}

	/**
	 * @param minorMinComments the minorMinComments to set
	 */
	public void setMinorMinComments(String[] minorMinComments) {
		this.minorMinComments = minorMinComments;
	}

	/**
	 * @return the minorMedComments
	 */
	public String[] getMinorMedComments() {
		return minorMedComments;
	}

	/**
	 * @param minorMedComments the minorMedComments to set
	 */
	public void setMinorMedComments(String[] minorMedComments) {
		this.minorMedComments = minorMedComments;
	}

	/**
	 * @return the minorMaxComments
	 */
	public String[] getMinorMaxComments() {
		return minorMaxComments;
	}

	/**
	 * @param minorMaxComments the minorMaxComments to set
	 */
	public void setMinorMaxComments(String[] minorMaxComments) {
		this.minorMaxComments = minorMaxComments;
	}

	/**
	 * @return the minorIntComments
	 */
	public String[] getMinorIntComments() {
		return minorIntComments;
	}

	/**
	 * @param minorIntComments the minorIntComments to set
	 */
	public void setMinorIntComments(String[] minorIntComments) {
		this.minorIntComments = minorIntComments;
	}

	/**
	 * @return the modSevereMinComments
	 */
	public String[] getModSevereMinComments() {
		return modSevereMinComments;
	}

	/**
	 * @param modSevereMinComments the modSevereMinComments to set
	 */
	public void setModSevereMinComments(String[] modSevereMinComments) {
		this.modSevereMinComments = modSevereMinComments;
	}

	/**
	 * @return the modSevereMedComments
	 */
	public String[] getModSevereMedComments() {
		return modSevereMedComments;
	}

	/**
	 * @param modSevereMedComments the modSevereMedComments to set
	 */
	public void setModSevereMedComments(String[] modSevereMedComments) {
		this.modSevereMedComments = modSevereMedComments;
	}

	/**
	 * @return the modSevereMaxComments
	 */
	public String[] getModSevereMaxComments() {
		return modSevereMaxComments;
	}

	/**
	 * @param modSevereMaxComments the modSevereMaxComments to set
	 */
	public void setModSevereMaxComments(String[] modSevereMaxComments) {
		this.modSevereMaxComments = modSevereMaxComments;
	}

	/**
	 * @return the modSevereIntComments
	 */
	public String[] getModSevereIntComments() {
		return modSevereIntComments;
	}

	/**
	 * @param modSevereIntComments the modSevereIntComments to set
	 */
	public void setModSevereIntComments(String[] modSevereIntComments) {
		this.modSevereIntComments = modSevereIntComments;
	}

	/**
	 * @return the minorMinOtherComments
	 */
	public String getMinorMinOtherComments() {
		return minorMinOtherComments;
	}

	/**
	 * @param minorMinOtherComments the minorMinOtherComments to set
	 */
	public void setMinorMinOtherComments(String minorMinOtherComments) {
		this.minorMinOtherComments = minorMinOtherComments;
	}

	/**
	 * @return the minorMedOtherComments
	 */
	public String getMinorMedOtherComments() {
		return minorMedOtherComments;
	}

	/**
	 * @param minorMedOtherComments the minorMedOtherComments to set
	 */
	public void setMinorMedOtherComments(String minorMedOtherComments) {
		this.minorMedOtherComments = minorMedOtherComments;
	}

	/**
	 * @return the minorMaxOtherComments
	 */
	public String getMinorMaxOtherComments() {
		return minorMaxOtherComments;
	}

	/**
	 * @param minorMaxOtherComments the minorMaxOtherComments to set
	 */
	public void setMinorMaxOtherComments(String minorMaxOtherComments) {
		this.minorMaxOtherComments = minorMaxOtherComments;
	}

	/**
	 * @return the minorIntOtherComments
	 */
	public String getMinorIntOtherComments() {
		return minorIntOtherComments;
	}

	/**
	 * @param minorIntOtherComments the minorIntOtherComments to set
	 */
	public void setMinorIntOtherComments(String minorIntOtherComments) {
		this.minorIntOtherComments = minorIntOtherComments;
	}

	/**
	 * @return the modSevereMinOtherComments
	 */
	public String getModSevereMinOtherComments() {
		return modSevereMinOtherComments;
	}

	/**
	 * @param modSevereMinOtherComments the modSevereMinOtherComments to set
	 */
	public void setModSevereMinOtherComments(String modSevereMinOtherComments) {
		this.modSevereMinOtherComments = modSevereMinOtherComments;
	}

	/**
	 * @return the modSevereMedOtherComments
	 */
	public String getModSevereMedOtherComments() {
		return modSevereMedOtherComments;
	}

	/**
	 * @param modSevereMedOtherComments the modSevereMedOtherComments to set
	 */
	public void setModSevereMedOtherComments(String modSevereMedOtherComments) {
		this.modSevereMedOtherComments = modSevereMedOtherComments;
	}

	/**
	 * @return the modSevereMaxOtherComments
	 */
	public String getModSevereMaxOtherComments() {
		return modSevereMaxOtherComments;
	}

	/**
	 * @param modSevereMaxOtherComments the modSevereMaxOtherComments to set
	 */
	public void setModSevereMaxOtherComments(String modSevereMaxOtherComments) {
		this.modSevereMaxOtherComments = modSevereMaxOtherComments;
	}

	/**
	 * @return the modSevereIntOtherComments
	 */
	public String getModSevereIntOtherComments() {
		return modSevereIntOtherComments;
	}

	/**
	 * @param modSevereIntOtherComments the modSevereIntOtherComments to set
	 */
	public void setModSevereIntOtherComments(String modSevereIntOtherComments) {
		this.modSevereIntOtherComments = modSevereIntOtherComments;
	}

	/**
	 * @return the minorMinOtherDescription
	 */
	public String getMinorMinOtherDescription() {
		return minorMinOtherDescription;
	}

	/**
	 * @param minorMinOtherDescription the minorMinOtherDescription to set
	 */
	public void setMinorMinOtherDescription(String minorMinOtherDescription) {
		this.minorMinOtherDescription = minorMinOtherDescription;
	}

	/**
	 * @return the minorMedOtherDescription
	 */
	public String getMinorMedOtherDescription() {
		return minorMedOtherDescription;
	}

	/**
	 * @param minorMedOtherDescription the minorMedOtherDescription to set
	 */
	public void setMinorMedOtherDescription(String minorMedOtherDescription) {
		this.minorMedOtherDescription = minorMedOtherDescription;
	}

	/**
	 * @return the minorMaxOtherDescription
	 */
	public String getMinorMaxOtherDescription() {
		return minorMaxOtherDescription;
	}

	/**
	 * @param minorMaxOtherDescription the minorMaxOtherDescription to set
	 */
	public void setMinorMaxOtherDescription(String minorMaxOtherDescription) {
		this.minorMaxOtherDescription = minorMaxOtherDescription;
	}

	/**
	 * @return the minorIntOtherDescription
	 */
	public String getMinorIntOtherDescription() {
		return minorIntOtherDescription;
	}

	/**
	 * @param minorIntOtherDescription the minorIntOtherDescription to set
	 */
	public void setMinorIntOtherDescription(String minorIntOtherDescription) {
		this.minorIntOtherDescription = minorIntOtherDescription;
	}

	/**
	 * @return the modSevereMinOtherDescription
	 */
	public String getModSevereMinOtherDescription() {
		return modSevereMinOtherDescription;
	}

	/**
	 * @param modSevereMinOtherDescription the modSevereMinOtherDescription to set
	 */
	public void setModSevereMinOtherDescription(String modSevereMinOtherDescription) {
		this.modSevereMinOtherDescription = modSevereMinOtherDescription;
	}

	/**
	 * @return the modSevereMedOtherDescription
	 */
	public String getModSevereMedOtherDescription() {
		return modSevereMedOtherDescription;
	}

	/**
	 * @param modSevereMedOtherDescription the modSevereMedOtherDescription to set
	 */
	public void setModSevereMedOtherDescription(String modSevereMedOtherDescription) {
		this.modSevereMedOtherDescription = modSevereMedOtherDescription;
	}

	/**
	 * @return the modSevereMaxOtherDescription
	 */
	public String getModSevereMaxOtherDescription() {
		return modSevereMaxOtherDescription;
	}

	/**
	 * @param modSevereMaxOtherDescription the modSevereMaxOtherDescription to set
	 */
	public void setModSevereMaxOtherDescription(String modSevereMaxOtherDescription) {
		this.modSevereMaxOtherDescription = modSevereMaxOtherDescription;
	}

	/**
	 * @return the modSevereIntOtherDescription
	 */
	public String getModSevereIntOtherDescription() {
		return modSevereIntOtherDescription;
	}

	/**
	 * @param modSevereIntOtherDescription the modSevereIntOtherDescription to set
	 */
	public void setModSevereIntOtherDescription(String modSevereIntOtherDescription) {
		this.modSevereIntOtherDescription = modSevereIntOtherDescription;
	}

	/**
	 * @return the updateFlow
	 */
	public String getUpdateFlow() {
		return updateFlow;
	}

	/**
	 * @param updateFlow the updateFlow to set
	 */
	public void setUpdateFlow(String updateFlow) {
		this.updateFlow = updateFlow;
	}
	
}
