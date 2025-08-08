/*
 * Created on Nov 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;


/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderProgramForm extends ActionForm {
//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	private boolean contractProgram;
	private String contractProgramAsStr;
	private String currentStatusDesc;
	private String currentStatusId;
	private String description;
	private String identifier;
	private String[] languagesOfferedIds;
	private String languagesOfferedDesc;
	private String name;
	private String officeHours;
	private String origProgramName;
	private int programCost;
	private String programDesc;
	private Date programEndDate;
	private String programEndDateAsStr;
	private String programGroupDesc;
	private String programGroupId;
	private String programId;
	private Date programStartDate;
	private String programStartDateAsStr;
	private String programTypeDesc;
	private String programTypeId;
	private String programSubTypeDesc;
	private String programSubTypeId;
	private String selectedLocationDescs;
	private String[] selectedLocationIds;
	private String[] selectedProgLocationIds;
	private String sexSpecificDesc;
	private String sexSpecificId;
	private String stateProgramCode;
//	private String referralType;
	private String serviceProviderId="";
	private String price;
	
	private String statusDateAsStr;
	private Date statusDate;
	private String statusReason;
	
	private String changeToStatusDesc;
	private String changeToStatusId;
	private String changeToStatusDateAsStr;
	private Date changeToStatusDate;
	private String changeToStatusReason;
	
	private Object[] oldProgLocations;
	private Collection locations;
	private Collection programGroups;
	private Collection programTypes;
	private Collection programSubTypes;
	private Collection programHeirarchyList;
	
	private String selectedDivision;
	private String selectedDivisionName;
	private List availableDivisions;
	private String selectedProgramUnit;
	private String selectedProgramUnitName;
	private List divisionProgramUnits = new ArrayList();
	private String selectedIncarcerationCondition;
	private String selectedIncarcerationConditionName;
	private List incarcerationConditions;
	
	
	
	
	public void clear(){
		contractProgram=false;
		contractProgramAsStr="";
		currentStatusDesc="";
		currentStatusId="";
		description="";
		identifier="";
		languagesOfferedIds=null;
		languagesOfferedDesc="";
		name="";
		officeHours="";
		origProgramName = "";
		price="0.00";
		programCost=0;
		programDesc="";
		programEndDate=null;
		programEndDateAsStr="";
		programGroupDesc="";
		programGroupId="";
		programId="";
		programStartDate=null;
		programStartDateAsStr="";
		programTypeDesc="";
		programTypeId="";
		programSubTypeDesc="";
		programSubTypeId="";
		selectedLocationDescs="";
		selectedLocationIds=null;
		selectedProgLocationIds=null;
		sexSpecificDesc="";
		sexSpecificId="";
		stateProgramCode="";
		serviceProviderId="";
		
		statusDateAsStr="";
		statusDate=null;
		statusReason="";
		
		changeToStatusDesc="";
		changeToStatusId="";
		changeToStatusDateAsStr="";
		changeToStatusDate=null;
		changeToStatusReason="";
		oldProgLocations=null;
		locations=new ArrayList();
		programGroups=new ArrayList();
		programTypes=new ArrayList();
		programSubTypes=new ArrayList();
		
		selectedDivision = "";
		selectedProgramUnit = "";
		selectedIncarcerationCondition = "";
		divisionProgramUnits = new ArrayList();
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
	 * @return Returns the contractProgram.
	 */
	public boolean isContractProgram() {
		return contractProgram;
	}
	/**
	 * @param contractProgram The contractProgram to set.
	 */
	public void setContractProgram(boolean aContractProgram) {
		this.contractProgram = aContractProgram;
		if(this.contractProgram){
			contractProgramAsStr=(UIConstants.YES_FULL_TEXT);
		}
		else{
			contractProgramAsStr=(UIConstants.NO_FULL_TEXT);
		}
	}
	/**
	 * @return Returns the contractProgramAsStr.
	 */
	public String getContractProgramAsStr() {
		return contractProgramAsStr;
	}
	/**
	 * @param contractProgramAsStr The contractProgramAsStr to set.
	 */
	public void setContractProgramAsStr(String aContractProgramAsStr) {
		this.contractProgramAsStr = aContractProgramAsStr;
		if(this.contractProgramAsStr!=null && (PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_YES.equals(this.contractProgramAsStr) || contractProgramAsStr.equalsIgnoreCase("true"))){
			contractProgram=true;
			contractProgramAsStr=UIConstants.YES_FULL_TEXT;
		}
		else if(this.contractProgramAsStr!=null && (PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_NO.equals(this.contractProgramAsStr) || contractProgramAsStr.equalsIgnoreCase("false"))){
			contractProgram=false;
			contractProgramAsStr=UIConstants.NO_FULL_TEXT;
		}
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
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the languagesOfferedDesc.
	 */
	public String getLanguagesOfferedDesc() {
		return languagesOfferedDesc;
	}
	/**
	 * @param languagesOfferedDesc The languagesOfferedDesc to set.
	 */
	public void setLanguagesOfferedDesc(String languagesOfferedDesc) {
		this.languagesOfferedDesc = languagesOfferedDesc;
	}
	/**
	 * @return Returns the languagesOfferedId.
	 */
	public String[] getLanguagesOfferedIds() {
		return languagesOfferedIds;
	}
	/**
	 * @param languagesOfferedId The languagesOfferedId to set.
	 */
	public void setLanguagesOfferedIds(String[] languagesOfferedId) {
		this.languagesOfferedIds = languagesOfferedId;
		StringBuffer myBuf=new StringBuffer();
		if(languagesOfferedId!=null && languagesOfferedId.length>0){
			for(int loopX=0; loopX<languagesOfferedId.length; loopX++){
				String ids=languagesOfferedId[loopX];
				String idDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LANGUAGE,ids);
				if(idDesc!=null && idDesc.length()>0){
					if(myBuf.length()>0){
						myBuf.append(", ");
					}
					myBuf.append(idDesc);
				}
			}
		}
		languagesOfferedDesc=myBuf.toString();
	}
	/**
	 * @return the oldProgLocations
	 */
	public Object[] getOldProgLocations() {
		return oldProgLocations;
	}

	/**
	 * @param oldProgLocations the oldProgLocations to set
	 */
	public void setOldProgLocations(Object[] oldProgLocations) {
		this.oldProgLocations = oldProgLocations;
	}

	/**
	 * @return Returns the locations.
	 */
	public Collection getLocations() {
		try{
			return ComplexCodeTableHelper.getLocationCodes();
		}
		catch(Exception e){
			return new ArrayList();
		}
	}
	/**
	 * @param locations The locations to set.
	 */
	public void setLocations(Collection locations) {
		this.locations = locations;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the officeHours.
	 */
	public String getOfficeHours() {
		return officeHours;
	}
	/**
	 * @param officeHours The officeHours to set.
	 */
	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}
	/**
	 * @return Returns the programEndDate.
	 */
	public Date getProgramEndDate() {
		return programEndDate;
	}
	/**
	 * @param programEndDate The programEndDate to set.
	 */
	public void setProgramEndDate(Date aProgramEndDate) {
		this.programEndDate = aProgramEndDate;
		if(programEndDate==null){
			programEndDateAsStr= "";
		}
		try{
			programEndDateAsStr=DateUtil.dateToString(programEndDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			programEndDateAsStr="";
		}
	}
	/**
	 * @return Returns the programEndDateAsStr.
	 */
	public String getProgramEndDateAsStr() {
		return programEndDateAsStr;
	}
	/**
	 * @param programEndDateAsStr The programEndDateAsStr to set.
	 */
	public void setProgramEndDateAsStr(String aProgramEndDateAsStr) {
		this.programEndDateAsStr="";
		if(aProgramEndDateAsStr==null || aProgramEndDateAsStr.equals(""))
			programEndDate=null;
		try{
			this.programEndDateAsStr = aProgramEndDateAsStr;
			programEndDate=DateUtil.stringToDate(programEndDateAsStr, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			programEndDate=null;
		}
	}
	/**
	 * @return Returns the programGroupDesc.
	 */
	public String getProgramGroupDesc() {
		return programGroupDesc;
	}
	/**
	 * @param programGroupDesc The programGroupDesc to set.
	 */
	public void setProgramGroupDesc(String programGroupDesc) {
		this.programGroupDesc = programGroupDesc;
	}
	/**
	 * @return Returns the programGroupId.
	 */
	public String getProgramGroupId() {
		return programGroupId;
	}
	/**
	 * @param programGroupId The programGroupId to set.
	 */
	public void setProgramGroupId(String programGroupId) {
		this.programGroupId = programGroupId;
		this.programGroupDesc="";
		programGroupDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_GROUP,programGroupId);

	}
	/**
	 * @return Returns the programGroups.
	 */
	public Collection getProgramGroups() {
		return programGroups;
	}
	/**
	 * @param programGroups The programGroups to set.
	 */
	public void setProgramGroups(Collection programGroups) {
		this.programGroups = programGroups;
	}
	/**
	 * @return Returns the programId.
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId The programId to set.
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return Returns the programStartDate.
	 */
	public Date getProgramStartDate() {
		return programStartDate;
	}
	/**
	 * @param programStartDate The programStartDate to set.
	 */
	public void setProgramStartDate(Date aProgramStartDate) {
		this.programStartDate = aProgramStartDate;
		if(programStartDate==null){
			programStartDateAsStr= "";
		}
		try{
			programStartDateAsStr=DateUtil.dateToString(programStartDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			programStartDateAsStr="";
		}
	}
	/**
	 * @return Returns the programStartDateAsStr.
	 */
	public String getProgramStartDateAsStr() {
		return programStartDateAsStr;
	}
	/**
	 * @param programStartDateAsStr The programStartDateAsStr to set.
	 */
	public void setProgramStartDateAsStr(String aProgramStartDateAsStr) {
		this.programStartDateAsStr="";
		if(aProgramStartDateAsStr==null || aProgramStartDateAsStr.equals(""))
			programStartDate=null;
		try{
			this.programStartDateAsStr = aProgramStartDateAsStr;
			programStartDate=DateUtil.stringToDate(programStartDateAsStr, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			programStartDate=null;
		}
	}
	/**
	 * @return Returns the programSubTypeDesc.
	 */
	public String getProgramSubTypeDesc() {
		return programSubTypeDesc;
	}
	/**
	 * @param programSubTypeDesc The programSubTypeDesc to set.
	 */
	public void setProgramSubTypeDesc(String programSubTypeDesc) {
		this.programSubTypeDesc = programSubTypeDesc;
	}
	/**
	 * @return Returns the programSubTypeId.
	 */
	public String getProgramSubTypeId() {
		return programSubTypeId;
	}
	/**
	 * @param programSubTypeId The programSubTypeId to set.
	 */
	public void setProgramSubTypeId(String programSubTypeId) {
		this.programSubTypeId = programSubTypeId;
	}
	/**
	 * @return Returns the programSubTypes.
	 */
	public Collection getProgramSubTypes() {
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_SUB_TYPE);
	}
	/**
	 * @param programSubTypes The programSubTypes to set.
	 */
	public void setProgramSubTypes(Collection programSubTypes) {
		this.programSubTypes = programSubTypes;
	}
	/**
	 * @return Returns the programTypes.
	 */
	public Collection getProgramTypes() {
		return programTypes;
	}
	/**
	 * @param programTypes The programTypes to set.
	 */
	public void setProgramTypes(Collection programTypes) {
		this.programTypes = programTypes;
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
	 * @return Returns the selectedLocationDescs.
	 */
	public String getSelectedLocationDescs() {
		return selectedLocationDescs;
	}
	/**
	 * @param selectedLocationDescs The selectedLocationDescs to set.
	 */
	public void setSelectedLocationDescs(String selectedLocationDescs) {
		this.selectedLocationDescs = selectedLocationDescs;
	}
	/**
	 * @return Returns the selectedLocationIds.
	 */
	public String[] getSelectedLocationIds() {
		return selectedLocationIds;
	}
	/**
	 * @param selectedLocationIds The selectedLocationIds to set.
	 */
	public void setSelectedLocationIds(String[] selectedLocationIds) {
		this.selectedLocationIds = selectedLocationIds;
		StringBuffer myBuf=new StringBuffer();
		List allLocations=ComplexCodeTableHelper.getLocationCodes();
		
		if(selectedLocationIds!=null && selectedLocationIds.length>0 && allLocations!=null && allLocations.size()>0){
//			int locSize=allLocations.size();
			for(int loopX=0; loopX<selectedLocationIds.length; loopX++){
				String locId=selectedLocationIds[loopX];
				String locDesc=ComplexCodeTableHelper.getLocationDesc(allLocations,locId);
				if(locDesc!=null && locDesc.length()>0){
					if(myBuf.length()>0){
						myBuf.append(", ");
					}
					myBuf.append(locDesc);
				}
			}
		}
		selectedLocationDescs=myBuf.toString();
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @return the selectedProgLocationIds
	 */
	public String[] getSelectedProgLocationIds() {
		return selectedProgLocationIds;
	}

	/**
	 * @param selectedProgLocationIds the selectedProgLocationIds to set
	 */
	public void setSelectedProgLocationIds(String[] selectedProgLocationIds) {
		this.selectedProgLocationIds = selectedProgLocationIds;
	}

	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the sexSpecificDesc.
	 */
	public String getSexSpecificDesc() {
		return sexSpecificDesc;
	}
	/**
	 * @param sexSpecificDesc The sexSpecificDesc to set.
	 */
	public void setSexSpecificDesc(String sexSpecificDesc) {
		this.sexSpecificDesc = sexSpecificDesc;
	}
	/**
	 * @return Returns the sexSpecificId.
	 */
	public String getSexSpecificId() {
		return sexSpecificId;
	}
	/**
	 * @param sexSpecificId The sexSpecificId to set.
	 */
	public void setSexSpecificId(String sexSpecificId) {
		this.sexSpecificId = sexSpecificId;
		this.sexSpecificDesc="";
		sexSpecificDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_SEX_SPECIFIC,sexSpecificId);
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
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}

	/**
	 * @return Returns the currentStatusDesc.
	 */
	public String getCurrentStatusDesc() {
		return currentStatusDesc;
	}
	/**
	 * @param currentStatusDesc The currentStatusDesc to set.
	 */
	public void setCurrentStatusDesc(String currentStatusDesc) {
		this.currentStatusDesc = currentStatusDesc;
	}
	/**
	 * @return Returns the currentStatusId.
	 */
	public String getCurrentStatusId() {
		return currentStatusId;
	}
	/**
	 * @param currentStatusId The currentStatusId to set.
	 */
	public void setCurrentStatusId(String currentStatusId) {
		this.currentStatusId = currentStatusId;
		this.currentStatusDesc="";
		currentStatusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_STATUS,currentStatusId);
	}
	
	/**
	 * @return Returns the identifier.
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier The identifier to set.
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * @return Returns the programCost.
	 */
	public int getProgramCost() {
		return programCost;
	}
	/**
	 * @param programCost The programCost to set.
	 */
	public void setProgramCost(int programCost) {
		this.programCost = programCost;
	}
	/**
	 * @return Returns the programDesc.
	 */
	public String getProgramDesc() {
		return programDesc;
	}
	/**
	 * @param programDesc The programDesc to set.
	 */
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}
	/**
	 * @return Returns the programTypeDesc.
	 */
	public String getProgramTypeDesc() {
		return programTypeDesc;
	}
	/**
	 * @param programTypeDesc The programTypeDesc to set.
	 */
	public void setProgramTypeDesc(String programTypeDesc) {
		this.programTypeDesc = programTypeDesc;
	}
	/**
	 * @return Returns the programTypeId.
	 */
	public String getProgramTypeId() {
		return programTypeId;
	}
	/**
	 * @param programTypeId The programTypeId to set.
	 */
	public void setProgramTypeId(String programTypeId) {
		this.programTypeId = programTypeId;
		this.programTypeDesc="";
		programTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_TYPE,programTypeId);

	}
	/**
	 * @return Returns the stateProgramCode.
	 */
	public String getStateProgramCode() {
		return stateProgramCode;
	}
	/**
	 * @param stateProgramCode The stateProgramCode to set.
	 */
	public void setStateProgramCode(String stateProgramCode) {
		this.stateProgramCode = stateProgramCode;
	}
	/**
	 * @return Returns the statusDate.
	 */
	public Date getStatusDate() {
		return statusDate;
	}
	/**
	 * @param statusDate The statusDate to set.
	 */
	public void setStatusDate(Date aStatusDate) {
		this.statusDate = aStatusDate;
		if(statusDate==null){
			statusDateAsStr= "";
		}
		try{
			statusDateAsStr=DateUtil.dateToString(statusDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			statusDateAsStr="";
		}
	}
	/**
	 * @return Returns the statusDateAsStr.
	 */
	public String getStatusDateAsStr() {
		return statusDateAsStr;
	}
	/**
	 * @param statusDateAsStr The statusDateAsStr to set.
	 */
	public void setStatusDateAsStr(String aStatusDateAsStr) {
		this.statusDateAsStr = aStatusDateAsStr;
		this.statusDateAsStr = "";
		if(aStatusDateAsStr==null || aStatusDateAsStr.equals(""))
			statusDate=null;
		try{
			this.statusDateAsStr = aStatusDateAsStr;
			statusDate=DateUtil.stringToDate(statusDateAsStr, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			statusDate=null;
		}
	}
	/**
	 * @return Returns the statusReason.
	 */
	public String getStatusReason() {
		return statusReason;
	}
	/**
	 * @param statusReason The statusReason to set.
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	/**
	 * @return Returns the referralType.
	 */
	public String getReferralType() {
		StringBuffer myBuffer=new StringBuffer();
		if(getProgramGroupDesc()!=null){
			myBuffer.append(getProgramGroupDesc());
		}
		if(getProgramTypeDesc()!=null){
			myBuffer.append(getProgramTypeDesc());
		}
		return myBuffer.toString();
	}
	
	/**
	 * @return Returns the changeToStatusDate.
	 */
	public Date getChangeToStatusDate() {
		return changeToStatusDate;
	}
	/**
	 * @param changeToStatusDate The changeToStatusDate to set.
	 */
	public void setChangeToStatusDate(Date aChangeToStatusDate) {
		this.changeToStatusDate = aChangeToStatusDate;
		if(changeToStatusDate==null){
			changeToStatusDateAsStr= "";
		}
		try{
			changeToStatusDateAsStr=DateUtil.dateToString(changeToStatusDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			changeToStatusDateAsStr="";
		}
	}
	/**
	 * @return Returns the changeToStatusDateAsStr.
	 */
	public String getChangeToStatusDateAsStr() {
		return changeToStatusDateAsStr;
	}
	/**
	 * @param changeToStatusDateAsStr The changeToStatusDateAsStr to set.
	 */
	public void setChangeToStatusDateAsStr(String aChangeToStatusDateAsStr) {
		this.changeToStatusDateAsStr = "";
		if(aChangeToStatusDateAsStr==null || aChangeToStatusDateAsStr.equals(""))
			changeToStatusDate=null;
		try{
			this.changeToStatusDateAsStr = aChangeToStatusDateAsStr;
			changeToStatusDate=DateUtil.stringToDate(changeToStatusDateAsStr, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			changeToStatusDate=null;
		}
	}
	/**
	 * @return Returns the changeToStatusDesc.
	 */
	public String getChangeToStatusDesc() {
		return changeToStatusDesc;
	}
	/**
	 * @param changeToStatusDesc The changeToStatusDesc to set.
	 */
	public void setChangeToStatusDesc(String changeToStatusDesc) {
		this.changeToStatusDesc = changeToStatusDesc;
	}
	/**
	 * @return Returns the changeToStatusId.
	 */
	public String getChangeToStatusId() {
		return changeToStatusId;
	}
	/**
	 * @param changeToStatusId The changeToStatusId to set.
	 */
	public void setChangeToStatusId(String changeToStatusId) {
		this.changeToStatusId = changeToStatusId;
		this.changeToStatusDesc="";
		changeToStatusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_STATUS,changeToStatusId);
	}
	/**
	 * @return Returns the changeToStatusReason.
	 */
	public String getChangeToStatusReason() {
		return changeToStatusReason;
	}
	/**
	 * @param changeToStatusReason The changeToStatusReason to set.
	 */
	public void setChangeToStatusReason(String changeToStatusReason) {
		this.changeToStatusReason = changeToStatusReason;
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	/**
	 * @return Returns the programHeirarchyList.
	 */
	public Collection getProgramHeirarchyList() {
	
		return programHeirarchyList;
	}
	/**
	 * @param programHeirarchyList The programHeirarchyList to set.
	 */
	public void setProgramHeirarchyList(Collection programHeirarchyList) {
		this.programHeirarchyList = programHeirarchyList;
	}

	public String getSelectedDivision() {
		return selectedDivision;
	}

	public void setSelectedDivision(String selectedDivision) {
		this.selectedDivision = selectedDivision;
	}

	public List getAvailableDivisions() {
		return availableDivisions;
	}

	public void setAvailableDivisions(List availableDivisions) {
		this.availableDivisions = availableDivisions;
	}

	public String getSelectedProgramUnit() {
		return selectedProgramUnit;
	}

	public void setSelectedProgramUnit(String selectedProgramUnit) {
		this.selectedProgramUnit = selectedProgramUnit;
	}

	public List getDivisionProgramUnits() {
		return divisionProgramUnits;
	}

	public void setDivisionProgramUnits(List divisionProgramUnits) {
		this.divisionProgramUnits = divisionProgramUnits;
	}

	public String getSelectedIncarcerationCondition() {
		return selectedIncarcerationCondition;
	}

	public void setSelectedIncarcerationCondition(
			String selectedIncarcerationCondition) {
		this.selectedIncarcerationCondition = selectedIncarcerationCondition;
	}

	public List getIncarcerationConditions() {
		return incarcerationConditions;
	}

	public void setIncarcerationConditions(List incarcerationConditions) {
		this.incarcerationConditions = incarcerationConditions;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSelectedDivisionName() {
		return selectedDivisionName;
	}

	public void setSelectedDivisionName(String selectedDivisionName) {
		this.selectedDivisionName = selectedDivisionName;
	}

	public String getSelectedProgramUnitName() {
		return selectedProgramUnitName;
	}

	public void setSelectedProgramUnitName(String selectedProgramUnitName) {
		this.selectedProgramUnitName = selectedProgramUnitName;
	}

	public String getSelectedIncarcerationConditionName() {
		return selectedIncarcerationConditionName;
	}

	public void setSelectedIncarcerationConditionName(
			String selectedIncarcerationConditionName) {
		this.selectedIncarcerationConditionName = selectedIncarcerationConditionName;
	}

	/**
	 * @return the origProgramName
	 */
	public String getOrigProgramName() {
		return origProgramName;
	}

	/**
	 * @param origProgramName the origProgramName to set
	 */
	public void setOrigProgramName(String origProgramName) {
		this.origProgramName = origProgramName;
	}
	
}// END CLASS
