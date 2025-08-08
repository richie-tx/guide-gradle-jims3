/*
 * Created on Apr 7, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.Date;
import java.util.List;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCProgramInfoBean 
{
	
	 private String programId;
	 private String programIdentifier;
	 private String programName;
	 public String programStatusId;
	 public String programStatusDesc;
	 private String referralTypeCd;
	 private String referralTypeNum;
	 private String cstsCode;
	 private String sexSpecificId;
	 private String sexSpecificDesc;
	 private String contractProgramDesc;
	 private List languagesOffered;
	 private String programPrice;
	 
	 private List programLocationList; //CSCLocationInfoBean
	 private List selectedLocationList; // CSCLocationInfoBean
	 
	 private String regionId;
	 private String regionDesc;
	
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
	 * @return the programStatusId
	 */
	public String getProgramStatusId() {
		return programStatusId;
	}
	/**
	 * @param programStatusId the programStatusId to set
	 */
	public void setProgramStatusId(String programStatusId) {
		this.programStatusId = programStatusId;
	}
	/**
	 * @return the programStatusDesc
	 */
	public String getProgramStatusDesc() {
		return programStatusDesc;
	}
	/**
	 * @param programStatusDesc the programStatusDesc to set
	 */
	public void setProgramStatusDesc(String programStatusDesc) {
		this.programStatusDesc = programStatusDesc;
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
	 * @return the sexSpecificId
	 */
	public String getSexSpecificId() {
		return sexSpecificId;
	}
	/**
	 * @param sexSpecificId the sexSpecificId to set
	 */
	public void setSexSpecificId(String sexSpecificId) {
		this.sexSpecificId = sexSpecificId;
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
	 * @return the programLocationList
	 */
	public List getProgramLocationList() {
		return programLocationList;
	}
	/**
	 * @param programLocationList the programLocationList to set
	 */
	public void setProgramLocationList(List programLocationList) {
		this.programLocationList = programLocationList;
	}
	/**
	 * @return the selectedLocationList
	 */
	public List getSelectedLocationList() {
		return selectedLocationList;
	}
	/**
	 * @param selectedLocationList the selectedLocationList to set
	 */
	public void setSelectedLocationList(List selectedLocationList) {
		this.selectedLocationList = selectedLocationList;
	}
	/**
	 * @return the regionId
	 */
	public String getRegionId() {
		return regionId;
	}
	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	/**
	 * @return the regionDesc
	 */
	public String getRegionDesc() {
		return regionDesc;
	}
	/**
	 * @param regionDesc the regionDesc to set
	 */
	public void setRegionDesc(String regionDesc) {
		this.regionDesc = regionDesc;
	}
	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
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
	 * @return the programPrice
	 */
	public String getProgramPrice() {
		return programPrice;
	}
	/**
	 * @param programPrice the programPrice to set
	 */
	public void setProgramPrice(String programPrice) {
		this.programPrice = programPrice;
	}
	
}// END CLASS
