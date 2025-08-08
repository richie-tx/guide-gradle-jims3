//created 6/22/2015
package ui.juvenilecase.schedulecalendarevent.to;

import java.util.Date;
import java.util.List;


public class AdjudicationNotificationTO
{
	//items used for report
	private Date currentDate ;
	private String juvenileFullName;
	private String birthDate;
	private List offenseInvolvedWeaponList;
	private String officerPhone;
	
	//items used for form and taken from the  ScheduleNewEventForm's subclass
	private String schoolDistrictName ;
	private String schoolName ;
	private String schoolStreet;
	private String schoolCity;
	private String schoolState;
	private String schoolZip;
	private String sexOffenderRegistrantStr;
	private String restrictionsOther;
	private String contactFirstName;
	private String contactLastName;
		//Current Event Data Used in the subclass
	private String eventDateStr ;
	private String eventTime ;
	
	
	public AdjudicationNotificationTO()
	{
		currentDate = new Date( ) ;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getJuvenileFullName() {
		return juvenileFullName;
	}
	public void setJuvenileFullName(String juvenileFullName) {
		this.juvenileFullName = juvenileFullName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public List getOffenseInvolvedWeaponList() {
		return offenseInvolvedWeaponList;
	}
	public void setOffenseInvolvedWeaponList(List offenseInvolvedWeaponList) {
		this.offenseInvolvedWeaponList = offenseInvolvedWeaponList;
	}
	public String getOfficerPhone() {
		return officerPhone;
	}
	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}

	public String getSexOffenderRegistrantStr() {
		return sexOffenderRegistrantStr;
	}

	public void setSexOffenderRegistrantStr(String sexOffenderRegistrantStr) {
		this.sexOffenderRegistrantStr = sexOffenderRegistrantStr;
	}

	public String getRestrictionsOther() {
		return restrictionsOther;
	}

	public void setRestrictionsOther(String restrictionsOther) {
		this.restrictionsOther = restrictionsOther;
	}

	public String getSchoolDistrictName() {
		return schoolDistrictName;
	}

	public void setSchoolDistrictName(String schoolDistrictName) {
		this.schoolDistrictName = schoolDistrictName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolStreet() {
		return schoolStreet;
	}

	public void setSchoolStreet(String schoolStreet) {
		this.schoolStreet = schoolStreet;
	}

	public String getSchoolCity() {
		return schoolCity;
	}

	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	public String getSchoolState() {
		return schoolState;
	}

	public void setSchoolState(String schoolState) {
		this.schoolState = schoolState;
	}

	public String getSchoolZip() {
		return schoolZip;
	}

	public void setSchoolZip(String schoolZip) {
		this.schoolZip = schoolZip;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getEventDateStr() {
		return eventDateStr;
	}

	public void setEventDateStr(String eventDateStr) {
		this.eventDateStr = eventDateStr;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	
}
