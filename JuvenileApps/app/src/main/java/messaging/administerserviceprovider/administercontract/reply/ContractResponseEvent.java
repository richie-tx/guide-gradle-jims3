/*
 * Create on Oct 05, 2006
 */

package messaging.administerserviceprovider.administercontract.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractResponseEvent extends ResponseEvent implements Comparable
{	
   private String contractName;
   private String number;
   private String contractTypeId;
   private String contractType;
   private String programFundingDescription;
   private Date startDate;
   private Date endDate;
   private String contractId;
   private String totalValue = "0.00";
   private String availableContractValue = "0.00";
   private String glAccountKeyId;
   private String glAccountKeyDesc;
   private String tracerNumberFrom;
   private String tracerNumberTo;
   private String serviceProviderValue = "0.00";
   private String renewalNum;
   private String contractServiceId;
   private String agencyId;
   private boolean isExpired;

   

   /**
	 * @return Returns the glAccountKeyDesc.
	 */
	public String getGlAccountKeyDesc() {
		return glAccountKeyDesc;
	}
	/**
	 * @param glAccountKeyDesc The glAccountKeyDesc to set.
	 */
	public void setGlAccountKeyDesc(String glAccountKeyDesc) {
		this.glAccountKeyDesc = glAccountKeyDesc;
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		ContractResponseEvent c = (ContractResponseEvent)o;
		if (c.getContractName() == null){
			return -1;
		}		
		if (this.getContractName() == null){
			return 1;
		}
		return this.getContractName().compareToIgnoreCase(c.getContractName());
//		return 0;
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
	 * @return Returns the tracerNumberFrom.
	 */
	public String getTracerNumberFrom() {
		return tracerNumberFrom;
	}
	/**
	 * @param tracerNumberFrom The tracerNumberFrom to set.
	 */
	public void setTracerNumberFrom(String tracerNumberFrom) {
		this.tracerNumberFrom = tracerNumberFrom;
	}
	/**
	 * @return Returns the tracerNumberTo.
	 */
	public String getTracerNumberTo() {
		return tracerNumberTo;
	}
	/**
	 * @param tracerNumberTo The tracerNumberTo to set.
	 */
	public void setTracerNumberTo(String tracerNumberTo) {
		this.tracerNumberTo = tracerNumberTo;
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
	 * @return Returns the isExpired.
	 */
	public boolean isExpired() {
		return isExpired;
	}
	/**
	 * @param isExpired The isExpired to set.
	 */
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
}