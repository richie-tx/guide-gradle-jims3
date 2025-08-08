/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IPhoneNumber;
import mojo.km.utilities.DateUtil;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AppointmentInfoBean 
{
	 private String officerName;
	 private String positionPOI;
	 private String positionName;
	 private String programUnitDesc;
	 private IPhoneNumber officerPhone;
	 
	 private boolean programLocationExist;
	 
	 private String superviseeName;
	 private String defendantId;
	 private IPhoneNumber superviseePhone;
	 private String offenseDesc;
	 private Date dob;
	 private String dobAsStr;
	 private String crt;
	 
	 private String serviceProviderName;
	 private IPhoneNumber serviceProviderPhone;
	 private String serviceProviderURL;
	 
	 private String scheduleDateStr;
	 private String weekDay;
	 private String scheduleTime;
	 
	 private CSCLocationInfoBean programLocationBean;
	 
	 private String programName;
	 private List contactsList;
	 private String officeHours;
	 
	 
	 
	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return the positionPOI
	 */
	public String getPositionPOI() {
		return positionPOI;
	}
	/**
	 * @param positionPOI the positionPOI to set
	 */
	public void setPositionPOI(String positionPOI) {
		this.positionPOI = positionPOI;
	}
	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}
	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @return the programUnitDesc
	 */
	public String getProgramUnitDesc() {
		return programUnitDesc;
	}
	/**
	 * @param programUnitDesc the programUnitDesc to set
	 */
	public void setProgramUnitDesc(String programUnitDesc) {
		this.programUnitDesc = programUnitDesc;
	}
	/**
	 * @return the officerPhone
	 */
	public IPhoneNumber getOfficerPhone() {
		return officerPhone;
	}
	/**
	 * @param officerPhone the officerPhone to set
	 */
	public void setOfficerPhone(IPhoneNumber officerPhone) {
		this.officerPhone = officerPhone;
	}
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the superviseePhone
	 */
	public IPhoneNumber getSuperviseePhone() {
		return superviseePhone;
	}
	/**
	 * @param superviseePhone the superviseePhone to set
	 */
	public void setSuperviseePhone(IPhoneNumber superviseePhone) {
		this.superviseePhone = superviseePhone;
	}
	/**
	 * @return the offenseDesc
	 */
	public String getOffenseDesc() {
		return offenseDesc;
	}
	/**
	 * @param offenseDesc the offenseDesc to set
	 */
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}
	/**
	 * @return the dob
	 */
	public Date getDob()
	{
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob)
	{
		this.dob = dob;
		this.dobAsStr = "";
		if(dob!=null)
		{
			try
			{
				this.dobAsStr = DateUtil.dateToString(dob, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.dobAsStr = "";
			}
		}
	}
	/**
	 * @return the dobAsStr
	 */
	public String getDobAsStr() 
	{
		return dobAsStr;
	}
	/**
	 * @param dobAsStr the dobAsStr to set
	 */
	public void setDobAsStr(String dobAsStr) {
		this.dobAsStr = dobAsStr;
	}
	/**
	 * @return the crt
	 */
	public String getCrt() {
		return crt;
	}
	/**
	 * @param crt the crt to set
	 */
	public void setCrt(String crt) {
		this.crt = crt;
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
	 * @return the serviceProviderPhone
	 */
	public IPhoneNumber getServiceProviderPhone() {
		return serviceProviderPhone;
	}
	/**
	 * @param serviceProviderPhone the serviceProviderPhone to set
	 */
	public void setServiceProviderPhone(IPhoneNumber serviceProviderPhone) {
		this.serviceProviderPhone = serviceProviderPhone;
	}
	/**
	 * @return the serviceProviderURL
	 */
	public String getServiceProviderURL() {
		return serviceProviderURL;
	}
	/**
	 * @param serviceProviderURL the serviceProviderURL to set
	 */
	public void setServiceProviderURL(String serviceProviderURL) {
		this.serviceProviderURL = serviceProviderURL;
	}
	/**
	 * @return the scheduleDateStr
	 */
	public String getScheduleDateStr() {
		return scheduleDateStr;
	}
	/**
	 * @param scheduleDateStr the scheduleDateStr to set
	 */
	public void setScheduleDateStr(String scheduleDateStr) {
		this.scheduleDateStr = scheduleDateStr;
	}
	/**
	 * @return the weekDay
	 */
	public String getWeekDay() {
		return weekDay;
	}
	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	/**
	 * @return the scheduleTime
	 */
	public String getScheduleTime() {
		return scheduleTime;
	}
	/**
	 * @param scheduleTime the scheduleTime to set
	 */
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
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
	 * @return the contactsList
	 */
	public List getContactsList() {
		return contactsList;
	}
	/**
	 * @param contactsList the contactsList to set
	 */
	public void setContactsList(List contactsList) {
		this.contactsList = contactsList;
	}
	/**
	 * @return the officeHours
	 */
	public String getOfficeHours() {
		return officeHours;
	}
	/**
	 * @param officeHours the officeHours to set
	 */
	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}
	/**
	 * @return the programLocationExist
	 */
	public boolean isProgramLocationExist() {
		return programLocationExist;
	}
	/**
	 * @param programLocationExist the programLocationExist to set
	 */
	public void setProgramLocationExist(boolean programLocationExist) {
		this.programLocationExist = programLocationExist;
	}
}
