/*
 * Created on Aug 1, 2006
 *
 */
package ui.supervision.administercasenotes.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;

/**
 * @author jjose
 *
 */
public class CasenoteSearchForm extends ActionForm {

	
	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	// NOTE CONTEXT ID is the same as CASENOTE TYPE ID
	private Date casenoteBeginDate;
	private Date casenoteEndDate;
	//private String casenoteBeginDateAsString;
	//private String casenoteEndDateAsString;
	private String court;
	private String contactMethodId;
	private String contactMethod;
	private String[] subjectIds;
	private String subjects;
	private String[] associateIds;
	private String associates;
	private String collateralId;
	private String collateral;
	private String casenoteText;
	private IName createdByName=new Name();
	private String generatedById;
	private String generatedBy;
	private String casenoteTypeId;
	private String casenoteType;
	private String casenoteStatusId;
	private String casenoteStatus;
	private String cases;
	private String[] caseIds;
	private String searchById;
	private Date createDate;
		
	private Collection casesList=null;
	private Collection casenoteResults=null;
	
	private Collection associateList;
	//LISTS
	private static Collection contactMethodList;
	private static Collection collateralList;
	private static Collection generatedByList;
	private static Collection casenoteTypeList;
	private static Collection casenoteStatusList;
	private static Collection conditionStatusList;
	private static Collection contactRelationshipList;
	private static Collection searchByList;
	private static Collection casenoteSubjectList;
	
	public CasenoteSearchForm(){
		setAllLists();
	}
	
