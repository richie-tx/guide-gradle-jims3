/**
 * 
 */
package ui.juvenilecase.facility;

import java.util.Collection;
import java.util.Date;

import ui.common.Address;

import messaging.juvenile.reply.JuvenileJobResponseEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;

/**
 * @author ugopinath
 *
 */
public class JuvenileFacilityReportBean {

	private String juvenileNum;	
	private String juvenileName;
	private String race;
	private String hispanic;
	private String gender;
	private String age;
	private String dob;
	private String verifiedDOB ;
	private String juvReligiousPreference;
	private String livesWith;
	private String maritalStatus;
	private String threeLettersOfLastname;
	
	private String admitDateStr;
	private String admitTime;
	private String admitAuthority;
	private String admitBy;
	private String detainedFacility;
	private String secureStatus;
	private String bookingReferral;
	private String bookingOffense;		
	private String bookingSupervisionNum;	
	private String referralSource;	
	private String referralOfficers;	
	private String valuablesReceipt;
	private String locker;		
	private String admitReason;	
	private Date detainedDate;		
	private String admissionComments;
	private String floorLocation;
	private String unitLocation;
	private String roomLocation;
	private String draft = "";
	private String spAttention;
	private String saReason;
		
	private String enrollmentStatus;
	private JuvenileSchoolHistoryResponseEvent schoolHistory = new JuvenileSchoolHistoryResponseEvent();
	private JuvenileJobResponseEvent employmentDet = new JuvenileJobResponseEvent();
	
