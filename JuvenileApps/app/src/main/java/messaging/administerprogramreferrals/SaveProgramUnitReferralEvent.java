/*
 * Created on Jan 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveProgramUnitReferralEvent extends RequestEvent 
{
	private String programReferralId; 
	private String programReferralToCloseId;
	
	private String defendantId;
	private String criminalCaseId;
	
	private String referralStatusCode;
	private String referralTypeCode;
	private Date referralDate;
	
	private String programId;
	private String locationId;
	
	private String serviceProviderName;
	private String serviceProviderPhone;
	private String serviceProviderFax;
	
	private Date scheduleDate;
	
	private Date programBeginDate;
	private boolean contractProgram;
	private boolean stateReporting;
	private String tracerNumber;
	private String placementReasonCd;
	private String submitComments;
	
	private Date programEndDate;
	private boolean programUnitReferral;
	private String dischargeReasonCd;
	private String exitComments;
	private boolean submitReferral;
	
	private String autoRefferalTypeCode;
	
	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	
	/*
	 * 
	 */
	public String getProgramReferralToCloseId() {
		return programReferralToCloseId;
	}
	
	/**
	 * 
	 * @param programReferralToCloseId
	 */
	public void setProgramReferralToCloseId(String programReferralToCloseId) {
		this.programReferralToCloseId = programReferralToCloseId;
	}
	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
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
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return Returns the referralType.
	 */
	public String getReferralTypeCode() {
		return referralTypeCode;
	}
	/**
	 * @param referralType The referralType to set.
	 */
	public void setReferralTypeCode(String referralTypeCode) {
		this.referralTypeCode = referralTypeCode;
	}
	/**
	 * @return Returns the referralStatusCode.
	 */
	public String getReferralStatusCode() {
		return referralStatusCode;
	}
	/**
	 * @param referralStatusCode The referralStatusCode to set.
	 */
	public void setReferralStatusCode(String referralStatusCode) {
		this.referralStatusCode = referralStatusCode;
	}
	/**
	 * @return Returns the autoRefferalTypeCode.
	 */
	public String getAutoRefferalTypeCode() {
		return autoRefferalTypeCode;
	}
	/**
	 * @param autoRefferalTypeCode The autoRefferalTypeCode to set.
	 */
	public void setAutoRefferalTypeCode(String autoRefferalTypeCode) {
		this.autoRefferalTypeCode = autoRefferalTypeCode;
	}
	/**
	 * @return the contractProgram
	 */
	public boolean isContractProgram() {
		return contractProgram;
	}
	/**
	 * @param contractProgram the contractProgram to set
	 */
	public void setContractProgram(boolean contractProgram) {
		this.contractProgram = contractProgram;
	}
	/**
	 * @return Returns the programBeginDate.
	 */
	public Date getProgramBeginDate() {
		return programBeginDate;
	}
	/**
	 * @param programBeginDate The programBeginDate to set.
	 */
	public void setProgramBeginDate(Date programBeginDate) {
		this.programBeginDate = programBeginDate;
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
	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}
	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	/**
	 * @return Returns the scheduleDate.
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}
	/**
	 * @param scheduleDate The scheduleDate to set.
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return Returns the tracerNumber.
	 */
	public String getTracerNumber() {
		return tracerNumber;
	}
	/**
	 * @param tracerNumber The tracerNumber to set.
	 */
	public void setTracerNumber(String tracerNumber) {
		this.tracerNumber = tracerNumber;
	}
	/**
	 * @return the newServiceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param newServiceProviderName the newServiceProviderName to set
	 */
	public void setServiceProviderName(String newServiceProviderName) {
		this.serviceProviderName = newServiceProviderName;
	}
	/**
	 * @return the newServiceProviderPhone
	 */
	public String getServiceProviderPhone() {
		return serviceProviderPhone;
	}
	/**
	 * @param newServiceProviderPhone the newServiceProviderPhone to set
	 */
	public void setServiceProviderPhone(String newServiceProviderPhone) {
		this.serviceProviderPhone = newServiceProviderPhone;
	}
	/**
	 * @return the newServiceProviderFax
	 */
	public String getServiceProviderFax() {
		return serviceProviderFax;
	}
	/**
	 * 
	 * @return
	 */
	
	public boolean isSubmitReferral() {
		return submitReferral;
	}
	
	/**
	 * 
	 * @param submitReferral
	 */
	public void setSubmitReferral(boolean submitReferral) {
		this.submitReferral = submitReferral;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isProgramUnitReferral() {
		return programUnitReferral;
	}
	/**
	 * 
	 * @param programUnitReferral
	 */
	public void setProgramUnitReferral(boolean programUnitReferral) {
		this.programUnitReferral = programUnitReferral;
	}
	/**
	 * @param newServiceProviderFax the newServiceProviderFax to set
	 */
	public void setServiceProviderFax(String newServiceProviderFax) {
		this.serviceProviderFax = newServiceProviderFax;
	}
	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the submitComments
	 */
	public String getSubmitComments() {
		return submitComments;
	}
	/**
	 * @param submitComments the submitComments to set
	 */
	public void setSubmitComments(String submitComments) {
		this.submitComments = submitComments;
	}
	/**
	 * @return the exitComments
	 */
	public String getExitComments() {
		return exitComments;
	}
	/**
	 * @param exitComments the exitComments to set
	 */
	public void setExitComments(String exitComments) {
		this.exitComments = exitComments;
	}
	/**
	 * @return the placementReasonCd
	 */
	public String getPlacementReasonCd() {
		return placementReasonCd;
	}
	/**
	 * @param placementReasonCd the placementReasonCd to set
	 */
	public void setPlacementReasonCd(String placementReasonCd) {
		this.placementReasonCd = placementReasonCd;
	}
	/**
	 * @return the dischargeReasonCd
	 */
	public String getDischargeReasonCd() {
		return dischargeReasonCd;
	}
	/**
	 * @param dischargeReasonCd the dischargeReasonCd to set
	 */
	public void setDischargeReasonCd(String dischargeReasonCd) {
		this.dischargeReasonCd = dischargeReasonCd;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isStateReporting() {
		return stateReporting;
	}
	/**
	 * 
	 * @param stateReporting
	 */
	public void setStateReporting(boolean stateReporting) {
		this.stateReporting = stateReporting;
	}
}