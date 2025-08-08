/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC;

import java.util.Date;

import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderLightBean implements Comparable{
	
	
	private String serviceProviderId="";
	private String serviceProviderName="";
	private String serviceProviderStatusId="";
	private String serviceProviderStatusDesc="";
	private String serviceProviderInHouseId="";
	private String serviceProviderInHouseDesc="";
	private String serviceProviderInContractProgramId="";
	private String serviceProviderInContractProgramDesc="";
	private String programIdentifier="";
	private String programName="";
	private String referralType="";
	private Date statusChangeDate=null;
	private String statusChangeDateAsStr="";
	private String statusChangeTimeAsStr="";
	private String statusChangeDateTimeAsStr="";
	private String sortId="";

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)
			return -1;
		ServiceProviderLightBean evt = (ServiceProviderLightBean) obj;
		int myVal=sortId.compareTo(evt.getSortId());	
		if(myVal>0){
			myVal= 1;
		}
		else if (myVal<0){
			myVal= -1;
		}
		else{
			myVal=0;
		}
		return myVal;
//		return 0;
	}
	/**
	 * @return Returns the programId.
	 */
	public String getProgramIdentifier() {
		return programIdentifier;
	}
	/**
	 * @param programId The programId to set.
	 */
	public void setProgramIdentifier(String programId) {
		this.programIdentifier = programId;
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
	 * @return Returns the referralType.
	 */
	public String getReferralType() {
		return referralType;
	}
	/**
	 * @param referralType The referralType to set.
	 */
	public void setReferralType(String referralType) {
		this.referralType = referralType;
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
	 * @return Returns the serviceProviderInContractProgramDesc.
	 */
	public String getServiceProviderInContractProgramDesc() {
		return serviceProviderInContractProgramDesc;
	}
	/**
	 * @param serviceProviderInContractProgramDesc The serviceProviderInContractProgramDesc to set.
	 */
	public void setServiceProviderInContractProgramDesc(String serviceProviderInContractProgramDesc) {
		this.serviceProviderInContractProgramDesc = serviceProviderInContractProgramDesc;
	}
	/**
	 * @return Returns the serviceProviderInContractProgramId.
	 */
	public String getServiceProviderInContractProgramId() {
		return serviceProviderInContractProgramId;
	}
	/**
	 * @param serviceProviderInContractProgramId The serviceProviderInContractProgramId to set.
	 */
	public void setServiceProviderInContractProgramId(String serviceProviderInContractProgramId) {
		this.serviceProviderInContractProgramId = serviceProviderInContractProgramId;
		serviceProviderInContractProgramDesc="";
		if(serviceProviderInContractProgramId!=null && (serviceProviderInContractProgramId.equals(PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_NO)  || serviceProviderInContractProgramId.equalsIgnoreCase("false"))){
			serviceProviderInContractProgramDesc=UIConstants.NO_FULL_TEXT;
		}
		else if(serviceProviderInContractProgramId!=null && (serviceProviderInContractProgramId.equals(PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_YES) || serviceProviderInContractProgramId.equalsIgnoreCase("true"))){
			serviceProviderInContractProgramDesc=UIConstants.YES_FULL_TEXT;
		}
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
	/**
	 * @param serviceProviderInHouseId The serviceProviderInHouseId to set.
	 */
	public void setServiceProviderInHouseId(String serviceProviderInHouseId) {
		this.serviceProviderInHouseId = serviceProviderInHouseId;
		serviceProviderInHouseDesc="";
		if(serviceProviderInHouseId!=null && (serviceProviderInHouseId.equals(PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_NO) || serviceProviderInHouseId.equalsIgnoreCase("false"))){
			serviceProviderInHouseDesc=UIConstants.NO_FULL_TEXT;
		}
		else if(serviceProviderInHouseId!=null && (serviceProviderInHouseId.equals(PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_YES)  || serviceProviderInHouseId.equalsIgnoreCase("true"))){
			serviceProviderInHouseDesc=UIConstants.YES_FULL_TEXT;
		}
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
	 * @return Returns the serviceProviderStatudId.
	 */
	public String getServiceProviderStatusId() {
		return serviceProviderStatusId;
	}
	/**
	 * @param serviceProviderStatudId The serviceProviderStatudId to set.
	 */
	public void setServiceProviderStatusId(String serviceProviderStatudId) {
		this.serviceProviderStatusId = serviceProviderStatudId;
		this.serviceProviderStatusDesc="";
		serviceProviderStatusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_SERVICE_PROVIDER_STATUS,serviceProviderStatudId);
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
	 * @return Returns the statusChangeDate.
	 */
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}
	/**
	 * @param statusChangeDate The statusChangeDate to set.
	 */
	public void setStatusChangeDate(Date aStatusChangeDate) {
		statusChangeDateTimeAsStr="";
		statusChangeDateAsStr="";
		statusChangeTimeAsStr="";
		this.statusChangeDate = aStatusChangeDate;
		if(statusChangeDate!=null){
			try{
				statusChangeDateTimeAsStr=DateUtil.dateToString(statusChangeDate, UIConstants.DATETIME_FMT_1AMPM);
				
			}
			catch(Exception e){
				statusChangeDateTimeAsStr="";
			}
		}
	}
	/**
	 * @return Returns the statusChangeDateAsStr.
	 */
	public String getStatusChangeDateAsStr() {
		return statusChangeDateAsStr;
	}
	/**
	 * @return Returns the statusChangeDateTimeAsStr.
	 */
	public String getStatusChangeDateTimeAsStr() {
		
		return statusChangeDateTimeAsStr;
	}
	/**
	 * @return Returns the statusChangeTimeAsStr.
	 */
	public String getStatusChangeTimeAsStr() {
		return statusChangeTimeAsStr;
	}
	/**
	 * @return the sortId
	 */
	public String getSortId() {
		return sortId;
	}
	/**
	 * @param sortId the sortId to set
	 */
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	
}// END CLASS
