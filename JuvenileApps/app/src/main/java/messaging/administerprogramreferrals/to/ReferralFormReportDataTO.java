package messaging.administerprogramreferrals.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.domintf.ISocialSecurity;

public class ReferralFormReportDataTO implements IReferralFormReportDataTO {

	/*********** SUPERVISEE DETAILS *************/
	private String superviseeId = "";
	private String superviseeName = "";
	private IPhoneNumber superviseePhone = null;
	private IPhoneNumber superviseeEmployPhone = null;
	private String superviseeDOB = "";
	private String court = "";
	private String currentOffense = "";
	private Date currentOffenseDate = null;
	private String cause = ""; // caseNumber
	private String race = "";
	private String sex = "";
	private String driverLicenseNumber = "";
	private String sid = ""; // TX ID#
	private ISocialSecurity ssn = null;

	private String superviseeAddress = "";// New Start

	/*********** OOC DETAILS *************/
	private String oocOriginatingCauseNumber = "";
	private String oocOriginatingCounty = "";
	private String oocOriginatingState = "";

	/*********** OFFICER DETAILS *************/
	// private String officerName = ""; Use supervisionStaffName for Referral
	// Officer Name
	private String supervisionStaffName = "";
	private IPhoneNumber supervisionStaffPhone = null;
	private String superviseeStaffEmail = "";
	private IPhoneNumber supervisionStaffFax = null;
	private String poi = "";
	private String staffPositionName = "";
	private String superviseeProgramUnit = "";
	private String programUnitAddress = "";
	private IPhoneNumber programUnitFax = null;
	private IPhoneNumber programUnitPhone = null;

	/*********** REFERRAL PROGRAM DETAILS *************/
	private String contractTracerNumber = "";
	private String programReferralId = "";
	private String referralDateAsStr = "";
	private String referralType = "";
	private String referralProgramLocation = "";
	private IPhoneNumber referralProgramPhone = null;// New Start
	private IPhoneNumber referralProgramFax = null;// New Start
	private Date docs = null; // date
	private Date dischargeDate = null; // date
	
	/*********** SERVICE PROVIDER DETAILS *************/
	private String serviceProviderName = "";
	private String serviceProviderLocation = "";
	private IPhoneNumber serviceProviderPhone = null;
	private IPhoneNumber serviceProviderFax = null;
	private IName serviceProviderContactName = null;
	private IPhoneNumber serviceProviderContactPhone = null;
	private List VIPEnglishList = new ArrayList();
	private List VIPSpanishList = new ArrayList();

	/*********** REFERRAL FORM DETAILS *************/
	private String referralFormId = "";
	private String referralFormName = "";
	private String programName = "";
	private String enrollmentNumber = "";
	private String referralReasonComments = "";
	private List priorOffenseRecordList = new ArrayList();

	/**
	 * @return the superviseeId
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            the superviseeId to set
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName
	 *            the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return the superviseePhone
	 */
	public IPhoneNumber getSuperviseePhone() {
		return superviseePhone;
	}

	/**
	 * @param superviseePhone
	 *            the superviseePhone to set
	 */
	public void setSuperviseePhone(IPhoneNumber superviseePhone) {
		this.superviseePhone = superviseePhone;
	}

	/**
	 * @return the superviseeEmployPhone
	 */
	public IPhoneNumber getSuperviseeEmployPhone() {
		return superviseeEmployPhone;
	}

	/**
	 * @param superviseeEmployPhone
	 *            the superviseeEmployPhone to set
	 */
	public void setSuperviseeEmployPhone(IPhoneNumber superviseeEmployPhone) {
		this.superviseeEmployPhone = superviseeEmployPhone;
	}

	/**
	 * @return the superviseeDOB
	 */
	public String getSuperviseeDOB() {
		return superviseeDOB;
	}

	/**
	 * @param superviseeDOB
	 *            the superviseeDOB to set
	 */
	public void setSuperviseeDOB(String superviseeDOB) {
		this.superviseeDOB = superviseeDOB;
	}

	/**
	 * @return the court
	 */
	public String getCourt() {
		return court;
	}

	/**
	 * @param court
	 *            the court to set
	 */
	public void setCourt(String court) {
		this.court = court;
	}