	private Collection detentionTraits;
	private JuvenilePhysicalAttributesResponseEvent physicalAttribute = new JuvenilePhysicalAttributesResponseEvent();	
	private Address memberAddress ;
	private Collection relatives ;
	private Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacList;
	private Collection saReasons;
	
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getJuvenileName() {
		return juvenileName;
	}
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getHispanic() {
		return hispanic;
	}
	public void setHispanic(String hispanic) {
		this.hispanic = hispanic;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	public String getJuvReligiousPreference() {
		return juvReligiousPreference;
	}
	public void setJuvReligiousPreference(String juvReligiousPreference) {
		this.juvReligiousPreference = juvReligiousPreference;
	}
	public String getAdmitDateStr() {
		return admitDateStr;
	}
	public void setAdmitDateStr(String admitDateStr) {
		this.admitDateStr = admitDateStr;
	}
	public String getAdmitTime() {
		return admitTime;
	}
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	public String getAdmitAuthority() {
		return admitAuthority;
	}
	public void setAdmitAuthority(String admitAuthority) {
		this.admitAuthority = admitAuthority;
	}
	public String getAdmitBy() {
		return admitBy;
	}
	public void setAdmitBy(String admitBy) {
		this.admitBy = admitBy;
	}
	public String getDetainedFacility() {
		return detainedFacility;
	}
	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}
	public String getSecureStatus() {
		return secureStatus;
	}
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}
	public String getBookingReferral() {
		return bookingReferral;
	}
	public void setBookingReferral(String bookingReferral) {
		this.bookingReferral = bookingReferral;
	}
	public String getBookingOffense() {
		return bookingOffense;
	}
	public void setBookingOffense(String bookingOffense) {
		this.bookingOffense = bookingOffense;
	}
	public String getBookingSupervisionNum() {
		return bookingSupervisionNum;
	}
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		this.bookingSupervisionNum = bookingSupervisionNum;
	}
	public String getReferralSource() {
		return referralSource;
	}
	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}
	public String getReferralOfficers() {
		return referralOfficers;
	}
	public void setReferralOfficers(String referralOfficers) {
		this.referralOfficers = referralOfficers;
	}
	public String getValuablesReceipt() {
		return valuablesReceipt;
	}
	public void setValuablesReceipt(String valuablesReceipt) {
		this.valuablesReceipt = valuablesReceipt;
	}
	public String getLocker() {
		return locker;
	}
	public void setLocker(String locker) {
		this.locker = locker;
	}
	public String getAdmitReason() {
		return admitReason;
	}
	public void setAdmitReason(String admitReason) {
		this.admitReason = admitReason;
	}
	public Date getDetainedDate() {
		return detainedDate;
	}
	public void setDetainedDate(Date detainedDate) {
		this.detainedDate = detainedDate;
	}
	public String getAdmissionComments() {
		return admissionComments;
	}
	public void setAdmissionComments(String admissionComments) {
		this.admissionComments = admissionComments;
	}
	public String getFloorLocation() {
		return floorLocation;
	}
	public void setFloorLocation(String floorLocation) {
		this.floorLocation = floorLocation;
	}
	public String getUnitLocation() {
		return unitLocation;
	}
	public void setUnitLocation(String unitLocation) {
		this.unitLocation = unitLocation;
	}
	public String getRoomLocation() {
		return roomLocation;
	}
	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}
	/*public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolDistrict() {
		return schoolDistrict;
	}
	public void setSchoolDistrict(String schoolDistrict) {
		this.schoolDistrict = schoolDistrict;
	}
	public String getCurrentGradeLevel() {
		return currentGradeLevel;
	}
	public void setCurrentGradeLevel(String currentGradeLevel) {
		this.currentGradeLevel = currentGradeLevel;
	}
	public String getProgramAttending() {
		return programAttending;
	}
	public void setProgramAttending(String programAttending) {
		this.programAttending = programAttending;
	}
	public String getSchoolAttendanceStatus() {
		return schoolAttendanceStatus;
	}
	public void setSchoolAttendanceStatus(String schoolAttendanceStatus) {
		this.schoolAttendanceStatus = schoolAttendanceStatus;
	}*/
	public Collection getDetentionTraits() {
		return detentionTraits;
	}
	public void setDetentionTraits(Collection detentionTraits) {
		this.detentionTraits = detentionTraits;
	}
	public JuvenilePhysicalAttributesResponseEvent getPhysicalAttribute() {
		return physicalAttribute;
	}
	public void setPhysicalAttribute(
			JuvenilePhysicalAttributesResponseEvent physicalAttribute) {
		this.physicalAttribute = physicalAttribute;
	}
	public Address getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(Address memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	public Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvFacList() {
		return juvFacList;
	}
	public void setJuvFacList(
			Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacList) {
		this.juvFacList = juvFacList;
	}
	
	public String getVerifiedDOB() {
		return verifiedDOB;
	}
	public void setVerifiedDOB(String verifiedDOB) {
		this.verifiedDOB = verifiedDOB;
	}
	public JuvenileSchoolHistoryResponseEvent getSchoolHistory() {
		return schoolHistory;
	}
	public void setSchoolHistory(JuvenileSchoolHistoryResponseEvent schoolHistory) {
		this.schoolHistory = schoolHistory;
	}
	public String getLivesWith() {
		return livesWith;
	}
	public void setLivesWith(String livesWith) {
		this.livesWith = livesWith;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public JuvenileJobResponseEvent getEmploymentDet() {
		return employmentDet;
	}
	public void setEmploymentDet(JuvenileJobResponseEvent employmentDet) {
		this.employmentDet = employmentDet;
	}
	public Collection getRelatives() {
		return relatives;
	}
	public void setRelatives(Collection relatives) {
		this.relatives = relatives;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSpAttention() {
		return spAttention;
	}
	public void setSpAttention(String spAttention) {
		this.spAttention = spAttention;
	}
	public String getSaReason() {
		return saReason;
	}
	public void setSaReason(String saReason) {
		this.saReason = saReason;
	}
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	public String getThreeLettersOfLastname() {
		return threeLettersOfLastname;
	}
	public void setThreeLettersOfLastname(String threeLettersOfLastname) {
		this.threeLettersOfLastname = threeLettersOfLastname;
	}
	public Collection getSaReasons() {
		return saReasons;
	}
	public void setSaReasons(Collection saReasons) {
		this.saReasons = saReasons;
	}
	
	
	
}
