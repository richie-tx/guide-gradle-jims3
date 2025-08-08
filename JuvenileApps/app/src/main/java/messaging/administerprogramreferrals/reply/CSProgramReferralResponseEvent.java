/*
 * Created on Mar 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.reply;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramReferralResponseEvent extends ResponseEvent 
{
	private String programReferralId; 
	
	private String criminalCaseId;
	private String defendantId;
	
	private String referralStatusCode;
	
	private String referralTypeCode;
	private String referralTypeDesc;
	
	private Date referralDate;	
	
	private String serviceProviderId;
	private String programLocationId;
	private String programId;
	private String locationId;
	
	private String newServiceProviderName;
	private String newServiceProviderPhone;
	private String newServiceProviderFax;
	
	private Date scheduleDate;
	
	private Date programBeginDate;
	private boolean isContractProgram;
	private String tracerNumber;
	private String placementReasonCd;
	private int confinementYears;
	private int confinementMonths;
	private int confinementDays;
	private String submitComments;
	
	private Date programEndDate;
	private String dischargeReasonCd;
	private String exitComments;
	
	private boolean isAutoReferral;
	private boolean isIncarcerationReferral;
	private boolean isProgramUnitRef;
	
	private boolean isSentToState;
	
	// Before deleting the following attribute, please consult with Violation Report Team
	private boolean manualAdded;
	
//	Referral Program Location Details
	private String serviceProviderName;
	private String programIdentifier;
	private String programName;
	private String cstsCode;
	private String sexSpecificCode;
	private boolean spContractProgram;
	private List programLanguagesList;
	
	private String streetNumber;
	private String streetName;
	private String streetType;
	private String aptNum;
	private String city;
	private String state;
	private String zipCode;
	private String locationPhone;
	private String locationFax;
	
	//this is a quick fix
	private String dischargeReason;
	
	
	
	public String getDischargeReason() {
		return dischargeReason;
	}
	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
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
	 * @return Returns the confinementDays.
	 */
	public int getConfinementDays() {
		return confinementDays;
	}
	/**
	 * @param confinementDays The confinementDays to set.
	 */
	public void setConfinementDays(int confinementDays) {
		this.confinementDays = confinementDays;
	}
	/**
	 * @return Returns the confinementMonths.
	 */
	public int getConfinementMonths() {
		return confinementMonths;
	}
	/**
	 * @param confinementMonths The confinementMonths to set.
	 */
	public void setConfinementMonths(int confinementMonths) {
		this.confinementMonths = confinementMonths;
	}
	/**
	 * @return Returns the confinementYears.
	 */
	public int getConfinementYears() {
		return confinementYears;
	}
	/**
	 * @param confinementYears The confinementYears to set.
	 */
	public void setConfinementYears(int confinementYears) {
		this.confinementYears = confinementYears;
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
	 * @return Returns the isContractProgram.
	 */
	public boolean isContractProgram() {
		return isContractProgram;
	}
	/**
	 * @param isContractProgram The isContractProgram to set.
	 */
	public void setContractProgram(boolean isContractProgram) {
		this.isContractProgram = isContractProgram;
	}
	/**
	 * @return Returns the newServiceProviderFax.
	 */
	public String getNewServiceProviderFax() {
		return newServiceProviderFax;
	}
	/**
	 * @param newServiceProviderFax The newServiceProviderFax to set.
	 */
	public void setNewServiceProviderFax(String newServiceProviderFax) {
		this.newServiceProviderFax = newServiceProviderFax;
	}
	/**
	 * @return Returns the newServiceProviderName.
	 */
	public String getNewServiceProviderName() {
		return newServiceProviderName;
	}
	/**
	 * @param newServiceProviderName The newServiceProviderName to set.
	 */
	public void setNewServiceProviderName(String newServiceProviderName) {
		this.newServiceProviderName = newServiceProviderName;
	}
	/**
	 * @return Returns the newServiceProviderPhone.
	 */
	public String getNewServiceProviderPhone() {
		return newServiceProviderPhone;
	}
	/**
	 * @param newServiceProviderPhone The newServiceProviderPhone to set.
	 */
	public void setNewServiceProviderPhone(String newServiceProviderPhone) {
		this.newServiceProviderPhone = newServiceProviderPhone;
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
	 * @return Returns the referralTypeCode.
	 */
	public String getReferralTypeCode() {
		return referralTypeCode;
	}
	/**
	 * @param referralTypeCode The referralTypeCode to set.
	 */
	public void setReferralTypeCode(String referralTypeCode) {
		this.referralTypeCode = referralTypeCode;
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
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return the programIdentifier
	 */
	public String getProgramIdentifier() {
		return programIdentifier;
	}
	/**
	 * @param programIdentifier the programIdentifier to set
	 */
	public void setProgramIdentifier(String programIdentifier) {
		this.programIdentifier = programIdentifier;
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
	 * @return the serviceProviderId
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return the cstsCode
	 */
	public String getCstsCode() {
		return cstsCode;
	}
	/**
	 * @param cstsCode the cstsCode to set
	 */
	public void setCstsCode(String cstsCode) {
		this.cstsCode = cstsCode;
	}
	/**
	 * @return the sexSpecificCode
	 */
	public String getSexSpecificCode() {
		return sexSpecificCode;
	}
	/**
	 * @param sexSpecificCode the sexSpecificCode to set
	 */
	public void setSexSpecificCode(String sexSpecificCode) {
		this.sexSpecificCode = sexSpecificCode;
	}
	/**
	 * @return the programLanguagesList
	 */
	public List getProgramLanguagesList() {
		return programLanguagesList;
	}
	/**
	 * @param programLanguagesList the programLanguagesList to set
	 */
	public void setProgramLanguagesList(List programLanguagesList) {
		this.programLanguagesList = programLanguagesList;
	}
	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the streetType
	 */
	public String getStreetType() {
		return streetType;
	}
	/**
	 * @param streetType the streetType to set
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}
	/**
	 * @param aptNum the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the locationPhone
	 */
	public String getLocationPhone() {
		return locationPhone;
	}
	/**
	 * @param locationPhone the locationPhone to set
	 */
	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}
	/**
	 * @return the locationFax
	 */
	public String getLocationFax() {
		return locationFax;
	}
	/**
	 * @param locationFax the locationFax to set
	 */
	public void setLocationFax(String locationFax) {
		this.locationFax = locationFax;
	}
	/**
	 * @return the spContractProgram
	 */
	public boolean isSpContractProgram() {
		return spContractProgram;
	}
	/**
	 * @param spContractProgram the spContractProgram to set
	 */
	public void setSpContractProgram(boolean spContractProgram) {
		this.spContractProgram = spContractProgram;
	}
	/**
	 * @return the programLocationId
	 */
	public String getProgramLocationId() {
		return programLocationId;
	}
	/**
	 * @param programLocationId the programLocationId to set
	 */
	public void setProgramLocationId(String programLocationId) {
		this.programLocationId = programLocationId;
	}
	/**
	 * @return the isSentToState
	 */
	public boolean isSentToState() {
		return isSentToState;
	}
	/**
	 * @param isSentToState the isSentToState to set
	 */
	public void setSentToState(boolean isSentToState) {
		this.isSentToState = isSentToState;
	}
	/**
	 * @return the isAutoReferral
	 */
	public boolean isAutoReferral() {
		return isAutoReferral;
	}
	/**
	 * @param isAutoReferral the isAutoReferral to set
	 */
	public void setAutoReferral(boolean isAutoReferral) {
		this.isAutoReferral = isAutoReferral;
	}
	/**
	 * @return the isIncarcerationReferral
	 */
	public boolean isIncarcerationReferral() {
		return isIncarcerationReferral;
	}
	/**
	 * @param isIncarcerationReferral the isIncarcerationReferral to set
	 */
	public void setIncarcerationReferral(boolean isIncarcerationReferral) {
		this.isIncarcerationReferral = isIncarcerationReferral;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isProgramUnitRef() {
		return isProgramUnitRef;
	}
	/**
	 * 
	 * @param isProgramUnitRef
	 */
	public void setProgramUnitRef(boolean isProgramUnitRef) {
		this.isProgramUnitRef = isProgramUnitRef;
	}
	
}
