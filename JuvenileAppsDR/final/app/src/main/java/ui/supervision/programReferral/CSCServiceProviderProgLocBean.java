package ui.supervision.programReferral;

import java.util.Date;
import java.util.List;

import mojo.km.utilities.DateUtil;

/**
 * 
 * @author cc_bjangay
 *
 */
public class CSCServiceProviderProgLocBean
{
	private String serviceProviderId;
	private String serviceProviderName;
	private String referralTypeNum;
	private String referralTypeCd;
	private String referralTypeDesc;
	private String programId;
	private String programIdentifier;
	private String programName;
	private String cstsCode;
	private List languagesOffered;
	private String sexSpecificDesc;
	private String contractProgramDesc;
	
	private CSCLocationInfoBean programLocationBean;
	private List programLocationsList; //CSCLocationInfoBean - only in case of re-referrals, to select a new location
	
	private Date scheduleDate;
	private String scheduleDateAsStr;
	private String scheduleTimeId;
	private String scheduleTimeDesc;
	
	
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
	 * @return the referralTypeNum
	 */
	public String getReferralTypeNum() {
		return referralTypeNum;
	}
	/**
	 * @param referralTypeNum the referralTypeNum to set
	 */
	public void setReferralTypeNum(String referralTypeNum) {
		this.referralTypeNum = referralTypeNum;
	}
	/**
	 * @return the referralTypeDesc
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	/**
	 * @param referralTypeDesc the referralTypeDesc to set
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
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
	 * @return the languagesOffered
	 */
	public List getLanguagesOffered() {
		return languagesOffered;
	}
	/**
	 * @param languagesOffered the languagesOffered to set
	 */
	public void setLanguagesOffered(List languagesOffered) {
		this.languagesOffered = languagesOffered;
	}
	/**
	 * @return the sexSpecificDesc
	 */
	public String getSexSpecificDesc() {
		return sexSpecificDesc;
	}
	/**
	 * @param sexSpecificDesc the sexSpecificDesc to set
	 */
	public void setSexSpecificDesc(String sexSpecificDesc) {
		this.sexSpecificDesc = sexSpecificDesc;
	}
	/**
	 * @return the contractProgramDesc
	 */
	public String getContractProgramDesc() {
		return contractProgramDesc;
	}
	/**
	 * @param contractProgramDesc the contractProgramDesc to set
	 */
	public void setContractProgramDesc(String contractProgramDesc) {
		this.contractProgramDesc = contractProgramDesc;
	}
	/**
	 * @return the programLocationsList
	 */
	public List getProgramLocationsList() {
		return programLocationsList;
	}
	/**
	 * @param programLocationsList the programLocationsList to set
	 */
	public void setProgramLocationsList(List programLocationsList) {
		this.programLocationsList = programLocationsList;
	}
	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate()
	{
		scheduleDate = null;
		try
		{
			scheduleDate = DateUtil.stringToDate(scheduleDateAsStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			scheduleDate = null;
		}
		return scheduleDate;
	}
	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return the scheduleDateAsStr
	 */
	public String getScheduleDateAsStr() {
		return scheduleDateAsStr;
	}
	/**
	 * @param scheduleDateAsStr the scheduleDateAsStr to set
	 */
	public void setScheduleDateAsStr(String scheduleDateAsStr) {
		this.scheduleDateAsStr = scheduleDateAsStr;
	}
	/**
	 * @return the scheduleTimeId
	 */
	public String getScheduleTimeId() {
		return scheduleTimeId;
	}
	/**
	 * @param scheduleTimeId the scheduleTimeId to set
	 */
	public void setScheduleTimeId(String scheduleTimeId) {
		this.scheduleTimeId = scheduleTimeId;
	}
	/**
	 * @return the scheduleTimeDesc
	 */
	public String getScheduleTimeDesc() {
		return scheduleTimeDesc;
	}
	/**
	 * @param scheduleTimeDesc the scheduleTimeDesc to set
	 */
	public void setScheduleTimeDesc(String scheduleTimeDesc) {
		this.scheduleTimeDesc = scheduleTimeDesc;
	}
	/**
	 * @return the referralTypeCd
	 */
	public String getReferralTypeCd() {
		return referralTypeCd;
	}
	/**
	 * @param referralTypeCd the referralTypeCd to set
	 */
	public void setReferralTypeCd(String referralTypeCd) {
		this.referralTypeCd = referralTypeCd;
	}
	/**
	 * @return the programLocationBean
	 */
	public CSCLocationInfoBean getProgramLocationBean() {
		return programLocationBean;
	}
	/**
	 * @param programLocationBean the programLocationBean to set
	 */
	public void setProgramLocationBean(CSCLocationInfoBean programLocationBean) {
		this.programLocationBean = programLocationBean;
	}
}
