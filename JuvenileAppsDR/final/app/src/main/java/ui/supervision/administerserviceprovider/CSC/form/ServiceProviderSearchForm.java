/*
 * Created on Nov 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.form;

import java.util.ArrayList;
import java.util.Collection;

import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderSearchForm extends ActionForm {

	//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";

	// General
	private String searchById;
	private String searchByDesc;
	private String searchByFieldsString;

	//Program related search options
	private Collection programSearchResults;
	private String programName;
	private String programGroupId;
	private String programGroupDesc;
	private String programTypeId;
	private String programTypeDesc;
	private String contractProgramId;
	private String contractProgramDesc;
	private String programStatusId;
	private String programStatusDesc;

	// Service provider search options
	private Collection serviceProviderSearchResults;
	private String serviceProviderName;
	private String serviceProviderStatusId;
	private String serviceProviderStatusDesc;
	private String serviceProviderInHouseId;
	private String serviceProviderInHouseDesc;
	private String serviceProviderContractProgId;
	private String serviceProviderContractProgDesc;

	// General Collection
	private Collection programGroups;
	private Collection programTypes;
	private Collection contractPrograms;
	private Collection spSearchTypes;
	private Collection programHeirarchyList;

	
	public void clearConsolidatedSearch(){
		
		
	}
	
	public void clearSPSearch(){
		serviceProviderSearchResults=new ArrayList();
		serviceProviderName="";
		serviceProviderStatusId="";
		serviceProviderStatusDesc="";
		serviceProviderInHouseId="";
		serviceProviderInHouseDesc="";
		serviceProviderContractProgId="";
		serviceProviderContractProgDesc="";
	}
	
	public void clearProgramSearch(){
		programSearchResults=new ArrayList();
		programName="";
		programGroupId="";
		programGroupDesc="";
		programTypeId="";
		programTypeDesc="";
		contractProgramId="";
		contractProgramDesc="";
		programStatusId="";
		programStatusDesc="";
	}
	
	public void clear(){
		searchById="";
		searchByDesc="";

		//Program related search options
		programSearchResults=new ArrayList();
		programName="";
		programGroupId="";
		programGroupDesc="";
		programTypeId="";
		programTypeDesc="";
		contractProgramId="";
		contractProgramDesc="";
		programStatusId="";
		programStatusDesc="";

		// Service provider search options
		serviceProviderSearchResults=new ArrayList();
		serviceProviderName="";
		serviceProviderStatusId="";
		serviceProviderStatusDesc="";
		serviceProviderInHouseId="";
		serviceProviderInHouseDesc="";
		serviceProviderContractProgId="";
		serviceProviderContractProgDesc="";
	}
	
	
	
	public void clearSearchResults(){
		serviceProviderSearchResults=new ArrayList();
		programSearchResults=new ArrayList();
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
	 * @return Returns the contractProgramDesc.
	 */
	public String getContractProgramDesc() {
		return contractProgramDesc;
	}
	/**
	 * @param contractProgramDesc The contractProgramDesc to set.
	 */
	public void setContractProgramDesc(String contractProgramDesc) {
		this.contractProgramDesc = contractProgramDesc;
	}
	/**
	 * @return Returns the contractProgramId.
	 */
	public String getContractProgramId() {
		return contractProgramId;
	}
	public boolean isContractProgram(){
		boolean bool=false;
		try{
			if(contractProgramId!=null && !contractProgramId.equals(""))
				bool=Boolean.valueOf(getContractProgramId()).booleanValue();
			return bool;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * @param contractProgramId The contractProgramId to set.
	 */
	public void setContractProgramId(String contractProgramId) {
		this.contractProgramId = contractProgramId;
	}
	/**
	 * @return Returns the contractPrograms.
	 */
	public Collection getContractPrograms() {
		return contractPrograms;
	}
	/**
	 * @param contractPrograms The contractPrograms to set.
	 */
	public void setContractPrograms(Collection contractPrograms) {
		this.contractPrograms = contractPrograms;
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
	}
	/**
	 * @return Returns the programGroups.
	 */
	public Collection getProgramGroups() {
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_GROUP);
	}
	/**
	 * @param programGroups The programGroups to set.
	 */
	public void setProgramGroups(Collection programGroups) {
		this.programGroups = programGroups;
	}
	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}


	public Collection getServiceProviderSearchResults() {
			return serviceProviderSearchResults;
	}
	/**
	 * @param programSearchResults The programSearchResults to set.
	 */
	public void setServiceProviderSearchResults(Collection serviceProviderSearchResults) {
		this.serviceProviderSearchResults = serviceProviderSearchResults;
	}
	/**
	 * @return Returns the programSearchResults.
	 */
	public Collection getProgramSearchResults() {
		return programSearchResults;
	}
	/**
	 * @param programSearchResults The programSearchResults to set.
	 */
	public void setProgramSearchResults(Collection programSearchResults) {
		this.programSearchResults = programSearchResults;
	}
	/**
	 * @return Returns the programStatusDesc.
	 */
	public String getProgramStatusDesc() {
		return programStatusDesc;
	}
	/**
	 * @param programStatusDesc The programStatusDesc to set.
	 */
	public void setProgramStatusDesc(String programStatusDesc) {
		this.programStatusDesc = programStatusDesc;
	}
	/**
	 * @return Returns the programStatusId.
	 */
	public String getProgramStatusId() {
		return programStatusId;
	}
	/**
	 * @param programStatusId The programStatusId to set.
	 */
	public void setProgramStatusId(String programStatusId) {
		this.programStatusId = programStatusId;
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
	 * @return Returns the searchByDesc.
	 */
	public String getSearchByDesc() {
		return searchByDesc;
	}
	/**
	 * @param searchByDesc The searchByDesc to set.
	 */
	public void setSearchByDesc(String searchByDesc) {
		this.searchByDesc = searchByDesc;
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
	 * @return Returns the serviceProviderContractProgDesc.
	 */
	public String getServiceProviderContractProgDesc() {
		return serviceProviderContractProgDesc;
	}
	/**
	 * @param serviceProviderContractProgDesc The serviceProviderContractProgDesc to set.
	 */
	public void setServiceProviderContractProgDesc(String serviceProviderContractProgDesc) {
		this.serviceProviderContractProgDesc = serviceProviderContractProgDesc;
	}
	/**
	 * @return Returns the serviceProviderContractProgId.
	 */
	public String getServiceProviderContractProgId() {
		return serviceProviderContractProgId;
	}

	public boolean isServiceProviderContractProgram(){
		
		boolean bool=false;
		try{
			if(getServiceProviderContractProgId()!=null && !getServiceProviderContractProgId().equals(""))
				bool=Boolean.valueOf(getServiceProviderContractProgId()).booleanValue();
			return bool;
		}
		catch(Exception e){
			return false;
		}
	}

	/**
	 * @param serviceProviderContractProgId The serviceProviderContractProgId to set.
	 */
	public void setServiceProviderContractProgId(String serviceProviderContractProgId) {
		this.serviceProviderContractProgId = serviceProviderContractProgId;
	}
	/**
	 * @return Returns the serviceProviderInHouseDesc.
	 */
	public String getServiceProviderInHouseDesc() {
		return serviceProviderInHouseDesc;
	}
	/**
	 * @param serviceProviderInHouseDesc The serviceProviderInHouseDesc to set.
	 */
	public void setServiceProviderInHouseDesc(String serviceProviderInHouseDesc) {
		this.serviceProviderInHouseDesc = serviceProviderInHouseDesc;
	}
	/**
	 * @return Returns the serviceProviderInHouseId.
	 */
	public String getServiceProviderInHouseId() {
		return serviceProviderInHouseId;
	}
	
	public boolean isServiceProviderInHouse(){
		
		boolean bool=false;
		try{
			if(getServiceProviderInHouseId()!=null && !getServiceProviderInHouseId().equals(""))
				bool=Boolean.valueOf(getServiceProviderInHouseId()).booleanValue();
			return bool;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * @param serviceProviderInHouseId The serviceProviderInHouseId to set.
	 */
	public void setServiceProviderInHouseId(String serviceProviderInHouseId) {
		this.serviceProviderInHouseId = serviceProviderInHouseId;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the serviceProviderStatusDesc.
	 */
	public String getServiceProviderStatusDesc() {
		return serviceProviderStatusDesc;
	}
	/**
	 * @param serviceProviderStatusDesc The serviceProviderStatusDesc to set.
	 */
	public void setServiceProviderStatusDesc(String serviceProviderStatusDesc) {
		this.serviceProviderStatusDesc = serviceProviderStatusDesc;
	}
	/**
	 * @return Returns the serviceProviderStatusId.
	 */
	public String getServiceProviderStatusId() {
		return serviceProviderStatusId;
	}
	/**
	 * @param serviceProviderStatusId The serviceProviderStatusId to set.
	 */
	public void setServiceProviderStatusId(String serviceProviderStatusId) {
		this.serviceProviderStatusId = serviceProviderStatusId;
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
	 * @return Returns the spSearchTypes.
	 */
	public Collection getSpSearchTypes() {
		return spSearchTypes;
	}
	/**
	 * @param spSearchTypes The spSearchTypes to set.
	 */
	public void setSpSearchTypes(Collection spSearchTypes) {
		this.spSearchTypes = spSearchTypes;
	}
	/**
	 * @return Returns the programHeirarchyList.
	 */
	public Collection getProgramHeirarchyList() {
		if(this.programHeirarchyList==null || programHeirarchyList.size()<1){
			this.setProgramHeirarchyList(ComplexCodeTableHelper.getCSCServiceProviderProgramHeirarchy());
		}
		return programHeirarchyList;
	}
	/**
	 * @param programHeirarchyList The programHeirarchyList to set.
	 */
	public void setProgramHeirarchyList(Collection programHeirarchyList) {
		this.programHeirarchyList = programHeirarchyList;
	}
	/**
	 * @param returns the searchByFieldsString.
	 */
	public String getSearchByFieldsString() {
		return searchByFieldsString;
	}
	/**
	 * @param searchByFieldsString The searchByFieldsString to set.
	 */
	public void setSearchByFieldsString(String searchByFieldsString) {
		this.searchByFieldsString = searchByFieldsString;
	}
	
}// END CLASS
