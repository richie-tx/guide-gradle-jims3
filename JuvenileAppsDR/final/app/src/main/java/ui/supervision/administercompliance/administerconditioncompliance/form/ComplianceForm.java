/*
 * Created on October 31, 2007
 *
 */
package ui.supervision.administercompliance.administerconditioncompliance.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.supervision.manageassociate.UIManageAssociateHelper;

/**
 * @author cc_rsojitrawala
 *  
 */
public class ComplianceForm extends ActionForm 
{
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	
	private String agencyId;
	private String caseNumber;
	private String complianceReasonId;
	private String compliancePage;
	private String conditionId;
	private String conditionLiteral;
	private String conditionName;
	private String confirmMessage;
	private String court;
	private String courtPolicyId;
	private String defendantId;
	private String displayAction;
	private Date eventDate;
	private String eventDetails;
	private String eventId;
	private String group1Id;
	private String group2Id;	
	private boolean isCompliant=false;
	private String nonComplianceCount;
	private String orderId;
	private String orderConditionId;
	private String showFilter;
	private String sprOrderConditionId;
	private String chainNum;
	
// values for displaying popup literals
	private String popupLiteral;
	private String popupLiteralName;
	
	private List allLikeConditions;
	private List allUniqueConditions;
	private List selectedConditions;
	private List selectedLikeConditions;
	private List selectedUniqueConditions;
	private String[] selectedCaseNumbers;
	private String eventTypes;
	private List likeConditions;
	private List uniqueConditions;
	
	private Collection caseNumbers;
	private Collection complianceReasons;
	private Collection groups;
	private Collection group2;	
    private String[] selectedConditionIds;
    private String[] complianceReasonIds;    
    
// Event fields for noncompliance
	private String details;
    private Date occurrenceDate;
	private String occurrenceDateStr;
	private String occurrenceTime;
	private String ncEventType;
	private String newEventType;	
	private Collection selectedConditionsEvents;
	private Collection selectedLikeConditionsEvents;
	private Collection selectedUniqueConditionsEvents;
    
// Casenotes drop down list fields
    private Collection contactMethodList;
    private Collection casenoteSubjectList;
    private Collection casenoteTypeList;
    private Collection collateralList;
    private Collection contactWithList;
    private Collection contactWithAssociatesList;
   
// Casenotes required fields
	private String[] associateIds;
	private String associates;
	private Date casenoteDate;
	private String casenoteDateAsString;
	private String casenoteDateAsStr;
    private String casenoteId;
	private String casenoteText;
    private String casenoteTime;
	private String casenoteType;
	private String casenoteTypeId;
	private String casenoteStatus;
	private String casenoteStatusId;
    private String collateral;
	private String collateralId;	
	private String contactMethod;
	private String contactMethodId;
	private String contextId;
	private IName createdByName;
	private Date createDate;
	private String generatedBy;
	private String generatedById;
	private String[] subjectIds;
	private String subjects;
	private String superviseeId;	
	private String supervisionPeriodBeginDateAsString;
// other casenote fields
	private String AMPMId;
	private String associateId;
	private String relationship;	
	private Collection associatesList;	
	private Collection casenoteStatusList;	
	private Collection generatedByList;
	private Collection currentCasenotes;
    private String[] selectedAssociateIds;
// casenote search fields
	private String searchById;
	private Collection searchByList;
	private String searchBeginDate;
	private String searchBeginDateAsString;
	private String searchEndDate;	
	private String searchEndDateAsString;
	private String searchCourt;
	private String searchCollateralId;
	private String searchGeneratedById;	
	private String searchCasenoteTypeId;
	private String[] searchCaseNumberIds;
	private String[] searchAssociateIds;
	private String[] searchSubjectIds;	
	
// Default Elements in all forms
    private static List emptyColl = new ArrayList();

    public void clear()
    {
    	this.eventId = "";
    	this.group1Id = "";
    	this.group2Id = "";
    	this.caseNumber = "";
    	this.displayAction = "";
    	this.supervisionPeriodBeginDateAsString = "";
    	this.clearCasenoteSearch();
    	this.clearCasenoteInputs();
    	this.confirmMessage = "";
    	this.sprOrderConditionId = "";
    	this.chainNum = "";
    	this.selectedCaseNumbers = null;
    	groups = new ArrayList();
    	group2 = new ArrayList();
    	allLikeConditions = new ArrayList();
    	allUniqueConditions = new ArrayList();
     	likeConditions = new ArrayList();;
    	uniqueConditions = new ArrayList();;
     	selectedUniqueConditions = new ArrayList(); 
     	selectedLikeConditions = new ArrayList();
		selectedAssociateIds = new String[0];
    	caseNumbers = new ArrayList();;    	
    }

