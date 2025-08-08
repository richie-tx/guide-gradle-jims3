//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\GetContractsEvent.java

package messaging.administercontract;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetContractsEvent extends RequestEvent 
{
   private String contractName;
   private String contractTypeId;
   private String fundingProgranDescription;
   private Date fromDate;
   private Date toDate;
   private String number;
   private String agencyId;
   private boolean isExpired;
   private String contractId;

   /**
    * @roseuid 451C4FB001FF
    */
   public GetContractsEvent() 
   {
    
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
	 * @return Returns the fromDate.
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return Returns the fundingProgranDescription.
	 */
	public String getFundingProgranDescription() {
		return fundingProgranDescription;
	}
	/**
	 * @param fundingProgranDescription The fundingProgranDescription to set.
	 */
	public void setFundingProgranDescription(String fundingProgranDescription) {
		this.fundingProgranDescription = fundingProgranDescription;
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
	 * @return Returns the toDate.
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
}