	/**
	 * @return the currentOffense
	 */
	public String getCurrentOffense() {
		return currentOffense;
	}

	/**
	 * @param currentOffense
	 *            the currentOffense to set
	 */
	public void setCurrentOffense(String currentOffense) {
		this.currentOffense = currentOffense;
	}

	/**
	 * @return the currentOffenseDate
	 */
	public Date getCurrentOffenseDate() {
		return currentOffenseDate;
	}

	/**
	 * @param currentOffenseDate
	 *            the currentOffenseDate to set
	 */
	public void setCurrentOffenseDate(Date currentOffenseDate) {
		this.currentOffenseDate = currentOffenseDate;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the driverLicenseNumber
	 */
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	/**
	 * @param driverLicenseNumber
	 *            the driverLicenseNumber to set
	 */
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * @return the ssn
	 */
	public ISocialSecurity getSsn() {
		return ssn;
	}

	/**
	 * @param ssn
	 *            the ssn to set
	 */
	public void setSsn(ISocialSecurity ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the superviseeAddress
	 */
	public String getSuperviseeAddress() {
		return superviseeAddress;
	}

	/**
	 * @param superviseeAddress
	 *            the superviseeAddress to set
	 */
	public void setSuperviseeAddress(String superviseeAddress) {
		this.superviseeAddress = superviseeAddress;
	}

	/**
	 * @return the oocOriginatingCauseNumber
	 */
	public String getOocOriginatingCauseNumber() {
		return oocOriginatingCauseNumber;
	}

	/**
	 * @param oocOriginatingCauseNumber
	 *            the oocOriginatingCauseNumber to set
	 */
	public void setOocOriginatingCauseNumber(String oocOriginatingCauseNumber) {
		this.oocOriginatingCauseNumber = oocOriginatingCauseNumber;
	}

	/**
	 * @return the oocOriginatingCounty
	 */
	public String getOocOriginatingCounty() {
		return oocOriginatingCounty;
	}

	/**
	 * @param oocOriginatingCounty
	 *            the oocOriginatingCounty to set
	 */
	public void setOocOriginatingCounty(String oocOriginatingCounty) {
		this.oocOriginatingCounty = oocOriginatingCounty;
	}

	/**
	 * @return the oocOriginatingState
	 */
	public String getOocOriginatingState() {
		return oocOriginatingState;
	}

	/**
	 * @param oocOriginatingState
	 *            the oocOriginatingState to set
	 */
	public void setOocOriginatingState(String oocOriginatingState) {
		this.oocOriginatingState = oocOriginatingState;
	}

	/**
	 * @return the supervisionStaffName
	 */
	public String getSupervisionStaffName() {
		return supervisionStaffName;
	}

	/**
	 * @param supervisionStaffName
	 *            the supervisionStaffName to set
	 */
	public void setSupervisionStaffName(String supervisionStaffName) {
		this.supervisionStaffName = supervisionStaffName;
	}

	/**
	 * @return the supervisionStaffPhone
	 */
	public IPhoneNumber getSupervisionStaffPhone() {
		return supervisionStaffPhone;
	}

	/**
	 * @param supervisionStaffPhone
	 *            the supervisionStaffPhone to set
	 */
	public void setSupervisionStaffPhone(IPhoneNumber supervisionStaffPhone) {
		this.supervisionStaffPhone = supervisionStaffPhone;
	}

	/**
	 * @return the superviseeStaffEmail
	 */
	public String getSuperviseeStaffEmail() {
		return superviseeStaffEmail;
	}

	/**
	 * @param superviseeStaffEmail
	 *            the superviseeStaffEmail to set
	 */
	public void setSuperviseeStaffEmail(String superviseeStaffEmail) {
		this.superviseeStaffEmail = superviseeStaffEmail;
	}

	/**
	 * @return the supervisionStaffFax
	 */
	public IPhoneNumber getSupervisionStaffFax() {
		return supervisionStaffFax;
	}

	/**
	 * @param supervisionStaffFax
	 *            the supervisionStaffFax to set
	 */
	public void setSupervisionStaffFax(IPhoneNumber supervisionStaffFax) {
		this.supervisionStaffFax = supervisionStaffFax;
	}

	/**
	 * @return the poi
	 */
	public String getPoi() {
		return poi;
	}

	/**
	 * @param poi
	 *            the poi to set
	 */
	public void setPoi(String poi) {
		this.poi = poi;
	}

	/**
	 * @return the staffPositionName
	 */
	public String getStaffPositionName() {
		return staffPositionName;
	}

	/**
	 * @param staffPositionName
	 *            the staffPositionName to set
	 */
	public void setStaffPositionName(String staffPositionName) {
		this.staffPositionName = staffPositionName;
	}

	/**
	 * @return the superviseeProgramUnit
	 */
	public String getSuperviseeProgramUnit() {
		return superviseeProgramUnit;
	}

	/**
	 * @param superviseeProgramUnit
	 *            the superviseeProgramUnit to set
	 */
	public void setSuperviseeProgramUnit(String superviseeProgramUnit) {
		this.superviseeProgramUnit = superviseeProgramUnit;
	}

	/**
	 * @return the programUnitAddress
	 */
	public String getProgramUnitAddress() {
		return programUnitAddress;
	}

	/**
	 * @param programUnitAddress
	 *            the programUnitAddress to set
	 */
	public void setProgramUnitAddress(String programUnitAddress) {
		this.programUnitAddress = programUnitAddress;
	}
	
	/**
	 * @return the programUnitFax
	 */
	public IPhoneNumber getProgramUnitFax() {
		return programUnitFax;
	}

	/**
	 * @param programUnitFax the programUnitFax to set
	 */
	public void setProgramUnitFax(IPhoneNumber programUnitFax) {
		this.programUnitFax = programUnitFax;
	}

	/**
	 * @return the programUnitPhone
	 */
	public IPhoneNumber getProgramUnitPhone() {
		return programUnitPhone;
	}

	/**
	 * @param programUnitPhone the programUnitPhone to set
	 */
	public void setProgramUnitPhone(IPhoneNumber programUnitPhone) {
		this.programUnitPhone = programUnitPhone;
	}

	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}

	/**
	 * @param programReferralId
	 *            the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}

	/**
	 * @return the contractTracerNumber
	 */
	public String getContractTracerNumber() {
		return contractTracerNumber;
	}

	/**
	 * @param contractTracerNumber the contractTracerNumber to set
	 */
	public void setContractTracerNumber(String contractTracerNumber) {
		this.contractTracerNumber = contractTracerNumber;
	}

	/**
	 * @return the referralType
	 */
	public String getReferralType() {
		return referralType;
	}

	/**
	 * @param referralType
	 *            the referralType to set
	 */
	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	/**
	 * @return the referralProgramLocation
	 */
	public String getReferralProgramLocation() {
		return referralProgramLocation;
	}

	/**
	 * @param referralProgramLocation
	 *            the referralProgramLocation to set
	 */
	public void setReferralProgramLocation(String referralProgramLocation) {
		this.referralProgramLocation = referralProgramLocation;
	}

	/**
	 * @return the referralProgramPhone
	 */
	public IPhoneNumber getReferralProgramPhone() {
		return referralProgramPhone;
	}

	/**
	 * @param referralProgramPhone
	 *            the referralProgramPhone to set
	 */
	public void setReferralProgramPhone(IPhoneNumber referralProgramPhone) {
		this.referralProgramPhone = referralProgramPhone;
	}

	/**
	 * @return the referralProgramFax
	 */
	public IPhoneNumber getReferralProgramFax() {
		return referralProgramFax;
	}

	/**
	 * @param referralProgramFax
	 *            the referralProgramFax to set
	 */
	public void setReferralProgramFax(IPhoneNumber referralProgramFax) {
		this.referralProgramFax = referralProgramFax;
	}

	/**
	 * @return the referralFormId
	 */
	public String getReferralFormId() {
		return referralFormId;
	}

	/**
	 * @param referralFormId
	 *            the referralFormId to set
	 */
	public void setReferralFormId(String referralFormId) {
		this.referralFormId = referralFormId;
	}

	/**
	 * @return the referralFormName
	 */
	public String getReferralFormName() {
		return referralFormName;
	}

	/**
	 * @param referralFormName
	 *            the referralFormName to set
	 */
	public void setReferralFormName(String referralFormName) {
		this.referralFormName = referralFormName;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName
	 *            the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	/**
	 * @param enrollmentNumber
	 *            the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	/**
	 * @return the referralReasonComments
	 */
	public String getReferralReasonComments() {
		return referralReasonComments;
	}

	/**
	 * @param referralReasonComments
	 *            the referralReasonComments to set
	 */
	public void setReferralReasonComments(String referralReasonComments) {
		this.referralReasonComments = referralReasonComments;
	}

	/**
	 * @return the priorOffenseRecordList
	 */
	public List getPriorOffenseRecordList() {
		return priorOffenseRecordList;
	}

	/**
	 * @param priorOffenseRecordList the priorOffenseRecordList to set
	 */
	public void setPriorOffenseRecordList(List priorOffenseRecordList) {
		this.priorOffenseRecordList = priorOffenseRecordList;
	}

	/**
	 * @return the referralDateAsStr
	 */
	public String getReferralDateAsStr() {
		return referralDateAsStr;
	}

	/**
	 * @param referralDateAsStr
	 *            the referralDateAsStr to set
	 */
	public void setReferralDateAsStr(String referralDateAsStr) {
		this.referralDateAsStr = referralDateAsStr;
	}

	/**
	 * @return the docs
	 */
	public Date getDocs() {
		return docs;
	}

	/**
	 * @param docs
	 *            the docs to set
	 */
	public void setDocs(Date docs) {
		this.docs = docs;
	}

	/**
	 * @return the dischargeDate
	 */
	public Date getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * @param dischargeDate 
	 * 			 the dischargeDate to set
	 */
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	
	/**
	 * @param serviceProviderName
	 *            the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return the serviceProviderLocation
	 */
	public String getServiceProviderLocation() {
		return serviceProviderLocation;
	}
	
	/**
	 * @param serviceProviderLocation
	 *            the serviceProviderLocation to set
	 */
	public void setServiceProviderLocation(String serviceProviderLocation) {
		this.serviceProviderLocation = serviceProviderLocation;
	}

	/**
	 * @return the serviceProviderPhone
	 */
	public IPhoneNumber getServiceProviderPhone() {
		return serviceProviderPhone;
	}
	
	/**
	 * @param serviceProviderPhone
	 *            the serviceProviderPhone to set
	 */
	public void setServiceProviderPhone(IPhoneNumber serviceProviderPhone) {
		this.serviceProviderPhone = serviceProviderPhone;
	}

	/**
	 * @return the serviceProviderFax
	 */
	public IPhoneNumber getServiceProviderFax() {
		return serviceProviderFax;
	}

	/**
	 * @param serviceProviderFax
	 *            the serviceProviderFax to set
	 */
	public void setServiceProviderFax(IPhoneNumber serviceProviderFax) {
		this.serviceProviderFax = serviceProviderFax;
	}

	/**
	 * @return the serviceProviderContactName
	 */
	public IName getServiceProviderContactName() {
		return serviceProviderContactName;
	}

	/**
	 * @param serviceProviderContactName
	 *            the serviceProviderContactName to set
	 */
	public void setServiceProviderContactName(IName serviceProviderContactName) {
		this.serviceProviderContactName = serviceProviderContactName;
	}

	/**
	 * @return the serviceProviderContactPhone
	 */
	public IPhoneNumber getServiceProviderContactPhone() {
		return serviceProviderContactPhone;
	}
	
	/**
	 * @param serviceProviderContactPhone the serviceProviderContactPhone to set
	 */
	public void setServiceProviderContactPhone(
			IPhoneNumber serviceProviderContactPhone) {
		this.serviceProviderContactPhone = serviceProviderContactPhone;
	}

	/**
	 * @return the vIPEnglishList
	 */
	public List getVIPEnglishList() {
		return VIPEnglishList;
	}

	/**
	 * @param englishList the vIPEnglishList to set
	 */
	public void setVIPEnglishList(List englishList) {
		VIPEnglishList = englishList;
	}

	/**
	 * @return the vIPSpanishList
	 */
	public List getVIPSpanishList() {
		return VIPSpanishList;
	}

	/**
	 * @param spanishList the vIPSpanishList to set
	 */
	public void setVIPSpanishList(List spanishList) {
		VIPSpanishList = spanishList;
	}
	
}