    public void clearCasenoteSearch()
    {
	    this.searchBeginDate = "";
	    this.searchBeginDateAsString = "";
	    this.searchById = "";
    	this.searchCasenoteTypeId = "";	    
	    this.searchCollateralId = "";
	    this.searchAssociateIds = new String[0];
	    this.searchCourt = "";
	    this.searchEndDate = "";
	    this.searchEndDateAsString = "";
	    this.searchGeneratedById = "";
	    this.searchCaseNumberIds =  new String[0];
	    this.searchSubjectIds = new String[0];    
    }
	
    public void clearCasenoteInputs()
    {
	    this.casenoteDateAsStr = "";
	    this.casenoteTime = "";
	    this.collateral = "";
	    this.collateralId = "";
	    this.contactMethod = "";
	    this.contactMethodId = "";
	    this.subjects = "";
	    this.subjectIds = new String[0];
	    this.casenoteText = "";   	
    }
    
    public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
    	String clearSelectedCaseNumbers = aRequest.getParameter("clearSelectedCaseNumbers");
    	if(clearSelectedCaseNumbers != null && clearSelectedCaseNumbers.equals("true")) {
    		selectedCaseNumbers = null;
    	}
    }
    /**
     * @return Returns the emptyColl.
     */
    public static List getEmptyColl()
    {
        return emptyColl;
    }
    /**
     * @param emptyColl
     *        The emptyColl to set.
     */
    public static void setEmptyColl(List emptyColl)
    {
        ComplianceForm.emptyColl = emptyColl;
    }
    /**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the allLikeConditions.
	 */
	public List getAllLikeConditions() {
		return allLikeConditions;
	}
	/**
	 * @param allLikeConditions The allLikeConditions to set.
	 */
	public void setAllLikeConditions(List allLikeConditions) {
		this.allLikeConditions = allLikeConditions;
	}
	/**
	 * @return Returns the allUniqueConditions.
	 */
	public List getAllUniqueConditions() {
		return allUniqueConditions;
	}
	/**
	 * @param allUniqueConditions The allUniqueConditions to set.
	 */
	public void setAllUniqueConditions(List allUniqueConditions) {
		this.allUniqueConditions = allUniqueConditions;
	}
	/**
	 * @return Returns the selectedLikeCondtions.
	 */
	public List getSelectedLikeConditions() {
		return selectedLikeConditions;
	}
	/**
	 * @param selectedLikeCondtions The selectedLikeCondtions to set.
	 */
	public void setSelectedLikeConditions(List selectedLikeConditions) {
		this.selectedLikeConditions = selectedLikeConditions;
	}	
	/**
	 * @return Returns the selectedUniqueConditions.
	 */
	public List getSelectedUniqueConditions() {
		return selectedUniqueConditions;
	}
	/**
	 * @param allSelectedUniqueConditions The selectedUniqueConditions to set.
	 */
	public void setSelectedUniqueConditions(List selectedUniqueConditions) {
		this.selectedUniqueConditions = selectedUniqueConditions;
	}
	/**
	 * @return Returns the selectedConditions.
	 */
	public List getSelectedConditions() {
		return selectedConditions;
	}
	/**
	 * @param selectedConditions The selectedConditions to set.
	 */
	public void setSelectedConditions(List selectedConditions) {
		this.selectedConditions = selectedConditions;
	}
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * @return Returns the complianceReasonId.
	 */
	public String getComplianceReasonId() {
		return complianceReasonId;
	}
	/**
	 * @param complianceReasonId The complianceReasonId to set.
	 */
	public void setComplianceReasonId(String complianceReasonId) {
		this.complianceReasonId = complianceReasonId;
	}
	/**
	 * @return Returns the compliancePage.
	 */
	public String getCompliancePage() {
		return compliancePage;
	}
	/**
	 * @param compliancePage The compliancePage to set.
	 */
	public void setCompliancePage(String compliancePage) {
		this.compliancePage = compliancePage;
	}
	/**
	 * @return Returns the complianceReasonIds.
	 */
	public String[] getComplianceReasonIds() {
		return complianceReasonIds;
	}
	/**
	 * @param complianceReasonIds The complianceReasonIds to set.
	 */
	public void setComplianceReasonIds(String[] complianceReasonIds) {
		this.complianceReasonIds = complianceReasonIds;
	}
	/**
	 * @return Returns the complianceReasons.
	 */
	public Collection getComplianceReasons() {
		return complianceReasons;
	}
	/**
	 * @param complianceReasons The complianceReasons to set.
	 */
	public void setComplianceReasons(Collection complianceReasons) {
		this.complianceReasons = complianceReasons;
	}
	/**
	 * @return Returns the conditionId.
	 */
	public String getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the conditionLiteral.
	 */
	public String getConditionLiteral() {
		return conditionLiteral;
	}
	/**
	 * @param conditionLiteral The conditionLiteral to set.
	 */
	public void setConditionLiteral(String conditionLiteral) {
		this.conditionLiteral = conditionLiteral;
	}
	/**
	 * @return Returns the conditionName.
	 */
	public String getConditionName() {
		return conditionName;
	}
	/**
	 * @param conditionName The conditionName to set.
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	/**
	 * @return Returns the confirmMessage.
	 */
	public String getConfirmMessage() {
		return confirmMessage;
	}
	/**
	 * @param confirmMessage The confirmMessage to set.
	 */
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}
	/**
	 * @return Returns the court.
	 */
	public String getCourt() {
		return court;
	}
	/**
	 * @param court The court to set.
	 */
	public void setCourt(String court) {
		this.court = court;
	}
	/**
	 * @return Returns the courtPolicyId.
	 */
	public String getCourtPolicyId() {
		return courtPolicyId;
	}
	/**
	 * @param courtPolicyId The courtPolicyId to set.
	 */
	public void setCourtPolicyId(String courtPolicyId) {
		this.courtPolicyId = courtPolicyId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return Returns the displayAction.
	 */
	public String getDisplayAction() {
		return displayAction;
	}
	/**
	 * @param displayAction The displayAction to set.
	 */
	public void setDisplayAction(String displayAction) {
		this.displayAction = displayAction;
	}
	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return Returns the eventDetails.
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	/**
	 * @param eventDetails The eventDetails to set.
	 */
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}
	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return Returns the eventTypes.
	 */
	public String getEventTypes() {
		return eventTypes;
	}
	/**
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(String eventTypes) {
		this.eventTypes = eventTypes;
	}
	/**
	 * @return Returns the isCompliant.
	 */
	public boolean isCompliant() {
		return isCompliant;
	}
	/**
	 * @param isCompliant The isCompliant to set.
	 */
	public void setCompliant(boolean isCompliant) {
		this.isCompliant = isCompliant;
	}
	/**
	 * @return Returns the group1Id.
	 */
	public String getGroup1Id() {
		return group1Id;
	}
	/**
	 * @param group1Id The group1Id to set.
	 */
	public void setGroup1Id(String group1Id) {
		this.group1Id = group1Id;
	}
	/**
	 * @return Returns the group2Id.
	 */
	public String getGroup2Id() {
		return group2Id;
	}
	/**
	 * @param group2Id The group2Id to set.
	 */
	public void setGroup2Id(String group2Id) {
		this.group2Id = group2Id;
	}
	/**
	 * @return Returns the groups.
	 */
	public Collection getGroups() {
		return groups;
	}
	/**
	 * @param groups The groups to set.
	 */
	public void setGroups(Collection groups) {
		this.groups = groups;
	}
	/**
	 * @return Returns the group2.
	 */
	public Collection getGroup2() {
		return group2;
	}
	/**
	 * @param group2 The group2 to set.
	 */
	public void setGroup2(Collection group2) {
		this.group2 = group2;
	}
	/**
	 * @return Returns the nonComplianceCount.
	 */
	public String getNonComplianceCount() {
		return nonComplianceCount;
	}
	/**
	 * @param nonComplianceCount The nonComplianceCount to set.
	 */
	public void setNonComplianceCount(String nonComplianceCount) {
		this.nonComplianceCount = nonComplianceCount;
	}
    /**
	 * @return Returns the caseNumbers.
	 */
	public Collection getCaseNumbers() {
		return caseNumbers;
	}
	/**
	 * @param caseNumbers The caseNumbers to set.
	 */
	public void setCaseNumbers(Collection caseNumbers) {
		this.caseNumbers = caseNumbers;
	}
	/**
	 * @return Returns the likeConditions.
	 */
	public List getLikeConditions() {
		return likeConditions;
	}
	/**
	 * @param likeConditions The likeConditions to set.
	 */
	public void setLikeConditions(List likeConditions) {
		this.likeConditions = likeConditions;
	}
	/**
	 * @return Returns the uniqueConditions.
	 */
	public List getUniqueConditions() {
		return uniqueConditions;
	}
	/**
	 * @param uniqueConditions The uniqueConditions to set.
	 */
	public void setUniqueConditions(List uniqueConditions) {
		this.uniqueConditions = uniqueConditions;
	}
	/**
	 * @return Returns the popupLiteral.
	 */
	public String getPopupLiteral() {
		return popupLiteral;
	}
	/**
	 * @param popupLiteral The popupLiteral to set.
	 */
	public void setPopupLiteral(String popupLiteral) {
		this.popupLiteral = popupLiteral;
	}
	/**
	 * @return Returns the popupLiteralName.
	 */
	public String getPopupLiteralName() {
		return popupLiteralName;
	}
	/**
	 * @param popupLiteralName The popupLiteralName to set.
	 */
	public void setPopupLiteralName(String popupLiteralName) {
		this.popupLiteralName = popupLiteralName;
	}
	/**
	 * @return Returns the selectedConditionIds.
	 */
	public String[] getSelectedConditionIds() {
		return selectedConditionIds;
	}
	/**
	 * @param selectedConditions The selectedConditionIds to set.
	 */
	public void setSelectedConditionIds(String[] selectedConditionIds) {
		this.selectedConditionIds = selectedConditionIds;
	}
	/**
	 * @return Returns the showFilter.
	 */
	public String getShowFilter() {
		return showFilter;
	}
	/**
	 * @param showFilter The showFilter to set.
	 */
	public void setShowFilter(String showFilter) {
		this.showFilter = showFilter;
	}
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public String getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(String sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
/** Casenote field getters and setters */
	/**
	 * @return the aMPMId
	 */
	public String getAMPMId() {
		return AMPMId;
	}
	/**
	 * @param id the aMPMId to set
	 */
	public void setAMPMId(String id) {
		AMPMId = id;
	}	
	/**
	 * @return Returns the associateIds.
	 */
	public String[] getAssociateIds() {
		return associateIds;
	}
	/**
	 * @param associateIds The associateIds to set.
	 */
	public void setAssociateIds(String[] associateIds) {
		this.associateIds = associateIds;
	}
	/**
	 * @return Returns the associates.
	 */
	public String getAssociates() {
		return associates;
	}
	/**
	 * @param associates The associates to set.
	 */
	public void setAssociates(String associates) {
		this.associates = associates;
	}
	/**
	 * @return Returns the associateId.
	 */
	public String getAssociateId() {
		return associateId;
	}
	/**
	 * @param associateId The associateId to set.
	 */
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	/**
	 * @return Returns the relationship.
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship The relationship to set.
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	/**
	 * @return Returns the associatesList.
	 */
	public Collection getAssociatesList() {
	    if (associatesList == null || associatesList.size() == 0) {
            // get groups
	    	associatesList = UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(this.getSuperviseeId());
        }
        return associatesList;
	}
	/**
	 * @param associatesList The associatesList to set.
	 */
	public void setAssociatesList(Collection associatesList) {
		this.associatesList = associatesList;
	}	
	/**
	 * @return Returns the casenoteSubjectList.
	 */
	public Collection getCasenoteSubjectList() {
		return casenoteSubjectList;
	}
	/**
	 * @param casenoteSubjectList The casenoteSubjectList to set.
	 */
	public void setCasenoteSubjectList(Collection casenoteSubjectList) {
		this.casenoteSubjectList = casenoteSubjectList;
	}
	/**
	 * @return Returns the casenoteTypeList.
	 */
	public Collection getCasenoteTypeList() {
		return casenoteTypeList;
	}
	/**
	 * @param casenoteTypeList The casenoteTypeList to set.
	 */
	public void setCasenoteTypeList(Collection casenoteTypeList) {
		this.casenoteTypeList = casenoteTypeList;
	}
	/**
	 * @return Returns the collateralList.
	 */
	public Collection getCollateralList() {
		return collateralList;
	}
	/**
	 * @param collateralList The collateralList to set.
	 */
	public void setCollateralList(Collection collateralList) {
		this.collateralList = collateralList;
	}
	/**
	 * @return Returns the contactMethodList.
	 */
	public Collection getContactMethodList() {
		return contactMethodList;
	}
	/**
	 * @param contactMethodList The contactMethodList to set.
	 */
	public void setContactMethodList(Collection contactMethodList) {
		this.contactMethodList = contactMethodList;
	}
	/**
	 * @return Returns the contactWithAssociatesList.
	 */
	public Collection getContactWithAssociatesList() {
		return contactWithAssociatesList;
	}
	/**
	 * @param contactWithAssociatesList The contactWithAssociatesList to set.
	 */
	public void setContactWithAssociatesList(Collection contactWithAssociatesList) {
		this.contactWithAssociatesList = contactWithAssociatesList;
	}
	/**
	 * @return Returns the contactWithList.
	 */
	public Collection getContactWithList() {
		return contactWithList;
	}
	/**
	 * @param contactWithList The contactWithList to set.
	 */
	public void setContactWithList(Collection contactWithList) {
		this.contactWithList = contactWithList;
	}
	/**
	 * @return Returns the currentCasenotes.
	 */
	public Collection getCurrentCasenotes() {
		return currentCasenotes;
	}
	/**
	 * @param currentCasenotes The currentCasenotes to set.
	 */
	public void setCurrentCasenotes(Collection currentCasenotes) {
		this.currentCasenotes = currentCasenotes;
	}
	/**
	 * @return Returns the selectedAssociateIds.
	 */
	public String[] getSelectedAssociateIds() {
		return selectedAssociateIds;
	}
	/**
	 * @param selectedAssociateIds The selectedAssociateIds to set.
	 */
	public void setSelectedAssociateIds(String[] selectedAssociateIds) {
		this.selectedAssociateIds = selectedAssociateIds;
	}
	/**
	 * @return Returns the casenoteDate.
	 */
	public Date getCasenoteDate() {
		return casenoteDate;
	}
	/**
	 * @param casenoteDate The casenoteDate to set.
	 */
	public void setCasenoteDate(Date casenoteDate) {
		this.casenoteDate = casenoteDate;
		if(casenoteDate==null){
			casenoteDateAsString= "";
		}
		try{
			casenoteDateAsString=DateUtil.dateToString(casenoteDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			casenoteDateAsString="";
		}
	}
	/**
	 * @return Returns the date.
	 */
	public void setCasenoteDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals("")){
			casenoteDate=null;
		}
		try {
			casenoteDate=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			casenoteDate=null;
		}
	}
	/**
	 * @return Returns the date.
	 */
	public String getCasenoteDateAsStr() {
		return casenoteDateAsString;
	}	
	/**
	 * @return Returns the casenoteDateAsString.
	 */
	public String getCasenoteDateAsString() {
		return casenoteDateAsString;
	}
	/**
	 * @param casenoteDateAsString The casenoteDateAsString to set.
	 */
	public void setCasenoteDateAsString(String casenoteDateAsString) {
		this.casenoteDateAsString = casenoteDateAsString;
	}
	/**
	 * @return Returns the casenoteId.
	 */
	public String getCasenoteId() {
		return casenoteId;
	}
	/**
	 * @param casenoteId The casenoteId to set.
	 */
	public void setCasenoteId(String casenoteId) {
		this.casenoteId = casenoteId;
	}
	/**
	 * @return Returns the casenoteStatus.
	 */
	public String getCasenoteStatus() {
		return casenoteStatus;
	}
	/**
	 * @param casenoteStatus The casenoteStatus to set.
	 */
	public void setCasenoteStatus(String casenoteStatus) {
		this.casenoteStatus = casenoteStatus;
	}
	/**
	 * @return Returns the casenoteStatusId.
	 */
	public String getCasenoteStatusId() {
		return casenoteStatusId;
	}
	/**
	 * @param casenoteStatusId The casenoteStatusId to set.
	 */
	public void setCasenoteStatusId(String casenoteStatusId) {
		this.casenoteStatusId = casenoteStatusId;
		if(this.casenoteStatusId==null || this.casenoteStatusId.equals("")){
			this.casenoteStatus="";
			return;
		}
			
		if(getCasenoteStatusList() !=null &&  getCasenoteStatusList().size()>0){
			this.casenoteStatus=CodeHelper.getCodeDescriptionByCode(getCasenoteStatusList(),this.casenoteStatusId);
		}
	}
	/**
	 * @return Returns the casenoteStatusList.
	 */
	public Collection getCasenoteStatusList() {
//		if(casenoteStatusList==null)
//			setAllLists();
		return casenoteStatusList;
	}
	/**
	 * @param casenoteStatusList The casenoteStatusList to set.
	 */
	public void setCasenoteStatusList(Collection aCasenoteStatusList) {
		casenoteStatusList = aCasenoteStatusList;
	}	
	/**
	 * @return Returns the casenoteText.
	 */
	public String getCasenoteText() {
		return casenoteText;
	}
	/**
	 * @param casenoteText The casenoteText to set.
	 */
	public void setCasenoteText(String casenoteText) {
		if(casenoteText!=null )
			casenoteText=UIUtil.removeStarting_BR_P_XMLtags(casenoteText);
		this.casenoteText = casenoteText;
	}
	/**
	 * @return Returns the casenoteTime.
	 */
	public String getCasenoteTime() {
		return casenoteTime;
	}
	/**
	 * @param casenoteTime The casenoteTime to set.
	 */
	public void setCasenoteTime(String aCasenoteTime) {
		if(aCasenoteTime!=null && (aCasenoteTime.length()==4 || aCasenoteTime.length()==7)){
			aCasenoteTime="0" + aCasenoteTime;
		}
		this.casenoteTime = aCasenoteTime;
	}
	/**
	 * @return Returns the casenoteType.
	 */
	public String getCasenoteType() {
		return casenoteType;
	}
	/**
	 * @param casenoteType The casenoteType to set.
	 */
	public void setCasenoteType(String casenoteType) {
		this.casenoteType = casenoteType;
	}
	/**
	 * @return Returns the casenoteTypeId.
	 */
	public String getCasenoteTypeId() {
		return casenoteTypeId;
	}
	/**
	 * @param casenoteTypeId The casenoteTypeId to set.
	 */
	public void setCasenoteTypeId(String casenoteTypeId) {
		this.casenoteTypeId = casenoteTypeId;
		if(this.casenoteTypeId==null || this.casenoteTypeId.equals("")){
			this.casenoteType="";
			return;
		}
			
		if(getCasenoteTypeList() !=null &&  getCasenoteTypeList().size()>0){
			this.casenoteType=CodeHelper.getCodeDescriptionByCode(getCasenoteTypeList(),this.casenoteTypeId);
		}
	}
	/**
	 * @return Returns the collateral.
	 */
	public String getCollateral() {
		return collateral;
	}
	/**
	 * @param collateral The collateral to set.
	 */
	public void setCollateral(String collateral) {
		this.collateral = collateral;
	}
	/**
	 * @return Returns the collateralId.
	 */
	public String getCollateralId() {
		return collateralId;
	}
	/**
	 * @param collateralId The collateralId to set.
	 */
	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
		if(this.collateralId==null || this.collateralId.equals("")){
			this.collateral="";
			return;
		}
			
		if(getCollateralList() !=null &&  getCollateralList().size()>0){
			this.collateral=CodeHelper.getCodeDescriptionByCode(getCollateralList(),this.collateralId);
		}
	}
	/**
	 * @return Returns the contactMethod.
	 */
	public String getContactMethod() {
		return contactMethod;
	}
	/**
	 * @param contactMethod The contactMethod to set.
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	/**
	 * @return Returns the contactMethodId.
	 */
	public String getContactMethodId() {
		return contactMethodId;
	}
	/**
	 * @param contactMethodId The contactMethodId to set.
	 */
	public void setContactMethodId(String contactMethodId) {
		this.contactMethodId = contactMethodId;
		if(this.contactMethodId==null || this.contactMethodId.equals("")){
			this.contactMethod="";
			return;
		}
			
		if(getContactMethodList() !=null &&  getContactMethodList().size()>0){
			this.contactMethod=CodeHelper.getCodeDescriptionByCode(getContactMethodList(),this.contactMethodId);
		}
	}
	/**
	 * @return Returns the contextId.
	 */
	public String getContextId() {
		return contextId;
	} 
	/**
	 * @param contextId The contextId to set.
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	} 
	/**
	 * @return Returns the createdByName.
	 */
	public IName getCreatedByName() {
		if(createdByName==null){
			createdByName=new Name();
		}
		return createdByName;
	}
	/**
	 * @param createdByName The createdByName to set.
	 */
	public void setCreatedByName(IName createdByName) {
		this.createdByName = createdByName;
	}
	/**
	 * @return Returns the generatedBy.
	 */
	public String getGeneratedBy() {
		return generatedBy;
	}
	/**
	 * @param generatedBy The generatedBy to set.
	 */
	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}
	/**
	 * @return Returns the generatedById.
	 */
	public String getGeneratedById() {
		return generatedById;
	}
	/**
	 * @param generatedById The generatedById to set.
	 */
	public void setGeneratedById(String generatedById) {
		this.generatedById = generatedById;
		if(this.generatedById==null || this.generatedById.equals("")){
			this.generatedBy="";
			return;
		}
			
		if(getGeneratedByList() !=null &&  getGeneratedByList().size()>0){
			this.generatedBy=CodeHelper.getCodeDescriptionByCode(getGeneratedByList(),this.generatedById);
		}
	}
	/**
	 * @return Returns the generatedByList.
	 */
	public Collection getGeneratedByList() {
//		if(generatedByList==null)
//			setAllLists();
		return generatedByList;
	}
	/**
	 * @param generatedByList The generatedByList to set.
	 */
	public void setGeneratedByList(Collection aGeneratedByList) {
		this.generatedByList = aGeneratedByList;
	}	
	/**
	 * @return Returns the subjectIds.
	 */
	public String[] getSubjectIds() {
		return subjectIds;
	}
	/**
	 * @param subjectIds The subjectIds to set.
	 */
	public void setSubjectIds(String[] subjectIds) {
		subjects="";
		if(subjectIds!=null && subjectIds.length>0){
			StringBuffer myStrBuffer=new StringBuffer();
			for(int loopX=0;loopX<subjectIds.length;loopX++){
				String subjectItem=subjectIds[loopX];
				if(getCasenoteSubjectList() !=null &&  getCasenoteSubjectList().size()>0){
					String subjDesc=CodeHelper.getCodeDescriptionByCode(getCasenoteSubjectList(),subjectItem);
					if(myStrBuffer.length()>0){
						myStrBuffer.append(", ");
					}
					myStrBuffer.append(subjDesc);
				}
			}
			subjects=myStrBuffer.toString();
		}
		this.subjectIds = subjectIds;
	}
	/**
	 * @return Returns the subjects.
	 */
	public String getSubjects() {
		return subjects;
	}
	/**
	 * @param subjects The subjects to set.
	 */
	public void setSubjects(String subjects) {
		this.subjects = subjects;
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
	 * @return Returns the supervisionPeriodBeginDateAsString.
	 */
	public String getSupervisionPeriodBeginDateAsString() {
		return supervisionPeriodBeginDateAsString;
	}
	/**
	 * @param supervisionPeriodBeginDateAsString The supervisionPeriodBeginDateAsString to set.
	 */
	public void setSupervisionPeriodBeginDateAsString(String supervisionPeriodBeginDateAsString) {
		this.supervisionPeriodBeginDateAsString = supervisionPeriodBeginDateAsString;
	}
/** CASENOTE SEARCH FIELDS */

	/**
	 * @return Returns the searchAssociateIds.
	 */
	public String[] getSearchAssociateIds() {
		return searchAssociateIds;
	}
	/**
	 * @param searchAssociateIds The searchAssociateIds to set.
	 */
	public void setSearchAssociateIds(String[] searchAssociateIds) {
		this.searchAssociateIds = searchAssociateIds;
	}
	/**
	 * @return Returns the searchBeginDate.
	 */
	public String getSearchBeginDate() {
		return searchBeginDate;
	}
	/**
	 * @param searchBeginDate The searchBeginDate to set.
	 */
	public void setSearchBeginDate(String searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}
	/**
	 * @return Returns the searchBeginDateAsString.
	 */
	public String getSearchBeginDateAsString() {
		return searchBeginDateAsString;
	}
	/**
	 * @param searchBeginDateAsString The searchBeginDateAsString to set.
	 */
	public void setSearchBeginDateAsString(String searchBeginDateAsString) {
		this.searchBeginDateAsString = searchBeginDateAsString;
	}
	/**
	 * @return Returns the searchById.
	 */
	public String getSearchById() {
		return searchById;
	}
	/**
	 * @param searchById The searchById to set.
	 */
	public void setSearchById(String searchById) {
		this.searchById = searchById;
	}
	/**
	 * @return Returns the searchByList.
	 */
	public Collection getSearchByList() {
		return searchByList;
	}
	/**
	 * @param searchByList The searchByList to set.
	 */
	public void setSearchByList(Collection searchByList) {
		this.searchByList = searchByList;
	}
	/**
	 * @return Returns the searchCasenoteTypeId.
	 */
	public String getSearchCasenoteTypeId() {
		return searchCasenoteTypeId;
	}
	/**
	 * @param searchCasenoteTypeId The searchCasenoteTypeId to set.
	 */
	public void setSearchCasenoteTypeId(String searchCasenoteTypeId) {
		this.searchCasenoteTypeId = searchCasenoteTypeId;
	}
	/**
	 * @return Returns the searchCollateralId.
	 */
	public String getSearchCollateralId() {
		return searchCollateralId;
	}
	/**
	 * @param searchCollateralId The searchCollateralId to set.
	 */
	public void setSearchCollateralId(String searchCollateralId) {
		this.searchCollateralId = searchCollateralId;
	}
	/**
	 * @return Returns the searchCourt.
	 */
	public String getSearchCourt() {
		return searchCourt;
	}
	/**
	 * @param searchCourt The searchCourt to set.
	 */
	public void setSearchCourt(String searchCourt) {
		this.searchCourt = searchCourt;
	}
	/**
	 * @return Returns the searchEndDate.
	 */
	public String getSearchEndDate() {
		return searchEndDate;
	}
	/**
	 * @param searchEndDate The searchEndDate to set.
	 */
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	/**
	 * @return Returns the searchEndDateAsString.
	 */
	public String getSearchEndDateAsString() {
		return searchEndDateAsString;
	}
	/**
	 * @param searchEndDateAsString The searchEndDateAsString to set.
	 */
	public void setSearchEndDateAsString(String searchEndDateAsString) {
		this.searchEndDateAsString = searchEndDateAsString;
	}
	/**
	 * @return Returns the searchGeneratedById.
	 */
	public String getSearchGeneratedById() {
		return searchGeneratedById;
	}
	/**
	 * @param searchGeneratedById The searchGeneratedById to set.
	 */
	public void setSearchGeneratedById(String searchGeneratedById) {
		this.searchGeneratedById = searchGeneratedById;
	}
	/**
	 * @return Returns the searchCaseNumberIds.
	 */
	public String[] getSearchCaseNumberIds() {
		return searchCaseNumberIds;
	}
	/**
	 * @param searchCaseNumberIds The searchCaseNumberIds to set.
	 */
	public void setSearchCaseNumberIds(String[] searchCaseNumberIds) {
		this.searchCaseNumberIds = searchCaseNumberIds;
	}
	/**
	 * @return Returns the searchSubjectIds.
	 */
	public String[] getSearchSubjectIds() {
		return searchSubjectIds;
	}
	/**
	 * @param searchSubjectIds The searchSubjectIds to set.
	 */
	public void setSearchSubjectIds(String[] searchSubjectIds) {
		subjects="";
		if(subjectIds!=null && subjectIds.length>0){
			StringBuffer myStrBuffer=new StringBuffer();
			for(int loopX=0;loopX<subjectIds.length;loopX++){
				String subjectItem=subjectIds[loopX];
				if(getCasenoteSubjectList() !=null &&  getCasenoteSubjectList().size()>0){
					String subjDesc=CodeHelper.getCodeDescriptionByCode(getCasenoteSubjectList(),subjectItem);
					if(myStrBuffer.length()>0){
						myStrBuffer.append(", ");
					}
					myStrBuffer.append(subjDesc);
				}
			}
			subjects=myStrBuffer.toString();
		}
		this.searchSubjectIds = subjectIds;
	}
/** SET CONDITIONS TO NONCOMPLIANT EVENT FIELDS */ 	

	/**
	 * @return Returns the occuranceDate.
	 */
	public Date getOccurrenceDate() {
		return occurrenceDate;
	}
	/**
	 * @param occurenceDate The occurenceDate to set.
	 */
	public void setOccurrenceDate(Date occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
		if(occurrenceDate==null){
			occurrenceDateStr = "";
		}
		try{
			occurrenceDateStr = DateUtil.dateToString(occurrenceDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			occurrenceDateStr ="";
		}		
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
	 * @return Returns the occuranceTime.
	 */
	public String getOccurrenceTime() {
		return occurrenceTime;
	}
	/**
	 * @param occuranceTime The occuranceTime to set.
	 */
	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}
	/**
	 * @return Returns the ncEventType.
	 */
	public String getNcEventType() {
		return ncEventType;
	}
	/**
	 * @param ncEventType The ncEventType to set.
	 */
	public void setNcEventType(String ncEventType) {
		this.ncEventType = ncEventType;
	}
	/**
	 * @return Returns the ncEventType.
	 */
	public String getNewEventType() {
		return newEventType;
	}
	/**
	 * @param newEventType The newEventType to set.
	 */
	public void setNewEventType(String newEventType) {
		this.newEventType = newEventType;
	}	
	/**
	 * @return Returns the selectedLikeConditionsEvents.
	 */
	public Collection getSelectedLikeConditionsEvents() {
		return selectedLikeConditionsEvents;
	}
	/**
	 * @param selectedLikeConditionsEvents The selectedLikeConditionsEvents to set.
	 */
	public void setSelectedLikeConditionsEvents(Collection selectedLikeConditionsEvents) {
		this.selectedLikeConditionsEvents = selectedLikeConditionsEvents;
	}
	/**
	 * @return Returns the selectedUniqueConditionsEvents.
	 */
	public Collection getSelectedUniqueConditionsEvents() {
		return selectedUniqueConditionsEvents;
	}
	/**
	 * @param selectedUniqueConditionsEvents The selectedUniqueConditionsEvents to set.
	 */
	public void setSelectedUniqueConditionsEvents(Collection selectedUniqueConditionsEvents) {
		this.selectedUniqueConditionsEvents = selectedUniqueConditionsEvents;
	}
/**
	 * @return Returns the orderConditionId.
	 */
	public String getOrderConditionId() {
		return orderConditionId;
	}
	/**
	 * @param orderConditionId The orderConditionId to set.
	 */
	public void setOrderConditionId(String orderConditionId) {
		this.orderConditionId = orderConditionId;
	}
	/**
	 * @return Returns the orderId.
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId The orderId to set.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return Returns the selectedConditionsEvents.
	 */
	public Collection getSelectedConditionsEvents() {
		return selectedConditionsEvents;
	}
	/**
	 * @param selectedConditionsEvents The selectedConditionsEvents to set.
	 */
	public void setSelectedConditionsEvents(Collection selectedConditionsEvents) {
		this.selectedConditionsEvents = selectedConditionsEvents;
	}
	/**
	 * @param list
	 * @param compForm
	 */
	public void setSelectedConditionsEvents(List list, ComplianceForm compForm) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
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
	/**
	 * @return Returns the selectedCaseNumbers.
	 */
	public String[] getSelectedCaseNumbers() {
		return selectedCaseNumbers;
	}
	/**
	 * @param selectedCaseNumbers The selectedCaseNumbers to set.
	 */
	public void setSelectedCaseNumbers(String[] selectedCaseNumbers) {
		if(selectedCaseNumbers != null && "".equals(selectedCaseNumbers[0])) {
			selectedCaseNumbers = null;
		}
		else {
			this.selectedCaseNumbers = selectedCaseNumbers;
		}
	}
	/**
	 * @return Returns the chainNum.
	 */
	public String getChainNum() {
		return chainNum;
	}
	/**
	 * @param chainNum The chainNum to set.
	 */
	public void setChainNum(String chainNum) {
		this.chainNum = chainNum;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}