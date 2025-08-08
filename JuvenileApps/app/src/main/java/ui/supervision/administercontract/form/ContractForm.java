/*
 * Created on Aug 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administercontract.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import messaging.administerserviceprovider.reply.ServiceProviderServiceResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.supervision.administerserviceprovider.form.ServiceProviderForm.Program;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractForm extends ActionForm {
	
	private String action;
	private String agencyId;
	private String availableContractValue;
	boolean contractExpired;
	private String contractId;
	private String contractName;
	private String contractNum;
	private String contractServiceId;
	private String contractType;
	private String contractTypeId;
	private String endDate;
	private String glAccountKey;
	private String glAccountKeyId;
	private String originalTotalVale;
	private String originalServiceProviderValue;
	private String programFundingDesc;
	private String renewalNum;	
	private String serviceProviderValue;	
	private String showDropAssignmentMessage;
	private String showUpdateConfirmMessage;
	private String showNoMessage;
	private String startDate;
	private String startDateFrom;
	private String startDateTo;
	private String totalValue;
	private String tracerNumberRangeFrom;
	private String tracerNumberRangeTo;
	private String departmentId;

	boolean showExpired;
	private String showServiceProviderInfo;

	//Unique search fields
	private String searchContractName;
	private String searchContractNum;
	private String searchProgramFundingDesc;

	// Collections
	private Collection contractTypes;
	private Collection services;
	private Collection currentContracts;
	private Collection currentSPServices;	
	private Collection availableContracts;
	private Collection glAccountKeys;
	private Collection serviceProviderServices;
	
	private String [] selectedContractIds;
	
	// service provider info
	private String inHouse;
	private String programName;
	private String serviceProviderName;
	private String serviceProviderStartDate;
	private String targetIntervention;
	
	// service info
	private String serviceCode;
	private String serviceCost;
	private String serviceId;
	private String serviceLocation;
	private String serviceMaxEnrollment;
	private String serviceName;
	private String serviceType;
	

	/**
	 * Constructor for the ContractForm
	 */
	public ContractForm()
	{
		super();
	}
	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
	}
	
	/**
	 * @param aRequest
	 */
	public void clear()
	{
		this.action = null;
		this.availableContractValue = null;
		this.contractId = null;
		this.contractName = null;
		this.contractNum = null;
		this.contractServiceId = null;
		this.contractType = null;
		this.contractTypeId = null;
		this.endDate = null;
		this.glAccountKey = null;
		this.glAccountKeyId = null;
		this.originalServiceProviderValue = null;
		this.originalTotalVale = null;
		this.programFundingDesc = null;
		this.renewalNum = null;	
		this.showNoMessage = null;
		this.showDropAssignmentMessage = null;
		this.showUpdateConfirmMessage = null;
		this.startDate = null;		
		this.startDateFrom = null;
		this.startDateTo = null;
		this.totalValue = null;
		this.tracerNumberRangeFrom = null;
		this.tracerNumberRangeTo = null;
		
		this.searchContractName = null;
		this.searchContractNum = null;
		this.searchProgramFundingDesc = null;
		
		this.selectedContractIds = null;
		this.serviceProviderValue = null;
		this.serviceProviderServices = null;

		
		// service header info
		if (this.showServiceProviderInfo == null ||!this.showServiceProviderInfo.equalsIgnoreCase("Y")){
			this.serviceProviderName = null;
			this.serviceProviderStartDate = null;
			this.programName = null;
			this.inHouse = null;
			this.targetIntervention = null;	
			this.serviceCode = null;
			this.serviceCost = null;
			this.serviceId = null;
			this.serviceLocation = null;
			this.serviceMaxEnrollment = null;
			this.serviceName = null;
			this.serviceType = null;
		}
		this.agencyId = null;

		this.currentContracts = null;
		this.availableContracts = null;
		
		showExpired = false;
		contractExpired = false;
			
		Collection emptyColl = new ArrayList();
		this.setServices(emptyColl);
		this.setCurrentSPServices(emptyColl);
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
	 * @return Returns the availableContractValue.
	 */
	public String getAvailableContractValue() {
		return availableContractValue;
	}
	/**
	 * @param availableContractValue The availableContractValue to set.
	 */
	public void setAvailableContractValue(String availableContractValue) {
		this.availableContractValue = availableContractValue;
	}
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractName.
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * @param contractName The contractName to set.
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return Returns the contractTypeId.
	 */
	public String getContractTypeId() {
		return contractTypeId;
	}
	/**
	 * @param contractTypeId The contractTypeId to set.
	 */
	public void setContractTypeId(String contractTypeId) {
		this.contractTypeId = contractTypeId;
	}
	/**
	 * @return Returns the contractTypes.
	 */
	public Collection getContractTypes() {
		return contractTypes;
	}
	/**
	 * @param contractTypes The contractTypes to set.
	 */
	public void setContractTypes(Collection contractTypes) {
		this.contractTypes = contractTypes;
	}
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the glAccountKey.
	 */
	public String getGlAccountKey() {
		return glAccountKey;
	}
	/**
	 * @param glAccountKey The glAccountKey to set.
	 */
	public void setGlAccountKey(String glAccountKey) {
		this.glAccountKey = glAccountKey;
	}
	/**
	 * @return Returns the glAccountKeys.
	 */
	public Collection getGlAccountKeys() {
		return glAccountKeys;
	}
	/**
	 * @param glAccountKeys The glAccountKeys to set.
	 */
	public void setGlAccountKeys(Collection glAccountKeys) {
		this.glAccountKeys = glAccountKeys;
	}
	/**
	 * @return Returns the glAccountKeyId.
	 */
	public String getGlAccountKeyId() {
		return glAccountKeyId;
	}
	/**
	 * @param glAccountKeyId The glAccountKeyId to set.
	 */
	public void setGlAccountKeyId(String glAccountKeyId) {
		this.glAccountKeyId = glAccountKeyId;
	}	
	/**
	 * @return Returns the contractNum.
	 */
	public String getContractNum() {
		return contractNum;
	}
	/**
	 * @param contractNumber The contractNumber to set.
	 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	/**
	 * @return Returns the programFundingDesc.
	 */
	public String getProgramFundingDesc() {
		return programFundingDesc;
	}
	/**
	 * @param programFundingDesc The programFundingDesc to set.
	 */
	public void setProgramFundingDesc(String programFundingDesc) {
		this.programFundingDesc = programFundingDesc;
	}
	/**
	 * @return Returns the renewalNum.
	 */
	public String getRenewalNum() {
		return renewalNum;
	}
	/**
	 * @param renewalNum The renewalNum to set.
	 */
	public void setRenewalNum(String renewalNum) {
		this.renewalNum = renewalNum;
	}
	
	/**
	 * @return Returns the showExpired.
	 */
	public boolean isShowExpired() {
		return showExpired;
	}
	/**
	 * @param showExpired The showExpired to set.
	 */
	public void setShowExpired(boolean showExpired) {
		this.showExpired = showExpired;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the startDateFrom.
	 */
	public String getStartDateFrom() {
		return startDateFrom;
	}
	/**
	 * @param startDateFrom The startDateFrom to set.
	 */
	public void setStartDateFrom(String startDateFrom) {
		this.startDateFrom = startDateFrom;
	}
	/**
	 * @return Returns the startDateTo.
	 */
	public String getStartDateTo() {
		return startDateTo;
	}
	/**
	 * @param startdateTo The startdateTo to set.
	 */
	public void setStartDateTo(String startDateTo) {
		this.startDateTo = startDateTo;
	}
	/**
	 * @return Returns the totalValue.
	 */
	public String getTotalValue() {
		return totalValue;
	}
	/**
	 * @param totalValue The totalValue to set.
	 */
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	/**
	 * @return Returns the tracerNumberRangeFrom.
	 */
	public String getTracerNumberRangeFrom() {
		return tracerNumberRangeFrom;
	}
	/**
	 * @param tracerNumberRangeFrom The tracerNumberRangeFrom to set.
	 */
	public void setTracerNumberRangeFrom(String tracerNumberRangeFrom) {
		this.tracerNumberRangeFrom = tracerNumberRangeFrom;
	}
	/**
	 * @return Returns the tracerNumberRangeTo.
	 */
	public String getTracerNumberRangeTo() {
		return tracerNumberRangeTo;
	}
	/**
	 * @param tracerNumberRangeTo The tracerNumberRangeTo to set.
	 */
	public void setTracerNumberRangeTo(String tracerNumberRangeTo) {
		this.tracerNumberRangeTo = tracerNumberRangeTo;
	}
	/**
	 * @return Returns the services.
	 */
	public Collection getServices() {
		return services;
	}
	/**
	 * @param services The services to set.
	 */
	public void setServices(Collection services) {
		this.services = services;
	}
	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return Returns the serviceProviderValue.
	 */
	public String getServiceProviderValue() {
		return serviceProviderValue;
	}
	/**
	 * @param serviceProviderValue The serviceProviderValue to set.
	 */
	public void setServiceProviderValue(String serviceProviderValue) {
		this.serviceProviderValue = serviceProviderValue;
	}
	/**
	 * @return Returns the inHouse.
	 */
	public String getInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(String inHouse) {
		this.inHouse = inHouse;
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
	/**
	 * @return Returns the serviceCode.
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode The serviceCode to set.
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return Returns the serviceCost.
	 */
	public String getServiceCost() {
		return serviceCost;
	}
	/**
	 * @param serviceCost The serviceCost to set.
	 */
	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}
	/**
	 * @return Returns the serviceLocation.
	 */
	public String getServiceLocation() {
		return serviceLocation;
	}
	/**
	 * @param serviceLocation The serviceLocation to set.
	 */
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	/**
	 * @return Returns the serviceMaxEnrollment.
	 */
	public String getServiceMaxEnrollment() {
		return serviceMaxEnrollment;
	}
	/**
	 * @param serviceMaxEnrollment The serviceMaxEnrollment to set.
	 */
	public void setServiceMaxEnrollment(String serviceMaxEnrollment) {
		this.serviceMaxEnrollment = serviceMaxEnrollment;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
	 * @return Returns the serviceProviderStartDate.
	 */
	public String getServiceProviderStartDate() {
		return serviceProviderStartDate;
	}
	/**
	 * @param serviceProviderStartDate The serviceProviderStartDate to set.
	 */
	public void setServiceProviderStartDate(String serviceProviderStartDate) {
		this.serviceProviderStartDate = serviceProviderStartDate;
	}
	/**
	 * @return Returns the serviceType.
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType The serviceType to set.
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return Returns the targetIntervention.
	 */
	public String getTargetIntervention() {
		return targetIntervention;
	}
	/**
	 * @param targetIntervention The targetIntervention to set.
	 */
	public void setTargetIntervention(String targetIntervention) {
		this.targetIntervention = targetIntervention;
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
	 * @return Returns the availableContracts.
	 */
	public Collection getAvailableContracts() {
//		if(this.availableContracts != null && !this.availableContracts.isEmpty()){
//		Collections.sort((ArrayList)availableContracts);
//	}else{
	if(this.availableContracts == null || this.availableContracts.isEmpty()){		
		availableContracts = MessageUtil.processEmptyCollection(availableContracts);	
	}
	return this.availableContracts;
	}
	/**
	 * @param availableContracts The availableContracts to set.
	 */
	public void setAvailableContracts(Collection availableContracts) {
		this.availableContracts = availableContracts;
	}
	/**
	 * @return Returns the currentContracts.
	 */
	public Collection getCurrentContracts() {
		if(this.currentContracts != null && !this.currentContracts.isEmpty()){
			Collections.sort((ArrayList)currentContracts);
		}else{
			currentContracts = MessageUtil.processEmptyCollection(currentContracts);	
		}
		return this.currentContracts;
	}
	/**
	 * @return Returns the currentContractsList.
	 */
	public Object[] getCurrentContractsList() {
		if(currentContracts != null ){
			return currentContracts.toArray();
		}else
		return null;
	}
	
	/**
	 * @return Returns the currentContractsList.
	 */
	public Object[] getServiceProviderServices() {
		if(serviceProviderServices != null ){
			return serviceProviderServices.toArray();
		}else
		return null;
	}
	/**
	 * @param currentContracts The currentContracts to set.
	 */
	public void setCurrentContracts(Collection currentContracts) {
		this.currentContracts = currentContracts;
	}
	/**
	 * @return Returns the selectedContractIds.
	 */
	public String[] getSelectedContractIds() {
		return selectedContractIds;
	}
	/**
	 * @param selectedContractIds The selectedContractIds to set.
	 */
	public void setSelectedContractIds(String[] selectedContractIds) {
		this.selectedContractIds = selectedContractIds;
	}
	/**
	 * @return Returns the searchContractName.
	 */
	public String getSearchContractName() {
		return searchContractName;
	}
	/**
	 * @param searchContractName The searchContractName to set.
	 */
	public void setSearchContractName(String searchContractName) {
		this.searchContractName = searchContractName;
	}
	/**
	 * @return Returns the searchContractNum.
	 */
	public String getSearchContractNum() {
		return searchContractNum;
	}
	/**
	 * @param searchContractNum The searchContractNum to set.
	 */
	public void setSearchContractNum(String searchContractNum) {
		this.searchContractNum = searchContractNum;
	}
	/**
	 * @return Returns the searchProgramFundingDesc.
	 */
	public String getSearchProgramFundingDesc() {
		return searchProgramFundingDesc;
	}
	/**
	 * @param searchProgramFundingDesc The searchProgramFundingDesc to set.
	 */
	public void setSearchProgramFundingDesc(String searchProgramFundingDesc) {
		this.searchProgramFundingDesc = searchProgramFundingDesc;
	}

	/**
	 * @return Returns the showServiceProviderInfo.
	 */
	public String getShowServiceProviderInfo() {
		return showServiceProviderInfo;
	}
	/**
	 * @param showServiceProviderInfo The showServiceProviderInfo to set.
	 */
	public void setShowServiceProviderInfo(String showServiceProviderInfo) {
		this.showServiceProviderInfo = showServiceProviderInfo;
	}
	/**
	 * @return Returns the contractServiceId.
	 */
	public String getContractServiceId() {
		return contractServiceId;
	}
	/**
	 * @param contractServiceId The contractServiceId to set.
	 */
	public void setContractServiceId(String contractServiceId) {
		this.contractServiceId = contractServiceId;
	}
	/**
	 * @return Returns the departmentId.
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId The departmentId to set.
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return Returns the contractExpired.
	 */
	public boolean isContractExpired() {
		return contractExpired;
	}
	/**
	 * @param contractExpired The contractExpired to set.
	 */
	public void setContractExpired(boolean contractExpired) {
		this.contractExpired = contractExpired;
	}
	
	/**
	 * @return Returns the originalServiceProviderValue.
	 */
	public String getOriginalServiceProviderValue() {
		return originalServiceProviderValue;
	}
	/**
	 * @param originalServiceProviderValue The originalServiceProviderValue to set.
	 */
	public void setOriginalServiceProviderValue(String originalServiceProviderValue) {
		this.originalServiceProviderValue = originalServiceProviderValue;
	}
	/**
	 * @return Returns the originalTotalVale.
	 */
	public String getOriginalTotalVale() {
		return originalTotalVale;
	}
	/**
	 * @param originalTotalVale The originalTotalVale to set.
	 */
	public void setOriginalTotalVale(String originalTotalVale) {
		this.originalTotalVale = originalTotalVale;
	}
	/**
	 * @return Returns the showDropAssignmentMessage.
	 */
	public String getShowDropAssignmentMessage() {
		return showDropAssignmentMessage;
	}
	/**
	 * @param showDropAssignmentMessage The showDropAssignmentMessage to set.
	 */
	public void setShowDropAssignmentMessage(String showDropAssignmentMessage) {
		this.showDropAssignmentMessage = showDropAssignmentMessage;
	}
	/**
	 * @return Returns the showNoMessage.
	 */
	public String getShowNoMessage() {
		return showNoMessage;
	}
	/**
	 * @param showNoMessage The showNoMessage to set.
	 */
	public void setShowNoMessage(String showNoMessage) {
		this.showNoMessage = showNoMessage;
	}
	/**
	 * @return Returns the showUpdateConfirmMessage.
	 */
	public String getShowUpdateConfirmMessage() {
		return showUpdateConfirmMessage;
	}
	/**
	 * @param showUpdateConfirmMessage The showUpdateConfirmMessage to set.
	 */
	public void setShowUpdateConfirmMessage(String showUpdateConfirmMessage) {
		this.showUpdateConfirmMessage = showUpdateConfirmMessage;
	}
	/**
	 * @return Returns the currentSPServices.
	 */
	public Collection getCurrentSPServices() {
		return currentSPServices;
	}

	/**
	 * @param currentSPServices The currentSPServices to set.
	 */
	public void setCurrentSPServices(Collection currentSPServices) {
		this.currentSPServices = currentSPServices;
	}
	/**
	 * @return Returns the serviceProviderServices.
	 */
	//public Collection getServiceProviderServices() {
		//return serviceProviderServices;
	//}
	/**
	 * @param serviceProviderServices The serviceProviderServices to set.
	 */
	public void setServiceProviderServices(Collection serviceProviderServices) {
		this.serviceProviderServices = serviceProviderServices;
	}
}