	private void setAllLists(){
		//UICasenotesLoadCoadTables.getInstance().setCasenoteSearchForm(this);
        String agencyId = SecurityUIHelper.getUserAgencyId();

        contactMethodList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_METHOD);
        casenoteSubjectList = ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.CASENOTE_SUBJECT);
        casenoteTypeList = ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.CASENOTE_TYPE);
        collateralList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_WITH);
        generatedByList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.HOW_GENERATED);
        searchByList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CASENOTE_SEARCHBY);
	}
	
	public void clearSearchFields()
	{
		casenoteBeginDate=null;
		casenoteEndDate=null;
		court="";
		contactMethodId="";
		contactMethod="";
		subjectIds=null;
		subjects="";
		collateralId="";
		collateral="";
		casenoteText="";
		createdByName=new Name();
		generatedById="";
		generatedBy="";
		casenoteTypeId="";
		casenoteType="";
		casenoteStatusId="";
		casenoteStatus="";
		searchById="";
		cases="";
		caseIds=null;
		associateIds=null;
		associates="";
	}
	
	public void clearAll()
	{
		casesList=null;
		searchById="";
		casenoteResults=null;
		casenoteBeginDate=null;
		casenoteEndDate=null;
		court="";
		contactMethodId="";
		contactMethod="";
		subjectIds=null;
		subjects="";
		collateralId="";
		collateral="";
		casenoteText="";
		createdByName=new Name();
		generatedById="";
		generatedBy="";
		casenoteTypeId="";
		casenoteType="";
		casenoteStatusId="";
		casenoteStatus="";
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
		associateList=null;
		associateIds=null;
		associates="";
	}
	/**
	 * @return Returns the casenoteStatusList.
	 */
	public Collection getCasenoteStatusList() {
		if(casenoteStatusList==null)
			setAllLists();
		return casenoteStatusList;
	}
	/**
	 * @param casenoteStatusList The casenoteStatusList to set.
	 */
	public void setCasenoteStatusList(Collection aCasenoteStatusList) {
		casenoteStatusList = aCasenoteStatusList;
	}
	/**
	 * @return Returns the casenoteTypeList.
	 */
	public Collection getCasenoteTypeList() {
		if(casenoteTypeList==null)
			setAllLists();
		return casenoteTypeList;
	}
	/**
	 * @param casenoteTypeList The casenoteTypeList to set.
	 */
	public void setCasenoteTypeList(Collection aCasenoteTypeList) {
		casenoteTypeList = aCasenoteTypeList;
	}
	/**
	 * @return Returns the collateralList.
	 */
	public Collection getCollateralList() {
		if(collateralList==null)
			setAllLists();
		return collateralList;
	}
	/**
	 * @param collateralList The collateralList to set.
	 */
	public void setCollateralList(Collection aCollateralList) {
		collateralList = aCollateralList;
	}
	/**
	 * @return Returns the conditionStatusList.
	 */
	public Collection getConditionStatusList() {
		if(conditionStatusList==null)
			setAllLists();
		return conditionStatusList;
	}
	/**
	 * @param conditionStatusList The conditionStatusList to set.
	 */
	public void setConditionStatusList(Collection aConditionStatusList) {
		conditionStatusList = aConditionStatusList;
	}
	/**
	 * @return Returns the contactMethodList.
	 */
	public Collection getContactMethodList() {
		if(contactMethodList==null)
			setAllLists();
		return contactMethodList;
	}
	/**
	 * @param contactMethodList The contactMethodList to set.
	 */
	public void setContactMethodList(Collection aContactMethodList) {
		contactMethodList = aContactMethodList;
	}
	/**
	 * @return Returns the contactRelationshipList.
	 */
	public Collection getContactRelationshipList() {
		if(contactRelationshipList==null)
			setAllLists();
		return contactRelationshipList;
	}
	/**
	 * @param contactRelationshipList The contactRelationshipList to set.
	 */
	public void setContactRelationshipList(Collection aContactRelationshipList) {
		contactRelationshipList = aContactRelationshipList;
	}
	/**
	 * @return Returns the generatedByList.
	 */
	public Collection getGeneratedByList() {
		if(generatedByList==null)
			setAllLists();
		return generatedByList;
	}
	/**
	 * @param generatedByList The generatedByList to set.
	 */
	public void setGeneratedByList(Collection aGeneratedByList) {
		generatedByList = aGeneratedByList;
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
	 * @return Returns the casenoteDate.
	 */
	public Date getCasenoteBeginDate() {
		return casenoteBeginDate;
	}

	/**
	 * @return Returns the casenoteDate as a String.
	 */
	public String getCasenoteBeginDateAsString() {
		if (casenoteBeginDate == null)
		{
			return "";
		}
		else
		{
			return DateUtil.dateToString(casenoteBeginDate, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param casenoteDate The casenoteDate to set.
	 */
	public void setCasenoteBeginDate(Date casenoteDate) {
		this.casenoteBeginDate = casenoteDate;
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
	 * @return Returns the casenoteText.
	 */
	public String getCasenoteText() {
		return casenoteText;
	}
	/**
	 * @param casenoteText The casenoteText to set.
	 */
	public void setCasenoteText(String casenoteText) {
		this.casenoteText = casenoteText;
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
	 * @return Returns the subjectIds.
	 */
	public String[] getSubjectIds() {
		return subjectIds;
	}
	/**
	 * @param subjectIds The subjectIds to set.
	 */
	public void setSubjectIds(String[] subjectIds) {
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
	 * @return Returns the casenoteStatusId.
	 */
	public String getCasenoteStatusId() {
		return casenoteStatusId;
	}
	/**
	 * @return Returns the casenoteTypeId.
	 */
	public String getCasenoteTypeId() {
		return casenoteTypeId;
	}
	/**
	 * @return Returns the collateralId.
	 */
	public String getCollateralId() {
		return collateralId;
	}
	
	/**
	 * @return Returns the contactMethodId.
	 */
	public String getContactMethodId() {
		return contactMethodId;
	}
	
	/**
	 * @return Returns the generatedById.
	 */
	public String getGeneratedById() {
		return generatedById;
	}
	
	
	/**
	 * @return Returns the casenoteEndDate.
	 */
	public Date getCasenoteEndDate() {
		return casenoteEndDate;
	}

	/**
	 * @return Returns the casenoteEndDate as a String.
	 */
	public String getCasenoteEndDateAsString() {
		if (casenoteEndDate == null)
		{
			return "";
		}
		else
		{
			return DateUtil.dateToString(casenoteEndDate, UIConstants.DATE_FMT_1);
		}
	}

/**
	 * @param casenoteEndDate The casenoteEndDate to set.
	 */
	public void setCasenoteEndDate(Date casenoteEndDate) {
		this.casenoteEndDate = casenoteEndDate;
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
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}
	/**
	 * @param listsSet The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
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
	 * @return Returns the casenoteResults.
	 */
	public Collection getCasenoteResults() {
		return casenoteResults;
	}
	/**
	 * @param casenoteResults The casenoteResults to set.
	 */
	public void setCasenoteResults(Collection casenoteResults) {
		this.casenoteResults = casenoteResults;
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
	 * @return Returns the associateList.
	 */
	public Collection getAssociateList() {
		return associateList;
	}
	/**
	 * @param associateList The associateList to set.
	 */
	public void setAssociateList(Collection associateList) {
		this.associateList = associateList;
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
	 * @return Returns the cases.
	 */
	public Collection getCasesList() {
		return casesList;
	}
	/**
	 * @param cases The cases to set.
	 */
	public void setCasesList(Collection cases) {
		this.casesList = cases;
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
        CasenoteSearchForm.searchByList = searchByList;
    }
    /**
     * @return Returns the caseId.
     */
    public String getCases() {
        return cases;
    }
    /**
     * @param caseId The caseId to set.
     */
    public void setCases(String caseId) {
        this.cases = caseId;
    }
    /**
     * @return Returns the casenoteSubjectList.
     */
    public  Collection getCasenoteSubjectList() {
        return casenoteSubjectList;
    }
    /**
     * @param casenoteSubjectList The casenoteSubjectList to set.
     */
    public  void setCasenoteSubjectList(Collection casenoteSubjectList) {
        CasenoteSearchForm.casenoteSubjectList = casenoteSubjectList;
    }
    
    /**
	 * @return Returns the dateOfBirth.
	 */
	public void setCasenoteBeginDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			casenoteBeginDate=null;
		try{
			casenoteBeginDate=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			casenoteBeginDate=null;
		}
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getCasenoteBeginDateAsStr() {
		if(casenoteBeginDate==null){
			return "";
		}
		try{
			return DateUtil.dateToString(casenoteBeginDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			return "";
		}
	}
	 /**
	 * @return Returns the dateOfBirth.
	 */
	public void setCasenoteEndDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			casenoteEndDate=null;
		try{
			casenoteEndDate=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			casenoteEndDate=null;
		}
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getCasenoteEndDateAsStr() {
		if(casenoteEndDate==null){
			return "";
		}
		try{
			return DateUtil.dateToString(casenoteEndDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			return "";
		}
	}
    
    
	/**
	 * @return Returns the caseIds.
	 */
	public String[] getCaseIds() {
		return caseIds;
	}
	/**
	 * @param caseIds The caseIds to set.
	 */
	public void setCaseIds(String[] caseIds) {
		this.caseIds = caseIds;
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
	
	
		
}// END CLASS
