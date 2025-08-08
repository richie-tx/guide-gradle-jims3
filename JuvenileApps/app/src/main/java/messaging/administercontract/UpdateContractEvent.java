//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\UpdateContractEvent.java

package messaging.administercontract;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateContractEvent extends CompositeRequest 
{
   private String number;
   private String contractName;
   private Date startDate;
   private Date endDate;
   private String contractTypeId;
   private String programFundingDescription;
   private String tracerRangeFrom;
   private String tracerRangeTo;
   private String contractId;
   private String action;
   private String totalValue;
   private String glAccountKey;
   private String renewalNum;
   private String  agencyId;
   private String serviceId;
   private String serviceProviderValue;
   private String contractServiceId;
   private boolean isServiceProviderFlow=false;
   
   /**
    * @roseuid 451C4FB1024D
    */
   public UpdateContractEvent() 
   {
    
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
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the number.
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number The number to set.
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return Returns the programFundingDescription.
	 */
	public String getProgramFundingDescription() {
		return programFundingDescription;
	}
	/**
	 * @param programFundingDescription The programFundingDescription to set.
	 */
	public void setProgramFundingDescription(String programFundingDescription) {
		this.programFundingDescription = programFundingDescription;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the tracerRangeFrom.
	 */
	public String getTracerRangeFrom() {
		return tracerRangeFrom;
	}
	/**
	 * @param tracerRangeFrom The tracerRangeFrom to set.
	 */
	public void setTracerRangeFrom(String tracerRangeFrom) {
		this.tracerRangeFrom = tracerRangeFrom;
	}
	/**
	 * @return Returns the tracerRangeTo.
	 */
	public String getTracerRangeTo() {
		return tracerRangeTo;
	}
	/**
	 * @param tracerRangeTo The tracerRangeTo to set.
	 */
	public void setTracerRangeTo(String tracerRangeTo) {
		this.tracerRangeTo = tracerRangeTo;
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
	 * @return Returns the isServiceProviderFlow.
	 */
	public boolean isServiceProviderFlow() {
		return isServiceProviderFlow;
	}
	/**
	 * @param isServiceProviderFlow The isServiceProviderFlow to set.
	 */
	public void setServiceProviderFlow(boolean isServiceProviderFlow) {
		this.isServiceProviderFlow = isServiceProviderFlow;
	}
}
